package fillers;

import java.util.Arrays;
import java.util.Random;

/**
 * <b>Fillers</b> class that implements {@link Filler} interface.
 * This class presents four overrided methods for different ways to fill arrays.
 * Class is not declared as final for possible extension.
 *
 * @author Dmytro Pylypyuk.
 * @version 1.0
 */
public class Fillers implements Filler {
    private static final int DEFAULT_ARRAY_SIZE = 100;
    private int arraySize;
    private Random random = new Random();

    public Fillers(){
        this.arraySize = DEFAULT_ARRAY_SIZE;
    }
    /**
     * Constructor takes and initializes parameter @param arraySize.
     */
    public Fillers(int arraySize) {
        this.arraySize = arraySize;
    }

    /**
     * Overrided version of {@link Filler#generateSortedArray()} method.
     * Generates array of pseudorandom integers with {@link Fillers#arraySize} size
     * and sorts by invoking {@link Arrays#sort(int[])} method.
     * For generating uses {@link Random}.
     *
     * @return sorted array of integers.
     */
    @Override
    public int[] generateSortedArray() {
        int[] array = new int[arraySize];

        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(200 + 1 - 50) - 50;
        }

        Arrays.sort(array);
        return array;
    }

    /**
     * Overrided version of {@link Filler#generateSortedArrayWithAddedElement()} method.
     * Generates array of pseudorandom integers with {@link Fillers#arraySize} size,
     * sorts by invoking {@link Arrays#sort(int[])} method and adds pseudorandom integer element in the end of array.
     * For generating uses {@link Random}.
     *
     * @return sorted array of integers with added element.
     */
    @Override
    public int[] generateSortedArrayWithAddedElement() {
        int[] array = new int[arraySize + 1];

        for (int i = 0; i < array.length - 1; i++) {
            array[i] = random.nextInt(200 + 1 - 50) - 50;
        }

        Arrays.sort(array);
        array[array.length - 1] = random.nextInt(200 + 1 - 50) - 50;

        return array;
    }

    /**
     * Overloaded version of {@link Fillers#generateSortedArrayWithAddedElement()}.
     * Method takes @param element. User can set element manually using this method.
     * For generating uses {@link Random}.
     *
     * @return sorted array of integers with added element.
     */
    public int[] generateSortedArrayWithAddedElement(int element) {
        int[] array = new int[arraySize + 1];

        for (int i = 0; i < array.length - 1; i++) {
            array[i] = random.nextInt(200 + 1 - 50) - 50;
        }

        Arrays.sort(array);
        array[array.length - 1] = element;

        return array;
    }

    /**
     * Overrided version of {@link Fillers#generateReversedArray()} method.
     * Generates array of pseudorandom integers with {@link Fillers#arraySize} size
     * using {@link Fillers#generateRandomArray()} and reverse it.
     * For generating uses {@link Random}.
     *
     * @return reversed sorted array of integers.
     */
    @Override
    public int[] generateReversedArray() {
        Fillers filler = new Fillers(arraySize);

        int[] array = filler.generateRandomArray();

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
    }

    /**
     * Generates array of pseudorandom integers with {@link Fillers#arraySize} size.
     * For generating uses {@link Random}.
     *
     * @return array of pseudorandom integers.
     */
    @Override
    public int[] generateRandomArray() {
        int[] array = new int[arraySize];

        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(200 + 1 - 50) - 50;
        }

        return array;
    }
}
