package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Train extends Thread {

    private HashMap<String, Integer> capacity;
    private int speed;
    private int assemblyTime;
    private int amortizationTime;
    private ArrayList<Storage> departureStorages;
    private ArrayList<Storage> destinationStorages;
    private Stations stations;
    private ArrayList<Product> products;

    public int getAmortizationTime() {
        return amortizationTime;
    }

    public int getAssemblyTime() {
        return assemblyTime;
    }

    public int getSpeed() {
        return speed;
    }

    public HashMap<String, Integer> getCapacity() {
        return capacity;
    }

    public void setAmortizationTime(int amortizationTime) {
        this.amortizationTime = amortizationTime;
    }

    public void setAssemblyTime(int assemblyTime) {
        this.assemblyTime = assemblyTime;
    }

    public void setCapacity(HashMap<String, Integer> capacity) {
        this.capacity = capacity;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setDepartureStorages(ArrayList<Storage> departureStorages) {
        this.departureStorages = departureStorages;
    }

    public void setDestinationStorages(ArrayList<Storage> destinationStorages) {
        this.destinationStorages = destinationStorages;
    }

    public void setStations(Stations stations) {
        this.stations = stations;
    }

    @Override
    public void run() {
        long startTime = System.currentTimeMillis();
        while (!isInterrupted() && ((System.currentTimeMillis() - startTime) / 1000 < amortizationTime)) {
            try {
                stations.takeDepartureRoute();
                load();
                stations.freeDepartureRoute();
                stations.takeRouteToDestinationStation();
                Thread.sleep(stations.getDistance() / speed);
                stations.freeRouteToDestinationStation();
                stations.takeDestinationRoute();
                unload();
                stations.freeDestinationRoute();
                stations.takeRouteToDepartureStation();
                Thread.sleep(stations.getDistance() / speed);
                stations.freeRouteToDepartureStation();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    private void load() throws InterruptedException {
        products = new ArrayList<Product>();
        for (Map.Entry<String, Integer> entry : capacity.entrySet()) {
            int loaded = 0;
            while (loaded != entry.getValue()) {
                int j = 0;
                if (departureStorages.get(j).isEmpty() || !departureStorages.get(j).getProductsType().equals(entry.getKey())) {
                    j = (j + 1) % departureStorages.size();
                } else {
                    products.add(departureStorages.get(j).get());
                    loaded++;
                }
            }
        }
    }

    private void unload() throws InterruptedException {
        for (Map.Entry<String, Integer> entry : capacity.entrySet()) {
            int unloaded = 0;
            while (unloaded != entry.getValue()) {
                int j = 0;
                if (departureStorages.get(j).isFilled() || !departureStorages.get(j).getProductsType().equals(entry.getKey())) {
                    j = (j + 1) % departureStorages.size();
                } else {
                    departureStorages.get(j).add(products.remove(0));
                }
            }
        }
    }

}
