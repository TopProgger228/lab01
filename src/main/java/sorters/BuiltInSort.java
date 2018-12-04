package sorters;

import exceptions.EmptyArrayException;

import java.util.Arrays;

/**
 * <b>BuiltInSort</b> class that extends {@link sorters.AbstractSorter} class.
 * This class presents {@link AbstractSorter#sort(int[])} method realization that invokes built-in sort method.
 * Class is declared as final.
 *
 * @author Dmytro Pylypiuk.
 * @version 1.0
 */
@SortAnnotation(nameOfSort = "Built-In sort")
public final class BuiltInSort extends AbstractSorter {
    /**
     * Overrided version of {@link sorters.AbstractSorter#sort(int[])} method.
     * This method invokes {@link java.util.Arrays#sort(int[])} method from {@link java.util.Arrays} class.
     * Takes @param array, array of integers, which user want to sort.
     */
    @Override
    public void sort(int[] array) throws EmptyArrayException{
        if (array.length > 0){
            Arrays.sort(array);
        }else {
            throw new EmptyArrayException();
        }
    }
}
