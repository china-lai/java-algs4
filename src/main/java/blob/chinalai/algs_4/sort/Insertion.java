package blob.chinalai.algs_4.sort;

/**
 * 插入排序
 */
public class Insertion extends SortTemplate{

    @Override
    public void sort(Comparable[] comparables) {
        int N = comparables.length;
        for (int i=1; i<N; i++) {
            for (int j=i; j > 0 && less(comparables[j],comparables[j-1]); j--) {
                exch(comparables,j,j-1);
            }
        }

    }

    public static void main(String[] args) {
        Insertion insertion = new Insertion();
        insertion.sortTests();
    }
}
