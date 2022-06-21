package blob.chinalai.algs_4.sort;

/**
 * 三向切分的快速排序
 * 对元素重复较多的清空下性能优化
 */
public class Quick3way extends SortTemplate{

    @Override
    public void sort(Comparable[] comparables) {
        sort(comparables,0,comparables.length-1);
    }
    public void sort(Comparable[] comparables,int lo, int hi) {
        if (hi <= lo)
            return;

        int lt = lo,
            i = lo + 1,
            gt = hi;

        Comparable v = comparables[lo];

        while ( i <= gt) {
            int cmp = comparables[i].compareTo(v);

            if (cmp < 0)
                exch(comparables,lt++,i++);
            else if (cmp > 0)
                exch(comparables,i,gt--);
            else
                i++;
        }

        sort(comparables,lo,lt - 1);
        sort(comparables,gt + 1, hi);
    }

    public static void main(String[] args) {
        Quick3way quick3way = new Quick3way();
        quick3way.sortTests();
    }
}
