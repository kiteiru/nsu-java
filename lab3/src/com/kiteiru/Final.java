package com.kiteiru;

import java.awt.*;
import java.awt.event.MouseAdapter;

public class Final extends MouseAdapter {
    private Rectangle winnerButton;
    private Font font;

    public Final() {

        int positionX, positionY;
        int winnerWidth = Game.WIDTH - 240, winnerHeight = 230;


        positionX = Game.WIDTH / 2 - winnerWidth / 2;
        positionY = Game.HEIGHT / 2 - winnerHeight / 2;
        winnerButton = new Rectangle(positionX, positionY, winnerWidth, winnerHeight);

        font = new Font("MS UI Gothic", Font.ROMAN_BASELINE, 50);
    }

    public void DrawWinner(Graphics g, boolean leftPlayer) {
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
        if (leftPlayer) {
            g.drawString("The winner is the left player!", (int) (winnerButton.getX() + winnerButton.getWidth() / 2 - strWidth / 2 - 50),
                    (int) (winnerButton.getY() + winnerButton.getHeight() - 160));
        } else {
            g.drawString("The winner is the right player!", (int) (winnerButton.getX() + winnerButton.getWidth() / 2 - strWidth / 2 - 50),
                    (int) (winnerButton.getY() + winnerButton.getHeight() - 160));
        }
        g.drawString("Congratulations(*¯v¯*)", (int) (winnerButton.getX() + winnerButton.getWidth() / 2 - strWidth / 2 + 10),
                (int) (winnerButton.getY() + winnerButton.getHeight() - 100));
        g.drawString("Press ESC to exit", (int) (winnerButton.getX() + winnerButton.getWidth() / 2 - strWidth / 2 + 70),
                (int) (winnerButton.getY() + winnerButton.getHeight() - 40));

    }
}