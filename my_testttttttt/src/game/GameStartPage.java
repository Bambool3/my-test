/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GameStartPage extends JPanel {
    private Image backgroundImage;
    
    public GameStartPage(ActionListener main) {
        this.setLayout(null);
        backgroundImage = new ImageIcon("src/image/gamestartpage.png").getImage(); // Change to your image file path
        JButton start = new JButton(new ImageIcon("src/image/start.png"));
        start.setBounds(350, 400, 250, 200);
        start.setBorderPainted(false);
        start.setContentAreaFilled(false);
        start.setFocusPainted(false);
        start.setActionCommand("start");
        start.addActionListener(main);
        this.add(start);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the background image
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}

