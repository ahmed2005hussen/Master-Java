![](../images/Pasted%20image%2020261009103428.png)

- اول حاجه نعرفها ان ال map هي مش جزء من ال Collection interface و ده لان ال Map طريقه شغلها مختلفه عن ال logic اللي موجود في ال Collection 
- ال map هي قايمه علي فكره ال hash, اللي هو بيكون عندنا key و value 
- بالتالي نفهم من هنا ان ال key لازم يبقا unique 
- و كمان نفهم ان كل المشاكل اللي كانت بتواجهنا في ال hash هتقابلنا هنا برضوا زي ال collision, احنا بقا عندنا implementations كتيره لل map و فيهم بيهندلوا المشاكل اللي ممكن تحصل دي 

## طريقه شغل ال Hash 

- اول حاجه علشان نفهم ال map محتاجين نفتكر ال hash بسرعه 

 فا ببساطه انا كان بيبقا عندي حاجه اسمها ال hash function كنت بديها القيمه اللي عايز اخزنها اللي هو ال Key و هي كانت بتحوله لي hash value و ال hash function دي كانت عباره عن معادله رياضيه عادي يعني مثلا length%size و بالتالي عمليه ال hash كانت بتكون O(1)

بعد ما بنجيب ال hash value , كنا بنروح علي ال hash table و ال hash table ده ما هو الا array عادي كده و كنا بال hash value بنجيب ال index اللي في ال hash table ده و بنحط فيه القيمه عالطول 

فا مثلا كنت ممكن اقول HashTable[ahmed] و هو يجيبلي القيمه اللي مخزنها عالطول في O(1) و ده لان عمليه ايجاد ال hash value هي O(1) زي ما قولنا , و الوصول لمكان في array بأستخدام ال index هي برضوا O(1)

### اهم معلومات هنا 

- ال  hash map هتعتمد علي ال hashCode اللي موجود في جافا و ده اللي بيحسب ال hash value  اللي اتكلمنا عليها 
- ال map بتخلينا اقدر اوصل للعنصر بتاعي في O(1) و ده سريع جدا

### مشاكل ال hash 

من مشاكل ال hash ان كان ممكن يحصل collision اللي هو عندي اتنين keys مختلفين ادوني نفس ال hash value و بالتالي ادوني نفس ال index , فا يجي السؤال هنحط انهي قيمه في ال index ده ؟ 

الاجابه علي السؤال ده كانت بكذا طريقه : 
1- نعمل linked list جوا كل index اللي هو جوا كل bucket 
2- نعمل binary tree جوا ال buckets بدل ال linked list لانها اسرع 
3- نزود حجم ال hash table و نعمل rehash 

و حلول تاني,  بس اللي يهمنا هنا ان كل ده جافا هتعمله بشكل داخلي جوا ال implementations بتاعت ال map بتاعتنا 

---
## ال Map interface 
هنتكلم بقا دلوقتي عن ال Map interface اللي موجود عندنا في جافا و نشوف فيه اي وكده. 
الـ `Map` موجودة في:

```java
import java.util.Map;
```

الـ `Map` بتحدد العمليات المتاحة، زي:

```java
put()
get()
remove()
containsKey()
```

- و بيجي بقا ال implementation بتاعت ال map بتحط الطريقه بتاعتها لتنفيذ ال functions دي 
---
# كل Methods الموجودة في Map Interface

## اضافة وتعديل العناصر
### `put(K key, V value)`


```java
    V put(K key, V value);
```

بتستقيل key , value و بترجع ال value القديمه : 

```java
Map<Integer, String> students = new HashMap<>();

students.put(101, "Ahmed");
students.put(102, "Ali");

students.put(101, "Mohamed");

System.out.println(students.get(101)); // Mohamed
System.out.println(students.get(112)); // null

```

- لو استخدمنا نفس ال key تاني فا هو بيعمل override 
- لو ال key مش موجود بيرجع null 

```java
 Map<Integer, String> mp = new HashMap();

 mp.put(1,"Ahmed");
 System.out.println(mp.put(1,"Hussein")); // Ahmed
```

- ال put بترجع القيمه القديمه و لو مكنش في حاجه قبل كده بترجع null 

### `putIfAbsent(K key, V value)`

```java
   default V putIfAbsent(K key, V value) {
        V v = get(key);
        if (v == null) {
            v = put(key, value);
        }

        return v;
    }
```
	
- بتضيف القيمة لو الـ Key مش موجود، أو لو موجودة لكن قيمتها `null`.

```java
Map<Integer, String> map = new HashMap<>();

map.put(1, "Ahmed");

map.putIfAbsent(1, "Ali");
map.putIfAbsent(2, "Mohamed");

System.out.println(map); // {1=Ahmed, 2=Mohamed}
```

