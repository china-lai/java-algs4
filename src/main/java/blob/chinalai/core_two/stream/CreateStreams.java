package blob.chinalai.core_two.stream;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * 创建流
 */
public class CreateStreams {
    public static void main(String[] args) throws IOException {

        // 简单生成流
        Stream<String> emptyStream = Stream.empty();

        // 数组的方式
        Stream<String> helloWorldStream = Stream.of("hello","world");

        // 如果值不为null，则生成流长度一个，否则 空流
        Stream<String> ofNullStream1 = Stream.ofNullable(null);
        Stream<String> ofNullStream2 = Stream.ofNullable("T");

        // 无规则无限流
        Stream<Double> randoms =  Stream.generate(Math::random); // 创建一个流生成器

        // 我只要100个随机浮点数，且写入到一个文件去
        var randomFileName = "random_100.txt";
        final BufferedWriter randomOut = new BufferedWriter(new FileWriter(randomFileName));

        // 遍历写入
        if (1 == 2) {
            randoms.limit(100).forEach(r -> {
                try {
                    randomOut.write(String.valueOf(r) + "\n");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }
        // 处理完再写入
        else {
            var words = randoms.limit(100).map(Object::toString).collect(Collectors.joining("\n"));
            randomOut.write(words);
            randomOut.close();
        }

        // 另外一种，生成无限流，但是有一定的规律
        var oddFile = "odd_100.txt";
        final BufferedWriter oddOut = new BufferedWriter(new FileWriter(oddFile));
        var oddRandoms = Stream.iterate(
                BigInteger.ZERO,
                n -> n.add(BigInteger.TWO)
        );

        // 三个形参，第二个形参变成类似无限流限流，意思是生成数要小于 202。如果是个无法满足条件的限制流，似乎会导致流无数据
        // var oddRandoms = Stream.iterate(
        //         BigInteger.ZERO,
        //         n -> n.compareTo(BigInteger.valueOf(202)) < 0,
        //         n -> n.add(BigInteger.TWO)
        // );

        var oddWords = oddRandoms
                .limit(100)
                .map(Object::toString).collect(Collectors.joining("\n"));
        oddOut.write(oddWords);
        oddOut.close();


        // 更多的时候，都是使用一些工具库去转换
        // 遍历根目录
        Stream<Path> rootDirStream = StreamSupport.stream(
                FileSystems.getDefault().getRootDirectories().spliterator(),
                false
        );
        rootDirStream.forEach(s -> {
            System.out.println(s.toString());
        });

    }
}
