package exceptions;

public class WrongArraySize extends Exception {
    @Override
    public void printStackTrace() {
        System.out.println("Wrong array size!");
    }
}
