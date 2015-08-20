package AssignmentOne;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Saif Asad
 */
public class Ball {

    private double deltaT;
    private double radius;
    private double positionX;
    private double positionY;
    private double velocityX;
    private double velocityY;
    private static Random random;
    private Color color;
    private Thread ballThread;
    private boolean isRunning;

    @SuppressWarnings("OverridableMethodCallInConstructor")
    public Ball() {
        setPositionX(600);
        setPositionY(400);
        double x = random.nextDouble();
        double y = random.nextDouble();
        double n = Math.sqrt(x * x + y * y);
        this.setVelocityX(5);
        this.setVelocityY(5);
        this.setRadius(10);
        this.setDeltaT(3.0);
        this.setColor(Color.red);
    }

    public void move() {
        positionX += deltaT * velocityX;
        positionY += deltaT * velocityY;
    }

    @Override
    public String toString() {
        return "position(" + getPositionX() + "," + getPositionY() + "), velocity("
                + getVelocityX() + "," + getVelocityY() + "), deltaT(" + getDeltaT()
                + "), radius(" + getRadius() + ")";
    }

    public void drawBall(Graphics g) {
        g.setColor(this.getColor());
        g.fillOval((int) (this.getPositionX() - this.getRadius()),
                (int) (this.getPositionY() - this.getRadius()),
                (int) this.getRadius() * 2, (int) this.getRadius() * 2);
    }
/*
    public void start() {
        ballThread = new Thread(this);
        isRunning = true;
        ballThread.start();
    }

    public void stop() {
        isRunning = false;
        ballThread = null;
    }

    
    @Override
    public void run() {
        while (isRunning) {
            repaint();

            //check ball collision with sides
            if ((this.getPositionX() + this.getDeltaT() * this.getVelocityX() > getWidth() - this.getRadius())
                    || this.getPositionX() + this.getDeltaT() * this.getVelocityX() < this.getRadius()) {
                this.setVelocityX(-this.getVelocityX());
            }
            //check ball collision with bottom
            if ((this.getPositionY() + this.getDeltaT() * this.getVelocityY()) > (getHeight() - this.getRadius())) {
                //revert direction
                this.setVelocityY(-this.getVelocityY());
                //inform the game object that the paddle missed the ball
                //?
                //?
                //?
            }

            //chekc collition with paddle
            //check collision with other balls
            //check collision with bricks
            //loop throught the bricks
            for (int i = 0; i < 40; i++) {
                if (bricks[i].isVisible == true) {

                    double deltaX = (this.getPositionX() + this.getDeltaT() * this.getVelocityX())
                            - (bricks[i].getPositionX() - bricks[i].getWidth());

                    double deltaY = (this.getPositionY() + this.getDeltaT() * this.getVelocityY())
                            - (bricks[i].getPositionY() - bricks[i].getHeight());

                    if (Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2)) <= this.getRadius() + bricks[i].getHeight()) {
                        bricks[i].isVisible = false;
                        this.setVelocityX(-this.getVelocityX());
                    }
                }

            }

            //for (int i = 0; i < NUMBER_OF_BALLS; i++) {
            //   for (int j = i + 1; j < NUMBER_OF_BALLS; j++) {
            Ball ball1 = balls.get(i);
            Ball ball2 = balls.get(j);

            double deltaX = (ball1.getPositionX() + ball1.getDeltaT() * ball1.getVelocityX())
                    - (ball2.getPositionX() + ball2.getDeltaT() * ball2.getVelocityX());

            double deltaY = (ball1.getPositionY() + ball1.getDeltaT() * ball1.getVelocityY())
                    - (ball2.getPositionY() + ball2.getDeltaT() * ball2.getVelocityY());

            if (Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2)) <= ball1.getRadius() + ball2.getRadius()) {
                double tempX = ball1.getVelocityX();
                double tempY = ball1.getVelocityY();
                double tempT = ball1.getDeltaT();
                ball1.setVelocityX(ball2.getVelocityX());
                ball1.setVelocityY(ball2.getVelocityY());
                ball1.setDeltaT(ball2.getDeltaT());
                ball2.setVelocityX(tempX);
                ball2.setVelocityY(tempY);
                ball2.setDeltaT(tempT);
                break;
            }
            this.move();
            try {
                Thread.sleep(50);
            } catch (InterruptedException ex) {
                Logger.getLogger(Ball.class.getName()).log(
                        Level.SEVERE, null, ex);
            }
        }
    }
*/
    //------------------------------------------------------------------------------------------------------------
    //------------------------------------------------getters and setters-----------------------------------------
    //------------------------------------------------------------------------------------------------------------
    public double getPositionX() {
        return positionX;
    }

    public void setPositionX(double positionX) {
        this.positionX = positionX;
    }

    public double getPositionY() {
        return positionY;
    }

    public void setPositionY(double positionY) {
        this.positionY = positionY;
    }

    public double getVelocityX() {
        return velocityX;
    }

    public void setVelocityX(double velocityX) {
        this.velocityX = velocityX;
    }

    public double getVelocityY() {
        return velocityY;
    }

    public void setVelocityY(double velocityY) {
        this.velocityY = velocityY;
    }

    public double getDeltaT() {
        return deltaT;
    }

    public void setDeltaT(double deltaT) {
        this.deltaT = deltaT;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public java.awt.Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    private static int randomInt(int min, int max) {
        return random.nextInt((max - min) + 1) + min;
    }

    //-------------------------------------------------------------------------------
    //-------------------------------------------------------------------------------
    //-------------------------------------------------------------------------------
    public static void main(String[] args) {
        random = new Random();
        int iteration = -1;
        Ball ball = new Ball();

        /*
         ball.setPositionX(randomInt(20, 580));
         ball.setPositionY(randomInt(20, 580));
         double x = random.nextDouble();
         double y = random.nextDouble();
         double n = Math.sqrt(x * x + y * y);
         ball.setVelocityX(x / n);
         ball.setVelocityY(y / n);
         ball.setRadius(10);
         ball.setDeltaT(1.0);
         */
        while (iteration++ < 100) {
            System.out.println(ball);
            ball.move();
        }
    }
}
