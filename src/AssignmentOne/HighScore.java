package AssignmentOne;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;

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
    //private ArrayList<Player> highScores = new ArrayList(); 
    private Player[] highScores = new Player[11];

    public HighScore(String filePath)
    {
        path = filePath;
    }

    public void writeHighScores()
    {
        try
        {
            FileWriter write = new FileWriter(path, false);
            PrintWriter printLine = new PrintWriter(write);
            printLine = new PrintWriter(write);
            for (int i = 0; i < 11; i++)
            {
                printLine.printf("%s" + "%n", highScores[i].getPlayerName() + " " + highScores[i].getScore());
            }
            printLine.close();
        } catch (IOException e)
        {
            System.out.println(e);
        }
    }

    public boolean checkIfHigherThanLast(int playerScore)
    {
        if (playerScore > highScores[9].getScore())
        {
            return true;
        }
        return false;
    }

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
                } else if (Character.isWhitespace(tempString.charAt(j)))
                {

                    String tempScore = tempString.substring(j + 1);
                    System.out.println("tempScore: " + tempScore);
                    Player p = new Player(tempName, Integer.parseInt(tempScore));
                    highScores[i] = p;
                    System.out.println(p);
                    break;
                }

            }
        }
    }

    public void checkHighScore(Player player)
    {
        if (player.getScore() > highScores[9].getScore())
        {//if the highscore is greater than the lowest score add the player's score to 
            //the array
            for (Player p : highScores)
            {
                if (p.getScore() <= player.getScore() )
                {
                    highScores[10] = player;
                }
            }
        }
    }

    public Player[] getHighScoresList()
    {
        return highScores;
    }

    public void drawHighScores(Graphics g)
    {
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
