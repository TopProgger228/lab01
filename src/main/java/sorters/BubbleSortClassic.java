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
            for (int i = getIterForOuterLoop(array.length); checkConditionForOuterLoop(i, array.length);
                 i = geNextIteration(i)) {
                for (int j = getIterForNestedLoop(i); checkConditionForNestedLoop(j, array.length);
                     j = geNextIteration(j)) {
                    swap(array, array[i], i, array[j], j);
                }
            }
        } else {
            throw new EmptyArrayException();
        }
    }

    @Override
    int geNextIteration(int iter) {
        return ++iter;
    }

    @Override
    int getIterForOuterLoop(int arraySize) {
        return 0;
    }

    @Override
    int getIterForNestedLoop(int i) {
        return i + 1;
    }

    @Override
    boolean checkConditionForOuterLoop(int i, int arraySize) {
        if (i < (arraySize - 1)) {
            return true;
        } else return false;
    }

    @Override
    boolean checkConditionForNestedLoop(int i, int arraySize) {
        if (i < arraySize) {
            return true;
        } else return false;
    }
}
