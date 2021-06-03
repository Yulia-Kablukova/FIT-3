package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Objects;

public class GameView extends JFrame implements GameObserver {

    public void start() {

        GameModel model = new GameModel();
        model.addObserver(this);

        GamePanel gamePanel = new GamePanel(model);
        gamePanel.addKeyListener(gamePanel);

/*        addKeyListener(new KeyListener() {
            public void keyPressed(KeyEvent e) {
                model.keyPressed(e);
            }
            public void keyReleased(KeyEvent e) {}

            public void keyTyped(KeyEvent e) {}
        });*/
    }

    @Override
    public void update() {
        SwingUtilities.invokeLater(this::repaint);
    }
}
