package org.example.sort;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * 算法比较
 */
public class SortCompare {

    public static double time(String name, Double[] doubles) {
        Stopwatch timer = new Stopwatch();

        if (name.equals(Insertion.class.getName())) new Insertion().sort(doubles);
        if (name.equals(Selection.class.getName())) new Selection().sort(doubles);
        if (name.equals(Shell.class.getName())) new Shell().sort(doubles);

        if (name.equals(Merge.class.getName())) new Merge().sort(doubles);
        if (name.equals(MergeBU.class.getName())) new MergeBU().sort(doubles);
        if (name.equals(Quick.class.getName())) new Quick().sort(doubles);
        return timer.elapsedTime();
    }

    public static double timeRandomInput(String name, int N, int T) {
        double total = 0.0;
        Double[] doubles = new Double[N];
        for (int t=0; t<T; t++) {
            for (int i=0; i<N; i++) {
                doubles[i] = StdRandom.uniform();
            }
            // // 如果有序，部分排序算法的效率基本为0
            // Arrays.sort(doubles);
            total += time(name,doubles);
        }
        // StdOut.println("total: " + total);
        return total;
    }


    public static void performance(String[] names){
        final int N = 1000000;
        final int T = 10;

        StdOut.printf("次数: %s, doubles.length: %s\n",T,N);

        for (String name: names) {
            double t = timeRandomInput(name,N,T);
            StdOut.printf("%s %.2f\n",name,t);
            // StdOut.printf("%s %.2f:1 %s",name1,t1/t2,name2);
        }
    }

    public static void main(String[] args) {
        performance(new String[]{

                // 这些排序性能都不高、希尔勉强可以工作
                // Selection.class.getName(),
                // Insertion.class.getName(),
                // Shell.class.getName(),

                Merge.class.getName(),
                MergeBU.class.getName(),
                Quick.class.getName(),
        });
    }
}
