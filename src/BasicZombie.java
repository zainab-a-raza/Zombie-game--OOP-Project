import java.awt.*;

public class BasicZombie extends ZombieBase {

    public BasicZombie(int x, int y, String path) {
        super(x, y, path);
        height = 100;
        width = 87;
    }

    @Override
    public void move() {
        this.x += dx; // basically velocity thing
    }

    @Override
    public void attack() {
      ///  none for basic
    }

    @Override
    public boolean isAlive() {
        return zombieAlive;
    }
}
