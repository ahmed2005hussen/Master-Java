- في جافا 21 عملوا ال SequencedCollection , SequencedSet and SequecedMap interfaces 

![](../../images/Pasted%20image%2020261012081922.png)

## يعني إيه Sequenced Collection؟

هو عباره عن interface بيوفر مجموعه من ال functions علشان نوفر طريقة موحدة للتعامل مع Collections اللي عندها ترتيب محدد، خصوصًا الوصول لأول عنصر وآخر عنصر وعكس ترتيب العناصر.
![](../../images/Pasted%20image%2020261012082551.png)

- فا هنلاقي انه هنا ضافلي شويه functions علشان اقدر اتعامل مع العناصر اللي في الاول و اللي في الاخر او اني اعمل reverse
- ال SequencedCollection هو بي extends من ال Collection و ده معناه ان اي impl هيجي لازم يعمل impl لل functions اللي موجوده في ال Collection و ال SequencedCollection 

ملحوظه مهمه : 
- و لكن علشان مش كل ال Data structure بيكون فيها فكره اننا نوصل لاول عنصر او لاخر عنصر او نعكس الترتيب وكده 
- فا مش منطقي اننا نخلي ال data structures دي تعمل impl لحاجه هي اصلا مش هتستعملها 
- و علشان هنلاقي ان ال functions اللي في ال Sequenced Collection معموله default impl بالشكل ده : 
  ```java 
  default void addFirst(E e) {
        throw new UnsupportedOperationException();
    }
  ```

- و بكده لو اي data structure حبت انها تعمل impl للحاجات دي هتعملها عادي جدا و تحط ال logic بتاعها 
- و لكن لو حاجه مش المفروض انها يبقا عندها الكلام ده , فا مش هتعمل impl ليها و لو حاولنا ننادي عليها هيحصل exception by default 
  
  - لو اخدنا بالنا من التصميم هنلاقي ان الحاجات الاساسيه اللي هتبقي موجوده في كل ال data structure محطوطه في ال collection 
  - و الحاجات التانيه الاضافيه محطوطه في ال sequenced collection
  - و بالتالي لو عندنا data structure بتقبل ال logic اللي في ال sequenced فا ساعتها هي هتعمل implements لل sequenced و ساعتها هي كأنها بتعمل impl للاتنين ال collection and Sequenced 
  - و لو في data structure مش عايزه الحاجات اللي في ال sequenced فا هيبقي كافي انها تعمل impls لل Collection بس 

---
## ليه Java احتاجت تضيف الـ Feature دي أصلًا؟

قبل Java 21، كان عندك أكتر من Interface للتعامل مع البيانات، وكل واحدة عندها قدرات مختلفة.
### أولا: الـ List

```java
List<Integer> numbers = new ArrayList<>();

numbers.add(10);
numbers.add(20);
numbers.add(30);

System.out.println(numbers.get(0)); // 10
System.out.println(numbers.get(2)); // 30
```

- الـ `List` عندها `get(index)`، وبالتالي الوصول للعناصر عن طريق الـ index سهل.
- لكن مش كل Collection عندها `get(index)`.
### ثانيًا: الـ Deque

```java
Deque<Integer> numbers = new ArrayDeque<>();

numbers.addFirst(10);
numbers.addLast(20);
numbers.addLast(30);

System.out.println(numbers.getFirst()); // 10
System.out.println(numbers.getLast());  // 30
```

لاحظ إن الـ `Deque` عندها بالفعل Methods للتعامل مع أول وآخر عنصر.
### ثالثًا: الـ Set

```java
Set<Integer> numbers = new LinkedHashSet<>();

numbers.add(10);
numbers.add(20);
numbers.add(30);
```

الـ `LinkedHashSet` بتحافظ على ترتيب الإضافة، لكن الـ `Set` Interface نفسها مكانش عندها Methods موحدة للوصول لأول وآخر عنصر.
كان لازم تعتمد على الـ implementation أو تستخدم الـ `Iterator`.

