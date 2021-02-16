package com.nsu.ccfit.oop.kiteiru.main;

import com.nsu.ccfit.oop.kiteiru.csvparser.CSVParser;
import java.io.*;

public class Main {
    public static void main (String [] args) throws IOException {
        if (args.length != 2) {
            System.out.println("Wrong input! Please, try again!");
            System.exit(0);
        }
        String inputFileName = args[0];
        String outputFileName = args[1];
        Reader reader = null;
        Writer writer = null;
        try {
            reader = new InputStreamReader(new FileInputStream(inputFileName));
            writer = new OutputStreamWriter(new FileOutputStream(outputFileName));
            CSVParser parser = new CSVParser(reader, writer);
        }
        catch (FileNotFoundException e) {
            System.err.println("Current error is: " + e.getLocalizedMessage());
        }
        finally {
            if (writer != null) {
                try {
                    writer.close();
                }
                catch (IOException e) {
                    e.printStackTrace(System.err);
                }
            }
            if (reader != null) {
                try {
                    reader.close();
                }
                catch (IOException e) {
                    e.printStackTrace(System.err);
                }
            }
        }
    }
}
