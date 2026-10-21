# 1. Queue Interface


الـ `Queue` هي Interface بتمثل طابور من العناصر، وغالبًا بتشتغل بمبدأ:

**FIFO = First In, First Out**
##  العلاقة بين Queue وCollection

الـ `Queue` بترث من `Collection`:

```
Iterable
   |
Collection
   |
  Queue
```

وبالتالي، هي بتدعم methods زي `size()` و`isEmpty()` و`contains()` و`clear()`، بالإضافة إلى الـ methods الخاصة بالطوابير.

أشهر implementations للـ `Queue`:

- `LinkedList`
- `PriorityQueue`
- `ArrayDeque`، ودي كمان بتنفذ `Deque`

خلينا نبدأ بأهم الـ methods.

## ثالثًا: أهم Methods في Queue

الـ `Queue` بتوفر مجموعتين من الـ methods: مجموعة للإضافة ومجموعة للحذف أو الاطلاع على أول عنصر.

|العملية|Method|لو العملية فشلت|
|---|---|---|
|إضافة عنصر|`add(e)`|ممكن ترمي Exception|
|إضافة عنصر|`offer(e)`|بترجع `false`|
|حذف وإرجاع أول عنصر|`remove()`|بترمي `NoSuchElementException` لو فاضية|
|حذف وإرجاع أول عنصر|`poll()`|بترجع `null` لو فاضية|
|الاطلاع على أول عنصر|`element()`|بترمي `NoSuchElementException` لو فاضية|
|الاطلاع على أول عنصر|`peek()`|بترجع `null` لو فاضية|

### 1. `add()` و`offer()`

الاتنين بيضيفوا عنصرًا للـ Queue.

```java
Queue<Integer> queue = new LinkedList<>();

queue.add(10);
queue.offer(20);
queue.offer(30);

System.out.println(queue);
```

الناتج:

```
[10, 20, 30]
```

طيب ليه عندنا اتنين بيعملوا نفس الوظيفة تقريبًا؟

الفرق بيظهر لو الـ Queue عندها حد أقصى للسعة، والعملية مش قادرة تضيف عنصرًا جديدًا:

- `add()` ممكن ترمي Exception.
- `offer()` بترجع `false`.

في `LinkedList` و`PriorityQueue` و`ArrayDeque`، السعة مش محددة بحد أقصى ثابت من ناحية الـ API، لكن `offer()` بتكون مفيدة جدًا مع Implementations محدودة السعة، زي بعض أنواع الـ Blocking Queues.

### 2. `remove()` و`poll()`

الاتنين بيحذفوا ويرجعوا العنصر الموجود في مقدمة الـ Queue.

```java
Queue<Integer> queue = new LinkedList<>();

queue.offer(10);
queue.offer(20);
queue.offer(30);

System.out.println(queue.poll()); // 10
System.out.println(queue.poll()); // 20

System.out.println(queue); // [30]
```

لكن لو الـ Queue فاضية:

```java
Queue<Integer> queue = new LinkedList<>();

System.out.println(queue.poll()); // null
```

أما:

```java
queue.remove();
```

فلو الـ Queue فاضية، هترمي `NoSuchElementException`.

**إمتى أستخدم `poll()`؟**

لما تكون عايز تحذف العنصر الأول، وممكن الطابور يكون فاضي، ومش عايز Exception لمجرد إن مفيش عناصر.

### 3. `element()` و`peek()`

الاتنين بيرجعوا أول عنصر من غير ما يحذفوه.

```java
Queue<Integer> queue = new LinkedList<>();

queue.offer(10);
queue.offer(20);

System.out.println(queue.peek()); // 10
System.out.println(queue);        // [10, 20]
```

لاحظ إن `peek()` ما حذفتش العنصر.

ولو الـ Queue فاضية:

```java
System.out.println(queue.peek()); // null
```

أما `element()` فبترمي `NoSuchElementException` لو الطابور فاضي.

---
# 2. LinkedList كـ Queue

في Java، الـ `LinkedList` بتنفذ أكتر من Interface:

```
                 List
                  |
              LinkedList
                  |
                 Queue
                  |
                 Deque
```

يعني تقدر تستخدم نفس الـ Object بأكتر من طريقة:

```java
LinkedList<Integer> list = new LinkedList<>();
```

أو:

```java
Queue<Integer> queue = new LinkedList<>();
```

أو:

```java
Deque<Integer> deque = new LinkedList<>();
```

