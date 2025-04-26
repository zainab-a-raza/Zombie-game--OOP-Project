public class Bullet extends Warrior{
    Boolean active;
    public Bullet(int x, int y, String path) {
        super(x, y, path);  // same path as warrior
        active= Boolean.TRUE;
    }
    void moveBullet(int x, int y){
        this.x= x;
        this.y = y; // change coordinate

        move(x, y);
    }
}
