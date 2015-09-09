package AssignmentOne;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * @author Mark Manson
 * @author Saif Asad
 */
public class Game extends JPanel implements Runnable {

    //dimensions
    private static final int BRICK_WIDTH = 100;
    private static final int BRICK_HEIGHT = 20;
    private static final int PADDLE_WIDTH = 200;
    private static final int PADDLE_HEIGHT = 10;

    //objects that form the game
    private Rectangle ballRectangle;
    private Paddle paddle;
    private Rectangle paddleRectangle;
    private List<Ball> balls = new ArrayList<>();
    private Thread thread;
    public boolean m_isFinished;
    //temporary variables
    private String playerName;
    private int difficulty = 0;
    private static final int INITIAL_SCORE = 0;
    private Player player;

    private Brick[][] bricks;
    private static final int NUMBER_OF_ROWS = 5; //5 rows * 8 cols
    private static final int NUMBER_OF_COLS = 8; //5 rows * 8 cols
    private boolean isRunning;
    private boolean isPaused;
    private final double ballVelocityX;
    private final double ballVelocityY;
    private Ball[] ballCounter;
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
    private JFrame frame;
    private HighScore highscore;
    //-------------------------------------------------------------------------------------------------------------------------------------------
    //Constructor
    public Game(Difficulty difficulty, JFrame frame) {
        
        super(new BorderLayout());
        this.frame = frame;
        m_isFinished = false;
        highscore = new HighScore("highscores.txt");
        highscore.readHighScores();
        
        frame.setResizable(false);
        setPreferredSize(new Dimension(BreakOut.DEFAULT_WIDTH, BreakOut.DEFAULT_HEIGHT));
            player = new Player(playerName, INITIAL_SCORE);
            bricks = new Brick[NUMBER_OF_ROWS][NUMBER_OF_COLS];
            ballCounter = new Ball[3];
            this.difficulty = difficulty.getDifficultyValue();
            System.out.println(difficulty);
            //the diffculty level will determine the score of each row of bricks
            //initialize the bricks array
            Color c = Color.green;
            frame.setContentPane(this);
            this.addKeyListener(new KeyboardInput());
            this.setFocusable(true);
            this.requestFocusInWindow();
            for (int i = 0; i < NUMBER_OF_ROWS; i++) {
                for (int j = 0; j < NUMBER_OF_COLS; j++) {
                    switch (i) {
                        case 0:
                            score = 10 * difficulty.getDifficultyValue();
                            c = Color.cyan;
                            break;
                        case 1:
                            score = 8 * difficulty.getDifficultyValue();
                            c = Color.blue;
                            break;
                        case 2:
                            score = 6 * difficulty.getDifficultyValue();
                            c = Color.orange;
                            break;
                        case 3:
                            score = 4 * difficulty.getDifficultyValue();
                            c = Color.yellow;
                            break;
                        case 4:
                            score = 2 * difficulty.getDifficultyValue();
                            c = Color.magenta;
                            break;
                    }
                    if ((i == 0 && j == 0) || (i == 1 && j == 6) || (i == 3 && j == 3) || (i == 4 && j == 0)) {
                        special = true;
                    } else {
                        special = false;
                    }
                    bricks[i][j] = new Brick(BreakOut.DEFAULT_WIDTH / 8 * j, BRICK_HEIGHT * i, BRICK_WIDTH, BRICK_HEIGHT, c, true, score, special);
                }
            }
            Ball ball = new Ball();
            ballVelocityX = ball.getVelocityX() + (difficulty.getDifficultyValue() / 2);
            ballVelocityY = ball.getVelocityY() + (difficulty.getDifficultyValue() / 2);
            ball.setVelocityX(ballVelocityX);
            ball.setVelocityY(ballVelocityY);
            balls.add(ball);
            paddle = new Paddle(BreakOut.DEFAULT_WIDTH/2, BreakOut.DEFAULT_HEIGHT- (BreakOut.DEFAULT_HEIGHT/10), PADDLE_WIDTH / difficulty.getDifficultyValue(), PADDLE_HEIGHT, Color.black);
            repaint();
    }

