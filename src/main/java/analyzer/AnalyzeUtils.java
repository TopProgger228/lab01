package analyzer;

import fillers.FillerAnnotation;
import fillers.Fillers;
import org.apache.commons.lang3.reflect.MethodUtils;
import org.reflections.Reflections;
import sorters.AbstractSorter;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

final class AnalyzeUtils {
    private AnalyzeUtils() {

    }

    static Set<Class<? extends AbstractSorter>> getSubTypes() {
        Reflections reflections = new Reflections("sorters");
        return reflections.getSubTypesOf(AbstractSorter.class);
    }

    static void excludeAbstractClasses(Set<Class<? extends AbstractSorter>> subTypes) {
        Iterator<Class<? extends AbstractSorter>> iterator = subTypes.iterator();

        while (iterator.hasNext()) {
            Class<? extends AbstractSorter> clazz = iterator.next();

            if (Modifier.isAbstract(clazz.getModifiers())) {
                iterator.remove();
            }
        }
    }

    static Method[] getFillers() {
        return MethodUtils.getMethodsWithAnnotation(Fillers.class, FillerAnnotation.class);
    }

    static ArrayList<Class<? extends AbstractSorter>> getSortsClasses(Class<? extends Annotation> annotation,
                                                                      Set<Class<? extends AbstractSorter>> set) {
        ArrayList<Class<? extends AbstractSorter>> sortsArrayList =
                new ArrayList<Class<? extends AbstractSorter>>();

        for (Class<? extends AbstractSorter> clazz : set) {
            if (clazz.isAnnotationPresent(annotation)) {
                sortsArrayList.add(clazz);
            }
        }

        return sortsArrayList;
    }
}
