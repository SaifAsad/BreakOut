package AssignmentOne;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * HighScore maintains a record of the top ten scores. persist the high score
 * record beyond the lifetime of the application, by writing these to and
 * reading them from a file.
 *
 * @author Mark Manson
 * @author Saif Asad
 */
public class HighScore
{

    private String path;
    private boolean appendToFile = true;
    private Player[] highScores = new Player[11];

    //-------------------------------------------------------------------------------------------------------------------------------------------
    /**
     * The file name for the highscore file, it should end in a .txt format
     * @param filePath the name of the file
     */
    public HighScore(String filePath)
    {
        path = filePath;
    }
    /**
     * This method writes the highscores from the highscores container of players to the text file
     */
    public void writeHighScores()
    {
        try
        {
            FileWriter write = new FileWriter(path, false);
            PrintWriter printLine = new PrintWriter(write);
            printLine = new PrintWriter(write);
            int i = 0;
            for (i = 0; i < 10; i++)
            {
                printLine.printf("%s" + "%n", highScores[i].getPlayerName() + " " + highScores[i].getScore());
                System.out.println(highScores[i].getPlayerName() + " " + highScores[i].getScore());
            }
            if (i == 10)
            {
                printLine.printf("%s", highScores[10].getPlayerName() + " " + highScores[10].getScore());
            }
            printLine.close();
        } catch (IOException e)
        {
            System.out.println(e);
        }
    }

    /**
     * This method simply checks if the score is greater the lowest highscore
     * @param player a player object that is checked against the lowest highscore
     * it will also get added to the container if it is higher than the loewst score
     * @return true if the player's score is higher than the lowest highscore, false if not
     */
    public boolean checkIfHigherThanLast(Player player)
    {
        if (player.getScore() > highScores[9].getScore())
        {
            highScores[10] = player;
            return true;
        } else
        {
            return false;
        }
    }
    /**
     * This method is used to read highscores from the text file, it reads each line and checks for alphabetical letters and assumes
     * this is the player name, it then checks for numerical values and assumes it is the score
     */
    public void readHighScores()
    {
        String[] textData = new String[1];
        try
        {
            FileReader fr = new FileReader(path);
            BufferedReader textReader = new BufferedReader(fr);
            int numberOfLines = 11;
            textData = new String[numberOfLines];

            for (int i = 0; i < numberOfLines; i++)
            {
                textData[i] = textReader.readLine();
            }
            textReader.close();
        } catch (IOException e)
        {
            System.out.println(e);
        }
        for (int i = 0; i < 11; i++)
        {
            String tempString = textData[i];
            String tempName = "";
            for (int j = 0; j < tempString.length(); j++)
            {
                if (!Character.isWhitespace(tempString.charAt(j)) && Character.isAlphabetic(tempString.charAt(j)))
                {
                    tempName += tempString.charAt(j);
                } 
                else if (Character.isWhitespace(tempString.charAt(j)))
                {
                    String tempScore = tempString.substring(j + 1);
                    Player p = new Player(tempName, Integer.parseInt(tempScore));
                    highScores[i] = p;
                    break;
                }

            }
        }
    }
    /**
     * Getter for returning the list of highscores in the cointainer highscores
     * @return an arraylist of player objects
     */
    public Player[] getHighScoresList()
    {
        return highScores;
    }
     /**
     * This method is responsible for drawing the highscores on the screen using the graphics object g, it takes all the information
     * from the container within this class
     * @param g the graphics object used for drawing text to screen
     */
    public void drawHighScores(Graphics g)
    {
        g.setColor(Color.black);
        Font headingFont = new Font("SansSerif", Font.BOLD, 36);
        g.setFont(headingFont);
        FontMetrics headingFontMetrics = g.getFontMetrics(headingFont);
        g.drawString("BreakOut High Scores",
                (800 - headingFontMetrics.stringWidth("BreakOut High Scores")) / 2,
                800 / 4);
        Font subHeadingFont = new Font("SansSerif", Font.BOLD, 24);
        g.setFont(subHeadingFont);
        FontMetrics subHeadingFontMetrics = g.getFontMetrics(subHeadingFont);
        g.drawString("NAME",
                (800 - subHeadingFontMetrics.stringWidth("NAME")) / 4,
                800 / 4 + (2 * headingFontMetrics.getHeight()));
        g.drawString("SCORE",
                3 * (800 - subHeadingFontMetrics.stringWidth("SCORE")) / 4,
                800 / 4 + (2 * headingFontMetrics.getHeight()));
        Font bodyFont = new Font("SansSerif", Font.PLAIN, 18);
        g.setFont(bodyFont);
        FontMetrics bodyFontMetrics = g.getFontMetrics(bodyFont);
        int increment = 1;
        for (int i = 0; i < 10; i++)
        {
            g.drawString(highScores[i].getPlayerName(),
                    (800 - subHeadingFontMetrics.stringWidth("NAME")) / 4,
                    800 / 4 + (increment * bodyFontMetrics.getHeight() / 2)
                    + (int) (2.5 * headingFontMetrics.getHeight()));
            g.drawString(highScores[i].getScore() + "",
                    3 * (800 - subHeadingFontMetrics.stringWidth("SCORE")) / 4,
                    800 / 4 + (increment * bodyFontMetrics.getHeight() / 2)
                    + (int) (2.5 * headingFontMetrics.getHeight()));
            increment += 2;
        }
    }
}
