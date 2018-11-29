package analyzer;

import exceptions.WrongRepeatAmount;
import org.apache.commons.lang3.reflect.MethodUtils;
import sorters.AbstractSorter;

import static analyzer.AnalyzeUtils.*;

import fillers.*;
import sorters.MergeSortAnnotation;
import sorters.SortAnnotation;


import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Random;


public abstract class Analyzer {

    abstract public ArrayList<AbstractSorter> getSorts() throws NoSuchMethodException,
            InstantiationException, IllegalAccessException, InvocationTargetException;

    public void repeatAnalyze(int amount) throws NoSuchMethodException, InstantiationException, IllegalAccessException,
            InvocationTargetException, NoSuchFieldException, WrongRepeatAmount {
        if (amount > 0) {
            int[] array = generateArraySizes(amount);

            for (int arraySize : array) {
                analyze(arraySize);
            }
        } else {
            throw new WrongRepeatAmount();
        }
    }

    private void analyze(int arraySize) throws NoSuchMethodException,
            InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchFieldException {

        ArrayList<AbstractSorter> objects = getSorts();

        Method[] fillers = getFillers();

        long performanceTime;

        if (objects.get(0).getClass().isAnnotationPresent(SortAnnotation.class)) {
            for (Method method : fillers) {
                FillerAnnotation annotation = method.getAnnotation(FillerAnnotation.class);
                System.out.println(annotation.nameOfFiller() + " " + "array size is:" + " " + arraySize);

                int[] array = getArray(method, arraySize);

                for (AbstractSorter object : objects) {
                    SortAnnotation sortAnnotation = object.getClass().getAnnotation(SortAnnotation.class);

                    performanceTime = getPerformance(object, array);

                    System.out.println("Sort type:" + " " + sortAnnotation.nameOfSort() + " " + performanceTime +
                            " " + "nanoseconds");
                }
            }
        } else if (objects.get(0).getClass().isAnnotationPresent(MergeSortAnnotation.class)) {
            for (Method method : fillers) {
                FillerAnnotation annotation = method.getAnnotation(FillerAnnotation.class);
                System.out.println(annotation.nameOfFiller() + " " + "array size is:" + " " + arraySize);

                int[] array = getArray(method, arraySize);

                for (AbstractSorter object : objects) {
                    Field field = object.getClass().getDeclaredField("sortTypeForMergeSort");
                    field.setAccessible(true);

                    AbstractSorter fieldValue = (AbstractSorter) field.get(object);

                    SortAnnotation sortAnnotation = fieldValue.getClass().getAnnotation(SortAnnotation.class);
                    MergeSortAnnotation mergeSortAnnotation = object.getClass().
                            getAnnotation(MergeSortAnnotation.class);

                    performanceTime = getPerformance(object, array);

                    System.out.println(mergeSortAnnotation.name() + " " + "with" + " " + sortAnnotation.nameOfSort() +
                            " " + performanceTime + " " + "nanoseconds");

                }
            }
        }
    }

    private static int[] generateArraySizes(int amount) {
        Random random = new Random();

        int[] array = new int[amount];

        for (int i = 0; i < amount; i++) {
            int elem = random.nextInt(10000) + 2;
            array[i] = elem;
        }

        return array;
    }

    private long getPerformance(AbstractSorter sorter, int[] array){
        long startTime = System.nanoTime();
        sorter.sort(array);
        long finishTime = System.nanoTime();

        return finishTime - startTime;
    }

    private int[] getArray(Method method, int arraySize) throws NoSuchMethodException,
            IllegalAccessException, InvocationTargetException{
        Object array = MethodUtils.invokeStaticMethod(Fillers.class, method.getName(), arraySize);
        return (int[]) array;
    }
}
