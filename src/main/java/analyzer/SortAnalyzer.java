package analyzer;

import fillers.FillerAnnotation;
import fillers.Fillers;
import org.apache.commons.lang3.reflect.MethodUtils;
import org.reflections.Reflections;
import sorters.AbstractSorter;
import sorters.MergeSortAnnotation;
import sorters.SortAnnotation;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;

public class SortAnalyzer implements Analyzer {
    public void analyze(int arraySize) throws NoSuchMethodException, InstantiationException,
            IllegalAccessException, InvocationTargetException {
        Reflections reflections = new Reflections("sorters");
        Set<Class<? extends AbstractSorter>> classSet = reflections.
                getSubTypesOf(AbstractSorter.class);

        Iterator<Class<? extends AbstractSorter>> iterator = classSet.iterator();

        while (iterator.hasNext()){
            Class<? extends AbstractSorter> clazz = iterator.next();

            if (Modifier.isAbstract(clazz.getModifiers())){
                iterator.remove();
            }
        }

        Method[] fillersMethods = MethodUtils.
                getMethodsWithAnnotation(Fillers.class, FillerAnnotation.class);

        ArrayList<Class<? extends AbstractSorter>> sortClassesArray =
                new ArrayList<Class<? extends AbstractSorter>>();

        ArrayList<Class<? extends AbstractSorter>> mergeSortClassesArray =
                new ArrayList<Class<? extends AbstractSorter>>();

        for (Class<? extends AbstractSorter> clazz : classSet){
            if (clazz.isAnnotationPresent(SortAnnotation.class)){
                sortClassesArray.add(clazz);
            }else if (clazz.isAnnotationPresent(MergeSortAnnotation.class)){
                mergeSortClassesArray.add(clazz);
            }
        }

        for (Class<? extends AbstractSorter> clazz : sortClassesArray){
            System.out.println("Sorting class name" + " " + clazz.getName());

            Constructor<? extends AbstractSorter> constructor = clazz.getConstructor();
            AbstractSorter object = constructor.newInstance();

            for (Method method : fillersMethods){
                System.out.println("Method name" + " " + method.getName());

                Object obArray = MethodUtils.invokeStaticMethod(Fillers.class, method.getName(), arraySize);
                int[] array = (int[]) obArray;

                object.sort(array);

                System.out.println(Arrays.toString(array));
            }
        }

        ArrayList<AbstractSorter> sorts = new ArrayList<AbstractSorter>();
        ArrayList<String> mergeClassNames = new ArrayList<String>();
        ArrayList<String> sortsNames = new ArrayList<String>();

        for (Class<? extends AbstractSorter> clazz : mergeSortClassesArray){
            Constructor<? extends AbstractSorter> constructor = clazz.getConstructor(AbstractSorter.class);
            mergeClassNames.add(clazz.getName());
            for (Class<? extends AbstractSorter> sort : sortClassesArray){
                Constructor<? extends AbstractSorter> sortConstructor = sort.getConstructor();
                AbstractSorter object = sortConstructor.newInstance();
                sortsNames.add(object.getClass().getName());
                sorts.add(constructor.newInstance(object));
            }
        }

        for (String mergeName : mergeClassNames){
            for (int i = 0; i < sorts.size(); i++){
                for (Method method : fillersMethods){
                    Object obArray = MethodUtils.invokeStaticMethod(Fillers.class, method.getName(), arraySize);
                    int[] array = (int[]) obArray;
                    sorts.get(i).sort(array);
                    System.out.println(mergeName + " " + "with" + " " + sortsNames.get(i) + " " +
                            method.getName());
                    System.out.println(Arrays.toString(array));
                }
            }
        }
    }
}