### طيب، إيه المشكلة اللي Java 21 حلتها؟

المشكلة كانت إن **الـ Collections اللي عندها ترتيب محدد مكانش عندها Interface مشتركة بتوفر عمليات البداية والنهاية وعكس الترتيب**.

| العملية               | `List` قبل Java 21                 | `Deque` قبل Java 21                | `Set` قبل Java 21                  |
| --------------------- | ---------------------------------- | ---------------------------------- | ---------------------------------- |
| الوصول لأول عنصر      | `get(0)`                           | `getFirst()`                       | مفيش Method موحدة                  |
| الوصول لآخر عنصر      | `get(size() - 1)`                  | `getLast()`                        | مفيش Method موحدة                  |
| إضافة عنصر في البداية | `add(0, element)`                  | `addFirst()`                       | مفيش Method موحدة                  |
| حذف أول عنصر          |  `remove(0)`                       | `removeFirst()`                    | مفيش Method موحدة                  |
| عكس ترتيب العناصر     | مفيش Method موحدة في الـ Interface | مفيش Method موحدة في الـ Interface | مفيش Method موحدة في الـ Interface |

---
## 3. شكل الـ Collection Hierarchy بعد Java 21

قبل Java 21، كان الشكل المبسط كده:
```
                Iterable
                   |
               Collection
              /     |     \
           List    Set    Queue
                           |
                          Deque
```

بعد Java 21، بقى عندنا Interface جديدة اسمها `SequencedCollection`:

```
                    Iterable
                       |
                   Collection
                       |
               SequencedCollection
                  /           \
                List          Deque

               SequencedSet
                   |
                  Set
```

خلينا نكتب الـ Interface الأساسية بشكل مبسط:

```java
public interface SequencedCollection<E> extends Collection<E> {

    void addFirst(E element);

    void addLast(E element);

    E getFirst();

    E getLast();

    E removeFirst();

    E removeLast();

    SequencedCollection<E> reversed();
}
```

لاحظ إن:

```java
extends Collection<E>
```

معناها إن `SequencedCollection` بتورث كل الـ Methods اللي موجودة في `Collection`، وبتضيف عليها Methods جديدة.

يعني هتقدر تستخدم:

```java
size();
isEmpty();
contains();
iterator();
stream();
```

بالإضافة إلى:

```java
getFirst();
getLast();
reversed();
```

وده مثال مهم على دور الـ Interfaces في تصميم Java: بدل ما تكرر نفس مجموعة الـ Methods في كل Interface، بتجمع السلوك المشترك في Interface واحدة.

---
# شرح الـ Methods الجديدة بالتفصيل

هنستخدم المثال ده في الشرح:

```java
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<String> names = new ArrayList<>();

        names.add("Ahmed");
        names.add("Mohamed");
        names.add("Ali");

        System.out.println(names); // [Ahmed, Mohamed, Ali]
    }
}
```
## Method 1: `getFirst()`

بترجع أول عنصر في الـ Collection.

```java 
System.out.println(names.getFirst()); // Ahmed
```

قبل Java 21، لو عندك `List` كنت ممكن تكتب:

```java
names.get(0);
```

**خلي بالك:**
- لو الـ Collection فاضية، `getFirst()` بترمي `NoSuchElementException`.

```java
List<String> names = new ArrayList<>();

System.out.println(names.getFirst()); // NoSuchElementException
```

## Method 2: `getLast()`

بترجع آخر عنصر في الـ Collection.

```java
System.out.println(names.getLast()); // Ali 
```

قبل Java 21، كنت هتكتب مع الـ `List`:

```java
names.get(names.size() - 1);
```

وبرضه لو الـ Collection فاضية، هتحصل على `NoSuchElementException`.

---
## Method 3: `addFirst()`

بتضيف عنصر في بداية الـ Collection.

لو عندنا:

```java
List<String> names = new ArrayList<>(
        List.of("Ahmed", "Mohamed", "Ali")
);
```

وكتبنا:

