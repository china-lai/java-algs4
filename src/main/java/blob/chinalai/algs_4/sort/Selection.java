package blob.chinalai.algs_4.sort;

/**
 * 选择排序
 */
public class Selection extends SortTemplate {

    public void sort(Comparable[] comparables) {
        int N = comparables.length;
        for (int i = 0; i < N; i++) {
            int min = i;

            for (int j = i + 1; j < N; j++)
                if (less(comparables[j], comparables[min]))
                    min = j;

            exch(comparables, i, min);
        }
    }

    public static void main(String[] args) {
        Selection selection = new Selection();
        selection.sortTests();
    }
}
