// import javax.swing.*;
// import java.awt.*;
// import java.util.ArrayList;
// import java.util.Iterator;

// public class Level extends JPanel{

//     Grenade grenade1 = new Grenade(5,50, "/grenade.png" );;

//     ArrayList<Platform> platforms = new ArrayList<>();
//     ArrayList<Bullet> bullets = new ArrayList<>();

//     /// POLYMORPHISM BELOW! YAYYY! ZombieBase array with child objects stored
//     ArrayList<ZombieBase> zombies = new ArrayList<>();
//     ArrayList<Ladder> ladders = new ArrayList<>();
//     boolean intersectplatform = false;
//     Warrior m1= new Warrior(0, 0, "/player.png");
//     Bullet bullet = new Bullet(m1.x, m1.y, "/bullet.png");
//     boolean bulletActive = false;
//     boolean warriorAlive = true;
//     protected int score = 0;
//     protected int bulletcount = 5;
//     protected int gameOver = 1;
//     Image bg;//background image

//     Rectangle player;

//     JLabel displayScore = new JLabel();
//     JLabel displayBullets = new JLabel();
//     JLabel displayHealth=new JLabel();

//     Level(){
//         displayScore.setText("Score: " + score);
//         displayScore.setFont(new Font("MV boli", Font.PLAIN, 20));
//         displayScore.setForeground(new Color(0xFFFFFF));
//         displayScore.setBounds(800, 800, 20, 20);
//         this.add(displayScore);

//         displayBullets.setText("Bullet Count: " + bulletcount);
//         displayBullets.setFont(new Font("MV boli", Font.PLAIN, 20));
//         displayBullets.setForeground(new Color(0xFFFFFF));
//         displayBullets.setBounds(800, 800, 20, 20);
//         this.add(displayBullets);

//         displayHealth.setText("Health: "+m1.health);
//         displayHealth.setFont(new Font("MV boli", Font.PLAIN, 20));
//         displayHealth.setForeground(new Color(0xFFFFFF));
//         displayHealth.setBounds(800, 830, 20, 20);
//         this.add(displayHealth);
//     }



//     public void draw(Graphics g){
//         g.drawImage(bg, 0, 0, 1500, 900, this);

//         if(grenade1.display){
//             grenade1.draw(g);
//         }
//         if(grenade1.isTriggerExplosion()){
//             grenade1.drawExplosion(g);
//         }

//         if (warriorAlive) {
//             m1.draw(g);
//             if(bulletcount>0) {
//                 bullet.draw(g);  // summon bullet
//             }
//         }
//         else{
//             g.setColor(Color.RED);
//             g.setFont(new Font("MV Boli", Font.BOLD, 80));
//             g.drawString("GAME OVER", 500, 400);
// //            timer.stop();
//             gameOver = 2;
//         }

//         for (Platform p : platforms) {
//             p.draw(g);
//         }

//         for (Bullet b : bullets) {
//             if (b.active) {
//                 b.draw(g);
//             }
//         }

//         for (ZombieBase z : zombies) {
//             if (z.zombieAlive) {
//                 z.draw(g);
//             }
//         }
// //
//         for(Ladder l: ladders){
//             l.draw(g);
//         }

//         g.setColor(Color.WHITE);
//         g.setFont(new Font("MV Boli", Font.PLAIN, 20));
//         g.drawString("Score: " + score, 1200, 50); // Adjust x,y as you want
//         g.drawString("Bullet Count: " + bulletcount, 1200, 80);
//         g.drawString("Health: " + Math.max(0, m1.health), 1200, 110);
//     };


//     public void update() {
//         player = new Rectangle(m1.x, m1.y, 100, 136);
//         int cy = 0;
//         for (Platform p : platforms) {
//             Rectangle plat = new Rectangle(p.x, p.y+10, p.img.getWidth(null), 61);  // Use 61 (your platform height)
//             if (!player.intersects(plat)) {
//                 cy = 15; // fall down
//             } else {
//                 cy = 0;  // standing on platform
//                 break;
//             }
//         }
//         /// ree
//         Rectangle grenadeRect = new Rectangle(grenade1.x, grenade1.y, 150, 150);
//         if (grenade1.isTriggerExplosion()) {
//             grenade1.updateExplosion();
//         }

//         /// ree

//         Rectangle bulletRect = new Rectangle(bullet.x, bullet.y, 50, 50);
//         player = new Rectangle(m1.x, m1.y, 100, 136);

//         /// ree

//         if (grenadeRect.intersects(player)) {
//             grenade1.setGrenadeInHand(true);  // Pick up
//         }

//         if (grenade1.isActive()) {
//             grenade1.move(10);
//             if (grenade1.x > 1500) {
//                 grenade1.setActive(false);
//             }
//         }
//         else if (grenade1.isGrenadeInHand()) {
//             // if it's still in hand, stick to player
//             grenade1.x = m1.x + 30;
//             grenade1.y = m1.y + 40;
//         }
//         /// ree
//         if(!grenade1.isGrenadeInHand()){
//             if (!bulletActive) {
//                 bullet.x = m1.x + 50;
//                 bullet.y = m1.y + 68;
//             } else {
//                 bullet.move(10);
//                 if (bullet.x > 1500) {
//                     bulletActive = false;
//                 }
//             }
//         }

