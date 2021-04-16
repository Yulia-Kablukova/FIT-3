package com.company.blocks;

import com.company.exceptions.IncorrectArgumentsNumberException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadFile implements Block {

    @Override
    public void execute(String[] args, ArrayList<String> text) throws Exception {

        if (args.length != 1) {
            throw new IncorrectArgumentsNumberException("readfile needs 1 argument");
        }

        Scanner scanner;

        try {
            scanner = new Scanner(new File(args[0]));
        } catch (Exception e) {
            throw new IOException("Unable to open the file");
        }

        String str;

        while (scanner.hasNext()) {
            str = scanner.nextLine();
            text.add(str);
        }
    }

    @Override
    public BlockType getBlockType() {
        return BlockType.IN;
    }
}
