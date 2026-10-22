
## 1. أولًا: يعني إيه Comparable؟

 هي Interface ، بتسمح للـ Class إنها تحدد **الترتيب.

يعني إنت بتقول إن الـ Objects من النوع ده المفروض تتقارن ببعض بطريقة أساسية محددة.

مثلًا:
- الأرقام بترتب تصاعديًا.
- الـ Strings بتترتب حسب الترتيب المعجمي للحروف.
- الطلاب ممكن نعتبر ترتيبهم الطبيعي حسب الـ ID أو الدرجة، حسب ما إنت تختار.
### إزاي بنستخدمها؟

الـ `Comparable` بتحتوي على Method أساسية اسمها:

```java
int compareTo(T other);
```

 الـ `compareTo()` بتقارن الـ Object الحالي بـ Object تاني.

مثلا: 

```java
class Student implements Comparable<Student> {
    String name;
    int grade;

    Student(String name, int grade) {
        this.name = name;
        this.grade = grade;
    }

    @Override
    public int compareTo(Student other) {
        return Integer.compare(this.grade, other.grade);
    }
}
```

السطر ده : 

```java
return Integer.compare(this.grade, other.grade);
```

معناه إننا بنقارن درجة الطالب الحالي بدرجة الطالب اللي بنقارنه بيه.

- لو درجة الطالب الحالي أقل، النتيجة سالبة.
- لو الدرجتان متساويتين، النتيجة صفر.
- لو درجة الطالب الحالي أكبر، النتيجة موجبة.

إحنا كده حددنا إن الترتيب الطبيعي للطلاب هو حسب الدرجة تصاعديًا.

افترض إن عندنا:

```java
Student s1 = new Student("Ahmed", 85);
Student s2 = new Student("Ali", 95);
```

ولما نكتب:

```java
s1.compareTo(s2);
```

إحنا بنقارن درجة أحمد بدرجة علي:

```java
Integer.compare(85, 95);
```

الناتج هيكون سالب؛ لأن `85 < 95`.

أما:

```java
s2.compareTo(s1);
```

فالناتج هيكون موجب؛ لأن `95 > 85`.
اذا : 

| نتيجة `compareTo()` | معناها                                     |
| ------------------- | ------------------------------------------ |
| قيمة سالبة          | الـ Object الحالي أصغر من الآخر في الترتيب |
| `0`                 | الاتنين متساويين بالنسبة لترتيب المقارنة   |
| قيمة موجبة          | الـ Object الحالي أكبر من الآخر في الترتيب |

مش لازم ترجع `-1` أو `0` أو `1` بالتحديد؛ أي قيمة سالبة أو موجبة بتعبّر عن نفس المعنى.

---
## 3. إيه فايدة Comparable ؟

بعد ما خلينا `Student` تنفذ `Comparable`، نقدر نستخدمها مع الـ Classes اللي بتعتمد على الـ Natural Ordering.

```java
import java.util.*;

class Student implements Comparable<Student> {
    String name;
    int grade;

    Student(String name, int grade) {
        this.name = name;
        this.grade = grade;
    }

    @Override
    public int compareTo(Student other) {
        return Integer.compare(this.grade, other.grade);
    }

    @Override
    public String toString() {
        return name + " - " + grade;
    }
}

public class Main {
    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();

        students.add(new Student("Ahmed", 85));
        students.add(new Student("Ali", 95));
        students.add(new Student("Omar", 75));

        Collections.sort(students);

        System.out.println(students);
    }
}
```

الناتج:

```java
[Omar - 75, Ahmed - 85, Ali - 95]
```

إزاي `Collections.sort(students)` عرفت ترتب الطلاب حسب الدرجة؟

لأننا خلينا `Student` تنفذ `Comparable`، وحددنا قاعدة الترتيب في `compareTo()`.

---
# 4. طيب إيه هو Comparator؟
الـ `Comparator` هي Interface بتسمح لك تحدد **قاعدة مقارنة خارج الـ Class نفسها**.

وهنا الفرق الأساسي بينها وبين `Comparable`.

مع `Comparable`، إنت بتحدد الترتيب الطبيعي داخل الـ Class.

أما مع `Comparator`، فتقدر تحدد ترتيبًا مختلفًا من خارج الـ Class، من غير ما تغيّر كودها.

```java
@Override
public int compareTo(Student other) {
    return Integer.compare(this.grade, other.grade);
}
```

 ماذا لو عايزين نرتب الطلاب حسب الاسم بدل الدرجة؟
أو حسب الدرجة تنازليًا؟
هل نغيّر `compareTo()` كل مرة؟

لأ، لأننا ممكن نحتاج أكتر من ترتيب لنفس الـ Class.
هنا بنستخدم `Comparator`.
## 5. مثال على Comparator

```java
import java.util.*;

class Student {
    String name;
    int grade;

    Student(String name, int grade) {
        this.name = name;
        this.grade = grade;
    }

    @Override
    public String toString() {
        return name + " - " + grade;
    }
}

public class Main {
    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();

        students.add(new Student("Ahmed", 85));
        students.add(new Student("Ali", 95));
        students.add(new Student("Omar", 75));

        Comparator<Student> byGrade =
                (s1, s2) -> Integer.compare(s1.grade, s2.grade);

        students.sort(byGrade);

        System.out.println(students);
    }
}
```

