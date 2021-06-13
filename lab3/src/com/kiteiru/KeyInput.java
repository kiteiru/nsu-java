package com.kiteiru;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {
    private Paddle leftPaddle;
    private boolean leftPadUp = false;
    private boolean leftPadDown = false;

    private Paddle rightPaddle;
    private boolean rightPadUp = false;
    private boolean rightPadDown = false;

    public KeyInput(Paddle leftPaddle, Paddle rightPaddle) {
        this.leftPaddle = leftPaddle;
        this.rightPaddle = rightPaddle;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_UP) {
            rightPaddle.ChangePaddleDirection(-1);
            rightPadUp = true;
        }
        if (key == KeyEvent.VK_DOWN) {
            rightPaddle.ChangePaddleDirection(1);
            rightPadDown = true;
        }
        if (key == KeyEvent.VK_W) {
            leftPaddle.ChangePaddleDirection(-1);
            leftPadUp = true;
        }
        if (key == KeyEvent.VK_S) {
            leftPaddle.ChangePaddleDirection(1);
            leftPadDown = true;
        }

        if (key == KeyEvent.VK_ESCAPE) {
            System.exit(0);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_UP) {
            rightPadUp = false;
        }
        if (key == KeyEvent.VK_DOWN) {
            rightPadDown = false;
        }
        if (key == KeyEvent.VK_W) {
            leftPadUp = false;
        }
        if (key == KeyEvent.VK_S) {
            leftPadDown = false;
        }

        if (!leftPadUp && !leftPadDown)
            leftPaddle.StopPaddle();
        if (!rightPadUp && !rightPadDown)
            rightPaddle.StopPaddle();
    }
}


