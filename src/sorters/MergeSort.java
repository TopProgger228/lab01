package sorters;

/**
 * <b>MergeSort</b> class that implements {@link sorters.Sort} interface.
 * This class presents merge sort realization with half division and different sorts
 * types for subarrays. This types presented in classes {@link BubbleSortClassic},
 * {@link BubbleSortReversed}, {@link QuickSort}, {@link BuiltInSort}.
 * Class is not declared as final for possible extension.
 * @author Dmytro Pylypyuk.
 * @version 1.3
 */
public class MergeSort implements Sort {
    /**
     * <b>private Sort</b> {@link MergeSort#sortTypeForMergeSort} field determines sort type that will be
     * invoked to sort subarrays.
     */
    private Sort sortTypeForMergeSort;

    /**
     *Constructor takes and initializes parameter @param sortTypeForMergeSort.
     */
    public MergeSort(Sort sortTypeForMergeSort){
        this.sortTypeForMergeSort = sortTypeForMergeSort;
    }

    /**
     * Overrided version of {@link sorters.Sort#sort(int[])} method.
     * Takes @param array, array of integers, which user want to sort.
     */
    @Override
    public void sort(int[] array) {
        if (getDivideCriterion(array)) {
            int[] firstPart;
            int[] secondPart;

            firstPart = divideArray(array, 1);
            secondPart = divideArray(array, 2);

            sort(firstPart);
            sort(secondPart);

            merge(array, firstPart, secondPart);
        }
        else
        {
            sortTypeForMergeSort.sort(array);
        }
    }
    /**
     * @return name of sort.
     */
    @Override
    public String getSortName() {
        return "Merge sort";
    }

    /**
     * Auxiliary method that checks possibility of array division.
     * Takes @param array, array of integers, which user want to sort.
     * @return <b>true</b> if division is possible, else return <b>false</b>.
     */
    private boolean getDivideCriterion(int[] array){
        return array.length > 4;
    }

    /**
     * Auxiliary method for array divison.
     * Takes @param array, array of integers, which user want to sort.
     * Method use parameter @param partToReturn to choose part of divided array. Should equals 1 or 2.
     * If @param partToReturn not equals 1 or 2 method returns array back.
     * @return part of divided array.
     */
    private int[] divideArray(int[] array, int partToReturn){
       int middle;
       int[] firstPart;
       int[] secondPart;

        if (array.length % 2 == 0) {
            middle = array.length / 2;
            firstPart = new int[middle];
            secondPart = new int[middle];
        } else {
            middle = (array.length - 1) / 2;
            firstPart = new int[middle];
            secondPart = new int[middle + 1];
        }

        for (int i = 0; i < firstPart.length; i++) {
            firstPart[i] = array[i];
        }

        for (int i = firstPart.length; i < array.length; i++) {
            secondPart[i - firstPart.length] = array[i];
        }

        if (partToReturn == 1){
            return firstPart;
        }else if (partToReturn == 2){
            return secondPart;
        }else return array;
    }

    /**
     * Auxiliary method for array`s parts merging.
     * Takes @param array, array of integers, which user want to sort.
     * Parameter @param firstPart is first part of array.
     * Parameter @param secondPart is second part of array.
     */
    private void merge(int[] array, int[] firstPart, int[] secondPart){
        int i = 0;
        int j = 0;

        for (int k = 0; k < array.length; k++) {
            if (i > firstPart.length - 1) {
                int a = secondPart[j];
                array[k] = a;
                j++;
            } else if (j > secondPart.length - 1) {
                int a = firstPart[i];
                array[k] = a;
                i++;
            } else if (firstPart[i] < secondPart[j]) {
                int a = firstPart[i];
                array[k] = a;
                i++;
            } else {
                int b = secondPart[j];
                array[k] = b;
                j++;
            }
        }
    }

    /**
     * Getter method.
     * @return type of sort for subarrays.
     */
    public Sort getSortTypeForMergeSort() {
        return sortTypeForMergeSort;
    }
}
