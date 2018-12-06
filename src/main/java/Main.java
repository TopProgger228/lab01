import analyzer.SortAnalyzer;
import exceptions.EmptyArrayException;
import exceptions.WrongArraySize;
import exceptions.WrongRepeatAmount;
import fillers.Fillers;
import sorters.BubbleSortClassic;
import sorters.BubbleSortReversed;
import sorters.BubbleSorter;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws WrongArraySize, EmptyArrayException {
//        SortAnalyzer testAnalyzer = new SortAnalyzer();
//
//        try {
//            testAnalyzer.repeatAnalyze(2);
//        } catch (NoSuchFieldException ex) {
//            ex.printStackTrace();
//        } catch (NoSuchMethodException ex) {
//            ex.printStackTrace();
//        } catch (InstantiationException ex) {
//            ex.printStackTrace();
//        } catch (IllegalAccessException ex) {
//            ex.printStackTrace();
//        } catch (InvocationTargetException ex) {
//            ex.printStackTrace();
//        } catch (WrongRepeatAmount ex) {
//            ex.printStackTrace();
//        } catch (EmptyArrayException ex) {
//            ex.printStackTrace();
//        }

        System.out.println(Arrays.toString(Fillers.generateSortedArray(10)));
    }
}
