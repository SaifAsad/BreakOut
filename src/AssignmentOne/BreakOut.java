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
import javax.swing.JOptionPane;

/**
 *
 * @author Saif Asad
 */
public class BreakOut extends JPanel implements Runnable, ActionListener {

    private static final int DEFAULT_WIDTH = 800;
    private static final int DEFAULT_HEIGHT = 800;
    private static final int NUMBER_OF_BRICKS = 40; //5 rows * 8 cols
    private static final int NUMBER_OF_ROWS = 5; //5 rows * 8 cols
    private static final int NUMBER_OF_COLS = 8; //5 rows * 8 cols
    private static final int BRICK_WIDTH = 100;
    private static final int BRICK_HEIGHT = 20;
    private static final int PADDLE_WIDTH = 150;
    private static final int PADDLE_HEIGHT = 20;

    //private final List<Brick> bricks;
    private Brick[][] bricks;
    //private Brick brick;
    private Thread thread;
    private boolean isRunning;
    Random random;
    String playerName;
    int difficulty;

    //items needed on the screen
    private final Paddle paddle;
    private Ball ball;

    private Color[] colors = {Color.yellow, Color.GREEN, Color.CYAN, Color.orange, Color.red, Color.darkGray};

    public BreakOut() {
        super(new BorderLayout());
        setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));

        bricks = new Brick[NUMBER_OF_ROWS][NUMBER_OF_COLS];

        //initialize the bricks array
        for (int i = 0; i < NUMBER_OF_ROWS; i++) {
            for (int j = 0; j < NUMBER_OF_COLS; j++) {
                Brick brick = new Brick(DEFAULT_WIDTH / 8 * j, BRICK_HEIGHT * i, BRICK_WIDTH, BRICK_HEIGHT, colors[i], true, 10, false);
                bricks[i][j] = brick;
            }
        }

        Scanner scanString = new Scanner(System.in);
        Scanner scanInt = new Scanner(System.in);
        //ask for player name
        //initial score is zero
        //Player player = new Player(playerName, 0);
        //JOptionPane nameContainer = new JOptionPane("Please enter your name: ");

        //ball = new Ball();
        paddle = new Paddle(400, 700, PADDLE_WIDTH, PADDLE_HEIGHT, Color.black);
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
        g.clearRect(0, 0, this.getWidth(), this.getHeight());

        //draw bricks
        for (int i = 0; i < NUMBER_OF_ROWS; i++) {
            for (int j = 0; j < NUMBER_OF_COLS; j++) {
                bricks[i][j].drawBricks(g);
            }
        }
        //draw ball
        //ball.drawBall(g);
        //draw paddle
        paddle.drawPaddle(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    class KeyboardInput extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                paddle.moveLeft();
                if (paddle.getPositionX() < 0) {
                    paddle.setPositionX(0);
                }
                System.out.println("Moved left");
            } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                paddle.moveRight();
                if ((paddle.getPositionX() + paddle.getWidth()) > DEFAULT_WIDTH) {
                    paddle.setPositionX(DEFAULT_WIDTH - paddle.getWidth());
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
