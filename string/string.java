public class string {
    public static void main(String[] args) {
        // Ways to create string:

        // 1- Literal:
        // to make java more efficient
        // because no new objects are created if it exists already in the java constant pool

        String s1 = "Ahmed";
        String s2 = "Ahmed";

        //  Stack                   Heap
        // -------             --------------------------
        // | s1  |- - - - - - -|-> -------------------  |
        // | s2  |- - - - - - -|-> |      "Ahmed"    |- | - ->  java constant pool
        // -------             |   -------------------  |
        //  The same object    -------------------------|


        // == compare if two object is the same

        if (s1 == s2) {
            System.out.println("Yes s1 and s2 are the same object in java constant pool");
        }
        else{
            System.out.println("no");
        }

        String s3 = "Ah" + "med";
        if (s1 == s3) {
            System.out.println("Yes s1 ,s2 , and s3 are the same object in java constant pool");
        }
        else{
            System.out.println("no");
        }

        // 2- Using new keyword (Heap Memory):
        // each time will create a new object in the heap

        String s4 = new String("Ahmed");
        String s5 = new String("Ahmed");

        if (s4 == s5) {
            System.out.println("Yes s4 and s5 are the same object in java constant pool");
        }
        else{
            System.out.println("No s4 and s5 are not the same object in Heap");
        }

        // .equals compare if two objects have the same value

        if(s4.equals(s5)){
            System.out.println("Yes s4 and s5 have the same value");
        }
        else{
            System.out.println("No s4 and s5 have different values");
        }


        // String immutable that's mean in each time you modified the string a new object is created in heap

        String a = "ahmed";

        a.concat(" hussein");

        System.out.println(a); // ahmed

        String b = a.concat(" hussein");
        System.out.println(a); // ahmed
        System.out.println(b); // ahmed hussein

        // CharSequence interface that's string implement it

        CharSequence c1 = new String("CharSequence object");

        System.out.println(c1);

    }
}