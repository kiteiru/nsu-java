package ru.ccfit.oop.kiteiru;

import java.awt.*;

public class Paddle {

    private int x, y;
    private int vel = 0;
    private int speed = 10;
    private int width = 22, height = 85;
    private int score = 0;
    private Color color;
    private boolean left; //whether 1 or 2 paddle

    public Paddle(Color c, boolean left) {
        color = c;
        this.left = left;

        if (left) {
            x = 0;
        }
        else {
            x = Game.WIDTH - width;
        }
        y = (Game.HEIGHT / 2) - (height / 2);
    }

    public void addPoint() {
        score++;
    }

    public void draw(Graphics g) {
        //draw paddle
        g.setColor(color);
        g.fillRect(x, y, width, height);

        //draw score
        int sx; //x position of string of a score
        int sy = 70;
        String scoreText = Integer.toString(score);
        Font font = new Font("Junegull", Font.BOLD, 56);

        int strWidth = g.getFontMetrics(font).stringWidth(scoreText) + 1;
        int padding = 25; // 25 pixels

        if(left) {
            sx = (Game.WIDTH / 2) - padding - strWidth;
        }
        else {
            sx = (Game.WIDTH / 2) + padding;
        }
        g.setFont(font);
        g.drawString(scoreText, sx, sy);
    }

    public void update(Ball ball) {
        //update position
        y = Game.ensureRange(y += vel, 0, Game.HEIGHT - height);

        int ballX = ball.getX();
        int ballY = ball.getY();

        //collisions pannels with ball

        if (left) {
            if ((ballX <= width) && (ballY + Ball.SIZE >= y) && (ballY <= y + height)) {
                ball.changeXDir();
            }
        }
        else {
            if ((ballX + Ball.SIZE >= Game.WIDTH - width) && (ballY + Ball.SIZE >= y) && (ballY <= y + height)) {
                ball.changeXDir();
            }
        }
    }

    public void switchDirection(int direction) {
        vel = speed * direction;
    }

    public void stop() {
        vel = 0;
    }
}
