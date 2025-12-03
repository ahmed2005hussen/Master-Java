import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class string {
    public static void main(String[] args) {

        // ===================================================================
        // PART 1: Ways to Create Strings
        // ===================================================================

        System.out.println("========== String Creation ==========\n");

        // Method 1: String Literal (Java Constant Pool)
        // More efficient - no new objects created if it already exists
        String s1 = "Ahmed";
        String s2 = "Ahmed";

        //  Stack                   Heap
        // -------             --------------------------
        // | s1  |- - - - - - -|-> -------------------  |
        // | s2  |- - - - - - -|-> |      "Ahmed"    |- | - ->  Java Constant Pool
        // -------             |   -------------------  |
        //  The same object    -------------------------|

        // Both s1 and s2 point to the same object in the String Pool
        if (s1 == s2) {
            System.out.println("✓ s1 and s2 are the same object in Java constant pool");
        }

        String s3 = "Ah" + "med";
        if (s1 == s3) {
            System.out.println("✓ s1, s2, and s3 are the same object in Java constant pool");
        }

        // Method 2: Using new keyword (Heap Memory)
        // Each time creates a new object in the heap
        String s4 = new String("Ahmed");
        String s5 = new String("Ahmed");

        if (s4 != s5) {
            System.out.println("✓ s4 and s5 are NOT the same object in Heap");
        }

        // .equals() compares values, not object references
        if (s4.equals(s5)) {
            System.out.println("✓ s4 and s5 have the same value\n");
        }

        // ===================================================================
        // PART 2: String Immutability
        // ===================================================================

        System.out.println("========== String Immutability ==========\n");

        String a = "ahmed";
        a.concat(" hussein");
        System.out.println("After concat (not assigned): " + a); // ahmed

        String b = a.concat(" hussein");
        System.out.println("Original string a: " + a); // ahmed
        System.out.println("New string b: " + b + "\n"); // ahmed hussein

        // CharSequence Interface (String implements it)
        CharSequence c1 = new String("CharSequence object");
        System.out.println("CharSequence: " + c1 + "\n");

        // ===================================================================
        // PART 3: String API Methods
        // ===================================================================

        System.out.println("========== String API Methods ==========\n");

        String str1 = "Ahmed";

        // --- toCharArray() & charAt() ---
        System.out.println("--- toCharArray() & charAt() ---");
        char[] arrayOfChar1 = str1.toCharArray();
        System.out.println("toCharArray()[0]: " + arrayOfChar1[0]); // A
        System.out.println("charAt(0): " + str1.charAt(0) + "\n"); // A

        // --- codePointAt() ---
        System.out.println("--- codePointAt() ---");
        System.out.println("codePointAt(0): " + str1.codePointAt(0)); // 65
        System.out.println("Equivalent (int)charAt(0): " + (int) str1.charAt(0) + "\n");

        // --- codePointBefore() ---
        System.out.println("--- codePointBefore() ---");
        System.out.println("codePointBefore(1): " + str1.codePointBefore(1) + "\n"); // 65 (A)

        // --- codePointCount() ---
        System.out.println("--- codePointCount() ---");
        System.out.println("codePointCount(0, 1): " + str1.codePointCount(0, 1));
        String emoji = "😊";
        System.out.println("Emoji length: " + emoji.length()); // 2 (stored as 2 chars)
        System.out.println("Emoji codePointCount: " + emoji.codePointCount(0, 2) + "\n"); // 1 (one actual character)

        // --- compareTo() ---
        System.out.println("--- compareTo() ---");
        str1 = "car";
        String str2 = "car";
        System.out.println("\"car\" compareTo \"car\": " + str1.compareTo(str2)); // 0

        str1 = "A";
        str2 = "B";
        System.out.println("\"A\" compareTo \"B\": " + str1.compareTo(str2)); // -1

        str1 = "B";
        str2 = "A";
        System.out.println("\"B\" compareTo \"A\": " + str1.compareTo(str2) + "\n"); // 1

        // --- compareToIgnoreCase() ---
        System.out.println("--- compareToIgnoreCase() ---");
        str1 = "a";
        str2 = "A";
        System.out.println("\"a\" compareToIgnoreCase \"A\": " + str1.compareToIgnoreCase(str2) + "\n"); // 0

        // --- concat() ---
        System.out.println("--- concat() ---");
        String concatenate = str2.concat(" hussein");
        System.out.println("Concatenated: " + concatenate + "\n");

        // --- contains() ---
        System.out.println("--- contains() ---");
        System.out.println("\"Ahmed\".contains(\"hm\"): " + "Ahmed".contains("hm")); // true
        System.out.println("\"Ahmed\".contains(\"a\"): " + "Ahmed".contains("a")); // false
        System.out.println("\"Ahmed\".contains(\"Ahe\"): " + "Ahmed".contains("Ahe")); // false
        System.out.println("\"Ahmed\".contains(\"A\"): " + "Ahmed".contains("A") + "\n"); // true

        // --- contentEquals() ---
        System.out.println("--- contentEquals() ---");
        System.out.println("\"Ahmed\".contentEquals(\"hm\"): " + "Ahmed".contentEquals("hm")); // false
        System.out.println("\"Ahmed\".contentEquals(\"Ahmed\"): " + "Ahmed".contentEquals("Ahmed") + "\n"); // true

        // contentEquals with StringBuffer
        s1 = "hello";
        StringBuffer sb = new StringBuffer("hello");
        System.out.println("String contentEquals StringBuffer: " + s1.contentEquals(sb)); // true
        System.out.println("String equals StringBuffer: " + s1.equals(sb) + "\n"); // false (different types)

        // --- copyValueOf() ---
        System.out.println("--- copyValueOf() ---");
        String copy = s1.copyValueOf(str2.toCharArray());
        System.out.println("copyValueOf: " + copy);

        s1 = "Ahmed";
        copy = s1.copyValueOf(s1.toCharArray(), 1, 3);
        System.out.println("copyValueOf(array, 1, 3): " + copy + "\n"); // hme

        // --- endsWith() ---
        System.out.println("--- endsWith() ---");
        s1 = "Ahmed";
        System.out.println("\"Ahmed\".endsWith(\"d\"): " + s1.endsWith("d")); // true
        System.out.println("\"Ahmed\".endsWith(\"ed\"): " + s1.endsWith("ed")); // true
        System.out.println("\"Ahmed\".endsWith(\"ad\"): " + s1.endsWith("ad") + "\n"); // false

        // --- equals() vs == ---
        System.out.println("--- equals() vs == ---");
        str1 = new String("ahmed");
        str2 = "ahmed";
        System.out.println("equals(): " + str1.equals(str2)); // true
        System.out.println("== : " + (str1 == str2) + "\n"); // false

        // --- equalsIgnoreCase() ---
        System.out.println("--- equalsIgnoreCase() ---");
        str1 = "ahmed";
        str2 = "Ahmed";
        System.out.println("\"ahmed\".equalsIgnoreCase(\"Ahmed\"): " + str1.equalsIgnoreCase(str2) + "\n"); // true

        // --- format() ---
        System.out.println("--- format() ---");
        String s = String.format(Locale.US, "Value: %.2f", 1234.56);
        System.out.println("US format: " + s);

        s = String.format(Locale.GERMAN, "Value: %.2f", 1234.56);
        System.out.println("German format: " + s);

        s = String.format(Locale.US, "Price: %,d$", 1000000);
        System.out.println("US currency: " + s);

        s = String.format(Locale.FRANCE, "Price: %,d €", 1000000);
        System.out.println("France currency: " + s);

        s = String.format("Name: %s, Age: %d", "Ahmed", 20);
        System.out.println("Custom format: " + s + "\n");

        // --- getBytes() ---
        System.out.println("--- getBytes() ---");
        s = "ABC";
        byte[] b1 = s.getBytes();
        System.out.println("Bytes of \"ABC\": " + Arrays.toString(b1) + "\n"); // [65, 66, 67]

        // --- getChars() ---
        System.out.println("--- getChars() ---");
        s = "ahm";
        char[] dest = new char[5];
        dest[3] = 'e';
        dest[4] = 'd';
        s.getChars(0, 3, dest, 0);
        System.out.println("getChars result: " + new String(dest) + "\n");

        // --- hashCode() ---
        System.out.println("--- hashCode() ---");
        s1 = "ahmed";
        System.out.println("hashCode of \"ahmed\": " + s1.hashCode() + "\n");

        // --- indexOf() ---
        System.out.println("--- indexOf() ---");
        System.out.println("indexOf('m'): " + s1.indexOf('m'));
        System.out.println("indexOf(\"hme\"): " + s1.indexOf("hme"));

        s1 = "AhmedA";
        System.out.println("indexOf(65, 1): " + s1.indexOf(65, 1)); // 5
        System.out.println("indexOf('A', 3): " + s1.indexOf('A', 3) + "\n");

        // --- intern() ---
        System.out.println("--- intern() ---");
        s1 = new String("ahmed"); // in Heap
        s2 = s1.intern(); // canonical copy in String Pool
        s3 = "ahmed"; // already in Pool
        System.out.println("s2 == s3: " + (s2 == s3)); // true
        System.out.println("s1 == s3: " + (s1 == s3) + "\n"); // false

        // --- isEmpty() ---
        System.out.println("--- isEmpty() ---");
        str1 = "ahmed";
        System.out.println("\"ahmed\".isEmpty(): " + str1.isEmpty()); // false
        str1 = "";
        System.out.println("\"\".isEmpty(): " + str1.isEmpty() + "\n"); // true

        // --- join() ---
        System.out.println("--- join() ---");
        String[] words = {"ahmed", "hussein", "ali"};
        String result = String.join(", ", words);
        System.out.println("join with array: " + result);

        List<String> names = List.of("ahmed", "hussein", "ali");
        String res2 = String.join(" - ", names);
        System.out.println("join with List: " + res2 + "\n");

        // --- lastIndexOf() ---
        System.out.println("--- lastIndexOf() ---");
        str1 = "AhmedA";
        System.out.println("lastIndexOf(65): " + str1.lastIndexOf(65)); // 5
        System.out.println("indexOf(65): " + str1.indexOf(65)); // 0
        System.out.println("lastIndexOf(65, 2): " + str1.lastIndexOf(65, 2));
        System.out.println("lastIndexOf(\"d\"): " + str1.lastIndexOf("d"));
        System.out.println("lastIndexOf(\"d\", 5): " + str1.lastIndexOf("d", 5) + "\n");

        // --- length() ---
        System.out.println("--- length() ---");
        str1 = "AhmedA";
        System.out.println("length: " + str1.length() + "\n");

        // --- replace() ---
        System.out.println("--- replace() ---");
        System.out.println("replace('A', 'a'): " + str1.replace('A', 'a') + "\n");

        // --- startsWith() ---
        System.out.println("--- startsWith() ---");
        str1 = "AhmedA";
        System.out.println("startsWith(\"Ah\"): " + str1.startsWith("Ah")); // true
        System.out.println("startsWith(\"ah\"): " + str1.startsWith("ah")); // false
        System.out.println("startsWith(\"Ah\", 0): " + str1.startsWith("Ah", 0)); // true
        System.out.println("startsWith(\"Ah\", 1): " + str1.startsWith("Ah", 1) + "\n"); // false

        // --- subSequence() ---
        System.out.println("--- subSequence() ---");
        str1 = "AhmedA";
        System.out.println("subSequence(0, 2): " + str1.subSequence(0, 2) + "\n");

        // --- substring() ---
        System.out.println("--- substring() ---");
        str1 = "AhmedA";
        System.out.println("substring(2): " + str1.substring(2));
        System.out.println("substring(0, 2): " + str1.substring(0, 2) + "\n");

        // --- toLowerCase() ---
        System.out.println("--- toLowerCase() ---");
        str1 = "AhmedA";
        System.out.println("toLowerCase: " + str1.toLowerCase() + "\n");

        // --- toString() ---
        System.out.println("--- toString() ---");
        str1 = "AhmedA";
        System.out.println("toString: " + str1.toString() + "\n");

        // --- toUpperCase() ---
        System.out.println("--- toUpperCase() ---");
        str1 = "AhmedA";
        System.out.println("toUpperCase: " + str1.toUpperCase() + "\n");

        // --- trim() ---
        System.out.println("--- trim() ---");
        str1 = "        AhmedA      ";
        System.out.println("Before trim: [" + str1 + "]");
        System.out.println("After trim: [" + str1.trim() + "]");
    }
}