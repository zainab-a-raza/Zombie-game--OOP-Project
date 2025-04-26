import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;
import java.util.Random;

public class GamePanel extends JPanel implements KeyListener, ActionListener {
    int score = 0;
    int bulletcount = 5;

    JLabel displayScore = new JLabel();
    JLabel displayBullets = new JLabel();
    JLabel displayHealth=new JLabel();

    boolean intersectplatform = false;
    Timer timer;
    Warrior m1;
    Bullet bullet;
    int key;
    Platform plat1, plat2, plat3, plat4, plat5;
    Zombies z1;
    Image bg;//background image
    Timer bulletSpawnTimer;
    Timer zombieSpawnTimer;
    final int BULLET_SPAWN_INTERVAL = 8000; // 8 seconds
    final int ZOMBIE_SPAWN_INTERVAL = 10000;
    boolean bulletActive = false;

    boolean warriorAlive = true;

    Bullet bullet2, bullet3, bullet4, bullet5, bullet6;
//    Ladder l1;
    ArrayList<Platform> platforms = new ArrayList<>();
    ArrayList<Bullet> bullets = new ArrayList<>();
    ArrayList<Zombies> zombies = new ArrayList<>();
    ArrayList<Ladder> ladders = new ArrayList<>();
    Random random=new Random();

    GamePanel() {
        m1 = new Warrior(0, 0, "/player.png");
        bullet = new Bullet(m1.x, m1.y, "/bullet.png");

        bullet2 = new Bullet(50, 600, "/bullet.png");
        bullet3 = new Bullet(50, 300, "/bullet.png");
        bullet4 = new Bullet(750, 100, "/bullet.png");
        bullet5 = new Bullet(195, 450, "/bullet.png");

//        l1 = new Ladder(350,320,"./ladder.png");

        bullets.add(bullet2);
        bullets.add(bullet3);
        bullets.add(bullet4);
        bullets.add(bullet5);

        plat1 = new Platform(20, 700, "./platform.png");
        plat2 = new Platform(20, 320, "./platform.png");
        plat3 = new Platform(700, 150, "./platform.png");
        plat4 = new Platform(700, 500, "./platform.png");
        plat5 = new Platform(20 + 175, 500, "./platform.png");

        platforms.add(plat1);
        platforms.add(plat2);
        platforms.add(plat3);
        platforms.add(plat4);
        platforms.add(plat5);

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

        bg = new ImageIcon(Objects.requireNonNull(getClass().getResource("./bg2.png"))).getImage();

        z1 = new Zombies(700, 235, "/zombies-01.png");

        zombies.add(z1);
        addKeyListener(this);
        setFocusable(true);
        this.requestFocusInWindow();
        timer = new Timer(10, this);
        timer.start();
        bulletSpawnTimer = new Timer(BULLET_SPAWN_INTERVAL, this);
        bulletSpawnTimer.start();
        zombieSpawnTimer = new Timer(ZOMBIE_SPAWN_INTERVAL, this);
        zombieSpawnTimer.start();
        repaint();
    }



    private void spawnRandomBullet() {
        int x = random.nextInt(1400);  // Random x within screen width
        int y = random.nextInt(800);   // Random y within screen height
        bullets.add(new Bullet(x, y, "/bullet.png"));
        repaint();
    }

    private void spawnZombie(){
        int x = random.nextInt(1400);  // Random x within screen width
        int y = random.nextInt(800);   // Random y within screen height
        zombies.add(new Zombies(x, y, "/zombies-01.png"));
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(bg, 0, 0, 1500, 900, this);

        if (warriorAlive) {
            m1.draw(g);
            bullet.draw(g);  // summon bullet
        }


        for (Platform p : platforms) {
            p.draw(g);
        }

        for (Bullet b : bullets) {
            if (b.active) {
                b.draw(g);
            }

        }

        for (Zombies z : zombies) {
            if (z.zombieAlive) {
                z.draw(g);
            }

        }
//
//        l1.draw(g);
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
        else if (key == KeyEvent.VK_UP) {
            cy = -50;
        }
        if (key == KeyEvent.VK_SPACE ) {
            if (!bulletActive && bulletcount > 0) {
                bulletActive = true;
                bulletcount--;
                displayBullets.setText("Bullets: " + bulletcount); // Update display
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
        if (e.getSource() == bulletSpawnTimer) {
            spawnRandomBullet();
            return;
        }

        if (e.getSource() == zombieSpawnTimer) {
            spawnZombie();
            return;
        }

        Rectangle bulletRect = new Rectangle(bullet.x, bullet.y, 50, 50);
        Rectangle player = new Rectangle(m1.x, m1.y, 100, 136);


        for(Zombies z:zombies){
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
                    z1.dx = 0;
                    z.zombieAlive = false;
                    bulletActive = false;
                    score += 1;
                }
            }
        }


//        Rectangle ladderTop = new Rectangle((l1.x+100)/2, l1.y, 100/4, 60);
//        Rectangle ladderBottom = new Rectangle((l1.x+100)/2, l1.y+108, 100/4, 70);
//        if(player.intersects(ladderTop)){
//            m1.move(0,168);
//        }
//        if (player.intersects(ladderBottom)){
//            m1.move(0,-168);
//        }



        if (m1.y > 900) {
            m1.y = 0;
            m1.x = 0;
            bullet.x = 0;   // bullet ka x , y needs to be same bhai
            bullet.y = 0;
        }


        int cy = 0;
        for (Platform p : platforms) {
            Rectangle plat = new Rectangle(p.x, p.y,  p.img.getWidth(null), 107);
            if (!player.intersects(plat)) {
                cy=15;
            } else {
                cy = 0;
                break;
            }
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

        m1.move(0, cy);
        repaint();
    }
}
