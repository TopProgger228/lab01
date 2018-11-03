package output;

import analyzer.Analyzer;
import sorters.*;

public class PerformanceOutput implements Outputer {
    private Analyzer sortAnalyzer;

    public PerformanceOutput(Analyzer sortAnalyzer){
        this.sortAnalyzer = sortAnalyzer;
    }

    @Override
    public void output(Sort sortType) {
      if (sortType instanceof BubbleSortClassic){
          System.out.println("Performance of classic bubble sort:");
          getPerformanceInfo(sortType);
      }else if (sortType instanceof BubbleSortReversed){
          System.out.println("Performance of reversed bubble sort:");
          getPerformanceInfo(sortType);
      }else if (sortType instanceof QuickSort){
          System.out.println("Performance of quick sort:");
          getPerformanceInfo(sortType);
      }else if (sortType instanceof BuiltInSort){
          System.out.println("Performance of built-in sort:");
          getPerformanceInfo(sortType);
      }else if (sortType instanceof MergeSort){
          var sortName = ((MergeSort)sortType).getSortTypeForMergeSort().getClass().getName();
          System.out.println("Performance of merge sort with " + sortName);
          getPerformanceInfo(sortType);
      }
    }

    private void getPerformanceInfo(Sort sortType){
        System.out.println("Method takes sorted array -->" + " " +
                sortAnalyzer.getPerformanceForSortedArray(sortType) + " " + "nanoseconds");

        System.out.println("Method takes sorted array with added element -->" + " " +
                sortAnalyzer.getPerformanceForArrayWithAddedElement(sortType) + " " + "nanoseconds");

        System.out.println("Method takes reversed array -->" + " " +
                sortAnalyzer.getPerformanceForReversedArray(sortType) + " " + "nanoseconds");

        System.out.println("Method takes random array -->" + " " +
                sortAnalyzer.getPerformanceForRandomArray(sortType) + " " + "nanoseconds");
    }

}
