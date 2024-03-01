package game;

import Character.*;
import Weapon.*;
import Event.Event;
import Optionalitem.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

public class Game extends JPanel implements MouseListener {
    Worm wormred = new Worm(120, 400, 100, 120, 0);
    Worm wormyellow = new Worm(780, 400, 100, 120, 1);
    Weapon redweapon, yellowweapon;
    ChargeBar chargeBarRed, chargeBarYellow; 
    Optionalitems[] optionalItemsRed, optionalItemsYellow;
    static Display display;
    private boolean startWeaponred = false;
    private boolean startWeaponyellow = false;
    private boolean redclick = false;
    private boolean yellowclick = false;
    private int currentPlayer;
    private Random random;
    private int wind, direction;
    Timer timer;
    
    public Game() {
        this.setBounds(0, 0, 1000, 600);
        this.setFocusable(true);
        this.setLayout(null);
        this.addMouseListener(this);
        this.OptionalItems();
        this.random = new Random();
    }
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        drawHealthRed(g2, wormred);
        drawHealthYellow(g2, wormyellow);
        drawWind(g2);
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        drawBackground(g2);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.drawImage(wormred.getImage("/image/Red_Idle.png"), wormred.x, wormred.y, wormred.wormSizeX, wormred.wormSizeY, null);
        g2.drawImage(wormyellow.getImage("/image/yellow_Idle.png"), wormyellow.x, wormyellow.y, wormyellow.wormSizeX, wormyellow.wormSizeY, null);
//        if (!redclick)
//            g2.drawImage(wormred.getImage("/image/Red_Idle.png"), wormred.x, wormred.y, wormred.wormSizeX, wormred.wormSizeY, null);
//        if (!yellowclick)
//            g2.drawImage(wormyellow.getImage("/image/yellow_Idle.png"), wormyellow.x, wormyellow.y, wormyellow.wormSizeX, wormyellow.wormSizeY, null);    
        if (redweapon != null) {
//            if (startWeaponred) 
//                g2.drawImage(wormred.getImage("/image/Red_Charge.png"), wormred.x, wormred.y, wormred.wormSizeX, wormred.wormSizeY, null);
//            else
//                g2.drawImage(wormred.getImage("/image/Red_Atk.png"), wormred.x, wormred.y, wormred.wormSizeX, wormred.wormSizeY, null);
            double x = redweapon.x + redweapon.calculateHorizontalDistance();
            double y = redweapon.y - redweapon.calculateVerticalDistance();
            chargeBarRed.paint(g2, 125, 340, 130, 130);
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
            
//            if (startWeaponyellow) 
//                g2.drawImage(wormyellow.getImage("/image/Yellow_Charge.png"), wormyellow.x, wormyellow.y, wormyellow.wormSizeX, wormyellow.wormSizeY, null);
//            else
//                g2.drawImage(wormyellow.getImage("/image/Yellow_Atk.png"), wormyellow.x, wormyellow.y, wormyellow.wormSizeX, wormyellow.wormSizeY, null);     
            double x = yellowweapon.x - yellowweapon.calculateHorizontalDistance();
            double y = yellowweapon.y - yellowweapon.calculateVerticalDistance();
            chargeBarYellow.paint(g2, 775, 340, 130, 130);
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
            //redclick = true;
            redweapon = new Weapon(wormred.x, wormred.y+10, 75);
            chargeBarRed = new ChargeBar();
            startWeaponred = true;
            startWeaponyellow = false;
            startCharging(chargeBarRed, e);
        }

        if (currentPlayer == 1 && !startWeaponyellow && x >= wormyellow.x && x <= wormyellow.x + wormyellow.wormSizeX && y >= wormyellow.y && y <= wormyellow.y + wormyellow.wormSizeY) {
            //yellowclick = true;
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
                if (Event.checkDirection(wormred, direction)) 
                    redweapon.startAnimation(this, chargeBarRed.getValue() - wind);
                else
                    redweapon.startAnimation(this, chargeBarRed.getValue() + wind);
                //redclick = false;
                startWeaponred = false;
                chargeBarRed.setValue(0);
                chargeBarRed.setShow(false);
            }
            if (currentPlayer == 1) {
                if (Event.checkDirection(wormyellow, direction)) 
                    yellowweapon.startAnimation(this, chargeBarYellow.getValue() - wind);
                else
                    yellowweapon.startAnimation(this, chargeBarYellow.getValue() + wind);
                //yellowclick = false;
                startWeaponyellow = false;
                chargeBarYellow.setValue(0);
                chargeBarYellow.setShow(false);
            }
            
        }
        currentPlayer = (currentPlayer + 1) % 2;
        // radom when currentPlayer == 1
        if (currentPlayer == 1) {
            randomWindAndDirection();
        }
            for(int i = 0; i < optionalItemsRed.length; i++) {
                optionalItemsRed[i].currentPlayer = currentPlayer;
                optionalItemsYellow[i].currentPlayer = currentPlayer;
                }
    }
    
    private void drawBackground(Graphics g2) {
        Image backgroundImage;
        backgroundImage = new ImageIcon("src/image/Background.png").getImage();
        g2.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
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
        g2.setColor(new Color(50, 205, 50));
        g2.drawLine(430, 85, 450-worm.health, 85);
        g2.setColor(Color.white);
        g2.setStroke(new BasicStroke(6.0f));
        g2.drawRoundRect(90, 75, 350, 20, 20, 20);   
    }

    private void drawHealthYellow(Graphics2D g2, Worm worm) {
        g2.setStroke(new BasicStroke(18.0f));
        g2.setColor(new Color(50, 205, 50));
        g2.drawLine(560, 85, 540+worm.health, 85);
        g2.setColor(Color.white);
        g2.setStroke(new BasicStroke(6.0f));
        g2.drawRoundRect(550, 75, 350, 20, 20, 20);
    }
    
    private void OptionalItems() {
        optionalItemsRed = drawOptionalItems(wormred, 130, 110);
        optionalItemsYellow = drawOptionalItems(wormyellow, 600, 110);
    }
    
    private Optionalitems[] drawOptionalItems(Worm worm, int x, int y) {
        Optionalitems[] optionalItems = new Optionalitems[3];
        optionalItems[0] = new Optionalitems("src/image/heal.png", "heal", x, y, 60, 60, worm);
        optionalItems[1] = new Optionalitems("src/image/damx2.png", "damX2", x + 100, y, 60, 60, worm);
        optionalItems[2] = new Optionalitems("src/image/immortal.png", "immortal", x + 200, y, 60, 60, worm);
        this.add(optionalItems[0]);
        this.add(optionalItems[1]);
        this.add(optionalItems[2]);
        return optionalItems;
    }
    
    // 0 is left and 1 is right
    private void randomWindAndDirection() {
        wind = random.nextInt(21);
        direction = random.nextInt(2);
        System.out.println(wind + " " + direction);
    }
    
    private void drawWind(Graphics2D g2) {
        g2.setColor(Color.white);
        g2.setStroke(new BasicStroke(8.0f));
        g2.drawRoundRect(460, 120, 70, 20, 20, 20);
        g2.setStroke(new BasicStroke(12.0f));
        if (direction == 0) { // If wind is to the left
            g2.setColor(Color.red);
            g2.drawLine(500 - wind, 130, 500, 130); // Draw red wind direction line
        } else { // If wind is to the right
           g2.setColor(Color.GREEN);
           g2.drawLine(500, 130, 500 + wind, 130); // Draw blue wind direction line
        }
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
