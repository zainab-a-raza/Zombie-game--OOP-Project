import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class GamePanel extends JPanel implements ActionListener, KeyListener {

    Timer timer;
    LevelManager levelManager;
    int key;

    public GamePanel() {

        this.setPreferredSize(new Dimension(1500, 900));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.addKeyListener(this);

        levelManager = new LevelManager();

        timer = new Timer(16, this); // roughly 60 FPS
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        levelManager.draw(g);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        levelManager.update();
        repaint();
    }

    // Handle key events (left, right, jump, shoot)
    @Override
    public void keyPressed(KeyEvent e) {
        Level currentLevel = levelManager.getCurrentLevel();
        Warrior m1 = currentLevel.m1;
        ArrayList<Ladder>ladders =currentLevel.ladders;
        Rectangle player= currentLevel.player;

        key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {
            m1.move(-10, 0);
        }
        if (key == KeyEvent.VK_RIGHT) {
            m1.move(10, 0);
        }
        /// ree
        if (key == KeyEvent.VK_SPACE) {
            if (currentLevel.grenade1.isGrenadeInHand()) {
                currentLevel.grenade1.setGrenadeInHand(false);
                currentLevel.grenade1.setActive(true);  // only throw when pressed
            }
            else if (!currentLevel.bulletActive && currentLevel.bulletcount > 0) {
                currentLevel.bulletActive = true;
                currentLevel.bulletcount--;
            }
        }

        for(Ladder l:ladders){
            Rectangle ladderBox = new Rectangle((l.x), l.y, l.width, l.height);
            if(key == KeyEvent.VK_UP && player.intersects(ladderBox)){
                m1.move(0,-300);
            }
            if(key == KeyEvent.VK_DOWN && player.intersects(ladderBox)){
                m1.move(0,270);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) { }

    @Override
    public void keyTyped(KeyEvent e) { }
}
