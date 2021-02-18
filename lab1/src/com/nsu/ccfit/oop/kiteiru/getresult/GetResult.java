package com.nsu.ccfit.oop.kiteiru.getresult;

import java.io.IOException;
import java.io.Writer;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GetResult {
    private final Writer writer;

    public GetResult(Writer writer, final Map<String, Integer> dataContainer, int wordCounter) throws IOException {
        this.writer = writer;
        Comparator<Map.Entry<String, Integer>> comparing = (o1, o2) -> {
            if (o2.getValue() - o1.getValue() != 0) {
                return o2.getValue() - o1.getValue();
            }
            else {
                return o1.getKey().compareTo(o2.getKey());
            }
        };
        List<Map.Entry<String, Integer>> finishedList = dataContainer.entrySet().stream().sorted(comparing).collect(Collectors.toList());

        writer.write("Total number of unique words is: " + wordCounter + "\n");
        for (var it : finishedList) {
            writer.write(it.getKey() + ";" + it.getValue().toString() + ";" + (100 * (double)it.getValue() / wordCounter) + "%\n");
        }
    }
}
