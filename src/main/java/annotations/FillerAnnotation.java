package annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation <b>FillerAnnotation</b> used to mark fillers.<br>This annotation helps to identify methods in runtime.</br>
 *
 * @author Dmytro Pylypiuk
 * @version 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface FillerAnnotation {
    /**
     * Filler name parameter.
     *
     * @return filler`s method name.
     */
    String nameOfFiller();
}
