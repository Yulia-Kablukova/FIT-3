package com.company;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Dino {

    private static int dinoTopY;
    private static int dinoStartX;
    private static int dinoTop;
    private static int topPoint;

    private static boolean topPointReached;
    private static final int jumpFactor = 20;

    public static final int STANDING = 1;
    public static final int RUNNING = 2;
    public static final int JUMPING = 3;
    public static final int DIE = 4;

    private final int NO_FOOT = 3;

    private static int state;

    private int foot;

    static BufferedImage dinoStand;
    BufferedImage leftUp;
    BufferedImage rightUp;
    BufferedImage deadDino;

    public Dino() {
        dinoStand = new Resource().getResourceImage("images/Dino-stand.png");
        leftUp = new Resource().getResourceImage("images/Dino-left-up.png");
        rightUp = new Resource().getResourceImage("images/Dino-right-up.png");
        deadDino = new Resource().getResourceImage("images/Dino-big-eyes.png");

        dinoTopY = Ground.GROUND_Y - dinoStand.getHeight() + 5;
        dinoStartX = 100;
        topPoint = dinoTopY - 120;

        state = 1;
        foot = NO_FOOT;
    }

    public void create(Graphics g) {

        int LEFT_FOOT = 1;
        switch(state) {

            case STANDING:
                g.drawImage(dinoStand, dinoStartX, dinoTopY, null);
                break;

            case RUNNING:
                if(foot == NO_FOOT) {
                    foot = LEFT_FOOT;
                    g.drawImage(leftUp, dinoStartX, dinoTopY, null);
                } else if(foot == LEFT_FOOT) {
                    foot = 2;
                    g.drawImage(rightUp, dinoStartX, dinoTopY, null);
                } else {
                    foot = LEFT_FOOT;
                    g.drawImage(leftUp, dinoStartX, dinoTopY, null);
                }
                break;

            case JUMPING:
                if(dinoTop > topPoint && !topPointReached) {
                    g.drawImage(dinoStand, dinoStartX, dinoTop -= jumpFactor, null);
                    break;
                }
                if(dinoTop >= topPoint && !topPointReached) {
                    topPointReached = true;
                    g.drawImage(dinoStand, dinoStartX, dinoTop += jumpFactor, null);
                    break;
                }
                if(dinoTop > topPoint) {
                    if(dinoTopY == dinoTop) {
                        state = RUNNING;
                        topPointReached = false;
                        break;
                    }
                    g.drawImage(dinoStand, dinoStartX, dinoTop += jumpFactor, null);
                    break;
                }
            case DIE:
                g.drawImage(deadDino, dinoStartX, dinoTop, null);
                break;
        }
    }

    public void die() {

        state = DIE;
    }

    public static Rectangle getDino() {
        Rectangle dino = new Rectangle();
        dino.x = dinoStartX;

        if(state == JUMPING && !topPointReached) {
            dino.y = dinoTop - jumpFactor;
        } else if(state == JUMPING) {
            dino.y = dinoTop + jumpFactor;
        }
        else {
            dino.y = dinoTop;
        }

        dino.width = dinoStand.getWidth();
        dino.height = dinoStand.getHeight();

        return dino;
    }

    public void startRunning() {
        dinoTop = dinoTopY;
        state = RUNNING;
    }

    public void jump() {
        dinoTop = dinoTopY;
        topPointReached = false;
        state = JUMPING;
    }
}
