package AssignmentOne;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Saif Asad
 */
public class Game extends JPanel implements ActionListener, Runnable {

    //dimensions
    private static final int DEFAULT_WIDTH = 800;
    private static final int DEFAULT_HEIGHT = 800;
    private static final int BRICK_WIDTH = 100;
    private static final int BRICK_HEIGHT = 20;
    private static final int PADDLE_WIDTH = 200;
    private static final int PADDLE_HEIGHT = 10;

    //objects that form the game
    private Ball ball;
    private Rectangle ballRectangle;
    private final Paddle paddle;
    private Rectangle paddleRectangle;
    private List<Ball> balls = new ArrayList<>();
    private Thread thread;

    //temporary variables
    private String playerName;
    private int difficulty = 1;
    private static final int INITIAL_SCORE = 0;
    private Player player;

    private Brick[][] bricks;
    private static final int NUMBER_OF_ROWS = 5; //5 rows * 8 cols
    private static final int NUMBER_OF_COLS = 8; //5 rows * 8 cols

    private boolean isRunning;
    private static Random random;
    private double deltaX, deltaY;

    //keeps track of how many times the player miss to hit the ball with the paddle
    private static int missedCounter = 0;
    //keeps track of how many bricks are hit
    private int bricksCounter = 40;
    //will be used to set up the brick status (if special)
    boolean special = true;
    //keeps track of the score value of each row of bricks
    private int score = 0;
    //array of colours for the bricks
    private final Color[] colors = {Color.blue, Color.GREEN, Color.CYAN, Color.MAGENTA, Color.yellow, Color.darkGray};

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    //Constructor
    public Game(Difficulty difficulty) {
        super(new BorderLayout());
        setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
        player = new Player(playerName, INITIAL_SCORE);
        bricks = new Brick[NUMBER_OF_ROWS][NUMBER_OF_COLS];
        
        //the diffculty level will determine the score of each row of bricks
        //initialize the bricks array
        for (int i = 0; i < NUMBER_OF_ROWS; i++) {
            for (int j = 0; j < NUMBER_OF_COLS; j++) {
                switch (i) {
                    case 0:
                        score = 10 * difficulty.getDifficulty();
                        break;
                    case 1:
                        score = 8 * difficulty.getDifficulty();
                        break;
                    case 2:
                        score = 6 * difficulty.getDifficulty();
                        break;
                    case 3:
                        score = 4 * difficulty.getDifficulty();
                        break;
                    case 4:
                        score = 2 * difficulty.getDifficulty();
                        break; 
                }
                if ((i == 0 && j == 0) || (i == 1 && j == 6) || (i == 3 && j == 3) || (i == 4 && j == 0)) {
                    special = true;
                } else {
                    special = false;
                }
                bricks[i][j] = new Brick(DEFAULT_WIDTH / 8 * j, BRICK_HEIGHT * i, BRICK_WIDTH, BRICK_HEIGHT, colors[i], true, score, special);
            }
        }
        ball = new Ball();
        balls.add(ball);
        paddle = new Paddle(400, 700, PADDLE_WIDTH / difficulty.getDifficulty(), PADDLE_HEIGHT, Color.black);
        this.addKeyListener(new KeyboardInput());
        this.setFocusable(true);
        this.requestFocusInWindow();

        repaint();
    }

