package sorters;

import java.util.Arrays;

public final class BuiltInSort implements Sort{
    @Override
    public void sort(int[] array) {
        Arrays.sort(array);
    }
}
