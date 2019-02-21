package sorters;

import annotations.MergeSortAnnotation;
import annotations.SortAnnotation;
import exceptions.EmptyArrayException;


/**
 * <b>MergeSort</b> class that extends {@link sorters.AbstractSorter} class.
 * <br>This class presents merge sort realization with half division and different sorts
 * types for subarrays.</br> <br>This types presented in classes {@link BubbleSortClassic},
 * {@link BubbleSortReversed}, {@link QuickSort}, {@link BuiltInSort}.</br>
 * <br>Class is not declared as final for possible extension.</br>
 * <br>Class is annotated by {@link SortAnnotation} annotation.</br>
 *
 * @author Dmytro Pylypyuk.
 * @version 1.3
 */
@MergeSortAnnotation(name = "Merge sort")
public final class MergeSort extends AbstractSorter {
    private AbstractSorter sortTypeForMergeSort;

    /**
     * @param sortTypeForMergeSort determines sort type that will be invoked
     *                             to subarrays.
     */
    public MergeSort(AbstractSorter sortTypeForMergeSort) {
        this.sortTypeForMergeSort = sortTypeForMergeSort;
    }

    /**
     * Overrided version of {@link sorters.AbstractSorter#sort(int[])} method.
     * Takes @param array, array of integers, which user want to sort.
     */

    @Override
    public void sort(int[] array) throws EmptyArrayException {
        MultiThreadedMergeSort multiThreadedMergeSort = new MultiThreadedMergeSort(array);
        multiThreadedMergeSort.start();

        try {
            multiThreadedMergeSort.join();
        }catch (InterruptedException ex){
            ex.printStackTrace();
        }
    }

    class MultiThreadedMergeSort extends Thread{
        private final int AVAILABLE_PROCESSORS = Runtime.getRuntime().availableProcessors();
        private int[] array;
        private int[] resultArray;

        MultiThreadedMergeSort(int[] array){
            this.array = array;
        }

        @Override
        public void run() {
            if (array.length <= 1) {
                resultArray = array;
            } else {
                int[] firstPart = divideArray(array, 1);
                int[] secondPart = divideArray(array, 2);

                if (activeCount() < AVAILABLE_PROCESSORS) {
                    MultiThreadedMergeSort firstThread = new MultiThreadedMergeSort(firstPart);
                    MultiThreadedMergeSort secondThread = new MultiThreadedMergeSort(secondPart);

                    firstThread.start();
                    secondThread.start();

                    try {
                        firstThread.join();
                        secondThread.join();

                        merge(firstPart, secondPart);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }

                } else {
                    try {
                        sortTypeForMergeSort.sort(firstPart);
                        sortTypeForMergeSort.sort(secondPart);
                    }catch (EmptyArrayException ex){
                        ex.printStackTrace();
                    }

                    merge(firstPart, secondPart);
                }
            }
        }

        private int[] divideArray(int[] array, int partToReturn) {
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

            if (partToReturn == 1) {
                return firstPart;
            } else if (partToReturn == 2) {
                return secondPart;
            } else return new int[0];
        }

        private void merge(int[] firstPart, int[] secondPart) {
            resultArray = new int[array.length];

            int i = 0;
            int j = 0;

            for (int k = 0; k < resultArray.length; k++) {
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
    }
}
