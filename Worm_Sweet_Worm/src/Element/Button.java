package Element;

import game.ComponentResizer;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Button {
    public static void addButton(JPanel panel, String imagePath, String actionCommand, ActionListener listener, int x, int y) {
        JButton button = new JButton(new ImageIcon(imagePath));
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setActionCommand(actionCommand);
        button.addActionListener(listener);
        panel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                ComponentResizer.resizecomponent(button, x, y, 250, 200, panel.getWidth(), panel.getHeight());
            }
        });
        panel.add(button);
    }
    
    public static void addOptionalitem(JPanel panel, String imagePath, String actionCommand, ActionListener listener, int x, int y) {    
        JButton optionalitem = new JButton(new ImageIcon(imagePath));
        optionalitem.setBorderPainted(false);
        optionalitem.setContentAreaFilled(false);
        optionalitem.setFocusPainted(false);
        optionalitem.setActionCommand(actionCommand);
        optionalitem.addActionListener(listener);
        panel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                ComponentResizer.resizecomponent(optionalitem, x, y, 60, 60, panel.getWidth(), panel.getHeight());
            }
        });
        panel.add(optionalitem);
    }
}
