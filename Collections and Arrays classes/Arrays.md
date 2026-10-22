
- ال `Collections` بتوفّر methods للتعامل مع الـ Collections زي `List`.
- ال `Arrays` بتوفّر methods للتعامل مع الـ **Arrays العادية**، زي `int[]` و`String[]`.

# 1. إيه هو `Arrays`؟

هو كلاس موجود في `java.util`:

```java
import java.util.Arrays;
```
---
# 2. `Arrays.sort()`

بتعمل Sorting للـ Array تصاعديًا.

```java
int[] arr = {5, 2, 8, 1, 3};

Arrays.sort(arr);

System.out.println(Arrays.toString(arr)); // [1, 2, 3, 5, 8]
```

ترتيب تنازلي: 

```java
Integer[] arr = {5, 2, 8, 1, 3};

Arrays.sort(arr, Comparator.reverseOrder());

System.out.println(Arrays.toString(arr)); // [8, 5, 3, 2, 1]
```

**خد بالك من الفرق ده:**

```
int[] arr
```

دي Array من الـ primitive type `int`، ومش هتقدر تستخدم معاها `Comparator.reverseOrder()` بالشكل ده.

---
# 3. `Arrays.toString()`

دي بتساعدك تطبع محتويات الـ Array بشكل واضح.

لو كتبت:

```java 
int[] arr = {10, 20, 30};

System.out.println(arr); // [I@...
```

لأنك بتطبع الـ Array object نفسه، مش محتوياته بالشكل اللي متوقعه.

الحل:

```java
System.out.println(Arrays.toString(arr)); // [10, 20, 30]
```

---
# 4. `Arrays.binarySearch()`

بتبحث عن عنصر داخل Array مرتبة.

```java
int[] arr = {10, 20, 30, 40, 50};

int index = Arrays.binarySearch(arr, 30);

System.out.println(index); // 2
```

---

# 5. `Arrays.equals()`

لو عايز تقارن بين Array وArray تانية من حيث المحتويات، استخدم `Arrays.equals()`.

مثلًا:

```java
int[] arr1 = {1, 2, 3};
int[] arr2 = {1, 2, 3};
int[] arr3 = {1, 2, 4};

System.out.println(Arrays.equals(arr1, arr2)); // true 
System.out.println(Arrays.equals(arr1, arr3)); // false 
```

خد بالك إن:

```
arr1 == arr2
```

مش بتقارن محتويات الـ Arrays؛ دي بتقارن هل الاتنين نفس الـ object في الذاكرة ولا لأ.

---
# 6. `Arrays.fill()`

بتملأ كل عناصر الـ Array بقيمة معينة.

```java
int[] arr = new int[5];

Arrays.fill(arr, 7);

System.out.println(Arrays.toString(arr)); // [7, 7, 7, 7, 7]
```

### طيب لو عايز تملأ جزء بس؟

تقدر تحدد البداية والنهاية:

```java
int[] arr = {1, 2, 3, 4, 5};

Arrays.fill(arr, 1, 4, 0);

System.out.println(Arrays.toString(arr)); // [1, 0, 0, 0, 5]
```

ليه؟

```
Arrays.fill(arr, fromIndex, toIndex, value);
```

الـ `fromIndex` معانا، لكن الـ `toIndex` مش معانا.

---
# 7. `Arrays.copyOf()`

لو عايز تعمل نسخة من Array، استخدم:

```java
int[] arr = {10, 20, 30};

int[] copy = Arrays.copyOf(arr, arr.length);

System.out.println(Arrays.toString(copy)); // [10, 20, 30]
```
### طيب لو عايز تغيّر طول النسخة؟

```java 
int[] copy = Arrays.copyOf(arr, 5);

System.out.println(Arrays.toString(copy));//[10, 20, 30, 0, 0]
```

**مهم:** دي نسخة جديدة للـ Array نفسها، لكن لو بتنسخ Array من objects، فالنسخ بيكون للمراجع، مش نسخة مستقلة من كل object.

---
# 8. `Arrays.copyOfRange()`

دي بتنسخ جزءًا من Array.

```java
int[] arr = {10, 20, 30, 40, 50};

int[] copy = Arrays.copyOfRange(arr, 1, 4);

System.out.println(Arrays.toString(copy)); // [20, 30, 40]
```

