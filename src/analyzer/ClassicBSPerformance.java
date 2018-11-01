package analyzer;

import fillers.Fillers;
import sorters.BubbleSortClassic;

public final class ClassicBSPerformance implements Analyzer {
    private BubbleSortClassic bubbleSortClassic;
    private Fillers filler;

    public ClassicBSPerformance(BubbleSortClassic bubbleSortClassic, Fillers filler){
        this.bubbleSortClassic = bubbleSortClassic;
        this.filler = filler;
    }

    @Override
    public long getPerformanceForSortedArray() {
        long startTime = System.nanoTime();
        bubbleSortClassic.sort(filler.generateSortedArray());
        long finishTime = System.nanoTime();

        return finishTime - startTime;
    }

    @Override
    public long getPerformanceForArrayWithAddedElement() {
        long startTime = System.nanoTime();
        bubbleSortClassic.sort(filler.generateSortedArrayWithAddedElement());
        long finishTime = System.nanoTime();

        return finishTime - startTime;
    }

    @Override
    public long getPerformanceForReversedArray() {
        long startTime = System.nanoTime();
        bubbleSortClassic.sort(filler.generateReversedArray());
        long finishTime = System.nanoTime();

        return finishTime - startTime;
    }

    @Override
    public long getPerformanceForRandomArray() {
        long startTime = System.nanoTime();
        bubbleSortClassic.sort(filler.generateRandomArray());
        long finishTime = System.nanoTime();

        return finishTime - startTime;
    }
}
