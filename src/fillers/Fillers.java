package fillers;

import java.util.Arrays;
import java.util.Random;

public class Fillers extends FillersTemplate{
    private int arraySize;
    private Random random = new Random();

    public Fillers(int arraySize) {
        this.arraySize = arraySize;
    }

    @Override
    public int[] generateSortedArray() {
        int[] array = new int[arraySize];

        for (int i = 0; i < array.length; i++){
            array[i] = random.nextInt(200 + 1 - 50) - 50;
        }

        Arrays.sort(array);
        return array;
    }

    @Override
    public int[] generateSortedArrayWithAddedElement() {
       int[] array = new int[arraySize + 1];

       for (int i = 0; i < array.length - 1; i++){
           array[i] = random.nextInt(200 + 1 - 50) - 50;
       }

       Arrays.sort(array);
       array[array.length - 1] = random.nextInt(200 + 1 - 50) - 50;

       return array;
    }

    public int[] generateSortedArrayWithAddedElement(int element){
        int[] array = new int[arraySize + 1];

        for (int i = 0; i < array.length - 1; i++){
            array[i] = random.nextInt(200 + 1 - 50) - 50;
        }

        Arrays.sort(array);
        array[array.length - 1] = element;

        return array;
    }

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

    @Override
    public int[] generateRandomArray() {
        int[] array = new int[arraySize];

        for (int i = 0; i < array.length; i++){
            array[i] = random.nextInt(200 + 1 - 50) - 50;
        }

        return array;
    }
}
