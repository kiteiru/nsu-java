package test;

import com.kiteiru.BlockFactory;
import com.kiteiru.blocklist.BlockBaseInterface;
import com.kiteiru.exceptions.FactoryException;
import org.junit.jupiter.api.*;

import com.kiteiru.exceptions.CommandException;

import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.stream.*;


public class TestWorkflow {
    private static List<String> context;
    private static List<String> testText;


    @Test //DumpBlock class testing
    void TestingDumpCommand() throws IOException, CommandException, FactoryException {
        BlockBaseInterface dumpBlockBaseInterface = BlockFactory.GetInstance().GetBlock("dump");
        context.addAll(testText);
        String dumpFile = "dumpTest.txt";
        dumpBlockBaseInterface.addArg(dumpFile);
        dumpBlockBaseInterface.ExecuteWF(context);
        Assertions.assertTrue(Files.exists(Path.of(dumpFile)));
    }

    @Test //GrepBlock class testing
    void TestingGrepCommand() throws CommandException, IOException, FactoryException {
        BlockBaseInterface grepBlockBaseInterface = BlockFactory.GetInstance().GetBlock("grep");
        context.addAll(testText);
        String grepWord = "grep";
        grepBlockBaseInterface.addArg(grepWord);
        grepBlockBaseInterface.ExecuteWF(context);
        List<String> grepText = new ArrayList<>(testText);
        grepText = grepText.stream().filter((s) -> s.contains(grepWord)).collect(Collectors.toList());
        Assertions.assertEquals(grepText, context);
    }

    @Test //ReadFileBlock class testing
    void TestingReadCommand() throws CommandException, IOException, FactoryException {
        BlockBaseInterface readBlockBaseInterface = BlockFactory.GetInstance().GetBlock("readfile");
        readBlockBaseInterface.addArg("in_test.txt");
        readBlockBaseInterface.ExecuteWF(context);
        Assertions.assertEquals(testText, context);
    }

    @Test //ReplaceBlock class testing
    void TestingReplaceCommand() throws CommandException, IOException, FactoryException {
        BlockBaseInterface replaceBlockBaseInterface = BlockFactory.GetInstance().GetBlock("replace");
        context.addAll(testText);
        String oldWord = "word1";
        String replacedWord = "word2";
        replaceBlockBaseInterface.addArg(oldWord);
        replaceBlockBaseInterface.addArg(replacedWord);
        replaceBlockBaseInterface.ExecuteWF(context);
        List<String> replaceText = new ArrayList<>(testText);
        replaceText = replaceText.stream().map((s) -> s.replaceAll(oldWord, replacedWord)).collect(Collectors.toList());
        Assertions.assertEquals(replaceText, context);
    }

    @Test //SortBlock class testing
    void TestingSortCommand() throws CommandException, IOException, FactoryException {
        BlockBaseInterface sortBlockBaseInterface = BlockFactory.GetInstance().GetBlock("sort");
        context.addAll(testText);
        sortBlockBaseInterface.ExecuteWF(context);
        List<String> sortText = new ArrayList<>(testText);
        sortText.sort(String::compareTo);
        Assertions.assertEquals(sortText, context);
    }

    @Test //WriteFileBlock class testing
    void TestingWriteCommand() throws CommandException, IOException, FactoryException {
        BlockBaseInterface writeBlockBaseInterface = BlockFactory.GetInstance().GetBlock("writefile");
        context.addAll(testText);
        String writeFile = "writeTest.txt";
        writeBlockBaseInterface.addArg(writeFile);
        writeBlockBaseInterface.ExecuteWF(context);
        Assertions.assertTrue(Files.exists(Path.of(writeFile)));
    }

    @BeforeAll
    static void CreateWorkflowTemplate() throws IOException {
        testText = Files.readAllLines(Path.of("in_test.txt"));
        testText = Collections.unmodifiableList(testText);
    }

    @BeforeEach
    void СreateContext() {
        context = new ArrayList<>();
    }

}