الناتج:

```java
[Omar - 75, Ahmed - 85, Ali - 95]
```

ركز هنا:

```java
Comparator<Student> byGrade =
        (s1, s2) -> Integer.compare(s1.grade, s2.grade);
```

إحنا أنشأنا Comparator بتحدد إزاي نقارن بين طالبين.

الـ `s1` و`s2` هما الطالبان اللي بنقارن بينهما، والـ `Integer.compare()` بتحدد نتيجة المقارنة حسب الدرجات.

ولأن `Comparator` هي Functional Interface، نقدر نستخدم معها Lambda Expression بالشكل ده.

وممكن نكتبها بالطريقة التقليدية باستخدام Anonymous Class:

```java
Comparator<Student> byGrade = new Comparator<Student>() {
    @Override
    public int compare(Student s1, Student s2) {
        return Integer.compare(s1.grade, s2.grade);
    }
};
```

الاتنين بيحققوا نفس الغرض، لكن الـ Lambda أقصر وأسهل في القراءة في الحالة دي.

---
# 6. إزاي أعمل أكتر من Comparator لنفس الـ Class؟

إحنا عندنا `Student`، وممكن نحتاج نرتب الطلاب بأكتر من طريقة:
1. حسب الدرجة تصاعديًا.
2. حسب الدرجة تنازليًا.
3. حسب الاسم أبجديًا.
### أولًا: الترتيب حسب الدرجة تصاعديًا

```java
Comparator<Student> byGradeAscending =
        (s1, s2) -> Integer.compare(s1.grade, s2.grade);
```

### ثانيًا: الترتيب حسب الدرجة تنازليًا

```java
Comparator<Student> byGradeDescending =
        (s1, s2) -> Integer.compare(s2.grade, s1.grade);
```

لاحظ إننا عكسنا ترتيب المتغيرات:

```java
Integer.compare(s2.grade, s1.grade)
```

وده بيخلّي الطالب صاحب الدرجة الأكبر يظهر أولًا.

### ثالثًا: الترتيب حسب الاسم

```java
Comparator<Student> byName =
        (s1, s2) -> s1.name.compareTo(s2.name);
```

هنا استخدمنا `String.compareTo()`؛ لأن الـ `String` نفسها بتنفذ `Comparable<String>`.

---

# 7.  ال  comparing() و thenComparing()

بدل ما تكتب:

```java
Comparator<Student> byGrade =
        (s1, s2) -> Integer.compare(s1.grade, s2.grade);
```

تقدر تكتب:

```java
Comparator<Student> byGrade =
        Comparator.comparingInt(s -> s.grade);
```

نفس الفكرة، لكن بطريقة أوضح وأقصر.

## طيب لو عايز الترتيب حسب الدرجة، ولو الدرجات متساوية حسب الاسم؟

هنا بنستخدم `thenComparing()`.

```java
Comparator<Student> comparator =
        Comparator.comparingInt((Student s) -> s.grade)
                  .thenComparing(s -> s.name);
```

معنى الكود:

1. قارن الطلاب حسب الدرجة.
2. لو الدرجات متساوية، قارنهم حسب الاسم.

### الترتيب التنازلي حسب الدرجة

```java
Comparator<Student> comparator =
        Comparator.comparingInt((Student s) -> s.grade)
                  .reversed();
```

### الترتيب التنازلي حسب الدرجة، ثم تصاعديًا حسب الاسم

```java
Comparator<Student> comparator =
        Comparator.comparingInt((Student s) -> s.grade)
                  .reversed()
                  .thenComparing(s -> s.name);
```

النتيجة:

- الدرجة الأعلى أولًا.
- لو الدرجات متساوية، الاسم الأبجدي أولًا.

---

# 8. الفرق بين Comparable وComparator

خلينا نلخص الفرق الأساسي.

|وجه المقارنة|Comparable|Comparator|
|---|---|---|
|الغرض|تحديد الترتيب الطبيعي للـ Class|تحديد قاعدة مقارنة|
|الـ Package|`java.lang`|`java.util`|
|الـ Method الأساسية|`compareTo(T other)`|`compare(T o1, T o2)`|
|مكان تحديد المقارنة|داخل الـ Class نفسها|خارج الـ Class|
|عدد طرق الترتيب|عادةً ترتيب طبيعي واحد|تقدر تعمل Comparators متعددة|
|هل تحتاج تعديل الـ Class؟|نعم، علشان تضيف `implements Comparable`|لا|
|الاستخدام|Natural Ordering|Custom Ordering|


---

# 9. طيب إيه علاقتهم بـ TreeSet وTreeMap؟

## أولًا: Comparable مع TreeSet

خلينا نفترض إن عندنا:

```java
TreeSet<Integer> numbers = new TreeSet<>();

numbers.add(30);
numbers.add(10);
numbers.add(20);

System.out.println(numbers);
```

الناتج:

```
[10, 20, 30]
```

