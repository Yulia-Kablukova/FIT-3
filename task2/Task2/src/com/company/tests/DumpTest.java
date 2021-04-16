package com.company.tests;

import com.company.blocks.Dump;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.Assert.*;

public class DumpTest {

    @Test
    public void execute() throws Exception {

        Dump newDump = new Dump();

        String[] args = {"dumpText.txt"};
        ArrayList<String> text = new ArrayList<>();
        text.add("1234");
        text.add("abcd");

        newDump.execute(args, text);

        Scanner scanner = new Scanner(new File("dumpTest.txt"));
        ArrayList<String> dumpResult = new ArrayList<>();
        String str;

        while (scanner.hasNext()) {
            str = scanner.nextLine();
            text.add(str);
        }

        Assert.assertEquals(text, dumpResult);
    }
}