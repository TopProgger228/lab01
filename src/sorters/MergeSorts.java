package sorters;

public final class MergeSorts implements MergeSort {
    @Override
    public void mergeSort(int[] array, Sort sortType) {
        int middle;
        int[] firstPart;
        int[] secondPart;

        if (array.length % 2 == 0){
            middle = array.length / 2;
            firstPart = new int[middle];
            secondPart = new int[middle];
        }else {
            middle = (array.length - 1) / 2;
            firstPart = new int[middle];
            secondPart = new int[middle + 1];
        }

        for (int i = 0; i < firstPart.length; i++){
            firstPart[i] = array[i];
        }

        for (int i = firstPart.length; i < array.length; i++){
            secondPart[i - firstPart.length] = array[i];
        }

        if (sortType instanceof BubbleSortClassic){
            sortType.sort(firstPart);
            sortType.sort(secondPart);
        }else if (sortType instanceof BubbleSortReversed){
            sortType.sort(firstPart);
            sortType.sort(secondPart);
        }else if (sortType instanceof QuickSort){
            sortType.sort(firstPart);
            sortType.sort(secondPart);
        }else if (sortType instanceof BuiltInSort){
            sortType.sort(firstPart);
            sortType.sort(secondPart);
        }

        int i = 0;
        int j = 0;

        for (int k = 0; k < array.length; k++){
            if (i > firstPart.length-1) {
                int a = secondPart[j];
                array[k] = a;
                j++;
            }
            else if (j > secondPart.length-1) {
                int a = firstPart[i];
                array[k] = a;
                i++;
            }
            else if (firstPart[i] < secondPart[j]) {
                int a = firstPart[i];
                array[k] = a;
                i++;
            }
            else {
                int b = secondPart[j];
                array[k] = b;
                j++;
            }
        }
    }
}
