package com.company.blocks;

import java.util.ArrayList;

public class Replace implements Block {

    @Override
    public void execute(String[] args, ArrayList<String> text) throws Exception {

        if (args.length != 2) {
            throw new Exception("Incorrect number of arguments");
        }

        for (int i = 0; i < text.size(); i++) {
            text.set(i, text.get(i).replace(args[0], args[1]));
        }
    }
}
