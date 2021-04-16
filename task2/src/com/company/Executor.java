package com.company;

import com.company.blocks.Block;
import com.company.exceptions.IncorrectBlockIdException;
import com.company.exceptions.InputFormatException;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import java.util.ArrayList;

public class Executor {

    static final Logger logger = LogManager.getLogger(BlockFactory.class.getName());

    public void start(String fileName) throws Exception {

        logger.info("log: Workflow executor is running...");

        logger.info("log: Started parsing the data...");

        try {
            Parser parser = new Parser();
            parser.readFile(fileName);
        } catch (Exception e) {
            logger.error("log: Error while parsing the data: ", e);
            throw e;
        }

        logger.info("log: Parsing finished successfully");

        var blocks = Parser.getBlocks();
        var ids = Parser.getIds();
        ArrayList<String> text = new ArrayList<>();

        for (int i = 0; i < ids.length; i++) {

            String blockId = ids[i];

            if (!blocks.containsKey(blockId)) {
                logger.error("log: No block related to ID " + blockId);
                throw new IncorrectBlockIdException("No block related to ID " + blockId);
            }

            String command = blocks.get(blockId).get(0);

            String[] args = new String[blocks.get(blockId).size() - 1];

            for (int j = 1; j < blocks.get(blockId).size(); j++) {
                args[j - 1] = blocks.get(blockId).get(j);
            }

            Block newBlock = BlockFactory.getInstance().getBlock(command);

            if (i == 0 && newBlock.getBlockType() != Block.BlockType.IN) {
                logger.error("log: No reading block in the beginning");
                throw new InputFormatException("No reading block in the beginning");
            }

            if (i == ids.length - 1 && newBlock.getBlockType() != Block.BlockType.OUT) {
                logger.error("log: No writing block in the end");
                throw new InputFormatException("No writing block in the end");
            }

            logger.info("log: " + command + " is executing...");

            try {
                newBlock.execute(args, text);
            } catch (Exception e) {
                logger.error("log: ", e);
                throw e;
            }

            logger.info("log: " + command + " was executed successfully");
        }

        logger.info("log: Workflow executor finished the work successfully");
    }
}
