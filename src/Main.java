import analyzer.Analyzer;
import analyzer.SortAnalyzer;
import output.Outputer;
import output.PerformanceOutput;
import sorters.*;
import fillers.*;

public class Main {
    public static void main(String[] args) {
        Sort classicBubbleSort = new BubbleSortClassic();
        Sort reversedBubbleSort = new BubbleSortReversed();
        Sort quickSort = new QuickSort();
        Sort builtInSort = new BuiltInSort();
        Sort mergeSortWithClassicBubbleSort = new MergeSort(classicBubbleSort);
        Sort mergeSortWithReversedBubbleSort = new MergeSort(reversedBubbleSort);
        Sort mergeWithQuickSort = new MergeSort(quickSort);
        Sort mergeWithBuiltInSort = new MergeSort(builtInSort);

        Fillers filler = new Fillers(100);

        Analyzer analyzer = new SortAnalyzer(filler);

        Outputer performance = new PerformanceOutput(analyzer);

        performance.output(classicBubbleSort);
        performance.output(reversedBubbleSort);
        performance.output(quickSort);
        performance.output(builtInSort);
        performance.output(mergeSortWithClassicBubbleSort);
        performance.output(mergeSortWithReversedBubbleSort);
        performance.output(mergeWithQuickSort);
        performance.output(mergeWithBuiltInSort);
    }
}
