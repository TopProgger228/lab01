package sorters;

import exceptions.EmptyArrayException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BubbleSortClassicTest {
    private BubbleSortClassic bubbleSortClassic;
    @Before
    public void init(){
       bubbleSortClassic = new BubbleSortClassic();
    }

    @Test(expected = EmptyArrayException.class)
    public void sort() throws EmptyArrayException {
        init();

        int[] myArray = {1, 2, 3, 4, 5};
        int[] array = {1, 2, 4, 3, 5};

        assertNotNull("Object is null", bubbleSortClassic);

        bubbleSortClassic.sort(array);

        assertArrayEquals(myArray, array);

    }
}