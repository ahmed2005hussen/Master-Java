هنتكلم دلوقتي عن ال List interface و زي ما شوفنا في ال hierarchy ان ال List كان بي extends من ال Sequenced List اللي بدوره كان extends ال collection و اللي بدوره كان بي extends ال iterable

- اذا نقدر نفهم من هنا ان ال List interface هو جي و معاه كل ال functions اللي كانت موجوده في كل اللي قابله. 

طيب اي ال functions الجديده اللي ال List جه يضيفها بقا ؟ 
```java
public interface List<E> extends SequencedCollection<E> {

    void add(int index, E element);
    boolean addAll(int index, Collection<? extends E> c);
	static <E> List<E> copyOf(Collection<? extends E> coll) {
        return ImmutableCollections.listCopy(coll);
    }
    
    E get(int index);
    int indexOf(Object o);
    int lastIndexOf(Object o);
    ListIterator<E> listIterator(int index);
    static <E> List<E> of() {
        return (List<E>) ImmutableCollections.EMPTY_LIST;
    }
    
    default void sort(Comparator<? super E> c) {
        Object[] a = this.toArray();
        Arrays.sort(a, (Comparator) c);
        ListIterator<E> i = this.listIterator();
        for (Object e : a) {
            i.next();
            i.set((E) e);
        }
    }
    
}	
```

و حاجات تاني طبعا , بالاضافه الي ده هو بدأ يعدل كمان علي شويه functions من ال parents مثلا: 
```java 
  default void addFirst(E e) {this.add(0, e);}
  default void addLast(E e) { this.add(e);}
  default E getFirst() {
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        } else {
            return this.get(0);
        }
    }
    
    default E removeFirst() {
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        } else {
            return this.remove(0);
        }
    }

```

فا هنلاقي  انه حط default impl لشويه functions  عندنا , زي انه مثلا في ال sequenced function كل اللي هو عمله انه بدأ ينده جواهم علي ال collections functions عادي , و لكن طبعا ده وفر علينا كود و عملنا unified interface نتعامل معاه. 

---
# طيب اي بقا هي ال List ؟ 

الـ `List` هي **Interface بتمثل مجموعة مرتبة من العناصر(Ordered)، وبتسمح بوجود عناصر مكررة**.
- لما بنقول مرتبه هنا معناها ان الترتيب اللي دخلت بيه العناصر هو اللي هتتخزن بيه, مش قصدنا انها sorted.

اذا ال list هي: 
1. بتكون ordered collection 
يعني العناصر ليها ترتيب محدد، وبتقدر تتعامل مع كل عنصر باستخدام الـ Index بتاعه.

```
Index:    0         1          2
       +---------+----------+-------+
       | Ahmed   | Mohamed  | Ali   |
       +---------+----------+-------+
```

2. بتسمح بال duplicates 
3. و كمان indexed او بنسميها positional access: 
يعني تقدر تتعامل مع العناصر باستخدام الـ Index.
---
```java
public interface List<E> extends SequencedCollection<E>
```

إيه الـ `E` دي؟

- دي اسمها **Generic Type Parameter**.
- يعني الـ `List` بتحدد نوع العناصر اللي هتتعامل معاها عن طريق الـ Type Parameter ده.

#معلومه بما ان ال list بتستقبل Generic معني كده انها عايز object فا مش هينفع معاها ال primitive لازم ال wrappers 

---
# ال List Method 
كنا شوفنا شويه methods ال list ضافتهم علي الحاجات اللي وخداها من ال parent فا يلا نشوهم اكتر: 

1. ال  `get(int index)` 
-  دي بترجع العنصر الموجود عند الـ Index ده .
- ممكن يحصل IndexOutOfBoundsException لو حاولنا نوصل لي index اكبر من ال size او سالب 

2. ال `set(int index, E element)`
- دي بتستبدل العنصر الموجود في الـ Index ده 
- وبترجع العنصر القديم.

|Method|بتعمل إيه؟|
|---|---|
|`get(index)`|بترجع العنصر الموجود في المكان|
|`set(index, element)`|بتستبدل العنصر الموجود|
|`add(index, element)`|بتضيف عنصرًا جديدًا في المكان|

---
# إضافة العناصر في الـ List

الـ `List` بتوفر طريقتين أساسيتين للإضافة باستخدام الـ Methods اللي بتعرّفها هي أو بترثها.
1. ال  `add(E element)`
- دي واخدينها من ال Collection عادي 
- بتضيف العناصر في الاخر 

2. ال `add(int index, E element)`
- ده بتاعت ال list نفسها بقا 
- بنحدد المكان اللي عايزين نضيف فيه ال object 
---
# حذف العناصر من الـ List

الـ `List` بتوفر طريقتين مهمتين للحذف: 
1.  ال `remove(int index)`
- بتحذف العنصر اللي موجود عند ال index ده 
- بترجعلنا العنصر اللي مسحناه ده 

2. ال `remove(Object o)`
- دي بقا واخدينها عادي من ال Collection 
- بتمسح ال object اللي احنا ادناهولها 
- لو عندنا اكتر من Object بي match اللي عايزين نمسحه, فا هي هتمسح اول واحد بس 

في بقا حاجه مهمه بتلغبط : 

```java
List<Integer> numbers = new ArrayList<>(
        List.of(10, 20, 30)
);

numbers.remove(1);
```

