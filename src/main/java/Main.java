import analyzer.ExcelAnalyzer;

public class Main {
    public static void main(String[] args) {
        ExcelAnalyzer excelAnalyzer = new ExcelAnalyzer("doc.xlsx");

        try {
            excelAnalyzer.analyze(15);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
