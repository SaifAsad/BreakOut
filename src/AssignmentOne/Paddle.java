/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AssignmentOne;

import java.awt.Color;

/**
 *
 * @author Saif Asad
 */
public class Paddle extends Block {
    //paddle object has a special colour
    private final Color color = Color.BLACK;
    
    
    //move left / right
    //move command recieed from the game object which takes them from the user input
    
    private double distance;
    
    public Paddle(int positionX, int PositionY, int width, int height, Color color)
    {//setup the block with variables
        super.setPositionX(positionX);
        super.setPositionY(PositionY);
        super.setWidth(width);
        super.setHeight(height);
        super.setIsVisible(true);
        super.setColor(color);
    }
    
    
    
    //????????????????????????
    //do not go outside the limit of the window
    public void moveLeft(){
        
        this.setPositionX(this.getPositionX() - 50);
    }
    public void moveRight(){
        
        this.setPositionX(this.getPositionX() + 50);
    }
   
    /**
     * @return the distance
     */
    public double getDistance() {
        return distance;
    }

    /**
     * @param distance the distance to set
     */
    public void setDistance(double distance) {
        this.distance = distance;
    }
    
}
