 import javax.swing.*;
 import java.awt.*;

 public class Grenade extends GameObject implements Weapons{
     private boolean active = false;   // only for showing stuff
     private boolean grenadeInHand = false;
     private boolean triggerExplosion = false;
     private int explosionTimer = 0;
     private Rectangle explosionRect = new Rectangle(x, y, 399, 300);
     private Image explosionImg;
     boolean display = true;

     public Grenade(int x, int y, String path) {
         super(x, y, path);
         explosionImg = new ImageIcon(getClass().getResource("/explosion.png")).getImage();
     }
    public boolean getActive(){
         return active;
    }
     public void setTriggerExplosion(boolean triggerExplosion) {
         this.triggerExplosion = triggerExplosion;
         if (triggerExplosion) {
             explosionTimer = 30;
         }
     }

     public boolean isTriggerExplosion() {
         return triggerExplosion;
     }

     public boolean isGrenadeInHand() {
         return grenadeInHand;
     }

     public void setActive(boolean active) {
         this.active = active;
     }

     public void setGrenadeInHand(boolean grenadeInHand) {
         this.grenadeInHand = grenadeInHand;
     }

     @Override
     public void move(int x) {
         this.x += x;
     }

     @Override
     public void draw(Graphics g) {
         if (triggerExplosion) {
             drawExplosion(g);
         } else {
             g.drawImage(img, x, y-10, null); // normal grenade
         }
     }


     public void drawExplosion(Graphics g) {
         g.drawImage(explosionImg, x, y, null);
     }

     public void updateExplosion() {
         if (triggerExplosion) {
             explosionTimer--;
             if (explosionTimer <= 0) {
                 triggerExplosion = false;
                 active = false;
             }
         }
     }

     @Override
     public boolean isActive() {
         return active;
     }
 }

