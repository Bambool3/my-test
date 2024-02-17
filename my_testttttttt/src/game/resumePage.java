/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;

import Element.EleButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

/**
 *
 * @author anakponggun
 */
public class resumePage extends JPanel {
    public resumePage(ActionListener main) {
        this.setBackground(new Color(241, 98, 69));
        this.setFocusable(true);
	this.setLayout(null);
        EleButton restart = new EleButton("restart", 15, 650, 400, 200, 50);		
        restart.addActionListener(main);
        EleButton quit = new EleButton("quit", 15, 200, 400, 200, 50);		
        quit.addActionListener(main);
        this.add(quit);	
        this.add(restart);
    }  
}
