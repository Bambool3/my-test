package game;

import Element.Button;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JPanel;



public class HowToPage extends JPanel {	
    private Image backgroundImage;
    public HowToPage(ActionListener main) {
        backgroundImage = new ImageIcon("src/image/Howtoplaypage.png").getImage(); // Change to your image file path
        Button.addButton(this, "src/image/letstart.png", "Let's start", main, 380, 420);
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