الـ Object في الحالات دي هو `LinkedList`، لكن نوع الـ Reference بيحدد الـ methods المتاحة لك مباشرةً من خلال المتغير.

### إيه اللي بيميز LinkedList هنا؟
الـ `LinkedList` في Java عبارة عن Doubly Linked List.
كل Node بتحتفظ بمراجع للعقدة السابقة والتالية، وده بيخلي إضافة أو حذف العناصر من الطرفين عملية سريعة.

بالنسبة لعمليات الـ Queue المعتادة:

|Operation|Time Complexity|
|---|---|
|`offer()`|O(1)|
|`poll()`|O(1)|
|`peek()`|O(1)|

لكن لو استخدمت `contains()`، فالعملية O(n) في أسوأ الحالات؛ لأنها بتحتاج تمر على العناصر للبحث عن القيمة.

---
# 3. PriorityQueue

## أولًا: يعني إيه PriorityQueue؟

الـ `PriorityQueue` هي Queue بتتعامل مع العناصر حسب **الأولوية**، مش حسب ترتيب إضافتها.
والترتيب الافتراضي للأرقام هو تصاعدي؛ يعني أصغر رقم هو اللي بيكون في مقدمة الطابور.

## ثانيًا: مثال على PriorityQueue

```java
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        pq.offer(40);
        pq.offer(10);
        pq.offer(30);
        pq.offer(20);

        System.out.println(pq.peek()); // 10

        System.out.println(pq.poll()); // 10
        System.out.println(pq.poll()); // 20
        System.out.println(pq.poll()); // 30
        System.out.println(pq.poll()); // 40
    }
}
```

رغم إننا أضفنا الأرقام بالترتيب `40, 10, 30, 20`، فإن الحذف رجّعهم بالترتيب `10, 20, 30, 40`.

**الـ `PriorityQueue` مش FIFO؛ العنصر اللي له الأولوية الأعلى هو اللي بيخرج أولًا.**

بالنسبة للأرقام، الأولوية الافتراضية بتكون للأصغر.

## ثالثًا: هل PriorityQueue بتخزن العناصر مرتبة بالكامل؟

لأ، ودي نقطة مهمة جدًا!

الـ `PriorityQueue` في Java بتستخدم بنية   **Binary Heap**، وبالتحديد Min-Heap في الترتيب الافتراضي.

الـ Heap بتضمن إن العنصر الموجود عند الـ Root هو أصغر عنصر، لكن مش بتضمن إن كل العناصر تكون مرتبة تصاعديًا داخل البنية.

تخيّل الشكل التالي:

```
             10
            /  \
          20    30
         /
        40
```

الشكل ده يحقق خصائص الـ Min-Heap:

- الأب أصغر من أو يساوي أبناءه.
- أصغر عنصر موجود عند الـ Root.
- الشجرة بتكون Complete Binary Tree.

لكن مش مطلوب إن كل العناصر تكون مرتبة تصاعديًا عند المرور على الشجرة.

### طيب لو طبعت الـ PriorityQueue؟

```java
PriorityQueue<Integer> pq = new PriorityQueue<>();

pq.add(40);
pq.add(10);
pq.add(30);
pq.add(20);

System.out.println(pq);
```

**ما تعتمدش على إن الطباعة هتكون `[10, 20, 30, 40]`.**

الـ Iterator والـ `toString()` مش بيضمنوا المرور على العناصر بترتيب الأولوية.

لو عايز العناصر تخرج بترتيب الأولوية، استخدم `poll()` بشكل متكرر:

```java
while (!pq.isEmpty()) {
    System.out.println(pq.poll());
}
```

الناتج هيكون:

```
10
20
30
40
```

خلي بالك إن `poll()` بتحذف العناصر من الـ Queue، فلو مش عايز تغيّر الأصلية، اعمل نسخة منها الأول.

---
## رابعًا: إزاي الـ PriorityQueue بتشتغل داخليًا؟

الـ `PriorityQueue` بتستخدم **Binary Heap ممثلة داخليًا باستخدام Array**.

وده معناه إنها مش محتاجة Nodes منفصلة ومراجع للأبناء زي الـ Linked Tree التقليدية.

ممكن تتخيّل الـ Array بالشكل ده:

```
Index:   0   1   2   3
       +---+---+---+---+
Array: | 10| 20| 30| 40|
       +---+---+---+---+
```

ده مجرد مثال لتمثيل Heap معينة؛ مش شرط يكون شكل الـ Array الفعلي في كل لحظة هو ده.

