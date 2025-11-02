import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PongGame extends JPanel implements ActionListener, KeyListener {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int PADDLE_WIDTH = 20;
    private static final int PADDLE_HEIGHT = 100;
    private static final int BALL_DIAMETER = 20;
    private static final int PADDLE_SPEED = 10;

    private int paddle1Y, paddle2Y;
    private int ballX, ballY;
    private int ballXVelocity = 5;
    private int ballYVelocity = 5;
    private int score1 = 0;
    private int score2 = 0;

    private boolean p1Up, p1Down, p2Up, p2Down;

    public PongGame() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(this);
        reset();
        Timer timer = new Timer(1000 / 60, this); // 60 FPS
        timer.start();
    }

    private void reset() {
        paddle1Y = HEIGHT / 2 - PADDLE_HEIGHT / 2;
        paddle2Y = HEIGHT / 2 - PADDLE_HEIGHT / 2;
        ballX = WIDTH / 2 - BALL_DIAMETER / 2;
        ballY = HEIGHT / 2 - BALL_DIAMETER / 2;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.WHITE);

        // Draw paddles
        g.fillRect(PADDLE_WIDTH, paddle1Y, PADDLE_WIDTH, PADDLE_HEIGHT);
        g.fillRect(WIDTH - PADDLE_WIDTH * 2, paddle2Y, PADDLE_WIDTH, PADDLE_HEIGHT);

        // Draw ball
        g.fillOval(ballX, ballY, BALL_DIAMETER, BALL_DIAMETER);

        // Draw scores
        g.setFont(new Font("Arial", Font.BOLD, 40));
        g.drawString(String.valueOf(score1), WIDTH / 4, 50);
        g.drawString(String.valueOf(score2), WIDTH * 3 / 4, 50);

        // Draw center line
        for (int i = 0; i < HEIGHT; i += 30) {
            g.fillRect(WIDTH / 2 - 2, i, 4, 15);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        movePaddles();
        moveBall();
        checkCollisions();
        repaint();
    }

    private void movePaddles() {
        if (p1Up && paddle1Y > 0) paddle1Y -= PADDLE_SPEED;
        if (p1Down && paddle1Y < HEIGHT - PADDLE_HEIGHT) paddle1Y += PADDLE_SPEED;
        if (p2Up && paddle2Y > 0) paddle2Y -= PADDLE_SPEED;
        if (p2Down && paddle2Y < HEIGHT - PADDLE_HEIGHT) paddle2Y += PADDLE_SPEED;
    }

    private void moveBall() {
        ballX += ballXVelocity;
        ballY += ballYVelocity;
    }

    private void checkCollisions() {
        // Ball collision with top/bottom walls
        if (ballY <= 0 || ballY >= HEIGHT - BALL_DIAMETER) {
            ballYVelocity *= -1;
        }

        // Ball collision with paddles
        Rectangle ballRect = new Rectangle(ballX, ballY, BALL_DIAMETER, BALL_DIAMETER);
        Rectangle paddle1Rect = new Rectangle(PADDLE_WIDTH, paddle1Y, PADDLE_WIDTH, PADDLE_HEIGHT);
        Rectangle paddle2Rect = new Rectangle(WIDTH - PADDLE_WIDTH * 2, paddle2Y, PADDLE_WIDTH, PADDLE_HEIGHT);

        if (ballRect.intersects(paddle1Rect) || ballRect.intersects(paddle2Rect)) {
            ballXVelocity *= -1;
        }

        // Scoring
        if (ballX < 0) {
            score2++;
            reset();
        }
        if (ballX > WIDTH) {
            score1++;
            reset();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_W) p1Up = true;
        if (key == KeyEvent.VK_S) p1Down = true;
        if (key == KeyEvent.VK_UP) p2Up = true;
        if (key == KeyEvent.VK_DOWN) p2Down = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_W) p1Up = false;
        if (key == KeyEvent.VK_S) p1Down = false;
        if (key == KeyEvent.VK_UP) p2Up = false;
        if (key == KeyEvent.VK_DOWN) p2Down = false;
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    public static void main(String[] args) {
        JFrame frame = new JFrame("Pong");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.add(new PongGame());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
