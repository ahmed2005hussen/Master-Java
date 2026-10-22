void main() {

    System.out.println("Sequenced Collection");

    SequencedCollection<String> s = new ArrayList<>(List.of("Ahmed", "Hussein", "Saleh"));

    System.out.println(s.getFirst()); // Ahmed
    System.out.println(s.getLast()); // Saleh

    s.addFirst("Hello First");
    s.addLast("Hello Last");
    System.out.println(s.removeFirst()); // Hello First
    System.out.println(s.removeLast()); // Hello Last

    s.reversed();

    System.out.println(s); // [Ahmed, Hussein, Saleh]

    SequencedCollection b = s.reversed();

    System.out.println(b); // [Saleh, Hussein, Ahmed]

    SequencedCollection<String> a = new ArrayList<>();

    // a.getFirst(); // NoSuchElementException
    // a.removeFirst(); // NoSuchElementException


    List<Integer> numbers = new ArrayList<>(
            List.of(10, 20, 30, 40, 50)
    );

    List<Integer> reversed = numbers.reversed();

    reversed.set(0, 99);

    System.out.println(numbers); // [10, 20, 30, 40, 99]
    System.out.println(reversed);//[99, 40, 30, 20, 10]

// ------------
    System.out.println("-------------------------------------");
    System.out.println("Sequenced Set");


    SequencedSet<Integer> i = new LinkedHashSet<>(Set.of(4, 2, 1, 3));

    System.out.println(i);

    System.out.println(i.getFirst());
    System.out.println(i.getLast());
    System.out.println(i.reversed());
    System.out.println(i);

    System.out.println(i.removeFirst());
    System.out.println(i.removeLast());
    i.reversed().addLast(12);
    System.out.println(i);

    SequencedSet<Integer> j = new LinkedHashSet<>();
    // System.out.println(j.getFirst()); // NoSuchElementException
    // System.out.println(j.removeFirst());// NoSuchElementException

    // ------------
    System.out.println("-------------------------------------");
    System.out.println("Sequenced Map");

    SequencedMap<Integer ,String> users = new LinkedHashMap<>();

    users.put(1, "Ahmed");
    users.put(2, "Mohamed");
    users.put(3, "Ali");

    System.out.println(users.firstEntry()); // 1=Ahmed
    System.out.println(users.lastEntry()); // 3=Ali

    System.out.println(users.pollFirstEntry()); // 1=Ahmed
    System.out.println(users.pollLastEntry()); // 3=Ali

    users.putFirst(4,"hi"); //
    users.putLast(5,"bey");

    System.out.println(users); // {4=hi, 2=Mohamed, 5=bey}
    System.out.println(users.reversed()); // {5=bey, 2=Mohamed, 4=hi}

    System.out.println(users.sequencedKeySet()); // [4, 2, 5]
    System.out.println(users.sequencedValues()); //[hi, Mohamed, bey]
    System.out.println(users.sequencedEntrySet());// [4=hi, 2=Mohamed, 5=bey]


}