package analyzer;

import fillers.Filler;
import sorters.Sort;

/**
 * <b>SortAnalyzer</b> class that implements {@link Analyzer} interface.
 * This class presents four overrided methods for performance measuring for
 * different filling ways.
 * All methods use {@link System#nanoTime()} to measure time.
 * @author Dmytro Pylypyuk.
 * @version 1.0
 */
public class SortAnalyzer implements Analyzer {
    /**
     * <b>private Filler</b> field.
     */
    private Filler filler;

    /**
     * Constructor takes and initializes parameter @param filler.
     */
    public SortAnalyzer(Filler filler){
        this.filler = filler;
    }

    /**
     * Overrided version of {@link Analyzer#getPerformanceForSortedArray(Sort)} method.
     * Method measures performance for @param sortType type of sort when array is sorted.
     * @return time in nanoseconds.
     */
    @Override
    public long getPerformanceForSortedArray(Sort sortType) {
        long startTime = System.nanoTime();
        sortType.sort(filler.generateSortedArray());
        long stopTime = System.nanoTime();

        return stopTime - startTime;
    }

    /**
     * Overrided version of {@link Analyzer#getPerformanceForArrayWithAddedElement(Sort)} method.
     * Method measures performance for @param sortType type of sort when array is sorted and have
     * added element in the end.
     * @return time in nanoseconds.
     */
    @Override
    public long getPerformanceForArrayWithAddedElement(Sort sortType) {
        long startTime = System.nanoTime();
        sortType.sort(filler.generateSortedArrayWithAddedElement());
        long stopTime = System.nanoTime();

        return stopTime - startTime;
    }

    /**
     * Overrided version of {@link Analyzer#getPerformanceForReversedArray(Sort)} method.
     * Method measures performance for @param sortType type of sort when array is sorted and reversed.
     * @return time in nanoseconds.
     */
    @Override
    public long getPerformanceForReversedArray(Sort sortType) {
        long startTime = System.nanoTime();
        sortType.sort(filler.generateReversedArray());
        long stopTime = System.nanoTime();

        return stopTime - startTime;
    }

    /**
     * Overrided version of {@link Analyzer#getPerformanceForRandomArray(Sort)} method.
     * Method measures performance for @param sortType type of sort when array is full of random elements.
     * @return time in nanoseconds.
     */
    @Override
    public long getPerformanceForRandomArray(Sort sortType) {
        long startTime = System.nanoTime();
        sortType.sort(filler.generateRandomArray());
        long stopTime = System.nanoTime();

        return stopTime - startTime;
    }
}
