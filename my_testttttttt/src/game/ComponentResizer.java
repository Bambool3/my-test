package game;

import java.awt.Component;
import java.awt.Graphics2D;
import javax.swing.JButton;
import javax.swing.JPanel;


public class ComponentResizer {
    public static void resizecomponent(Component component, int x, int y, int width, int height, int parentWidth, int parentHeight) {
        int Width = (int) (width * ((double) parentWidth / 1000));
        int Height = (int) (height * ((double) parentHeight / 600));
        int X = (int) (x * ((double) parentWidth / 1000));
        int Y = (int) (y * ((double) parentHeight / 600));
        
        if (component instanceof JButton) {
            component.setBounds(X, Y, Width, Height);
        }

    }
}
         