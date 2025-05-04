import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class GamePanel extends JPanel implements ActionListener, KeyListener {

    Timer timer;
    LevelManager levelManager;
    int key;
    static boolean onLadder = false;

    ImageIcon startImage = new ImageIcon(getClass().getResource("./startButton.png"));

    ImageIcon retry = new ImageIcon(getClass().getResource("./retryButton.png"));
    ImageIcon pause = new ImageIcon(getClass().getResource("./pauseButton.png"));
    Image startScreen = new ImageIcon(getClass().getResource("./startScreen.png")).getImage();
    Image endScreen = new ImageIcon(getClass().getResource("./endScreen.png")).getImage();
    JButton startButton;
    JButton retryButton = new JButton(retry);
    JButton pauseButton = new JButton(pause);

    JTextField playerNameBox =  new JTextField();
    JLabel playerNameLabel = new JLabel("Enter player name");

    String playerName;

    boolean startGame = false;
    public GamePanel() {

        this.setPreferredSize(new Dimension(1500, 900));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.addKeyListener(this);

        levelManager = new LevelManager();

        JButton startButton = new JButton(startImage);
        startButton.setBounds(610,600,250,94);
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

        playerNameBox.setBounds(500,520,500,50);
        playerNameBox.setForeground(Color.GREEN);
        playerNameBox.setBackground(Color.BLACK);
        playerNameBox.setCaretColor(Color.GREEN);
        playerNameBox.setFont(new Font("Arial", Font.PLAIN,16));
        playerNameBox.setOpaque(false);
        playerNameBox.setBorder(new javax.swing.border.LineBorder(Color.GREEN,2,true));
        playerNameLabel.setHorizontalAlignment(JTextField.CENTER);

        playerNameLabel.setForeground(Color.WHITE);
        playerNameLabel.setFont(new Font("Arial", Font.PLAIN,16));
        playerNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        playerNameLabel.setBounds(500,480,500,50);
        this.add(playerNameLabel);



        timer = new Timer(16, this); // roughly 60 FPS
        this.setLayout(null);
        this.add(startButton);
        this.add(playerNameBox);
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startGame = true;
                timer.start();
                remove(startButton);
                remove(playerNameBox);
                remove(playerNameLabel);
                playerName = playerNameBox.getText();
            }
        });
        ////

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
        Rectangle player= new Rectangle(m1.x, m1.y, 82, 134);

        key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {
            m1.move(-10, 0);
        }
        if (key == KeyEvent.VK_RIGHT) {
            m1.move(10, 0);
        }
        /// ree
        if (key == KeyEvent.VK_SPACE) {
            if (currentLevel.grenade.isGrenadeInHand()) {
                currentLevel.grenade.setGrenadeInHand(false);
                currentLevel.grenade.setActive(true);  // only throw when pressed
            }
            else if (!currentLevel.bulletActive && currentLevel.bulletcount > 0) {
                currentLevel.bulletActive = true;
                currentLevel.bulletcount--;
            }
        }

        boolean isOnLadder = false;
        int step = 10;

        for (Ladder l : ladders) {
            Rectangle ladderBox = new Rectangle((l.x), l.y, l.width, l.height);

            if (player.intersects(ladderBox)) {
                isOnLadder = true;

                if (key == KeyEvent.VK_UP && player.y + player.height > l.y) {
                    m1.move(0, -step);
                    System.out.println("Command up" + step + " " + m1.y);
                }

                if (key == KeyEvent.VK_DOWN && player.y + player.height < l.y + l.height) {
                    m1.move(0, step);
                    System.out.println("Command down" + step + " " + m1.y);
                }
                break;
            }
        }

        GamePanel.onLadder = isOnLadder;

    }

    @Override
    public void keyReleased(KeyEvent e) { }

    @Override
    public void keyTyped(KeyEvent e) { }
}
