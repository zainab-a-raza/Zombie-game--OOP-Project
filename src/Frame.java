import javax.swing.*;
public class Frame {
    public static void main(String[] args) {

        GamePanel gamePanel = new GamePanel();

        // lvl1 = new Level_1();
        JFrame myFrame = new JFrame("Zombie Game");

        ImageIcon image = new ImageIcon("src/logo.jpeg");
        myFrame.setIconImage(image.getImage());

        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.add(gamePanel);
        myFrame.setVisible(true);
        myFrame.setSize(1500, 900);
    }
}
