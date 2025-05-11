import java.awt.*;

public class FastZombie extends ZombieBase {
    public FastZombie(int x, int y, String path) {
        super(x, y, path);
        height = 143;
        width = 125;
        dx = 4;
    }

    @Override
    public void move() {
        this.x += 5*dx; // basically velocity thing
    }

    @Override
    public void attack() {

    }

    @Override
    public boolean isAlive() {
        return zombieAlive;
    }
}
