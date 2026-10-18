-  الـ **`Vector` شبه الـ `ArrayList`
-  ال vector  بيكون **Synchronization** يعني لو حد بيستعمله فا هو بي lock اي عمليه تاني لحد ما اللي بيستعمله يخلص, علشان يحافظ علي ال consistency بتاعت ال data و ميحصلش race condition او حاجه. 
- ال vector مبقاش مستعمل اوي 
- بنستعمله لو عندنا case اننا محتاجين نتعامل مع اكتر من thread. 
- ال time complexity هو هو ال ArrayList , و لكن ال Vector هيكون في تكلفه زياده بتاعت ال Synchronization
- ال Capacity الأولية بتكون **10** لو محطناش Capacity في ال constructor  ، بعكس `new ArrayList<>()` اللي ممكن يبدأ فاضي عادي.

```java
Vector<Integer> numbers = new Vector<>();
```

- ال vector بعد كده لما بيتملي بيضرب ال capacity في 2 علي عكس ال arrayList 
- بالتالي نقدر نقول ان ال Vector بياخد memory اكتر و ممكن منحتجش اصلا كل ده 
---
- ده بيبقا شكل ال methods بتكون synchronized

```java
   public synchronized boolean add(E e) {
        modCount++;
        add(e, elementData, elementCount);
        return true;
    }

```

---
اخيرا زي ماقولنا ال vector مش مستعمل كتير و مش بستعمله الا في حالات قليله اويي, بسسس ادينا عرفناه

و شكرا :) 