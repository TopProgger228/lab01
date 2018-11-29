package analyzer;

import org.apache.commons.lang3.reflect.MethodUtils;
import sorters.AbstractSorter;
import static analyzer.AnalyzeUtils.*;
import fillers.*;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;


public abstract class Analyzer {

    abstract public ArrayList<AbstractSorter> getSorts() throws NoSuchMethodException,
            InstantiationException, IllegalAccessException, InvocationTargetException;

    public void analyze() throws NoSuchMethodException,
            InstantiationException, IllegalAccessException, InvocationTargetException{

        ArrayList<AbstractSorter> objects = getSorts();

        Method[] fillers = getFillers();

        for(Method method : fillers){
            FillerAnnotation annotation = method.getAnnotation(FillerAnnotation.class);
            System.out.println(annotation.nameOfFiller());

            Object arrayObject = MethodUtils.invokeStaticMethod(Fillers.class, method.getName(), 10);
            int[] array = (int[]) arrayObject;

            System.out.println("Unsorted array:" + " " + Arrays.toString(array));

            for (AbstractSorter object : objects){
                System.out.println("Sort type:" + " " + object.getClass().getName());

                object.sort(array);

                System.out.println("Sorted array:" + " " + Arrays.toString(array));
            }
        }


    }

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
