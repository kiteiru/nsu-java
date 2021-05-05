package com.kiteiru;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Menu extends MouseAdapter {

    public boolean active;

    private Rectangle playButton;
    private Rectangle exitButton;
    private Rectangle infoButton;

    private Font font;
    private Font infoFont;

    private boolean playHighlight = false;
    private boolean exitHighlight = false;

    public Menu(Game game) {

        active = true;
        game.StartThread();


        int positionX, positionY;
        int buttonWidth = Game.WIDTH / 2 - 105, buttonHeight = 135;
        int infoWidth = Game.WIDTH / 2 - 105, infoHeight = Game.HEIGHT - 225;

        positionX = Game.WIDTH / 2 + 50;
        positionY = Game.HEIGHT / 2 - 135 - 50;
        playButton = new Rectangle(positionX, positionY, buttonWidth, buttonHeight);

        positionY = Game.HEIGHT / 2 - 135 + 190;
        exitButton = new Rectangle(positionX, positionY, buttonWidth, buttonHeight);

        positionX = Game.WIDTH / 2 - infoWidth / 2 - 245;
        positionY = Game.HEIGHT / 2 - 185;
        infoButton = new Rectangle(positionX, positionY, infoWidth, infoHeight);

        font = new Font("MS UI Gothic", Font.ROMAN_BASELINE, 80);
        infoFont = new Font("MS UI Gothic", Font.ROMAN_BASELINE, 35);
    }

    public void DrawMenu(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g.setFont(font);
        //g.setFont(infoFont);

        g.setColor(Color.decode("0xF0936A"));
        if (playHighlight) {
            g.setColor(Color.decode("0xEB6A33"));
        }
        g2d.fill(playButton);

        g.setColor(Color.decode("0x9EA4BD"));
        if (exitHighlight) {
            g.setColor(Color.decode("0x6D779C"));
        }
        g2d.fill(exitButton);

        g.setColor(Color.decode("0xCFD2DE"));
        g2d.fill(infoButton);

        g.setColor(Color.decode("0xFFFFFF"));
        g2d.draw(playButton);
        g2d.draw(exitButton);
        g2d.draw(infoButton);

        int strWidth, strHeight;

        strWidth = g.getFontMetrics(font).stringWidth("play");
        strHeight = g.getFontMetrics(font).getHeight();

        g.setColor(Color.decode("0x2D3142"));
        g.drawString("play", (int) (playButton.getX() + playButton.getWidth() / 2 - strWidth / 2),
                (int) (playButton.getY() + playButton.getHeight() / 2 + strHeight / 4));

        strWidth = g.getFontMetrics(font).stringWidth("exit");
        strHeight = g.getFontMetrics(font).getHeight();

        g.setColor(Color.decode("0x2D3142"));
        g.drawString("exit", (int) (exitButton.getX() + exitButton.getWidth() / 2 - strWidth / 2),
                (int) (exitButton.getY() + exitButton.getHeight() / 2 + strHeight / 4 + 5));


        g.setFont(infoFont);

        g.setColor(Color.decode("0x2D3142"));
        drawString(g, "You'll be playing till one\nof players got 10 points!\n\nLeft player controls\npaddle with AWSD keys\nand right player — with\narrow keys.\n\nGood luck!\t(ﾉ◕ヮ◕)ﾉ*:･ﾟ✧", (int) (infoButton.getX() + infoButton.getWidth() / 2 - 175),
                (int) (infoButton.getY() + 15));
    }

    private void drawString(Graphics g, String text, int x, int y) {
        for (String line : text.split("\n"))
            g.drawString(line, x, y += g.getFontMetrics().getHeight());
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Point p = e.getPoint();

        if (playButton.contains(p)) {
            active = false;
        } else if (exitButton.contains(p)) {
            System.exit(0);
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        Point p = e.getPoint();

        playHighlight = playButton.contains(p);
        exitHighlight = exitButton.contains(p);
    }

}