package com.nsu.ccfit.oop.kiteiru.getresult;

import java.io.PrintWriter;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GetResult {
    public GetResult(PrintWriter writer, final Map<String, Integer> dataContainer, int wordCounter) {
        Comparator<Map.Entry<String, Integer>> comparing = (o1, o2) -> {
            if (o2.getValue() - o1.getValue() != 0) {
                return o2.getValue() - o1.getValue();
            }
            else {
                return o1.getKey().compareTo(o2.getKey());
            }
        };
        List<Map.Entry<String, Integer>> resultList = dataContainer.entrySet().stream().sorted(comparing).collect(Collectors.toList());
        writer.write("Total number of words is: " + wordCounter + "\n\n");
        writer.printf("unique.word      word.freq  freq,perc\n");
        for (var it : resultList) {
            double percentage = 100 * (double)it.getValue() / wordCounter;
            writer.printf("%-14s %-4s %-6f%%\n", it.getKey(), it.getValue().toString(), percentage);
        }
    }
}
