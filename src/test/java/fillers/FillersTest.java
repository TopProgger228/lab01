package fillers;

import exceptions.WrongArraySize;

import static org.junit.Assert.*;

public class FillersTest {

    @org.junit.Test(expected = WrongArraySize.class)
    public void generateSortedArray() throws WrongArraySize{
        int[] array = Fillers.generateSortedArray(3);
    }

    @org.junit.Test
    public void generateSortedArrayWithAddedElement() {
    }

    @org.junit.Test
    public void generateReversedArray() {
    }

    @org.junit.Test
    public void generateRandomArray() {
    }
}