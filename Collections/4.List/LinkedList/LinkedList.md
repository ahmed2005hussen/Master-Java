- ال LinkedList  هو implementation لل List.
- ال LinkedList مبنيه علي Doubly Linked List.

![](../../../images/Pasted%20image%2020261014202526.png)
- ال Linked List من جوه عندها Node و بيحتوي علي الاتي: 
1. ال item الحاجه اللي مخزننها 
2. ال next و ده reference لل بعدي
3. ال prev و ده reference للي قبلي 

```java
 private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }
```

- ال linked List بيكون عندها اتنين reference اساسين و هما 
1. ال first node 
2. ال last node
```java
    /**
     * Pointer to first node.
     */
    transient Node<E> first;

    /**
     * Pointer to last node.
     */
    transient Node<E> last;
```

-  ال linked list مش لازم تتخزن في contiguous memory زي ال ArrayList
- كل Node ممكن تتخزن في اي حته عادي و ال reference بيربطهم ببعض 
---
## الـ Time Complexity بتاعتها

| Operation               | LinkedList |
| ----------------------- | ---------- |
| `get(index)`            | `O(n)`     |
| `set(index, value)`     | `O(n)`     |
| `add(value)` في النهاية | `O(1)`     |
| `addFirst(value)`       | `O(1)`     |
| `removeFirst()`         | `O(1)`     |
| `removeLast()`          | `O(1)`     |
| `contains(value)`       | `O(n)`     |
| `add(index, value)`     | `O(n)`     |
| `remove(index)`         | `O(n)`     |
و لو هنقارن بين ال ArrayList و ال LinkedList 

| Operation               | ArrayList        | LinkedList |
| ----------------------- | ---------------- | ---------- |
| `get(index)`            | `O(1)`           | `O(n)`     |
| `set(index, value)`     | `O(1)`           | `O(n)`     |
| `add(value)` في النهاية | `O(1)` Amortized | `O(1)`     |
| `addFirst(value)`       | `O(n)`           | `O(1)`     |
| `removeFirst()`         | `O(n)`           | `O(1)`     |
| `removeLast()`          | `O(1)`           | `O(1)`     |
| `contains(value)`       | `O(n)`           | `O(n)`     |
| `add(index, value)`     | `O(n)`           | `O(n)`     |
| `remove(index)`         | `O(n)`           | `O(n)`     |

---
## Notes
- ال LinkedList في Java لما بنقولها get(5) مثلا , فا هي بتدور في الناحيه الاقرب, يعني لو الاقرب من ال last فا هتبدأ iteration من ورا, و لو الاقرب من ال First هتبدأ iteration من ال First 


- في Java، الـ `LinkedList` بتنفّذ أكتر من Interface:

``` java
public class LinkedList<E>
    extends AbstractSequentialList<E>
    implements List<E>, Deque<E>, Cloneable, java.io.Serializable
```

- ال `List`: بتسمح لنا نتعامل مع العناصر باستخدام الـ index.
- ال `Deque`: بتسمح لنا تتعامل مع العناصر من أول ال List وآخرها.
- وده معناه إنك تقدر تستخدم `LinkedList` كـ List عادية، أو كـ Queue، أو كـ Stack.

### أ. استخدامها كـ Queue

- الـ Queue بتستخدم  مبدأ FIFO، يعني First In, First Out.
- أول عنصر يدخل هو أول عنصر يخرج.

```java
Queue<String> queue = new LinkedList<>();

queue.offer("Ahmed");
queue.offer("Ali");
queue.offer("Omar");

System.out.println(queue.poll()); // Ahmed
System.out.println(queue.poll()); // Ali
```

### ب. استخدامها كـ Stack

- الـ Stack بتستخدم مبدأ LIFO، يعني Last In, First Out.
- آخر عنصر يدخل هو أول عنصر يخرج.

```java
Deque<String> stack = new LinkedList<>();

stack.push("Ahmed");
stack.push("Ali");
stack.push("Omar");

System.out.println(stack.pop()); // Omar
System.out.println(stack.pop()); // Ali
```

- هنبقي نشوف Interface ال Queue و ال Stack اكتر لما نوصله 
-  و لكن Stack أو Queue فقط، ففي الغالب الأفضل تستخدم `ArrayDeque` 
- بدل `LinkedList`، لأنها عادةً أكثر كفاءة في الذاكرة والأداء لهذه الاستخدامات.
---

- كنا قولنا ان ال Node بتخزن 3 حاجات و ده معناه انها بتاخد Memory اكتر مقارنه بال ArrayList
- هنا بتقابلنا مشكله ال Locality of reference كنا اخدناها في ال HPC, اختصارا نقدر نقول ان ال ArrayList بيخزن العناصر ورا بعض فا لما ال CPU يجيب شويه بيانات و يحطها في ال Cache علشان يشتغل عليها فا هو هيجيب Block من ال memory و بما ان ال Array List اصلا contiguous فا مش هنحتاج كل شويه نروح ال memory نجيب ال index اللي عليه الدور لان احنا جايبين Block ممكن يبقا فيه 10 عناصر مثلا و ده وفر علينا 10 مرات نروح لل  memory و نرجع 

- لكن ال LinkedList هي مش متخزنه ورا بعض, و بالتالي ال Block اللي ال CPU هيجيبه احنا مش ضامنين هو فيه كام عنصر من ال LinkedLIst , و بالتالي هروح لل memory كتير علشان اجيب ال indexes بتاعتي

-  و علشان كده حتي في الحالات اللي فيها ال ArrayList and LinkedList هما الاتنين O(n) ممكن ال ArrayList تبقي احسن .
---
## امتى أستخدم LinkedList؟

- لو عايزين نضيف او نمسح من قدام او من الاخر لانهم O(1)
- و لكن زي ما قولنا اغلب الحالات بنستعمل ال ArrayList عادي جدا


مافيش كود لاننا غطينا كل ال functions في ال Docs اللي قبل كده :)
