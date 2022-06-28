package blob.chinalai.core_two.stream;


import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

/**
 * 原生类型流
 */
public class PrimitiveTypeStreams {

    public static void main(String[] args) {

        System.out.println(
                IntStream.generate(() -> (int)(Math.random() * 100)).limit(100).mapToObj(Integer::toString).collect(
                        Collectors.joining(", ")
                )
        );

        System.out.println(
                DoubleStream.generate(Math::random).limit(100).mapToObj(Double::toString).collect(
                        Collectors.joining(", ")
                )
        );
    }
}