ليه؟

لأن `Integer` بتنفذ `Comparable<Integer>`، والـ `TreeSet` بتستخدم الـ Natural Ordering افتراضيًا.
يعني هي عارفة إزاي تقارن الأرقام ببعضها.

ونفس الفكرة مع `String`؛ لأن الـ `String` عندها Natural Ordering.
## ثانيًا: Comparator مع TreeSet

لو عايز ترتيبًا تنازليًا:

```java
TreeSet<Integer> numbers =
        new TreeSet<>(Comparator.reverseOrder());

numbers.add(30);
numbers.add(10);
numbers.add(20);

System.out.println(numbers);
```

الناتج:

```java
[30, 20, 10]
```

هنا الـ `TreeSet` استخدمت الـ Comparator اللي إحنا مررناه للـ Constructor بدل الترتيب الطبيعي الافتراضي.

### طيب لو عندي Student؟

```java
TreeSet<Student> students =
        new TreeSet<>(
                Comparator.comparingInt((Student s) -> s.grade)
        );
```

كده الـ `TreeSet` هترتب الطلاب حسب الدرجة.

**لكن خد بالك من نقطة مهمة جدًا:**

الـ `TreeSet` بتستخدم نتيجة المقارنة لتحديد إذا كان العنصر الجديد يعتبر موجودًا بالفعل أم لا.

لو الـ Comparator رجّعت `0` لطالبين، الـ `TreeSet` هتعتبرهما متساويين من ناحية الترتيب، ومش هتضيف الطالب الثاني كعنصر مستقل.

مثلًا، لو عندك طالبين درجاتهم `85`، والـ Comparator بتقارن الدرجات فقط، فممكن الطالب الثاني ما يتضافش رغم اختلاف اسميهما.

لو عايز تحتفظ بالطالبين، تقدر تضيف الاسم كمعيار ثانوي:

```
Comparator<Student> comparator =
        Comparator.comparingInt((Student s) -> s.grade)
                  .thenComparing(s -> s.name);
```

وبكده لو الدرجات متساوية، المقارنة تنتقل للاسم.

الأفضل عمومًا إن ترتيب المقارنة يكون متوافقًا مع `equals()` لتجنب السلوك غير المتوقع مع الـ Sorted Sets والـ Sorted Maps.

---
# شوية Methods مهمة في Comparator

| Method                         | وظيفتها                                   |
| ------------------------------ | ----------------------------------------- |
| `Comparator.comparing()`       | المقارنة بناءً على قيمة مستخرجة من العنصر |
| `Comparator.comparingInt()`    | المقارنة بناءً على قيمة `int`             |
| `Comparator.comparingDouble()` | المقارنة بناءً على قيمة `double`          |
| `Comparator.comparingLong()`   | المقارنة بناءً على قيمة `long`            |
| `thenComparing()`              | إضافة معيار مقارنة ثانوي                  |
| `reversed()`                   | عكس ترتيب المقارنة                        |
| `naturalOrder()`               | استخدام الترتيب الطبيعي                   |
| `reverseOrder()`               | استخدام الترتيب الطبيعي بصورة معكوسة      |
| `nullsFirst()`                 | وضع `null` أولًا                          |
| `nullsLast()`                  | وضع `null` أخيرًا                         |

مثال على المقارنة حسب الاسم:

```java
Comparator<Student> byName =
        Comparator.comparing(s -> s.name);
```

--- 
## `Comparator.reverseOrder()`

دي **static method** موجودة في `Comparator`، بترجع Comparator بيعكس الـ Natural Ordering.

مثال:

```java
List<Integer> numbers = new ArrayList<>(
    List.of(5, 2, 8, 1, 3)
);

numbers.sort(Comparator.reverseOrder());

System.out.println(numbers);
```

الناتج:

```
[8, 5, 3, 2, 1]
```

الأرقام بدل ما تترتب تصاعديًا، اترتبت تنازليًا.

---
##  `comparator.reversed()`

دي **instance method** موجودة في الـ `Comparator` نفسه، بتاخد Comparator موجود بالفعل وترجع Comparator بيعكس ترتيبه.

مثال:

```java
Comparator<Integer> ascending = Comparator.naturalOrder();

Comparator<Integer> descending = ascending.reversed();

List<Integer> numbers = new ArrayList<>(
    List.of(5, 2, 8, 1, 3)
);

numbers.sort(descending);

System.out.println(numbers);
```

الناتج:

```
[8, 5, 3, 2, 1]
```

هنا بدأنا بـ Comparator بيرتب تصاعديًا، وبعد كده عكسنا ترتيبه باستخدام `reversed()`.

---
## 3. الفرق الأساسي بينهم

|المقارنة|`Comparator.reverseOrder()`|`comparator.reversed()`|
|---|---|---|
|النوع|Static method|Instance method|
|بتستدعيها على|`Comparator`|Comparator موجود|
|الوظيفة|إنشاء Comparator بترتيب طبيعي معكوس|عكس ترتيب Comparator موجود|
|الاستخدام|`Comparator.reverseOrder()`|`myComparator.reversed()`|

---
و بس كده :)