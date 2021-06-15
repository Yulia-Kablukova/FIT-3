package com.company;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

public class ConfigReader {

    public ArrayList<Product> readProductsConfig(String fileName) throws IOException {

        var configStream = ConfigReader.class.getResourceAsStream(fileName);
        Properties config = new Properties();

        config.load(configStream);

        String[] products = config.getProperty("products").split(",");
        ArrayList<Product> productsList = new ArrayList<Product>();

        for (String productName : products) {
            Product product = new Product();

            product.setName(config.getProperty(productName + ".name"));
            product.setConsumptionTime(Integer.parseInt(config.getProperty(productName + ".consumptionTime")));
            product.setProductionTime(Integer.parseInt(config.getProperty(productName + ".productionTime")));
            product.setDepartureStorageSize(Integer.parseInt(config.getProperty(productName + ".departureStorageSize")));
            product.setDestinationStorageSize(Integer.parseInt(config.getProperty(productName + ".destinationStorageSize")));
            product.setLoadingTime(Integer.parseInt(config.getProperty(productName + ".loadingTime")));
            product.setUnloadingTime(Integer.parseInt(config.getProperty(productName + ".unloadingTime")));
            product.setFabricsCount(Integer.parseInt(config.getProperty(productName + ".fabricsCount")));
            product.setConsumersCount(Integer.parseInt(config.getProperty(productName + ".consumersCount")));

            productsList.add(product);
        }

        return productsList;
    }

    public Stations readStationsConfig(String fileName) throws IOException {

        var configStream = ConfigReader.class.getResourceAsStream(fileName);
        Properties config = new Properties();

        config.load(configStream);

        Stations stations = new Stations();

        stations.setDepartureRoutes(Integer.parseInt(config.getProperty("departureRoutes")));
        stations.setDestinationRoutes(Integer.parseInt(config.getProperty("destinationRoutes")));
        stations.setDistance(Integer.parseInt(config.getProperty("distance")));
        stations.setRoutesToDepartureStation(Integer.parseInt(config.getProperty("routesToDepartureStation")));
        stations.setRoutesToDestinationStation(Integer.parseInt(config.getProperty("routesToDestinationStation")));

        return stations;
    }

    public ArrayList<Train> readTrainsConfig(String fileName) throws IOException {

        var configStream = ConfigReader.class.getResourceAsStream(fileName);
        Properties config = new Properties();

        config.load(configStream);

        String[] products = config.getProperty("products").split(",");
        String[] trains = config.getProperty("trains").split(",");
        ArrayList<Train> trainsList = new ArrayList<Train>();

        for (var trainName: trains) {
            Train train = new Train();

            train.setAmortizationTime(Integer.parseInt(config.getProperty(trainName + ".amortizationTime")));
            train.setAssemblyTime(Integer.parseInt(config.getProperty(trainName + ".assemblyTime")));
            train.setSpeed(Integer.parseInt(config.getProperty(trainName + ".speed")));

            HashMap<String, Integer> capacity = new HashMap<>();

            for (String productName : products) {
                capacity.put(productName, Integer.parseInt(config.getProperty(trainName + ".capacity." + productName)));
            }

            train.setCapacity(capacity);

            trainsList.add(train);
        }

        return trainsList;
    }
}
