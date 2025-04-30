import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class GamePanel extends JPanel implements ActionListener, KeyListener {

    Timer timer;
    LevelManager levelManager;
    int key;


    ImageIcon startImage = new ImageIcon(getClass().getResource("./startButton.png"));

    ImageIcon retry = new ImageIcon(getClass().getResource("./retryButton.png"));
    ImageIcon pause = new ImageIcon(getClass().getResource("./pauseButton.png"));
    Image startScreen = new ImageIcon(getClass().getResource("./startScreen.png")).getImage();
    Image endScreen = new ImageIcon(getClass().getResource("./endScreen.png")).getImage();
    JButton startButton;
    JButton retryButton = new JButton(retry);
    JButton pauseButton = new JButton(pause);


    boolean startGame = false;
    public GamePanel() {

        this.setPreferredSize(new Dimension(1500, 900));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.addKeyListener(this);

        levelManager = new LevelManager();

        JButton startButton = new JButton(startImage);
        startButton.setBounds(610,500,250,94);
        startButton.setOpaque(false);
        startButton.setContentAreaFilled(false);
        startButton.setBorderPainted(false);

        pauseButton.setBounds(1400,50,58,56);
        pauseButton.setOpaque(false);
        pauseButton.setContentAreaFilled(false);
        pauseButton.setBorderPainted(false);

        retryButton.setBounds(610,500,250,71);
        retryButton.setOpaque(false);
        retryButton.setContentAreaFilled(false);
        retryButton.setBorderPainted(false);


        timer = new Timer(16, this); // roughly 60 FPS
        this.setLayout(null);
        this.add(startButton);
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startGame = true;
                timer.start();
                remove(startButton);
            }
        });

        retryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                levelManager.levelNumber =1;
                levelManager.loadLevel(levelManager.levelNumber);
                levelManager.gamelost = false;
                GamePanel.this.remove(retryButton);
                repaint();
            }
        });

        pauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(timer.isRunning()){
                    timer.stop();
                }else{
                    timer.start();
                }
            }
        });

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(startGame==false && levelManager.gamelost == false){
            g.drawImage(startScreen,0,0,1500,900,null);
        }else if(startGame== true && levelManager.gamelost == true){
            g.drawImage(endScreen,0,0,1500,900,null);
            this.add(retryButton);
        }
        else{
            levelManager.draw(g);
            this.add(pauseButton);
        }




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

        int step =10;
        for(Ladder l:ladders){
            Rectangle ladderBox = new Rectangle((l.x), l.y, l.width, l.height);
            if(key == KeyEvent.VK_UP && player.intersects(ladderBox) && player.y>l.y){
                m1.move(0,-step);
            }
            if(key == KeyEvent.VK_DOWN && player.intersects(ladderBox) && (player.y+player.height<l.y +l.height)){
                m1.move(0,step);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) { }

    @Override
    public void keyTyped(KeyEvent e) { }
}
