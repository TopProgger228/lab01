package analyzer;

import java.lang.reflect.InvocationTargetException;

public interface Analyzer {
    public void analyze(int arraySize) throws NoSuchMethodException, InstantiationException,
            IllegalAccessException, InvocationTargetException;
}
