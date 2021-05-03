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

        int positionX, positionY, buttonWidth = 275, buttonHeight = 135;

        positionX = Game.WIDTH / 2 - buttonWidth / 2;

        positionY = Game.HEIGHT / 2 - buttonHeight - 50;
        playButton = new Rectangle(positionX, positionY, buttonWidth, buttonHeight);

        positionY = Game.HEIGHT / 2 - buttonHeight + 190;
        winnerButton = new Rectangle(positionX, positionY, buttonWidth, buttonHeight);

        font = new Font("Roboto", Font.PLAIN, 100);
    }

    public void DrawWinner(Graphics g) {
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

        strWidth = g.getFontMetrics(font).stringWidth("Play again");
        strHeight = g.getFontMetrics(font).getHeight();

        g.setColor(Color.decode("0x2D3142"));
        g.drawString("Play", (int) (playButton.getX() + playButton.getWidth() / 2 - strWidth / 2),
                (int) (playButton.getY() + playButton.getHeight() / 2 + strHeight / 4));

        strWidth = g.getFontMetrics(font).stringWidth("Winner");
        strHeight = g.getFontMetrics(font).getHeight();

        g.setColor(Color.decode("0x2D3142"));
        g.drawString("Exit", (int) (winnerButton.getX() + winnerButton.getWidth() / 2 - strWidth / 2),
                (int) (winnerButton.getY() + winnerButton.getHeight() / 2 + strHeight / 4));
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Point p = e.getPoint();

        if (playButton.contains(p))
            active = false;
        else if (winnerButton.contains(p))
            System.exit(0);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        Point p = e.getPoint();

        playHighlight = playButton.contains(p);
        winnerHighlight = winnerButton.contains(p);

    }
}