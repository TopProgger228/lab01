import org.apache.commons.lang3.reflect.MethodUtils;
import org.reflections.Reflections;
import sorters.*;
import fillers.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException,
            InvocationTargetException, NoSuchMethodException, NoSuchFieldException {
        Reflections reflections = new Reflections("sorters");
        Set<Class<? extends AbstractSorter>> set = reflections.getSubTypesOf(AbstractSorter.class);

//        for (Class<? extends AbstractSorter> c : set){
//            System.out.println(c.getName());
//        }

        set.remove(BubbleSorter.class);

        Method[] methods = MethodUtils.getMethodsWithAnnotation(Fillers.class, FillerAnnotation.class);

//        for (Method method : methods){
//            System.out.println(method.getName());
////            Object obarray = method.invoke(null, 10);
//            Object obarray = MethodUtils.invokeStaticMethod(Fillers.class, method.getName(), 10);
//            int[] array = (int[]) obarray;
//            System.out.println(Arrays.toString(array));
//        }

        ArrayList<AbstractSorter> sorts = new ArrayList<AbstractSorter>();
        ArrayList<AbstractSorter> arr = new ArrayList<AbstractSorter>();

        for (Class<? extends AbstractSorter> c : set){
            if(!c.equals(MergeSort.class)){
                Constructor<? extends AbstractSorter> constructor = c.getConstructor();
                AbstractSorter object = constructor.newInstance();
                arr.add(object);
            }else{
                Constructor<? extends AbstractSorter> constructor = c.getConstructor(AbstractSorter.class);
                sorts.add(constructor.newInstance(new BubbleSortReversed()));
                sorts.add(constructor.newInstance(new BubbleSortClassic()));
                sorts.add(constructor.newInstance(new QuickSort()));
                sorts.add(constructor.newInstance(new BuiltInSort()));
            }
        }

        for (AbstractSorter as : sorts){
            System.out.println(as.getClass().getName());
//            Field field = as.getClass().getDeclaredField("sortTypeForMergeSort");
//            Type type = field.getType();
//            System.out.println(type);
            for (Method m : methods){
                System.out.println(m.getName());
                Object obarray = MethodUtils.invokeStaticMethod(Fillers.class, m.getName(), 10);
                int[] array = (int[]) obarray;
                as.sort(array);
                System.out.println(Arrays.toString(array));
            }
        }

        for (AbstractSorter object : arr){
            System.out.println(object.getClass().getName());
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
