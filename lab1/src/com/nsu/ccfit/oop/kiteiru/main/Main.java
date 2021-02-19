package com.nsu.ccfit.oop.kiteiru.main;

import com.nsu.ccfit.oop.kiteiru.csvparser.CSVParser;
import java.io.*;

public class Main {
    public static void main (String [] args) throws IOException {
        if (args.length < 2) {
            System.out.println("Wrong input! Please, try again! \nHave to be at least one input and output filenames!");
            System.exit(0);
        }
        else {
            String inputFileName;
            String outputFileName = args[args.length - 1];
            Reader reader = null;
            PrintWriter writer = new PrintWriter(new FileOutputStream(outputFileName));
            CSVParser parser = new CSVParser();
            try {
                for (int i = 0; i < args.length - 1; i++) {
                    inputFileName = args[i];
                    reader = new InputStreamReader(new FileInputStream(inputFileName));
                    parser.AddDataFromFile(reader);
                    try {
                        reader.close();
                    }
                    catch (IOException e) {
                        e.printStackTrace(System.err);
                    }
                }
            }
            catch (FileNotFoundException e) {
                System.err.println("Current error is: " + e.getLocalizedMessage());
            }
            try {
                parser.FinishParsing(writer);
            }
            catch (FileNotFoundException e) {
                System.err.println("Current error is: " + e.getLocalizedMessage());
            }
            finally {
                if (writer != null) {
                    writer.close();
                }
            }
        }
    }
}
