package game;

import Character.*;
import Weapon.*;
import static Event.Event.checkHit;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Game extends JPanel implements MouseListener {
    Worm wormred = new Worm(150, 300, 100, 120);
    Worm wormyellow = new Worm(750, 300, 100, 120);
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
        g2.drawImage(wormred.getImage("/image/Red_Idle.png"), wormred.x, wormred.y, wormred.wormSizeX, wormred.wormSizeY, null);
        g2.drawImage(wormyellow.getImage("/image/Yellow_Idle.png"), wormyellow.x, wormyellow.y, wormyellow.wormSizeX, wormyellow.wormSizeY, null);   
        drawHealth(g2, wormred);
    } 

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        drawBackground(g2);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if (redweapon != null) {
            double x = redweapon.x + redweapon.calculateHorizontalDistance();
            double y = redweapon.y - redweapon.calculateVerticalDistance();
            chargeBarRed.paint(g2, 150, 260, 100, 100);
            if (checkHit(wormyellow, x, y) && !redweapon.getHit()) {
                wormyellow.health -= 20;
                redweapon.setHit(true);
                System.out.println("Hit!!!!!!");
            }
            g2.drawImage(wormred.getImage("/image/Red_Weapon.png"), (int) x, (int) y, redweapon.size, redweapon.size, null);
        }
        if (yellowweapon != null) {
            double x = yellowweapon.x - yellowweapon.calculateHorizontalDistance();
            double y = yellowweapon.y - yellowweapon.calculateVerticalDistance();
            chargeBarYellow.paint(g2, 750, 260, 100, 100);
            if (checkHit(wormred, x, y) && !yellowweapon.getHit()) {
                wormred.health -= 20;
                yellowweapon.setHit(true);
                System.out.println("Hit!!!!!!");
            }
            g2.drawImage(wormyellow.getImage("/image/Yellow_Weapon.png"), (int) x, (int) y, yellowweapon.size, yellowweapon.size, null);
        }
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();

        if (currentPlayer == 0 && !startWeaponred && x >= wormred.x && x <= wormred.x + wormred.wormSizeX && y >= wormred.y && y <= wormred.y + wormred.wormSizeY) {
            redweapon = new Weapon(wormred.x, wormred.y+25, 75);
            chargeBarRed = new ChargeBar();
            startWeaponred = true;
            startWeaponyellow = false;
            startCharging(chargeBarRed, e);
        }

        if (currentPlayer == 1 && !startWeaponyellow && x >= wormyellow.x && x <= wormyellow.x + wormyellow.wormSizeX && y >= wormyellow.y && y <= wormyellow.y + wormyellow.wormSizeY) {
            yellowweapon = new Weapon(wormyellow.x, wormyellow.y+25, 75);
            chargeBarYellow = new ChargeBar();
            startWeaponyellow = true;
            startWeaponred = false;
            startCharging(chargeBarYellow, e);
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
            if (currentPlayer == 0) {
                redweapon.startAnimation(this, chargeBarRed.getValue());
                startWeaponred = false;
                chargeBarRed.setValue(0);
            }
            if (currentPlayer == 1) {
                yellowweapon.startAnimation(this, chargeBarYellow.getValue());
                startWeaponyellow = false;
                chargeBarYellow.setValue(0);
            }
            
        }
        currentPlayer = (currentPlayer + 1) % 2;
    }
    
    private void drawBackground(Graphics g2) {
        try {
            BufferedImage backgroundImage = ImageIO.read(getClass().getResource("/image/Background.png"));
            g2.drawImage(backgroundImage, 0, 0, 1000, 600, null);
        } catch (IOException ex) {
            ex.printStackTrace(); // Handle or log the exception properly
        }
    }

    private void startCharging(ChargeBar chargebar, MouseEvent e) {
        timer = new Timer(5, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ei) {
                int newValue = chargebar.getValue() + 1; 
                chargebar.setValue(newValue);
                if (newValue >= 100) {
                    mouseReleased(e); // Pass the MouseEvent that initiated the charging
                }
                repaint();
            } 
        });
        timer.start();
    }
    
    private void drawHealth(Graphics2D g2, Worm worm) {
        g2.drawImage(worm.getImage("/image/heart.png"),10,20, 20,20,null);
        g2.setStroke(new BasicStroke(18.0f));
        g2.setColor(new Color(241, 98, 69));
        g2.drawLine(60, 30,60+worm.health,30);
        g2.setColor(Color.white);
        g2.setStroke(new BasicStroke(6.0f));
        g2.drawRect(50,20, 200,20);
    }
    

    // Implementing other MouseListener methods
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}
