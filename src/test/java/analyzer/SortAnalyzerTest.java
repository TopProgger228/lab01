package analyzer;

import exceptions.EmptyArrayException;
import exceptions.WrongRepeatAmount;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;

public class SortAnalyzerTest {
    private SortAnalyzer analyzer;

    @Before
    public void setUp() throws Exception {
        analyzer = new SortAnalyzer();
    }

    @Test
    public void repeatAnalyze() throws NoSuchMethodException, InstantiationException,
            IllegalAccessException, InvocationTargetException, NoSuchFieldException, WrongRepeatAmount,
            EmptyArrayException {
        analyzer.repeatAnalyze(10);
    }

    @Test
    public void analyze() throws NoSuchMethodException, InstantiationException,
            IllegalAccessException, InvocationTargetException, NoSuchFieldException, EmptyArrayException {
        analyzer.analyze(100);
    }

    @Test(timeout = 2000)
    public void testAnalyzeExecution() throws NoSuchMethodException, InstantiationException,
            IllegalAccessException, InvocationTargetException, NoSuchFieldException, EmptyArrayException {
        analyzer.analyze(3);
    }

    @Test(expected = InvocationTargetException.class)
    public void testAnalyzeException() throws NoSuchMethodException, InstantiationException,
            IllegalAccessException, InvocationTargetException, NoSuchFieldException, EmptyArrayException {
        analyzer.analyze(0);
    }

    @Test(timeout = 20000)
    public void testRepeatAnalyzeExecution() throws NoSuchMethodException, InstantiationException,
            IllegalAccessException, InvocationTargetException, NoSuchFieldException, WrongRepeatAmount,
            EmptyArrayException {
        analyzer.repeatAnalyze(10);
    }

    @Test(expected = WrongRepeatAmount.class)
    public void testRepeatAnalyzeException() throws NoSuchMethodException, InstantiationException,
            IllegalAccessException, InvocationTargetException, NoSuchFieldException, WrongRepeatAmount,
            EmptyArrayException {
        analyzer.repeatAnalyze(-1);
    }
}