/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;

import Element.Button;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.*;
import java.awt.event.ActionListener;

public class StoryPage extends JPanel {

    public StoryPage(ActionListener main) {
        this.setBounds(0, 0, 1000, 600);
        this.setLayout(null);
        Button.addButton(this, "src/image/howtoplay.png", "how to play", main, 150, 400);
        Button.addButton(this, "src/image/letstart.png", "Let's start", main, 600, 400);
    }
}
