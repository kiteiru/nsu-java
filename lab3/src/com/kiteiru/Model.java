package com.kiteiru;

import java.awt.*;

public class Model extends Canvas implements Runnable  {

    public final static int winnerScore = 10;
    public int scoreLeft = 0;
    public int scoreRight = 0;

    public final static int WIDTH = 1000;
    public final static int HEIGHT = 600;

    public boolean running = false;
    private Thread modelThread;

    private Ball ball;
    private Paddle leftPaddle;
    private Paddle rightPaddle;
    private Menu menu;
    private GameStatus gameStatus;

    public Model() {
        //View view = new View(this);
        //this.view = view;
    }

    public void Play() {
        gameStatus = GameStatus.PLAY;
        //view.SetupCanvas();
        //view.Window(this);

        InitialiseObjects();

        this.addKeyListener(new KeyInput(leftPaddle, rightPaddle));
        this.addMouseListener(menu);
        this.addMouseMotionListener(menu);
        this.setFocusable(true);

    }

    void InitialiseObjects() {
        ball = new Ball();

        leftPaddle = new Paddle(true);
        rightPaddle = new Paddle(false);

        menu = new Menu(this);
    }

    @Override
    public void run() { ////TODO MODEL
        this.requestFocus();

        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if (delta >= 1) {
                UpdateObjPositions();
                delta--;
                gameStatus = GameStatus.UPDATE;
                //view.DrawEnvironment(this, ball, leftPaddle, rightPaddle, scoreLeft, scoreRight, menu);
            }

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
            }
        }
        StopThread();
    }

    public synchronized void StartThread() {
        modelThread = new Thread(this);
        modelThread.start();
        running = true;
    }

    public void StopThread() {
        try {
            modelThread.join();
            running = false;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void UpdateObjPositions() {
        if (!(menu.check) && (scoreLeft != winnerScore && scoreRight != winnerScore)) {
            ball.ChangeBallDir();
            scoreLeft = ball.UpdateScore(true, scoreLeft);
            scoreRight = ball.UpdateScore(false, scoreRight);

            leftPaddle.HitTheBall(ball);
            rightPaddle.HitTheBall(ball);
        }
    }

    public static int AvailableMovingRange(int value, int min, int max) {
        return Math.min(Math.max(value, min), max);
    }

    public GameStatus GetGameStatus() {
        return gameStatus;
    }

}