```java
names.addFirst("Omar");

System.out.println(names);// [Omar, Ahmed, Mohamed, Ali]
```

قبل Java 21، كنت هتستخدم مع الـ `List`:

```java
names.add(0, "Omar");
```

**ملحوظة مهمة:** مش كل `SequencedCollection` بتدعم الإضافة في البداية. بعض الـ Collections غير القابلة للتعديل بترمي `UnsupportedOperationException`، والـ `SortedSet` زي `TreeSet` مش بتدعم تحديد مكان الإضافة بالشكل ده.
## Method 4: `addLast()`

بتضيف عنصر في نهاية الـ Collection.

```java
names.addLast("Mostafa");

System.out.println(names);
```

---
## Method 5: `removeFirst()`

بتحذف أول عنصر وبترجعه في نفس الوقت.

```java
List<String> names = new ArrayList<>(
        List.of("Ahmed", "Mohamed", "Ali")
);

String removed = names.removeFirst();

System.out.println(removed); //Ahmed
System.out.println(names); //[Mohamed, Ali]
```

لكن لو القائمة فاضية، هتحصل على `NoSuchElementException`.

## Method 6: `removeLast()`

نفس الفكرة، لكن بتحذف آخر عنصر.

```java
String removed = names.removeLast();
```

---
# 5. أهم Method في الموضوع: `reversed()`


```java
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<Integer> numbers = new ArrayList<>(
                List.of(10, 20, 30, 40, 50)
        );

        System.out.println(numbers); // [10, 20, 30, 40, 50]

        System.out.println(numbers.reversed()); // [50, 40, 30, 20, 10]

        System.out.println(numbers); // [10, 20, 30, 40, 50]
    }
}
```

- ال **`reversed()` مش بتعكس ترتيب الـ List الأصلية نفسها.**
- هي بترجع حاجة اسمها **Reverse-ordered View**، يعني View بتعرض نفس الـ Collection بترتيب معكوس.
### طيب، إيه معنى View؟
تخيل إن عندك كتاب بتقرأه من أول صفحة لآخر صفحة.
بدل ما تنسخ الكتاب كله وتحط الصفحات بالترتيب العكسي، إنت بتستخدم طريقة عرض بتخليك تقرأه من آخر صفحة لأول صفحة.

ده تقريبًا مفهوم الـ View هنا.

عندك الـ Collection الأصلية:

```
10 → 20 → 30 → 40 → 50
```

والـ Reverse View:

```
50 → 40 → 30 → 20 → 10
```

الاتنين مرتبطين ببعض، لكن كل واحدة بتعرض ترتيب العناصر من اتجاه مختلف.

### تجربة مهمة: التعديل على الـ Reverse View

```java
List<Integer> numbers = new ArrayList<>(
        List.of(10, 20, 30, 40, 50)
);

List<Integer> reversed = numbers.reversed();

reversed.set(0, 99);

System.out.println(numbers); // [10, 20, 30, 40, 99]
System.out.println(reversed); // [99, 40, 30, 20, 10]
```

ليه حصل كده؟

لأن أول عنصر في الـ `reversed` هو نفسه آخر عنصر في القائمة الأصلية.

فلما عملنا:

```java
reversed.set(0, 99);
```

إحنا غيّرنا العنصر الموجود في آخر القائمة الأصلية.
**يعني التعديل على الـ View ممكن يغيّر الـ Collection الأصلية، والعكس صحيح.**

---
### طيب، لو عايز نسخة مستقلة بترتيب معكوس؟

هنا ممكن تعمل:

```java
List<Integer> reversedCopy =
        new ArrayList<>(numbers.reversed());
```

كده أنشأت `ArrayList` جديدة بالعناصر بالترتيب المعكوس.

لو عدلت في `reversedCopy` بعد كده، التعديل مش هيغيّر ترتيب عناصر `numbers`.

### استخدام `reversed()` مع الـ Enhanced For Loop

```java
for (int number : numbers.reversed()) {
    System.out.println(number);
}
```

