package com.company.blocks;

import java.util.ArrayList;
import java.util.Collections;

public class Sort implements Block {

    @Override
    public void execute(String[] args, ArrayList<String> text) throws Exception {

        if (args.length != 0) {
            throw new Exception("Incorrect number of arguments");
        }

        Collections.sort(text);
    }
}
