package fillers;

import annotations.FillerAnnotation;
import exceptions.WrongArraySize;

import java.util.Arrays;
import java.util.Random;

/**
 * Class contains methods for generating different types of arrays.
 * <br>There are:</br>
 * <pre>
 *     &#9;Generator for sorted array.
 *     &#9;Generator for sorted array with added element in the end of array.
 *     &#9;Generator for reversed sorted array.
 *     &#9;Generator for random array.
 * </pre>
 * Class can not be extended.
 * <b>Fillers</b> instances should NOT be constructed in standard programming.
 *
 * @author Dmytro Pylypiuk
 * @version 1.0
 */
public class Fillers {
    /**
     * private static field of type Random that helps to get array elements.
     */
    private static Random random = new Random();

    /**
     * <b>Fillers</b> instances should NOT be constructed in standard programming.
     */
    private Fillers() {
    }

    /**
     * Method generates sorted array of pseudorandom integers.
     * <br>Is annotated by {@link FillerAnnotation} annotation.</br>
     * <br>{@code Fillers.generateSortedArray(10) = int[] array}</br>
     *
     * @param arraySize size of array that will be created.
     * @return Generated sorted array.
     * @throws WrongArraySize if size of array is zero or less.
     */
    @FillerAnnotation(nameOfFiller = "Sorted array")
    public static int[] generateSortedArray(int arraySize) throws WrongArraySize {
        if (arraySize > 0) {
            int[] array = new int[arraySize];

            for (int i = 0; i < array.length; i++) {
                array[i] = random.nextInt(200 + 1 - 50) - 50;
            }

            Arrays.sort(array);
            return array;
        } else {
            throw new WrongArraySize();
        }
    }

    /**
     * Method generates sorted array of pseudorandom integers and add random element in the end.
     * <br>Is annotated by {@link FillerAnnotation} annotation.</br>
     * <br>{@code Fillers.generateSortedArrayWithAddedElement(10) = int[] array}</br>
     *
     * @param arraySize size of array that will be created.
     * @return Generated sorted array with added element.
     * @throws WrongArraySize if size of array is zero or less.
     */
    @FillerAnnotation(nameOfFiller = "Sorted array with added element")
    public static int[] generateSortedArrayWithAddedElement(int arraySize) throws WrongArraySize {
        if (arraySize > 0) {
            int[] array = new int[arraySize + 1];

            for (int i = 0; i < array.length - 1; i++) {
                array[i] = random.nextInt(200 + 1 - 50) - 50;
            }

            Arrays.sort(array);
            array[array.length - 1] = random.nextInt(200 + 1 - 50) - 50;

            return array;
        } else {
            throw new WrongArraySize();
        }
    }

    /**
     * Method generates sorted array of pseudorandom integers and reverse it.
     * <br>Is annotated by {@link FillerAnnotation} annotation.</br>
     * <br>{@code Fillers.generateReversedArray(10) = int[] array}</br>
     *
     * @param arraySize size of array that will be created.
     * @return Generated reversed sorted array.
     * @throws WrongArraySize if size of array is zero or less.
     */
    @FillerAnnotation(nameOfFiller = "Reversed array")
    public static int[] generateReversedArray(int arraySize) throws WrongArraySize {
        if (arraySize > 0) {
            int[] array = Fillers.generateRandomArray(arraySize);

            for (int i = array.length - 1; i > 0; i--) {
                for (int j = i - 1; j >= 0; j--) {
                    if (array[i] > array[j]) {
                        int var = array[i];
                        array[i] = array[j];
                        array[j] = var;
                    }
                }
            }

            return array;
        } else {
            throw new WrongArraySize();
        }
    }

    /**
     * Method generates array of pseudorandom integers.
     * <br>Is annotated by {@link FillerAnnotation} annotation.</br>
     * <br>{@code Fillers.generateRandomArray(10) = int[] array}</br>
     *
     * @param arraySize size of array that will be created.
     * @return Generated array.
     * @throws WrongArraySize if size of array is zero or less.
     */
    @FillerAnnotation(nameOfFiller = "Random array")
    public static int[] generateRandomArray(int arraySize) throws WrongArraySize {
        if (arraySize > 0) {
            int[] array = new int[arraySize];

            for (int i = 0; i < array.length; i++) {
                array[i] = random.nextInt(200 + 1 - 50) - 50;
            }

            return array;
        } else {
            throw new WrongArraySize();
        }
    }
}
