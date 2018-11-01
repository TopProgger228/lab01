import analyzer.ClassicBSPerformance;
import output.ClassicBSOutput;
import sorters.*;
import fillers.*;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        BubbleSortClassic bubbleSortClassic = new BubbleSortClassic();
        Fillers filler = new Fillers(30);
        ClassicBSPerformance performance = new ClassicBSPerformance(bubbleSortClassic, filler);
        ClassicBSOutput outputer = new ClassicBSOutput(performance);
        outputer.output();
    }
}
