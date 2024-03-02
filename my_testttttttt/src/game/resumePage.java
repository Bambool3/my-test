package game;

import Character.Worm;
import Element.Button;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JPanel;


public class resumePage extends JPanel {
    private Image backgroundImage;
    public resumePage(ActionListener main, Worm worm) {
        if (worm.currentPlayer == 0)
            backgroundImage = new ImageIcon("src/image/redwin.png").getImage(); // Change to your image file path
        else
            backgroundImage = new ImageIcon("src/image/yellowwin.png").getImage();
        Button.addButton(this, "src/image/restart.png", "restart", main, 600, 400);
        Button.addButton(this, "src/image/quit.png", "quit", main, 150, 400);
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
