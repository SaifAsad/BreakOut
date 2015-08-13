package AssignmentOne;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 *
 * @author Saif Asad
 */
public class BreakOut extends JPanel implements Runnable, ActionListener {

    private static final int DEFAULT_WIDTH = 600;
    private static final int DEFAULT_HEIGHT = 600;
    public static final int NUMBER_OF_BRICKS = 40; //5 rows * 8 cols

    //private final List<Brick> bricks;
    private Brick[] bricks;

    private Thread thread;
    private boolean isRunning;
    Random random;
    String playerName;
    int difficulty;
    private Paddle p;

    public BreakOut() {
        super(new BorderLayout());
        setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));

        bricks = new Brick[NUMBER_OF_BRICKS];

        Scanner scanString = new Scanner(System.in);
        Scanner scanInt = new Scanner(System.in);
        //ask for player name
        //initial score is zero
        //Player player = new Player(playerName, 0);

     
        Ball b = new Ball();
        p = new Paddle(300, 550, 20, 10, Color.black);
        this.addKeyListener(new KeyboardInput());
        this.setFocusable(true);
        this.requestFocusInWindow();

        repaint();
        //read top 10 high scores from a textfile
        //HighScore.readHighScores();
        //random = new Random(System.currentTimeMillis());
        //bricks = new ArrayList<>();
//        for (int i = 0; i < NUMBER_OF_BRICKS; i++) {
//            double x = random.nextDouble();
//            double y = random.nextDouble();
//            double n = Math.sqrt(x * x + y * y);
//
//            brick = new Brick(int positionX
//            , int PositionY, int width, int height, Color.red
//            , 
//                true, 10, false
//            );
//            bricks.add(brick);
//            Brick brick;
//            for(int i = 0; i < NUMBER_OF_BRICKS; i ++)
//            {
//                if(i >= 0 && i < 8)
//                {
//                    brick = new Brick(DEFAULT_WIDTH/8*i, DEFAULT_HEIGHT/8*i, 20,10, new Color(100,100,100,0), true, 10 , false);
//                }
//                else
//                {
//                    break;
//                }
//                if(i > 8 && i < 16)
//                {
//                    brick = new Brick(DEFAULT_WIDTH/8*(i), DEFAULT_HEIGHT/8*(i/8), 20,10, new Color(100,100,100,0), true, 10 , false);
//                }
//                if(i > 16 && i < 25)
//                {
//                }
//                if(i > 25 && i < 33)
//                {
//                }
//                if(i > 32 && i < 40)
//                {
//                }
    }

    @Override
    public void run() {

    }

    public void start() {
        thread = new Thread(this);
        isRunning = true;
        thread.start();
    }

    public void stop() {
        isRunning = false;
        thread = null;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        System.out.println("This is running");
        g.clearRect(0, 0, this.getWidth(), this.getHeight());
        //Brick brick = new Brick(DEFAULT_WIDTH/8*1, DEFAULT_HEIGHT/8, 20,10, Color.red, true, 10 , false);
        //draw bricks
        for (int i = 0; i < NUMBER_OF_BRICKS; i++) {
            Brick brick = new Brick(DEFAULT_WIDTH/8*i, DEFAULT_HEIGHT/8, 20,10, Color.red, true, 10 , false);
            brick.drawBricks(g, DEFAULT_WIDTH, DEFAULT_HEIGHT);
        }
        //draw ball

        //draw paddle
        g.setColor(p.getColor());
        //System.out.println("paddle colour: " + p.getColor().toString());
        g.fill3DRect(p.getPositionX(), p.getPositionY(), p.getWidth(), p.getHeight(), true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    class KeyboardInput extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                p.moveLeft();
                if (p.getPositionX() < 0) {
                    p.setPositionX(0);
                }
                System.out.println("Moved left");
            } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                p.moveRight();
                if ((p.getPositionX() + p.getWidth()) > DEFAULT_WIDTH) {
                    p.setPositionX(DEFAULT_WIDTH - p.getWidth());
                }
                System.out.println("Moved right");
            }
            repaint();
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Assignment One");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        BreakOut breakOut = new BreakOut();

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                //add code to write scores to file

                breakOut.stop();
            }
        });
        frame.setContentPane(breakOut);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        breakOut.start();
    }
}
