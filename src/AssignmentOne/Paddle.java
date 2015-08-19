package AssignmentOne;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Saif Asad
 */
public class Paddle extends Block {
   
    public Paddle(int positionX, int PositionY, int width, int height, Color color)
    {//setup the block with variables
        super.setPositionX(positionX);
        super.setPositionY(PositionY);
        super.setWidth(width);
        super.setHeight(height);
        super.setIsVisible(true);
        super.setColor(color);
    }   
    
    public void moveLeft(){
    
        this.setPositionX(this.getPositionX() - 50);
    }
    public void moveRight(){
        
        this.setPositionX(this.getPositionX() + 50);
    }
     
    public void drawPaddle(Graphics g){ 
        g.setColor(this.getColor());
        g.fill3DRect(this.getPositionX(), this.getPositionY(), this.getWidth(), this.getHeight(), true);
    }
}
