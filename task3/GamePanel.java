package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Objects;

public class GamePanel extends JPanel implements KeyListener, Runnable {

    private Thread animator;
    GameModel model;

    public GamePanel(GameModel _model) {

        model = _model;

        JFrame mainWindow = new JFrame("T-Rex");
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageIcon img = new ImageIcon(Objects.requireNonNull(getClass().getResource("images/Icon.png")));
        mainWindow.setIconImage(img.getImage());

        Container container = mainWindow.getContentPane();
        container.setLayout(new BorderLayout());
        container.add(this, BorderLayout.CENTER);

        mainWindow.setSize(GameModel.WIDTH, GameModel.HEIGHT);
        mainWindow.setLocationRelativeTo(null);
        mainWindow.setResizable(false);
        mainWindow.setVisible(true);
        setFocusable(true);
    }

    public void paint(Graphics g) {
        super.paint(g);
        g.setFont(new Font("Courier New", Font.BOLD, 25));
        g.drawString(Integer.toString(model.getScore()), getWidth() - 100, 50);
        model.ground.create(g);
        model.dino.create(g);
        model.obstacles.create(g);
    }

    public void run() {
        model.setRunning(true);
        while(model.isRunning()) {
            model.updateGame();
            repaint();
            try {
                Thread.sleep(50);
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void keyTyped(KeyEvent e) {
        if(e.getKeyChar() == ' ') {
            if(model.isGameOver()) model.reset();
            if (animator == null || !model.isRunning()) {
                animator = new Thread(this);
                animator.start();
                model.dino.startRunning();
            } else {
                model.dino.jump();
            }
        }
    }

    public void keyPressed(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
    }
}