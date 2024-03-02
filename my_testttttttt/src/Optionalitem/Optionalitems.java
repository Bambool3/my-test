package Optionalitem;

import Element.Button;
import Character.Worm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Optionalitems {
    private int currentPlayer;

    public Optionalitems() {
    }
    
    public void setCurrent(int currentPlayer) {
        this.currentPlayer = currentPlayer;
    }
    
    public void addOpitem(JPanel panel, Worm worm, int x, int y) {
        ActionListener optionalItemListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (worm.currentPlayer == currentPlayer) {
                    switch (e.getActionCommand()) {
                        case "heal":
                            if (worm.health <= 250) {
                                worm.health += 100;
                            } else {
                                worm.health = 350;
                            }
                            panel.repaint();
                            break;
                        case "damX2":
                            worm.setDamX2(true);
                            break;
                        case "immortal":
                            worm.setImmortal(true);
                            break;
                        default:
                            break;
                    }
                    panel.remove((JButton) e.getSource());
                    panel.repaint();
                }
            }
        };
        Button.addOptionalitem(panel, "src/image/heal.png", "heal", optionalItemListener, x, y);
        Button.addOptionalitem(panel, "src/image/damx2.png", "damX2", optionalItemListener, x + 100, y);
        Button.addOptionalitem(panel, "src/image/immortal.png", "immortal", optionalItemListener, x + 200, y);
    }
}


