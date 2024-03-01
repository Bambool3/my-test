/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;

import Element.Button;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;

public class StoryPage extends JPanel {
    private Image backgroundImage;
    public StoryPage(ActionListener main) {
        backgroundImage = new ImageIcon("src/image/Story.png").getImage(); // Change to your image file path
        Button.addButton(this, "src/image/howtoplay.png", "how to play", main, 150, 400);
        Button.addButton(this, "src/image/letstart.png", "Let's start", main, 600, 400);
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
