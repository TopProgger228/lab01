import analyzer.SortAnalyzer;
import output.PerformanceOutput;
import sorters.*;
import fillers.*;

public class Main {
    public static void main(String[] args) {
        BubbleSortClassic bubbleSortClassic = new BubbleSortClassic();
        BubbleSortReversed bubbleSortReversed = new BubbleSortReversed();
        QuickSort quickSort = new QuickSort();
        BuiltInSort builtInSort = new BuiltInSort();

        Fillers filler = new Fillers(100);

        SortAnalyzer sortAnalyzer = new SortAnalyzer(filler);

        PerformanceOutput performance = new PerformanceOutput(sortAnalyzer);

        performance.output(bubbleSortClassic);
        performance.output(bubbleSortReversed);
        performance.output(quickSort);
        performance.output(builtInSort);
    }
}
