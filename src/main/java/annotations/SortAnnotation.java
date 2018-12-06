package annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation <b>SortAnnotation</b> is used to mark different types of sorts.
 * Should not be applied for MergeSort.
 *
 * @author Dmytro Pylypiuk
 * @version 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface SortAnnotation {
    /**
     * @return sort`s name.
     */
    String nameOfSort();
}
