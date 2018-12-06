package sorters;

import exceptions.EmptyArrayException;
import exceptions.WrongArraySize;
import fillers.Fillers;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class QuickSortTest {
    private QuickSort quickSort;

    @Before
    public void setUp() throws Exception {
        quickSort = new QuickSort();
    }

    @Test
    public void sort() throws EmptyArrayException {
        int[] arrayForComparing = {-4, -2, 0, 0, 1, 2, 4, 7, 9, 10, 13, 19, 65, 101, 202, 900};
        int[] arrayForSorting = {-2, 0, -4, 0, 2, 1, 7, 4, 10, 9, 19, 13, 101, 65, 900, 202};

        quickSort.sort(arrayForSorting);

        assertArrayEquals(arrayForComparing, arrayForSorting);
    }

    @Test(timeout = 1000)
    public void testExecutionTime() throws WrongArraySize, EmptyArrayException {
        int[] testArray = Fillers.generateRandomArray(10000);
        quickSort.sort(testArray);
    }


    @Test(expected = EmptyArrayException.class)
    public void testException() throws EmptyArrayException {
        int[] testArray = {};
        quickSort.sort(testArray);
    }

    @Test(expected = NullPointerException.class)
    public void testNullPointerException() throws EmptyArrayException {
        int[] testArray = null;
        quickSort.sort(testArray);
    }
}