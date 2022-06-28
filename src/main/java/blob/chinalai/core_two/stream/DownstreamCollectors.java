package blob.chinalai.core_two.stream;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

/**
 * 下游收集器
 */
public class DownstreamCollectors {

    public static class City {
        private String name;
        private String state;
        private int population;

        public City(String name, String state, int population) {
            this.name = name;
            this.state = state;
            this.population = population;
        }

        public String getName() {
            return name;
        }

        public String getState() {
            return state;
        }

        public int getPopulation() {
            return population;
        }
    }

    public static Stream<City> readCities() {
        return Stream.of(
                new City("广州","GD",500000),
                new City("Test","GD",100),
                new City("深圳","GD",600000),
                new City("揭阳","GD",1000),
                new City("北京","BJ",900000),
                new City("上海","SH",600000),
                new City("武汉","HB",10000)
        );
    }

    public static void main(String[] args) {

        // 打印 城市名
        System.out.println(
                Stream.of(Locale.getAvailableLocales()).limit(10).map(Locale::getCountry).collect(
                        Collectors.joining(", ")
                )
        );

        // 默认toString 打印 简称
        System.out.println(
                Stream.of(Locale.getAvailableLocales()).limit(10).map(Locale::toString).collect(
                        Collectors.joining(", ")
                )
        );

        // 下游收集器 收集 Set
        System.out.println(
                Stream.of(Locale.getAvailableLocales())
                        .collect(
                                groupingBy(Locale::getCountry, Collectors.toSet())
                        )
        );

        // 下游收集器 收集 总数
        System.out.println(
                Stream.of(Locale.getAvailableLocales())
                        .collect(
                                groupingBy(
                                        Locale::getCountry, Collectors.counting()
                                )
                        )
        );

        // 根据 state 进行分组，然后 summingInt 算总和
        System.out.println(
                readCities().collect(
                        groupingBy(
                                City::getState, summingInt( City::getPopulation )
                        )
                )
        );

        // 下游收集器 收集 状态 对 城市名 最长的 Optional
        System.out.println(
                readCities().collect(
                        groupingBy(
                                City::getState,
                                mapping(
                                        City::getName,
                                        maxBy(Comparator.comparing( String::length ) )
                                )
                        )
                )
        );


    }

}
