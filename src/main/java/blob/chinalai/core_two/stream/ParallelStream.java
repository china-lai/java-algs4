package blob.chinalai.core_two.stream;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ParallelStream {
    public static void main(String[] args) throws IOException {
        var contents = new String(
                Files.readAllBytes(
                        Paths.get("src/main/java/blob/chinalai/core_two/stream/ParallelStream.java")
                ),
                StandardCharsets.UTF_8
        );

        var words = List.of( contents.split("\\PL+") );

        var shortWords = new int[10];
        words.parallelStream().forEach(s -> {
            if (s.length() < 10)
                shortWords[s.length()]++;
        });
        System.out.println(Arrays.toString(shortWords));


        // Ingeger -> Long
        System.out.println(
                words.parallelStream().filter(s -> s.length() < 10).collect(
                        Collectors.groupingBy(
                                String::length,
                                Collectors.counting()
                        )
                )
        );

        // 长度 -> List<String>
        System.out.println(
                words.parallelStream().collect(
                        Collectors.groupingByConcurrent(String::length)
                )
        );
    }
}
