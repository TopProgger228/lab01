package sorters;

public final class BubbleSortClassic implements Sort {
    @Override
    public void sort(int[] array) {
        for (int i = 0; i < (array.length - 1); i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] > array[j]) {
                    int var = array[i];
                    array[i] = array[j];
                    array[j] = var;
                }
            }
        }
    }
}
