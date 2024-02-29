/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Element;

import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Button {
    public static void addButton(JPanel panel, String imagePath, String actionCommand, ActionListener listener, int x, int y) {
        JButton button = new JButton(new ImageIcon(imagePath));
        button.setBounds(x, y, 250, 200);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setActionCommand(actionCommand);
        button.addActionListener(listener);
        panel.add(button);
    }
}
