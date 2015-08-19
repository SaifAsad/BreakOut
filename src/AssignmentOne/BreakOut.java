package AssignmentOne;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Saif Asad
 */
public class BreakOut extends JPanel  {

    private static final int DEFAULT_WIDTH = 800;
    private static final int DEFAULT_HEIGHT = 800;
    private String playerName = "SAIF";
    private int difficulty = 1;
    private static final int INITIAL_SCORE = 0;
    Game game;
    public BreakOut() {
        super(new BorderLayout());
        setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
        Player player = new Player(playerName, INITIAL_SCORE);
       //game = new Game(player, difficulty, DEFAULT_WIDTH);
    }       
    public static void main(String[] args) {
        JFrame frame = new JFrame("Assignment One");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        BreakOut breakOut = new BreakOut();

        frame.setContentPane(breakOut);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        //game.start();

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                //add code to write scores to file

               // breakOut.stop();
            }
        });
    }
}
