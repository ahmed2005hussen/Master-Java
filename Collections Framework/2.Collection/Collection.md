if you don't see : 
1. [Iterable](../1.Iterable/Iterable.md)
please go to it before this.
---
ال collection هو interface الاساسيه اللي بتعرفلنا ال functions العامه اللي اي حد هيجي يعملها implementation لازم يبقا عنده.
![](../../images/Pasted%20image%2020261009151958.png)


- هنلاقيها انها بتعرف فانكشنز زي add, isEmpty,clear,contain و حاجات تاني كتير, و دي الفانكشنز اللي بتخلينا نتعامل مع اي data structure عندنا. 

اول حاجه ال Collection بي extends ال iterable : 
```java
public interface Collection<E> extends Iterable<E> {
}
```

- بما انها عامله extends لي ال iterable فا ده معناه ان اي data structure هتعمل implements لل Collection لازم تعمل implements للفانكشنز اللي جوا ال iterable. 
- ال `<E>` ده اللي هو Generic type, يعني يقدر يشيل اي نوع عادي. 
- ال Collection مبتقدرش تشيل غير حاجه من نوع Object يعني ال primitive مش هتنفع لازم نستعمل ال Wrapper classes
- موجود في: 
```java
java.util.Collection
```

- اي DS بيعملها implements و بيحط ال logic للفانكنشز اللي قولنا عليها فوق: 
```text
                    Iterable<E>
                         |
                    Collection<E>
                    /    |     \
                   /     |      \
                List    Set     Queue
                 |       |        |
            ArrayList  HashSet  PriorityQueue
            LinkedList TreeSet  ArrayDeque
```

---

##  أهم Methods في Collection Interface

هنشرح هنا اهم ال methods و هي هتتكرر في كل ال DS اللي هنشوفهم.

هنبدأ بمثال:

```java
Collection<String> names = new ArrayList<>();

names.add("Ahmed");
names.add("Mohamed");
names.add("Ali");
```

في العادي مش بنعمل Collection بالشكل ده و لكن بما اننا في ال Collection و عايزين بس نشوف الفانكشنز المشتركه في كل ال DS اللي بت implements ال Collection فا هنخلي ال reference يبقا علي الCollection 

ال array عندنا دلوقتي بالشكل ده: 

```text
[Ahmed, Mohamed, Ali]
```

### add(Object o) 

بتضيف عنصر في الاخر في ال DS بتاعتنا.

```java
System.out.println(names.add("Omar")); // true 
```

-  كده ضيفنا omar في ال list 
- ال add بترجع boolean 
- معظم الـ Collections القابلة للتعديل هتقبل الإضافة وترجع `true`.
- ال set لو كان العنصر موجود قبل كده فا مش هتضيفه يعني هترجع false.
---

### remove(Object o)



```java
  System.out.println(c.remove("Hussein")); // true
  System.out.println(c.remove("Hussein")); // false
```

- بنديلها ال Object اللي عايزين نمسحه و بتمسحه. 
- بترجع true لو ال object اتمسح 
- بترجع false لو ال object متمسحش ( لو مش موجود اصلا مثلا )
- لو عندي اكتر من عنصر نفس ال object بتمسح اول واحد.

معلومه مهمه: احنا هنا علشان بنتعامل مع Collection فا لو عملنا Collection من نوع Integer و مسحنا 1 هو بيحول ال 1 ده لي Object عادي و بيشيله من ال Collection 

و لكن في ال List لما ناخدها هنلاقي انها عندها فانكشن تاني اسمها remove بتستقبل ال index , و بكده بقا عندنا 2 فانكشن لل remove : 
```java
public boolean remove(Object o)
public E remove(int index)
```

و لو عملنا remove(1) فا هو بيعتبرها انها ال index , لو عايزينها بقا ال Object: 
```java
c.remove(Integer.valueOf(1)); 
```

كده هنقدر نفصل بين ال remove(object) و remove(index)

---
### size()
بترجع عدد العناصر الموجودة في ال Collection.

```java
System.out.println(c.size()); 
```

---
### isEmpty()

- بترجع true لو كان فاضي 
- بترجع false لو حاجه فيه عنصر واحد علي الاقل 

```java
Collection<String> names = new ArrayList<>();

System.out.println(names.isEmpty()); // true

names.add("Ahmed");

System.out.println(names.isEmpty()); // false
```

---
### contains(Object o)

- بترجع true لو كان العنصر موجود في ال Collection 
- بترجع false لو كان العنصر مش موجود في ال Collection 

```java
Collection<String> names = new ArrayList<>();

names.add("Ahmed");
names.add("Ali");

System.out.println(names.contains("Ahmed")); // true
System.out.println(names.contains("Omar"));  // false
```

ال contains من جوا بتستعمل ال equals علشان تتحقق اذا كان موجود ولا لا 
- و ده يخلينا نفهم من هنا ان لو عندنا List من class احنا اللي عاملينه فا لازم نعمل override لل equals علشان نقدر نعدلها بالشكل اللي عايزينه نقارن بيه

