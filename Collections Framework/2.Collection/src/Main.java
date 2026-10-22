
void print(Collection c) {
    c.forEach(System.out::println);
    System.out.println("-----------------");
}

void main() {

    Collection<String> c = new ArrayList<>();
    System.out.println(c.add("Ahmed")); // true
    c.add("Hussein");
    c.add("Mohammed");
    print(c);

    System.out.println(c.remove("Hussein")); // true
    System.out.println(c.remove("Hussein")); // false
    print(c);

    c.add("Hussein");
    c.add("Hussein");
    c.add("Hussein");
    c.add("Hussein");
    System.out.println(c.remove("Hussein")); // true
    print(c);

    ArrayList<Integer> a = new ArrayList<>();
    a.add(1);
    a.add(2);
    a.add(3);
    a.remove(1); // index so will delete 2
    a.remove(Integer.valueOf(1)); // object 1 (the number itself) so will delete 1
    print(a);
    System.out.println(c.size());// print size ;

    System.out.println("--------------------");

    Collection<String> names = new ArrayList<>();
    System.out.println(names.isEmpty()); // true
    names.add("Ahmed");
    System.out.println(names.isEmpty()); // false
    System.out.println(names.contains("Ahmed")); // true
    System.out.println(names.contains("Omar"));  // false
    names.clear();
    System.out.println(names);
    System.out.println("------------------");

    User user1 = new User("Ahmed");
    Collection<User> cu = new ArrayList<>();
    cu.add(user1);
    User userSearch = new User("Ahmed");
    System.out.println(cu.contains(userSearch)); // true (we update the .equals())
    // if you remove .equals will print false

    System.out.println("-----------------------");

    Collection<Integer> aa = new ArrayList<>(
            List.of(1, 2, 3, 4)
    );

    Collection<Integer> bb = new ArrayList<>(
            List.of(3, 4, 5, 6)
    );
    aa.addAll(bb);
    System.out.println(aa); // [1, 2, 3, 4, 3, 4, 5, 6]

    System.out.println("--------------");

    Collection<Integer> a1 = new ArrayList<>(
            List.of(1, 2, 3, 4)
    );
    Collection<Integer> b = new ArrayList<>(List.of(2, 3));
    System.out.println(a1.containsAll(b)); // true
    b.add(123);
    System.out.println(a1.containsAll(b)); // false

    System.out.println("----------------------");

    Collection<Integer> a2 = new ArrayList<>(
            List.of(1, 2, 3, 4)
    );
    Collection<Integer> b1 = new ArrayList<>(List.of(2, 3));
    a1.removeAll(b1);
    System.out.println(a1); // [1,4]

    System.out.println("-------------------------");

    Collection<Integer> a21 = new ArrayList<>(
            List.of(1, 2, 3, 4)
    );
    Collection<Integer> b11 = new ArrayList<>(List.of(2, 3 , 5));
    a21.retainAll(b11);
    System.out.println(a21); // [2,3]
    a21.retainAll(new ArrayList<>());
    System.out.println(a21); // []

    System.out.println("-----------------------");

    Collection<Integer> numbers = new ArrayList<>(
            List.of(1, 2, 3, 4, 5, 6)
    );
    System.out.println(numbers.removeIf(number -> number % 2 == 0)); // true
    System.out.println(numbers);

    Collection<String> names1 = new ArrayList<>(
            List.of("Ahmed", "Ali", "Mohamed")
    );

    String[] arr = names1.toArray(new String[0]);
    String[] arr1 = names1.toArray(String[]::new);
    System.out.println(arr);
    System.out.println(arr1);

}