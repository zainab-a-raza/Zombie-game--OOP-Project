import javax.swing.*;
import java.awt.*;

public class Frame {
    public static void main(String[] args) {

        GamePanel lvl1 = new Level_1();
        JFrame myFrame=  new JFrame();
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setVisible(true);
        myFrame.add(lvl1);
        //myFrame.getContentPane().setBackground(Color.CYAN);
        myFrame.setSize(1500,900);

//        if(lvl1.getGameOver()==2){
//            GamePanel lvl2 = new Level_2();
//            JFrame myFrame2=  new JFrame();
//            myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//            myFrame.setVisible(true);
//            myFrame.add(lvl2);
//            //myFrame.getContentPane().setBackground(Color.CYAN);
//            myFrame.setSize(1500,900);
//        }
    }

}
