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
    private final Reader reader;
    private final Writer writer;

    public CSVParser(Reader reader, Writer writer) throws IOException {
        this.reader = reader;
        this.writer = writer;
        AddDataInMap();
    }

    private void AddDataInMap() throws IOException {
        StringBuilder word = new StringBuilder();
        int character;
        while(reader.ready()) {
            character = reader.read();
            if (Character.isLetterOrDigit((char)character)) {
                word.append((char)character);
            }
            else if (word.length() != 0) {
                IncreaseValue(word.toString());
                wordCounter++;
                word.delete(0, word.length());
            }
        }
        if (word.length() != 0) {
            IncreaseValue(word.toString());
            wordCounter++;
        }
        GetResult result = new GetResult(writer, dataContainer, wordCounter);
    }

    private void IncreaseValue(String word) {
        if (dataContainer.containsKey(word)){
            dataContainer.put(word, dataContainer.get(word) + 1);
        }
        else {
            dataContainer.put(word, 1);
        }
    }
}
