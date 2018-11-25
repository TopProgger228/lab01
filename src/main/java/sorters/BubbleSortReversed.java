package sorters;

@SortAnnotation
public final class BubbleSortReversed extends BubbleSorter {
    @Override
    public void sort(int[] array) {
        for (int i = array.length - 1; i > 0; i--) {
            for (int j = i - 1; j >= 0; j--) {
                if (array[i] < array[j]) {
                    int var = array[i];
                    array[i] = array[j];
                    array[j] = var;
                }
            }
        }
    }
}
