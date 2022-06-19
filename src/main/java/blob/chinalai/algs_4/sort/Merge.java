package blob.chinalai.algs_4.sort;

/**
 * 归并排序
 */
public class Merge extends SortTemplate{
    private static Comparable[] aux; // 归并所需的辅助数组

    @Override
    public void sort(Comparable[] comparables) {
        aux = new Comparable[comparables.length];
        sort(comparables,0,comparables.length-1);
    }

    public void sort(
            Comparable[] comparables,
            int lo,
            int hi
    ) {
        if (hi <= lo) return;
        int mid = lo + (hi - lo) / 2;
        sort(comparables,lo,mid); // 左边排序
        sort(comparables,mid + 1, hi); // 右边排序
        Merge.merge(comparables,lo,mid,hi); // 归并结果
    }

    public static void merge(
            Comparable[] cs,
            int lo,
            int mid,
            int hi
    ) {
        int i = lo;
        int j = mid + 1;

        for (int k = lo; k <= hi; k++)
            aux[k] = cs[k];

        for (int k = lo; k <= hi; k++) {
            if (i > mid)
                cs[k] = aux[j++];
            else if (j > hi)
                cs[k] = aux[i++];
            else if (less(aux[j],aux[i]))
                cs[k] = aux[j++];
            else
                cs[k] = aux[i++];
        }
    }
    public static void main(String[] args) {

        Merge merge = new Merge();
        merge.sortTests();
    }
}
