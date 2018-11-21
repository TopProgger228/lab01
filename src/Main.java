import analyzer.Analyzer;
import analyzer.SortAnalyzer;
import fillers.Filler;
import fillers.Fillers;
import output.Outputer;
import output.PerformanceOutput;
import sorters.*;
import sorters.Sort;

public class Main {
    public static void main(String[] args) {
        Sort classicBubbleSort = new BubbleSortClassic();
        Sort reversedBubbleSort = new BubbleSortReversed();
        Sort quickSort = new QuickSort();
        Sort builtInSort = new BuiltInSort();

        Sort mergeSortWithBS = new MergeSort(classicBubbleSort);
        Sort mergeSortWithRBS = new MergeSort(reversedBubbleSort);
        Sort mergeSortWithQuickSort = new MergeSort(quickSort);
        Sort mergeSortWithBuiltInSort = new MergeSort(builtInSort);

        Filler filler = new Fillers();

        Analyzer analyzer = new SortAnalyzer(filler);

        Outputer performance = new PerformanceOutput(analyzer);

        performance.output(mergeSortWithBS);
        performance.output(mergeSortWithRBS);
        performance.output(mergeSortWithQuickSort);
        performance.output(mergeSortWithBuiltInSort);

        performance.output(classicBubbleSort);
        performance.output(reversedBubbleSort);
        performance.output(quickSort);
        performance.output(builtInSort);
    }
}
