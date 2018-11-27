package sorters;

import java.util.Arrays;

@SortAnnotation(nameOfSort = "Built-In sort")
public class BuiltInSort extends AbstractSorter {
    @Override
    public void sort(int[] array) {
        Arrays.sort(array);
    }
}
