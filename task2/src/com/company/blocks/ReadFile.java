package com.company.blocks;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadFile implements Block {

    @Override
    public void execute(String[] args, ArrayList<String> text) throws Exception {

        if (args.length != 1) {
            throw new Exception("Incorrect number of arguments");
        }

        Scanner scanner;

        try {
            scanner = new Scanner(new File(args[0]));
        } catch (Exception e) {
            throw new Exception("Unable to open the file");
        }

        String str;

        while (scanner.hasNext()) {
            str = scanner.nextLine();
            text.add(str);
        }
    }
}
