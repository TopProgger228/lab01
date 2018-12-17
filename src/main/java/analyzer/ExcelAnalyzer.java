package analyzer;

import annotations.*;
import exceptions.EmptyArrayException;
import fillers.Fillers;
import org.apache.commons.lang3.reflect.MethodUtils;
import org.apache.poi.ss.usermodel.charts.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;
import org.apache.poi.xssf.usermodel.charts.XSSFChartLegend;
import sorters.AbstractSorter;

import java.io.*;
import java.lang.reflect.*;
import java.util.*;

import static analyzer.AnalyzeUtils.getMergeSortClass;
import static analyzer.AnalyzeUtils.getSortsClasses;

/**
 * Class <b>ExcelAnalyzer</b> contains features for sorts analysis. It provides methods that create
 * <br>report about analysis in <b>xlsx</b> format.</br>
 *
 * @author Dmytro Pylypyuk
 * @version 1.1
 */
public class ExcelAnalyzer extends Analyzer{
    private String excelFileName;
    private XSSFWorkbook workbook = createWorkbook();
    private int[] arraySizes;

    /**
     * Standard construtor that initialize ExcelAnalyzer object.
     * @param excelFileName report file name.
     */
    public ExcelAnalyzer(String excelFileName) {
        this.excelFileName = excelFileName;
    }

    /**
     * Overrided version of {@link Analyzer#analyze(int)} methods. This method do analysis and create excel report.
     * @param arraysAmount amount of array sizes that will be generated.
     * @throws IOException read {@link IOException}
     * @throws NoSuchMethodException read {@link NoSuchMethodException}
     * @throws InstantiationException read {@link InstantiationException}
     * @throws IllegalAccessException read {@link IllegalAccessException}
     * @throws InvocationTargetException read {@link InvocationTargetException}
     * @throws EmptyArrayException read {@link EmptyArrayException}
     * @throws NoSuchFieldException read {@link NoSuchFieldException}
     */
    @Override
    public void analyze(int arraysAmount) throws IOException, NoSuchMethodException, InstantiationException,
            IllegalAccessException, InvocationTargetException, EmptyArrayException, NoSuchFieldException {

        arraySizes = generateArraySizes(arraysAmount);
        Method[] fillers = AnalyzeUtils.getFillers();

        for (Method filler : fillers) {
            XSSFSheet sheet = workbook.getSheet(getFillerName(filler));

            Map<String, Long[]> map = getSortsStatistic(filler);

            Set<String> keysSet = map.keySet();
            ArrayList<String> keysArray = new ArrayList<String>(keysSet);

            ArrayList<Long[]> valuesArray = new ArrayList<Long[]>();

            XSSFRow titleRow = sheet.createRow(0);
            titleRow.createCell(0).setCellValue("Sizes");

            for (int i = 1; i <= keysArray.size(); i++) {
                XSSFCell titleCell = titleRow.createCell(i);
                titleCell.setCellValue(keysArray.get(i - 1));

                valuesArray.add(map.get(keysArray.get(i - 1)));
            }

            for (int i = 0; i < arraySizes.length; i++) {
                XSSFRow row = sheet.createRow(i + 1);

                row.createCell(0).setCellValue(arraySizes[i]);

                for (int j = 0; j < valuesArray.size(); j++) {
                    XSSFCell cell = row.createCell(j + 1);
                    cell.setCellValue(valuesArray.get(j)[i]);
                }
            }

            createChart(sheet);

            for (int i = 0; i <= keysArray.size(); i++) {
                sheet.autoSizeColumn(i);
            }
        }

        writeAndClose();
    }

    /**
     * Initialize workbook object and create sheets in this workbook.
     * @return workbook object.
     */
    private XSSFWorkbook createWorkbook() {
        workbook = new XSSFWorkbook();

        Method[] fillers = AnalyzeUtils.getFillers();

        for (Method filler : fillers) {
            workbook.createSheet(filler.getAnnotation(FillerAnnotation.class).nameOfFiller());
        }

        return workbook;
    }

