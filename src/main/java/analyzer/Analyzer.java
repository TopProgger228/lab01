package analyzer;

import java.lang.reflect.InvocationTargetException;
import java.util.Random;

public abstract class Analyzer {
    abstract void analyze(int[] arraySizes) throws NoSuchMethodException, InstantiationException,
            IllegalAccessException, InvocationTargetException;

    public static int[] generateArraySizes(int amount){
        Random random = new Random();

        int[] array = new int[amount];

        for (int i = 0; i < amount; i++){
            int elem = random.nextInt(100) + 2;
            array[i] = elem;
        }

        return array;
    }
}
