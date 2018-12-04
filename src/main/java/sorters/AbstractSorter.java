package sorters;

import exceptions.EmptyArrayException;

/**
 * Abstract class <b>AbstractSorter</b> presents template for different types of sorts.
 * Contains only one method {@link AbstractSorter#sort(int[])}.
 * @author Dmytro Pylypiuk
 * @version 1.0
 */
abstract public class AbstractSorter {
    /**
     * This abstract method takes only one parameter @param array. It is integer`s array, that should be sorted.
     * This method must be overrided in all classes that extends <b>AbstractSorter</b>
     */
    abstract public void sort(int[] array) throws EmptyArrayException;
}
