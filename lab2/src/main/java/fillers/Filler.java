package fillers;

/**
 * Interface <b>Filler</b> declares methods for different ways to fill arrays.
 */
public interface Filler {
    /**
     * Method <b>generateSortedArray()</b> must be overrided in classes that implement <b>Filler</b> interface.
     *
     * @return array of integers.
     */
    int[] generateSortedArray();

    /**
     * Method <b>generateSortedArrayWithAddedElement()</b> must be overrided in classes
     * that implement <b>Filler</b> interface.
     *
     * @return array of integers.
     */
    int[] generateSortedArrayWithAddedElement();

    /**
     * Method <b>generateReversedArray()</b> must be overrided in classes that implement <b>Filler</b> interface.
     *
     * @return array of integers.
     */
    int[] generateReversedArray();

    /**
     * Method <b>generateRandomArray()</b> must be overrided in classes that implement <b>Filler</b> interface.
     *
     * @return array of integers.
     */
    int[] generateRandomArray();
}
