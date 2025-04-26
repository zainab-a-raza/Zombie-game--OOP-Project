public class Zombies extends GameObject{

    //    int dx=2;
//    int dy=2;
//    public Zombies(int x, int y, String path) {
//        super(x, y, path);
//    }
    int dx = 2;
    boolean isAlive = true;
    boolean justHitPlayer = false;


    public Zombies(int x, int y, String path) {
        super(x, y, path);
    }
}