    //-------------------------------------------------------------------------------------------------------------------------------------------
    //----------------------------------------------------UTILITY METHODS------------------------------------------------------------------------
    //-------------------------------------------------------------------------------------------------------------------------------------------
    @Override
    public void run() {
        while (isRunning) {
            boolean addBall = false;
            repaint();
            //for testing
            int num = 0;
            for (int i = 0; i < NUMBER_OF_ROWS && num !=40 ; i++) {
                for (int j = 0; j < NUMBER_OF_COLS; j++) {
                    System.out.println((num++) + " " + bricks[i][j]);
                }
            }
            //check ball collision with sides
            for (Ball ball : balls) {
                if ((ball.getPositionX() + ball.getDeltaT() * ball.getVelocityX() > getWidth() - ball.getRadius())
                        || ball.getPositionX() + ball.getDeltaT() * ball.getVelocityX() < ball.getRadius()) {
                    ball.setVelocityX(-ball.getVelocityX());
                }
                //check ball collision with bottom
                if ((ball.getPositionY() + ball.getDeltaT() * ball.getVelocityY() > getHeight() - ball.getRadius())
                        || ball.getPositionY() + ball.getDeltaT() * ball.getVelocityY() < ball.getRadius()) {
                    ball.setVelocityY(-ball.getVelocityY());
                    missedCounter++;
                    if (missedCounter == 3) {
                        stop();
                        JOptionPane.showMessageDialog(null, "You Lose!!!!!");
                        break; //skip the while loop
                    }
                }

                //check collition with paddle            
                ballRectangle = new Rectangle((int) (ball.getPositionX() - ball.getRadius()), (int) (ball.getPositionY() - ball.getRadius()),
                        (int) (ball.getRadius() * 2), (int) (ball.getRadius() * 2));
                paddleRectangle = new Rectangle(paddle.getPositionX(), paddle.getPositionY(),
                        paddle.getWidth(), paddle.getHeight());
                if (ballRectangle.intersects(paddleRectangle)) {
                    ball.setVelocityY(-ball.getVelocityY());
                }

                //check collision with bricks
                boolean quit = true;
                for (int i = 0; i < NUMBER_OF_ROWS && quit; i++) {
                    for (int j = 0; j < NUMBER_OF_COLS; j++) {
                        if (bricks[i][j].isIsVisible()) {
                            ballRectangle = new Rectangle((int) (ball.getPositionX() - ball.getRadius()), (int) (ball.getPositionY() - ball.getRadius()),
                                    (int) (ball.getRadius() * 2), (int) (ball.getRadius() * 2));
                            Rectangle brickRectangle = new Rectangle((DEFAULT_WIDTH / 8 * j), (BRICK_HEIGHT * i),
                                    bricks[i][j].getWidth(), bricks[i][j].getHeight());
                            if (brickRectangle.intersects(ballRectangle)) {
                                System.out.println("Detected brick collision");
                                bricksCounter--;
                                //score++ dependent on the score of the brick
                                bricks[i][j].setIsVisible(false);
                                ball.setVelocityY(-ball.getVelocityY());
                                if (bricks[i][j].isIsSpecial()) {
                                    System.out.println("HIt a special brick");
                                    //balls.add(new Ball());
                                    addBall = true;
                                    //add it to the balls thread
                                }
                                repaint();
                                quit = false; //this will break the outer for loop
                                break; //this will break the inner for loop
                            }
                        }
                    }
                }

                //check collision with other balls
                for (int i = 0; i < balls.size(); i++) {
                    for (int j = i + 1; j < balls.size(); j++) {
                        Ball ball1 = balls.get(i);
                        Ball ball2 = balls.get(j);

                        deltaX = (ball1.getPositionX() + ball1.getDeltaT() * ball1.getVelocityX())
                                - (ball2.getPositionX() + ball2.getDeltaT() * ball2.getVelocityX());

                        deltaY = (ball1.getPositionY() + ball1.getDeltaT() * ball1.getVelocityY())
                                - (ball2.getPositionY() + ball2.getDeltaT() * ball2.getVelocityY());

                        if (Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2)) <= ball1.getRadius() + ball2.getRadius()) {
                            double tempX = ball1.getVelocityX();
                            double tempY = ball1.getVelocityY();
                            double tempT = ball1.getDeltaT();
                            ball1.setVelocityX(ball2.getVelocityX());
                            ball1.setVelocityY(ball2.getVelocityY());
                            ball1.setDeltaT(ball2.getDeltaT());
                            ball2.setVelocityX(tempX);
                            ball2.setVelocityY(tempY);
                            ball2.setDeltaT(tempT);
                            break;
                        }
                    }
                }
            }
            if (bricksCounter == 0) {
                stop();
                JOptionPane.showMessageDialog(null, "You won!!!!");
                break; //leave the while loop
            }

            for (Ball ball : balls) {
                ball.move();
            }
            if(addBall)
            {
                balls.add(new Ball());
                addBall = false;
            }
            try {
                Thread.sleep(50);
            } catch (InterruptedException ex) {
                Logger.getLogger(Ball.class.getName()).log(
                        Level.SEVERE, null, ex);
            }
        }
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

    public void pause() {
        System.out.println("Game Paused");
        stop();
        JOptionPane.showMessageDialog(null, "GAME PAUSED");
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.clearRect(0, 0, this.getWidth(), this.getHeight());
        //draw bricks
        for (int i = 0; i < NUMBER_OF_ROWS; i++) {
            for (int j = 0; j < NUMBER_OF_COLS; j++) {
                if (bricks[i][j].isIsVisible()) {
                    bricks[i][j].drawBricks(g);
                }
            }
        }
        //draw balls
        for (Ball ball : balls) {
            ball.drawBall(g);
        }
        //draw paddle
        paddle.drawPaddle(g);
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
            } else if (e.getKeyCode() == KeyEvent.VK_B) {
                pause();
                System.out.println("thread paused");
            } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                start();
                System.out.println("thread resumed");
            }
            repaint();
        }
    }

    private static int randomInt(int min, int max) {
        return random.nextInt((max - min) + 1) + min;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Assignment One");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Game game = new Game(Difficulty.HARD);

        frame.setContentPane(game);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        game.start();

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                //add code to write scores to file

                // breakOut.stop();
            }
        });
    }
}
