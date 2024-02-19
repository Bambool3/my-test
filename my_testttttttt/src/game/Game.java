
package game;
import Character.*;
import Weapon.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Game extends JPanel implements MouseListener{
    WormRed wormred = new WormRed(150, 300, 100, 120, 100);
    WormYellow wormyellow = new WormYellow(750, 300, 100, 120, 100);
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
        if (!startWeaponred && x >= wormred.x && x <= wormred.x + wormred.wormSizeX && y >= wormred.y && y <= wormred.y + wormred.wormSizeY) {
            redweapon = new Weapon(wormred.x, wormred.y, 75);
            redweapon.startAnimation(this);
            startWeaponred = true;
            startWeaponyellow = false;
        }
        if (!startWeaponyellow && x >= wormyellow.x && x <= wormyellow.x + wormyellow.wormSizeX && y >= wormyellow.y && y <= wormyellow.y + wormyellow.wormSizeY) {
            yellowweapon = new Weapon(wormyellow.x, wormyellow.y, 75);
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
        //g2.drawRect(wormred.x, wormred.y ,wormred.wormRize, wormred.wormRize);
        g2.drawImage(wormred.getImageCharacter(), wormred.x, wormred.y, wormred.wormSizeX, wormred.wormSizeY, null);
        //g2.drawRect(wormyellow.x, wormyellow.y, wormyellow.wormSize, wormyellow.wormSize);
        g2.drawImage(wormyellow.getImageCharacter(), wormyellow.x, wormyellow.y, wormyellow.wormSizeX, wormyellow.wormSizeY, null);   
    }    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g); 
        Graphics2D g2d = (Graphics2D) g;
        this.drawBackground(g2d);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if (redweapon != null) {
            double xr = redweapon.x + redweapon.calculateHorizontalDistance();
            double yr = redweapon.y - redweapon.calculateVerticalDistance();
            g2d.setColor(Color.BLUE);
            g2d.drawImage(wormred.getImageWeapon(), (int) xr, (int) yr, redweapon.size, redweapon.size, null);
            //g2d.fill(new Rectangle2D.Double(xr, yr, 20, 20));
        }
        if (yellowweapon != null) {
            double xy = yellowweapon.x - yellowweapon.calculateHorizontalDistance();
            double yy = yellowweapon.y - yellowweapon.calculateVerticalDistance();
            g2d.setColor(Color.YELLOW);
            g2d.drawImage(wormyellow.getImageWeapon(), (int) xy, (int) yy, yellowweapon.size, yellowweapon.size, null);
            //g2d.fill(new Rectangle2D.Double(xy, yy, 20, 20));
        }
    }
    private void drawBackground(Graphics g2) {
        try {
            BufferedImage backgroundImage = ImageIO.read(getClass().getResource("/image/Background.png"));
            g2.drawImage(backgroundImage, 0, 0, 1000, 600, null);
        } catch (IOException ex) {
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

    private BufferedImage getImage() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
