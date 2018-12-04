package sorters;

/**
 * <b>QuickSort</b> class that implements {@link sorters.Sort} interface.
 * This class presents quicksort (Hoare algorithm) realization.
 * Class is declared as final.
 *
 * @author Dmytro Pylypyuk.
 * @version 1.0
 */
public final class QuickSort implements Sort {
    /**
     * Overrided version of {@link sorters.Sort#sort(int[])} method.
     * This method sorts array using quicksort.
     * Takes @param array, array of integers, which user want to sort.
     * Invokes auxiliary method {@link sorters.QuickSort#doSort(int[], int, int)}.
     */

    public void sort(int[] array) {
        int startIndex = 0;
        int endIndex = array.length - 1;
        doSort(array, startIndex, endIndex);
    }

    /**
     * @return name of sort.
     */

    public String getSortName() {
        return "Quick sort";
    }

    /**
     * Auxiliary method for {@link sorters.QuickSort#sort(int[])} method.
     * Contains logic of quicksort algorithm.
     * Takes @param array, array of integers, which user want to sort.
     * Parameter @param start is first index of array.
     * Parameter @param end is last index of array.
     */
    private static void doSort(int[] array, int start, int end) {
        if (start >= end)
            return;
        int i = start, j = end;
        int cur = i - (i - j) / 2;
        while (i < j) {
            while (i < cur && (array[i] <= array[cur])) {
                i++;
            }
            while (j > cur && (array[cur] <= array[j])) {
                j--;
            }
            if (i < j) {
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                if (i == cur)
                    cur = j;
                else if (j == cur)
                    cur = i;
            }
        }
        doSort(array, start, cur);
        doSort(array, cur + 1, end);
    }
}
