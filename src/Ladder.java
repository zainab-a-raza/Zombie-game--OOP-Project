public class Ladder extends GameObject{
    private int width=71;
    private int height =270;

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Ladder(int x, int y, String path) {
        super(x, y, path);
    }
}
