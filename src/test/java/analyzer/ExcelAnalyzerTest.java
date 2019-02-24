package analyzer;

import exceptions.EmptyArrayException;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import static org.junit.Assert.*;

public class ExcelAnalyzerTest {
    private ExcelAnalyzer analyzer;

    @Before
    public void setUp() {
        analyzer = new ExcelAnalyzer("test.xlsx");
    }

    @Test
    public void analyze() throws IOException, NoSuchMethodException, InstantiationException,
            IllegalAccessException, InvocationTargetException, EmptyArrayException, NoSuchFieldException {
        analyzer.analyze(5);
    }

    @Test(timeout = 20000)
    public void testAnalyzeExecution() throws IOException, NoSuchMethodException, InstantiationException,
            IllegalAccessException, InvocationTargetException, EmptyArrayException, NoSuchFieldException {
        analyzer.analyze(10);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testExpectedException() throws IOException, NoSuchMethodException, InstantiationException,
            IllegalAccessException, InvocationTargetException, EmptyArrayException, NoSuchFieldException {
        analyzer.analyze(0);
    }

    @Test(expected = NegativeArraySizeException.class)
    public void testArraySizeException() throws IOException, NoSuchMethodException, InstantiationException,
            IllegalAccessException, InvocationTargetException, EmptyArrayException, NoSuchFieldException {
        analyzer.analyze(-1);
    }
}