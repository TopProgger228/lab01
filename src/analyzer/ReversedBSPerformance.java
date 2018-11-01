package analyzer;

import fillers.Fillers;

public class ReversedBSPerformance implements Analyzer {
    private Fillers filler;

    public ReversedBSPerformance(Fillers filler){
        this.filler = filler;
    }

    @Override
    public long getPerformanceForSortedArray() {
        long startTime = System.nanoTime();
        filler.generateSortedArray();
        long finishTime = System.nanoTime();

        return finishTime - startTime;
    }

    @Override
    public long getPerformanceForArrayWithAddedElement() {
        return 0;
    }

    @Override
    public long getPerformanceForReversedArray() {
        return 0;
    }

    @Override
    public long getPerformanceForRandomArray() {
        return 0;
    }
}
