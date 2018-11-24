import org.reflections.Reflections;
import sorters.AbstractSorter;
import fillers.*;
import java.lang.reflect.Method;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Reflections reflections = new Reflections("sorters");
        Set<Class<? extends AbstractSorter>> set = reflections.getSubTypesOf(AbstractSorter.class);

        Class<?> fillers = Fillers.class;
        Method[] methods = fillers.getDeclaredMethods();
        for (Class<?> c : set){
            System.out.println(c.getName());
        }

        for (Method m : methods){
            System.out.println(m.getName());
        }
    }
}
