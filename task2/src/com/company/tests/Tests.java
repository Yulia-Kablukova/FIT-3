package com.company.tests;

import com.company.blocks.*;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Tests {

    @Test
    public void readTest() throws Exception {

        ReadFile newRead = new ReadFile();
        String[] args = {"in.txt"};
        ArrayList<String> readResult = new ArrayList<>();

        newRead.execute(args, readResult);

        ArrayList<String> text = new ArrayList<>();
        text.add("Hello, world!");
        text.add("Hello, Jack");
        text.add("2 1 3452");
        text.add("abcc d");
        text.add("1 2 3 4 5");

        Assert.assertEquals(text, readResult);
    }

    @Test
    public void writeTest() throws Exception {

        ReadFile newRead = new ReadFile();
        String[] readArgs = {"in.txt"};
        ArrayList<String> text = new ArrayList<>();
        newRead.execute(readArgs, text);

        WriteFile newWrite = new WriteFile();
        String[] writeArgs = {"writeTest.txt"};

        newWrite.execute(writeArgs, text);

        Scanner scanner = new Scanner(new File("writeTest.txt"));
        ArrayList<String> writeResult = new ArrayList<>();
        String str;

        while (scanner.hasNext()) {
            str = scanner.nextLine();
            writeResult.add(str);
        }

        Assert.assertEquals(text, writeResult);
    }

    @Test
    public void grepTest() throws Exception {

        Grep newGrep = new Grep();
        String[] args = {"2"};
        ArrayList<String> grepResult = new ArrayList<>();
        grepResult.add("1234");
        grepResult.add("abcd");
        grepResult.add("4321");

        newGrep.execute(args, grepResult);

        ArrayList<String> text = new ArrayList<>();
        text.add("1234");
        text.add("4321");

        Assert.assertEquals(text, grepResult);
    }

    @Test
    public void sortTest() throws Exception {

        Sort newSort = new Sort();
        String[] args = {};
        ArrayList<String> sortResult = new ArrayList<>();
        sortResult.add("5678");
        sortResult.add("1234");
        sortResult.add("4321");

        newSort.execute(args, sortResult);

        ArrayList<String> text = new ArrayList<>();
        text.add("1234");
        text.add("4321");
        text.add("5678");

        Assert.assertEquals(text, sortResult);
    }

    @Test
    public void replaceTest() throws Exception {

        Replace newReplace = new Replace();
        String[] args = {"2", "3"};
        ArrayList<String> replaceResult = new ArrayList<>();
        replaceResult.add("5228");
        replaceResult.add("1234");
        replaceResult.add("4321");

        newReplace.execute(args, replaceResult);

        ArrayList<String> text = new ArrayList<>();
        text.add("5338");
        text.add("1334");
        text.add("4331");

        Assert.assertEquals(text, replaceResult);
    }

    @Test
    public void dumpTest() throws Exception {

        Dump newDump = new Dump();
        String[] args = {"dumpTest.txt"};
        ArrayList<String> text = new ArrayList<>();
        text.add("1234");
        text.add("abcd");

        newDump.execute(args, text);

        ArrayList<String> dumpResult = new ArrayList<>();
        ReadFile newRead = new ReadFile();
        newRead.execute(args, dumpResult);

        Assert.assertEquals(text, dumpResult);
    }
}