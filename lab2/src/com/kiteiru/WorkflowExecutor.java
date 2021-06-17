package com.kiteiru;

import com.kiteiru.blocklist.BlockBaseInterface;
import com.kiteiru.exceptions.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.logging.*;

public class WorkflowExecutor {
    private static final Logger log = Logger.getLogger(WorkflowExecutor.class.getName());
    List<String> context = new ArrayList<>();
    Map<Integer, BlockBaseInterface> commandMap = new HashMap<>();
    private int curPosition = 0;

    public void ExecuteWF(InputStream input) throws ExecutorException {
        try {
            BlockFactory factory = BlockFactory.GetInstance();
            WorkflowParser parser = new WorkflowParser();
            parser.parse(input);

            BlockInfo blockInfo;
            while ((blockInfo = parser.nextBlock()) != null) {
                System.out.println(blockInfo.getName());
                log.info("Trying to load blockInfo of " + blockInfo.getName());
                BlockBaseInterface blockBaseInterface = factory.GetBlock(blockInfo.getName());
                log.info("Success!");
                for (String args : blockInfo.getArgs()) {
                    blockBaseInterface.addArg(args);
                }
                commandMap.put(blockInfo.getId(), blockBaseInterface);
            }
            log.info("workflow file has been successfully parse");
            log.info("Start executing commands");
            for (Integer i : parser.GetBlocks()) {
                BlockBaseInterface blockBaseInterface = commandMap.get(i);
                if (blockBaseInterface == null) {
                    log.severe("workflow file does`t have blockInfo with id = " + i);
                    throw new ExecutorException("There is no blockInfo with id = " + i);
                }
                if (!CheckPositionOfBlock(blockBaseInterface, parser.GetBlocks().size())) {
                    log.severe("Wrong position of BlockBaseInterface, necessary:" + blockBaseInterface.getPosition());
                    throw new ExecutorException("Incorrect commands order");
                }
                log.info("Trying to execute blockBaseInterface");
                blockBaseInterface.ExecuteWF(context);
                log.info("Success with execution!");
                curPosition++;
            }
            log.info("Workflow execution completed");


        } catch (ParserException e) {
            log.log(Level.SEVERE, "Workflow parse error", e);
            log.info("Workflow execution completed due to error");
            throw new ExecutorException("Error while parsing ", e);

        } catch (CommandException e) {
            log.log(Level.SEVERE, "BlockBaseInterface error", e);
            log.info("Workflow execution completed due to error");
            throw new ExecutorException("BlockBaseInterface error", e);

        } catch (FactoryException | IOException e) {
            log.log(Level.SEVERE, "Workflow command load error", e);
            log.info("Workflow execution completed due to error");
            throw new ExecutorException(" ", e);
        }

    }

    boolean CheckPositionOfBlock(BlockBaseInterface blockBaseInterface, int count) {
        if (blockBaseInterface.getPosition() == 1 && curPosition == 0) {
            return true;
        } else if (blockBaseInterface.getPosition() == 2 && (curPosition != 0 && curPosition != count - 1)) {
            return true;
        } else if (blockBaseInterface.getPosition() == 3 && curPosition == count - 1) {
            return true;
        } else {
            return false;
        }
    }
}