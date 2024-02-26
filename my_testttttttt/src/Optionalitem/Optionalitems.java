
package Optionalitem;


import Character.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Optionalitems extends JButton implements ActionListener {
    private String actionCommand;
    private Worm worm; // Assuming Worm class exists;
    public int currentPlayer;
    public Optionalitems(String image, String op, int x, int y, int width, int height, Worm worm) {
        super(new ImageIcon(image));
        this.actionCommand = op;
        this.worm = worm;
        this.setBounds(x, y, width, height);
        this.setBorderPainted(false);
        this.setContentAreaFilled(false);
        this.setFocusPainted(false);
        this.setActionCommand(actionCommand);
        this.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (worm.currentPlayer == currentPlayer) {
            switch (e.getActionCommand()) {
                case "heal":
                    if (worm.health <=  250) 
                        worm.health += 100;
                    else
                        worm.health = 350; 
                    repaint();
                    break;
                case "damX2":
                    worm.setDamX2(true);
                    break;
                case "immortal":
                    worm.setImmortal(true); // Set worm's immortal state to true
                    break;
                default:
                    break;
                
            }
            this.setVisible(false);
        }
    }
}

