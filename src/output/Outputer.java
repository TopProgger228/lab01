package output;

import sorters.Sort;

/**
 * Interface <b>Outputer</b> declares method for information display.
 */
public interface Outputer {
    /**
     * Method <b>output()</b> must be overrided in classes that implement <b>Outputer</b> interface.
     * Takes parameter @param sortType to determine what info should be displayed.
     */
    void output(Sort sortType);
}
