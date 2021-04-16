package com.company.blocks;

import com.company.exceptions.IncorrectArgumentsNumberException;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Dump implements Block {

    @Override
    public void execute(String[] args, ArrayList<String> text) throws Exception {

        if (args.length != 1) {
            throw new IncorrectArgumentsNumberException("dump needs 1 argument");
        }

        try {
            FileWriter writer = new FileWriter(args[0]);
            for (int i = 0; i < text.size(); i++) {
                writer.write(text.get(i) + "\n");
            }
            writer.close();
        } catch (IOException e) {
            throw new IOException("Error while writing to file: " + e);
        }
    }

    @Override
    public BlockType getBlockType() {
        return BlockType.IN_OUT;
    }
}
