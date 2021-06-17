package com.kiteiru;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import com.kiteiru.GameStatus;


public class View {

    public static final int SIZE = 35;
    private int widthPaddle = 20, heightPaddle = 105;

    private Model model;

    int xBall;
    int yBall;
    int xLeftPaddle;
    int yLeftPaddle;
    int xRightPaddle;
    int yRightPaddle;

    private Rectangle winnerButton;
    private Font font;

    private Ball ball;
    private Paddle leftPaddle;
    private Paddle rightPaddle;
    private Menu menu;

    private String leftColor = "0xBFC0C0";
    private String rightColor = "0xF2A07D";
    private String backColor = "0x2D3142";

    public final static int WIDTH = 1000;
    public final static int HEIGHT = 600;

    public final static int winnerScore = 10;
    private int scoreLeft;
    private int scoreRight;

    private Graphics g;

    private String title = "ピ ン ポ ン";

    public View(Model model){
        this.model = model;
    }


    void SetupCanvas() {
        model.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        model.setMaximumSize(new Dimension(WIDTH, HEIGHT));
        model.setMinimumSize(new Dimension(WIDTH, HEIGHT));
    }

    public void Window(Model model) {
        JFrame frame = new JFrame(title);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.add(model);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void CheckGameStatus() {
        if (model.GetGameStatus() == GameStatus.PLAY) {
            SetupCanvas();
            Window(model);
        } else if (model.GetGameStatus() == GameStatus.UPDATE) {
            this.ball = ball.GetBall();
            this.leftPaddle = leftPaddle.GetPaddle();
            this.rightPaddle = rightPaddle.GetPaddle();
            this.menu = menu.GetMenu();
            DrawEnvironment();
        }
    }

    public void DrawEnvironment() {
        BufferStrategy buffer = model.getBufferStrategy();
        if (buffer == null) {
            model.createBufferStrategy(3);
            return;
        }

        g = buffer.getDrawGraphics();
        DrawBackground(backColor);
        DrawObjects();

        if (menu.check) {
            menu.SetMenu();
        }

        this.scoreLeft = model.GetScoreLeft();
        this.scoreRight = model.GetScoreRight();

        if ((scoreLeft == winnerScore || scoreRight == winnerScore)) {
            DrawWinner();
        }

        g.dispose();
        buffer.show();
    }

    public void DrawObjects() {
        DrawBall();

        DrawLeftPaddle();
        DrawRightPaddle();

        DrawScore(scoreLeft, Color.decode(leftColor), true);
        DrawScore(scoreRight, Color.decode(rightColor), false);
    }

    void DrawBackground(String backColor) {
        g.setColor(Color.decode(backColor));
        g.fillRect(0, 0, model.WIDTH, model.HEIGHT);

        g.setColor(Color.white);
        Graphics2D g2d = (Graphics2D) g;
        Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, null, 0);
        g2d.setStroke(dashed);
        g.drawLine(model.WIDTH / 2, 0, model.WIDTH / 2, model.HEIGHT);
    }

    public void DrawBall() {
        g.setColor(Color.decode("0xFFFFFF"));
        xBall = ball.GetX();
        yBall = ball.GetY();
        g.fillOval(xBall, yBall, SIZE, SIZE);
    }

    public void DrawLeftPaddle() {
        g.setColor(Color.decode(leftColor));
        xLeftPaddle = leftPaddle.GetX();
        yLeftPaddle = leftPaddle.GetY();
        g.fillRect(xLeftPaddle, yLeftPaddle, widthPaddle, heightPaddle);
    }

    public void DrawRightPaddle() {
        g.setColor(Color.decode(rightColor));
        xRightPaddle = rightPaddle.GetX();
        yRightPaddle = rightPaddle.GetY();
        g.fillRect(xRightPaddle, yRightPaddle, widthPaddle, heightPaddle);
    }

    public void DrawScore(int score, Color color, boolean leftPlayer) {
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

    public void DrawMenu(Font playExitFont, Font infoFont, boolean playHighlight,
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

    public void DrawWinner() {
        int winnerWidth = Model.WIDTH / 2, winnerHeight = Model.HEIGHT / 3;

        int positionX = Model.WIDTH / 2 - winnerWidth / 2;
        int positionY = Model.HEIGHT / 2 - winnerHeight / 2;
        winnerButton = new Rectangle(positionX, positionY, winnerWidth, winnerHeight);
        font = new Font("MS UI Gothic", Font.ROMAN_BASELINE, 35);

        Graphics2D g2d = (Graphics2D) g;
        g.setFont(font);

        if (scoreLeft == winnerScore) {
            g.setColor(Color.decode("0xCFD2DE"));
        } else {
            g.setColor(Color.decode("0xF5BCA3"));
        }
        g2d.fill(winnerButton);

        g.setColor(Color.decode("0xFFFFFF"));
        g2d.draw(winnerButton);

        int strWidth;

        if (scoreLeft == winnerScore) {
            strWidth = g.getFontMetrics(font).stringWidth("The winner is left player!");
        } else {
            strWidth = g.getFontMetrics(font).stringWidth("The winner is right player!");
        }

        g.setColor(Color.decode("0x2D3142"));
        g.drawString("。。。", (int) (winnerButton.getX() + winnerButton.getWidth() / 2 - 30),
                (int) (winnerButton.getY() + winnerButton.getHeight() - 175));
        if (scoreLeft == winnerScore) {
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





