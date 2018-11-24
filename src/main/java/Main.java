import org.apache.commons.lang3.reflect.MethodUtils;
import org.reflections.Reflections;
import sorters.AbstractSorter;
import fillers.*;
import sorters.BubbleSorter;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException,
            InvocationTargetException, NoSuchMethodException {
        Reflections reflections = new Reflections("sorters");
        Set<Class<? extends AbstractSorter>> set = reflections.getSubTypesOf(AbstractSorter.class);

        for (Class<? extends AbstractSorter> c : set){
            System.out.println(c.getName());
        }

        set.remove(BubbleSorter.class);

        Method[] methods = MethodUtils.getMethodsWithAnnotation(Fillers.class, FillerAnnotation.class);

        for (Method method : methods){
            System.out.println(method.getName());
//            Object obarray = method.invoke(null, 10);
            Object obarray = MethodUtils.invokeStaticMethod(Fillers.class, method.getName(), 10);
            int[] array = (int[]) obarray;
            System.out.println(Arrays.toString(array));
        }

        for (Class<? extends AbstractSorter> c : set){
            System.out.println(c.getName());
            Constructor<? extends AbstractSorter> constructor = c.getConstructor();
            AbstractSorter object = constructor.newInstance();

            for (Method m : methods){
                System.out.println(m.getName());
                Object obarray = MethodUtils.invokeStaticMethod(Fillers.class, m.getName(), 10);
                int[] array = (int[]) obarray;
                object.sort(array);
                System.out.println(Arrays.toString(array));
            }
        }
    }
}
