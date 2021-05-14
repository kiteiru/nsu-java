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

    /*public void DrawWinner(Graphics g, boolean leftPlayer) {
        Graphics2D g2d = (Graphics2D) g;
        g.setFont(font);

        if (leftPlayer) {
            g.setColor(Color.decode("0xCFD2DE"));
        } else {
            g.setColor(Color.decode("0xF5BCA3"));
        }
        g2d.fill(winnerButton);

        g.setColor(Color.decode("0xFFFFFF"));
        g2d.draw(winnerButton);

        int strWidth;

        if (leftPlayer) {
            strWidth = g.getFontMetrics(font).stringWidth("The winner is left player!");
        } else {
            strWidth = g.getFontMetrics(font).stringWidth("The winner is right player!");
        }

        g.setColor(Color.decode("0x2D3142"));
        g.drawString("。。。", (int) (winnerButton.getX() + winnerButton.getWidth() / 2 - 30),
                (int) (winnerButton.getY() + winnerButton.getHeight() - 175));
        if (leftPlayer) {
            g.drawString("The winner is the left player!", (int) (winnerButton.getX() + winnerButton.getWidth() / 2 - strWidth / 2 - 30),
                    (int) (winnerButton.getY() + winnerButton.getHeight() - 132));
        } else {
            g.drawString("The winner is the right player!", (int) (winnerButton.getX() + winnerButton.getWidth() / 2 - strWidth / 2 - 30),
                    (int) (winnerButton.getY() + winnerButton.getHeight() - 132));
        }
        g.drawString("Congratulations(*¯v¯*)", (int) (winnerButton.getX() + winnerButton.getWidth() / 2 - strWidth / 2 + 15),
                (int) (winnerButton.getY() + winnerButton.getHeight() - 87));
        g.drawString("Press ESC to exit", (int) (winnerButton.getX() + winnerButton.getWidth() / 2 - strWidth / 2 + 55),
                (int) (winnerButton.getY() + winnerButton.getHeight() - 42));
        g.drawString("。。。", (int) (winnerButton.getX() + winnerButton.getWidth() / 2 - 30),
                (int) (winnerButton.getY() + winnerButton.getHeight() - 20));
    }*/
}