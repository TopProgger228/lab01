import analyzer.SortAnalyzer;
import exceptions.EmptyArrayException;
import exceptions.WrongRepeatAmount;

import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args) {
        SortAnalyzer testAnalyzer = new SortAnalyzer();

        try {
            testAnalyzer.repeatAnalyze(2);
        } catch (NoSuchFieldException ex) {
            ex.printStackTrace();
        } catch (NoSuchMethodException ex) {
            ex.printStackTrace();
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (InvocationTargetException ex) {
            ex.printStackTrace();
        } catch (WrongRepeatAmount ex) {
            ex.printStackTrace();
        } catch (EmptyArrayException ex) {
            ex.printStackTrace();
        }
    }
}
