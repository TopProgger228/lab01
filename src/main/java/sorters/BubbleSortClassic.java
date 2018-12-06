package sorters;

import annotations.SortAnnotation;
import exceptions.EmptyArrayException;

/**
 * <b>BubbleSortClassic</b> class that extends {@link sorters.AbstractSorter} class.
 * <br>This class presents classic bubble sort realization.</br>
 * <br>Class is declared as final.</br>
 * <br>Class annotated by {@link SortAnnotation} annotation.</br>
 *
 * @author Dmytro Pylypiuk.
 * @version 1.1
 */
@SortAnnotation(nameOfSort = "Classic bubble sort")
public final class BubbleSortClassic extends BubbleSorter {
    /**
     * Overrided version of {@link sorters.AbstractSorter#sort(int[])} method.
     * This method sorts array using bubble sort algorithm.
     *
     * @param array array of integers, which user want to sort.
     */
    @Override
    public void sort(int[] array) throws EmptyArrayException {
        if (array.length > 0) {
            for (int i = 0; i < (array.length - 1); i++) {
                for (int j = i + 1; j < array.length; j++) {
                    swap(array, array[i], i, array[j], j);
                }
            }
        } else {
            throw new EmptyArrayException();
        }
    }

    /**
     * Method swaps elements in array.
     *
     * @param array           reference on array.
     * @param firstElem       value of first element.
     * @param firstElemIndex  index of first element.
     * @param secondElem      value of second element.
     * @param secondElemIndex index of second element.
     * @since 1.1
     */
    @Override
    void swap(int[] array, int firstElem, int firstElemIndex, int secondElem, int secondElemIndex) {
        if (firstElem > secondElem) {
            array[firstElemIndex] = secondElem;
            array[secondElemIndex] = firstElem;
        }
    }
}
