package com.company;

public class Main {

    public static void main(String[] args) {

        if (args.length != 1) {
            System.out.println("Incorrect number of arguments");
        }

        try {
            Executor.start(args[0]);
        } catch (Exception e) {
            System.err.println(e.getLocalizedMessage());
        }
    }
}
