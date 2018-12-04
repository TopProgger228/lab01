package sorters;

import exceptions.EmptyArrayException;

/**
 * <b>QuickSort</b> class that extends {@link sorters.AbstractSorter} class.
 * This class presents quicksort (Hoare algorithm) realization.
 * Class is declared as final.
 * Class annotated by {@link SortAnnotation} annotation.
 *
 * @author Dmytro Pylypiuk
 * @version 1.0
 */
@SortAnnotation(nameOfSort = "Quick sort")
public final class QuickSort extends AbstractSorter {
    /**
     * Overrided version of {@link sorters.AbstractSorter#sort(int[])} method.
     * This method sorts array using quicksort.
     * Takes @param array, array of integers, which user want to sort.
     * Invokes auxiliary method {@link sorters.QuickSort#doSort(int[], int, int)}.
     */
    @Override
    public void sort(int[] array) throws EmptyArrayException {
        if (array.length > 0){
            int startIndex = 0;
            int endIndex = array.length - 1;
            doSort(array, startIndex, endIndex);
        }else {
            throw new EmptyArrayException();
        }
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
