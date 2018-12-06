package sorters;

/**
 * Abstract class <b>BubbleSorter</b> presents template for bubble sort realizations.
 * <br>Class extends another abstract class <b>AbstractSorter</b> </br>
 * <br>and inherit his method {@link AbstractSorter#sort(int[])}</br>
 * <br>Contains only one method {@link BubbleSorter#swap(int[], int, int, int, int)}</br>
 *
 * @author Dmytro Pylypiuk
 * @version 1.1
 */
abstract class BubbleSorter extends AbstractSorter {
    /**
     * This abstract method should be overrided in all classes extends <b>BubbleSorter</b>.
     *
     * @param array           reference on array.
     * @param firstElem       value of first element.
     * @param firstElemIndex  index of first element.
     * @param secondElem      value of second element.
     * @param secondElemIndex index of second element.
     */
    abstract void swap(int[] array, int firstElem, int firstElemIndex, int secondElem, int secondElemIndex);
}
