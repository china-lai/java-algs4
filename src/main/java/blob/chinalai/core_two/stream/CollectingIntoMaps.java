package blob.chinalai.core_two.stream;

import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectingIntoMaps {

    public static void main(String[] args) {


        try {
            System.out.println(
                    people().collect(
                            Collectors.toMap(
                                    Person::getId,
                                    // Person::getName,
                                    Function.identity() // 获取本身
                            )
                    ).toString()
            );
        } catch(IllegalStateException e) {
            System.out.println("重复 key");
        }

        // 重复值的处理
        System.out.println(
                people().collect(
                        Collectors.toMap(
                                Person::getId,
                                Function.identity(), // 获取本身
                                (existingValue, newValue) -> {
                                    // throw new IllegalStateException();
                                    return newValue; // 取最新的
                                },
                                TreeMap::new
                        )
                ).toString()
        );

        // 获取地区名=语言 如果地区名一致，取最开始获取的那条
        System.out.println(
                Stream.of(Locale.getAvailableLocales()).collect(
                        Collectors.toMap(
                                Locale::getDisplayLanguage,
                                l -> l.getDisplayLanguage(l),
                                (existingValue, newValue) -> existingValue
                        )
                )
        );


        // 获取地区名=语言 如果地区名一致，Set 存多个
        System.out.println(
                Stream.of(Locale.getAvailableLocales()).collect(
                        Collectors.toMap(
                                Locale::getDisplayLanguage,
                                l -> Set.of(l.getDisplayLanguage(l)),
                                (existsValue, newValue) -> {
                                    Set<String> union = new HashSet<>(existsValue);
                                    union.addAll(newValue);
                                    return union;
                                }
                        )
                )
        );

        // 上述实现，基本上都过于冗余，所以大部分的时候这个特性仅仅只是简单 流 转个 map 即可，而无需过多考虑重复的问题

    }

    public static Stream<Person> people() {
        return Stream.of(
                new Person(1001, "Lai"),
                new Person(1002, "Li"),
                new Person(1003, "Wang"),
                new Person(1001, "Lai Wen Jie")
        );
    }

    public static class Person {
        private final int id;
        private final String name;

        public Person(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
}
