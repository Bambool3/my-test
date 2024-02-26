package game;

import Character.*;
import Weapon.*;
import Event.Event;
import Optionalitem.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Game extends JPanel implements MouseListener {
    Worm wormred = new Worm(150, 300, 100, 120, 0);
    Worm wormyellow = new Worm(720, 300, 100, 120, 1);
    Weapon redweapon, yellowweapon;
    ChargeBar chargeBarRed, chargeBarYellow; 
    Optionalitems[] optionalItemsRed, optionalItemsYellow;
    static Display display;
    private boolean startWeaponred = false;
    private boolean startWeaponyellow = false;
    private int currentPlayer;
    Timer timer;
    
    public Game() {
        this.setBounds(0, 0, 1000, 600);
        this.setFocusable(true);
        this.setLayout(null);
        this.addMouseListener(this);
        OptionalItems();
    }
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(wormred.getImage("/image/Red_Idle.png"), wormred.x, wormred.y, wormred.wormSizeX, wormred.wormSizeY, null);
        g2.drawImage(wormyellow.getImage("/image/Yellow_Idle.png"), wormyellow.x, wormyellow.y, wormyellow.wormSizeX, wormyellow.wormSizeY, null);   
        drawHealthRed(g2, wormred);
        drawHealthYellow(g2, wormyellow);
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
            if (Event.checkHit(wormyellow, x, y) && !redweapon.getHit()) {
                if (!wormyellow.isImmortal()) 
                    if (wormred.isDamX2())
                        wormyellow.health -= wormred.dam * 2;
                     else
                        wormyellow.health -= wormred.dam;
                wormred.setDamX2(false);
                wormyellow.setImmortal(false);
                redweapon.setHit(true);
                if (!wormyellow.isAlive()) {
                    display.resumePage();
                }
            }
            if (wormred.x != x && wormred.y != y)
                g2.drawImage(wormred.getImage("/image/Red_Weapon.png"), (int) x, (int) y, redweapon.size, redweapon.size, null);
        }
        if (yellowweapon != null) {
            double x = yellowweapon.x - yellowweapon.calculateHorizontalDistance();
            double y = yellowweapon.y - yellowweapon.calculateVerticalDistance();
            chargeBarYellow.paint(g2, 750, 260, 100, 100);
            if (Event.checkHit(wormred, x, y) && !yellowweapon.getHit()) {
                if (!wormred.isImmortal())
                    if (wormyellow.isDamX2())
                        wormred.health -= wormyellow.dam * 2;
                    else
                        wormred.health -= wormyellow.dam;
                wormyellow.setDamX2(false);
                wormred.setImmortal(false);
                yellowweapon.setHit(true);
                if (!wormred.isAlive()) {
                    display.resumePage();
                }
            }
            if (wormyellow.x != x && wormyellow.y != y)
                g2.drawImage(wormyellow.getImage("/image/Yellow_Weapon.png"), (int) x, (int) y, yellowweapon.size, yellowweapon.size, null);
        }
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();

        if (currentPlayer == 0 && !startWeaponred && x >= wormred.x && x <= wormred.x + wormred.wormSizeX && y >= wormred.y && y <= wormred.y + wormred.wormSizeY) {
            redweapon = new Weapon(wormred.x, wormred.y+10, 75);
            chargeBarRed = new ChargeBar();
            startWeaponred = true;
            startWeaponyellow = false;
            startCharging(chargeBarRed, e);
        }

        if (currentPlayer == 1 && !startWeaponyellow && x >= wormyellow.x && x <= wormyellow.x + wormyellow.wormSizeX && y >= wormyellow.y && y <= wormyellow.y + wormyellow.wormSizeY) {
            yellowweapon = new Weapon(wormyellow.x, wormyellow.y+10, 75);
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
        Timer delayTimer = new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentPlayer = (currentPlayer + 1) % 2;
                for(int i = 0; i < optionalItemsRed.length; i++) {
                    optionalItemsRed[i].currentPlayer = currentPlayer;
                    optionalItemsYellow[i].currentPlayer = currentPlayer;
                    }
                }
            });
        delayTimer.setRepeats(false); // Only run once
        delayTimer.start();
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
    
    private void drawHealthRed(Graphics2D g2, Worm worm) {
        g2.setStroke(new BasicStroke(18.0f));
        g2.setColor(new Color(241, 98, 69));
        g2.drawLine(430, 85, 450-worm.health, 85);
        g2.setColor(Color.white);
        g2.setStroke(new BasicStroke(6.0f));
        g2.drawRoundRect(90, 75, 350, 20, 20, 20);   
    }

    private void drawHealthYellow(Graphics2D g2, Worm worm) {
        g2.setStroke(new BasicStroke(18.0f));
        g2.setColor(new Color(241, 98, 69));
        g2.drawLine(560, 85, 540+worm.health, 85);
        g2.setColor(Color.white);
        g2.setStroke(new BasicStroke(6.0f));
        g2.drawRoundRect(550, 75, 350, 20, 20, 20);
    }
    
    private void OptionalItems() {
        optionalItemsRed = drawOptionalItems(wormred, 150, 110);
        optionalItemsYellow = drawOptionalItems(wormyellow, 600, 110);
    }

    private Optionalitems[] drawOptionalItems(Worm worm, int x, int y) {
        Optionalitems[] optionalItems = new Optionalitems[3];
        optionalItems[0] = new Optionalitems("src/image/heart.png", "heal", x, y, 30, 30, worm);
        optionalItems[1] = new Optionalitems("src/image/heart.png", "damX2", x + 100, y, 30, 30, worm);
        optionalItems[2] = new Optionalitems("src/image/heart.png", "immortal", x + 200, y, 30, 30, worm);
        this.add(optionalItems[0]);
        this.add(optionalItems[1]);
        this.add(optionalItems[2]);
        return optionalItems;
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
    
    public static void main(String[] args) {
        display = new Display();
    }

}
