package output;

import analyzer.ClassicBSPerformance;
import fillers.Fillers;
import sorters.BubbleSortClassic;

public class ClassicBSOutput implements Outputer {
    private ClassicBSPerformance performance;

    public ClassicBSOutput(ClassicBSPerformance performance){
        this.performance = performance;
    }
    @Override
    public void output() {
        System.out.println("Performance with sorted array --->" + " "
                + performance.getPerformanceForSortedArray() + " " + "nanoseconds");

        System.out.println("Performance with inserted element --->" + " " +
                + performance.getPerformanceForArrayWithAddedElement() + " " + "nanoseconds");

        System.out.println("Performance with reversed array --->" + " "
        + performance.getPerformanceForReversedArray() + " " + "nanoseconds");

        System.out.println("Performance with random array --->" + " " +
                + performance.getPerformanceForRandomArray() + " " + "nanoseconds");
    }
}
