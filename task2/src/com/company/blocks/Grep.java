package com.company.blocks;

import com.company.exceptions.IncorrectArgumentsNumberException;

import java.util.ArrayList;

public class Grep implements Block {

    @Override
    public void execute(String[] args, ArrayList<String> text) throws Exception {

        if (args.length != 1) {
            throw new IncorrectArgumentsNumberException("grep needs 1 argument");
        }

        text.removeIf(line -> (!line.contains(args[0])));
    }

    @Override
    public BlockType getBlockType() {
        return BlockType.IN_OUT;
    }
}
