// import java.awt.*;
// import java.awt.event.ActionEvent;
// import java.awt.event.ActionListener;
// import java.util.Objects;
// import javax.swing.*;

// public class Level_2 extends Level {
//     Platform plat1, plat2, plat3, plat4, plat5;
//     Bullet bullet2, bullet3, bullet4, bullet5, bullet6;
//     Ladder l1, l2, l3, l4;

//     BasicZombie basicZombie;
//     FastZombie fastZombie;



//     Level_2() {

//         int frameWidth = 1500;
//         int platformWidth = 699;

//         bg = new ImageIcon(Objects.requireNonNull(getClass().getResource("./bg_lvl2.png"))).getImage();

//         // Top platforms
//         plat1 = new Platform(0, 200, "./longPlatform.png");                               // Top left
//         plat2 = new Platform(frameWidth - platformWidth, 200, "./longPlatform.png");      // Top right
//         // Middle platform
//         plat3 = new Platform((frameWidth / 2) - (platformWidth / 2), 450, "./longPlatform.png");
//         // Bottom platforms
//         plat4 = new Platform(0, 700, "./longPlatform.png");                               // Bottom left
//         plat5 = new Platform(frameWidth - platformWidth, 700, "./longPlatform.png");      // Bottom right

//         // Background

//         platforms.add(plat1);
//         platforms.add(plat2);
//         platforms.add(plat3);
//         platforms.add(plat4);
//         platforms.add(plat5);


//         l1 = new Ladder(590,200,"./ladder.png");
//         l2 = new Ladder(801,200,"./ladder.png");
//         l3 = new Ladder(400,450,"./ladder.png");
//         l4 = new Ladder(990,450,"./ladder.png");

//         ladders.add(l1);
//         ladders.add(l2);
//         ladders.add(l3);
//         ladders.add(l4);

//         bullet2 = new Bullet(50, 600, "/bullet.png");
//         bullet3 = new Bullet(700, 600, "/bullet.png");
//         bullet4 = new Bullet(550, 100, "/bullet.png");
//         bullet5 = new Bullet(750, 400, "/bullet.png");


//         /// Bullets
//         bullets.add(bullet2);
//         bullets.add(bullet3);
//         bullets.add(bullet4);
//         bullets.add(bullet5);

//         basicZombie = new BasicZombie(700, 200-61, "/zombies-01.png");
//         fastZombie = new FastZombie((1500 / 2) - (699/ 2), 380,"/zombies-08.png");


//         zombies.add(basicZombie);
//         zombies.add(fastZombie);


//     }

// }
// ///// Spawns
// //     private void spawnRandomBullet() {
// //      int x = random.nextInt(1400);  // Random x within screen width
// //       int y = random.nextInt(800);   // Random y within screen height
// //       bullets.add(new Bullet(x, y, "/bullet.png"));
// //         repaint();
// //       }
// //     private void spawnZombie(){
// //       int x = random.nextInt(1400);  // Random x within screen width
// //     int y = random.nextInt(800);   // Random y within screen height
// //     zombies.add(new BasicZombie(x, y, "/zombies-01.png"));
// //     int x2 = random.nextInt(1400);  // Random x within screen width
// //     int y2 = random.nextInt(800);   // Random y within screen height
// //     zombies.add(new FastZombie(x2, y2, "/zombies-08.png"));
// //     repaint();
// //    }
// //
// //    @Override
// //    public void actionPerformed(ActionEvent e){
// //        super.actionPerformed(e);
// //             if (e.getSource() == bulletSpawnTimer) {
// //               spawnRandomBullet();
// //             return;
// //        }
// //
// //             if (e.getSource() == zombieSpawnTimer) {
// //               spawnZombie();
// //             return;
// //        }
// //
// //        Rectangle player = new Rectangle(m1.x, m1.y, 100, 136);
// //        int cy = 0;
// //        for (Platform p : platforms) {
// //            Rectangle plat = new Rectangle(p.x, p.y, p.img.getWidth(null), 61);  // Use 61 (your platform height)
// //            if (!player.intersects(plat)) {
// //                cy = 15; // fall down
// //            } else {
// //                cy = 0;  // standing on platform
// //                break;
// //            }
// //        }
// //
// //        m1.move(0, cy);
// //        repaint();
// //
// //    }
// //}}
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;
import javax.swing.*;

public class Level_2 extends Level {
    private Platform plat1, plat2, plat3, plat4, plat5;
    private Bullet bullet2, bullet3, bullet4, bullet5, bullet6;
    private Ladder l1, l2, l3, l4;

    private BasicZombie basicZombie;
    private BasicZombie basicZombie2;
    private FastZombie fastZombie;
    private FastZombie fastZombie2;

    Level_2() {

        bg = new ImageIcon(Objects.requireNonNull(getClass().getResource("./bg_lvl2.png"))).getImage();

        // Top platforms
        plat1 = new Platform(0, 200, "./longPlatform.png");                               // Top left
        plat2 = new Platform(frameWidth - platformWidth, 200, "./longPlatform.png");      // Top right
        // Middle platform
        plat3 = new Platform((frameWidth / 2) - (platformWidth / 2), 450, "./longPlatform.png");
        // Bottom platforms
        plat4 = new Platform(0, 700, "./longPlatform.png");                               // Bottom left
        plat5 = new Platform(frameWidth - platformWidth, 700, "./longPlatform.png");      // Bottom right

        // Background

        platforms.add(plat1);
        platforms.add(plat2);
        platforms.add(plat3);
        platforms.add(plat4);
        platforms.add(plat5);


        l1 = new Ladder(590,200,"./ladder.png");
        l2 = new Ladder(801,200,"./ladder.png");
        l3 = new Ladder(400,450,"./ladder.png");
        l4 = new Ladder(990,450,"./ladder.png");

        ladders.add(l1);
        ladders.add(l2);
        ladders.add(l3);
        ladders.add(l4);

        bullet2 = new Bullet(50, 600, "/bullet.png");
        bullet3 = new Bullet(700, 600, "/bullet.png");
        bullet4 = new Bullet(550, 100, "/bullet.png");
        bullet5 = new Bullet(750, 400, "/bullet.png");


        /// Bullets
        bullets.add(bullet2);
        bullets.add(bullet3);
        bullets.add(bullet4);
        bullets.add(bullet5);


        basicZombie = new BasicZombie(700, 200 - 110, "/zombies-01.png");
        basicZombie2 = new BasicZombie(700, 450 - 110, "/zombies-01.png");
        fastZombie = new FastZombie(700, 450 - 125, "/zombies-08.png");
        fastZombie2 = new FastZombie(5, 700 - 125, "/zombies-08.png");


        zombies.add(basicZombie);
        zombies.add(basicZombie2);
        zombies.add(fastZombie);
        zombies.add(fastZombie2);


    }
}
