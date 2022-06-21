package blob.chinalai.algs_4.sort;


/**
 * 归并排序
 * 自顶向上
 */
public class MergeBU extends SortTemplate{

    private static Comparable[] aux; // 归并所需的辅助数组


    @Override
    public void sort(Comparable[] comparables) {
        int N = comparables.length;
        aux = new Comparable[N];

        for (int sz = 1; sz < N; sz *= 2) {
            for (int lo = 0 ; lo < N - sz; lo += sz * 2 ) {
                MergeBU.merge(comparables,lo,lo + sz - 1, Math.min(lo + sz + sz - 1,N - 1));
            }
        }
    }

    public static void merge(Comparable[] cs, int lo, int mid, int hi) {
        int i = lo,
            j = mid + 1;

        for (int k = lo; k <= hi; k++) {
            aux[k] = cs[k];
        }
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
        MergeBU mergeBU = new MergeBU();
        mergeBU.sortTests();
    }
}
