import analyzer.*;
import exceptions.WrongRepeatAmount;

public class Main {
    public static void main(String[] args) {
        Analyzer analyzer = new SortsAnalyzer();
        Analyzer mergeAnalyzer = new MergeSortsAnalyzer();

        try {
            analyzer.repeatAnalyze(1);
            mergeAnalyzer.repeatAnalyze(1);
        }catch (WrongRepeatAmount ex){
            ex.printStackTrace();
        }catch (Exception ex){
            ex.printStackTrace();
        } finally {
            System.out.println("Well done!");
        }
    }
}
