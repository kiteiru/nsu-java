package ru.ccfit.oop.kiteiru;


import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Game extends Canvas implements Runnable {

    public static final int WIDTH = 1000;
    public static final int HEIGHT = 600;

    public boolean running = false;
    private Thread gameThread;

    private BufferedImage back;

    private Ball ball;
    private Paddle paddle1;
    private Paddle paddle2;


    public Game(){
        canvasSetup();
        intialize();

        new Window("p i n g   p o n g", this);

        this.addKeyListener(new KeyInput(paddle1, paddle2));
        this.setFocusable(true);
    }

    public static int sign(double d) { //sign of velocity
        if(d <= 0) {
            return -1;
        }
        return 1;
    }

    public static int ensureRange(int value, int min, int max) {

        return Math.min(Math.max(value, min), max);

    }

    private void intialize() {
        //intialize ball
        ball = new Ball();

        //intialize both paddles
        paddle1 = new Paddle(Color.PINK, true);
        paddle2 = new Paddle(Color.ORANGE, false);
    }

    private void canvasSetup() {
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setMaximumSize(new Dimension(WIDTH, HEIGHT));
        this.setMinimumSize(new Dimension(WIDTH, HEIGHT));
    }

    @Override
    public void run() {
        this.requestFocus();
        //game timer

        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while (running){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                update();
                delta--;
            }
            if (running) {
                draw();
            }
            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                //System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
        try {
            stop();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void update() {
        //update ball
        ball.update(paddle1, paddle2);
        //update paddles
        paddle1.update(ball); //check on colision
        paddle2.update(ball);
    }

    private void draw() {
        //intialize drawing tools
        BufferStrategy buffer = this.getBufferStrategy();

        if (buffer == null) {
            this.createBufferStrategy(3);
            return;
        }


        Graphics g = buffer.getDrawGraphics(); //graphic content
        //draw back
        drawBackground(g);

        //draw ball
        ////////ImageIcon duck = new ImageIcon("/graphics/duck.jpg");

        ball.draw(g);



        //draw paddles and score
        paddle1.draw(g);
        paddle2.draw(g);
        //dispose, actually draw
        g.dispose(); //impact on showing
        buffer.show();
    }

    private void drawBackground(Graphics g) {
        //black back
        g.setColor(Color.darkGray);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        //dotted line
        g.setColor(Color.white);
        Graphics2D g2d = (Graphics2D) g;
        Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{10}, 0);
        g2d.setStroke(dashed);
        g2d.drawLine(WIDTH / 2, 0, WIDTH / 2, HEIGHT);


    }

    public void start() {
        gameThread = new Thread(this);
        gameThread.start();
        running = true;
    }


    public void stop() throws InterruptedException {
        try {
            gameThread.join();
            running = false;
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
