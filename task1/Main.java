package com.company;

public class Main {

    public static void main(String[] args) {

        if (args.length != 2) {
            System.out.println("Incorrect number of arguments");
        }

        WordCounter text = new WordCounter();

        try {
            text.readFile(args[0]);
            text.printAnswer(args[1]);
        }
        catch (Exception e) {
            System.err.println(e.getLocalizedMessage());
        }
    }
}
