package com.company;

import com.company.blocks.Block;
import com.company.exceptions.BlockNotFoundException;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import java.io.IOException;
import java.util.Properties;

public class BlockFactory {

    static final Logger logger = LogManager.getLogger(BlockFactory.class.getName());

    private static final Properties config = new Properties();

    private BlockFactory() throws IOException {

        logger.info("log: Creating BlockFactory...");

        var configStream = BlockFactory.class.getResourceAsStream("BlockFactory.config");

        if (configStream == null) {
            logger.error("log: Unable to read config");
            throw new IOException("Unable to read config");
        }

        config.load(configStream);
        logger.info("log: BlockFactory was created successfully");
    }

    private static volatile BlockFactory instance;

    public static BlockFactory getInstance() throws IOException {

        if (instance == null) {
            synchronized (BlockFactory.class) {
                if (instance == null) {
                    instance = new BlockFactory();
                }
            }
        }
        return instance;
    }

    public Block getBlock(String blockName) throws Exception {

        if (!config.containsKey(blockName)) {
            logger.error("log: Block " + blockName + " not found");
            throw new BlockNotFoundException("Block " + blockName + " not found");
        }

        Block block;

        try {
            var blockClass = Class.forName(config.getProperty(blockName));
            var blockObject = blockClass.getDeclaredConstructor().newInstance();
            block = (Block) blockObject;
        } catch (Exception e) {
            logger.error("log: Unable to create block " + blockName, e);
            throw new Exception("Unable to create block " + blockName + ": " + e.getLocalizedMessage());
        }

        logger.info("log: " + blockName + " block created successfully");

        return block;
    }
}