    /**
     * Method returns name of filler method.
     * @param filler reference on filler object.
     * @return filler`s name.
     */
    private String getFillerName(Method filler) {
        return filler.getAnnotation(FillerAnnotation.class).nameOfFiller();
    }

    /**
     * Write data in excel file and close resources.
     * @throws IOException read {@link IOException}
     */
    private void writeAndClose() throws IOException {
        workbook.write(new FileOutputStream(excelFileName));
        workbook.close();
    }

    /**
     * Method analyze information about sort and group it into map.
     * @param filler filler that will fill arrays.
     * @return map where keys are names of sorts and values are arrays of performance for differrent sizes.
     * @throws NoSuchMethodException read {@link NoSuchMethodException}
     * @throws InstantiationException read {@link InstantiationException}
     * @throws IllegalAccessException read {@link IllegalAccessException}
     * @throws InvocationTargetException read {@link InvocationTargetException}
     * @throws EmptyArrayException when sort takes empty array.
     * @throws NoSuchFieldException read {@link NoSuchFieldException}
     */
    private Map<String, Long[]> getSortsStatistic(Method filler) throws NoSuchMethodException, InstantiationException,
            IllegalAccessException, InvocationTargetException, EmptyArrayException, NoSuchFieldException {
        Map<String, Long[]> map = new TreeMap<String, Long[]>();

        Set<Class<? extends AbstractSorter>> subTypesSet = AnalyzeUtils.getSubTypes();

        ArrayList<Class<? extends AbstractSorter>> sortsArrayList = getSortsClasses(subTypesSet);

        ArrayList<AbstractSorter> sortsObjects = getSortsObjects(sortsArrayList);

        Class<? extends AbstractSorter> mergeSortClass = getMergeSortClass(subTypesSet);
        Constructor<? extends AbstractSorter> constructor = getMergeSortConstructor(mergeSortClass);

        Long performance;
        long start;

        for (AbstractSorter sorter : sortsObjects) {
            String name = sorter.getClass().getAnnotation(SortAnnotation.class).nameOfSort();
            Long[] measures = new Long[arraySizes.length];

            for (int i = 0; i < arraySizes.length; i++) {
                int[] array = getArray(filler, arraySizes[i]);

                start = System.nanoTime();
                sorter.sort(array);
                performance = System.nanoTime() - start;

                measures[i] = performance;
            }

            map.put(name, measures);

            AbstractSorter mergeSort = constructor.newInstance(sorter);

            for (int i = 0; i < arraySizes.length; i++) {
                int[] array = getArray(filler, arraySizes[i]);

                start = System.nanoTime();
                mergeSort.sort(array);
                performance = System.nanoTime() - start;

                measures[i] = performance;
            }

            String mergeName = mergeSort.getClass().getAnnotation(MergeSortAnnotation.class).name() + " " + "with" + " " +
                    getMergeSortParam(mergeSort);

            map.put(mergeName, measures);
        }
        return map;
    }

    /**
     * This method creates chart which shows dependence of time on elements of the array.
     * @param sheet sheet in workbook where chart will be created.
     */
    private void createChart(XSSFSheet sheet) {
        XSSFDrawing drawing = sheet.createDrawingPatriarch();
        XSSFClientAnchor anchor = drawing.createAnchor(0, 0, 0, 0, 0,
                arraySizes.length + 1, 15, arraySizes.length + 20);

        XSSFChart chart = drawing.createChart(anchor);
        XSSFChartLegend legend = chart.getOrCreateLegend();
        legend.setPosition(LegendPosition.BOTTOM);

        LineChartData data = chart.getChartDataFactory().createLineChartData();
        ChartAxis bottomAxis = chart.getChartAxisFactory().createCategoryAxis(AxisPosition.BOTTOM);
        ValueAxis leftAxis = chart.getChartAxisFactory().createValueAxis(AxisPosition.LEFT);
        leftAxis.setCrosses(AxisCrosses.AUTO_ZERO);

        ChartDataSource<Number> sizesDataSource = DataSources.fromNumericCellRange(sheet,
                new CellRangeAddress(1, arraySizes.length, 0, 0));

        ArrayList<ChartDataSource<Number>> chartDataSources = new ArrayList<ChartDataSource<Number>>();
        ArrayList<String> titles = new ArrayList<String>();

        for (int i = 1; i < sheet.getRow(0).getLastCellNum(); i++) {
            chartDataSources.add(DataSources.fromNumericCellRange(sheet, new CellRangeAddress(1,
                    arraySizes.length, i, i)));

            titles.add(sheet.getRow(0).getCell(i).getStringCellValue());
        }

        for (int i = 0; i < chartDataSources.size(); i++) {
            data.addSeries(sizesDataSource, chartDataSources.get(i)).setTitle(titles.get(i));
        }

        ChartAxis[] axes = {bottomAxis, leftAxis};
        chart.plot(data, axes);
    }

