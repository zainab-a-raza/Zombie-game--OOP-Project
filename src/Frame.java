import javax.swing.*;
import java.awt.*;

public class Frame {
    public static void main(String[] args) {

        GamePanel gamePanel = new GamePanel();

        // lvl1 = new Level_1();
        JFrame myFrame = new JFrame();

        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.add(gamePanel);
        myFrame.setVisible(true);
        myFrame.setSize(1500, 900);
    }
}
