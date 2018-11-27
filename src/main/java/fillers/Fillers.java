package fillers;

import exceptions.WrongArraySize;

import java.util.Arrays;
import java.util.Random;

public class Fillers {
    private static Random random = new Random();

    private Fillers(){}

    @FillerAnnotation(nameOfFiller = "Sorted array")
    public static int[] generateSortedArray(int arraySize) throws WrongArraySize {
        if (arraySize > 0){
            int[] array = new int[arraySize];

            for (int i = 0; i < array.length; i++) {
                array[i] = random.nextInt(200 + 1 - 50) - 50;
            }

            Arrays.sort(array);
            return array;
        }else {
            throw new WrongArraySize();
        }
    }

    @FillerAnnotation(nameOfFiller = "Sorted array with added element")
    public static int[] generateSortedArrayWithAddedElement(int arraySize) throws WrongArraySize {
        if (arraySize > 0){
            int[] array = new int[arraySize + 1];

            for (int i = 0; i < array.length - 1; i++) {
                array[i] = random.nextInt(200 + 1 - 50) - 50;
            }

            Arrays.sort(array);
            array[array.length - 1] = random.nextInt(200 + 1 - 50) - 50;

            return array;
        }else {
            throw new WrongArraySize();
        }
    }

    @FillerAnnotation(nameOfFiller = "Reversed array")
    public static int[] generateReversedArray(int arraySize) throws WrongArraySize{
        if (arraySize > 0){
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
        }else {
            throw new WrongArraySize();
        }
    }

    @FillerAnnotation(nameOfFiller = "Random array")
    public static int[] generateRandomArray(int arraySize) throws WrongArraySize {
        if (arraySize > 0){
            int[] array = new int[arraySize];

            for (int i = 0; i < array.length; i++) {
                array[i] = random.nextInt(200 + 1 - 50) - 50;
            }

            return array;
        }else {
            throw new WrongArraySize();
        }
    }
}
