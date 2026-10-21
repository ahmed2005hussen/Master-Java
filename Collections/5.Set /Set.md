

- ال set مش بتسمح بالتكرار 
- الـ `Set` هي Interface في Java Collections Framework، فا هي بت extends ال Collection 

```
Iterable
   |
Collection
   |
   Set
```

بالتالي، الـ `Set` بتدعم معظم الـ methods اللي اتكلمنا عنها في `Collection`، زي:

|Method|وظيفتها|
|---|---|
|`add(e)`|إضافة عنصر، لو مش موجود بالفعل|
|`addAll(c)`|إضافة عناصر Collection تانية|
|`remove(o)`|حذف عنصر|
|`removeAll(c)`|حذف العناصر الموجودة في Collection تانية|
|`retainAll(c)`|الاحتفاظ بالعناصر المشتركة فقط|
|`contains(o)`|التأكد من وجود عنصر|
|`containsAll(c)`|التأكد من وجود كل العناصر|
|`size()`|عدد العناصر|
|`isEmpty()`|هل الـ Set فاضية؟|
|`clear()`|حذف كل العناصر|
|`iterator()`|الحصول على Iterator|
|`toArray()`|تحويل العناصر إلى Array|

-  الـ `Set` مش بتضيف `get(index)` أو `set(index, value)` زي `List`، لأنها مش مبنية على مفهوم الوصول للعنصر عن طريق الـ index.

---

##  أهم Implementations للـ Set

|Feature|HashSet|LinkedHashSet|TreeSet|
|---|---|---|---|
|التكرار|ممنوع|ممنوع|ممنوع|
|ترتيب العناصر|غير مضمون|ترتيب الإضافة|ترتيب تصاعدي افتراضيًا|
|Data Structure|Hash table|Hash table + Linked List|Red-Black Tree|
|`add()`|Average O(1)|Average O(1)|O(log n)|
|`remove()`|Average O(1)|Average O(1)|O(log n)|
|`contains()`|Average O(1)|Average O(1)|O(log n)|
|`null`|مسموح بعنصر واحد|مسموح بعنصر واحد|عادةً غير مسموح|

---
# 4. HashSet

## أولًا: بتشتغل إزاي؟

الـ `HashSet` بتعتمد على الـ Hashing، وبتستخدم داخليًا `HashMap` لتخزين العناصر, يعني جواها Map و بتضيف فيه ال key و بتجيبه منه وكده 

```
HashMap
+--------------------+
| Key       | Value  |
+--------------------+
| Ahmed     | Dummy  |
| Ali       | Dummy  |
| Omar      | Dummy  |
+--------------------+
```


---

# 5. LinkedHashSet


**الـ `LinkedHashSet` بتحافظ على ترتيب إضافة العناصر.* , و بتستعمل جواها ال LinkedHashMap
### Time Complexity

|Operation|Average Time Complexity|
|---|---|
|`add()`|O(1)|
|`remove()`|O(1)|
|`contains()`|O(1)|


---

# 6. TreeSet

- بتستعمل جواها ال TreeMap فا بالتالي هتستعمل ال  **Red-Black Tree**.
- فا بالتالي هتبقي sorted 
##  الـ Time Complexity

|Operation|Time Complexity|
|---|---|
|`add()`|O(log n)|
|`remove()`|O(log n)|
|`contains()`|O(log n)|

---

# 7. EnumSet

دي نوع متخصص من الـ `Set`، معمول علشان يتعامل مع عناصر من نوع `enum` فقط.

خلينا نفترض إن عندنا أيام الأسبوع:

```java
enum Day {
    SATURDAY,
    SUNDAY,
    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY
}
```

نقدر نعمل:

```java
Set<Day> workingDays = EnumSet.of(
        Day.SUNDAY,
        Day.MONDAY,
        Day.TUESDAY,
        Day.WEDNESDAY,
        Day.THURSDAY
);

System.out.println(workingDays);
```

