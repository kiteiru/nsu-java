package com.kiteiru;

import java.awt.*;

public class Paddle {

    private int x, y;
    private int vel = 0;
    private int speed = 15;
    private int width = 20, height = 105;
    private Color color;
    private boolean leftPlayer;


    public Paddle(Color c, boolean leftPlayer) {
        color = c;
        this.leftPlayer = leftPlayer;

        if (leftPlayer){
            x = 0;
        } else {
            x = Game.WIDTH - width;
        }

        y = (Game.HEIGHT / 2) - (height / 2);

    }


    public void DrawPaddle(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, width, height);
    }

    public void DrawScore(Graphics g, int score) {
        g.setColor(color);

        int sx;
        int sy = 70;
        String scoreText = Integer.toString(score);
        Font font = new Font("Junegull", Font.BOLD, 56);

        int strWidth = g.getFontMetrics(font).stringWidth(scoreText);
        int padding = 25;


        if (leftPlayer) {
            sx = (Game.WIDTH / 2) - padding - strWidth;
        } else {
            sx = (Game.WIDTH / 2) + padding;
        }
        g.setFont(font);
        g.drawString(scoreText, sx, sy);
    }

    public void HitTheBall(Ball ball) {
        y = Game.AvailableMovingRange(y + vel, 0, Game.HEIGHT - height);

        int ballX = ball.GetX();
        int ballY = ball.GetY();

        if (leftPlayer) {
            if ((ballX <= width) && (ballY + Ball.SIZE >= y) && (ballY <= y + height)) {
                ball.ChangeXDir();
            }
        } else {
            if ((ballX + Ball.SIZE >= Game.WIDTH - width) && (ballY + Ball.SIZE >= y) && (ballY <= y + height)) {
                ball.ChangeXDir();
            }
        }

    }

    public void ChangePaddleDirection(int direction) {
        vel = speed * direction;
    }

    public void StopPaddle() {
        vel = 0;
    }

}