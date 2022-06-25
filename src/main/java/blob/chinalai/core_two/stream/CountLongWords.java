package blob.chinalai.core_two.stream;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * 长单词计数器
 */
public class CountLongWords {

    public static void main(String[] args) throws IOException {
        var contents = new String(
                Files.readAllBytes(
                        Paths.get("src/main/resources/static/data/hello_world.txt")
                ),
                StandardCharsets.UTF_8
        );

        var world = List.of( contents.split("\\PL+") );

        var count = 0L;
        // 统计长单词数量
        // 第一种方案是我怎么做，遍历？
        for (var word: world) {
            if (word.length() > 12)
                count++;
        }
        System.out.println("default count: " + count);

        // 第二种 流式
        // 我要长单词，我需要做什么。只需要判断下单词长度即可
        count = world.stream().filter( w -> w.length() > 12 ).count();
        System.out.println("stream count: " + count);
    }

}