ده كده هيعتبر انهي؟ ال remove(index ) ولا ال remove(object) ؟ 
- هنا هو استقبل primitive و بالتلي هيمسح ال index = 1 
- لو عايزه يمسح العدد 1 فا هنحتاج نعمل boxing 
- يعني نستعمل Integer.valueOf(1) بالتالي دي بقا object فا هيمسح العدد مش ال index 
---
# البحث عن العناصر داخل الـ List

الـ `List` بتوفر Methods للبحث عن مكان العنصر.
1. ال  `indexOf(Object o)`
- بترجع Index أول ظهور للعنصر.
- ولو العنصر مش موجود هترجع: -1 

2. ال `lastIndexOf(Object o)`
- بترجع Index آخر ظهور للعنصر.
---
#  الـ `ListIterator` وعلاقته بالـ List

- وده Iterator متخصص في التعامل مع الـ Lists.
- و بي extends من ال iterator : 
```java
public interface ListIterator<E> extends Iterator<E> {

}
```

هنلاقي فيه : 

![](../../images/Pasted%20image%2020261012112814.png)

إيه المميز فيه؟
إنه بيسمحلك تتحرك في الاتجاهين، وتتعامل مع الـ Indexes، وكمان تضيف أو تستبدل عناصر أثناء الـ Iteration وفقًا لقواعده.

1. ال  `listIterator()`
- ده بيرجع `ListIterator` يبدأ قبل أول عنصر.

2. ال `hasNext()` و `next()`
3. ال `hasPrevious()` و`previous()`
- دول بيسمحوا لك تتحرك للخلف.

4. ال  `nextIndex()` و`previousIndex()`
- دول بيرجعوا الـ Index المتوقع للعنصر التالي أو السابق حسب موضع الـ Iterator.

5. ال  `add()` و `set()` داخل الـ Iterator
- الـ `ListIterator` بيسمحلك تضيف عنصرًا عند موضع الـ Iterator الحالي باستخدام:
```java
iterator.add("Omar");
```

- تستبدل آخر عنصر رجعته `next()` أو `previous()` باستخدام:
```java 
iterator.set("Mostafa");
```

**الفرق الأساسي:**
- الـ `Iterator` العادي بيتحرك للأمام
- أما `ListIterator` فبيدعم الحركة في الاتجاهين، ويوفر عمليات إضافية مرتبطة بالـ List.


اهم notes 
- ال iterator بيبقا واقف في الاول قبل العناصر 
- لما بنحركه بيقف بين العناصر 
- علشان نعمل set or remove لازم بعد عمليه next او previous 
- لانه بيعمل set or remove لاخر حاجه رجعت من ال next or previous 
- لو عملت حاجه منهم من غير next or previous هيعمل IllegalStateException
- ال add بتضيف عند المكان اللي ال iterator واقف عليه ,  و بتحرك ال iterator بعد المكان تاني علشان يرجع تاني بين العناصر 
- لو عايز تمشي من العكس: 
```java
ListIterator<String> it = b.listIterator(b.size());

while (it.hasPrevious()) {
    System.out.println(it.previous());
}
```

- مشروح كل ده بالتفصيل في الكود :) 
---
#  الـ `subList()` 


```java
List<Integer> numbers = new ArrayList<>(
        List.of(10, 20, 30, 40, 50)
);
```
لو عايز جزءًا من القائمة، ممكن تستخدم:

```java
List<Integer> part = numbers.subList(1, 4); // 20 , 30 , 40
```

- ال index الاول ده اللي هنبدأ من عنده و بناخده معانا عادي 
- ال index التاني ده اللي هنقف عنده و مش بناخده معانا 
### ملحوظة مهمة جدًا

الـ `subList()` مش بتضمن إنها تنشئ نسخة مستقلة من العناصر هي بترجع **View** لجزء من القائمة الأصلية.

مثلًا:

```java 
List<Integer> numbers = new ArrayList<>(
        List.of(10, 20, 30, 40, 50)
);

List<Integer> part = numbers.subList(1, 4);

part.set(0, 99);

System.out.println(numbers); //[10, 99, 30, 40, 50]
System.out.println(part); // [99, 30, 40]
```

ولو عايز نسخة مستقلة من القائمة الجزئية، ممكن تكتب:

```java
List<Integer> partCopy = new ArrayList<>(
        numbers.subList(1, 4)
);
```
---
#  الـ Static Methods الموجودة في `List`

عندنا Java بتوفر Static Methods داخل الـ Interface نفسها لإنشاء Lists بطريقة مختصرة.
أهمها:
### `List.of()`

```java
List<String> names = List.of("Ahmed", "Ali", "Omar");
```

- بتنشئ List تحتوي على العناصر المحددة.
- لكن لازم تعرف إن القائمة دي immutable **غير قابلة للتعديل**؛ يعني عمليات الإضافة والحذف والاستبدال عليها هترمي `UnsupportedOperationException`.
- كمان `List.of()` لا تسمح بعناصر `null`.

### `List.copyOf()`

```java
List<String> names = List.copyOf(existingNames);
```

- بتنشئ List بتكون immutable تحتوي على نفس العناصر الموجودة في الـ Collection المصدر.
- العناصر نفسها مش بالضرورة تكون Objects جديدة؛ دي نسخة على مستوى القائمة، مش Deep Copy للكائنات.
- كمان `List.copyOf()` بترفض وجود `null` في العناصر.
---

و بس كده ال list هو interface  بيحدد behavior معين للي هيجي impl ال list و هنشوف مين ال implementations لل list :) 