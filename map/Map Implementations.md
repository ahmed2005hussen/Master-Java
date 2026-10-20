```java
Map<Integer, String> students = new HashMap<>();
```

- ال hashmap مش ordered يعني مش بتهتم بترتيب العناصر اللي دخلناها
- ال hashmap بتحل مشكله ال collision عن طريق انها بتعمل linkedList في ال buckets 
- و لو في عناصر كتير في ال bucket فا بتحول ال linked list الي binary tree 
- و لما بنبدأ نحذف عناصر من ال bucket لما نوصل لرقم معين فا بترجعه تاني لي linked list 
- ال hashmap عندها load balance لما بنعديه بتعمل table اكبر 

```java
static final float DEFAULT_LOAD_FACTOR = 0.75f; // load factor 

static final int TREEIFY_THRESHOLD = 8; // لما نوصل لي 8 في الباكت هنحوله لتري

static final int UNTREEIFY_THRESHOLD = 6;// لو مسحنا عناصر من الباكت و هو تري, و وصلنا ل 6 عناصر , فا هنرجع تاني للينكد ليست 

```

### الـ Time Complexity

|Operation|Average / Expected|Worst Case|
|---|---|---|
|`put()`|O(1)|O(n) نظريًا|
|`get()`|O(1)|O(n) نظريًا|
|`remove()`|O(1)|O(n) نظريًا|
|`containsKey()`|O(1)|O(n) نظريًا|
|`containsValue()`|O(n)|O(n)|

### مميزات HashMap

- وصول سريع للقيم باستخدام الـ Key.
- مناسبة لمعظم استخدامات الـ Map العادية.
- بتسمح بـ `null` كـ Key واحدة، وبـ `null` Values.
- مفيش ضمان لترتيب العناصر.
### عيوبها

- مش Thread-safe للاستخدام المتزامن غير المنسق.
- مش بتحافظ على ترتيب الإضافة.
- الأداء ممكن يتأثر بالـ Hash Collisions.

**إمتى تستخدمها؟**
لما تحتاج تربط Keys بـ Values ومش محتاج ترتيب محدد، وده غالبًا اختيارك الأول للـ Map.

---
##  LinkedHashMap

```java
public class LinkedHashMap<K,V>
    extends HashMap<K,V>
    implements SequencedMap<K,V> {}
```

- هنلاحظ انها بت implements ال sequenced , و ده معناه انها بتحافظ علي ال order بتاع البيانات 
- فكره ال LinkedHashMap ان بيكون عندنا linkedList بتربط العناصر اللي دخلت علشان نحافظ علي ال order بتاعهم 
- فا لكل عنصر بنضيفه في الهاش بيكون في next , prev pointers بتشاول علي العنصر اللي بعده و اللي قبله علشان لما نيجي نطبع نقدر نطبع بنفع ال order اللي دخلوه بيه 
### Complexity

العمليات الأساسية زي `get()` و`put()` و`remove()` متوسطها `O(1)`، مع وجود تكلفة إضافية للحفاظ على ترتيب الـ Entries.

**إمتى تستخدمها؟**

لما تحتاج أداء قريب من `HashMap` مع الحفاظ على ترتيب الإضافة، أو لما تحتاج Access Order.

---
##  TreeMap

```java
Map<Integer, String> map = new TreeMap<>();
```

- هنا بقا احنا بنستعمل ال red black tree علشان نخزن العناصر علشان نقدر نخليها في شكل مترتب

- هي بترتب علي حسب ال .equals فا لو عايزين نرتب علي حاجه معينه لازم نعدل ال .equals 
### Complexity

|Operation|Time Complexity|
|---|---|
|`put()`|O(log n)|
|`get()`|O(log n)|
|`remove()`|O(log n)|
|`containsKey()`|O(log n)|
### ميزة إضافية: البحث حسب النطاق
لأن `TreeMap` مرتبة، تقدر تجيب Keys قبل أو بعد Key معينة.

```java
TreeMap<Integer, String> map = new TreeMap<>();

map.put(10, "Ahmed");
map.put(20, "Ali");
map.put(30, "Omar");
map.put(40, "Mona");

System.out.println(map.firstKey()); // 10
System.out.println(map.lastKey());  // 40

System.out.println(map.lowerKey(30));   // 20
System.out.println(map.higherKey(30));  // 40

System.out.println(map.floorKey(25));   // 20
System.out.println(map.ceilingKey(25)); // 30
```

الفرق:

- `lowerKey(k)`: أكبر Key أصغر من `k`.
- `higherKey(k)`: أصغر Key أكبر من `k`.
- `floorKey(k)`: أكبر Key أصغر من أو تساوي `k`.
- `ceilingKey(k)`: أصغر Key أكبر من أو تساوي `k`.

وفيه كمان `firstEntry()` و`lastEntry()` و`pollFirstEntry()` و`pollLastEntry()`.

