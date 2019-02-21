package sorters;

import exceptions.EmptyArrayException;

/**
 * Abstract class <b>AbstractSorter</b> presents template for different types of sorts.
 * Contains only one method {@link AbstractSorter#sort(int[])}.
 *
 * @author Dmytro Pylypiuk
 * @version 1.0
 */
abstract public class AbstractSorter {
    /**
     * This abstract method should be overrided in all classes extends <b>AbstractSorter</b>.
     *
     * @param array array that will be sorted.
     * @throws EmptyArrayException if method takes empty array.
     */
    abstract public void sort(int[] array) throws EmptyArrayException;
}
