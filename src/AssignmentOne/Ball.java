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
public class Ball implements Runnable {

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
        setPositionX(550);
        setPositionY(580);
        //double x = random.nextDouble();
        //double y = random.nextDouble();
        //double n = Math.sqrt(x * x + y * y);
        this.setVelocityX(20);
        this.setVelocityY(50);
        this.setRadius(10);
        this.setDeltaT(1.0);
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

    public void start() {
        ballThread = new Thread(this);
        isRunning = true;
        ballThread.start();
    }

    public void stop() {
        isRunning = false;
        ballThread = null;
    }

//    @Override
//    public void run() {
//        while (isRunning) {
//            repaint();
//
//            //check ball collision with sides
//            if ((ball.getPositionX() + ball.getDeltaT() * ball.getVelocityX() > getWidth() - ball.getRadius())
//                    || ball.getPositionX() + ball.getDeltaT() * ball.getVelocityX() < ball.getRadius()) {
//                ball.setVelocityX(-ball.getVelocityX());
//                ball.setColor(Color.getHSBColor(random.nextFloat(), 1.0f, 1.0f));
//            }
//            //check ball collision with the top and down
//            if ((ball.getPositionY() + ball.getDeltaT() * ball.getVelocityY() > getHeight() - ball.getRadius())
//                    || ball.getPositionY() + ball.getDeltaT() * ball.getVelocityY() < ball.getRadius()) {
//                ball.setVelocityY(-ball.getVelocityY());
//                ball.setColor(Color.getHSBColor(random.nextFloat(), 1.0f, 1.0f));
//            }
//            //check collision with other balls
//            //check collision with bricks
//            //for (int i = 0; i < NUMBER_OF_BALLS; i++) {
//            //   for (int j = i + 1; j < NUMBER_OF_BALLS; j++) {
//            Ball ball1 = balls.get(i);
//            Ball ball2 = balls.get(j);
//
//            double deltaX = (ball1.getPositionX() + ball1.getDeltaT() * ball1.getVelocityX())
//                    - (ball2.getPositionX() + ball2.getDeltaT() * ball2.getVelocityX());
//
//            double deltaY = (ball1.getPositionY() + ball1.getDeltaT() * ball1.getVelocityY())
//                    - (ball2.getPositionY() + ball2.getDeltaT() * ball2.getVelocityY());
//
//            if (Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2)) <= ball1.getRadius() + ball2.getRadius()) {
//                double tempX = ball1.getVelocityX();
//                double tempY = ball1.getVelocityY();
//                double tempT = ball1.getDeltaT();
//                ball1.setVelocityX(ball2.getVelocityX());
//                ball1.setVelocityY(ball2.getVelocityY());
//                ball1.setDeltaT(ball2.getDeltaT());
//                ball2.setVelocityX(tempX);
//                ball2.setVelocityY(tempY);
//                ball2.setDeltaT(tempT);
//                break;
//            }
//            this.move();
//            try {
//                Thread.sleep(50);
//            } catch (InterruptedException ex) {
//                Logger.getLogger(Ball.class.getName()).log(
//                        Level.SEVERE, null, ex);
//            }
//        }
//    }

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

    /**
     * @param color the color to set
     */
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

    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
