package analyzer;

import sorters.Sort;

/**
 * Interface <b>Analyzer</b> declares four methods for performance measuring.
 */
public interface Analyzer {
    /**
     * Method <b>getPerformanceForSortedArray()</b> must be overrided in classes
     * that implement <b>Analyzer</b> interface.
     * Parameter @param sortType defines type of sort.
     *
     * @return time in nanoseconds.
     */
    long getPerformanceForSortedArray(Sort sortType);

    /**
     * Method <b>getPerformanceForArrayWithAddedElement()</b> must be overrided in classes
     * that implement <b>Analyzer</b> interface.
     * Parameter @param sortType defines type of sort.
     *
     * @return time in nanoseconds.
     */
    long getPerformanceForArrayWithAddedElement(Sort sortType);

    /**
     * Method <b>getPerformanceForReversedArray()</b> must be overrided in classes
     * that implement <b>Analyzer</b> interface.
     * Parameter @param sortType defines type of sort.
     *
     * @return time in nanoseconds.
     */
    long getPerformanceForReversedArray(Sort sortType);

    /**
     * Method <b>getPerformanceForRandomArray()</b> must be overrided in classes
     * that implement <b>Analyzer</b> interface.
     * Parameter @param sortType defines type of sort.
     *
     * @return time in nanoseconds.
     */
    long getPerformanceForRandomArray(Sort sortType);
}
