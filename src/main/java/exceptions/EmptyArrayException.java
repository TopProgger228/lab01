package exceptions;

/**
 * <b>EmptyArrayException</b> throws when realizations of {@link sorters.AbstractSorter#sort(int[])} method
 * <br>take empty array.</br>
 *
 * @author Dmytro Pylypiuk
 * @version 1.0
 */
public class EmptyArrayException extends Exception {
    /**
     * Method prints message about exception when it was caught.
     */
    @Override
    public void printStackTrace() {
        System.out.println("Empty array can not be sorted!");
    }
}
