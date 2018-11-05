package output;

import analyzer.Analyzer;
import sorters.*;

/**
 * <b>PerformanceOutputer</b> class that implements {@link Outputer} interface.
 * This class presents overrided method for information displaying.
 * @author Dmytro Pylypyuk.
 * @version 1.2
 */
public class PerformanceOutput implements Outputer {
    /**
     * <b>private Analyzer</b> field.
     */
    private Analyzer sortAnalyzer;

    /**
     * Constructor takes and initialize parameter @param sortAnalyzer.
     */
    public PerformanceOutput(Analyzer sortAnalyzer){
        this.sortAnalyzer = sortAnalyzer;
    }

    /**
     * Method checks type of sort @param sortType and display information.
     * Invokes auxiliary method {@link PerformanceOutput#getPerformanceInfo(Sort)}.
     */
    @Override
    public void output(Sort sortType) {
      if (sortType instanceof MergeSort){
          var sortName = ((MergeSort)sortType).getSortTypeForMergeSort().getClass().getName();
          System.out.println("Performance of" + " " + sortType.getSortName() + " " + "with" + " " + sortName);
          getPerformanceInfo(sortType);
      }else {
          System.out.println("Performance of" + " " + sortType.getSortName());
          getPerformanceInfo(sortType);
      }
    }

    /**
     * Auxiliary method for {@link PerformanceOutput#output(Sort)} method.
     * Takes parameter @param sortType to determine what info should be displayed.
     */
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