بالنسبة للـ Binary Heap الممثلة باستخدام Array، لو العنصر موجود عند Index اسمه `i`، فمواقع الأبناء بتكون:

```
Left Child  = 2 * i + 1
Right Child = 2 * i + 2
Parent      = (i - 1) / 2
```

المعادلات دي بتفترض إن الـ Index بيبدأ من صفر، وإننا بنتعامل مع عنصر مش موجود عند الـ Root لما نحسب موقع الأب.

### إيه اللي بيحصل لما تضيف عنصرًا؟

لما تعمل:

```
pq.offer(15);
```

الـ `PriorityQueue` بتضيف العنصر في المكان المناسب في الـ Array، وبعد كده ممكن تعيد ترتيب بعض العناصر علشان تحافظ على خصائص الـ Heap.

العملية دي اسمها عادةً **Sift Up** أو **Heapify Up**.

### وإيه اللي بيحصل لما تحذف العنصر الأول؟

لما تعمل:

```
pq.poll();
```

الـ `PriorityQueue` بترجع أصغر عنصر، وبعدها بتعيد ترتيب العناصر للمحافظة على خصائص الـ Heap.

العملية دي بتُعرف عادةً باسم **Sift Down** أو **Heapify Down**.

---
## خامسًا: Time Complexity للـ PriorityQueue

|Operation|Time Complexity|
|---|---|
|`peek()`|O(1)|
|`offer()`|O(log n)|
|`add()`|O(log n)|
|`poll()`|O(log n)|
|`remove(Object)`|O(n)|
|`contains(Object)`|O(n)|
ليه `peek()` هي O(1)؟

لأنها بتقرأ العنصر الموجود عند الـ Root مباشرةً، من غير ما تحتاج تعيد ترتيب الـ Heap.

ليه `offer()` و`poll()` هما O(log n)؟

لأن ارتفاع الـ Binary Heap المتوازنة من حيث شكلها هو O(log n)، وإعادة ترتيب العناصر بتحتاج المرور على عدد من المستويات يتناسب مع ارتفاع الشجرة.

أما `contains()` فهي O(n) في أسوأ الحالات؛ لأن الـ Heap مش مرتبة بالكامل، وبالتالي مش ممكن تعتمد على البحث الثنائي للوصول لأي عنصر.

---
## سادسًا: إزاي أعمل PriorityQueue بترتيب تنازلي؟

افتراضيًا:

```java
PriorityQueue<Integer> pq = new PriorityQueue<>();
```

أصغر رقم بيكون له الأولوية الأعلى.

لو عايز أكبر رقم يخرج الأول، استخدم `Comparator.reverseOrder()`:

```java
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) {
        PriorityQueue<Integer> pq =
                new PriorityQueue<>(Comparator.reverseOrder());

        pq.offer(10);
        pq.offer(40);
        pq.offer(20);
        pq.offer(30);

        while (!pq.isEmpty()) {
            System.out.println(pq.poll());
        }
    }
}
```

الناتج:

```
40
30
20
10
```

ملحوظة: ده بيغيّر ترتيب الأولوية، مش نوع الـ Data Structure الأساسية.

---

# 4. Deque Interface

الاسم الكامل هو **Double-Ended Queue**، 

فكرتها إنها Queue بتسمح لك تضيف وتحذف من الطرفين:

- Front: أول الطابور.
- Rear: آخر الطابور.

يعني مش مقيدة بنفس سلوك الـ FIFO التقليدي.

## أولًا: شكل Deque

تخيّل إن عندك:

```
Front                       Rear
  |                           |
  v                           v
[10]  ↔  [20]  ↔  [30]  ↔  [40]
```

تقدر تعمل:

- إضافة عند الـ Front.
- إضافة عند الـ Rear.
- حذف من الـ Front.
- حذف من الـ Rear.

وده بيخلي الـ `Deque` مناسبة لتطبيقات مختلفة، زي الـ Queue والـ Stack.

## ثانيًا: العلاقة بين Deque وQueue

الـ `Deque` بترث من `Queue`:

```
Iterable
   |
Collection
   |
  Queue
   |
  Deque
```

وبالتالي، الـ `Deque` بتدعم methods الـ `Queue`، وبتضيف methods خاصة بالتعامل مع الطرفين.

من أشهر implementations:

- `ArrayDeque`
- `LinkedList`

**مهم:** الـ `Deque` نفسها Interface، أما `ArrayDeque` و`LinkedList` فهما Classes بتنفذها.

---
## ثالثًا: أهم Methods في Deque

