package exceptions;

public class WrongRepeatAmount extends Exception {
    @Override
    public void printStackTrace() {
        System.out.println("Repeat amount is wrong!");
    }
}
