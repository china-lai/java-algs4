package org.example.sort;

/**
 * 希尔排序
 */
public class Shell extends SortTemplate{

    @Override
    public void sort(Comparable[] comparables) {
        int N = comparables.length;
        int h = 1;

        while (h < N / 3)
            h = 3 * h + 1;

        while (h >= 1) {
            for (int i=h; i<N; i++) {

                int j = i;
                for (; j >= h && less(comparables[j],comparables[j-h]); j -= h)
                    exch(comparables,j,j-h);
            }
            h = h / 3;
        }
    }

    public static void main(String[] args) {
        Shell shell = new Shell();
        shell.sortTests();

    }
}
