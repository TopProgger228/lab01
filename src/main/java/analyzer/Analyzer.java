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
import java.util.Arrays;
import java.util.Random;


public abstract class Analyzer {

    abstract public ArrayList<AbstractSorter> getSorts() throws NoSuchMethodException,
            InstantiationException, IllegalAccessException, InvocationTargetException;

    public void doAnalyzeMore(int amount) throws NoSuchMethodException,InstantiationException, IllegalAccessException,
            InvocationTargetException, NoSuchFieldException, WrongRepeatAmount{
        if (amount > 0){
            int[] array = generateArraySizes(amount);

            for (int arraySize : array){
                analyze(arraySize);
            }
        }else {
            throw new WrongRepeatAmount();
        }
    }

    private void analyze(int arraySize) throws NoSuchMethodException,
            InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchFieldException{

        ArrayList<AbstractSorter> objects = getSorts();

        Method[] fillers = getFillers();

        if (objects.get(0).getClass().isAnnotationPresent(SortAnnotation.class)){
            for(Method method : fillers){
                FillerAnnotation annotation = method.getAnnotation(FillerAnnotation.class);
                System.out.println(annotation.nameOfFiller());

                Object arrayObject = MethodUtils.invokeStaticMethod(Fillers.class, method.getName(), arraySize);
                int[] array = (int[]) arrayObject;

                System.out.println("Unsorted array:" + " " + Arrays.toString(array));

                for (AbstractSorter object : objects){
                    SortAnnotation sortAnnotation = object.getClass().getAnnotation(SortAnnotation.class);
                    System.out.println("Sort type:" + " " + sortAnnotation.nameOfSort());

                    object.sort(array);

                    System.out.println("Sorted array:" + " " + Arrays.toString(array));
                }
            }
        }else if (objects.get(0).getClass().isAnnotationPresent(MergeSortAnnotation.class)){
            for (Method method : fillers){
                FillerAnnotation annotation = method.getAnnotation(FillerAnnotation.class);
                System.out.println(annotation.nameOfFiller());

                Object arrayObject = MethodUtils.invokeStaticMethod(Fillers.class, method.getName(), arraySize);
                int[] array = (int[]) arrayObject;

                System.out.println("Unsorted array:" + " " + Arrays.toString(array));

                for (AbstractSorter object : objects){
                    Field field = object.getClass().getDeclaredField("sortTypeForMergeSort");
                    field.setAccessible(true);

                    AbstractSorter fieldValue = (AbstractSorter) field.get(object);

                    SortAnnotation sortAnnotation = fieldValue.getClass().getAnnotation(SortAnnotation.class);
                    MergeSortAnnotation mergeSortAnnotation = object.getClass().
                            getAnnotation(MergeSortAnnotation.class);

                    System.out.println(mergeSortAnnotation.name() + " " + "with" + " " + sortAnnotation.nameOfSort());

                    object.sort(array);

                    System.out.println("Sorted array:" + " " + Arrays.toString(array));
                }
            }
        }
    }

    private static int[] generateArraySizes(int amount){
        Random random = new Random();

        int[] array = new int[amount];

        for (int i = 0; i < amount; i++){
            int elem = random.nextInt(10) + 2;
            array[i] = elem;
        }

        return array;
    }
}
