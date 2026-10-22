# 1. أولًا: إيه هي Collections في Java؟

كلمة Collections ممكن تقابلك بمعنيين مختلفين:

1. ال **Collections Framework:** مجموعة من الـ interfaces والـ classes اللي Java بتوفرها علشان تخزّن البيانات وتتعامل معاها. و دي شرحناها خلاص
2. ال **`Collections` class:** كلاس جاهز في `java.util` فيه methods بتساعدك تتعامل مع الـ collections، زي الترتيب والعكس وإيجاد أكبر وأصغر عنصر.


```java
List<Integer> numbers = new ArrayList<>();

numbers.add(5);
numbers.add(2);
numbers.add(8);
numbers.add(1);

Collections.sort(numbers);
System.out.println(numbers); // [1, 2, 5, 8]
```

---
# 2. أشهر Methods في `Collections`

الـ `Collections` class موجود في:

```java
import java.util.Collections;
```

وغالبًا هتستخدمه مع `List`، وخصوصًا `ArrayList`.

## ① `Collections.sort()`

بترتّب العناصر تصاعديًا حسب الـ Natural Ordering بتاعها.

```java
List<Integer> numbers = new ArrayList<>(
    List.of(5, 2, 8, 1, 3)
);

Collections.sort(numbers);

System.out.println(numbers); // [1, 2, 3, 5, 8]
```

طيب لو عايز ترتيب تنازلي؟

```java
Collections.sort(numbers, Collections.reverseOrder());

System.out.println(numbers); //[8, 5, 3, 2, 1]
```

**خد بالك:** `Collections.sort()` بتعدّل ترتيب العناصر في الـ List الأصلية، مش بترجع List جديدة.

---
## ② `Collections.reverse()`

بتعكس ترتيب العناصر الموجودة بالفعل، من غير ما تعمل Sorting.

```java
List<Integer> numbers = new ArrayList<>(
    List.of(5, 2, 8, 1)
);

Collections.reverse(numbers);

System.out.println(numbers);
```

|Method|الوظيفة|
|---|---|
|`Collections.sort(list)`|ترتب العناصر|
|`Collections.reverse(list)`|تعكس ترتيب العناصر الحالي|
|`Collections.shuffle(list)`|تغيّر ترتيب العناصر عشوائيًا|

---
## ④ `Collections.max()` و`Collections.min()`

دول بيجيبوا أكبر وأصغر عنصر في الـ Collection حسب الـ Natural Ordering أو الـ Comparator اللي تحدده.

```java
List<Integer> numbers = List.of(5, 2, 8, 1, 3);

System.out.println(Collections.max(numbers));
System.out.println(Collections.min(numbers));
```

**ملحوظة:** لو الـ Collection فاضية، `max()` و`min()` بيرموا `NoSuchElementException`.

---
## ⑤ `Collections.frequency()`

 بتحسب عدد مرات ظهور عنصر معين.

```java
List<Integer> numbers = List.of(1, 2, 2, 3, 2, 4, 1);

int count = Collections.frequency(numbers, 2);

System.out.println(count);
```
---

## ⑥ `Collections.binarySearch()`


بتبحث عن عنصر باستخدام **Binary Search**، بشرط إن الـ List تكون مترتبة بنفس طريقة البحث المستخدمة.

```java
List<Integer> numbers = new ArrayList<>(
    List.of(10, 20, 30, 40, 50)
);

int index = Collections.binarySearch(numbers, 30);

System.out.println(index); // 2
```

ليه `2`؟ لأن الـ Index بتاع الرقم `30` هو `2`.
### طيب لو العنصر مش موجود؟
 هيرجع رقمًا سالبًا.
---
## ⑦ `Collections.swap()`

بتبدّل مكان عنصرين باستخدام الـ Index.

```java
List<Integer> numbers = new ArrayList<>(
    List.of(10, 20, 30, 40)
);

Collections.swap(numbers, 0, 2);

System.out.println(numbers); // [30, 20, 10, 40]
```
---
## ⑧ `Collections.copy()`