    //-------------------------------------------------------------------------------------------------------------------------------------------
    /**
     * Checks the high score of the player after they have finished playing, to see if they are in the top ten scores,
     * if they are they get asks for name input and then the scores of the top ten are drawn to screen, otherwise the scores
     * are drawn regardless
     */
    public void checkHighScore(){
        Sort<Player> sortScores = new Sort(highscore.getHighScoresList());
        if (highscore.checkIfHigherThanLast(player)) 
        {
            String name = JOptionPane.showInputDialog(null, "Enter name :  ");
            if (name == null) 
            {
                name = "Unknown";
            }
            player.setPlayerName(name);   
        }
        revalidate();
        sortScores.quickSort();
        
        highscore.writeHighScores();
        m_isFinished = true;
    }

    /**
     * Checks the current score of the player and shows to screen what it is, also draws what difficulty is set for the game to
     * screen
     * @param g the graphics object used to draw text to screen
     */
    public void drawScore(Graphics g) {
        Font headingFont = new Font("SansSerif", Font.PLAIN, 12);
        g.setFont(headingFont);
        g.setColor(Color.yellow);
        String diff = "";
        switch(difficulty)
        {
            case 1:
                diff = "Easy";
            break;
            case 2:
                diff = "Medium";
            break;
            case 3:
                diff = "Hard";
            break;
        }
        FontMetrics headingFontMetrics = g.getFontMetrics(headingFont);
        g.setColor(Color.blue);
        g.drawString("Score: " + player.getScore(),
                50,
                ((getHeight() - 100) - headingFontMetrics.stringWidth("Score: " + player.getScore())));
        g.setColor(Color.red);
        g.drawString("Difficulty: " + diff,
                50,
                ((getHeight() - 50) - headingFontMetrics.stringWidth("Difficulty: " + difficulty)));
    }
    /**
     * Checks the missed ball counter and draws balls to the screen to represent how many tries you have left
     * @param g the graphics object used to draw the amount of balls/tries you have left
     */
    public void drawBallCounter(Graphics g)
    {
        int triesLeft = missedCounter;
        g.setColor(Color.blue);
        Font headingFont = new Font("SansSerif", Font.BOLD, 12);
        g.setFont(headingFont);
        g.drawString("Tries Left:",20,(BreakOut.DEFAULT_HEIGHT/2)-20);
        for(int i = 0; i < ballCounter.length; i++)
        {
            if(triesLeft < 1)
            {
                g.setColor(Color.blue);
                g.fillOval(35, BreakOut.DEFAULT_HEIGHT/2+(i*40), 20, 20);
            }
            else
            {
                g.setColor(Color.gray);
                g.fillOval(35, BreakOut.DEFAULT_HEIGHT/2+(i*40), 20, 20);
                triesLeft--;
            }
        }
    }
    //-------------------------------------------------------------------------------------------------------------------------------------------
    @Override
    public void run() {
        while (isRunning) 
        {
            boolean addBall = false;
            //check ball collision with sides
            for (Ball ball : balls) {
                if (ball.checkCollisionWithSides(getWidth())) 
                {
                    ball.setVelocityX(-ball.getVelocityX());
                }
                //check ball collision with bottom
                if (ball.checkCollisionWithBottom(getHeight())) 
                {
                    ball.setVelocityY(-ball.getVelocityY());
                    missedCounter++;
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
                            Rectangle brickRectangle = new Rectangle((BreakOut.DEFAULT_WIDTH / 8 * j), (BRICK_HEIGHT * i),
                                    bricks[i][j].getWidth(), bricks[i][j].getHeight());
                            if (brickRectangle.intersects(ballRectangle)) {
                                System.out.println("Detected brick collision");
                                bricksCounter--;
                                bricks[i][j].setIsVisible(false);
                                ball.setVelocityY(-ball.getVelocityY());
                                if (bricks[i][j].isIsSpecial()) {
                                    System.out.println("HIt a special brick");
                                    //balls.add(new Ball());
                                    addBall = true;
                                    //add it to the balls thread
                                }
                                player.setScore(player.getScore() + bricks[i][j].getScore());
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

                        if (ball1.checkCollisionWithOtherBall(ball2)) {
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
            //calls the ball.move() method to move the ball's position
            for (Ball ball : balls) {
                ball.move();
            }
            //adds a new ball to the ball collection if the boolean addBall is true
            if (addBall) {
                Ball ball = new Ball();
                ball.setVelocityX(ballVelocityX);
                ball.setVelocityY(ballVelocityY);
                balls.add(ball);
                addBall = false;
            }
            //checks if the 
            if (bricksCounter == 0 || missedCounter == 3)
            {
                stop();
                isRunning = false;
                repaint();
            }
            repaint();
            try {
                Thread.sleep(50);
            } catch (InterruptedException ex) {
                Logger.getLogger(Ball.class.getName()).log(
                        Level.SEVERE, null, ex);
            }
        }
        if (bricksCounter == 0 || missedCounter == 3)
        {
            checkHighScore();
            repaint();
        }
    }

    //-------------------------------------------------------------------------------------------------------------------------------------------
    /**
     * Getter for returning the game status 
     * @return a boolean used to run the while loop that processes the game, true returned if the game is running
     * false if not
     */
    public boolean checkGameStatus() {
        return isRunning;
    }
    /**
     * Getter for returning the bricks remaining in the game
     * @return an integer representing the bricks counter
     */
    public int getBricksCounter() {
        return bricksCounter;
    }
    /**
     * Getter for returning the amount of tries left
     * @return an integer representing the missed counter
     */
    public int getMissedCounter() {
        return missedCounter;
    }
    /**
     * Getter for returning the player's score
     * @return an integer that represents the player's score
     */
    public int getHighScore() {
        return player.getScore();
    }

    //-------------------------------------------------------------------------------------------------------------------------------------------
    //thread methods
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
        isPaused = true;
        stop();
        JOptionPane.showMessageDialog(null, "GAME PAUSED");
    }

    //-------------------------------------------------------------------------------------------------------------------------------------------
    @Override
    public void paintComponent(Graphics g) {
        g.clearRect(0, 0, this.getWidth(), this.getHeight());
        //draw remaining ball counter
        drawBallCounter(g);
        //draw score and difficulty
        drawScore(g);
        //draw bricks
        for (int i = 0; i < NUMBER_OF_ROWS; i++) {
            for (int j = 0; j < NUMBER_OF_COLS; j++) {
                if (bricks[i][j].isIsVisible()) {
                    bricks[i][j].drawBrick(g);
                }
            }
        }
        //draw balls
        for (Ball ball : balls) {
            ball.drawBall(g);
        }
        //draw paddle
        paddle.drawPaddle(g);
        if (missedCounter == 3 || bricksCounter == 0) {
            g.clearRect(0, 0, this.getWidth(), this.getHeight());
            highscore.drawHighScores(g);
        }
    }

    //-------------------------------------------------------------------------------------------------------------------------------------------
    /**
     * A private class that manages all the keyboard input, it is used to move the paddle left, right, and pause, resume the game
     */
    class KeyboardInput extends KeyAdapter 
    {
        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_LEFT && !isPaused && !m_isFinished) {
                paddle.moveLeft();
                if (paddle.getPositionX() < 0) {
                    paddle.setPositionX(0);
                }
                System.out.println("Moved left");
            } else if (e.getKeyCode() == KeyEvent.VK_RIGHT && !isPaused && !m_isFinished) {
                paddle.moveRight();
                if ((paddle.getPositionX() + paddle.getWidth()) > BreakOut.DEFAULT_WIDTH) {
                    paddle.setPositionX(BreakOut.DEFAULT_WIDTH - paddle.getWidth());
                }
                System.out.println("Moved right");
            } else if (e.getKeyCode() == KeyEvent.VK_B && !m_isFinished) {
                pause();
                System.out.println("thread paused");
            } else if (e.getKeyCode() == KeyEvent.VK_SPACE && isPaused) {
                start();
                System.out.println("thread resumed");
                isPaused = false;
            }
        }
    }
}
