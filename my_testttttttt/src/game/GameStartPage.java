/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;

import java.awt.Color;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import Element.EleButton;
import javax.swing.ImageIcon;


public class GameStartPage extends JPanel {
    public long point;		
    public GameStartPage(ActionListener main) {
        this.setBackground(new Color(241, 98, 69));
        this.setBounds(0, 0, 1000, 600);
        this.setFocusable(true);
	this.setLayout(null);
        EleButton start = new EleButton("start", 15, 380, 400, 200, 50);
        start.addActionListener(main);		
        this.add(start);		
    }
}
