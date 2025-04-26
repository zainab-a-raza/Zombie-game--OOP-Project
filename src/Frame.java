import javax.swing.*;
import java.awt.*;

public class Frame {
    public static void main(String[] args) {
        GamePanel panel = new GamePanel();
        JFrame myFrame=  new JFrame();
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setVisible(true);
        myFrame.add(panel);
        //myFrame.getContentPane().setBackground(Color.CYAN);
        myFrame.setSize(1500,900);
    }
}
