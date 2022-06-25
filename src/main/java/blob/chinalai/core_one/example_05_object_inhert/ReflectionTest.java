package blob.chinalai.core_one.example_05_object_inhert;

import java.lang.reflect.Modifier;
import java.util.Scanner;

/**
 * 反射学习
 */
public class ReflectionTest {

    public static void main(String[] args) throws ClassNotFoundException {
        var className = "";
        if (args.length > 0) {
            className = args[0];
        }
        else {
            var in = new Scanner(System.in);
            System.out.println("输入 class 名（java.util.Date）: ");
            className = in.next();
        }

        Class c1 = Class.forName(className);
        Class superC1 = c1.getSuperclass(); // 父类
        String modifiers = Modifier.toString( c1.getModifiers() ); // 类修饰符

        // 打印修饰符
        if (modifiers.length() > 0)
            System.out.print(modifiers + " ");

        System.out.print("class " + className);
        if (superC1 != null && superC1 != Object.class)
            System.out.print(" extends " + superC1.getName());

        System.out.print(" {");

        // 现在可以给根据 反射的特性，获取 类 的 构造器、域、方法

        System.out.println();
        System.out.println("\n}");
    }
}
