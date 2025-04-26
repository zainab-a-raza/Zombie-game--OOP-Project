import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;
import javax.swing.*;

public class Level_2 extends GamePanel {

    Timer bulletSpawnTimer;
    Timer zombieSpawnTimer;
    final int BULLET_SPAWN_INTERVAL = 8000; // 8 seconds
    final int ZOMBIE_SPAWN_INTERVAL = 10000;

    Level_2() {
        int frameWidth = 1500;
        int platformWidth = 699;

        // Top platforms
        plat1 = new Platform(0, 200, "./longPlatform.png");                               // Top left
        plat2 = new Platform(frameWidth - platformWidth, 200, "./longPlatform.png");      // Top right

        // Middle platform
        plat3 = new Platform((frameWidth / 2) - (platformWidth / 2), 500, "./longPlatform.png");

        // Bottom platforms
        plat4 = new Platform(0, 700, "./longPlatform.png");                               // Bottom left
        plat5 = new Platform(frameWidth - platformWidth, 700, "./longPlatform.png");      // Bottom right

        // Background
        bg = new ImageIcon(Objects.requireNonNull(getClass().getResource("./bg_lvl2.png"))).getImage();

        // Add platforms
        platforms.add(plat1);
        platforms.add(plat2);
        platforms.add(plat3);
        platforms.add(plat4);
        platforms.add(plat5);

         bulletSpawnTimer = new Timer(BULLET_SPAWN_INTERVAL, this);
         bulletSpawnTimer.start();
         zombieSpawnTimer = new Timer(ZOMBIE_SPAWN_INTERVAL, this);
        zombieSpawnTimer.start();
        repaint();
    }
/// Spawns
     private void spawnRandomBullet() {
      int x = random.nextInt(1400);  // Random x within screen width
       int y = random.nextInt(800);   // Random y within screen height
       bullets.add(new Bullet(x, y, "/bullet.png"));
         repaint();
       }
     private void spawnZombie(){
       int x = random.nextInt(1400);  // Random x within screen width
     int y = random.nextInt(800);   // Random y within screen height
     zombies.add(new BasicZombie(x, y, "/zombies-01.png"));
     int x2 = random.nextInt(1400);  // Random x within screen width
     int y2 = random.nextInt(800);   // Random y within screen height
     zombies.add(new FastZombie(x2, y2, "/zombies-08.png"));
     repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e){
        super.actionPerformed(e);
             if (e.getSource() == bulletSpawnTimer) {
               spawnRandomBullet();
             return;
        }

             if (e.getSource() == zombieSpawnTimer) {
               spawnZombie();
             return;
        }

        Rectangle player = new Rectangle(m1.x, m1.y, 100, 136);
        int cy = 0;
        for (Platform p : platforms) {
            Rectangle plat = new Rectangle(p.x, p.y, p.img.getWidth(null), 61);  // Use 61 (your platform height)
            if (!player.intersects(plat)) {
                cy = 15; // fall down
            } else {
                cy = 0;  // standing on platform
                break;
            }
        }

        m1.move(0, cy);
        repaint();

    }
}