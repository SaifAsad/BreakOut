package AssignmentOne;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Saif Asad
 * @author Mark Manson
 */
public class Ball {

    private double deltaT;
    private double radius;
    private double positionX;
    private double positionY;
    private double velocityX;
    private double velocityY;
    private Color color;

    @SuppressWarnings("OverridableMethodCallInConstructor")
    public Ball() {
        setPositionX(400);
        setPositionY(400);
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
    /**
     * Draws a ball object to the screen
     * @param g the graphics object responsible for drawing the ball on the screen
     */
    public void drawBall(Graphics g) {
        g.setColor(this.getColor());
        g.fillOval((int) (this.getPositionX() - this.getRadius()),
                (int) (this.getPositionY() - this.getRadius()),
                (int) this.getRadius() * 2, (int) this.getRadius() * 2);
    }
    /**
     * This method checks if the ball has collided with the sides of the panel
     * @param width the width of the panel
     * @return true if the ball has collided, false if not
     */
    public boolean checkCollisionWithSides(double width)
    {
        return (getPositionX() + getDeltaT() * getVelocityX() > width - getRadius()
                    || getPositionX() + getDeltaT() * getVelocityX() < getRadius());
    }
    /**
     * This method checks if the ball has collided with the bottom of the panel
     * @param bottom the bottom of the panel
     * @return true if the ball has collided, false if not
     */
    public boolean checkCollisionWithBottom(int bottom)
    {
        return (getPositionY() + getDeltaT() * getVelocityY() > bottom - getRadius()
                    || getPositionY() + getDeltaT() * getVelocityY() < getRadius());
    }
    /**
     * This method checks if the ball has collided with another ball object
     * @param b another ball object
     * @return true if the ball has collided, false if not
     */
    public boolean checkCollisionWithOtherBall(Ball b)
    {
        double deltaX = (getPositionX() + getDeltaT() * getVelocityX())
                                - (b.getPositionX() + b.getDeltaT() * b.getVelocityX());

        double deltaY = (getPositionY() + getDeltaT() * getVelocityY())
                - (b.getPositionY() + b.getDeltaT() * b.getVelocityY());
        return Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2)) <= getRadius() + b.getRadius();
    }
    /**
     * Gets the X position of the ball
     * @return a double representing the x position of the ball
     */
    public double getPositionX() {
        return positionX;
    }
    /**
     * Sets the X position of the ball
     * @param positionX a value that replaces the current X position of the ball
     */
    public void setPositionX(double positionX) {
        this.positionX = positionX;
    }
    /**
     * Gets the Y position of the ball
     * @return a double representing the y position of the ball
     */
    public double getPositionY() {
        return positionY;
    }
    /**
     * Sets the Y position of the ball
     * @param positionY a value that replaces the current Y position of the ball
     */
    public void setPositionY(double positionY) {
        this.positionY = positionY;
    }
    /**
     * Gets the X velocity of the ball
     * @return a double that represents the ball's x velocity
     */
    public double getVelocityX() {
        return velocityX;
    }
    /**
     * Sets the X velocity of the ball
     * @param velocityX a value that replaces the current X velocity
     */
    public void setVelocityX(double velocityX) {
        this.velocityX = velocityX;
    }
    /**
     * Sets the Y velocity of the ball
     * @return velocityY a value that replaces the current Y velocity
     */
    public double getVelocityY() {
        return velocityY;
    }
    /**
     * Sets the X velocity of the ball
     * @param velocityY a value that replaces the current X velocity
     */
    public void setVelocityY(double velocityY) {
        this.velocityY = velocityY;
    }
    /**
     * Gets the DeltaT of the ball
     * @return a double that represents the delta T of the ball
     */
    public double getDeltaT() {
        return deltaT;
    }
    /**
     * Sets the DeltaT of the ball
     * @param deltaT a value that replaces the current deltaT of the ball
     */
    public void setDeltaT(double deltaT) {
        this.deltaT = deltaT;
    }
    /**
     * Gets the current radius of the ball
     * @return a double that represents the radius of the ball
     */
    public double getRadius() {
        return radius;
    }
    /**
     * Sets the radius of the ball
     * @param radius a value that will replace the current radius of the ball
     */
    public void setRadius(double radius) {
        this.radius = radius;
    }
    /**
     * Returns the color object of the ball
     * @return a color object
     */
    public java.awt.Color getColor() {
        return color;
    }
    /**
     * Sets the color of the ball
     * @param color a color object
     */
    public void setColor(Color color) {
        this.color = color;
    }
}
