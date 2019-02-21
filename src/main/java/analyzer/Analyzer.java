package analyzer;

import exceptions.EmptyArrayException;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * Abstract class <b>Analyzer</b> presents template for different types of analyzers.
 * <br>Class contains only one public abstract method <b>analyze()</b>.</br>
 *
 * @author Dmytro Pylypiuk
 * @version 1.3
 */
public abstract class Analyzer {
    /**
     * Method`s behavior should be realized in classes that extends <b>Analyzer</b>.
     *
     * @param arraySize size of arrays that will be generated for analyzing.
     * @throws NoSuchMethodException     read {@link NoSuchMethodException}
     * @throws InstantiationException    read {@link InstantiationException}
     * @throws IllegalAccessException    read {@link IllegalAccessException}
     * @throws InvocationTargetException read {@link InvocationTargetException}
     * @throws NoSuchFieldException      read {@link NoSuchFieldException}
     * @throws EmptyArrayException       if method invoke filler for emty array.
     */
    public abstract void analyze(int arraySize) throws IOException, NoSuchMethodException, InstantiationException,
            IllegalAccessException, InvocationTargetException, NoSuchFieldException, EmptyArrayException;
}
