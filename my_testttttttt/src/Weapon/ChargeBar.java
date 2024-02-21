/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Weapon;


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Arc2D;
import javax.swing.JComponent;

public class ChargeBar extends JComponent {
    
    private int guage_size=15;
    private Color color1 = new Color(51, 71, 216);
    private Color color2 = new Color(237, 63, 63);
    private int value;
    private int maximum = 100;

    public int getGuage_size() {
        return guage_size;
    }

    public void setGuage_size(int guage_size) {
        this.guage_size = guage_size;
         repaint();
    }

    public Color getColor1() {
        return color1;
    }

    public void setColor1(Color color1) {
        this.color1 = color1;
         repaint();
    }

    public Color getColor2() {
        return color2;
    }

    public void setColor2(Color color2) {
        this.color2 = color2;
         repaint();
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        if (value < 0)
            value = 0;
        if (value > 100)
            value = 100;
        this.value = value;
        repaint();
        
    }

    public int getMaximum() {
        return maximum;
        
    }

    public void setMaximum(int maximum) {
        this.maximum = maximum;
         repaint();
    }
    
    
    public ChargeBar() {
        
    }
    
    public void paint(Graphics grph, int x, int y, int width, int height) {
        Graphics2D g2 = (Graphics2D) grph;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        float spaceBot = 0.15f;
        height += (int) (height * spaceBot);
        int size = Math.min(width, height) - (guage_size + 5);
        int angleStart = 25;
        g2.setColor(new Color(200, 200, 200));
        g2.setStroke(new BasicStroke(guage_size, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER));
        Shape s = new Arc2D.Double(x, y, size, size, angleStart, 130, Arc2D.OPEN);
        double angle = getAngleOfValues();
        if (angle > 0) {
            s = new Arc2D.Double(x, y, size, size, angleStart + 130 - angle, angle, Arc2D.OPEN);
            GradientPaint gra = new GradientPaint(x, 0, color1, x + width, 0, color2);
            g2.setPaint(gra);
            g2.draw(s);
        }
        repaint();
        g2.setStroke(new BasicStroke(1f));
    }

    
    private double getAngleOfValues() {
        double max = maximum;
        double v = getValueFixed();
        double n = v / max*100f;
        double angle = n*130f/100f;
        return angle;
    }
    
    private int getValueFixed(){
        return value > maximum ? maximum : value;
    }
}
    

