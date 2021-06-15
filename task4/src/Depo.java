package com.company;

import java.util.ArrayList;

public class Depo extends Thread {

    private ArrayList<Train> trainsList;

    public Depo(ArrayList<Train> trainsList) {
        this.trainsList = trainsList;
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            for(Train train: trainsList) {
                if (!train.isAlive()) {
                    try {
                        Thread.sleep(train.getAmortizationTime());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    train.start();
                }
            }
        }
        for (Train train: trainsList) {
            if (train.isAlive()) {
                train.interrupt();
            }
        }
    }
}
