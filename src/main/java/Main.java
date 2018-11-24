import org.apache.commons.lang3.reflect.MethodUtils;
import org.reflections.Reflections;
import sorters.AbstractSorter;
import fillers.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException,
            InvocationTargetException {
        Reflections reflections = new Reflections("sorters");
        Set<Class<? extends AbstractSorter>> set = reflections.getSubTypesOf(AbstractSorter.class);

        for (Class<?> c : set){
            System.out.println(c.getName());
        }

        Method[] methods = MethodUtils.getMethodsWithAnnotation(Fillers.class, FillerAnnotation.class);

        for (Method method : methods){
            System.out.println(method.getName());
            Object obarray = method.invoke(null, 10);
            int[] array = (int[]) obarray;
            System.out.println(Arrays.toString(array));
        }
    }
}