مثلا، لو عندك:

```java
class User {
    private String name;
}
```

---
### clear()

بتحذف كل العناصر من ال Collection.

```java
Collection<Integer> numbers = new ArrayList<>(
    List.of(10, 20, 30)
);

numbers.clear();

System.out.println(numbers);       // []
System.out.println(numbers.size()); // 0
```

---
لو عايز تحط قيم ابتدائيه في ال Collection من غير ما تعمل add فا بنستعمل جواه ال List.of(Objects) : 

```java
Collection<Integer> a = new ArrayList<>(
    List.of(1, 2, 3, 4)
);

Collection<Integer> b = new ArrayList<>(
    List.of(3, 4, 5, 6)
);
```

---
###  `addAll(Collection<? extends E> c)`

بتضيف كل عناصر مجموعة إلى مجموعة أخرى.

```java
a.addAll(b);

System.out.println(a);
```

النتيجة:

```text
[1, 2, 3, 4, 3, 4, 5, 6]
```
---
### `containsAll(Collection<?> c)`

بتتحقق إذا كانت المجموعة تحتوي على كل العناصر الموجودة في مجموعة أخرى.
مثلًا:

```java
 Collection<Integer> a1 = new ArrayList<>(
            List.of(1, 2, 3, 4)
    );
    Collection<Integer> b = new ArrayList<>(List.of(2, 3));
    System.out.println(a1.containsAll(b)); // true
    b.add(123);
    System.out.println(a1.containsAll(b)); // false
```

---

### `removeAll(Collection<?> c)`

بنديها Collection و بتمسح كل العناصر ده من ال Collection الاساسي اللي بينادي ال function: 

مثال:

```java
Collection<Integer> a2 = new ArrayList<>(
            List.of(1, 2, 3, 4)
    );
    Collection<Integer> b1 = new ArrayList<>(List.of(2, 3));
    a1.removeAll(b1);
    System.out.println(a1); // [1,4]
```

---

### `retainAll(Collection<?> c)`

- هنا بقا دي بتحفظ المتشابه بس من الاتنين و بتمسح الباقي.

مثال:

```java
 Collection<Integer> a21 = new ArrayList<>(
            List.of(1, 2, 3, 4)
    );
    Collection<Integer> b11 = new ArrayList<>(List.of(2, 3));
    a21.retainAll(b11);
    System.out.println(a21); // [2,3]
    a21.retainAll(new ArrayList<>());
    System.out.println(a21); // [] 
```


---
### removeIf()


دي موجودة كـ `default method` في `Collection`، وبتسمحلك تحذف العناصر اللي بتحقق شرطًا معينًا.
- افتكر ان default في ال interface معناها ان هنحط impl للفانكشن دي جوا ال interface نفسه.
- الشرط بيكون عباره عن lambda عادي من نوع Predicate (هنعرف الحاجات دي بعدين)

مثلًا، عايز تحذف كل الأرقام الزوجية:

```java
  Collection<Integer> numbers = new ArrayList<>(
            List.of(1, 2, 3, 4, 5, 6)
    );
    System.out.println(numbers.removeIf(number -> number % 2 == 0)); // true
    System.out.println(numbers); // [1,3,5]

```

- ال removeIf بترجع boolean لو اتمسح بترجع true 

---
### `toArray()`

- بتحول ال Collection الي array اللي هو مثلا زي Integer[] هو ده كده 
ليه بنحتاج حاجه زي دي؟ 
انا ممكن اتعامل عادي مع ال ArrayList مثلا فا ليه محتاج اني ارجع تاني لي array عادي, انا اصلا بستعمل ال ArrayList علشان اتجنب استعمال ال array العادي, السبب هو: 

- بعض الـ APIs بتستقبل Arrays بدل Collections.
-  ممكن نكون شغالين علي  function بتستقبل array: 
  ```java
  public Fun(String[] names)
  ```

- وانا دلوقتي معايا ArrayList و عايز اني ابعت ال ArrayList دي للفانكشن, فا بدل ما نعمل لوب بقا و كده هنعمل: 
  ```java
  Collection<String> names = new ArrayList<>(
    List.of("Ahmed", "Ali", "Mohamed")
);

  Fun(names.toArray(new String[0])); 
  ```

- هنا ال new String[0], احنا بنقوله حول الداتا تايب لي String  و حجمه 0. 
- لان اصلا toArray بترجع Object فا بنعمل casting بالطريقه دي.
- نقدر نكتبها كده كمان: 
  ```java
      String[] arr1 = names.toArray(String[]::new);
  ```

مثلًا:

```java
Collection<String> names = new ArrayList<>(
    List.of("Ahmed", "Ali", "Mohamed")
);

Object[] array = names.toArray();

System.out.println(Arrays.toString(array)); // [Ahmed,Ali,Mohamed]
```
 ---
 اخيرا هنلاقي في حاجات تاني زي ال stream, هنبقي نشوفها بشكل منفصل. 

