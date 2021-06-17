package com.kiteiru;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Menu extends MouseAdapter {
    public boolean check;

    private Rectangle playButton;
    private Rectangle exitButton;
    private Rectangle infoButton;

    private Font playExitFont;
    private Font infoFont;

    private boolean playHighlight = false;
    private boolean exitHighlight = false;

    private GameStatus gameStatus;

    View view;

    public Menu(Model model) {
        this.view = view;

        check = true;
        model.StartThread();

        int positionX, positionY;
        int buttonWidth = Model.WIDTH / 2 - 105, buttonHeight = 135;
        int infoWidth = Model.WIDTH / 2 - 105, infoHeight = Model.HEIGHT - 225;

        positionX = Model.WIDTH / 2 + 50;
        positionY = Model.HEIGHT / 2 - 135 - 50;
        playButton = new Rectangle(positionX, positionY, buttonWidth, buttonHeight);

        positionY = Model.HEIGHT / 2 - 135 + 190;
        exitButton = new Rectangle(positionX, positionY, buttonWidth, buttonHeight);

        positionX = Model.WIDTH / 2 - infoWidth / 2 - 245;
        positionY = Model.HEIGHT / 2 - 185;
        infoButton = new Rectangle(positionX, positionY, infoWidth, infoHeight);

        playExitFont = new Font("MS UI Gothic", Font.ROMAN_BASELINE, 80);
        infoFont = new Font("MS UI Gothic", Font.ROMAN_BASELINE, 35);


    }

    public void SetMenu(Graphics g) {
        view.DrawMenu(g, playExitFont, infoFont, playHighlight,
                exitHighlight, playButton, exitButton, infoButton);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Point p = e.getPoint();

        if (playButton.contains(p)) {
            check = false;
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

    public Menu GetMenu() {
        return this;
    }

}


