package ru.ccfit.oop.kiteiru;

import javax.swing.*;


public class Window {
    public Window(String title, Game game) {
        JFrame frame = new JFrame(title);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.add(game);
        frame.pack();
        frame.setLocationRelativeTo(null); //game appears centered
        frame.setVisible(true);

        game.start();
    }
}
