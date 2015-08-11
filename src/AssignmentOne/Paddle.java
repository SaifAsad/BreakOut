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
    private int moveLeft;
    private int moveRight;
    
    
}
