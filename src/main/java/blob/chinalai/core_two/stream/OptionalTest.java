package blob.chinalai.core_two.stream;


import java.io.IOException;
import java.nio.channels.IllegalChannelGroupException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class OptionalTest {
    public static void main(String[] args) throws IOException {

        // 简单约简
        // 包装器对象、要么包装了类型T的对象，要么没有包装对象(null)

        // 获取当前文件的单词
        var contents = new String(
                Files.readAllBytes(
                        Paths.get("src/main/java/blob/chinalai/core_two/stream/OptionalTest.java")
                ),
                StandardCharsets.UTF_8
        );

        var words = List.of( contents.split("\\PL+") );

        // 第一个元素
        System.out.println(
                // 无论写什么，基本都是有的，比较是读当前文件
                // 也可以使用 \"\"java 这样子实际是查 ""java 所以就找不到输出 无
                words.stream().filter(s -> s.contains("java")).findFirst().orElse("无")
        );

        // 空值代替值
        System.out.println(
                Optional.empty().orElse("无")
        );

        // 计算
        System.out.println(
                Optional.empty().orElseGet(() -> "random: " + Math.random())
        );


        // 没有则抛出错误，也可以自定义错误
        try {
            Optional.empty().orElseThrow( );
        } catch (Exception e) {
            System.out.println("NoSuchElementException: " + e.getMessage());
        }

        // flatMap 可以直接处理数据，因为 Optional 为空的时候，flatMap 会跳过，所以有值的话执行逻辑并返回一个无 Optional 包裹的值
        // 可以改用 map，你会发现 Optional 的类型一直在包裹
        System.out.println(
                Optional.of(4.0).flatMap(OptionalTest::inverse)
        );

        //
        System.out.println(
                inverse(4.0).flatMap(OptionalTest::squareRoot)
        );

        // 一种计算 总和、大小值、平均值
        var wordLengthSummary = words.stream().distinct().collect(
                Collectors.summarizingInt(String::length)
        );
        System.out.println(
                wordLengthSummary.getMin()
                + " "
                + wordLengthSummary.getMax()
                + " "
                + wordLengthSummary.getAverage()
                + " "
                + wordLengthSummary.getCount()
        );


    }

    public static Optional<Double> inverse(Double x) {
     return x == 0 ? Optional.empty() : Optional.of( 1 / x);
    }

    public static Optional<Double> squareRoot(Double x) {
        return x < 0 ? Optional.empty() : Optional.of(Math.sqrt(x));
    }
}
