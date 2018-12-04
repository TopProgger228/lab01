package exceptions;

public class EmptyArrayException extends Exception {
    @Override
    public void printStackTrace() {
        System.out.println("Empty array can not be sorted!");
    }
}
