package sorters;

/**
 * Interface <b>Sort</b> presents template for different sorting types.
 */
public interface Sort {
    /**
     * Method <b>sort()</b> must be overrided in classes that implement <b>Sort</b> interface.
     * This method takes one parameter @param array. It is array of integers, which will be sorted.
     */
    void sort(int[] array);

    /**
     * @return name of sort.
     */
    String getSortName();
}
