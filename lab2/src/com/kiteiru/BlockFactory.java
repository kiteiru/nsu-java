package com.kiteiru;

import com.kiteiru.blocklist.BlockBaseInterface;
import com.kiteiru.exceptions.FactoryException;

import java.io.IOException;
import java.util.Properties;

public class BlockFactory {

    private final Properties config = new Properties();

    private BlockFactory() throws IOException {
        var configStream = getClass().getResourceAsStream("BlockFactory.config");
        if (configStream == null) {
            throw new IOException("Sorry, failed reading config");
        }
        config.load(configStream);
    }

    public BlockBaseInterface GetBlock(String blockName) throws FactoryException {
        if (!config.containsKey(blockName)) {
            throw new FactoryException("BlockBaseInterface " + blockName + " not found");
        }
        BlockBaseInterface blockBaseInterface;
        try {
            blockBaseInterface = ((BlockBaseInterface) Class.forName(config.getProperty(blockName)).getDeclaredConstructor().newInstance());
        } catch (ClassNotFoundException e) {
            throw new FactoryException("Unable to find class with " + blockName + " blockBaseInterface from the config", e);
        } catch (InstantiationException e) {
            throw new FactoryException("Error occured with creating blockBaseInterface " + blockName + " instance", e);
        }
        return blockBaseInterface;
    }

    private static volatile BlockFactory instance;
    public static BlockFactory GetInstance() throws IOException {
        if (instance == null) {
            synchronized (BlockFactory.class) {
                if (instance == null) {
                    instance = new BlockFactory();
                }
            }
        }
        return instance;
    }
}