بتنسخ عناصر List إلى List تانية موجودة بالفعل.

```java
List<Integer> source = List.of(10, 20, 30);

List<Integer> destination = new ArrayList<>(
    Arrays.asList(0, 0, 0)
);

Collections.copy(destination, source);

System.out.println(destination); //[10, 20, 30]
```

**أهم نقطة هنا:** الـ `destination` لازم تكون موجودة بالفعل، وحجمها لازم يكون أكبر من أو يساوي حجم الـ `source`.

---
## ⑨ `Collections.fill()`

بتستبدل كل العناصر الموجودة في الـ List بقيمة واحدة.

```java
List<Integer> numbers = new ArrayList<>(
    Arrays.asList(1, 2, 3, 4)
);

Collections.fill(numbers, 0);

System.out.println(numbers); // [0, 0, 0, 0]
```
---
## ⑩ `Collections.replaceAll()`

بتستبدل كل ظهور لقيمة معينة بقيمة تانية.

```java
List<Integer> numbers = new ArrayList<>(
    List.of(1, 2, 2, 3, 2)
);

Collections.replaceAll(numbers, 2, 9);

System.out.println(numbers); // [1, 9, 9, 3, 9]
```

---

# 3. ال `Collections.unmodifiableList()`

أحيانًا بتكون عايز تسمح لشخص أو جزء من البرنامج إنه يقرأ الـ List، لكن **ما تسمحلوش إنه يغيّرها من خلال الـ List دي**.

```java
List<Integer> numbers = new ArrayList<>(
    List.of(10, 20, 30)
);

List<Integer> readOnlyNumbers =
    Collections.unmodifiableList(numbers);

System.out.println(readOnlyNumbers);
```

لو حاولت تعمل:

```java
readOnlyNumbers.add(40);
```

هتحصل على:

```java
UnsupportedOperationException
```

لأن الـ List دي غير قابلة للتعديل من خلال الـ reference ده.
### بس فيه تفصيلة مهمة جدًا!

الـ `unmodifiableList()` مش بتعمل نسخة منفصلة من البيانات؛ دي بتعمل **View** غير قابلة للتعديل من خلال الـ View نفسها.

```java
numbers.add(40);

System.out.println(readOnlyNumbers); //[10, 20, 30, 40]
```

ليه؟ لأن الـ `numbers` الأصلية اتعدّلت، والـ `readOnlyNumbers` لسه بتشوف نفس البيانات.

لو محتاج نسخة غير قابلة للتعديل ومستقلة عن الـ List الأصلية، ممكن تعمل:

```java
List<Integer> copy =
    Collections.unmodifiableList(new ArrayList<>(numbers));
```

أو تستخدم `List.copyOf(numbers)` لو عايز List غير قابلة للتعديل، مع العلم إن `List.copyOf()` بترفض العناصر `null`.

ومهم تعرف إن عدم قابلية التعديل هنا بتخص الـ List نفسها، مش بالضرورة حالة الكائنات الموجودة جواها لو كانت كائنات قابلة للتغيير.

---

# 4. هل `Collections` بتشتغل مع `Set` و`Queue` و`Map`؟

رغم إن اسم الكلاس `Collections`، مش كل الـ methods بتاعته بتشتغل مع كل أنواع الـ Collections.

|النوع|أمثلة على الاستخدام|
|---|---|
|`List`|`sort()`، `reverse()`، `shuffle()`، `swap()`، `binarySearch()`|
|`Set`|ممكن تستخدم بعض الـ methods العامة زي `min()` و`max()` و`frequency()`|
|`Queue`|ممكن تستخدم بعض الـ methods العامة، حسب نوع الـ Queue|
|`Map`|مش بتتعامل معاها الـ methods العامة دي باعتبارها Collection مباشرة|

---
و بس كده :) 