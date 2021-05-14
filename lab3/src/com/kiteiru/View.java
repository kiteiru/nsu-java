package com.kiteiru;

import javax.swing.*;
import java.awt.*;


public class View {
    private Model model;

    public View(Model model){
        this.model = model;
    }

    public void Window(String title, Model model) {
        JFrame frame = new JFrame(title);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.add(model);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void DrawObjects(Graphics g, Ball ball, Paddle leftPaddle, Paddle rightPaddle, int scoreLeft, int scoreRight) {
        ball.DrawBall(g, this);

        leftPaddle.DrawPaddle(g, this);
        rightPaddle.DrawPaddle(g, this);

        leftPaddle.DrawScore(g, scoreLeft, this);
        rightPaddle.DrawScore(g, scoreRight, this);
    }

    void DrawBackground(Graphics g, String backColor) {
        g.setColor(Color.decode(backColor));
        g.fillRect(0, 0, model.WIDTH, model.HEIGHT);

        g.setColor(Color.white);
        Graphics2D g2d = (Graphics2D) g;
        Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, null, 0);
        g2d.setStroke(dashed);
        g.drawLine(model.WIDTH / 2, 0, model.WIDTH / 2, model.HEIGHT);
    }

    public void DrawBall(Graphics g, int x, int y, int SIZE) {
        g.setColor(Color.decode("0xFFFFFF"));
        g.fillOval(x, y, SIZE, SIZE);
    }

    public void DrawPaddle(Graphics g, Color color, int x, int y, int width, int height) {
        g.setColor(color);
        g.fillRect(x, y, width, height);
    }

    public void DrawScore(Graphics g, int score, Color color, boolean leftPlayer) {
        g.setColor(color);

        int sx;
        int sy = 70;
        String scoreText = Integer.toString(score);
        Font font = new Font("MS UI Gothic", Font.BOLD, 56);

        int strWidth = g.getFontMetrics(font).stringWidth(scoreText);
        int padding = 25;


        if (leftPlayer) {
            sx = (Model.WIDTH / 2) - padding - strWidth;
        } else {
            sx = (Model.WIDTH / 2) + padding;
        }
        g.setFont(font);
        g.drawString(scoreText, sx, sy);
    }

    public void DrawMenu(Graphics g, Font playExitFont, Font infoFont, boolean playHighlight,
                         boolean exitHighlight, Rectangle playButton, Rectangle exitButton, Rectangle infoButton) {
        Graphics2D g2d = (Graphics2D) g;
        g.setFont(playExitFont);

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

        int strWidth = g.getFontMetrics(playExitFont).stringWidth("play");
        int strHeight = g.getFontMetrics(playExitFont).getHeight();

        g.setColor(Color.decode("0x2D3142"));
        g.drawString("play", (int) (playButton.getX() + playButton.getWidth() / 2 - strWidth / 2),
                (int) (playButton.getY() + playButton.getHeight() / 2 + strHeight / 4));

        strWidth = g.getFontMetrics(playExitFont).stringWidth("exit");
        strHeight = g.getFontMetrics(playExitFont).getHeight();

        g.setColor(Color.decode("0x2D3142"));
        g.drawString("exit", (int) (exitButton.getX() + exitButton.getWidth() / 2 - strWidth / 2),
                (int) (exitButton.getY() + exitButton.getHeight() / 2 + strHeight / 4 + 5));


        g.setFont(infoFont);

        g.setColor(Color.decode("0x2D3142"));
        drawString(g, "You'll be playing till one\nof players got 10 points!\n\nLeft player controls\n" +
                        "paddle with AWSD keys\nand right player — with\narrow keys.\n\nGood luck!\t(ﾉ◕ヮ◕)ﾉ*:･ﾟ✧",
                (int) (infoButton.getX() + infoButton.getWidth() / 2 - 175),
                (int) (infoButton.getY() + 15));
    }

    private void drawString(Graphics g, String text, int x, int y) {
        for (String line : text.split("\n"))
            g.drawString(line, x, y += g.getFontMetrics().getHeight());
    }

    public void DrawWinner(Graphics g, boolean leftPlayer, Font font, Rectangle winnerButton) {
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
    }
}
