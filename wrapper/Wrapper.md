في جافا عندنا primitive data type و عندنا ال Wrapper classes ليهم : 

|Primitive Type|Wrapper Class|
|---|---|
|`byte`|`Byte`|
|`short`|`Short`|
|`int`|`Integer`|
|`long`|`Long`|
|`float`|`Float`|
|`double`|`Double`|
|`char`|`Character`|
|`boolean`|`Boolean`|
- اهميه ال wrapper انها بتدينا شويه function جاهزه تخلينا نتعامل معاهم بسرعه 
- ال Collection مش بيستقبل غير Object يعني مينفعش معاه primitive لازم ال Wrapper 
---
جوا كل class من ال wrappers هنلاقي الكثير من ال functions بتختلف طبعا بأختلاف ال data type و العمليات اللي ممكن تتعمل عليها , احنا هنا هنمسك بعض المفاهيم المهمه و اللي هتفيدنا مع ال collection 

## 1. Boxing 
ال boxing معناه اننا نحول primitive الي wrapper و عندنا كذا طريقه لده : 
1. valueOf()
مثال: 
```java
	Integer num = Integer.valueOf(1);
    Integer num1 = Integer.valueOf("2");
    System.out.println(num + num1); // 3
    System.out.println("-----------------"); 
```

- ال valueOf هي static فا نقدر نستعملها من اسم ال class عالطول
- نفس ال approach ده نقدر نعمله مع كل ال wrapper 
- ممكن تستقبل number as literal و هتقدر تحول لي Integer عادي 

2. auto boxing 
مثال: 
```java 
    Integer n = 1;
    int n1 = 2;
    // Integer n1 = "2"; // wrong
    System.out.println(n + n1); // 3
```

- هنا الفكره بقا اننا نقدر نساوي عالطول ال primitive بال Wrapper و هو هيعرف يحوله عادي
- هنا مش هنقدر نساويه بي String و هيبقي لازم في الحاله دي نستعمل valueOf
---
## Unboxing 
دي العمليه العكسيه من ال boxing فكرتها ان عندنا wrapper عايزين نرجعه الي ال primitive تاني.
1. intValue
مثال: 
```java
  Integer num = 123; 
  int pn = num.intValue(); 
```

- هنلاقي برضوا لكل wrapper الفانكشن دي بمختلف المسميات (doubleValue , longValue)
2.auto unboxing 
مثال:

```java
  Integer num = 123; 
  int pn = num;
  num = null ;
  pn = num; // error null pointer exception  
```

- نفس فكره ال auto boxing هيتعمله unboxing لوحده 
- خلي بالك من ال null 
---
## parse 
فكره ال Parse ان لو جالنا int بس في  هيئه String ازاي احوله لي int كا primitive, مثال: 

```java
	String s = "123";
    int parse = Integer.parseInt(s);
    System.out.println(parse + 1); // 124
```

- نفس الفكره هنلاقي الفانكشن دي لكل ال wrapper  فا (parseDouble , parseLong)
---
## caching in wrapper 

ال wrapper numerical classes بتقدر تعمل cache للارقام من -128 الي 127 , مثال: 
 
```java
  Integer a = Integer.valueOf(123);
    Integer b = Integer.valueOf(123);
    System.out.println(a==b); // true 

    a = 128;
    b = 128;
    System.out.println(a==b); // false 
```

- الاولي كانت true علشان جوا ال limit لل cache 
- التانيه false علشان ال 128 مش معانا في ال range 
---
 ال wrapper لسه فيها الكثير و الكثير من ال functions بس هنكتفي بده :)