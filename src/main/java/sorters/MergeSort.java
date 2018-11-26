package sorters;

@MergeSortAnnotation
public final class MergeSort extends AbstractSorter {
    private AbstractSorter sortTypeForMergeSort;

    public MergeSort(AbstractSorter sortTypeForMergeSort) {
        this.sortTypeForMergeSort = sortTypeForMergeSort;
    }

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
        } else {
            sortTypeForMergeSort.sort(array);
        }
    }

    private boolean getDivideCriterion(int[] array) {
        return array.length > 4;
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
        } else return array;
    }

    private void merge(int[] array, int[] firstPart, int[] secondPart) {
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

    public AbstractSorter getSortTypeForMergeSort() {
        return sortTypeForMergeSort;
    }
}
