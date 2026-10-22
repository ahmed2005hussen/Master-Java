import java.util.List;


void main() {

    List<String> s = List.of("ahmed" , "Hussein" , "Ahmed");
    s.forEach(System.out::println);
    System.out.println("-----------------");
    s.forEach(c -> {
        c = c.toUpperCase() ;
        System.out.println(c);
    });

    System.out.println("-----------------------");
    List<String> a = new ArrayList<>();
    a.add("a");
    a.add("b");
    a.add("c");
    Iterator<String> it = a.iterator();

    System.out.println(it.next()); // a
    System.out.println(it.hasNext()); // true
    it.remove(); // remove a
    it.next(); // we at b
    it.next(); // we at c
    it.remove();// remove c
    System.out.println(it.hasNext());// false
    // it.next(); // exception
    a.forEach(System.out::println); // b only

    System.out.println("-----------------------");
    List<String> b = new ArrayList<>();
    b.add("a");
    b.add("b");
    b.add("c");
    b.add("d");
    b.add("e");
    b.add("f");

// uncomment the one do you want to run

//        for(int i = 0 ; i < b.size(); i++){
//            if(i == 1){
//                b.remove("b");
//                continue;
//            }
//            System.out.println(b.get(i));
//        }



//        int size = b.size();
//        for(int i = 0 ; i < size; i++){
//            if(i == 1){
//                b.remove("b");
//                continue;
//            }
//            System.out.println(b.get(i));
//        }


//
//
//        for(String c : b){
//            b.remove("a");
//        }
//
    Iterator<String> it1 = b.iterator();

    while(it1.hasNext()){

        String c = it1.next();
        if(c == "a"){
            it1.remove();
        }
    }


}
