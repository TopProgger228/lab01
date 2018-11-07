package sorters;

/**
 * <b>BubbleSortReversed</b> class that implements {@link sorters.Sort} interface.
 * This class presents bubble sort realization when current element goes down.
 * Class is declared as final.
 *
 * @author Dmytro Pylypyuk.
 * @version 1.0
 */
public final class BubbleSortReversed implements Sort {
    /**
     * Overrided version of {@link sorters.Sort#sort(int[])} method.
     * This method sorts array using bubble sort algorithm, but current element goes down.
     * Takes @param array, array of integers, which user want to sort.
     */
    @Override
    public void sort(int[] array) {
        for (int i = array.length - 1; i > 0; i--) {
            for (int j = i - 1; j >= 0; j--) {
                if (array[i] < array[j]) {
                    int var = array[i];
                    array[i] = array[j];
                    array[j] = var;
                }
            }
        }
    }

    /**
     * @return name of sort.
     */
    @Override
    public String getSortName() {
        return "Reversed bubble sort";
    }
}
