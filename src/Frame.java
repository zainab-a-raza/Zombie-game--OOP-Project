import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Frame {
    private static int currentLevel = 0;
    private static JFrame myFrame;
    private static ArrayList<GamePanel> levels = new ArrayList<>();
    private static Thread levelTimerThread;
    private static Timer gameOverTimer;

    public static void main(String[] args) {
        levels.add(new Level_1());
        levels.add(new Level_2());
        // levels.add(new Level_3());

        myFrame = new JFrame("Zombie Game");
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setSize(1500, 900);
        startLevel(currentLevel);

        myFrame.setVisible(true);
    }

    private static void startLevel(int levelIndex) {
        if (levelIndex >= levels.size()) {
            showGameComplete();
            return;
        }

        // Cancel any existing timer
        if (levelTimerThread != null && levelTimerThread.isAlive()) {
            levelTimerThread.interrupt();
        }

        myFrame.getContentPane().removeAll();
        GamePanel level = levels.get(levelIndex);
        level.setCurrentLevel(levelIndex);
        myFrame.add(level);
        myFrame.revalidate();
        myFrame.repaint();

        levelTimerThread = new Thread(() -> {
            try {
                long startTime = System.currentTimeMillis();
                while (System.currentTimeMillis() - startTime < level.getTimeLimit() * 1000L) {
                    if (level.checkLevelCompleted()) {
                        SwingUtilities.invokeLater(() -> {
                            currentLevel++;
                            showLevelTransition(currentLevel);
                            startLevel(currentLevel);
                        });
                        return;
                    }
                    Thread.sleep(100); // Check 10 times per second
                }

                // Time expired
                SwingUtilities.invokeLater(() -> {
                    if (level.checkLevelCompleted()) {
                        currentLevel++;
                        startLevel(currentLevel);
                    } else {
                        showGameOver();
                    }
                });
            } catch (InterruptedException e) {
                // Normal interruption when level completes early
            }
        });
        levelTimerThread.start();
    }

    static void showLevelTransition(int nextLevel) {
        myFrame.getContentPane().removeAll();
        JLabel label = new JLabel("Level " + (nextLevel+1) + " Starting...", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 60));
        label.setForeground(Color.WHITE);
        myFrame.add(label);
        myFrame.revalidate();

        // Auto-progress after 2 seconds
        new Timer(2000, e -> {}).setRepeats(false);
    }

    protected static void showGameOver() {
        myFrame.getContentPane().removeAll();
        JLabel gameOverLabel = new JLabel("GAME OVER", SwingConstants.CENTER);
        gameOverLabel.setFont(new Font("Arial", Font.BOLD, 80));
        gameOverLabel.setForeground(Color.RED);
        myFrame.add(gameOverLabel);
        myFrame.revalidate();

        // Exit after 3 seconds
        gameOverTimer = new Timer(3000, e -> {
            myFrame.dispose();
            System.exit(0);
        });
        gameOverTimer.setRepeats(false);
        gameOverTimer.start();
    }

    private static void showGameComplete() {
        myFrame.getContentPane().removeAll();
        JLabel completeLabel = new JLabel("YOU WIN!", SwingConstants.CENTER);
        completeLabel.setFont(new Font("Arial", Font.BOLD, 80));
        completeLabel.setForeground(Color.GREEN);
        myFrame.add(completeLabel);
        myFrame.revalidate();

        gameOverTimer = new Timer(3000, e -> {
            myFrame.dispose();
            System.exit(0);
        });
        gameOverTimer.setRepeats(false);
        gameOverTimer.start();
    }
}
