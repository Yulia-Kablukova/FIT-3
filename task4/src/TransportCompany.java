package com.company;

import java.io.IOException;
import java.util.ArrayList;

public class TransportCompany {

    private ArrayList<Product> productsList;
    private Stations stations;
    private ArrayList<Train> trainsList;
    private ArrayList<Factory> factories;
    private ArrayList<Consumer> consumers;
    private Depo depo;

    public TransportCompany() {
        productsList = new ArrayList<Product>();
        stations = new Stations();
        trainsList = new ArrayList<Train>();
        factories = new ArrayList<Factory>();
        consumers = new ArrayList<Consumer>();
    }

    public void start() throws IOException {

        ConfigReader configReader = new ConfigReader();

        productsList = configReader.readProductsConfig("products.config");
        stations = configReader.readStationsConfig("stations.config");
        trainsList = configReader.readTrainsConfig("trains.config");

        ArrayList<Storage> departureStorages = new ArrayList<Storage>();
        ArrayList<Storage> destinationStorages = new ArrayList<Storage>();

        for (Product product: productsList) {
            for (int i = 0; i < product.getFabricsCount(); i++) {

                Storage departureStorage = new Storage(product.getDepartureStorageSize());
                departureStorages.add(departureStorage);

                Factory factory = new Factory(product, departureStorage);
                factories.add(factory);
                factory.start();
            }

            for (int i = 0; i < product.getConsumersCount(); i++) {

                Storage destinationStorage = new Storage(product.getDestinationStorageSize());
                destinationStorages.add(destinationStorage);

                Consumer consumer = new Consumer(product, destinationStorage);
                consumers.add(consumer);
                consumer.start();
            }
        }

        stations.init();
        for (Train train: trainsList) {
            train.setDepartureStorages(departureStorages);
            train.setDestinationStorages(destinationStorages);
            train.setStations(stations);
        }
        depo = new Depo(trainsList);
        depo.start();
    }

    public void stop() {

        for (var factory: factories) {
            factory.interrupt();
        }

        for (var consumer: consumers) {
            consumer.interrupt();
        }

        depo.interrupt();
    }
}
