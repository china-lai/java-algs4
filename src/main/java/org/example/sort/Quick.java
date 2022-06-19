package org.example.sort;

public class Quick extends SortTemplate{

    @Override
    public void sort(Comparable[] comparables) {
        // StdRandom.shuffle(comparables); // 打乱顺序
        sort(comparables,0,comparables.length-1);
    }
    public void sort(Comparable[] comparables,int lo, int hi) {

        if (hi <= lo) return;
        int j = partition(comparables,lo,hi);
        sort(comparables,lo, j-1);
        sort(comparables,j+1, hi);
    }

    public int partition(Comparable[] comparables,int lo,int hi) {
        int i = lo;
        int j = hi + 1;

        Comparable v = comparables[lo];
        while (true) {

            while (less(comparables[++i],v))
                if (i == hi)
                    break;

            while (less(v,comparables[--j]))
                if (j == lo)
                    break;

            if (i >= j)
                break;

            exch(comparables,i,j);
        }
        exch(comparables,lo,j);
        return j;
    }

    public static void main(String[] args) {
        Quick quick = new Quick();
        quick.sortTests();
    }
}
