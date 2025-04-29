public class JumpingZombie extends ZombieBase {

    double dy = 0;
    double gravity = 0.5;
    double jumpForce = -10;

    public JumpingZombie(int x, int y, String path) {
        super(x, y, path);
        dx = 5;
    }

    @Override
    void move() {
        x += dx;
        dy += gravity;
        y += dy;

        if (x <= 100 || x >= 1400) {
            dx *= -1;
        }

        if (onGround()) {
            jump();
        }
    }

    private void jump() {
        dy = jumpForce;
    }

    private boolean onGround() {
        return y >= 600;
    }

    @Override
    void attack() {
    }

    @Override
    boolean isAlive() {
        return zombieAlive;
    }
}