### ترتيب مخصص باستخدام Comparator

تقدر تتحكم في ترتيب الـ Keys:

```java
TreeMap<Integer, String> map =
        new TreeMap<>(Comparator.reverseOrder());

map.put(10, "Ahmed");
map.put(30, "Ali");
map.put(20, "Omar");

System.out.println(map);
```

هنا الـ Keys هتترتب تنازليًا.

---
# . SortedMap وNavigableMap وSequencedMap

دي Interfaces مهمة لأنها بتضيف قدرات فوق الـ `Map` الأساسية.

## SortedMap

```java
SortedMap<Integer, String> map = new TreeMap<>();
```

الـ `SortedMap` بتضمن إن الـ Keys بتتقدم بترتيب معين.

أهم Methods:

```java
firstKey()
lastKey()
headMap()
tailMap()
subMap()
comparator()
```

مثال:

```java
SortedMap<Integer, String> map = new TreeMap<>();

map.put(10, "Ahmed");
map.put(20, "Ali");
map.put(30, "Omar");
map.put(40, "Mona");

System.out.println(map.headMap(30));
System.out.println(map.tailMap(30));
System.out.println(map.subMap(20, 40));
```

بشكل افتراضي:

- `headMap(30)`: الـ Keys الأصغر من `30`.
- `tailMap(30)`: الـ Keys الأكبر من أو تساوي `30`.
- `subMap(20, 40)`: الـ Keys من `20` حتى قبل `40`.

الـ Views الناتجة عن `headMap()` و`tailMap()` و`subMap()` مرتبطة بالـ Map الأصلية، مش نسخ مستقلة.

##  NavigableMap

```java
NavigableMap<Integer, String> map = new TreeMap<>();
```

بتوسع قدرات `SortedMap` بعمليات البحث عن أقرب Key.

أهم Methods:

|Method|المعنى|
|---|---|
|`lowerKey(k)`|أكبر Key أصغر من `k`|
|`floorKey(k)`|أكبر Key أصغر من أو تساوي `k`|
|`ceilingKey(k)`|أصغر Key أكبر من أو تساوي `k`|
|`higherKey(k)`|أصغر Key أكبر من `k`|
|`firstEntry()`|أول Entry حسب الترتيب|
|`lastEntry()`|آخر Entry حسب الترتيب|
|`pollFirstEntry()`|ترجع وتحذف أول Entry|
|`pollLastEntry()`|ترجع وتحذف آخر Entry|
|`descendingMap()`|View بترتيب معكوس|

الـ `TreeMap` هي أشهر Implementation للـ `NavigableMap`.

##  SequencedMap — Java 21+

دي Interface بتوفر عمليات التعامل مع الـ Map على أساس ترتيب الـ Entries، زي معرفة أول وآخر Entry أو عكس الترتيب.

أهم Methods:

```java
firstEntry()
lastEntry()
pollFirstEntry()
pollLastEntry()
putFirst(key, value)
putLast(key, value)
reversed()
```

لكن فيه فرق لازم تنتبه له: بعض الـ Methods دي أصلًا موجودة في `NavigableMap`، والـ `SequencedMap` بتوفرها كجزء من عقد عام للتعامل مع الـ Maps المرتبة.

مثال:

```java
SequencedMap<Integer, String> map = new LinkedHashMap<>();

map.put(10, "Ahmed");
map.put(20, "Ali");
map.put(30, "Omar");

System.out.println(map.firstEntry());
System.out.println(map.lastEntry());
```

تقدر كمان تحصل على View بترتيب عكسي:

```java
SequencedMap<Integer, String> reversed = map.reversed();
```

**مهم:** `reversed()` بترجع View بترتيب عكسي، مش نسخة مستقلة بالضرورة.

وكمان `putFirst()` و`putLast()` مش معناهم إن أي Implementation هتسمح لك تتحكم في الترتيب بنفس الطريقة؛ لو الـ Implementation مش بتقدر تحقق العملية المطلوبة، ممكن ترمي `UnsupportedOperationException`.

---

# إزاي تعمل Immutable map   ؟

##  `Map.of()`

لو عايز تنشئ Map صغيرة جاهزة:

```java
Map<Integer, String> students = Map.of(
        1, "Ahmed",
        2, "Ali",
        3, "Omar"
);
```

تقدر تستخدمها مباشرة:

```java
System.out.println(students.get(2)); // Ali
```

لكن فيه 3 حاجات مهمة:

1. الـ Map غير قابلة للتعديل (Unmodifiable).
2. لا تسمح بـ `null` كـ Key أو Value.
3. الـ Keys لازم تكون فريدة؛ لو فيه Keys مكررة، الإنشاء هيفشل بـ `IllegalArgumentException`.

مثلًا:

```java
Map<Integer, String> map = Map.of(1, "Ahmed");

map.put(2, "Ali"); // UnsupportedOperationException
```

##  `Map.ofEntries()`