الـ `Deque` عندها مجموعات من الـ methods للإضافة والحذف والاطلاع على العناصر من الطرفين.

|العملية|من البداية Front|من النهاية Rear|
|---|---|---|
|إضافة مع احتمال Exception|`addFirst(e)`|`addLast(e)`|
|إضافة بدون Exception عند فشل الإضافة|`offerFirst(e)`|`offerLast(e)`|
|حذف مع Exception لو فاضية|`removeFirst()`|`removeLast()`|
|حذف وإرجاع `null` لو فاضية|`pollFirst()`|`pollLast()`|
|قراءة بدون حذف، مع Exception لو فاضية|`getFirst()`|`getLast()`|
|قراءة بدون حذف، وإرجاع `null` لو فاضية|`peekFirst()`|`peekLast()`|

خلينا نفهم كل مجموعة بمثال.

### 1. الإضافة من الطرفين

```
Deque<Integer> deque = new ArrayDeque<>();

deque.addFirst(20);
deque.addFirst(10);
deque.addLast(30);
deque.addLast(40);

System.out.println(deque);
```

الناتج:

```
[10, 20, 30, 40]
```

إيه اللي حصل؟

1. أضفنا `20` في البداية.
2. أضفنا `10` في البداية، فبقت قبل `20`.
3. أضفنا `30` في النهاية.
4. أضفنا `40` في النهاية.

لاحظ إن `addFirst()` بتضيف العنصر في البداية، و`addLast()` بتضيفه في النهاية.

### 2. الحذف من الطرفين

```
Deque<Integer> deque = new ArrayDeque<>();

deque.addLast(10);
deque.addLast(20);
deque.addLast(30);
deque.addLast(40);

System.out.println(deque.pollFirst()); // 10
System.out.println(deque.pollLast());  // 40

System.out.println(deque); // [20, 30]
```

الـ `pollFirst()` حذفت أول عنصر، والـ `pollLast()` حذفت آخر عنصر.

### 3. قراءة العناصر من الطرفين

```
Deque<Integer> deque = new ArrayDeque<>();

deque.addLast(10);
deque.addLast(20);
deque.addLast(30);

System.out.println(deque.peekFirst()); // 10
System.out.println(deque.peekLast());  // 30

System.out.println(deque); // [10, 20, 30]
```

الـ `peekFirst()` و`peekLast()` بيرجعوا العناصر من غير حذفها.

---

# 5. ArrayDeque

دي من أهم الـ Implementations للـ `Deque`.

الـ `ArrayDeque` بتستخدم **Circular Array**  ومصممة علشان تدعم الإضافة والحذف من الطرفين بكفاءة.

```
Deque<Integer> deque = new ArrayDeque<>();
```

## أولًا: إيه اللي بيميز ArrayDeque؟

- بتدعم الإضافة والحذف من الطرفين.
- بتقدر تستخدمها كـ Queue أو Stack.
- مش بتسمح بـ `null`.
- مش Thread-safe؛ يعني مش مصممة للاستخدام المتزامن بين Threads من غير تنسيق إضافي.
- غالبًا بتكون اختيارًا أفضل من `LinkedList` لما تحتاج Deque عادية، خصوصًا لو مش محتاج عمليات `List` أو `null`.

## ثانيًا: Time Complexity

|Operation|Time Complexity|
|---|---|
|`addFirst()`|O(1) amortized|
|`addLast()`|O(1) amortized|
|`pollFirst()`|O(1)|
|`pollLast()`|O(1)|
|`peekFirst()`|O(1)|
|`peekLast()`|O(1)|
|`contains()`|O(n)|

**يعني إيه Amortized O(1)؟**

الـ `ArrayDeque` ممكن تحتاج تكبّر الـ Array الداخلية لما السعة الحالية متبقاش كافية، وده بيحتاج نقل العناصر، والعملية دي ممكن تكون O(n).

لكن عمليات الإضافة العادية بتكون سريعة، ولما نحسب التكلفة المتوسطة لعدد كبير من عمليات الإضافة، بنوصل إلى Amortized O(1).

## ثالثًا: ليه ArrayDeque ممكن تكون أفضل من LinkedList؟

الـ `LinkedList` بتحتاج Nodes منفصلة، وكل Node بتحتفظ بمراجع للعناصر المجاورة.

أما `ArrayDeque` فبتخزن العناصر داخل **Circular Array**، وده بيقلل الـ Memory Overhead وبيساعد على تحسين الأداء في الاستخدامات المعتادة.

