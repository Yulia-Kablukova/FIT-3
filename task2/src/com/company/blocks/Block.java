package com.company.blocks;

import java.util.ArrayList;

public interface Block {

    enum BlockType {
        IN,
        OUT,
        IN_OUT
    }

    void execute(String[] args, ArrayList<String> text) throws Exception;

    BlockType getBlockType();
}
