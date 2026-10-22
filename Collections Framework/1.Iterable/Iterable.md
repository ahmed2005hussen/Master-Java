# Iterable 

ال iterable هو interface في java نقدر نعمله implement. معني iterable ان ال object ده نقدر نلوب عليه يعني نقدر نعمل iteration علي العناصر بتاعته. ![Iterable](../../images/Pasted%20image%2020261009104957.png)

عندنا 3 فانكشنز جوا ال iterable :
1. forEach()
2. iterator()
3. spliterator() -> we will not go throw it. (rarely used)
## forEach
- هي ببساطه طريقه علشان نقدر نعمل loop علي العناصر اللي عندي في ال array , list , او اي حاجه بت implements iterable.
- ال default بتاعها انها بتستعمل ال **Enhanced for loop** :
  ![Enhanced for loop](../../images/Pasted%20image%2020261009110439.png)

و ال Enhanced for loop بتستعمل من جواها ال iterator و هنعرفه كمان شويه.
Example:
```java
import java.util.List;

 void main() {

        List<String> s = List.of("ahmed" , "Hussein" , "Ahmed");
        s.forEach(System.out::println);
        System.out.println("-----------------");
        
        s.forEach(
	        c -> {
	            c = c.toUpperCase() ;
	            System.out.println(c);
	        }
         );

    }
```
---
## iterator
ال iterator هو interface معناه ان ال object ده اقدر اني اتحرك عليه واحده واحده.

الـ `iterator()` method بترجع Object من نوع `Iterator<T>`.

الـ Object ده بيسمحلك تتحرك بين عناصر الـ Iterable واحدًا واحدًا, و بيحتوي جواه علي:
![Iterator interface](../../images/Pasted%20image%2020261009110929.png)

- ناخد بالنا من حاجه الاول ان ال iterator ده مين اللي بيوفره؟ اللي بيوفره هو ال iterable و ده معناه اني مقدرش استعمل ال iterator لوحده لازم يبقا من object بي implement ال iterable و هنعرف ازاي نستعمله.

### hasNext()
بتعرفني هل في عنصر تاني ولا لا ؟ و بترجع true or false.
- طيب في الاول ال iterator بيكون واقف قبل اول حرف خالص, نقدر نقول عند -1
- لما بنقول hasNext() فا هو بيبص عند ال iterator + 1, لو في عنصر بيرجع true لو لا هيرجع false
- مش بتحرك ال iterator هي بس بتبص و تقول في مكان ولا لا.

### next ()
ال next بتحرك ال iterator للمكان اللي بعده بشكل فعلي.

### remove()
بتمسح العنصر اللي انا واقف عنده يعني اخر عنصر رجعته ال next , هنفهم مع المثال اكتر.

| Method      | وظيفتها                                             |
| ----------- | --------------------------------------------------- |
| `hasNext()` | هل فيه عنصر تاني نقدر نوصله؟                        |
| `next()`    | ترجع العنصر التالي وتنقل موضع الـ Iterator          |
| `remove()`  | تحذف آخر عنصر رجعته `next()`، لو عملية الحذف مدعومة |
ex :
```java
import java.util.*;

public class Main {

 List<String> a = new ArrayList<>();
        a.add("a");
        a.add("b");
        a.add("c");

 Iterator<String> it = a.iterator();

        System.out.println(it.next()); // a
        System.out.println(it.hasNext()); // true
        it.remove(); // remove a
        it.next(); // we at b
        it.next(); // we at c
        it.remove();// remove c
        System.out.println(it.hasNext());// false
        a.forEach(System.out::println); // b only 
}
```

- طيب مبدأيا ليه مستعملناش List.of ؟ لانها immutable فا لو استعملناها مش هنقدر نعمل remove و كان هيطلع ال exceptions دي: UnsupportedOperationException و ImmutableCollections.java
- لما جينا نعمل نوع iterator استعملها فانكشن .iterator() اللي جوا ال list لان ال list بت implement ال iterable و بالتالي نقدر نقول اننا استعملنا فانشكن ال iterator اللي جوا iterable
- في الاول كنا واقفين قبل حرف ال 'a' و علشان كده مينفعش نعمل remove من المكان ده لاننا مش واقفين علي حاجه
- لما بنتحرك لي next فا روحنا لحرف ال a, فا كده انا لو مسحت فا همسح اللي انا واقف عليه اللي هو حرف ال 'a'
- ال hasNext بترجعلي بس true or false علي حسب لو في عنصر بعدي تاني ولا لا.
- لو عملنا next بعد اخر element هناخد exception من نوع: NoSuchElementException

