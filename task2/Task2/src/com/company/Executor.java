package com.company;

import com.company.blocks.Block;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import java.util.ArrayList;

public class Executor {

    static final Logger logger = LogManager.getLogger(BlockFactory.class.getName());

    public static void start(String fileName) throws Exception {

        logger.info("log: Workflow executor is running...");

        logger.info("log: Started parsing the data...");

        try {
            Parser.readFile(fileName);
        } catch (Exception e) {
            logger.error("log: Error while parsing the data: " + e.getLocalizedMessage());
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
                throw new Exception("No block related to ID " + blockId);
            }

            String command = blocks.get(blockId).get(0);

            if (i == 0 && !command.equals("readfile")) {
                logger.error("log: No reading block in the beginning");
                throw new Exception("No reading block in the beginning");
            }

            if (i == ids.length - 1 && !command.equals("writefile")) {
                logger.error("log: No writing block in the end");
                throw new Exception("No writing block in the end");
            }

            String[] args = new String[blocks.get(blockId).size() - 1];

            for (int j = 1; j < blocks.get(blockId).size(); j++) {
                args[j - 1] = blocks.get(blockId).get(j);
            }

            Block newBlock = BlockFactory.getInstance().getBlock(command);

            logger.info("log: " + command + " is executing...");

            try {
                newBlock.execute(args, text);
            } catch (Exception e) {
                logger.error("log: " + e.getLocalizedMessage());
                throw e;
            }

            logger.info("log: " + command + " was executed successfully");
        }

        logger.info("log: Workflow executor finished the work successfully");
    }
}
