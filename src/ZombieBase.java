import java.awt.*;

public abstract class ZombieBase extends GameObject {
    ///  shared variables
    int height;
    int width;

    protected int dx = 12;
    protected boolean zombieAlive = true;
    protected boolean justHitPlayer = false;
    // has x,y,img cuz of GameObj
    ///  cons
    public ZombieBase(int x, int y, String path) {
        super(x, y, path);
    }
    // must-have functions (abstract)
    abstract void move();
    abstract void attack();
    abstract boolean isAlive();
}
