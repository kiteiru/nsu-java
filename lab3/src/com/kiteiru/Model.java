package com.kiteiru;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Model extends Canvas implements Runnable {

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

    private String leftColor = "0xBFC0C0";
    private String rightColor = "0xF2A07D";
    private String backColor = "0x2D3142";

    private Menu menu;
    private Final fin;

    View view;

    private String title = "ピ ン ポ ン";

    public Model() {
        View view = new View(this);
        this.view = view;
    }

    public void Play() {
        SetupCanvas();
        view.Window(title, this);

        InitialiseObjects();

        this.addKeyListener(new KeyInput(leftPaddle, rightPaddle));
        this.addMouseListener(menu);
        this.addMouseMotionListener(menu);
        this.setFocusable(true);

    }

    private void InitialiseObjects() {
        ball = new Ball();

        leftPaddle = new Paddle(Color.decode(leftColor), true);
        rightPaddle = new Paddle(Color.decode(rightColor), false);
        menu = new Menu(this, view);
    }

    private void SetupCanvas() {
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setMaximumSize(new Dimension(WIDTH, HEIGHT));
        this.setMinimumSize(new Dimension(WIDTH, HEIGHT));
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
                DrawEnvironment();
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

    public void DrawEnvironment() { ////TODO VIEW
        BufferStrategy buffer = this.getBufferStrategy();
        if (buffer == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = buffer.getDrawGraphics();
        view.DrawBackground(g, backColor);

        //view.StartMenu(g, menu);
        view.DrawObjects(g, ball, leftPaddle, rightPaddle, scoreLeft, scoreRight);

        if (menu.check) {
            menu.SetMenu(g);
        }

        /*ball.DrawBall(g);

        leftPaddle.DrawPaddle(g);
        rightPaddle.DrawPaddle(g);

        leftPaddle.DrawScore(g, scoreLeft);
        rightPaddle.DrawScore(g, scoreRight);*/

        if ((scoreLeft == winnerScore || scoreRight == winnerScore)) {
            fin = new Final(scoreLeft, g, view);
            /*if (scoreLeft == winnerScore) {
                fin.DrawWinner(g, true);
            } else {
                fin.DrawWinner(g, false);
            }*/
        }

        g.dispose();
        buffer.show();
    }

    /*private void DrawBackground(Graphics g) {
        g.setColor(Color.decode(backColor));
        g.fillRect(0, 0, WIDTH, HEIGHT);

        g.setColor(Color.white);
        Graphics2D g2d = (Graphics2D) g;
        Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, null, 0);
        g2d.setStroke(dashed);
        g.drawLine(WIDTH / 2, 0, WIDTH / 2, HEIGHT);
    }*/

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
}
