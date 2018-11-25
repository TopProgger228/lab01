package sorters;

import java.util.Arrays;

@SortAnnotation
public class BuiltInSort extends AbstractSorter {
    @Override
    public void sort(int[] array) {
        Arrays.sort(array);
    }
}
