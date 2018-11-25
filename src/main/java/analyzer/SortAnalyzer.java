package analyzer;

import fillers.FillerAnnotation;
import fillers.Fillers;
import org.apache.commons.lang3.reflect.MethodUtils;
import org.reflections.Reflections;
import sorters.AbstractSorter;
import sorters.MergeSortAnnotation;
import sorters.SortAnnotation;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Set;

public class SortAnalyzer implements Analyzer {
    public void analyze() {
        Reflections reflections = new Reflections("sorters");
        Set<Class<? extends AbstractSorter>> classSet = reflections.
                getSubTypesOf(AbstractSorter.class);

        for (Class<? extends AbstractSorter> clazz : classSet){
            if (Modifier.isAbstract(clazz.getModifiers())){
                classSet.remove(clazz);
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
    }
}
