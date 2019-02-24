package sorters;

import exceptions.EmptyArrayException;
import exceptions.WrongArraySize;
import fillers.Fillers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

public class MergeSortTest {
    private MergeSort mergeSort;
    private BubbleSortClassic bubbleSortClassic;
    private BubbleSortReversed bubbleSortReversed;
    private BuiltInSort builtInSort;
    private QuickSort quickSort;

    @Before
    public void setUp() {
        bubbleSortClassic = new BubbleSortClassic();
        bubbleSortReversed = new BubbleSortReversed();
        builtInSort = new BuiltInSort();
        quickSort = new QuickSort();
    }

    @Test
    public void sort() throws EmptyArrayException {
        int[] arrayForComparing = {-5, 1, 7, 12, 44, 90, 101, 245, 567, 1252, 5456};
        int[] arrayForSorting = {1, -5, 7, 44, 12, 101, 245, 90, 1252, 567, 5456};

        mergeSort = new MergeSort(bubbleSortClassic);
        mergeSort.sort(arrayForSorting);
        assertArrayEquals(arrayForComparing, arrayForSorting);

        mergeSort = new MergeSort(bubbleSortReversed);
        mergeSort.sort(arrayForSorting);
        assertArrayEquals(arrayForComparing, arrayForSorting);

        mergeSort = new MergeSort(builtInSort);
        mergeSort.sort(arrayForSorting);
        assertArrayEquals(arrayForComparing, arrayForSorting);

        mergeSort = new MergeSort(quickSort);
        mergeSort.sort(arrayForSorting);
        assertArrayEquals(arrayForComparing, arrayForSorting);
    }

    @Test(timeout = 1000)
    public void testExecutionTimeWithBSC() throws WrongArraySize, EmptyArrayException {
        int[] testArray = Fillers.generateRandomArray(10000);
        mergeSort = new MergeSort(bubbleSortClassic);
        mergeSort.sort(testArray);
    }

    @Test(timeout = 1000)
    public void testExecutionTimeWithBSR() throws WrongArraySize, EmptyArrayException {
        int[] testArray = Fillers.generateRandomArray(10000);
        mergeSort = new MergeSort(bubbleSortReversed);
        mergeSort.sort(testArray);
    }

    @Test(timeout = 1000)
    public void testExecutionTimeWithBIS() throws WrongArraySize, EmptyArrayException {
        int[] testArray = Fillers.generateRandomArray(10000);
        mergeSort = new MergeSort(builtInSort);
        mergeSort.sort(testArray);
    }

    @Test(timeout = 1000)
    public void testExecutionTimeWithQS() throws WrongArraySize, EmptyArrayException {
        int[] testArray = Fillers.generateRandomArray(10000);
        mergeSort = new MergeSort(quickSort);
        mergeSort.sort(testArray);
    }

}