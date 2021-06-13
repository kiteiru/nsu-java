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
            x = Model.WIDTH - width;
        }

        y = (Model.HEIGHT / 2) - (height / 2);
    }

    public void DrawPaddle(Graphics g, View view) {
        view.DrawPaddle(g, color, x, y, width, height);
    }

    public void DrawScore(Graphics g, int score, View view) {
        view.DrawScore(g, score, color, leftPlayer);
    }

    public void HitTheBall(Ball ball) {
        y = Model.AvailableMovingRange(y + vel, 0, Model.HEIGHT - height);

        int ballX = ball.GetX();
        int ballY = ball.GetY();

        if (leftPlayer) {
            if ((ballX <= width) && (ballY + Ball.SIZE >= y) && (ballY <= y + height)) {
                ball.ChangeXDir();
            }
        } else {
            if ((ballX + Ball.SIZE >= Model.WIDTH - width) && (ballY + Ball.SIZE >= y) && (ballY <= y + height)) {
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



