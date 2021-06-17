package com.kiteiru;

import com.kiteiru.exceptions.ExecutorException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.LogManager;

public class Main {
    public static void main(String[] args) {
        try {
            LogManager.getLogManager().readConfiguration(Main.class.getResourceAsStream("logging.properties"));
        } catch (IOException e) {
            System.err.println("Error occured with setting up logger properties: " + e.toString());
        }

        try {
            FileInputStream fileInputStream = new FileInputStream("workflow.txt");
            WorkflowExecutor executor = new WorkflowExecutor();
            executor.ExecuteWF(fileInputStream);
        } catch (ExecutorException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}