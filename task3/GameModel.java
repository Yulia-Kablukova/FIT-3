package com.company;

import java.util.ArrayList;

public class GameModel implements Runnable{

    public static int WIDTH = 800;
    public static int HEIGHT = 500;
    Ground ground;
    Dino dino;
    Obstacles obstacles;
    private boolean running = false;
    private boolean gameOver = false;
    private int score;
    ArrayList<GameObserver> observers = new ArrayList<>();

    public GameModel() {
        ground = new Ground(HEIGHT);
        dino = new Dino();
        obstacles = new Obstacles((int)(WIDTH * 1.5));
        score = 0;
    }

    public void addObserver(GameObserver observer){
        observers.add(observer);
    }

    public void removeObserver(GameObserver observer){
        observers.remove(observer);
    }

    private void notifyObservers(){
        for(GameObserver observer : observers){
            observer.update();
        }
    }

    public void setScore(int _score) {

        score = _score;
    }

    public void setRunning(boolean _running) {
        running = _running;
    }

    public int getScore() {

        return score;
    }

    public boolean isRunning() {
        return running;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void increasePoints(int value) {

        score += value;
    }

    public void updateGame() {
        score += 1;

        ground.update();
        // dino.update();
        obstacles.update();

        if(obstacles.hasCollided()) {
            dino.die();
            running = false;
            gameOver = true;
        }
    }

    public void reset() {
        score = 0;
        obstacles.resume();
        gameOver = false;
    }

    public void run() {
        setRunning(true);
        while(isRunning()) {
            updateGame();
            try {
                Thread.sleep(10);
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
