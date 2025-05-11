import javax.swing.*;
import java.awt.*;

public class Warrior extends GameObject{

    private int health=100;

    public int getHealth() {
        return health;
    }

    public void changeHealth(int health){
        this.health += health;
    }
    public void setHealth(int health) {
        this.health = health;  // directly assign
    }

    public Warrior(int x, int y, String path) {
        super(x, y, path);
    }

}
