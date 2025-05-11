public class Platform extends GameObject{
    protected int getX;
    protected int getY;
    public Platform(int x, int y, String path) {
        super(x, y, path);
    }

    public int getX() {
        return getX;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return 669;
    }

    public int getHeight() {
        return 61;
    }
}
