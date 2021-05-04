package com.kiteiru;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Final extends MouseAdapter {
    private static final long serialVersionUID = 5162710183389028792L;

    public boolean active;

    private Rectangle playButton;
    private Rectangle winnerButton;

    private Font font;

    private boolean playHighlight = false;
    private boolean winnerHighlight = false;


    public Final(Game game) {
        active = true;

        int positionX, positionY, buttonWidth = 400, buttonHeight = 135;
        int winnerWidth = Game.WIDTH - 270, winnerHeight = 200;

        positionX = Game.WIDTH / 2 - buttonWidth / 2;
        positionY = Game.HEIGHT / 2 - buttonHeight - 50;
        playButton = new Rectangle(positionX, positionY, buttonWidth, buttonHeight);


        positionX = Game.WIDTH / 2 - winnerWidth / 2;
        positionY = Game.HEIGHT / 2 - winnerHeight + 250;
        winnerButton = new Rectangle(positionX, positionY, winnerWidth, winnerHeight);

        font = new Font("Roboto", Font.PLAIN, 50);
    }

    public void DrawWinner(Graphics g, boolean leftPlayer) {
        Graphics2D g2d = (Graphics2D) g;
        g.setFont(font);

        g.setColor(Color.decode("0xF5BCA3"));
        if (playHighlight)
            g.setColor(Color.decode("0xEB7847"));
        g2d.fill(playButton);

        g.setColor(Color.decode("0xCFD2DE"));
        g2d.fill(winnerButton);

        g.setColor(Color.decode("0xFFFFFF"));
        g2d.draw(playButton);
        g2d.draw(winnerButton);

        int strWidth, strHeight;

        strWidth = g.getFontMetrics(font).stringWidth("Take revenge");
        strHeight = g.getFontMetrics(font).getHeight();

        g.setColor(Color.decode("0x2D3142"));
        g.drawString("Take revenge", (int) (playButton.getX() + playButton.getWidth() / 2 - strWidth / 2),
                (int) (playButton.getY() + playButton.getHeight() / 2 + strHeight / 4));

        if (leftPlayer) {
            strWidth = g.getFontMetrics(font).stringWidth("The winner is left player!");
        } else {
            strWidth = g.getFontMetrics(font).stringWidth("The winner is right player!");
        }
        //strHeight = g.getFontMetrics(font).getHeight();

        g.setColor(Color.decode("0x2D3142"));
        if (leftPlayer) {
            g.drawString("The winner is left player!", (int) (winnerButton.getX() + winnerButton.getWidth() / 2 - strWidth / 2),
                    (int) (winnerButton.getY() + winnerButton.getHeight() - 120));
        } else {
            g.drawString("The winner is right player!", (int) (winnerButton.getX() + winnerButton.getWidth() / 2 - strWidth / 2),
                    (int) (winnerButton.getY() + winnerButton.getHeight() - 120));
        }
        g.drawString("Congratulations(*¯︶¯*)", (int) (winnerButton.getX() + winnerButton.getWidth() / 2 - strWidth / 2 + 20),
                (int) (winnerButton.getY() + winnerButton.getHeight() - 60));

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Point p = e.getPoint();

        if (playButton.contains(p)) {
            active = false;
        }
        else if (winnerButton.contains(p)) {
            System.exit(0);
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        Point p = e.getPoint();

        playHighlight = playButton.contains(p);
        winnerHighlight = winnerButton.contains(p);

    }
}