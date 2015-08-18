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

    public void writeHighScores()
    {
        highScores[0][0] = "Mark";
        highScores[0][1] = "200";
        try
        {
            FileWriter write = new FileWriter(path, false);
            PrintWriter printLine = new PrintWriter(write);
            printLine = new PrintWriter(write);
            for(int i = 0; i < 10; i++)
            {
                printLine.printf("%s" + "%n", highScores[i][0]+" "+ highScores[i][1]);
            }
            printLine.close();
        } catch (IOException ex)
        {
            Logger.getLogger(HighScore.class.getName()).log(Level.SEVERE, null, ex);
        }
        for(int i = 0; i < highScores.length; i++)
        {
            System.out.println(highScores[i][0]+" "+highScores[i][1]);
        }
    }
    
    public void checkHighScore(Player p)
    {   //get score of player
        int score = p.getScore();
        for(int i = 0; i < 10; i++)
        {//get score of index 
            int prevScore = Integer.valueOf(highScores[i][1]);
            //if the player's score is higher than this number
            if(score < prevScore)
            {
                if(i+1 < 10)
                {
                    int nextScore = Integer.parseInt(highScores[i+1][1]);
                    if(score > nextScore)
                    {
                        highScores[i+1][1] = Integer.toString(score);
                        highScores[i+1][0] = p.getPlayerName();
                        break;
                    }
                }
            }                        
            if(score > prevScore && i-1 > 0)
            {
                highScores[i-1][1] = Integer.toString(score);
                highScores[i-1][0] = p.getPlayerName();
                break;
            }
            else if(score > prevScore && i -1 < 0)
            {
                highScores[0][1] = Integer.toString(score);
                highScores[0][0] = p.getPlayerName();
                break;
            }
        }
        System.out.println("Updated highscores");
        for(int i = 0; i < highScores.length; i++)
        {
            System.out.println(highScores[i][0]+" "+highScores[i][1]);
        }
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
            {//save the line into a temp string
                String tempString = textReader.readLine();
                for(int j = 0; j < tempString.length(); j++)
                {//checks if there is a whitespace from this point onwards
                    if(Character.isWhitespace(tempString.charAt(j)))
                    {
                        temp[i][1] = tempString.substring(j+1);
                    }//checks while making sure the index-1 is within the string's bounds that j is not a whitespace
                    else if(j-1 < tempString.length() && j-1 > 0 && !Character.isWhitespace(tempString.charAt(j)))
                    { //adds a substring from the start of the string to index-1 to first index of temp[][]
                        temp[i][0] = tempString.substring(0, j-1);
                        //removes any whitespace at the end of the string
                        temp[i][0] = temp[i][0].trim();
                    }
                }
            }
            //fixes the first index having part of the score and a whitespace
            temp[0][0] = temp[0][0].substring(0, temp[0][0].length()-2);
            temp[0][0] = temp[0][0].trim();
            textReader.close();
            //for each index within temp[][] add the string to each index of highScore[][]
            for(int i = 0; i < temp.length; i++)
            {
                highScores[i][0] = temp[i][0];
                highScores[i][1] = temp[i][1];
                System.out.println(highScores[i][0]+" "+highScores[i][1]);
            }
        }
        catch(IOException e)
        {
            System.out.println(e);
        }
    }
}
