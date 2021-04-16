package com.company.blocks;

import java.util.ArrayList;

public interface Block {

    void execute(String[] args, ArrayList<String> text) throws Exception;
}
