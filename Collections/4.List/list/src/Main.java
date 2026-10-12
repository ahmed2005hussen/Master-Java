void main() {

    List<String> a = new ArrayList<>(
            List.of("Ahmed", "Hussein", "Saleh")
    );

    System.out.println(a.get(0)); // "Ahmed"
    System.out.println(a.set(0, "AAHHMMEEDD")); // Ahmed (old one)
    a.add(2, "hello 1");
    a.add("end of the list");
    System.out.println(a); // [ AAHHMMEEDD, Hussein, hello 1 , Saleh , end of the list ]

    System.out.println(a.remove(0)); // AAHHMMEEDD
    //System.out.println(a.remove(12)); // IndexOutOfBoundsException
    //System.out.println(a.remove(-1)); // IndexOutOfBoundsException
    System.out.println(a.remove("AAHHMMEEDD")); // false
    System.out.println(a.remove("Hussein")); // true


    List<Integer> numbers = new ArrayList<>(
            List.of(4, 2, 1, 3)
    );

    System.out.println(numbers.remove(1)); // index -> 2
    System.out.println(numbers); // [4, 1, 3]

    // System.out.println(numbers.remove(3)); // IndexOutOfBoundsException
    System.out.println(numbers.remove(Integer.valueOf(3))); // number -> 3 (true)
    System.out.println(numbers); // [4, 1]


    List<String> b = new ArrayList<>(
            List.of("Ahmed", "Hussein", "Saleh", "Hussein")
    );

    System.out.println(b.indexOf("Hussein")); // 1
    System.out.println(b.indexOf("a")); // -1

    System.out.println(b.lastIndexOf("Hussein")); // 3
    System.out.println(b.lastIndexOf("a")); // -1


    System.out.println("-----------------------");
    ListIterator<String> listIterator = b.listIterator();

    // nothing will happen
    while (listIterator.hasPrevious()) {
        System.out.println(listIterator.previous());
    }

    listIterator = b.listIterator(b.size());

    while (listIterator.hasPrevious()) {
        System.out.println(listIterator.previous());
    }

    // now we are in the beginning before the first one

    // Iterator "Ahmed" "Hussein" "Saleh" "Hussein"

    System.out.println(listIterator.nextIndex()); // 0

    // added exactly at the iterator,
    // then move iterator to between the added one and the next one
    listIterator.add("hello index 0 ");

    // "hello index 0"  Iterator "Ahmed" "Hussein" "Saleh" "Hussein"

    System.out.println(listIterator.nextIndex()); // 1
    System.out.println(listIterator.previousIndex()); // 0

    listIterator.add("hello index 1 "); // added exactly at the iterator then move
    // "hello index 0" "hello index 1" Iterator "Ahmed" "Hussein" "Saleh" "Hussein"

    System.out.println(listIterator.nextIndex()); // 2
    System.out.println(listIterator.previousIndex()); // 1

    System.out.println(b); //[hello index 0 , hello index 1 , Ahmed, Hussein, Saleh, Hussein]

    System.out.println(listIterator.next()); // "Ahmed"
    // "hello index 0" "hello index 1"  "Ahmed" Iterator "Hussein" "Saleh" "Hussein"


    System.out.println(b);
    //"hello index 0" "hello index 1"  "Ahmed" "Hussein" "Saleh" "Hussein"

    System.out.println(listIterator.previous()); // "Ahmed"
    // "hello index 0" "hello index 1" Iterator  "Ahmed"  "Hussein" "Saleh" "Hussein"

    listIterator.add("hello index 2");
    // "hello index 0" "hello index 1" "hello index 2"  Iterator  "Ahmed"  "Hussein" "Saleh" "Hussein"

    listIterator.add("hello index 3");
    // "hello index 0" "hello index 1" "hello index 2" "hello index 3"  Iterator  "Ahmed"  "Hussein" "Saleh" "Hussein"

    // listIterator.set("hiiii"); // error ( must be call after .next or .previous)

    System.out.println(listIterator.next());// "Ahmed"
    // "hello index 0" "hello index 1" "hello index 2" "hello index 3" "Ahmed" Iterator  "Hussein" "Saleh" "Hussein"

    listIterator.set("hiiii");
    // "hello index 0" "hello index 1" "hello index 2" "hello index 3" "hiiii" Iterator  "Hussein" "Saleh" "Hussein"

    System.out.println(listIterator.nextIndex()); // 5
    System.out.println(listIterator.previousIndex()); // 4


    listIterator.set("df"); // on the last next which is "hiiii"
    // "hello index 0" "hello index 1" "hello index 2" "hello index 3" "df" Iterator  "Hussein" "Saleh" "Hussein"

    System.out.println(b);

    System.out.println("-----------------------");


    numbers = new ArrayList<>(
            List.of(10, 20, 30, 40, 50)
    );

    List<Integer> part = numbers.subList(1, 4);
    part.set(0, 99);

    System.out.println(numbers); //[10, 99, 30, 40, 50]
    System.out.println(part); // [99, 30, 40]

    numbers = new ArrayList<>(
            List.of(10, 20, 30, 40, 50)
    );

    List<Integer> indPart = new ArrayList<>(numbers.subList(1, 4));
    indPart.set(0, 99);

    System.out.println(numbers); //[10, 20, 30, 40, 50]
    System.out.println(indPart); // [99, 30, 40]

    System.out.println("--------------------");

    List<String> names = List.of("Ahmed", "Ali", "Omar");
//    names.add("adf"); // UnsupportedOperationException,ImmutableCollections.java:159


    List<String> names1 = List.copyOf(names);
    // names1.add("hi"); // UnsupportedOperationException,ImmutableCollections.java:159


}