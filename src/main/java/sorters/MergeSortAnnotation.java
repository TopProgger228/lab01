package sorters;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <b>MergeSortAnnotation</b> is special annotation, that used to mark Merge sort classes.
 * @author Dmytro Pylypiuk
 * @version 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface MergeSortAnnotation {
    /**
     *
     * @return name of Merge sort.
     */
    String name();
}
