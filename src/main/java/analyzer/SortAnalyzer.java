package analyzer;

import exceptions.EmptyArrayException;
import exceptions.WrongRepeatAmount;
import annotations.FillerAnnotation;
import fillers.Fillers;
import org.apache.commons.lang3.reflect.MethodUtils;
import sorters.AbstractSorter;
import annotations.MergeSortAnnotation;
import annotations.SortAnnotation;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Random;
import java.util.Set;

import static analyzer.AnalyzeUtils.*;

/**
 * Class <b>SortAnalyzer</b> extends <b>Analyzer</b> and realize methods that doing sorts analyzing.
 * <br>Class has two main methods:</br>
 * <pre>
 *     &#9;{@link SortAnalyzer#analyze(int)} method.
 *     &#9;{@link SortAnalyzer#repeatAnalyze(int)} method.
 * </pre>
 *
 * @author Dmytro Pylypiuk
 * @version 1.5
 */
public class SortAnalyzer extends Analyzer {
    /**
     * Method repeats analyzing of sorts for different array sizes.
     * <br>{@code new SortAnalyzer().repeatAnalyze(0) = WrongRepeatAmount}</br>
     * <br>{@code new SortAnalyzer().repeatAnalyze(3) = true}</br>
     *
     * @param amount amount of analyzing repetitions.
     * @throws NoSuchMethodException     read {@link NoSuchFieldException}
     * @throws InstantiationException    read {@link InstantiationException}
     * @throws IllegalAccessException    read {@link IllegalAccessException}
     * @throws InvocationTargetException read {@link InvocationTargetException}
     * @throws NoSuchFieldException      read {@link NoSuchFieldException}
     * @throws EmptyArrayException       if method invoke filler for emty array.
     */
    public void repeatAnalyze(int amount) throws NoSuchMethodException, InstantiationException,
            IllegalAccessException, InvocationTargetException, NoSuchFieldException, WrongRepeatAmount,
            EmptyArrayException {
        if (amount > 0) {
            int[] arraySizes = generateArraySizes(amount);

            for (int arraySize : arraySizes) {
                analyze(arraySize);
            }
        } else {
            throw new WrongRepeatAmount();
        }
    }

    /**
     * Method analyze different types of sorts and printing result in console.
     * <br>{@code new SortAnalyzer().analyze(0) = InvocationTargetException}</br>
     * <br>{@code new SortAnalyzer().analyze(1) = true}</br>
     *
     * @param arraySize size of arrays that will be generated for analyzing.
     * @throws NoSuchMethodException     read {@link NoSuchFieldException}
     * @throws InstantiationException    read {@link InstantiationException}
     * @throws IllegalAccessException    read {@link IllegalAccessException}
     * @throws InvocationTargetException read {@link InvocationTargetException}
     * @throws NoSuchFieldException      read {@link NoSuchFieldException}
     * @throws EmptyArrayException       if method invoke filler for emty array.
     */
    @Override
    public void analyze(int arraySize) throws NoSuchMethodException, InstantiationException,
            IllegalAccessException, InvocationTargetException, NoSuchFieldException, EmptyArrayException {
        Set<Class<? extends AbstractSorter>> subTypesSet = getSubTypes();

        Method[] fillersMethod = getFillers();

        ArrayList<Class<? extends AbstractSorter>> sortsArrayList = getSortsClasses(subTypesSet);
        ArrayList<AbstractSorter> sortsObjects = getSortsObjects(sortsArrayList);

        Class<? extends AbstractSorter> mergeSortClass = getMergeSortClass(subTypesSet);
        Constructor<? extends AbstractSorter> constructor = getMergeSortConstructor(mergeSortClass);

        long performanceTime;

        for (Method method : fillersMethod) {
            FillerAnnotation fillerAnnotation = method.getAnnotation(FillerAnnotation.class);
            System.out.println(fillerAnnotation.nameOfFiller() + " " + "with array size:" + " " + arraySize + "\n");

            int[] array = getArray(method, arraySize);

            for (AbstractSorter object : sortsObjects) {
                long startTime = System.nanoTime();
                object.sort(array);
                long finishTime = System.nanoTime();

                performanceTime = finishTime - startTime;
                SortAnnotation annotation = object.getClass().getAnnotation(SortAnnotation.class);

                System.out.println("Sort type:" + " " + annotation.nameOfSort() + " " + "time:" + " " + performanceTime
                        + " " + "ns");

                AbstractSorter mergeSort = constructor.newInstance(object);

                long mergeSortStartTime = System.nanoTime();
                mergeSort.sort(array);
                long mergeSortFinishTime = System.nanoTime();

                performanceTime = mergeSortFinishTime - mergeSortStartTime;

                MergeSortAnnotation mergeSortAnnotation = mergeSort.getClass().getAnnotation(MergeSortAnnotation.class);
                System.out.println("Sort type:" + " " + mergeSortAnnotation.name() + " " + "with" + " " +
                        getMergeSortParam(mergeSort) + " " + "time:" + " " + performanceTime + " " + "ns");

            }
            System.out.println();
        }

    }

