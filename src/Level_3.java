// import javax.swing.*;
// import java.awt.*;
// import java.util.Objects;

// public class Level_3 extends Level{
//     Platform plat1, plat2, plat3, plat4, plat5;
//     Bullet bullet2, bullet3, bullet4, bullet5, bullet6;
//     Ladder l1, l2, l3, l4;

//     JumpingZombie jumpingZombie;
//     FastZombie fastZombie;



//     Level_3() {
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
//         bullet3 = new Bullet(50, 300, "/bullet.png");
//         bullet4 = new Bullet(750, 100, "/bullet.png");
//         bullet5 = new Bullet(195, 450, "/bullet.png");

//         /// Bullets
//         bullets.add(bullet2);
//         bullets.add(bullet3);
//         bullets.add(bullet4);
//         bullets.add(bullet5);

//         jumpingZombie = new JumpingZombie(300, 800, "/zombies-01.png");
//         fastZombie = new FastZombie((1500 / 2) - (699/ 3), 400,"/zombies-08.png");

//         zombies.add(jumpingZombie);
//         zombies.add(fastZombie);

//     }

// }
import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.util.Objects;

public class Level_3 extends Level{
    Platform plat1, plat2, plat3, plat4, plat5;
    Bullet bullet2, bullet3, bullet4, bullet5, bullet6;
    Ladder l1, l2, l3, l4;

    JumpingZombie jumpingZombie;
    FastZombie fastZombie;

    private Timer bulletSpawnTimer;
    private Timer zombieSpawnTimer;
    private Random random = new Random();


    Level_3() {

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
        bullet3 = new Bullet(50, 300, "/bullet.png");
        bullet4 = new Bullet(750, 100, "/bullet.png");
        bullet5 = new Bullet(195, 450, "/bullet.png");

        /// Bullets
        bullets.add(bullet2);
        bullets.add(bullet3);
        bullets.add(bullet4);
        bullets.add(bullet5);

        jumpingZombie = new JumpingZombie(300, 800, "/jumping zombie.png");
        fastZombie = new FastZombie((1500 / 2) - (699/ 3), 400,"/zombies-08.png");

        zombies.add(jumpingZombie);
        zombies.add(fastZombie);

        bulletSpawnTimer = new Timer(10000, e -> spawnRandomBullet());
        zombieSpawnTimer = new Timer(15000, e -> spawnRandomZombie());

        bulletSpawnTimer.start();
        zombieSpawnTimer.start();
    }

    private void spawnRandomBullet() {
        int x = random.nextInt(1400);
        int y = random.nextInt(800);
        bullets.add(new Bullet(x, y, "/bullet.png"));
    }
    private void spawnRandomZombie() {
        int x = random.nextInt(1400);
        int y = random.nextInt(800);
        int zombieType = random.nextInt(3);

        if (zombieType == 0) {
            zombies.add(new BasicZombie(x, y, "/zombies-01.png"));
        } else if (zombieType==1) {
            zombies.add(new JumpingZombie(x, y, "/jumping zombie.png"));
        } else {
            zombies.add(new FastZombie(x, y, "/zombies-08.png"));
        }
    }
    @Override
    public void reset() {
        super.reset();
        if (bulletSpawnTimer != null) {
            bulletSpawnTimer.stop();
        }
        if (zombieSpawnTimer != null) {
            zombieSpawnTimer.stop();
        }
    }

}
