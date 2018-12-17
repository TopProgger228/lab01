package fillers;

import exceptions.WrongArraySize;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class FillersTest {

    @org.junit.Test
    public void generateSortedArray() throws WrongArraySize {
        int[] testArray = Fillers.generateSortedArray(10);

        for (int i = 0; i < testArray.length; i++) {
            for (int j = i + 1; j < testArray.length; j++) {
                assertTrue(testArray[i] <= testArray[j]);
            }
        }

        int[] arrayForComparing = new int[testArray.length];

        for (int i = 0; i < arrayForComparing.length; i++) {
            arrayForComparing[i] = testArray[i];
        }

        Arrays.sort(arrayForComparing);

        assertArrayEquals(arrayForComparing, testArray);
    }

    @org.junit.Test
    public void generateSortedArrayWithAddedElement() throws WrongArraySize {
        int arraySize = 10;
        int[] testArray = Fillers.generateSortedArrayWithAddedElement(10);
        int currentArraySize = testArray.length;

        assertTrue(currentArraySize > arraySize);
        assertEquals(currentArraySize, arraySize + 1);
    }


    @org.junit.Test
    public void generateReversedArray() throws WrongArraySize {
        int[] testArray = Fillers.generateReversedArray(10);

        for (int i = 0; i < testArray.length; i++) {
            for (int j = i + 1; j < testArray.length; j++) {
                assertTrue(testArray[i] >= testArray[j]);
            }
        }
    }

    @org.junit.Test
    public void generateRandomArray() throws WrongArraySize {
        int arraySize = 10;
        int[] testArray = Fillers.generateRandomArray(arraySize);

        assertEquals(arraySize, testArray.length);
    }

    @Test
    public void checkArraysElements() throws WrongArraySize {
        int arraySize = 100;

        int[] sortedArray = Fillers.generateSortedArray(arraySize);
        int[] reversedArray = Fillers.generateReversedArray(arraySize);
        int[] randomArray = Fillers.generateRandomArray(arraySize);
        int[] arrayWithAddedElement = Fillers.generateSortedArrayWithAddedElement(arraySize);

        for (int i = 0; i < arraySize; i++) {
            assertTrue((sortedArray[i] <= 100) && (sortedArray[i] >= -100));
            assertTrue((reversedArray[i] <= 100) && (reversedArray[i] >= -100));
            assertTrue((randomArray[i] <= 100) && (randomArray[i] >= -100));
            assertTrue((arrayWithAddedElement[i] <= 100) && (arrayWithAddedElement[i] >= -100));
        }
    }

    @Test(expected = WrongArraySize.class)
    public void testSortedArrayException() throws WrongArraySize {
        Fillers.generateSortedArray(-1);
    }

    @Test(expected = WrongArraySize.class)
    public void testReversedArrayException() throws WrongArraySize {
        Fillers.generateReversedArray(-1);
    }

    @Test(expected = WrongArraySize.class)
    public void testRandomArrayException() throws WrongArraySize {
        Fillers.generateRandomArray(-1);
    }

    @Test(expected = WrongArraySize.class)
    public void testSortedArrayWithAddedElemException() throws WrongArraySize {
        Fillers.generateSortedArrayWithAddedElement(-1);
    }

    @Test(timeout = 100)
    public void testSortedArrayExecution() throws WrongArraySize {
        Fillers.generateSortedArray(100);
    }

    @Test(timeout = 100)
    public void testReversedArrayExecution() throws WrongArraySize {
        Fillers.generateReversedArray(100);
    }

    @Test(timeout = 100)
    public void testRandomArrayExecution() throws WrongArraySize {
        Fillers.generateRandomArray(100);
    }

    @Test(timeout = 100)
    public void testSortedArrayWithAddedElemExecution() throws WrongArraySize {
        Fillers.generateSortedArrayWithAddedElement(100);
    }
}