    /**
     * Method creates array of sorts objects and return it.
     * @param sortClasses array list of sorts classes.
     * @return array list of sorts objects.
     * @throws NoSuchMethodException read {@link NoSuchMethodException}
     * @throws InstantiationException read {@link InstantiationException}
     * @throws IllegalAccessException read {@link IllegalAccessException}
     * @throws InvocationTargetException read {@link InvocationTargetException}
     */
    private static ArrayList<AbstractSorter> getSortsObjects(ArrayList<Class<? extends AbstractSorter>> sortClasses)
            throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {

        ArrayList<AbstractSorter> sortsObjects = new ArrayList<AbstractSorter>();

        for (Class<? extends AbstractSorter> clazz : sortClasses) {
            Constructor<? extends AbstractSorter> constructor = clazz.getConstructor();
            AbstractSorter object = constructor.newInstance();

            sortsObjects.add(object);
        }

        return sortsObjects;
    }

    /**
     * Method gets constructor for merge sort object.
     * @param clazz class object of merge sort.
     * @return MergeSort constructor.
     * @throws NoSuchMethodException read {@link NoSuchMethodException}
     */
    private static Constructor<? extends AbstractSorter> getMergeSortConstructor(Class<? extends AbstractSorter> clazz)
            throws NoSuchMethodException {
        return clazz.getConstructor(AbstractSorter.class);
    }

    /**
     * Method gets private parameter of merge sort which presents type of subsort.
     * @param object merge sort object.
     * @return name of sort.
     * @throws NoSuchFieldException read {@link NoSuchFieldException}
     * @throws IllegalAccessException read {@link IllegalAccessException}
     */
    private static String getMergeSortParam(AbstractSorter object) throws NoSuchFieldException, IllegalAccessException {
        Field field = object.getClass().getDeclaredField("sortTypeForMergeSort");
        field.setAccessible(true);

        AbstractSorter fieldValue = (AbstractSorter) field.get(object);

        SortAnnotation annotation = fieldValue.getClass().getAnnotation(SortAnnotation.class);

        return annotation.nameOfSort();
    }

    /**
     * Method initialize array using taken filler and array size.
     * @param method filler.
     * @param arraySize size of array.
     * @return array of integers.
     * @throws NoSuchMethodException read {@link NoSuchMethodException}
     * @throws IllegalAccessException read {@link IllegalAccessException}
     * @throws InvocationTargetException read {@link InvocationTargetException}
     */
    private static int[] getArray(Method method, int arraySize) throws NoSuchMethodException, IllegalAccessException,
            InvocationTargetException {
        Object array = MethodUtils.invokeStaticMethod(Fillers.class, method.getName(), arraySize);
        return (int[]) array;
    }

    /**
     * Method generate array of array sizes.
     * @param amount amount of array sizes.
     * @return array of sizes.
     */
    private static int[] generateArraySizes(int amount) {
        Random random = new Random();

        int[] array = new int[amount];

        int randomNumber = (random.nextInt(100) + 1);
        array[0] = (random.nextInt(200) + 1);

        for (int i = 1; i < array.length; i++) {
            array[i] = array[i - 1] + randomNumber;
        }

        return array;
    }
}
