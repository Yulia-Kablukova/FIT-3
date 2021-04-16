package com.company.blocks;

import com.company.exceptions.IncorrectArgumentsNumberException;

import java.util.ArrayList;
import java.util.Collections;

public class Sort implements Block {

    @Override
    public void execute(String[] args, ArrayList<String> text) throws Exception {

        if (args.length != 0) {
            throw new IncorrectArgumentsNumberException("sort needs 0 arguments");
        }

        Collections.sort(text);
    }

    @Override
    public BlockType getBlockType() {
        return BlockType.IN_OUT;
    }
}
