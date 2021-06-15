package com.company;

public class Consumer extends Thread {

    private Product product;
    private Storage storage;

    public Consumer(Product product, Storage storage) {
        this.product = product;
        this.storage = storage;
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            try {
                Thread.sleep(product.getConsumptionTime());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                Product newProduct = storage.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
