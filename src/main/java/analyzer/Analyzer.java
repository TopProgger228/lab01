package analyzer;

import exceptions.EmptyArrayException;

import java.lang.reflect.InvocationTargetException;

public abstract class Analyzer {
    public abstract void analyze(int arraySize) throws NoSuchMethodException, InstantiationException,
            IllegalAccessException, InvocationTargetException, NoSuchFieldException, EmptyArrayException;
}
