- ال ArrayList  هو implementation لل List.
- ال ArrayList هو جه يحل مشاكل ال array العاديه زي ان ال array العاديه عندها fixed size 
- ال ArrayList من جوا اصلا مبني علي Array عادي بس احنا بنقدر نستعمله في شكل dynamic 
- ال ArrayList بما انها اصلا Array عادي, فا هي محتاجه Contiguous memory 
---
## ايه اللي موجود جوا الـ ArrayList؟

```java
ArrayList<String> names = new ArrayList<>();

names.add("Ahmed");
names.add("Ali");
names.add("Omar");
```

- من جوا ال ArrayList بيكون عندها Array بتخزن فيه العناصر دي: 
```java
transient Object[] elementData;
```

- و من لما بنيجي نعمل add هي بتشوف بقا ال capacity لو ينفع نضيف العنصر و في مكان فا بنضيفه
- لو مافيش مكان بتبدأ تكبر ال capacity و تنقل العناصر القديمه في ال array الجديد اللي بقا عند capacity اعلي. 

دي الفانكشن اللي احنا بنستعملها: 

```java
  public boolean add(E e) {
        modCount++;
        add(e, elementData, size);
        return true;
    }
```

بتنادي علي private add اللي بتشوف بقا هل في مكان ولا لا محتاجين نكبر ؟ 

```java
  private void add(E e, Object[] elementData, int s) {
        if (s == elementData.length)
            elementData = grow();
        elementData[s] = e;
        size = s + 1;
    }
```

لو محتاجين نكبر فا بننادي علي فانكشن ال grow و دي بترجعلي array جديد ب capacity اعلي و بيكون فيه العناصر القديمه

يبقا ممكن نلخص ال add بالشكل الاتي: 

عندنا arrayList فيها الاتي: 

```java
Size = 3
Capacity = 3

[Ahmed, Ali, Omar]
```

وبعدين كتبت:

```java
names.add("Hassan");
```

المساحة خلصت، فلازم الـ `ArrayList` تكبّر الـ Array الداخلية.

الخطوات:

1. تنشئ Array داخلية أكبر.
2. تنقل العناصر القديمة إليها.
3. تضيف العنصر الجديد.
4. تستخدم الـ Array الجديدة بدل القديمة.

فا ممكن نتخيل ان ده الشكل النهائي 

```
Before:
[Ahmed, Ali, Omar]

After:
[Ahmed, Ali, Omar, Hassan, null, null, ...]
```

الحجم الفعلي أصبح 4، لكن الـ Capacity ممكن تكون أكبر من 4.

و هنا محتاجين ناخد بالنا من حاجتين : 

- ال **Size:** عدد العناصر الموجودة فعلًا في الـ `ArrayList`.
- ال **Capacity:** عدد العناصر اللي الـ Array الداخلية تقدر تستوعبها قبل ما تحتاج تكبر.

نقدر نعرف الـ Size باستخدام:

```java
System.out.println(names.size()); 
```

لكن مفيش ميثود في `ArrayList` ترجع لك الـ Capacity مباشرة.

---
### هل الـ Capacity بتزيد بمقدار ثابت؟

- في java 21 , لما بنحتاج نكبر حجم ال array فا بيزيد غالبا بمقدار 50% من ال capacity القديمه.
- مثلًا، لو السعة القديمة 10، فا الجديده ممكن تبقي  15.
---
## 4. الـ Time Complexity بتاعتها

- في نقطه اتكلمنا فيها مهمه ان عمليه ال add في وقت معين ممكن تبقي محتاجه انها تزود ال capacity 
- و عمليه زياده ال capacity هي تعتبر O(n) و ده لاننا بنعمل array جديد و بننقل n من العناصر للجديد
- و لكن في حاجه مهمه ان انا ممكن اكبر ال capacity و بعدها اضيف مثلا 10 عناصر عادي في O(1) لان انا بقا عندي اماكن فاضيه , و عند العنصر ال 11 هيبقي لازم اعمل array اكبر يعني O(n) بس بعدها هعمل عمليات اضافه تاني كتير في O(1) و ده مفهوم ال  **O(1) Amortized**.

| العملية                      | Time Complexity  | السبب                                       |
| ---------------------------- | ---------------- | ------------------------------------------- |
| `get(index)`                 | `O(1)`           | الوصول المباشر باستخدام الـ index           |
| `set(index, value)`          | `O(1)`           | استبدال عنصر في مكان معروف                  |
| `add(value)` لنهاية الـ List | `O(1)` Amortized | شرحناها فوق                                 |
| `add(index, value)`          | `O(n)`           | ممكن تحتاج تحريك عناصر                      |
| `remove(index)`              | `O(n)`           | هنحتاج نعمل shift                           |
| `remove(value)`              | `O(n)`           | البحث عن العنصر، ثم تحريك العناصر عند الحذف |
| `contains(value)`            | `O(n)`           | البحث بشكل خطي                              |
| `size()`                     | `O(1)`           | الحجم محفوظ داخل الـ List                   |

