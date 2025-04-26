import javax.swing.*;
import java.awt.*;

public class Warrior extends GameObject{

    int vx = 0;
    int vy = 0;
    int health=100;

    public Warrior(int x, int y, String path) {
        super(x, y, path);
    }

}