لو القائمة الأصلية:

```java
[10, 20, 30, 40, 50]
```

هيطبع:

```shell
50
40
30
20
10
```
---
و بس كده نقدر نقول اخيرا ان ال Sequenced collection ما هو ال abstraction and unified علشان نواحد طريقه عمل شويه ds مع اول و اخر عنصر سواء من اضافه او مسح او قراءه و كمان بتخلينا نقدر نعمل reverse .



##  طيب، إيه هي `SequencedSet`؟

```java
public interface SequencedSet<E> extends SequencedCollection<E>, Set<E> {

    SequencedSet<E> reversed();
}

```

الفرق الوحيد هنا اننا بدل ما نخلي الفانكشن دي ترجع SequencedCollection خليناها ترجع SequencedSet و ده علشان نعمل abstract اكتر لكل حاجه علي حدي , لان مثلا ال list ليها طرق مختلفه عن ال set 

---
# طيب، إيه هي `SequencedMap`؟

ال map مش بتخزن عنصر هي بتخزن Entry 
```java
Key → Value
```

علشان كده Java 21 أضافت **`SequencedMap<K, V>`** لان ال map ليها خصائص مختلفه عن ال collections

![](../../images/Pasted%20image%2020261012091121.png)

### 1. `firstEntry()`

```java
students.firstEntry(); // 101=Ahmed
```

بترجع أول `Map.Entry`، يعني أول زوج من Key وValue حسب ترتيب الـ Map.

النوع اللي بترجعه هو:

```java
Map.Entry<Integer, String>
```

تقدر تستفيد منه كده:

```java
Map.Entry<Integer, String> first = students.firstEntry();

System.out.println(first.getKey()); // 101
System.out.println(first.getValue()); // Ahmed
```

### 2. `lastEntry()`

```java
students.lastEntry();//103=Ali
```

بترجع آخر Entry حسب ترتيب الـ Map.

### 3. `pollFirstEntry()`

دي بتعمل حاجتين:

- بترجع أول Entry.
- بتحذف الـ Entry دي من الـ Map.

```java
Map.Entry<Integer, String> first = students.pollFirstEntry();

System.out.println(first);// 101=Ahmed
System.out.println(students);// {102=Mohamed, 103=Ali}
```

### 4. `pollLastEntry()`

نفس الفكرة، لكن مع آخر Entry.

```java
Map.Entry<Integer, String> last = students.pollLastEntry();
```

هترجع آخر Entry وتحذفها من الـ Map.

### 5. `reversed()`

دي بتوفر Reverse View للـ Map.

```java
System.out.println(students.reversed());
```

لو الـ Map الأصلية:

```java
{101=Ahmed, 102=Mohamed, 103=Ali}
```

الـ Reverse View هتعرض:

```java
{103=Ali, 102=Mohamed, 101=Ahmed}
```

---
### `sequencedKeySet()`

بترجع View للـ Keys بنوع `SequencedSet`.

```java
[101, 102, 103]
System.out.println(students.sequencedKeySet()); //
```

الميزة هنا إنك تقدر تتعامل مع الـ Keys بترتيبها، وتستخدم العمليات المناسبة للـ `SequencedSet`.
### `sequencedValues()`

بترجع View للـ Values بنوع `SequencedCollection`.

```java
System.out.println(students.sequencedValues()); // [Ahmed, Mohamed, Ali]
```

### `sequencedEntrySet()`

بترجع View للـ Entries بنوع `SequencedSet`.

```java
System.out.println(students.sequencedEntrySet());
```

النتيجة:

```
[101=Ahmed, 102=Mohamed, 103=Ali]
```

الـ Views دي مرتبطة بالـ Map الأصلية، وترتيبها بيعكس ترتيب الـ Entries فيها.

---

و بس كده خلصنا فا الفكره بسيطه فيهم كلهم مجرد functions زياده جت تحل مشاكل كانت قديمه في ال design و تسهل شغل اكتر و شكرا :) 