- الـ Key `1` مرتبطة بـ `"Ahmed"`، فماتغيرتش.
- الـ Key `2` مكنتش موجودة، فاتضافت.
### `putAll(Map<? extends K, ? extends V> m)`

بتنسخ كل الـ Entries من Map إلى Map تانية.

```java
    Map<Integer, String> first = new HashMap<>();
    first.put(1, "Ahmed"); 
    first.put(2, "Ali");

    Map<Integer, String> second = new HashMap<>();
    second.put(3, "Omar");
    second.putAll(first);

    System.out.println(second); // {1=Ahmed, 2=Ali, 3=Omar}
    System.out.println(first); // {1=Ahmed, 2=Ali}
```


لو فيه Key مشتركة بين الـ Maps، القيمة الموجودة في الـ Map المستقبلة هتتغير للقيمة القادمة من `first`.

```java
	Map<Integer, String> first = new HashMap<>();
	first.put(1, "Ahmed");
    first.put(2, "Ali");

    Map<Integer, String> second = new HashMap<>();
    second.put(2, "Omar");
    second.putAll(first);

    System.out.println(second); // {1=Ahmed, 2=Ali}
    System.out.println(first); // {1=Ahmed, 2=Ali}
```

---

## الوصول إلى العناصر

### `get(Object key)`

بترجع القيمة المرتبطة بالـ Key.

```java
Map<Integer, String> map = new HashMap<>();

map.put(1, "Ahmed");
map.put(2, "Ali");

System.out.println(map.get(1));  // Ahmed
System.out.println(map.get(10)); // null
```

لو الـ Key مش موجودة، بترجع `null`.

لكن فيه نقطة مهمة: لو الـ Map بتسمح بقيمة `null`، فـ `get()` وحدها مش كفاية عشان تفرق بين:
1. الـ Key مش موجودة.
2. الـ Key موجودة وقيمتها `null`.

الحل هنا `containsKey()`.

### `getOrDefault(Object key, V defaultValue)`

بترجع القيمة لو الـ Key موجودة، وإلا بترجع قيمة افتراضية.

```java
Map<Integer, String> map = new HashMap<>();

map.put(1, "Ahmed");

System.out.println(map.getOrDefault(1, "Unknown"));
// Ahmed

System.out.println(map.getOrDefault(2, "Unknown"));
// Unknown
```

 لو الـ Key موجودة وقيمتها `null`، فـ `getOrDefault()` هترجع `null`
 
---
## حذف العناصر
### `remove(Object key)`

```java
V remove(Object key);
```

بتحذف الـ Entry المرتبطة بالـ Key.

```java
Map<Integer, String> map = new HashMap<>();

map.put(1, "Ahmed");
map.put(2, "Ali");

map.remove(1);

System.out.println(map); // {2=Ali}
```

- بترجع القيمة اللي كانت مرتبطة بالـ Key قبل الحذف، أو `null` لو مفيش قيمة مرتبطة بيها.

### `remove(Object key, Object value)`

```java
default boolean remove(Object key, Object value) {
        Object curValue = get(key);
        if (!Objects.equals(curValue, value) ||
            (curValue == null && !containsKey(key))) {
            return false;
        }
        remove(key);
        return true;
    }
```

بتحذف الـ Entry فقط لو الـ Key والـ Value الاتنين مطابقين.

```java
Map<Integer, String> map = new HashMap<>();

map.put(1, "Ahmed");

System.out.println(map.remove(1, "Ali"));   // false
System.out.println(map.remove(1, "Ahmed")); // true
```

---
## البحث 

### `containsKey(Object key)`

بتتحقق هل الـ Key موجودة في الـ Map.

```java
Map<Integer, String> map = new HashMap<>();

map.put(1, "Ahmed");

System.out.println(map.containsKey(1));  // true
System.out.println(map.containsKey(10)); // false
```

### `containsValue(Object value)`

بتتحقق هل فيه Entry عندها الـ Value دي.

```java
System.out.println(map.containsValue("Ahmed")); // true
System.out.println(map.containsValue("Ali"));   // false
```

في `HashMap`، البحث عن Key بيكون عادةً أسرع بكتير من البحث عن Value؛ لأن `containsValue()` بتحتاج غالبًا تمر على العناصر.

### `size()`

بترجع عدد الـ Entries الموجودة.

```java
System.out.println(map.size());
```

### `isEmpty()`

بتتحقق هل الـ Map فاضية.

```java
if (map.isEmpty()) {
    System.out.println("No students");
}
```

### `clear()`
بتمسح كل الـ Entries.

