package com.kiteiru;

import java.awt.Color;
import java.awt.Graphics;

public class Ball {

    public static final int SIZE = 35;

    private int x, y;
    private int xVel, yVel;
    private int speed = 5;
    private double acceleration = 1;

    public Ball() {
        ResetBall();
    }

    private void ResetBall() {
        x = (Game.WIDTH / 2) - (SIZE / 2);
        y = (Game.HEIGHT / 2) - (SIZE / 2);

        xVel = Ball.ResetStartBallDir(Math.random() * 2.0 - 1);
        yVel = Ball.ResetStartBallDir(Math.random() * 2.0 - 1);
    }

    public void DrawBall(Graphics g) {
        g.setColor(Color.decode("0xFFFFFF"));
        g.fillOval(x, y, SIZE, SIZE);
    }

    public void ChangeBallDir() {

        x += xVel * (speed + acceleration);
        y += yVel * (speed + acceleration);
        acceleration += 0.0075;

        if ((y + SIZE) >= Game.HEIGHT || y <= 0) {
            ChangeYDir();
        }

    }

    public int UpdateScore(boolean leftPaddle, int score) {
        if (leftPaddle) {
            if ((x + SIZE) >= Game.WIDTH) {
                score++;
                ResetBall();
            }
        } else {
            if (x <= 0) {
                score++;
                ResetBall();
            }
        }
        return score;
    }

    public int GetX() {
        return x;
    }

    public int GetY() {
        return y;
    }

    public void ChangeXDir() {
        xVel *= -1;
    }

    public void ChangeYDir() {
        yVel *= -1;
    }

    public static int ResetStartBallDir(double d) {
        if (d <= 0) {
            return -1;
        }
        return 1;
    }

}
