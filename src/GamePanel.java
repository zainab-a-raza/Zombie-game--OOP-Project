import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class GamePanel extends JPanel implements KeyListener, ActionListener {
    protected int score = 0;
    protected int bulletcount = 5;
    protected int gameOver = 1;
    protected int timeLimit; // in seconds
    protected long levelStartTime;
    protected Timer healthBoosterTimer;
    protected boolean healthBoosterActive = false;
    protected GameObject healthBooster;

    public int getTimeLimit() {
        return timeLimit;
    }

    protected int currentLevel;

    public void setCurrentLevel(int level) {
        this.currentLevel = level;
    }

    public boolean checkLevelCompleted() {
        if (currentLevel == 0) { // Level 1 - score based
            return score >= 1;
        } else if (currentLevel == 1) { // Level 2 - survival
            return warriorAlive;
        }
        // Add conditions for other levels
        return false;
    }

    void spawnHealthBooster() {
        if (!healthBoosterActive) {
            int x = random.nextInt(1400);
            int y = random.nextInt(800);
            healthBooster = new GameObject(x, y, "/heart.png");
            healthBoosterActive = true;

            // Change duration to 5000ms (5 seconds)
            Timer hideTimer = new Timer(5000, e -> {
                healthBoosterActive = false;
                repaint();
            });
            hideTimer.setRepeats(false);
            hideTimer.start();
            repaint();
        }
    }

    public int getScore() {
        return score;
    }

    public boolean isWarriorAlive(){
        return warriorAlive;
    }

    JLabel displayScore = new JLabel();
    JLabel displayBullets = new JLabel();
    JLabel displayHealth=new JLabel();
    private JLabel timeLabel = new JLabel();


    boolean intersectplatform = false;
    Timer timer;
    Warrior m1;
    Bullet bullet;
    int key;
    Platform plat1, plat2, plat3, plat4, plat5;

    BasicZombie basicZombie;
    FastZombie fastZombie;
    //Rectangle ladderBox;
    Rectangle player;
    Image bg;//background image
    // Timer bulletSpawnTimer;
    // Timer zombieSpawnTimer;
    // final int BULLET_SPAWN_INTERVAL = 8000; // 8 seconds
    // final int ZOMBIE_SPAWN_INTERVAL = 10000;
    boolean bulletActive = false;
    boolean warriorAlive = true;

    Bullet bullet2, bullet3, bullet4, bullet5, bullet6;
    Ladder l1,l2,l3,l4;
    ArrayList<Platform> platforms = new ArrayList<>();
    ArrayList<Bullet> bullets = new ArrayList<>();

    /// POLYMORPHISM BELOW! YAYYY! ZombieBase array with child objects stored
    ArrayList<ZombieBase> zombies = new ArrayList<>();
    ArrayList<Ladder> ladders = new ArrayList<>();
    Random random=new Random();

    GamePanel() {
        m1 = new Warrior(0, 0, "/player.png");
        bullet = new Bullet(m1.x, m1.y, "/bullet.png");

        bullet2 = new Bullet(50, 600, "/bullet.png");
        bullet3 = new Bullet(50, 300, "/bullet.png");
        bullet4 = new Bullet(750, 100, "/bullet.png");
        bullet5 = new Bullet(195, 450, "/bullet.png");

        l1 = new Ladder(590,200,"./ladder.png");
        l2 = new Ladder(801,200,"./ladder.png");
        l3 = new Ladder(400,450,"./ladder.png");
        l4 = new Ladder(990,450,"./ladder.png");

        ladders.add(l1);
        ladders.add(l2);
        ladders.add(l3);
        ladders.add(l4);

        /// Bullets
        bullets.add(bullet2);
        bullets.add(bullet3);
        bullets.add(bullet4);
        bullets.add(bullet5);

        timeLabel.setFont(new Font("MV Boli", Font.PLAIN, 20));
        timeLabel.setForeground(Color.WHITE);
        timeLabel.setBounds(50, 800, 200, 20);
        this.add(timeLabel);

        ///  Displays
        displayScore.setText("Score: " + score);
        displayScore.setFont(new Font("MV boli", Font.PLAIN, 20));
        displayScore.setForeground(new Color(0xFFFFFF));
        displayScore.setBounds(800, 800, 20, 20);
        this.add(displayScore);

        displayBullets.setText("Bullet Count: " + bulletcount);
        displayBullets.setFont(new Font("MV boli", Font.PLAIN, 20));
        displayBullets.setForeground(new Color(0xFFFFFF));
        displayBullets.setBounds(800, 800, 20, 20);
        this.add(displayBullets);

        displayHealth.setText("Health: "+m1.health);
        displayHealth.setFont(new Font("MV boli", Font.PLAIN, 20));
        displayHealth.setForeground(new Color(0xFFFFFF));
        displayHealth.setBounds(800, 830, 20, 20);
        this.add(displayHealth);

        basicZombie = new BasicZombie(700, 200-61, "/zombies-01.png");
        zombies.add(basicZombie);

        ///
        addKeyListener(this);
        setFocusable(true);
        this.requestFocusInWindow();

        ///  Timers
        timer = new Timer(10, this);
        timer.start();
        // bulletSpawnTimer = new Timer(BULLET_SPAWN_INTERVAL, this);
        // bulletSpawnTimer.start();
        // zombieSpawnTimer = new Timer(ZOMBIE_SPAWN_INTERVAL, this);
        //zombieSpawnTimer.start();
        repaint();
    }

/// Spawns
    // private void spawnRandomBullet() {
    //  int x = random.nextInt(1400);  // Random x within screen width
    //   int y = random.nextInt(800);   // Random y within screen height
    //   bullets.add(new Bullet(x, y, "/bullet.png"));
    //     repaint();
    //   }
    /// Separate level
    // private void spawnZombie(){
    //   int x = random.nextInt(1400);  // Random x within screen width
    // int y = random.nextInt(800);   // Random y within screen height
    // zombies.add(new BasicZombie(x, y, "/zombies-01.png"));
    // int x2 = random.nextInt(1400);  // Random x within screen width
    // int y2 = random.nextInt(800);   // Random y within screen height
    // zombies.add(new FastZombie(x2, y2, "/zombies-08.png"));
    // repaint();
    //}

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(bg, 0, 0, 1500, 900, this);

        if (warriorAlive) {
            m1.draw(g);
            if(bulletcount>0) {
                bullet.draw(g);  // summon bullet
            }
        }
        else{
            g.setColor(Color.RED);
            g.setFont(new Font("MV Boli", Font.BOLD, 80));
            g.drawString("GAME OVER", 500, 400);
            timer.stop();
            gameOver = 2;
        }

        for (Platform p : platforms) {
            p.draw(g);
        }

        for (Bullet b : bullets) {
            if (b.active) {
                b.draw(g);
            }
        }

        for (ZombieBase z : zombies) {
            if (z.zombieAlive) {
                z.draw(g);
            }
        }
//
        for(Ladder l: ladders){
            l.draw(g);
        }

        displayScore.setText("Score: " + score);
        displayBullets.setText("Bullet Count: " + bulletcount);
        displayHealth.setText("Health: " + Math.max(0, m1.health));
    }

    @Override
    public void keyPressed(KeyEvent e) {
        key = e.getKeyCode();
        int cx = 0;
        int cy = 0;

        if (key == KeyEvent.VK_LEFT) {
            cx = -15;
        } else if (key == KeyEvent.VK_RIGHT) {
            cx = 15;
        }
        ///  jump
//        else if (key == KeyEvent.VK_UP) {
//            cy = -50;
//        }
        if (key == KeyEvent.VK_SPACE ) {
            if (!bulletActive && bulletcount > 0) {
                bulletActive = true;
                bulletcount--;
                displayBullets.setText("Bullets: " + bulletcount); // Update display
            }
        }


        for(Ladder l:ladders){
            Rectangle ladderBox = new Rectangle((l.x), l.y, l.width, l.height);
            if(key == KeyEvent.VK_UP && player.intersects(ladderBox)){
                m1.move(0,-300);
            }
            if(key == KeyEvent.VK_DOWN && player.intersects(ladderBox)){
                m1.move(0,300);
            }
        }


        m1.move(cx, cy);
        bullet.move(cx, cy);



        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //     if (e.getSource() == bulletSpawnTimer) {
        //       spawnRandomBullet();
        //     return;
        //}

        //     if (e.getSource() == zombieSpawnTimer) {
        //       spawnZombie();
        //     return;
        //}

        Rectangle bulletRect = new Rectangle(bullet.x, bullet.y, 50, 50);
        player = new Rectangle(m1.x, m1.y, 100, 136);


        for(ZombieBase z:zombies){
            Rectangle z1Rect = new Rectangle(z.x, z.y, 121, 211);
            if (z.zombieAlive) {
                z.move(z.dx, 0);

                if (player.intersects(z1Rect)) {
                    if (!z.justHitPlayer) {
                        // Only damage player if this is a new collision
                        m1.health -= 10;
                        z.justHitPlayer = true;
                        z.dx *= -1;  // Make zombie turn around

                        if (m1.health <= 0) {
                            warriorAlive = false;
                            m1.health = 0;
                        }
                    }
                } else {
                    // Reset flag when not colliding
                    z.justHitPlayer = false;
                }

                if (z.x >= 1250 || z.x < 0) {
                    z.dx *= -1;
                }
            }
            if (!bulletActive) {
                bullet.x = m1.x + 50;
                bullet.y = m1.y + 68;
            } else {
                bullet.move(10, 0);
                if (bullet.x > 1500) {
                    bulletActive = false;
                }

                if (bulletRect.intersects(z1Rect) && z.zombieAlive) {
                    basicZombie.dx = 0;
                    z.zombieAlive = false;
                    bulletActive = false;
                    score += 1;
                }
            }
        }

//////
//        for(Ladder l:ladders){
//            ladderBox = new Rectangle((l1.x), l1.y, l1.width, l1.height);
//        }


        if (m1.y > 900) {
            m1.y = 0;
            m1.x = 0;
            bullet.x = 0;   // bullet ka x , y needs to be same bhai
            bullet.y = 0;
        }

        Iterator<Bullet> iterator = bullets.iterator();
        while (iterator.hasNext()) {
            Bullet b = iterator.next();
            if (b.active) {
                Rectangle bulletBorder = new Rectangle(b.x, b.y, 25, 25);
                if (player.intersects(bulletBorder)) {
                    b.active = false;
                    bulletcount++;
                    iterator.remove();
                }
            }
        }

        long elapsed = (System.currentTimeMillis() - levelStartTime) / 1000;
        long remaining = timeLimit - elapsed;
        timeLabel.setText("Time: " + remaining + "s");

        if (healthBoosterActive) {
            Rectangle boosterRect = new Rectangle(healthBooster.x, healthBooster.y,
                    healthBooster.img.getWidth(null),
                    healthBooster.img.getHeight(null));
            if (player.intersects(boosterRect)) {
                m1.health = Math.min(100, m1.health + 10);
                healthBoosterActive = false;
                displayHealth.setText("Health: " + m1.health);
            }
        }

        int cy = 0;
//        for (Platform p : platforms) {
//            Rectangle plat = new Rectangle(p.x, p.y,  p.img.getWidth(null), 107);
//            if (!player.intersects(plat)) {
//                cy=15;
//            } else {
//                cy = 0;
//                break;
//            }
//        }

        m1.move(0, cy);
        repaint();
    }

    public int getGameOver() {
        return gameOver;
    }
}
