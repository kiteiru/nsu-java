package com.nsu.ccfit.oop.kiteiru.csvparser;

import com.nsu.ccfit.oop.kiteiru.getresult.GetResult;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

public class CSVParser {
    final Map<String, Integer> dataContainer = new HashMap<>();
    int wordCounter = 0;
    private Reader reader;

    public void AddDataFromFile(Reader reader) throws IOException{
        this.reader = reader;
        StringBuilder word = new StringBuilder();
        int character;
        while(reader.ready()) {
            character = reader.read();
            if (Character.isLetterOrDigit((char)character)) {
                word.append((char)character);
            }
            else if (word.length() > 0) {
                AddWordInMap(word.toString());
                wordCounter++;
                word.delete(0, word.length());
            }
        }
        if (word.length() > 0) {
            AddWordInMap(word.toString());
            wordCounter++;
        }
    }

    private void AddWordInMap(String word) {
        if (dataContainer.containsKey(word)){
            dataContainer.put(word, dataContainer.get(word) + 1);
        }
        else {
            dataContainer.put(word, 1);
        }
    }

    public void FinishParsing(PrintWriter writer) throws IOException {
        GetResult result = new GetResult(writer, dataContainer, wordCounter);
    }
}
