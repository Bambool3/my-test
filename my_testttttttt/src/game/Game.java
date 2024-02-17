
package game;
import Character.*;
import Weapon.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

public class Game extends JPanel {
    WormRed wormred = new WormRed(150, 300, 50, 100);
    WormYellow wormyellow = new WormYellow(800, 300, 50, 100);
    RedWeapon redweapon = new RedWeapon(wormred.x, wormred.y, wormred.wormRize);
    public Game() {
        this.setBounds(0, 0, 1000, 600);
        this.setFocusable(true);
        this.setLayout(null);
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                if (x >= wormred.x && x <= wormred.x + wormred.wormRize && y >= wormred.y && y <= wormred.y + wormred.wormRize) {
                    redweapon.startAnimation(Game.this);    
                }
            }
        });
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.RED);
        g2.drawRect(wormred.x, wormred.y ,wormred.wormRize, wormred.wormRize);
        //g2.drawImage(wormred.getImage(), wormred.x, wormred.y, wormred.wormRedSize, wormred.wormRedSize, null);
        g2.drawRect(wormyellow.x, wormyellow.y, wormyellow.wormRize, wormyellow.wormRize);
    }    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g); 
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        double x = redweapon.x + redweapon.calculateHorizontalDistance();
        double y = redweapon.y - (redweapon.initialVelocity * Math.sin(redweapon.angle) * redweapon.time - 0.5 * redweapon.gravity * redweapon.time * redweapon.time);
        g2d.setColor(Color.BLUE);
        g2d.fill(new Rectangle2D.Double(x, y, 20, 20));
    }
}
