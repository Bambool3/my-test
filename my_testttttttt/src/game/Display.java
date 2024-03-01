/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;
import Character.Worm;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.*;

public class Display extends JFrame implements ActionListener {
    private final Dimension size = new Dimension(1000, 600);
    public Display() {
        setting();
    }
   
    private void setting() {
        this.setTitle("Worm Sweet Worm");
        this.setSize(size);
        this.getContentPane().add(new GameStartPage(this));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(280, 100);
        this.setVisible(true);
    }
    
    public void startGame() {
        this.getContentPane().add(new Game());
    }
    
    public void storyPage() {
        this.getContentPane().add(new StoryPage(this));
    }
    
    public void howToPage() {
        this.getContentPane().add(new HowToPage(this));
    }
    
    public void resumePage(Worm worm) {
        removeContent();
	this.getContentPane().add(new resumePage(this, worm));
    }
    
    private void removeContent() {
	this.getContentPane().removeAll();
        this.getContentPane().revalidate();
	this.getContentPane().repaint();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("start")) {
            removeContent();
            storyPage();
        }
        else if (e.getActionCommand().equals("how to play")) {
            removeContent();
            howToPage();
        }
        else if (e.getActionCommand().equals("Let's start") || (e.getActionCommand().equals("restart"))) {
            removeContent();
            startGame();
        }
        else if (e.getActionCommand().equals("quit")) {
            removeContent();
            System.exit(0);
        }
    }
    
}
