package analyzer;

import fillers.Fillers;
import sorters.Sort;

public class SortAnalyzer implements Analyzer {
    private Fillers filler;

    public SortAnalyzer(Fillers filler){
        this.filler = filler;
    }
    @Override
    public long getPerformanceForSortedArray(Sort sortType) {
        long startTime = System.nanoTime();
        sortType.sort(filler.generateSortedArray());
        long stopTime = System.nanoTime();

        return stopTime - startTime;
    }

    @Override
    public long getPerformanceForArrayWithAddedElement(Sort sortType) {
        long startTime = System.nanoTime();
        sortType.sort(filler.generateSortedArrayWithAddedElement());
        long stopTime = System.nanoTime();

        return stopTime - startTime;
    }

    @Override
    public long getPerformanceForReversedArray(Sort sortType) {
        long startTime = System.nanoTime();
        sortType.sort(filler.generateReversedArray());
        long stopTime = System.nanoTime();

        return stopTime - startTime;
    }

    @Override
    public long getPerformanceForRandomArray(Sort sortType) {
        long startTime = System.nanoTime();
        sortType.sort(filler.generateRandomArray());
        long stopTime = System.nanoTime();

        return stopTime - startTime;
    }
}
