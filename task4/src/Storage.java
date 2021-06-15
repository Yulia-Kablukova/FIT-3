package com.company;

import java.util.ArrayList;

public class Storage {

    private int size;
    private ArrayList<Product> products;

    public Storage(int size) {
        this.size = size;
        products = new ArrayList<Product>();
    }

    public synchronized void add(Product product) throws InterruptedException {
        while (products.size() >= size) {
            this.wait();
        }
        products.add(product);
        this.notifyAll();
    }

    public synchronized Product get() throws InterruptedException {
        while (products.size() <= 0) {
            this.wait();
        }
        Product product = products.remove(0);
        this.notifyAll();
        return product;
    }

    public synchronized boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    public synchronized boolean isFilled() {
        if (size == products.size()) {
            return true;
        }
        return false;
    }

    public String getProductsType() {
        return products.get(0).getName();
    }
}
