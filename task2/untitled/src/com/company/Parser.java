package com.company;

import com.company.exceptions.InputFormatException;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Parser {

    private static Map<String, ArrayList<String>> blocks;
    private static String[] ids;

    public static Map<String, ArrayList<String>> getBlocks() {

        return blocks;
    }

    public static String[] getIds() {

        return ids;
    }

    public void readFile(String fileName) throws Exception {

        Scanner scanner;

        try {
            scanner = new Scanner(new File(fileName));
        } catch (Exception e) {
            throw new IOException("Unable to open the file");
        }

        String str = scanner.nextLine();

        if (!str.equals("desc")) {
            throw new InputFormatException("Need desc.");
        }

        blocks = new HashMap<String, ArrayList<String>>();
        String[] splitedLine;

        while (scanner.hasNext()) {
            str = scanner.nextLine();

            if (str.equals("csed")) {
                break;
            }

            splitedLine = str.split(" ");

            if (!splitedLine[1].equals("=") || splitedLine.length < 3) {
                throw new InputFormatException("Incorrect data format in input file");
            }

            ArrayList<String> block = new ArrayList<String>(Arrays.asList(splitedLine));
            block.remove(splitedLine[0]);
            block.remove(splitedLine[1]);

            blocks.put(splitedLine[0], block);
        }

        str = scanner.nextLine();

        if (str.isEmpty()) {
            throw new InputFormatException("Need IDs' order.");
        }

        splitedLine = str.split(" -> ");

        ids = splitedLine;
    }
}
