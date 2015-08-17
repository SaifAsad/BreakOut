package AssignmentOne;

import java.awt.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * HighScore maintains a record of the top ten scores. persist the high score
 * record beyond the lifetime of the application, by writing these to and
 * reading them from a file.
 *
 * @author Saif Asad
 */
public class HighScore
{

    private String path;
    private boolean appendToFile = true;
    private String[][] highScores = new String[10][2];

    public HighScore(String filePath)
    {
        path = filePath;
    }

    public void writeHighScores(String textLine)
    {
        try
        {
            FileWriter write = new FileWriter(path, appendToFile);
            PrintWriter printLine = new PrintWriter(write);
            printLine.printf("%s" + "%n", textLine);
            printLine.close();
        } catch (IOException ex)
        {
            Logger.getLogger(HighScore.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void checkHighScore(Player p)
    {
        //checks to see if the player's score is either higher than or greater than the score of the tenth highest score
        //if so checks all other scores to determine what index it will be saved to
        //saves the score to the list of highscores
    }
    
    public void readHighScores()
    {
        //runs a loop to add to each score to a string
        //each string is then added to the score list
        //the string is then cleared and written to again
        //make sure that the container is only a length of 10
        try
        {
            FileReader fr = new FileReader(path);
            BufferedReader textReader = new BufferedReader(fr);
            String[][] temp = new String[10][2];
            for(int i = 0; i < temp.length; i++ )
            {
                //temp[i][0] = textReader.readLine();
                String tempString = textReader.readLine();
                for(int j = 0; j < tempString.length(); j++)
                {
                    if(Character.isWhitespace(tempString.charAt(j)))
                    {
                        temp[i][1] = tempString.substring(j+1);
                    }
                    else
                    {
                        temp[i][0] = tempString;
                    }
                }
            }
            textReader.close();
            for(int i = 0; i < temp.length; i++)
            {
                System.out.println(temp[i][0]+" "+ temp[i][1]);
            }
        }
        catch(IOException e)
        {
            System.out.println(e);
        }
    }
}