ال for enhanced بتستعمل من جواها iterator علشان تعدي علي العناصر.
لما تكتب:

```java
List<Integer> numbers = List.of(10, 20, 30);

for (Integer number : numbers) {
        System.out.println(number);
}
```

هتكون شكلها كده:

```java
List<Integer> numbers = List.of(10, 20, 30);

for (Iterator<Integer> iterator = numbers.iterator();
     iterator.hasNext();) {

Integer number = iterator.next();

    System.out.println(number);
}
```

- من هنا نفهم حاجه مهمه ان ال for-each هي عباره عن iteration mechanism عن طريق انها بتستعمل ال iterable عادي جدا.
- كمان نفهم ان ال for-each مش هتشتغل غير مع الحاجات اللي بت implements ال iterable
  ممكن يجي سؤال في بالنا انا لو عملت كده:

```java
int[] numbers = {1, 2, 3};

for (int number : numbers) {
        System.out.println(number);
}
```

ده هيشتغل حاجه مع ان المفروض ده مش حاجه من نوع iterable بس الفكره ان ال array لم بنعمله بالطريقه التقليديه دي بتستخدم آلية خاصة بالتعامل مع الـ Arrays، ومش محتاجة تكون `Iterable`.


## spliterator()



دي بتوفّر أداة للمرور على العناصر، مع إمكانيات إضافية زي تقسيم العناصر لأجزاء. و مش بنستعملها كتير و مش هندوس فيها.

---
نقدر نلخص ال iterator and iterable:

|المقارنة|Iterable|Iterator|
|---|---|---|
|النوع|Interface|Interface|
|الوظيفة|بتوفّر طريقة للحصول على Iterator|بيسمح بالمرور على العناصر|
|أهم Method|`iterator()`|`hasNext()` و`next()`|
|الاستخدام|تمكين الـ For-each|التحكم في الانتقال بين العناصر|
|الـ Package|`java.lang`|`java.util`|

---
## some notes
عندنا اراي بالشكل ده :
```java
  List<String> b = new ArrayList<>();
        b.add("a");
        b.add("b");
        b.add("c");
        b.add("d");
        b.add("e");
        b.add("f");
```

لو عملنا :
1. ex1
```java
   for(int i = 0 ; i < b.size(); i++){
        if(i == 1){
        b.remove("b");
                continue;
                        }
                        System.out.println(b.get(i));
        }
```

- ده هيطلع نواتج غلط لانه هيكنسل ال c لان لما بنمسح بنعمل shift فا ال c بدل ما كانت عند 2 هتبقي عند 1 اللي هو كان مكان ال b فا لما يتعمل continue فا احنا كنسلنا العنصر ده
-  هنا محصلش exception لان ال b.size لما نمسح عنصر قيمتها هتتعدل جوال اللوب فا بدل ما كانت i < 6 هتبقي i<5 و بالتالي مش هنطلع بره ال size

2. ex2
```java
 int size = b.size();
        for(int i = 0 ; i < size; i++){
        if(i == 1){
        b.remove("b");
                continue;
                        }
                        System.out.println(b.get(i));
        }
```

- هنا هيحصل exception من نوع: IndexOutOfBoundsException
- و ده علشان احنا حطينا ال size بشكل fixed بي 6 فا لما مسحنا فا اللوب لسه هتلوب لحد 6 و ده هيرمي ايرور

3. ex3
```java
 for(String c : b){
        b.remove("a");
        }
```

- ده برضوا هيرمي exception من نوع: ConcurrentModificationException
- هنا اتفقنا ان ال enhanced loop اصلا بتستعمل iterator فا لما جينا مسحنا العنصر احنا مسحناه عن طريق ال remove اللي جوا ال list فا بالتالي ال iterator بالنسباله هو لسه واقف في مكانه و محصلش حاجه.
- ال iterator شغاله بأليه معينه انه بيشوف ال conCurrent يعني نقدر نقول انه بيبص في ال list و في ال iterator عنده فا هيعرف ان حصل تعديل في ال list و انها صغرت بس التعديل ده مش عندي فا هيرمي الايرور ده
4. ex4
```java
        Iterator<String> it1 = b.iterator();

        while(it1.hasNext()){

String c = it1.next();
            if(c == "a"){
        it1.remove();
            }
                    }
```

و دي بقا أمن و احسن طريقه لاننا اتعاملنا مع ال iterator نفسه.

--- 
فا المشكله الاساسيه هتكون في ال for-each لو مسحنا منها عنصر , او في ال for العاديه لو بنعتمد علي fixed number في ال condition او بنعمل continue.
