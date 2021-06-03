package com.company;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Obstacles {
    private static class Obstacle {

        BufferedImage image;
        int x;
        int y;

        Rectangle getObstacle() {

            Rectangle obstacle = new Rectangle();
            obstacle.x = x;
            obstacle.y = y;
            obstacle.width = image.getWidth();
            obstacle.height = image.getHeight();

            return obstacle;
        }
    }

    private final int firstX;
    private final int minObstacleInterval;
    private final int speed;

    private final ArrayList<BufferedImage> imageList;
    private ArrayList<Obstacle> obList;

    public Obstacles(int firstPos) {
        obList = new ArrayList<Obstacle>();
        imageList = new ArrayList<BufferedImage>();

        firstX = firstPos;
        minObstacleInterval = 200;
        speed = 11;

        imageList.add(new Resource().getResourceImage("images/Cactus1.png"));
        imageList.add(new Resource().getResourceImage("images/Cactus2.png"));
        imageList.add(new Resource().getResourceImage("images/Cactus3.png"));

        int x = firstX;

        for (BufferedImage bi : imageList) {

            Obstacle ob = new Obstacle();

            ob.image = bi;
            ob.x = x;
            ob.y = Ground.GROUND_Y - bi.getHeight() + 5;
            Random random = new Random();
            x += minObstacleInterval + random.nextInt(300);

            obList.add(ob);
        }
    }

    public void update() {
        Iterator<Obstacle> looper = obList.iterator();

        Obstacle firstOb = looper.next();
        firstOb.x -= speed;

        while (looper.hasNext()) {
            Obstacle ob = looper.next();
            ob.x -= speed;
        }

        if (firstOb.x < -firstOb.image.getWidth()) {
            obList.remove(firstOb);
            Random random = new Random();
            firstOb.x = obList.get(obList.size() - 1).x + minObstacleInterval + random.nextInt(100);
            obList.add(firstOb);
        }
    }

    public void create(Graphics g) {
        for (Obstacle ob : obList) {
            g.setColor(Color.black);
            g.drawImage(ob.image, ob.x, ob.y, null);
        }
    }

    public boolean hasCollided() {
        for (Obstacle ob : obList) {
            if (Dino.getDino().intersects(ob.getObstacle())) {
                System.out.println("Dino = " + Dino.getDino() + "\nObstacle = " + ob.getObstacle() + "\n\n");
                return true;
            }
        }
        return false;
    }

    public void resume() {
        int x = firstX / 2;
        obList = new ArrayList<Obstacle>();

        for (BufferedImage bi : imageList) {

            Obstacle ob = new Obstacle();

            ob.image = bi;
            ob.x = x;
            ob.y = Ground.GROUND_Y - bi.getHeight() + 5;
            Random random = new Random();
            x += minObstacleInterval + random.nextInt(300);

            obList.add(ob);
        }
    }
}
