package com.company;

public class Factory extends Thread {

    private Product product;
    private Storage storage;

    public Factory(Product product, Storage storage) {
        this.product = product;
        this.storage = storage;
    }

    @Override
    public void run() {
        while (!isInterrupted()) {

            try {
                Thread.sleep(product.getProductionTime());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Product newProduct = new Product();

            try {
                storage.add(newProduct);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
