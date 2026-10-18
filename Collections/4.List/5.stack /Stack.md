
- ال stack شغال بمبدأ **LIFO = Last In, First Out**
- ال stack بي extends من ال vector 
```java
public class Stack<E> extends Vector<E> { } 
```

و هنلاقي جواه شويه functions زي: 

| Method      | وظيفتها                                          | Time Complexity   |
| ----------- | ------------------------------------------------ | ----------------- |
| `push(e)`   | إضافة عنصر فوق الـ Stack                         | `O(1)` في المعتاد |
| `pop()`     | حذف وإرجاع العنصر اللي فوق                       | `O(1)`            |
| `peek()`    | إرجاع العنصر اللي فوق من غير حذفه                | `O(1)`            |
| `empty()`   | التحقق هل الـ Stack فاضية                        | `O(1)`            |
| `search(o)` | البحث عن عنصر وإرجاع موضعه من الأعلى، بدءًا من 1 | `O(n)`            |
- طبعا بالاضافه لل functions اللي جوا ال Vector عادي و ده غلط في التصميم لان ال stack المفروض ميكونش فيه add , get و الحاجات دي , فا الفانكشنز دي هتشتغل بس ده مش صح لان المفروض ال stack مفهوش الكلام ده 
- طبعا هيبقي synchronized زي ال vector 
### مثال

```java 
Stack<Integer> stack = new Stack<>();

stack.push(10);
stack.push(20);
stack.push(30);

System.out.println(stack);       // [10, 20, 30]

System.out.println(stack.peek()); // 30
System.out.println(stack.pop());  // 30
System.out.println(stack.pop());  // 20

System.out.println(stack);        // [10]
```
---

- ده مش احسن impl لل stack و مش بنستعمله غالبا 

- بنستعمل: 
```java
Deque<Integer> stack = new ArrayDeque<>();
```

- و هنلاقي عندنا بقا ال push , pop , peek 
- هنبقي ندوس في الطريقه دي اكتر قدام, في طريقه ال stack الفعليه قدام 
---

و بس كده ده اخر حاجه في ال List interface , و زي ما قولنا مش بنستعمل ال stack ده لان في حاجه احسن ,و مش هنستعمل ال vector برضوا الا في حالات قليله اويي و ممكن نستعمل حاجه غيره اصلا.

و شكرا :) 