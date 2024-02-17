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


public class StoryPage extends JPanel {	
    public StoryPage(ActionListener main) {
        this.setBackground(new Color(241, 98, 69));
        this.setBounds(0, 0, 1000, 600);
        this.setFocusable(true);
	this.setLayout(null);
        EleButton howToPlay = new EleButton("how to play", 15, 200, 400, 200, 50);		
        howToPlay.addActionListener(main);
        this.add(howToPlay);	
        EleButton letStart = new EleButton("Let's start", 15, 650, 400, 200, 50);		
        letStart.addActionListener(main);
        this.add(letStart);
    }   
}
