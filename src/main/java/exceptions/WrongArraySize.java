package exceptions;

/**
 * <b>WrongArraySize</b> exception throws when {@link fillers.Fillers} methods
 * <br>take array size equals zero or less than zero.</br>
 *
 * @author Dmytro Pylypiuk
 * @version 1.0
 */
public class WrongArraySize extends Exception {
    /**
     * Method prints message about exception when it was caught.
     */
    @Override
    public void printStackTrace() {
        System.out.println("Wrong array size!");
    }
}