//         ///
//         for(ZombieBase z:zombies){
//             Rectangle z1Rect = new Rectangle(z.x, z.y, 121, 211);
//             if (z.zombieAlive) {
//                 z.move();

//                 if (player.intersects(z1Rect)) {
//                     if (!z.justHitPlayer) {
//                         // Only damage player if this is a new collision
//                         m1.health -= 10;
//                         z.justHitPlayer = true;
//                         z.dx *= -1;  // Make zombie turn around

//                         if (m1.health <= 0) {
//                             warriorAlive = false;
//                             m1.health = 0;
//                         }
//                     }
//                 } else {
//                     // Reset flag when not colliding
//                     z.justHitPlayer = false;
//                 }

//                 if (z.x >= 1250 || z.x < 0) {
//                     z.dx *= -1;
//                 }
//                 // -- Grenade hits zombie --
//                 if (grenadeRect.intersects(z1Rect) && grenade1.isActive()) {
//                     grenade1.setTriggerExplosion(true);

//                     z.zombieAlive = false;
//                     z.dx = 0;
//                     grenade1.display =false; // check if better way
//                     grenade1.setActive(false);
//                     grenade1.setGrenadeInHand(false);
//                     score += 1;
//                 }
//                 ///  reee
//                 // -- Bullet hits zombie --
//                 if (bulletActive && bulletRect.intersects(z1Rect)) {
//                     z.zombieAlive = false;
//                     z.dx = 0;
//                     bulletActive = false;
//                     score += 1;
//                 }
//             }
//             ///

//             if (m1.y > 900) {
//                 m1.y = 0;
//                 m1.x = 0;
//                 bullet.x = 0;   // bullet ka x , y needs to be same bhai
//                 bullet.y = 0;
//             }

//             Iterator<Bullet> iterator = bullets.iterator();
//             while (iterator.hasNext()) {
//                 Bullet b = iterator.next();
//                 if (b.active) {
//                     Rectangle bulletBorder = new Rectangle(b.x, b.y, 25, 25);
//                     if (player.intersects(bulletBorder)) {
//                         b.active = false;
//                         bulletcount++;
//                         iterator.remove();
//                     }
//                 }
//             }

//             m1.move(0, cy);
//         }
//     }

//     public boolean isLevelCompleted() {
//         for (ZombieBase z : zombies) {
//             if (z.zombieAlive) {
//                 return false;
//             }
//         }
//         return true;
// //        for (ZombieBase z : zombies) {
// //            if (z.zombieAlive) {
// //                return false;
// //            }
// //        }
// //        return true;
//     }


//     public boolean isGameOver() {
//         if(m1.health <= 0){
//             return true;
//         }else{
//             return false;
//         }
//     }


//     public void reset() {

//     }

// }
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Iterator;

public class Level extends JPanel{

    protected int frameWidth = 1500;
    protected int platformWidth = 699;


    ArrayList<Platform> platforms = new ArrayList<>();
    ArrayList<Bullet> bullets = new ArrayList<>();
    ArrayList<Grenade> grenades=new ArrayList<>();
    //ArrayList<HealthBooster> healthBoosters=new ArrayList<>();

    /// POLYMORPHISM BELOW! YAYYY! ZombieBase array with child objects stored
    ArrayList<ZombieBase> zombies = new ArrayList<>();
    ArrayList<Ladder> ladders = new ArrayList<>();
    boolean intersectplatform = false;
    Warrior m1= new Warrior(0, 0, "/player.png");
    Bullet bullet = new Bullet(m1.x, m1.y, "/bullet.png");
    Grenade grenade = new Grenade(m1.x, m1.y, "/grenade.png" );
    boolean bulletActive = false;
    boolean warriorAlive = true;
    protected int score = 0;
    protected int bulletcount = 5;
    protected int grenadecount = 1;
    protected int gameOver = 1;
    Image bg;//background image

    Rectangle player;

    JLabel displayScore = new JLabel();
    JLabel displayBullets = new JLabel();
    JLabel displayGrenades= new JLabel();
    JLabel displayHealth=new JLabel();

