package AssignmentOne;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Saif Asad
 */
public class Brick extends Block {

    //determine the color of non special bricks
    private int score;
    //this will cause a ball object hitting it to duplicate
    //have ditinct color than no special
    private boolean isSpecial;

    public Brick(int positionX, int PositionY, int width, int height, Color color, boolean isVisible, int score, boolean isSpecial) {//setup the block with variables
        super.setPositionX(positionX);
        super.setPositionY(PositionY);
        super.setWidth(width);
        super.setHeight(height);
        super.setIsVisible(isVisible);
        if (isSpecial) {//setup the special block to be gray and not have a score value
            super.setColor(Color.gray);
            this.score = 0;
        } else{//this block is not special and must have a score value
            this.score = score;
            super.setColor(color);
        }
    }
    
    public void drawBricks(Graphics g) {
        g.setColor(this.getColor());
        g.fill3DRect(this.getPositionX(), this.getPositionY(), this.getWidth(), this.getHeight(), true);
    }

    //-----------------------------------------------------------getters and setters-----------------------------------------
    /**
     * @return the score
     */
    public int getScore() {
        return score;
    }

    /**
     * @param score the score to set
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * @return the isSpecial
     */
    public boolean isIsSpecial() {
        return isSpecial;
    }

    /**
     * @param isSpecial the isSpecial to set
     */
    public void setIsSpecial(boolean isSpecial) {
        this.isSpecial = isSpecial;
    }
}
