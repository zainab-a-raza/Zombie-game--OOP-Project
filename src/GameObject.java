import javax.swing.*;
import java.awt.*;

public class GameObject {
    int x,y;
    Image img;

    public GameObject(int x, int y, String path) {
        img = new ImageIcon(getClass().getResource(path)).getImage();
        this.x = x;
        this.y = y;
    }

    void draw(Graphics g){
        g.drawImage(img,x,y,null);
    }

    void move(int dx, int dy){
        this.x += dx;
        this.y += dy;
    }
}
