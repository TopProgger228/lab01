package sorters;

/**
 * <b>BubbleSortClassic</b> class that implements {@link sorters.Sort} interface.
 * This class presents classic bubble sort realization.
 * Class is declared as final.
 * @author Dmytro Pylypyuk.
 * @version 1.0
 */
public final class BubbleSortClassic implements Sort {
    /**
     * Overrided version of {@link sorters.Sort#sort(int[])} method.
     * This method sorts array using bubble sort algorithm.
     * Takes @param array, array of integers, which user want to sort.
     */
    @Override
    public void sort(int[] array) {
        for (int i = 0; i < (array.length - 1); i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] > array[j]) {
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
        return "Classic bubble sort";
    }
}
