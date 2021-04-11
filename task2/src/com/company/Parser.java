package com.company;

import java.io.File;
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

    public static void readFile(String fileName) throws Exception {

        Scanner scanner;

        try {
            scanner = new Scanner(new File(fileName));
        } catch (Exception e) {
            throw new Exception("Unable to open the file");
        }

        String str = scanner.nextLine();

        if (!str.equals("desc")) {
            throw new Exception("Incorrect data format in input file. Need desc.");
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
                throw new Exception("Incorrect data format in input file");
            }

            ArrayList<String> block = new ArrayList<String>(Arrays.asList(splitedLine));
            block.remove(splitedLine[0]);
            block.remove(splitedLine[1]);

            blocks.put(splitedLine[0], block);
        }

        str = scanner.nextLine();

        if (str.isEmpty()) {
            throw new Exception("Incorrect data format in input file. Need IDs' order.");
        }

        splitedLine = str.split(" -> ");

        ids = splitedLine;
    }
}
