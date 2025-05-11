public class JumpingZombie extends ZombieBase {

    private double dy = 0;
    private double gravity = 0.5;
    private double jumpForce = -10;
     int height = 100;
     int width = 99;

    public JumpingZombie(int x, int y, String path) {
        super(x, y, path);
        dx = 5;
        height = 100;
        width = 99;
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