لو عندك عدد كبير من الـ Entries، تقدر تستخدم `Map.ofEntries()`:

```java
Map<Integer, String> students = Map.ofEntries(
        Map.entry(1, "Ahmed"),
        Map.entry(2, "Ali"),
        Map.entry(3, "Omar")
);
```

كل `Map.entry(key, value)` بتمثل Entry واحدة.
والـ Map الناتجة برضه Unmodifiable ولا تسمح بـ `null`.

##  `Map.copyOf()`

لو عندك Map موجودة وعايز تعمل Unmodifiable Copy:

```java
Map<Integer, String> original = new HashMap<>();

original.put(1, "Ahmed");
original.put(2, "Ali");

Map<Integer, String> copy = Map.copyOf(original);
```

##  Constructor بياخد Map

لو عايز نسخة قابلة للتعديل:

```java
Map<Integer, String> original = Map.of(
        1, "Ahmed",
        2, "Ali"
);

Map<Integer, String> copy = new HashMap<>(original);

copy.put(3, "Omar");
```

دي طريقة شائعة جدًا لإنشاء Map جديدة من Map موجودة.

---

#  إزاي تعمل Iteration على Map؟

خلينا نستخدم المثال ده:

```java
Map<Integer, String> students = new HashMap<>();

students.put(101, "Ahmed");
students.put(102, "Ali");
students.put(103, "Omar");
```

## الطريقة الأولى: باستخدام `entrySet()`

```java
for (Map.Entry<Integer, String> entry : students.entrySet()) {
    System.out.println(
            entry.getKey() + " : " + entry.getValue()
    );
}
```

**دي غالبًا أفضل طريقة لو محتاج الـ Key والـ Value مع بعض.**

## الطريقة الثانية: باستخدام `keySet()`

```java
for (Integer key : students.keySet()) {
    System.out.println(key);
}
```

ولو محتاج الـ Value كمان:

```java
for (Integer key : students.keySet()) {
    System.out.println(key + " : " + students.get(key));
}
```

الطريقة دي صحيحة، لكن لو محتاج الاتنين، `entrySet()` غالبًا أوضح وأفضل.

## الطريقة الثالثة: باستخدام `values()`

```java
for (String value : students.values()) {
    System.out.println(value);
}
```

دي لو محتاج الـ Values فقط.

## الطريقة الرابعة: باستخدام `forEach()`

```java
students.forEach((key, value) ->
        System.out.println(key + " : " + value)
);
```

ودي طريقة مختصرة ومناسبة جدًا مع الـ Lambda Expressions.

## الطريقة الخامسة: باستخدام Iterator

```java
Iterator<Map.Entry<Integer, String>> iterator =
        students.entrySet().iterator();

while (iterator.hasNext()) {
    Map.Entry<Integer, String> entry = iterator.next();

    System.out.println(entry.getKey());
    System.out.println(entry.getValue());
}
```

دي مفيدة لما تحتاج تتحكم في عملية المرور على العناصر، خصوصًا لو هتحذف Entries أثناء الـ Iteration.

مثلًا، لو عايز تحذف الطلاب اللي ID بتاعهم أقل من `103`:

```java
Iterator<Map.Entry<Integer, String>> iterator =
        students.entrySet().iterator();

while (iterator.hasNext()) {
    Map.Entry<Integer, String> entry = iterator.next();

    if (entry.getKey() < 103) {
        iterator.remove();
    }
}
```

استخدمنا `iterator.remove()` عشان نحذف بأمان أثناء الـ Iteration.

أما حذف العناصر مباشرةً من الـ Map داخل Enhanced For Loop، فممكن يؤدي إلى `ConcurrentModificationException` في الـ Implementations ذات الـ Fail-Fast Iterators.

---
#  الفرق بين HashMap وLinkedHashMap وTreeMap

| Feature                | HashMap      | LinkedHashMap            | TreeMap                        |
| ---------------------- | ------------ | ------------------------ | ------------------------------ |
| طريقة التنظيم          | Hash Table   | Hash Table + Linked List | Red-Black Tree                 |
| ترتيب العناصر          | غير مضمون    | ترتيب الإضافة افتراضيًا  | ترتيب الـ Keys                 |
| `get()`                | متوسط O(1)   | متوسط O(1)               | O(log n)                       |
| `put()`                | متوسط O(1)   | متوسط O(1)               | O(log n)                       |
| `remove()`             | متوسط O(1)   | متوسط O(1)               | O(log n)                       |
| Null Key               | مسموحة واحدة | مسموحة واحدة             | غير مسموحة مع Natural Ordering |
| Null Values            | مسموحة       | مسموحة                   | مسموحة                         |
| Thread-safe افتراضيًا؟ | لا           | لا                       | لا                             |
| الاستخدام المعتاد      | وصول سريع    | الحفاظ على الترتيب       | الترتيب والـ Range Queries     |

---
