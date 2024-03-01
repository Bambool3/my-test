
package game;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import Element.Button;

public class GameStartPage extends JPanel {
    private Image backgroundImage;
    
    public GameStartPage(ActionListener main) {
        backgroundImage = new ImageIcon("src/image/gamestartpage.png").getImage(); // Change to your image file path
        Button.addButton(this, "src/image/start.png", "start", main, 350, 420);
        this.setLayout(null); 
        this.setBounds(0, 0, getWidth(), getHeight());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the background image
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}

