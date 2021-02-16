package com.nsu.ccfit.oop.kiteiru.csvparser;

import com.nsu.ccfit.oop.kiteiru.getresult.GetResult;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

public class CSVParser {
    final Map<String, Integer> dataContainer = new HashMap<>();
    int wordCounter = 0;

    public CSVParser(Reader reader, Writer writer) throws IOException {
        StringBuilder word = new StringBuilder();

        while(reader.read() > 0) {
            if (Character.isLetterOrDigit((char)(reader.read()))) {
                word.append((char)(reader.read()));
            }
        }
        if (word.length() > 0) {
            addMapValue(word.toString());
            wordCounter++;
        }
        GetResult result = new GetResult(writer, dataContainer, wordCounter);
    }

    private void addMapValue(String word) {
        if (dataContainer.containsKey(word)) {
            dataContainer.put(word, dataContainer.get(word) + 1);
        }
        else {
            dataContainer.put(word, 1);
        }
    }
}