    /**
     * Method creates ArrayList of sorts objects.
     *
     * @param sortClasses ArrayList of sorts classes that annotated by {@link SortAnnotation}.
     * @return ArrayList of AbstractSort subtypes objects.
     * @throws NoSuchMethodException     read {@link NoSuchFieldException}
     * @throws InstantiationException    read {@link InstantiationException}
     * @throws IllegalAccessException    read {@link IllegalAccessException}
     * @throws InvocationTargetException read {@link InvocationTargetException}
     */
    private static ArrayList<AbstractSorter> getSortsObjects(ArrayList<Class<? extends AbstractSorter>> sortClasses)
            throws NoSuchMethodException,
            InstantiationException,
            IllegalAccessException,
            InvocationTargetException {

        ArrayList<AbstractSorter> sortsObjects = new ArrayList<AbstractSorter>();

        for (Class<? extends AbstractSorter> clazz : sortClasses) {
            Constructor<? extends AbstractSorter> constructor = clazz.getConstructor();
            AbstractSorter object = constructor.newInstance();

            sortsObjects.add(object);
        }

        return sortsObjects;
    }

    /**
     * Method creates constructor for MergeSort class.
     *
     * @param clazz MergeSort class.
     * @return MergeSort constructor.
     * @throws NoSuchMethodException read {@link NoSuchFieldException}
     */
    private static Constructor<? extends AbstractSorter> getMergeSortConstructor(Class<? extends AbstractSorter> clazz)
            throws NoSuchMethodException {
        return clazz.getConstructor(AbstractSorter.class);
    }

    /**
     * Method gets sort`s name that was used in MergeSort.
     *
     * @param object sort object.
     * @return name of sort type.
     * @throws NoSuchFieldException   read {@link NoSuchFieldException}
     * @throws IllegalAccessException read {@link IllegalAccessException}
     */
    private static String getMergeSortParam(AbstractSorter object)
            throws NoSuchFieldException, IllegalAccessException {
        Field field = object.getClass().getDeclaredField("sortTypeForMergeSort");
        field.setAccessible(true);

        AbstractSorter fieldValue = (AbstractSorter) field.get(object);

        SortAnnotation annotation = fieldValue.getClass().getAnnotation(SortAnnotation.class);

        return annotation.nameOfSort();
    }

    /**
     * Creates array of integers using different fillers.
     *
     * @param method    filler method.
     * @param arraySize size of array that will be created.
     * @return array of integers.
     * @throws NoSuchMethodException     read {@link NoSuchFieldException}
     * @throws IllegalAccessException    read {@link IllegalAccessException}
     * @throws InvocationTargetException read {@link InvocationTargetException}
     */
    private static int[] getArray(Method method, int arraySize) throws NoSuchMethodException,
            IllegalAccessException, InvocationTargetException {
        Object array = MethodUtils.invokeStaticMethod(Fillers.class, method.getName(), arraySize);
        return (int[]) array;
    }

    /**
     * Generate array of array sizes.
     *
     * @param amount amount of array sizes.
     * @return array of array sizes.
     */
    private static int[] generateArraySizes(int amount) {
        Random random = new Random();

        int[] array = new int[amount];

        int randomNumber = (random.nextInt(100) + 1);
        array[0] = (random.nextInt(200 + 1 - 50) - 50);

        for (int i = 1; i < array.length; i++) {
            array[i] = array[i - 1] + randomNumber;
        }

        return array;
    }
}
