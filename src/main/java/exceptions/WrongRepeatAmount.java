package exceptions;

/**
 * <b>WrongRepeatAmount</b> exception throws when {@link analyzer.SortAnalyzer#repeatAnalyze(int)} method
 * <br>takes amount equals zero or less than zero.</br>
 *
 * @author Dmytro Pylypiuk
 * @version 1.0
 */
public class WrongRepeatAmount extends Exception {
    /**
     * Method prints message about exception when it was caught.
     */
    @Override
    public void printStackTrace() {
        System.out.println("Repeat amount is wrong!");
    }
}
