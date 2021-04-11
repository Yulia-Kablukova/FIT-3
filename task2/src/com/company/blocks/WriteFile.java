package com.company.blocks;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class WriteFile implements Block {

    @Override
    public void execute(String[] args, ArrayList<String> text) throws Exception {

        if (args.length != 1) {
            throw new Exception("Incorrect number of arguments");
        }

        try {
            FileWriter writer = new FileWriter(args[0]);
            for (int i = 0; i < text.size(); i++) {
                writer.write(text.get(i) + "\n");
            }
            writer.close();
        } catch (IOException e) {
            throw new Exception("Error while writing to file: " + e);
        }
    }
}
