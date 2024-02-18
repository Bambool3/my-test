
package game;
import Character.*;
import Weapon.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;

public class Game extends JPanel implements MouseListener{
    WormRed wormred = new WormRed(150, 300, 50, 100);
    WormYellow wormyellow = new WormYellow(800, 300, 50, 100);
    Weapon redweapon;
    Weapon yellowweapon;
    private boolean startWeaponred = false;
    private boolean startWeaponyellow = false;
    public Game() {
        this.setBounds(0, 0, 1000, 600);
        this.setFocusable(true);
        this.setLayout(null);
        this.addMouseListener(Game.this);
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        if (!startWeaponred && x >= wormred.x && x <= wormred.x + wormred.wormRize && y >= wormred.y && y <= wormred.y + wormred.wormRize) {
            redweapon = new Weapon(wormred.x, wormred.y, wormred.wormRize);
            redweapon.startAnimation(this);
            startWeaponred = true;
            startWeaponyellow = false;
        }
        if (!startWeaponyellow && x >= wormyellow.x && x <= wormyellow.x + wormyellow.wormRize && y >= wormyellow.y && y <= wormyellow.y + wormyellow.wormRize) {
            yellowweapon = new Weapon(wormyellow.x, wormyellow.y, wormyellow.wormRize);
            yellowweapon.startAnimation(this);
            startWeaponyellow = true;
            startWeaponred = false;
        }    
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
        if (redweapon != null) {
            double xr = redweapon.x + redweapon.calculateHorizontalDistance();
            double yr = redweapon.y - redweapon.calculateVerticalDistance();
            g2d.setColor(Color.BLUE);
            g2d.fill(new Rectangle2D.Double(xr, yr, 20, 20));
        }
        if (yellowweapon != null) {
            double xy = yellowweapon.x - yellowweapon.calculateHorizontalDistance();
            double yy = yellowweapon.y - yellowweapon.calculateVerticalDistance();
            g2d.setColor(Color.YELLOW);
            g2d.fill(new Rectangle2D.Double(xy, yy, 20, 20));
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //--
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //--
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //--
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //--
    }
}
