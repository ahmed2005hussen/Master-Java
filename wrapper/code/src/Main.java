void main() {
    // Boxing

    Integer num = Integer.valueOf(1);
    Integer num1 = Integer.valueOf("2");
    System.out.println(num + num1);// 3
    System.out.println("-----------------");

    Integer n = 1;
    int n1 = 2;
    // Integer n1 = "2"; // wrong
    System.out.println(n + n1); // 3
    System.out.println("-----------------");

    // unboxing
    int pn = num.intValue();
    int pn1 = num;

    System.out.println(pn + pn1);
    num = null;
//    pn = num; // NullPointerException
    System.out.println("------------------");

    // parsing

    String s = "123";
    int parse = Integer.parseInt(s);
    System.out.println(parse + 1);

    System.out.println("----------------------------");

    Integer a = Integer.valueOf(123);
    Integer b = Integer.valueOf(123);
    System.out.println(a==b);

    a = 128;
    b = 128;
    System.out.println(a==b);

}