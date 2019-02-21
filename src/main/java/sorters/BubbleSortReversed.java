package sorters;

import annotations.SortAnnotation;
import exceptions.EmptyArrayException;

/**
 * <b>BubbleSortReversed</b> class that extends {@link sorters.AbstractSorter} class.
 * <br>This class presents bubble sort realization when current element goes down.</br>
 * <br>Class is declared as final.</br>
 * <br>Class annotated by {@link SortAnnotation} annotation.</br>
 *
 * @author Dmytro Pylypiuk.
 * @version 1.1
 */
@SortAnnotation(nameOfSort = "Reversed bubble sort")
public final class BubbleSortReversed extends BubbleSorter {
    @Override
    public void sort(int[] array) throws EmptyArrayException {
        if (array.length > 0) {
            for (int i = getIterForOuterLoop(array.length); checkConditionForOuterLoop(i, array.length);
                 i = geNextIteration(i)) {
                for (int j = getIterForNestedLoop(i); checkConditionForNestedLoop(j); j = geNextIteration(j)) {
                    swap(array, array[j], j, array[i], i);
                }
            }
        } else {
            throw new EmptyArrayException();
        }
    }

    @Override
    int geNextIteration(int iter) {
        return --iter;
    }

    @Override
    int getIterForOuterLoop(int arraySize) {
        return arraySize - 1;
    }

    @Override
    int getIterForNestedLoop(int i) {
        return i - 1;
    }

    @Override
    boolean checkConditionForOuterLoop(int i, int arraySize) {
        if (i > 0){
            return true;
        }else return false;
    }

    @Override
    boolean checkConditionForNestedLoop(int i, int arraySize) {
        return false;
    }

    private boolean checkConditionForNestedLoop(int i){
        if (i >= 0){
            return true;
        }else return false;
    }
}

