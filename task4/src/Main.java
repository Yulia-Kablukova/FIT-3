package com.company;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        TransportCompany transportCompany = new TransportCompany();

        try {
            transportCompany.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

        int stopFlag = 0;

        while (stopFlag != '\n') {
            try {
                stopFlag = System.in.read();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        transportCompany.stop();
    }
}
