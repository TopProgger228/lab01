package analyzer;

import fillers.Fillers;
import sorters.Sort;

public interface Analyzer {
    long getPerformanceForSortedArray(Sort sortType);

    long getPerformanceForArrayWithAddedElement(Sort sortType);

    long getPerformanceForReversedArray(Sort sortType);

    long getPerformanceForRandomArray(Sort sortType);
}
