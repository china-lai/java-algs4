package blob.chinalai.core_two.stream;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 创建流
 */
public class CreateStreams {
    public static void main(String[] args) throws IOException {


        // 无规则无限流
        Stream<Double> randoms =  Stream.generate(Math::random); // 创建一个流生成器

        // 我只要100个随机浮点数，且写入到一个文件去
        var file_name = "random_100.txt";
        BufferedWriter out = new BufferedWriter(new FileWriter(file_name));

        // 遍历写入
//        randoms.limit(100).forEach(r -> {
//            try {
//                out.write(String.valueOf(r) + "\n");
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        });

        // 处理完再写入
        var words = randoms.limit(100).map(Object::toString).collect(Collectors.joining("\n"));
        out.write(words);
        out.close();

    }
}
