package com.company;

public class Stations {

    private int distance;
    private int departureRoutes;
    private int destinationRoutes;
    private int routesToDestinationStation;
    private int routesToDepartureStation;

    private int freeDepartureRoutes;
    private int freeDestinationRoutes;
    private int freeRoutesToDestinationStation;
    private int freeRoutesToDepartureStation;

    public int getDepartureRoutes() {
        return departureRoutes;
    }

    public int getDestinationRoutes() {
        return destinationRoutes;
    }

    public int getDistance() {
        return distance;
    }

    public int getRoutesToDepartureStation() {
        return routesToDepartureStation;
    }

    public int getRoutesToDestinationStation() {
        return routesToDestinationStation;
    }

    public void setDepartureRoutes(int departureRoutes) {
        this.departureRoutes = departureRoutes;
    }

    public void setDestinationRoutes(int destinationRoutes) {
        this.destinationRoutes = destinationRoutes;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public void setRoutesToDepartureStation(int routesToDepartureStation) {
        this.routesToDepartureStation = routesToDepartureStation;
    }

    public void setRoutesToDestinationStation(int routesToDestinationStation) {
        this.routesToDestinationStation = routesToDestinationStation;
    }

    public void setFreeDepartureRoutes(int freeDepartureRoutes) {
        this.freeDepartureRoutes = freeDepartureRoutes;
    }

    public void setFreeDestinationRoutes(int freeDestinationRoutes) {
        this.freeDestinationRoutes = freeDestinationRoutes;
    }

    public void setFreeRoutesToDepartureStation(int freeRoutesToDepartureStation) {
        this.freeRoutesToDepartureStation = freeRoutesToDepartureStation;
    }

    public void setFreeRoutesToDestinationStation(int freeRoutesToDestinationStation) {
        this.freeRoutesToDestinationStation = freeRoutesToDestinationStation;
    }

    public int getFreeDepartureRoutes() {
        return freeDepartureRoutes;
    }

    public int getFreeDestinationRoutes() {
        return freeDestinationRoutes;
    }

    public int getFreeRoutesToDepartureStation() {
        return freeRoutesToDepartureStation;
    }

    public int getFreeRoutesToDestinationStation() {
        return freeRoutesToDestinationStation;
    }

    public void init() {
        freeDepartureRoutes = departureRoutes;
        freeDestinationRoutes = destinationRoutes;
        freeRoutesToDepartureStation = routesToDepartureStation;
        freeRoutesToDestinationStation = routesToDestinationStation;
    }

    public synchronized void takeDepartureRoute() throws InterruptedException {
        while (freeDepartureRoutes <= 0) {
            this.wait();
        }
        freeDepartureRoutes--;
        this.notifyAll();
    }

    public synchronized void freeDepartureRoute() {
        freeDepartureRoutes++;
    }

    public synchronized void takeRouteToDestinationStation() throws InterruptedException {
        while (freeRoutesToDestinationStation <= 0) {
            this.wait();
        }
        freeRoutesToDestinationStation--;
        this.notifyAll();
    }

    public synchronized void freeRouteToDestinationStation() {
        freeRoutesToDestinationStation++;
    }

    public synchronized void takeDestinationRoute() throws InterruptedException {
        while (freeDestinationRoutes <= 0) {
            this.wait();
        }
        freeDestinationRoutes--;
        this.notifyAll();
    }

    public synchronized void freeDestinationRoute() {
        freeDestinationRoutes++;
    }

    public synchronized void takeRouteToDepartureStation() throws InterruptedException {
        while (freeRoutesToDepartureStation <= 0) {
            this.wait();
        }
        freeRoutesToDepartureStation--;
        this.notifyAll();
    }

    public synchronized void freeRouteToDepartureStation() {
        freeRoutesToDepartureStation++;
    }
}
