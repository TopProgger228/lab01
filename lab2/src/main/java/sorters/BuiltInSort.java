package sorters;

import java.util.Arrays;

/**
 * <b>BuiltInSort</b> class that implements {@link sorters.Sort} interface.
 * This class presents {@link Sort#sort(int[])} method realization that invokes built-in sort method.
 * Class is not declared as final for possible extension.
 *
 * @author Dmytro Pylypyuk.
 * @version 1.0
 */
public class BuiltInSort implements Sort {
    /**
     * Overrided version of {@link sorters.Sort#sort(int[])} method.
     * This method invokes {@link Arrays#sort(int[])} method from {@link Arrays} class.
     * Takes @param array, array of integers, which user want to sort.
     */

    public void sort(int[] array) {
        Arrays.sort(array);
    }

    /**
     * @return name of sort.
     */

    public String getSortName() {
        return "Built-in sort";
    }
}
