package com.company;

import java.util.UUID;

public class Product {

    private String name;
    private int departureStorageSize;
    private int destinationStorageSize;
    private int productionTime;
    private int consumptionTime;
    private int loadingTime;
    private int unloadingTime;
    private int fabricsCount;
    private int consumersCount;
    private UUID uuid;

    public Product() {
        uuid = UUID.randomUUID();
    }

    public Product(Product product) {
        name = product.name;
        departureStorageSize = product.getDepartureStorageSize();
        destinationStorageSize = product.getDestinationStorageSize();
        productionTime = product.getProductionTime();
        consumptionTime = product.getConsumptionTime();
        loadingTime = product.getLoadingTime();
        unloadingTime = product.getUnloadingTime();
        fabricsCount = product.getFabricsCount();
        consumersCount = product.getConsumersCount();
        uuid = UUID.randomUUID();
    }

    public void setConsumptionTime(int consumptionTime) {
        this.consumptionTime = consumptionTime;
    }

    public void setDepartureStorageSize(int departureStorageSize) {
        this.departureStorageSize = departureStorageSize;
    }

    public void setDestinationStorageSize(int destinationStorageSize) {
        this.destinationStorageSize = destinationStorageSize;
    }

    public void setLoadingTime(int loadingTime) {
        this.loadingTime = loadingTime;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProductionTime(int productionTime) {
        this.productionTime = productionTime;
    }

    public void setUnloadingTime(int unloadingTime) {
        this.unloadingTime = unloadingTime;
    }

    public void setFabricsCount(int fabricsCount) {
        this.fabricsCount = fabricsCount;
    }

    public void setConsumersCount(int consumersCount) {
        this.consumersCount = consumersCount;
    }

    public UUID getUid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public int getDepartureStorageSize() {
        return departureStorageSize;
    }

    public int getDestinationStorageSize() {
        return destinationStorageSize;
    }

    public int getProductionTime() {
        return productionTime;
    }

    public int getConsumptionTime() {
        return consumptionTime;
    }

    public int getLoadingTime() {
        return loadingTime;
    }

    public int getUnloadingTime() {
        return unloadingTime;
    }

    public int getFabricsCount() {
        return fabricsCount;
    }

    public int getConsumersCount() {
        return consumersCount;
    }
}