```java
map.clear();

System.out.println(map.isEmpty()); // true
```

---
## الحصول على Keys وValues والـ Entries

دي من أهم الحاجات في الـ `Map`.
### `keySet()`
بترجع set تحتوي على كل الـ Keys.

```java
Map<Integer, String> students = new HashMap<>();

students.put(101, "Ahmed");
students.put(102, "Ali");
students.put(103, "Omar");

Set<Integer> keys = students.keySet();

System.out.println(keys);
```

ليه بترجع `Set`؟
لأن الـ Keys في الـ Map فريدة، والـ `Set` بتضمن عدم وجود عناصر متساوية مكررة.

تقدر تلف على الـ Keys بال enhanced loop 

```java 
for (Integer id : students.keySet()) {
    System.out.println(id);
}
```

او بال iterator 
```java
 Set<Integer> keys = students.keySet();

        Iterator it = keys.iterator();

        while(it.hasNext()){
            System.out.println(it.next());
        }
```

**مهم:** الـ `keySet()` بترجع View مرتبطة بالـ Map الأصلية، مش نسخة مستقلة.

```java
Set<Integer> keys = students.keySet();

keys.remove(101);

System.out.println(students.containsKey(101)); // false
```

حذف الـ Key من الـ View حذفها من الـ Map الأصلية أيضًا.
وبالمثل، حذف الـ Entry من الـ Map الأصلية هيظهر في الـ View.
### `values()`

بترجع `Collection` تحتوي على كل الـ Values.

```java
Collection<String> values = students.values();
```

ليه `Collection` مش `Set`؟
لأن الـ Values ممكن تتكرر.

```java
Map<Integer, String> students = new HashMap<>();

students.put(1, "Ahmed");
students.put(2, "Ahmed");
students.put(3, "Ali");

System.out.println(students.values());
```

وتقدر تلف عليها:

```java
for (String name : students.values()) {
    System.out.println(name);
}
```

او برضوا بال iterator 

والـ `values()` برضه View مرتبطة بالـ Map الأصلية، وحذف قيمة من الـ View بيحذف الـ Entry المقابلة لها من الـ Map.
### `entrySet()`

بترجع `Set<Map.Entry<K, V>>` تحتوي على الـ Entries.

كل Entry بتتكون من Key وValue.

```java 
Set<Map.Entry<Integer, String>> entries = students.entrySet();
```

تقدر تتعامل مع كل Entry بالشكل ده:

```java 
for (Map.Entry<Integer, String> entry : students.entrySet()) {
    System.out.println(entry.getKey());
    System.out.println(entry.getValue());
}
```

**ليه `entrySet()` مفيدة؟**

لو استخدمت:

```java
for (Integer key : students.keySet()) {
    System.out.println(key + " " + students.get(key));
}
```

فأنت بتلف على الـ Keys، وبعدها بتعمل `get()` لكل واحدة.

لكن مع `entrySet()` بتتعامل مباشرة مع كل Entry:

```java
for (Map.Entry<Integer, String> entry : students.entrySet()) {
    System.out.println(entry.getKey() + " " + entry.getValue());
}
```

و زي  `keySet()` و`values()`، الـ `entrySet()` هي View مرتبطة بالـ Map، والتعديلات المسموح بها من خلالها بتنعكس على الـ Map الأصلية.

---
## الـ Methods الخاصة بالـ Functional Programming

### `forEach(BiConsumer<? super K, ? super V> action)`

بتنفذ عملية على كل Key وValue.

```java 
Map<Integer, String> students = new HashMap<>();

students.put(1, "Ahmed");
students.put(2, "Ali");

students.forEach((id, name) ->
        System.out.println(id + " : " + name)
);
```

بدل ما تكتب `for` loop مع `entrySet()`، تقدر تستخدم `forEach()`.

### `replace(K key, V value)`

بتغير القيمة لو الـ Key موجودة.

```java 
map.replace(1, "Mohamed");
```

لو الـ Key مش موجودة، مش بيتم إضافة Entry جديدة.

### `replace(K key, V oldValue, V newValue)`

بتغير القيمة فقط لو القيمة الحالية مطابقة لـ `oldValue`.

```java
Map<Integer, String> map = new HashMap<>();

map.put(1, "Ahmed");

boolean changed = map.replace(1, "Ahmed", "Ali");

System.out.println(changed); // true
System.out.println(map);     // {1=Ali}
```

### `replaceAll(BiFunction<? super K, ? super V, ? extends V> function)`

بتحسب قيمة جديدة لكل Entry بناءً على الـ Key والـ Value الحالية.

