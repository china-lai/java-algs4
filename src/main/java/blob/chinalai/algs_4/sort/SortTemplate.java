package blob.chinalai.algs_4.sort;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.Arrays;

/**
 * 算法模板
 */
public abstract class SortTemplate {

    // 数据量设置小一点，以便学习逻辑
//    private static final int N = 1000000; // 百万
    private static final int N = 100000; // 十万
//    private static final int N = 10000; // 一万
//    private static final int N = 100; // 一百
    private static final int[] Ns = new int[] {N,N*10,N*20,N*30,N*40,N*50,N*60,N*70,N*80,N*90,N*100};

    private void sortDefault(int n){

        Stopwatch stopwatch = new Stopwatch();
        Double[] doubles = new Double[n];
        for (int i=0; i<n; i++) {
            doubles[i] = StdRandom.uniform();
        }
        double time = stopwatch.elapsedTime();
        StdOut.println("生成: " + n + ", 级数据, 共花费: " + time + "s");

        // Arrays.sort(doubles); // 测试有序的数组
        
        stopwatch = new Stopwatch();
        StdOut.println("start: " + n);
        sort(doubles);
        time = stopwatch.elapsedTime();
        StdOut.println("end: " + time + "s");

        try {
            if (!isSorted(doubles)) {
                throw new Exception("数组没有排序成功");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    private void sortDefault(){

        Stopwatch stopwatch = new Stopwatch();
        Double[] doubles = new Double[N];
        for (int i=0; i<N; i++) {
            doubles[i] = StdRandom.uniform();
        }
        double time = stopwatch.elapsedTime();
        StdOut.println("生成: " + N + ", 级数据, 共花费: " + time + "s");

         Arrays.sort(doubles); // 测试有序的数组

        stopwatch = new Stopwatch();
        StdOut.println("start: " + N);
        sort(doubles);
        time = stopwatch.elapsedTime();
        StdOut.println("end: " + time + "s");
    }
    public void sortTest(){

        StdOut.println("算法: " + super.getClass().getName());
        sortDefault();
    }

    public void sortTests(){

        StdOut.println("算法: " + super.getClass().getName());

        for (int j=0; j<Ns.length; j++) {
            int n = Ns[j];
            sortDefault(n);
            StdOut.println();

        }
    }
    // 排序
    public abstract void sort(Comparable[] comparables);

    // 比较
    public static boolean less(Comparable c1, Comparable c2) {
        return c1.compareTo(c2) < 0;
    }

    // 调换位置
    public static void exch(Comparable[] comparables, int i, int j) {
        Comparable temp = comparables[i];
        comparables[i] = comparables[j];
        comparables[j] = temp;
    }

    // 显示值
    public static void show(Comparable[] comparables) {
        for (int i=0; i<comparables.length; i++)
            StdOut.print(comparables[i] + " ");
        StdOut.println();
    }

    // 判断是否排序
    public static boolean isSorted(Comparable[] comparables) {
        for (int i = 1; i < comparables.length; i++) {
            if (less(comparables[i],comparables[i - 1]))
                return false;
        }
        return true;
    }
}
