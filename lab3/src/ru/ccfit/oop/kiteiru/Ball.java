package ru.ccfit.oop.kiteiru;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Ball {
    public static final int SIZE = 45;

    private int x, y;
    private int xVel, yVel; //coordinate velocity
    private int speed = 5;


    private BufferedImage ballImage;

    public Ball () {
        reset();
    }

    private void reset() {
        //initial position
        x = (Game.WIDTH / 2) - (SIZE / 2);
        y = (Game.HEIGHT / 2) - (SIZE / 2);

        //initial velocity
        xVel = Game.sign(Math.random() * 2.0 - 1);
        yVel = Game.sign(Math.random() * 2.0 - 1);
    }

    public void changeXDir() { //change x direction
        xVel *= -1;
    }

    public void changeYDir() { //change y direction
        yVel *= -1;
    }

    public void draw(Graphics g) {


        g.drawImage(ballImage, x, y, SIZE, SIZE, null);
        g.setColor(Color.white);
        g.fillOval(x, y, SIZE, SIZE);
    }

    public void update(Paddle paddle1, Paddle paddle2) {
        //update movement of ball
        x += xVel * speed;
        y += yVel * speed;

        //collisions
        if ((y + SIZE) >= Game.HEIGHT || y <= 0) {
            changeYDir();
            //with walls
            if ((x + SIZE) >= Game.WIDTH) {
                paddle1.addPoint();
                reset();
            }
            if (x <= 0) {
                paddle2.addPoint();
                reset();
            }

        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    /*public void checkScoreOnWin(int score) {
        if (score == )
    }*/
}