    Level(){
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

        displayGrenades.setText("Grenade Count: " + grenadecount);
        displayGrenades.setFont(new Font("MV boli", Font.PLAIN, 20));
        displayGrenades.setForeground(new Color(0xFFFFFF));
        displayGrenades.setBounds(800, 800, 20, 20);
        this.add(displayGrenades);

        displayHealth.setText("Health: "+m1.health);
        displayHealth.setFont(new Font("MV boli", Font.PLAIN, 20));
        displayHealth.setForeground(new Color(0xFFFFFF));
        displayHealth.setBounds(800, 830, 20, 20);
        this.add(displayHealth);
        grenades.add(grenade);
    }
    public void draw(Graphics g){
        g.drawImage(bg, 0, 0, 1500, 900, this);

        for (Grenade grenade:grenades){
            if (grenadecount>0){
                if(grenade.display){
                    grenade.draw(g);
                }
                if(grenade.isTriggerExplosion()){
                    grenade.drawExplosion(g);
                }
            }
        }
//        for (HealthBooster healthBooster:healthBoosters){
//            if (healthBooster.getisActive()){
//                healthBooster.draw(g);
//            }
//        }
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

//        for (Grenade grenade1:grenades){
//            if (grenade1.active){
//                grenade1.draw(g);
//            }
//        }

        for (ZombieBase z : zombies) {
            if (z.zombieAlive) {
                z.draw(g);
            }
        }
//
        for(Ladder l: ladders){
            l.draw(g);
        }

        g.setColor(Color.WHITE);
        g.setFont(new Font("MV Boli", Font.PLAIN, 20));
        g.drawString("Score: " + score, 1200, 50); // Adjust x,y as you want
        g.drawString("Bullet Count: " + bulletcount, 1200, 80);
        g.drawString("Health: " + Math.max(0, m1.health), 1200, 110);
        g.drawString("Grenade Count: "+grenadecount,1200,140);
    }


    public void update() {
        player = new Rectangle(m1.x, m1.y, 100, 136);

        int cy = 15;

        for (Platform p : platforms) {
            Rectangle plat = new Rectangle(p.x, p.y + 10, p.img.getWidth(null), 61);
            if (player.intersects(plat)) {
                cy = 0;
                break;
            }
        }

        /// ree
        Rectangle grenadeRect = new Rectangle(grenade.x, grenade.y, 40, 40);
        if (grenade.isTriggerExplosion()) {
            grenade.updateExplosion();
        }

        /// ree

        Rectangle bulletRect = new Rectangle(bullet.x, bullet.y, 40, 40);
        player = new Rectangle(m1.x, m1.y, 100, 136);

        /// ree

        if (grenadeRect.intersects(player)) {
            grenade.setGrenadeInHand(true);  // Pick up
        }

        if (grenade.isActive()) {
            grenade.move(10,0);
            if (grenade.x > 1500) {
                grenade.setActive(false);
            }
        }
        else if (grenade.isGrenadeInHand()) {
            // if it's still in hand, stick to player
            grenade.x = m1.x + 30;
            grenade.y = m1.y + 40;
        }
        /// ree
        if(!grenade.isGrenadeInHand()){
            if (!bulletActive) {
                bullet.x = m1.x + 50;
                bullet.y = m1.y + 68;
            } else {
                bullet.move(10,0);
                if (bullet.x > 1500) {
                    bulletActive = false;
                }
            }
        }

        ///
        for(ZombieBase z:zombies) {
            Rectangle z1Rect = new Rectangle(z.x, z.y, 121, 211);
            if (z.zombieAlive) {
                z.move();

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
                // -- Grenade hits zombie --
                if (grenadeRect.intersects(z1Rect) && grenade.isActive()) {
                    grenade.setTriggerExplosion(true);

                    z.zombieAlive = false;
                    z.dx = 0;
                    grenade.display = false; // check if better way
                    grenade.setActive(false);
                    grenade.setGrenadeInHand(false);
                    score += 1;
                }
                ///  reee
                // -- Bullet hits zombie --
                if (bulletActive && bulletRect.intersects(z1Rect)) {
                    z.zombieAlive = false;
                    z.dx = 0;
                    bulletActive = false;
                    score += 1;
                }
            }
        }
            ///

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
            Iterator<Grenade> graenadeIterator = grenades.iterator();
            while (graenadeIterator.hasNext()) {
                Grenade g=graenadeIterator.next();
                if (g.active) {
                    Rectangle grenadeBorder = new Rectangle(g.x, g.y, 25, 25);
                    if (player.intersects(grenadeBorder)) {
                        g.active = false;
                        grenadecount++;
                        graenadeIterator.remove();
                    }
                }
            }
            System.out.println("Cy "+ cy+" m1.y: " + m1.y);

            if(GamePanel.onLadder) {
                cy = 0; // cancel gravity
                m1.move(0, cy);
            }
            else{   // include gravity
                m1.move(0, cy);
            }
        }

    public boolean isLevelCompleted() {
        for (ZombieBase z : zombies) {
            if (z.zombieAlive) {
                return false;
            }
        }
        return true;
//        for (ZombieBase z : zombies) {
//            if (z.zombieAlive) {
//                return false;
//            }
//        }
//        return true;
    }


    public boolean isGameOver() {
        if(m1.health <= 0){
            return true;
        }else{
            return false;
        }
    }


    public void reset() {

    }

    protected void actionPerformed(ActionEvent e) {

    }
}