لـ `from` معانا، والـ `to`  مش معانا.

---

# 9. `Arrays.deepToString()`

دي مهمة لو بتتعامل مع **Multidimensional Arrays**، زي الـ 2D Array.

لو عندك:

```java
int[][] matrix = {
    {1, 2, 3},
    {4, 5, 6}
};

System.out.println(Arrays.deepToString(matrix)); // [[1, 2, 3], [4, 5, 6]]
```

لكن لو استخدمت:

```java
System.out.println(Arrays.toString(matrix));
```

مش هتلاقي العناصر الداخلية مطبوعة بالشكل الواضح اللي إنت عايزه.

علشان كده:

- `Arrays.toString()` مناسبة للـ Array العادية.
- `Arrays.deepToString()` مناسبة لعرض العناصر المتداخلة في Multidimensional Arrays.

---
# 10. `Arrays.deepEquals()`

زي `Arrays.equals()`، لكن للمقارنة بين الـ Multidimensional Arrays من حيث المحتويات الداخلية.

```java
int[][] a = {
    {1, 2},
    {3, 4}
};

int[][] b = {
    {1, 2},
    {3, 4}
};

System.out.println(Arrays.deepEquals(a, b)); // true
```

---
# 11. `Arrays.asList()`

```java
String[] names = {"Ahmed", "Ali", "Omar"};

List<String> list = Arrays.asList(names);

System.out.println(list);// [Ahmed, Ali, Omar]
```

**بس فيه تفصيلة مهمة:** الـ List الناتجة حجمها ثابت؛ يعني تقدر تغيّر عناصرها، لكن مش تقدر تضيف أو تحذف عناصر منها.

```java
list.set(0, "Mohamed"); // ينفع

list.add("Mona");       // UnsupportedOperationException
```

ولو عدّلت عنصرًا في الـ List الناتجة، هتلاقي التعديل انعكس على الـ Array الأصلية؛ لأن الـ List دي مرتبطة بنفس الـ Array.

لو عايز `ArrayList` مستقلة وقابلة للإضافة والحذف:

```java
List<String> list = new ArrayList<>(Arrays.asList(names));

list.add("Mona");
```

كده تقدر تضيف وتحذف عناصر عادي.

---

# 12. `Arrays.compare()` و`Arrays.mismatch()`

دول مفيدين لو عايز تقارن بين الـ Arrays بطريقة أدق.

### `Arrays.compare()`

بتقارن الـ Arrays بالترتيب المعجمي للعناصر، وبتحدد أيهما أصغر أو أكبر وفقًا لقواعد المقارنة.

```java
int[] a = {1, 2, 3};
int[] b = {1, 2, 4};

System.out.println(Arrays.compare(a, b));
```

الناتج هنا هيكون رقمًا سالبًا؛ لأن أول اختلاف هو `3` مقابل `4`، و`3` أصغر من `4`.

### `Arrays.mismatch()`

بترجع الـ Index لأول عنصر مختلف بين الـ Arrays، أو `-1` لو مفيش اختلاف.

```java
int[] a = {1, 2, 3, 4};
int[] b = {1, 2, 9, 4};

System.out.println(Arrays.mismatch(a, b)); // 2
```

لأن أول اختلاف موجود عند الـ Index رقم `2`.

---

# 13. مقارنة سريعة بين `Arrays` و`Collections`

|العملية|Array|List|
|---|---|---|
|الترتيب|`Arrays.sort(arr)`|`Collections.sort(list)` أو `list.sort(...)`|
|الطباعة|`Arrays.toString(arr)`|`System.out.println(list)`|
|البحث الثنائي|`Arrays.binarySearch(arr, x)`|`Collections.binarySearch(list, x)`|
|المقارنة|`Arrays.equals(a, b)`|`list1.equals(list2)`|
|ملء العناصر|`Arrays.fill(arr, x)`|`Collections.fill(list, x)`|
|النسخ|`Arrays.copyOf(arr, n)`|`new ArrayList<>(list)` لعمل نسخة من الـ List|
|عكس الترتيب|مفيش `Arrays.reverse()` عامة مباشرة|`Collections.reverse(list)`|

لاحظ إن الـ Array حجمها ثابت بعد إنشائها، أما الـ `ArrayList` فبتقدر تضيف وتحذف منها عناصر.

---
