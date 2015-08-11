/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AssignmentOne;

import java.awt.Color;
import java.util.Random;

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
    private final Color color = Color.red;

    private static Random random;

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
     * generates a random number
     * 
     * @param min
     * @param max
     * @return 
     */
    private static int randomInt(int min, int max) {
        return random.nextInt((max - min) + 1) + min;
    }

    public static void main(String[] args) {
        random = new Random();
        int iteration = -1;
        Ball ball = new Ball();
      
        ball.setPositionX(randomInt(20, 580));
        ball.setPositionY(randomInt(20, 580));
        double x = random.nextDouble();
        double y = random.nextDouble();
        double n = Math.sqrt(x * x + y * y);
        ball.setVelocityX(x / n);
        ball.setVelocityY(y / n);
        ///ball.setColor(Color.getHSBColor(random.nextFloat(), 1.0f, 1.0f));
        ball.setRadius(10);
        ball.setDeltaT(1.0);
        while (iteration++ < 100) {
            System.out.println(ball);
            ball.move();
        }
    }

    
}
