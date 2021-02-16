package com.nsu.ccfit.oop.kiteiru.getresult;

import java.io.IOException;
import java.io.Writer;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class GetResult {
    public GetResult(Writer writer, final Map<String, Integer> dataContainer, int wordCounter) throws IOException {
        List<Map.Entry<String, Integer>> list = new LinkedList<>(dataContainer.entrySet());
        list.sort(Map.Entry.<String, Integer>comparingByValue().reversed());

        for (var entry : list) {
            writer.write(entry.getKey() + ";" + entry.getValue().toString() + ";" + (100 * (double) entry.getValue() / wordCounter) + "%n");
        }
    }
}
