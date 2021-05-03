package com.kiteiru;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable {

    //private static final long serialVersionUID = 1L;

    public final static int winnerScore = 2;
    public int scoreLeft = 0;
    public int scoreRight = 0;

    public final static int WIDTH = 1000;
    public final static int HEIGHT = 600;

    public boolean running = false;
    private Thread gameThread;

    private Ball ball;
    private Paddle leftPaddle;
    private Paddle rightPaddle;

    private MainMenu menu;

    private Final fin;
    private BufferStrategy buffer;
    private int t = 1;

    private Graphics g;

    public Game() {

        SetupCanvas();

        new Window("p i n g   p o n g", this);

        InitialiseObjects();

        this.addKeyListener(new KeyInput(leftPaddle, rightPaddle));
        this.addMouseListener(menu);
        this.addMouseMotionListener(menu);
        this.setFocusable(true);

    }

    private void InitialiseObjects() {
        ball = new Ball();

        leftPaddle = new Paddle(Color.decode("0xBFC0C0"), true);
        rightPaddle = new Paddle(Color.decode("0xF2A07D"), false);

        menu = new MainMenu(this);
        fin = new Final(this);
    }

    private void SetupCanvas() {
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setMaximumSize(new Dimension(WIDTH, HEIGHT));
        this.setMinimumSize(new Dimension(WIDTH, HEIGHT));
    }

    @Override
    public void run() {
        this.requestFocus();

        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if (delta >= 1) {
                UpdateObjPositions();
                delta--;
                DrawEnvironment();
                frames++;
            }

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                frames = 0;
            }
        }

        StopThread();
    }

    public synchronized void StartThread() {
        gameThread = new Thread(this);
        gameThread.start();
        running = true;
    }

    public void StopThread() {
        try {
            gameThread.join();
            running = false;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void DrawEnvironment() {
        BufferStrategy buffer = this.getBufferStrategy();

        if (buffer == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = buffer.getDrawGraphics();
        DrawBackground(g);

        if (menu.active)
            menu.DrawMenu(g);

        ball.DrawBall(g);

        leftPaddle.DrawPaddle(g);
        rightPaddle.DrawPaddle(g);

        leftPaddle.DrawScore(g, scoreLeft);
        rightPaddle.DrawScore(g, scoreRight);

        if ((scoreLeft == winnerScore || scoreRight ==  winnerScore)) {

            if (fin.active) {
                fin.DrawWinner(g);
            } else {
                scoreLeft = 0;
                scoreRight = 0;
            }


        }

        g.dispose();
        buffer.show();

    }

    private void DrawBackground(Graphics g) {
        g.setColor(Color.decode("0x2D3142"));
        g.fillRect(0, 0, WIDTH, HEIGHT);

        g.setColor(Color.white);
        Graphics2D g2d = (Graphics2D) g;
        Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, null, 0);
        g2d.setStroke(dashed);
        g.drawLine(WIDTH / 2, 0, WIDTH / 2, HEIGHT);
    }

    public void UpdateObjPositions() {

        if (!(menu.active) && (scoreLeft != winnerScore && scoreRight != winnerScore)) {
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
