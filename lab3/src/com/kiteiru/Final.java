package com.kiteiru;

import java.awt.*;
import java.awt.event.MouseAdapter;

public class Final extends MouseAdapter {
    public final static int winnerScore = 10;
    private Rectangle winnerButton;
    private Font font;

    public Final(int scoreLeft, Graphics g, View view) {

        int positionX, positionY;
        int winnerWidth = Model.WIDTH / 2, winnerHeight = Model.HEIGHT / 3;

        positionX = Model.WIDTH / 2 - winnerWidth / 2;
        positionY = Model.HEIGHT / 2 - winnerHeight / 2;
        winnerButton = new Rectangle(positionX, positionY, winnerWidth, winnerHeight);

        font = new Font("MS UI Gothic", Font.ROMAN_BASELINE, 35);

        if (scoreLeft == winnerScore) {
            view.DrawWinner(g, true, font, winnerButton);
        } else {
            view.DrawWinner(g, false, font, winnerButton);
        }
    }
}