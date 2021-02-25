package com.company;

import java.util.*;
import java.lang.*;
import java.io.*;

public class WordCounter {

    Map<String, Integer> wordMap;
    int wordCount;

    WordCounter() {
        wordMap = new HashMap<>();
        wordCount = 0;
    }

    public void readFile(final String fileName) throws Exception {

        InputStreamReader reader = null;
        try {
            reader = new InputStreamReader(new FileInputStream(fileName));
        }
        catch (IOException e) {
            throw new Exception("Error while opening file: " + e.getLocalizedMessage());
        }

        BufferedReader buffer = new BufferedReader(reader);
        String line;
        String word = "";

        while ((line = buffer.readLine()) != null) {
            line.toLowerCase();

            for (char symb : line.toCharArray()) {
                if (Character.isLetterOrDigit(symb)) {
                    word += symb;
                }
                else {
                    word = saveWord(word);
                }
            }
            word = saveWord(word);
        }

        reader.close();
    }

    private String saveWord(String str) {

        if (str == "") {
            return str;
        }

        Character.toUpperCase(str.charAt(0));
        wordCount++;

        if (wordMap.get(str) != null) {
            wordMap.put(str, wordMap.get(str) + 1);
        }
        else {
            wordMap.put(str, 1);
        }

        return "";
    }

    public void printAnswer(final String fileName) throws Exception {

        OutputStreamWriter writer = null;
        try {
            writer = new OutputStreamWriter(new FileOutputStream(fileName));
        }
        catch (IOException e) {
            throw new Exception("Error while opening file: " + e.getLocalizedMessage());
        }

        if (wordCount == 0)
        {
            writer.close();
            return;
        }

        List<Map.Entry<String, Integer>> wordList = new ArrayList<>(wordMap.entrySet());
        Collections.sort(wordList, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> first, Map.Entry<String, Integer> second) {
                if (first.getValue() > second.getValue()) {
                    return 1;
                }
                if (first.getValue() < second.getValue()) {
                    return -1;
                }

                return first.getKey().compareTo(second.getKey());
            }
        });

        Collections.reverse(wordList);

        print(wordList, writer);

        writer.close();
    }

    private void print(List<Map.Entry<String, Integer>> wordList, OutputStreamWriter writer) throws IOException {
        for (Map.Entry<String, Integer> entry : wordList)
        {
            writer.write(entry.getKey() + ";" + entry.getValue() + ";" + ((double)entry.getValue() / wordCount * 100) + " %\n");
        }
    }
}
