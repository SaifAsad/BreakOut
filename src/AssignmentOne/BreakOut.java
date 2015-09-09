package AssignmentOne;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

/**
 *
 * @author Saif Asad
 * @author Mark Manson
 */
public class BreakOut extends JPanel
{

    protected static final int DEFAULT_WIDTH = 800;
    protected static final int DEFAULT_HEIGHT = 800;
    public int difficulty = 1;
    private static final int INITIAL_SCORE = 0;
    protected Game game;
    private Player player;
    public static boolean gameRunning;

    public static int missedCounter = 0;
    public static int bricksCounter = 40;

    private JLayeredPane lpane = new JLayeredPane();

    private JPanel breakoutPanel;
    private static JFrame frame;

    protected JButton easy;
    protected JButton medium;
    protected JButton hard;


    //-------------------------------------------------------------------------------------------------------------------------------------------
    public BreakOut(JFrame frame)
    {
        frame.setTitle("Assignment 1: Breakout");
        frame.setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
        gameRunning = true;
        setLayout(new BorderLayout());
        lpane.setBounds(0, 0, DEFAULT_WIDTH, DEFAULT_HEIGHT);

        breakoutPanel = new JPanel(null);
        breakoutPanel.setBackground(Color.BLACK);
        JLabel title = new JLabel("BREAKOUT!!!!");
        title.setFont(new Font("Serif", Font.BOLD, 42));
        title.setForeground(Color.YELLOW);
        title.setBounds(250, 150, 300, 300);


        easy = new JButton("Easy");
        easy.setBounds(250, 400, 70, 30);
        easy.addActionListener((ActionEvent e) ->
        {
            startGame(Difficulty.EASY);
        });
        medium = new JButton("Medium");
        medium.setBounds(350, 400, 80, 30);
        medium.addActionListener((ActionEvent e) ->
        {
            startGame(Difficulty.MEDIUM);
        });
        hard = new JButton("Hard");
        hard.setBounds(450, 400, 70, 30);
        hard.addActionListener((ActionEvent e) ->
        {
            startGame(Difficulty.HARD);
        });
        frame.setResizable(false);
        breakoutPanel.add(easy);
        breakoutPanel.add(medium);
        breakoutPanel.add(hard);
        breakoutPanel.add(title);
        breakoutPanel.setBounds(0, 0, DEFAULT_WIDTH, DEFAULT_HEIGHT);
        breakoutPanel.setOpaque(true);

        frame.setContentPane(lpane);

        player = new Player("default", INITIAL_SCORE);
        breakoutPanel.setFocusable(true);
        lpane.add(breakoutPanel);
    }

    //-------------------------------------------------------------------------------------------------------------------------------------------
    /**
     * Starts the thread in the game class
     * @param d takes a difficulty value used for setting up scores and velocity of the ball
     */
    public void startGame(Difficulty d)
    {
        game = new Game(d, frame);
        this.setVisible(false);
        easy.setEnabled(false);
        medium.setEnabled(false);
        hard.setEnabled(false);
        game.setBounds(0, 0, DEFAULT_WIDTH, DEFAULT_HEIGHT);
        game.setOpaque(true);
        lpane.add(game);
        frame.setContentPane(lpane);
        game.start();
    }
    public static void main(String[] args)
    {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        BreakOut breakOut = new BreakOut(frame);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
