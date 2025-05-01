
// /// ree
// import java.awt.*;

// public class Bullet extends GameObject implements Weapons {
//     boolean active = true;

//     public Bullet(int x, int y, String path) {
//         super(x, y, path);
//     }

//     @Override
//     public void move(int x) {
//         this.x += x;
//     }

//     @Override
//     public void draw(Graphics g) {
//         g.drawImage(img, x, y, null);
//     }

//     @Override
//     public boolean isActive() {
//         return active;
//     }
// }
// /// ree

// //public class Bullet extends Warrior{
// //    Boolean active;
// //    public Bullet(int x, int y, String path) {
// //        super(x, y, path);  // same path as warrior
// //        active= Boolean.TRUE;
// //    }
// //    void moveBullet(int x, int y){
// //        this.x= x;
// //        this.y = y; // change coordinate
// //
// //        move(x, y);
// //    }
// //}

import java.awt.*;

public class Bullet extends GameObject implements Weapons {
    boolean active = true;

    public Bullet(int x, int y, String path) {
        super(x, y, path);
    }

    @Override
    public void move(int x,int y) {
        this.x += x;
    }

    @Override
    public void move(int x) {

    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(img, x, y, null);
    }

    @Override
    public boolean isActive() {
        return active;
    }
}