---
## طيب أستخدم ArrayList إمتى؟

- بنستخدم ال ArrayList في اغلب الحالات, الا لو احتاجت حاجه هي احسن في data structure تاني زي ما هنشوف 
- لو عايز اوصل للعناصر بال index بشكل كتير
- لو هضيف بس في الاخر مش هحتاج اضيف كتير في النص او الاول 

---

## امتى ArrayList متكونش الاختيار الأفضل؟

- لو عايزين نضيف او نمسح من الاول هنا ال linked list هتبقي احسن 
- لو محتاجين العناصر متكونش duplicate هنا ال set هتبقي احسن 
- لو هنحتاج ان العناصر تبقا sorted , هنا ال tree set هتبقي احسن 
---
## Notes
- **نقدر نحدد ال capacity بتاعت ال ArrayList بالشكل ده:** 

```java
ArrayList<Integer> numbers = new ArrayList<>(1000);
```

بس بما اننا مضفناش اي عناصر فا ال size هيبقي ب zero عادي جدا

```java 
System.out.println(numbers.size()); // 0
```

أما لو كتبت:

```java
ArrayList<Integer> numbers = new ArrayList<>();
```

- كده ال capacity بتكون 0 
- لما بنضيف اول عنصر ال capacity هتبقي 10 
- بعد كده لما نيجي نضيف العنصر ال 11 ال capacity هتزيد بمقدار 50 في الميه من ال 10 فا هتبقي 15

---
**ال Diamond Operator** 
- نقدر نعرف ال arrayList بالشكل ده 

```java
ArrayList<Integer> a = new ArrayList<Integer>(); 
```
- طيب اولا ال <> اللي في اليمين او الشمال , دي وظيفتها انها تحدد النوع اللي هنخزنه
- مش لازم نكتب يمين و شمال النوع, ممكن نكتبفي بشمال بس 
- طيب ليه موجود في اليمين ؟ علشان لو هنستعمل var 
```java
var a = new ArrayList<Integer>(); 
```

- كده انت خليته من نوع Integer 
- و ال () ده ال contractor ممكن نحط في ال capacity 
- لو مكتبتش بقا نوع هو في الحاله دي هيقبل اي Object عادي: 
```java

 ArrayList a = new ArrayList<> ();
 var b = new ArrayList<> ();
 a.add(1);
 a.add("hello");
 a.add(12);

```
- هنا في الحالتين دول هو هيستقبل اي Object يعني نقدر نخزن فيه Integer , String و كله علي كله 
- خلي بالك انك لو حاولت تعمل حاجه زي: 
  ```java
  String s = a.get(1); // error 
  ```
- ده مش هيشتغل اصلا لان احنا خزنا Object مش String 
- بالتالي لازم تعمل Casting : 
```Java
 String s =(String) a.get(1);

```

- طبعا الطريقه دي وحشه اننا نحط كله علي كله في ال List بتاعتنا, الاحسن يبقا نوع واحد بس 

---
ال Abstraction 
- الاحسن اننا نعمل: 
- ```java 
  List<Integer> a = new ArrayList<>(); 
  ```

- بالشكل ده احنا اعتمدنا علي ال Interface 
- ده بيدينا مرونه اننا نقدر نبدل ال impl في اي وقت 
- يعني ممكن في نص البرنامج اروح عامل: 
  
```java
a = new LinkedList<>(); 
```

- خلي بالك من حاجتين: 

```java
// -------- 1 ----------
   List<Integer> a = new ArrayList ();
   a = new LinkedList<>();

// -------- 2 ----------
   List a = new ArrayList <Integer> ();
   a = new LinkedList<>();
   
 a = new LinkedList<Integer>();


```

- بالنسبه لرقم 1 فا هنا ال reference هو List بتشيل Integer و بالتالي  لما نعمل LinkedList فا هو هيبقي من نفس النوع اللي هو List بيشيل Integer بس بنستعمل ال impl بتاع ال linkedList
- في رقم 2 بقا ال reference ملهوش data type فا هو هيعمله بيشيل Object, في الحاله دي ال LinkedList هتقدر برضوا تشيل Object 
- في اخر سطر بقا حتي لو حددنا انها Integer , بس ال reference لسه من بيشيل Object  فا هتشيل كله برضوا عادي
---

مافيش كود لاننا غطينا كل ال functions في ال Docs اللي قبل كده :)

