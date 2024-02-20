
package game;
import Character.*;
import Weapon.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Game extends JPanel implements MouseListener{
    Worm wormred = new Worm(150, 300, 100, 120, 100);
    Worm wormyellow = new Worm(750, 300, 100, 120, 100);
    Weapon redweapon;
    Weapon yellowweapon;
    ChargeBar chargeBarRed;
    ChargeBar chargeBarYellow;
    private boolean startWeaponred = false;
    private boolean startWeaponyellow = false;
    private int currentPlayer = 0;
    Timer timer;
    public Game() {
        this.setBounds(0, 0, 1000, 600);
        this.setFocusable(true);
        this.setLayout(null);
        this.addMouseListener(this);
    }
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.RED);
        //g2.drawRect(wormred.x, wormred.y ,wormred.wormRize, wormred.wormRize);
        g2.drawImage(wormred.getImage("/image/Red_Idle.png"), wormred.x, wormred.y, wormred.wormSizeX, wormred.wormSizeY, null);
        //g2.drawRect(wormyellow.x, wormyellow.y, wormyellow.wormSize, wormyellow.wormSize);
        g2.drawImage(wormyellow.getImage("/image/Yellow_Idle.png"), wormyellow.x, wormyellow.y, wormyellow.wormSizeX, wormyellow.wormSizeY, null);   
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
            chargeBarRed.paint(g2d, 150, 260, 100, 100);
            g2d.drawImage(wormred.getImage("/image/Red_Weapon.png"), (int) xr, (int) yr, redweapon.size, redweapon.size, null);
            //g2d.fill(new Rectangle2D.Double(xr, yr, 20, 20));
        }
        if (yellowweapon != null) {
            double xy = yellowweapon.x - yellowweapon.calculateHorizontalDistance();
            double yy = yellowweapon.y - yellowweapon.calculateVerticalDistance();
            g2d.setColor(Color.YELLOW);
            chargeBarYellow.paint(g2d, 750, 260, 100, 100);
            g2d.drawImage(wormyellow.getImage("/image/Yellow_Weapon.png"), (int) xy, (int) yy, yellowweapon.size, yellowweapon.size, null);
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
        int x = e.getX();
        int y = e.getY();

        if (currentPlayer == 0 && !startWeaponred && x >= wormred.x && x <= wormred.x + wormred.wormSizeX && y >= wormred.y && y <= wormred.y + wormred.wormSizeY) {
            redweapon = new Weapon(wormred.x, wormred.y, 75);
            chargeBarRed = new ChargeBar();
            startWeaponred = true;
            startWeaponyellow = false;
            timer = new Timer(10, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ei) {
                    // Increase the value of Charge_bar
                    int newValue = chargeBarRed.getValue() + 1; // Increase by 1, adjust as needed
                    chargeBarRed.setValue(newValue);                     
                    
                    // Check if value reaches 100, and release the mouse if it does
                    if (newValue >= 100) {
                        mouseReleased(new MouseEvent(Game.this, MouseEvent.MOUSE_RELEASED, System.currentTimeMillis(), 0, e.getX(), e.getY(), 1, false));
                    }
                }
            });
            timer.start();
        }

        if (currentPlayer == 1 && !startWeaponyellow && x >= wormyellow.x && x <= wormyellow.x + wormyellow.wormSizeX && y >= wormyellow.y && y <= wormyellow.y + wormyellow.wormSizeY) {
            yellowweapon = new Weapon(wormyellow.x, wormyellow.y, 75);
            chargeBarYellow = new ChargeBar();
            startWeaponyellow = true;
            startWeaponred = false;
            timer = new Timer(10, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ei) {
                    // Increase the value of Charge_bar
                    int newValue = chargeBarYellow.getValue() + 1; // Increase by 1, adjust as needed
                    chargeBarYellow.setValue(newValue);                     
                    // Check if value reaches 100, and release the mouse if it does
                    if (newValue >= 100) {
                        mouseReleased(new MouseEvent(Game.this, MouseEvent.MOUSE_RELEASED, System.currentTimeMillis(), 0, e.getX(), e.getY(), 1, false));
                    }
                }
            });
            timer.start();
        }
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
        if (!startWeaponred && !startWeaponyellow) {
            // If neither weapon is started, return
            return;
        }
        if (timer != null) {
            timer.stop(); // Stop the timer
            //System.out.println(chargeBar.getValue()); // Print the current value of chargeBar
             // Reset the value to 0
            if (currentPlayer == 0) {
                // Start animation for red weapon   
                redweapon.startAnimation(this, chargeBarRed.getValue());
                startWeaponred = false;
                chargeBarRed.setValue(0);
            }
            if (currentPlayer == 1) {
                // Start animation for yellow weapon   
                yellowweapon.startAnimation(this, chargeBarYellow.getValue());
                startWeaponyellow = false;
                chargeBarYellow.setValue(0);
            }
        }
        currentPlayer = (currentPlayer + 1) % 2;
    }


    @Override
    public void mouseEntered(MouseEvent e) {
        //--
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //--
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
    }
}
