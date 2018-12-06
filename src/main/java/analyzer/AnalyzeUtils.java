package analyzer;

import annotations.FillerAnnotation;
import fillers.Fillers;
import org.apache.commons.lang3.reflect.MethodUtils;
import org.reflections.Reflections;
import sorters.AbstractSorter;
import annotations.MergeSortAnnotation;
import annotations.SortAnnotation;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

/**
 * Class contains methods that will help to make sorts analyzing more simple.
 * <br>There are:</br>
 * <pre>
 *     &#9;Method that returns subtypes of class in package.
 *     &#9;Method that excludes abstract classes from set of classes.
 *     &#9;Method that returns filler methods.
 *     &#9;Methods that return classes by their annotations.
 * </pre>
 * Class can not be extended.
 * <b>AnalyzeUtils</b> instances should NOT be constructed in standard programming.
 *
 * @author Dmytro Pylypiuk
 * @version 1.2
 */
final class AnalyzeUtils {
    /**
     * <b>AnalyzeUtils</b> instances should NOT be constructed in standard programming.
     */
    private AnalyzeUtils() {

    }

    /**
     * Method that gets all subtypes classes of {@see #sorters.AbstractSorter} class in "sorters" package.
     * <br>Invokes {@link Reflections#getSubTypesOf(Class)} method.</br>
     *
     * @return Set of <b>AbstractSorter</b> subtypes classes.
     */
    static Set<Class<? extends AbstractSorter>> getSubTypes() {
        Reflections reflections = new Reflections("sorters");
        return reflections.getSubTypesOf(AbstractSorter.class);
    }

    /**
     * Method excludes abstract classes from set of {@link AbstractSorter} subtypes.
     *
     * @param subTypes set of subtypes.
     */
    static void excludeAbstractClasses(Set<Class<? extends AbstractSorter>> subTypes) {
        Iterator<Class<? extends AbstractSorter>> iterator = subTypes.iterator();

        while (iterator.hasNext()) {
            Class<? extends AbstractSorter> clazz = iterator.next();

            if (Modifier.isAbstract(clazz.getModifiers())) {
                iterator.remove();
            }
        }
    }

    /**
     * Method that returns array of fillers.
     * <br>Invokes {@link MethodUtils#getMethodsWithAnnotation(Class, Class)} method.</br>
     * <br>{@code AnalyzeUtils.getFillers = Method[] methods}</br>
     *
     * @return Array of methods.
     */
    static Method[] getFillers() {
        return MethodUtils.getMethodsWithAnnotation(Fillers.class, FillerAnnotation.class);
    }

    /**
     * Method returns classes that are annotated by {@see annotations.SortAnnotation} annotation.
     *
     * @param set set of {@link AbstractSorter} class subtypes.
     * @return ArrayList of classes.
     */
    static ArrayList<Class<? extends AbstractSorter>> getSortsClasses(Set<Class<? extends AbstractSorter>> set) {
        ArrayList<Class<? extends AbstractSorter>> sortsArrayList =
                new ArrayList<Class<? extends AbstractSorter>>();

        for (Class<? extends AbstractSorter> clazz : set) {
            if (clazz.isAnnotationPresent(SortAnnotation.class)) {
                sortsArrayList.add(clazz);
            }
        }

        return sortsArrayList;
    }

    /**
     * Method returns class that is annotated by {@link MergeSortAnnotation} annotation.
     *
     * @param set set of {@link AbstractSorter} class subtypes.
     * @return MergeSort class.
     */
    static Class<? extends AbstractSorter> getMergeSortClass(Set<Class<? extends AbstractSorter>> set) {
        Class<? extends AbstractSorter> mergeSortClass = null;

        for (Class<? extends AbstractSorter> clazz : set) {
            if (clazz.isAnnotationPresent(MergeSortAnnotation.class)) {
                mergeSortClass = clazz;
                break;
            }
        }

        return mergeSortClass;
    }
}
