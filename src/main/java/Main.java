import analyzer.ExcelAnalyzer;

public class Main {
    public static void main(String[] args) {
        ExcelAnalyzer excelAnalyzer = new ExcelAnalyzer("report.xlsx");

        try {
            excelAnalyzer.analyze(10);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
