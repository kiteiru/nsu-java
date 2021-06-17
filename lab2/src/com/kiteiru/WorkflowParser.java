package com.kiteiru;

import com.kiteiru.exceptions.ParserException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WorkflowParser {
    List<String> blocksDescript = new ArrayList<>();
    List<Integer> blocksSeq = new ArrayList<>();

    public void parse(InputStream fileName) throws ParserException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(fileName))) {
            String line;
            line = reader.readLine();

            if (line == null || !line.equals("desc")) {
                throw new ParserException("Have to start with 'desc'");
            }
            while (!(line = reader.readLine()).equals("csed")) {
                blocksDescript.add(line);
            }
            String workflowStructure = reader.readLine();
            for (String s : workflowStructure.split(" -> ")) {
                blocksSeq.add(Integer.parseInt(s));
            }
        } catch (IOException e) {
            throw new ParserException("Error occured with opening file", e);
        }
    }

    BlockInfo nextBlock() throws ParserException {
        if (blocksDescript.size() == 0) {
            return null;
        }

        String[] words = blocksDescript.get(0).split(" ");
        blocksDescript.remove(0);
        if (words.length < 3) {
            throw new ParserException("Wrong command format");
        }

        int currentId = Integer.parseInt(words[0]);
        String currentName = words[2];

        List<String> currentArgs = new ArrayList<>(Arrays.asList(words).subList(3, words.length));
        return new BlockInfo(currentId, currentName, currentArgs);
    }

    List<Integer> GetBlocks() {
        return blocksSeq;
    }

}
