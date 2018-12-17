package analyzer;

import annotations.FillerAnnotation;
import annotations.MergeSortAnnotation;
import annotations.SortAnnotation;
import exceptions.EmptyArrayException;
import fillers.Fillers;
import org.apache.commons.lang3.reflect.MethodUtils;
import org.apache.poi.ss.usermodel.charts.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;
import org.apache.poi.xssf.usermodel.charts.XSSFChartLegend;
import sorters.AbstractSorter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

import static analyzer.AnalyzeUtils.getMergeSortClass;
import static analyzer.AnalyzeUtils.getSortsClasses;


public class ExcelAnalyzer extends Analyzer{
    private String excelFileName;
    private XSSFWorkbook workbook = createWorkbook();
    private int[] arraySizes;

    public ExcelAnalyzer(String excelFileName) {
        this.excelFileName = excelFileName;
    }

    @Override
    public void analyze(int arraysAmount) throws IOException, NoSuchMethodException, InstantiationException,
            IllegalAccessException, InvocationTargetException, EmptyArrayException, NoSuchFieldException {

        arraySizes = generateArraySizes(arraysAmount);
        Method[] fillers = AnalyzeUtils.getFillers();

        for (Method filler : fillers) {
            XSSFSheet sheet = workbook.getSheet(getFillerName(filler));

            Map<String, Long[]> map = getSortsStatistic(filler);

            Set<Map.Entry<String, Long[]>> entries = map.entrySet();

            for (Map.Entry<String, Long[]> entry : entries) {
                System.out.println(entry.getKey() + " " + Arrays.toString(entry.getValue()));
            }

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

    private XSSFWorkbook createWorkbook() {
        workbook = new XSSFWorkbook();

        Method[] fillers = AnalyzeUtils.getFillers();

        for (Method filler : fillers) {
            workbook.createSheet(filler.getAnnotation(FillerAnnotation.class).nameOfFiller());
        }

        return workbook;
    }

    private String getFillerName(Method filler) {
        return filler.getAnnotation(FillerAnnotation.class).nameOfFiller();
    }

    private void writeAndClose() throws IOException {
        workbook.write(new FileOutputStream(excelFileName));
        workbook.close();
    }

    private Map<String, Long[]> getSortsStatistic(Method filler) throws NoSuchMethodException, InstantiationException,
            IllegalAccessException, InvocationTargetException, EmptyArrayException, NoSuchFieldException {
        Map<String, Long[]> map = new TreeMap<String, Long[]>();

        Set<Class<? extends AbstractSorter>> subTypesSet = AnalyzeUtils.getSubTypes();

        ArrayList<Class<? extends AbstractSorter>> sortsArrayList = getSortsClasses(subTypesSet);

        ArrayList<AbstractSorter> sortsObjects = getSortsObjects(sortsArrayList);

        Class<? extends AbstractSorter> mergeSortClass = getMergeSortClass(subTypesSet);
        Constructor<? extends AbstractSorter> constructor = getMergeSortConstructor(mergeSortClass);

        Long performance;
        Long start;
        Long finish;

        for (AbstractSorter sorter : sortsObjects) {
            String name = sorter.getClass().getAnnotation(SortAnnotation.class).nameOfSort();
            Long[] measures = new Long[arraySizes.length];

            for (int i = 0; i < arraySizes.length; i++) {
                int[] array = getArray(filler, arraySizes[i]);

                start = System.nanoTime();
                sorter.sort(array);
                finish = System.nanoTime();

                performance = finish - start;

                measures[i] = performance;
            }

            map.put(name, measures);
        }

        for (AbstractSorter sorter : sortsObjects) {
            AbstractSorter mergeSort = constructor.newInstance(sorter);
            Long[] measures = new Long[arraySizes.length];

            for (int i = 0; i < arraySizes.length; i++) {
                int[] array = getArray(filler, arraySizes[i]);

                start = System.nanoTime();
                mergeSort.sort(array);
                finish = System.nanoTime();

                performance = finish - start;

                measures[i] = performance;
            }

            String name = mergeSort.getClass().getAnnotation(MergeSortAnnotation.class).name() + " " + "with" + " " +
                    getMergeSortParam(mergeSort);

            map.put(name, measures);
        }

        return map;
    }


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

    private static Constructor<? extends AbstractSorter> getMergeSortConstructor(Class<? extends AbstractSorter> clazz)
            throws NoSuchMethodException {
        return clazz.getConstructor(AbstractSorter.class);
    }

    private static String getMergeSortParam(AbstractSorter object) throws NoSuchFieldException, IllegalAccessException {
        Field field = object.getClass().getDeclaredField("sortTypeForMergeSort");
        field.setAccessible(true);

        AbstractSorter fieldValue = (AbstractSorter) field.get(object);

        SortAnnotation annotation = fieldValue.getClass().getAnnotation(SortAnnotation.class);

        return annotation.nameOfSort();
    }

    private static int[] getArray(Method method, int arraySize) throws NoSuchMethodException, IllegalAccessException,
            InvocationTargetException {
        Object array = MethodUtils.invokeStaticMethod(Fillers.class, method.getName(), arraySize);
        return (int[]) array;
    }

    private static int[] generateArraySizes(int amount) {
        Random random = new Random();

        int[] array = new int[amount];

        int randomNumber = (random.nextInt(100) + 1);
        array[0] = (random.nextInt(200 + 1 - 50) - 50);

        for (int i = 1; i < array.length; i++) {
            array[i] = array[i - 1] + randomNumber;
        }

        return array;
    }
}
