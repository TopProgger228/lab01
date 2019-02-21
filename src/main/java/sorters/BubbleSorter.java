package sorters;

import exceptions.EmptyArrayException;

/**
 * Abstract class <b>BubbleSorter</b> presents template for bubble sort realizations.
 * <br>Class extends another abstract class <b>AbstractSorter</b> </br>
 * <br>and inherit his method {@link AbstractSorter#sort(int[])}</br>
 * <br>Contains only one method {@link BubbleSorter#swap(int[], int, int, int, int)}</br>
 *
 * @author Dmytro Pylypiuk
 * @version 1.1
 */
abstract public class BubbleSorter extends AbstractSorter {
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

    /**
     * Method swaps two elements in array.
     *
     * @param array           reference on array.
     * @param firstElem       value of first element.
     * @param firstElemIndex  index of first element.
     * @param secondElem      value of second element.
     * @param secondElemIndex index of second element.
     */
    void swap(int[] array, int firstElem, int firstElemIndex, int secondElem, int secondElemIndex) {
        if (firstElem > secondElem) {
            array[firstElemIndex] = secondElem;
            array[secondElemIndex] = firstElem;
        }
    }

    abstract int geNextIteration(int iter);

    abstract int getIterForOuterLoop(int arraySize);

    abstract int getIterForNestedLoop(int i);

    abstract boolean checkConditionForOuterLoop(int i, int arraySize);

    abstract boolean checkConditionForNestedLoop(int i, int arraySize);
}