|Feature|ArrayDeque|LinkedList|
|---|---|---|
|البنية الداخلية|Resizable Array|Doubly Linked List|
|إضافة وحذف من الطرفين|Amortized O(1) / O(1)|O(1)|
|استهلاك الذاكرة|غالبًا أقل|غالبًا أعلى بسبب الـ Nodes|
|السماح بـ `null`|لا|نعم|
|تنفيذ `List`|لا|نعم|
|تنفيذ `Deque`|نعم|نعم|

لو محتاج مجرد Queue أو Stack، فـ `ArrayDeque` غالبًا اختيار ممتاز.

---
# 6. إزاي أستخدم Deque كـ Stack؟

دي نقطة مهمة جدًا.

إحنا عادةً بنربط الـ Stack بمبدأ:

**LIFO = Last In, First Out**

يعني آخر عنصر يدخل هو أول عنصر يخرج.

مثلًا، لو ضفت:

```
10 → 20 → 30
```

فالـ `30` هو أول عنصر يخرج.

تقدر تستخدم `Deque` كـ Stack بسهولة.

```java
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
    public static void main(String[] args) {
        Deque<Integer> stack = new ArrayDeque<>();

        stack.push(10);
        stack.push(20);
        stack.push(30);

        System.out.println(stack.pop());  // 30
        System.out.println(stack.pop());  // 20
        System.out.println(stack.peek()); // 10
    }
}
```

### إيه اللي بيحصل هنا؟

الـ `push()` بتضيف العنصر في البداية، والـ `pop()` بتحذف وترجع العنصر الموجود في البداية، والـ `peek()` بتقرأ العنصر الموجود في البداية من غير حذفه.

بالتالي، آخر عنصر ضفناه هو أول عنصر خرج.

### أهم Methods لاستخدام Deque كـ Stack

|Method|الوظيفة|
|---|---|
|`push(e)`|إضافة عنصر أعلى الـ Stack|
|`pop()`|حذف وإرجاع العنصر الأعلى|
|`peek()`|قراءة العنصر الأعلى من غير حذف|

خلي بالك إن `pop()` بترمي `NoSuchElementException` لو الـ Deque فاضية.

ولو عايز عملية حذف آمنة ترجع `null` عند الفراغ، تقدر تستخدم `pollFirst()`.

# 7. الفرق بين Queue وPriorityQueue وDeque

دلوقتي خلينا نجمع الفروق الأساسية بينهم.

|Feature|Queue عادية|PriorityQueue|Deque|
|---|---|---|---|
|الفكرة|طابور عناصر|طابور حسب الأولوية|طابور من الطرفين|
|ترتيب الخروج|غالبًا FIFO|حسب الأولوية|حسب الطرف اللي بتحذف منه|
|الإضافة من البداية|مش من الـ Queue API الأساسية|لا|نعم|
|الإضافة من النهاية|`offer()`|`offer()`|`offerLast()`|
|الحذف من البداية|`poll()`|`poll()`|`pollFirst()`|
|الحذف من النهاية|مش من الـ Queue API الأساسية|لا|`pollLast()`|
|أشهر Implementations|`LinkedList`|`PriorityQueue`|`ArrayDeque`, `LinkedList`|

خلي بالك إن كلمة «Queue عادية» هنا بتشير إلى الـ Queue اللي بتتعامل بمبدأ FIFO، مش إلى إن الـ Interface بتفرض الترتيب ده على كل Implementations.

---

# 8. مقارنة الـ Time Complexity

دي أهم العمليات اللي لازم تبقى عارف تعقيدها.

| Operation          | LinkedList كـ Queue | PriorityQueue          | ArrayDeque     |
| ------------------ | ------------------- | ---------------------- | -------------- |
| `offer()`          | O(1)                | O(log n)               | O(1) amortized |
| `poll()`           | O(1)                | O(log n)               | O(1)           |
| `peek()`           | O(1)                | O(1)                   | O(1)           |
| `contains()`       | O(n)                | O(n)                   | O(n)           |
| الإضافة من البداية | O(1)                | غير متاحة              | O(1) amortized |
| الإضافة من النهاية | O(1)                | غير متاحة كعملية طرفية | O(1) amortized |

الـ `PriorityQueue` مختلفة؛ لأن الإضافة والحذف بيحتاجوا المحافظة على ترتيب الـ Heap.

أما `ArrayDeque` و`LinkedList`، فالإضافة والحذف من الأطراف بيكونوا سريعين.

---