الـ `EnumSet` مناسبة جدًا لما تكون العناصر من نوع `enum`، لأنها بتستخدم تمثيلًا داخليًا فعالًا للذاكرة والأداء.

### مميزاتها

- لا تسمح بالتكرار.
- لا تسمح بـ `null`.
- عناصرها من نوع `enum` واحد.
- بتحتفظ بترتيب ثوابت الـ `enum` حسب تعريفها، مش حسب ترتيب الإضافة.
- عملياتها الأساسية فعالة جدًا.

مثلًا:

```java
EnumSet<Day> weekend = EnumSet.of(
        Day.FRIDAY,
        Day.SATURDAY
);
```

ممكن تستخدمها لما تمثل أيام الإجازات أو صلاحيات المستخدم أو حالات معينة محددة مسبقًا.

---

# 9. عمليات المجموعات باستخدام Set

الـ `Set` مفيدة جدًا في تنفيذ العمليات الرياضية على المجموعات.

خلينا نفترض:

```java
Set<Integer> a = new HashSet<>(Set.of(1, 2, 3, 4));
Set<Integer> b = new HashSet<>(Set.of(3, 4, 5, 6));
```

## أولًا: Union

الاتحاد هو كل العناصر الموجودة في أي واحدة من المجموعتين، من غير تكرار.

```java
Set<Integer> union = new HashSet<>(a);
union.addAll(b);

System.out.println(union);
```

الناتج:

```
[1, 2, 3, 4, 5, 6]
```

استخدمنا `addAll()` لإضافة عناصر `b` إلى `union`.

## ثانيًا: Intersection

التقاطع هو العناصر المشتركة بين المجموعتين.

```java
Set<Integer> intersection = new HashSet<>(a);
intersection.retainAll(b);

System.out.println(intersection);
```

الناتج:

```
[3, 4]
```

استخدمنا `retainAll()` علشان نحتفظ بالعناصر المشتركة بس.

## ثالثًا: Difference 

الفرق بين `a` و`b` هو العناصر الموجودة في `a` وغير الموجودة في `b`.

```java
Set<Integer> difference = new HashSet<>(a);
difference.removeAll(b);

System.out.println(difference);
```

الناتج:

```
[1, 2]
```

خلي بالك إن العمليات دي بتغيّر المجموعة اللي استدعيت عليها الـ method. علشان كده أنشأنا نسخة من `a` الأول بدل ما نغيّر المجموعة الأصلية.

---

# 10. إزاي تعمل Iteration على Set؟

تقدر تستخدم أكتر من طريقة.

### باستخدام enhanced for loop

```java
Set<String> names = new HashSet<>();

names.add("Ahmed");
names.add("Ali");
names.add("Omar");

for (String name : names) {
    System.out.println(name);
}
```

خلي بالك إن ترتيب الطباعة هنا مش مضمون؛ لأننا بنستخدم `HashSet`.

### باستخدام Iterator

```java
Iterator<String> iterator = names.iterator();

while (iterator.hasNext()) {
    String name = iterator.next();
    System.out.println(name);
}
```

### حذف العناصر أثناء الـ Iteration

لو عايز تحذف عناصر أثناء المرور عليها، استخدم `Iterator.remove()`:

```java
Set<Integer> numbers = new HashSet<>(
        Set.of(10, 20, 30, 40)
);

Iterator<Integer> iterator = numbers.iterator();

while (iterator.hasNext()) {
    int number = iterator.next();

    if (number > 20) {
        iterator.remove();
    }
}

System.out.println(numbers);
```

الناتج:

```
[10, 20]
```

استخدام `iterator.remove()` هو الطريقة المناسبة للحذف أثناء المرور بالـ Iterator نفسه. أما تعديل الـ `Set` مباشرةً أثناء الـ iteration، فقد يؤدي إلى `ConcurrentModificationException` مع الـ implementations المعتادة غير المصممة للتعديل المتزامن.

---
