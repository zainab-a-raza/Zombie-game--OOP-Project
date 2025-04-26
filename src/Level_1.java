import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Objects;

public class Level_1 extends GamePanel {
    Level_1() {
        int frameWidth = 1500;
        int platformWidth = 699;

        // Top platforms
        plat1 = new Platform(0, 200, "./longPlatform.png");                               // Top left
        plat2 = new Platform(frameWidth - platformWidth, 200, "./longPlatform.png");      // Top right

        // Middle platform
        plat3 = new Platform((frameWidth / 2) - (platformWidth / 2), 500, "./longPlatform.png");

        // Bottom platforms
        plat4 = new Platform(0, 700, "./longPlatform.png");                               // Bottom left
        plat5 = new Platform(frameWidth - platformWidth, 700, "./longPlatform.png");      // Bottom right

        // Background
        bg = new ImageIcon(Objects.requireNonNull(getClass().getResource("./bg2.png"))).getImage();

        // Add platforms
        platforms.add(plat1);
        platforms.add(plat2);
        platforms.add(plat3);
        platforms.add(plat4);
        platforms.add(plat5);

    }
    @Override
    public void actionPerformed(ActionEvent e){
        super.actionPerformed(e);
        Rectangle player = new Rectangle(m1.x, m1.y, 100, 136);
        int cy = 0;
        for (Platform p : platforms) {
            Rectangle plat = new Rectangle(p.x, p.y, p.img.getWidth(null), 61);  // Use 61 (your platform height)
            if (!player.intersects(plat)) {
                cy = 15; // fall down
            } else {
                cy = 0;  // standing on platform
                break;
            }
        }

        m1.move(0, cy);
        repaint();

    }
}
