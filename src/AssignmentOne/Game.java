
package AssignmentOne;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

/**
 *
 * @author Saif Asad
 */
public class Game implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent e) {
       
    }
    //hits counter, if 40 player winds
    //fails counter if 3 the player loses
    //scores counter, increases every time a brick is hit
    
    
    //if a brick is hit
    //  -make it invisible
    //  -his++;
    //  -scores is incemeented depending on the row nummber
    
    //an array of 5 * 8 to represent the bricks
    public static void main(String[] args)
    {
        //Testing for writing to a file
        Player p = new Player("Bob", 1201);
        HighScore h = new HighScore("test.txt");
        h.readHighScores();
        Scanner s = new Scanner(System.in);
        if(s.hasNext())
        {
            h.writeHighScores();
        }
        //need to look at how to do this part with sorting algorithms
        //h.checkHighScore(p);
        
        //Starts the game
        //inside the game asks for the player's name
        //sets the name to the player object
        //after game has finished main loop finds the score and if is bigger than the tenth highest score 
        //checks to see how big the score is with every score first to ninth
        //adds the score to the container that will be added to the text file
    }
    
}
