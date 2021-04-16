package com.company.blocks;

import java.util.ArrayList;

public class Grep implements Block {

    @Override
    public void execute(String[] args, ArrayList<String> text) throws Exception {

        if (args.length != 1) {
            throw new Exception("Incorrect number of arguments");
        }

        text.removeIf(line -> (!line.contains(args[0])));
    }
}
