/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;

import java.awt.Color;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import javax.swing.ImageIcon;
import javax.swing.JButton;


public class GameStartPage extends JPanel {	
    public GameStartPage(ActionListener main) {
        this.setBackground(new Color(255, 250, 240));
        this.setFocusable(true);
	this.setLayout(null);
        JButton start = new JButton(new ImageIcon("src/image/start.png"));
        start.setBounds(380, 400, 200, 100);
        start.setBorderPainted(false);
        start.setContentAreaFilled(false);
        start.setFocusPainted(false);
        start.setActionCommand("start");
        start.addActionListener(main);
        this.add(start);		
    }
}
