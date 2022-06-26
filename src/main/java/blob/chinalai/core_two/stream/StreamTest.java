package blob.chinalai.core_two.stream;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTest {
    public static void main(String[] args) throws IOException {

        // 获取当前文件的单词
        var contents = new String(
                Files.readAllBytes(
                        Paths.get("src/main/java/blob/chinalai/core_two/stream/StreamTest.java")
                ),
                StandardCharsets.UTF_8
        );

        var words = List.of( contents.split("\\PL+") );

        // 过滤
        System.out.println( words.stream().filter(w -> w.length() > 12).collect(Collectors.joining(" ")) );

        // 转换
        System.out.println( words.stream().map(String::toUpperCase).collect(Collectors.joining(" ")) );

        // 摊平流，意思是流中流的处理
        System.out.println( words.stream().flatMap( w -> {
            var result = new ArrayList<String>();
            int i = 0;
            while (i < w.length()) {
                int j = w.offsetByCodePoints(i,1);
                result.add( w.substring(i,j) );
                i = j;
            }
            return result.stream();
        })
        // .map( s -> s.collect(Collectors.joining(" "))) // 如果使用 flatMap 他会自己 摊平 返回流，所以不需要这里再继续转换为字符串以供后续流处理
        .collect(Collectors.joining(" ")) );

        // 限制10个流，常用于无限流
        System.out.println( words.stream().limit(10).collect(Collectors.joining(" ")) );

        // 跳过5个，且限制5个，意思是上面流的后面一半数据
        System.out.println( words.stream().skip(5).limit(5).collect(Collectors.joining(" ")) );

        // 满足条件 一直 进入 新流，直到第一个条件不满足，就结束。意思：截止到出现第一个元素非小写 的流
        System.out.println( words.stream().takeWhile(w -> {
            var j = w.codePointAt(0);
            return Character.isLowerCase(j);
        }).collect(Collectors.joining(" ")) );


        // 满足条件 不进入 新流，直到第一个元素不满足，就截止当前元素和后面的所有元素。意思：第一个出现元素非小写到后面 的流
        System.out.println( words.stream().dropWhile(w -> {
            var j = w.codePointAt(0);
            return Character.isLowerCase(j);
        }).collect(Collectors.joining(" ")) );

        // 上述二者 concat 等于原流
        System.out.println(
                Stream.concat(
                        words.stream().takeWhile(w -> {
                            var j = w.codePointAt(0);
                            return Character.isLowerCase(j);
                        }),
                        words.stream().dropWhile(w -> {
                            var j = w.codePointAt(0);
                            return Character.isLowerCase(j);
                        })
                ).collect(Collectors.joining(" "))
        );

        // 去除
        System.out.println(
                words.stream().distinct().collect(Collectors.joining(" "))
        );

        // 排序
        System.out.println(
                words.stream().sorted().collect(Collectors.joining(" "))
        );

        // 惰性
        var lazy_words = words.stream().filter(w -> w.length() > 12).peek(s -> {
            System.out.println("原字符串: " + s);
            // return s.toUpperCase(); // 也可以使用 map，如果只是日志需要，可以使用 peek，相当于加个  action 事件
        });

        System.out.println("上述代码会执行吗???");

        // 只有需要获取结果，流才会被处理
        System.out.println(
                lazy_words.count()
        );

    }


}
