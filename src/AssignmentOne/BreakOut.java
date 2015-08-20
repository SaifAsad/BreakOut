package AssignmentOne;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Saif Asad
 */
public class BreakOut extends JFrame {

    private static final int DEFAULT_WIDTH = 800;
    private static final int DEFAULT_HEIGHT = 800;
    private String playerName = "SAIF";
    private int difficulty = 1;
    private static final int INITIAL_SCORE = 0;
    private Game game;
    private Player player;
    private boolean gameRunning = true;
   
    public static int missedCounter = 0;
    public static int bricksCounter = 40;
    
    public static void incrementMissedCounter(){
        missedCounter++;
    }
    public static void decrementBricksCounter(){
        bricksCounter--; 
    }
    public BreakOut(Difficulty d) {
        setTitle("Assignment 1:Breakout");
        setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
        player = new Player(playerName, INITIAL_SCORE);
        game = new Game(d);
        setContentPane(game);
    }
    public void runGame()
    {
        game.start();
    }
    public void getHighScore(Graphics g)
    {
        g.clearRect(0, 0, WIDTH, HEIGHT);
        HighScore h = new HighScore("highscores.txt");
        h.readHighScores();
        h.checkHighScore(player);
        if(h.checkIfHigherThanLast(game.getHighScore()))
        {
            String name = JOptionPane.showInputDialog(game, "Please type your name");
            if(name == null)
            {
                name = "Unknown";
            }
            player.setPlayerName(name);
            player.setScore(game.getHighScore());
            h.checkHighScore(player);
            Sort<Player> sortScores = new Sort(h.getHighScoresList());
            sortScores.quickSort();
            h.writeHighScores();
            h.drawHighScores(g);
        }
        repaint();
    }
    public static void main(String[] args) {
        BreakOut breakOut = new BreakOut(Difficulty.EASY);
        breakOut.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        breakOut.pack();
        breakOut.setLocationRelativeTo(null);
        breakOut.setVisible(true);
        breakOut.runGame();
    }
}
