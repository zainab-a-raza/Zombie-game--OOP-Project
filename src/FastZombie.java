import java.awt.*;

public class FastZombie extends ZombieBase {

    public FastZombie(int x, int y, String path) {
        super(x, y, path);
        dx = 5;
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
