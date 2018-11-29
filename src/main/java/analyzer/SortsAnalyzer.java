package analyzer;

import sorters.AbstractSorter;
import sorters.SortAnnotation;

import static analyzer.AnalyzeUtils.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Set;

public class SortsAnalyzer extends Analyzer {
    @Override
    public ArrayList<AbstractSorter> getSorts() throws NoSuchMethodException, InstantiationException,
            IllegalAccessException, InvocationTargetException {

        Set<Class<? extends AbstractSorter>> subTypesSet = getSubTypes();
        excludeAbstractClasses(subTypesSet);

        ArrayList<Class<? extends AbstractSorter>> sortsArrayList = getSortsClasses(SortAnnotation.class,
                subTypesSet);

        ArrayList<AbstractSorter> sortsObjects = new ArrayList<AbstractSorter>();

        for (Class<? extends AbstractSorter> clazz : sortsArrayList) {
            Constructor<? extends AbstractSorter> constructor = clazz.getConstructor();
            AbstractSorter object = constructor.newInstance();

            sortsObjects.add(object);
        }

        return sortsObjects;
    }
}