```java 
Map<Integer, Integer> numbers = new HashMap<>();

numbers.put(1, 10);
numbers.put(2, 20);
numbers.put(3, 30);

numbers.replaceAll((key, value) -> value * 2);

System.out.println(numbers);
```

كل قيمة هتتضاعف.

---

### `computeIfAbsent(K key, Function<? super K, ? extends V> mappingFunction)`

بتحسب قيمة وتضيفها لو الـ Key مش موجودة أو مرتبطة بـ `null`.
مثال عملي: عندك Map بتخزن أسماء الطلاب، وعايز تضمن وجود قائمة أسماء لكل قسم.

```java
Map<String, List<String>> departments = new HashMap<>();

departments.computeIfAbsent("CS", key -> new ArrayList<>())
           .add("Ahmed");

departments.computeIfAbsent("CS", key -> new ArrayList<>())
           .add("Ali");

System.out.println(departments);
```

النتيجة:

```
{CS=[Ahmed, Ali]}
```


أول مرة:
1. مفيش Key باسم `"CS"`.
2. الـ Lambda أنشأت `ArrayList` جديدة.
3. القائمة اتخزنت تحت الـ Key `"CS"`.
4. اتضاف `"Ahmed"` للقائمة.

تاني مرة:
1. الـ Key موجودة والقيمة مش `null`.
2. مش محتاجين ننشئ قائمة جديدة.
3. رجعت القائمة الموجودة واتضاف `"Ali"`.

### `computeIfPresent(K key, BiFunction...)`

بتحسب قيمة جديدة لو الـ Key موجودة وقيمتها مش `null`.

```java
Map<Integer, Integer> scores = new HashMap<>();

scores.put(1, 50);

scores.computeIfPresent(1, (key, value) -> value + 10);

System.out.println(scores.get(1)); // 60
```

لو الـ Key مش موجودة أو قيمتها `null`، الـ function مش هتتنفذ.

### `compute(K key, BiFunction...)`

بتحسب القيمة اعتمادًا على الـ Key والقيمة الحالية، سواء الـ Key موجودة أو لأ.

```java
Map<String, Integer> counts = new HashMap<>();

counts.compute("Java", (key, value) ->
        value == null ? 1 : value + 1
);

counts.compute("Java", (key, value) ->
        value == null ? 1 : value + 1
);

System.out.println(counts); // {Java=2}
```

هنا:

- أول مرة `value` كانت `null`، فرجعنا `1`.
- تاني مرة كانت `1`، فزودناها لـ `2`.

**ملحوظة:** في الـ `compute` والـ `computeIfPresent` والـ `computeIfAbsent`، لو الـ function رجعت `null`، النتيجة بتختلف حسب الـ method؛ في `compute` و`computeIfPresent`، القيمة بتتشال لو كانت موجودة، أما `computeIfAbsent` فلن تضيف Entry لو الـ function رجعت `null`.

### `merge(K key, V value, BiFunction...)`

مفيدة جدًا لما تكون عايز تضيف قيمة أو تدمجها مع قيمة موجودة.

مثال عدّ تكرار الكلمات:

```java
Map<String, Integer> frequency = new HashMap<>();

frequency.merge("Java", 1, Integer::sum);
frequency.merge("Java", 1, Integer::sum);
frequency.merge("C++", 1, Integer::sum);

System.out.println(frequency);
// {Java=2, C++=1}
```

الفكرة:

- لو الـ Key مش موجودة أو قيمتها `null`، بتاخد القيمة الجديدة مباشرة.
- لو موجودة بقيمة غير `null`، بيتم دمج القيمة القديمة مع الجديدة باستخدام الـ function.

`Integer::sum` معناها اجمع العدد القديم مع العدد الجديد.

دي من أنضف الطرق لحساب التكرار باستخدام `Map`.

---

## 3.7 الـ Methods الخاصة بالمقارنة والنسخ

### `equals(Object o)`

بتتحقق هل الـ Map مساوية لـ Map تانية من ناحية الـ Entries.
مش شرط يكونوا من نفس الـ Implementation أو بنفس ترتيب العناصر.

```java
Map<Integer, String> a = new HashMap<>();
Map<Integer, String> b = new TreeMap<>();

a.put(1, "Ahmed");
a.put(2, "Ali");

b.put(2, "Ali");
b.put(1, "Ahmed");

System.out.println(a.equals(b)); // true
```

الترتيب مش مهم في المقارنة بين الـ Maps.

### `hashCode()`

بترجع Hash Code للـ Map، ومتوافقة مع قواعد `equals()`.

يعني لو Map مساوية لـ Map تانية باستخدام `equals()`، لازم يكون عندهم نفس الـ `hashCode()`.

---
