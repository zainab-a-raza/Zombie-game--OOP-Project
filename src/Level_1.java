import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Iterator;
import java.util.Objects;

public class Level_1 extends Level{

    Platform plat1, plat2, plat3, plat4, plat5;
    Bullet bullet2, bullet3, bullet4, bullet5, bullet6;
    Ladder l1,l2,l3,l4;

    BasicZombie basicZombie;
    BasicZombie basicZombie2;

    Level_1() {
        int frameWidth = 1500;
        int platformWidth = 699;


        // Top platforms
        plat1 = new Platform(0, 200, "./longPlatform.png");                               // Top left
        plat2 = new Platform(frameWidth - platformWidth, 200, "./longPlatform.png");      // Top right
        // Middle platform
        plat3 = new Platform((frameWidth / 2) - (platformWidth / 2), 450, "./longPlatform.png");
        // Bottom platforms
        plat4 = new Platform(0, 700, "./longPlatform.png");                               // Bottom left
        plat5 = new Platform(frameWidth - platformWidth, 700, "./longPlatform.png");      // Bottom right

        // Background
        bg = new ImageIcon(Objects.requireNonNull(getClass().getResource("./bg2.png"))).getImage();

        // Add platforms
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

//        m1 = new Warrior(0, 0, "/player.png");
        //bullet = new Bullet(m1.x, m1.y, "/bullet.png");

        basicZombie = new BasicZombie(700, 200-61, "/zombies-01.png");
        basicZombie2 = new BasicZombie((1500 / 2) - (699/ 2), 380,"/zombies-01.png");


        zombies.add(basicZombie);
        zombies.add(basicZombie2);


    }


}
