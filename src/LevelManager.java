import javax.swing.*;
import java.awt.*;

public class LevelManager {
    protected boolean hasWon = false;
    protected  boolean gamelost= false;
    protected Level currentLevel;
    protected int levelNumber=1;


    LevelManager() {
        loadLevel(levelNumber);
    }

    public void loadLevel(int levelNumber){
        if (levelNumber == 1) {
            currentLevel = new Level_1();
        }
        else if(levelNumber ==2) {currentLevel = new Level_2();}
        else if(levelNumber ==3){
            currentLevel= new Level_3();
        }
        else if(levelNumber >3){
            //currentLevel= null;
            hasWon= true;
        }
    }

    public void draw(Graphics g) {
        if (currentLevel != null && hasWon == false && gamelost== false) {
            currentLevel.draw(g);
        } else if(hasWon && !gamelost){
            g.setColor(Color.BLACK); // Paint background black
            g.fillRect(0, 0, 1500, 900);
            g.setColor(Color.GREEN);
            g.setFont(new Font("Arial", Font.BOLD, 100));
            g.drawString("YOU WON!", 400, 450);
        }
    }



    public Level getCurrentLevel(){
        return currentLevel;
    }

    public void update(){
        if (currentLevel != null) {
            currentLevel.update();
            if (currentLevel.isGameOver()) {
                gamelost=true;
                //if you dont want to reset if lost
//                levelNumber = 1;
//                loadLevel(levelNumber);
            } else if (currentLevel.isLevelCompleted()) {
                levelNumber++;
                loadLevel(levelNumber);
            }
        }
    }
}
