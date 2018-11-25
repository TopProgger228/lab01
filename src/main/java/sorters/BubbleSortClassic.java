package sorters;

@SortAnnotation
public final class BubbleSortClassic extends BubbleSorter {
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
