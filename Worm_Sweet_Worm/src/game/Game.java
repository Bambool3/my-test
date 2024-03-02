package game;

import Character.*;
import Weapon.*;
import Event.Event;
import Optionalitem.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

public class Game extends JPanel implements MouseListener, ComponentListener {
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 600;
    Worm wormred = new Worm(120, 400, 150, 150, 0);
    Worm wormyellow = new Worm(750, 400, 150, 150, 1);
    Weapon redweapon, yellowweapon;
    ChargeBar chargeBarRed, chargeBarYellow; 
    static Display display;
    private boolean startWeaponred = false;
    private boolean startWeaponyellow = false;
    private int currentPlayer;
    private Random random;
    private int wind, direction;
    private double scaleX, scaleY;
    Optionalitems op = new Optionalitems();
    Timer timer;
    
    public Game() {
        this.setBounds(0,0,WIDTH, HEIGHT);
        this.setFocusable(true);
        this.setLayout(null);
        this.addMouseListener(this);
        this.random = new Random();
        this.addComponentListener(this);
        this.optinalItems();
    }
    
    @Override
        public void componentResized(ComponentEvent e) {
        // Get the new scale factors
            scaleX = (double) getWidth() / WIDTH;
            scaleY = (double) getHeight() / HEIGHT;
        // Set scale for wormred and wormyellow
            setScaleworm(scaleX, scaleY, wormred);
            setScaleworm(scaleX, scaleY, wormyellow);
        // Repaint the panel
        repaint();
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        drawBackground(g2);
        drawHealthRed(g2, wormred);
        drawHealthYellow(g2, wormyellow);
        draw(g2);
        drawWind(g2);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.drawImage(wormred.getImage("/image/Red_Idle.png"), wormred.x, wormred.y, wormred.wormSizeX, wormred.wormSizeY, null);
        g2.drawImage(wormyellow.getImage("/image/Yellow_Idle.png"), wormyellow.x, wormyellow.y, wormyellow.wormSizeX, wormyellow.wormSizeY, null);
        if (redweapon != null) {
            double x = redweapon.x + redweapon.calculateHorizontalDistance();
            double y = redweapon.y - redweapon.calculateVerticalDistance();
            // wormred.wormSizeX change dai
            chargeBarRed.paint(g2, wormred.x, wormred.y - 60, wormred.wormSizeX + 30, wormred.wormSizeX + 30);
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
                    display.resumePage(wormred);
                }
            }
            
            if (wormred.x != x && wormred.y != y)
                g2.drawImage(wormred.getImage("/image/Red_Weapon.png"), (int) x, (int) y, redweapon.size, redweapon.size, null);
        }      
        if (yellowweapon != null) {  
            double x = yellowweapon.x - yellowweapon.calculateHorizontalDistance();
            double y = yellowweapon.y - yellowweapon.calculateVerticalDistance();
            chargeBarYellow.paint(g2, wormyellow.x, wormred.y - 60, wormyellow.wormSizeX + 30, wormyellow.wormSizeX + 30);
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
                    display.resumePage(wormyellow);
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
                if (Event.checkDirection(wormred, direction)) 
                    redweapon.startAnimation(this, chargeBarRed.getValue() - wind);
                else
                    redweapon.startAnimation(this, chargeBarRed.getValue() + wind);
                startWeaponred = false;
                chargeBarRed.setValue(0);
                chargeBarRed.setShow(false);
            }
            if (currentPlayer == 1) {
                if (Event.checkDirection(wormyellow, direction)) 
                    yellowweapon.startAnimation(this, chargeBarYellow.getValue() - wind);
                else
                    yellowweapon.startAnimation(this, chargeBarYellow.getValue() + wind);
                startWeaponyellow = false;
                chargeBarYellow.setValue(0);
                chargeBarYellow.setShow(false);
            }
            
        }
        currentPlayer = (currentPlayer + 1) % 2;
        op.setCurrent(currentPlayer);
        // radom when currentPlayer == 1
        if (currentPlayer == 1) {
            randomWindAndDirection();
        }
    }
        
    private void setScaleworm(double scaleX, double scaleY, Worm worm) {
        worm.x = (int) (worm.x_df * scaleX);
        worm.y = (int) (worm.y_df * scaleY);
        worm.wormSizeX = (int) (worm.sizeX_df * scaleX);
        worm.wormSizeY = (int) (worm.sizeY_df * scaleY);
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
        g2.setStroke(new BasicStroke(scale(18, true)));
        g2.setColor(new Color(50, 205, 50));
        g2.drawLine(scale(430, true), scale(85, false), scale(450-worm.health, true), scale(85, false));
        g2.setColor(Color.white);
        g2.setStroke(new BasicStroke(scale(6, true)));
        g2.drawRoundRect(scale(90, true), scale(75, false), scale(350, true), scale(20, false), scale(20, true), scale(20, true));   
    }

    private void drawHealthYellow(Graphics2D g2, Worm worm) {
        g2.setStroke(new BasicStroke(scale(18, true)));
        g2.setColor(new Color(50, 205, 50));
        g2.drawLine(scale(560, true), scale(85, false), scale(540+worm.health, true), scale(85, false));
        g2.setColor(Color.white);
        g2.setStroke(new BasicStroke(scale(6, true)));
        g2.drawRoundRect(scale(550, true), scale(75, false), scale(350, true), scale(20, false), scale(20, true), scale(20, true));
    }
    
    private void optinalItems() {
        addOpitem(wormred, 130, 110);
        addOpitem(wormyellow, 600, 110);
    }
    
    private void addOpitem(Worm worm, int x, int y) {
        op.addOpitem(this, worm, x, y);
    }
    
    // 0 is left and 1 is right
    private void randomWindAndDirection() {
        wind = random.nextInt(21);
        direction = random.nextInt(2);
    }
    
    private void draw(Graphics2D g2) {
        g2.setColor(Color.WHITE);
        g2.setStroke(new BasicStroke(scale(30, true)));
        g2.drawLine(scale(450, true), scale(85, false), scale(540, true), scale(85, false));
        g2.setStroke(new BasicStroke(scale(18, true)));
        g2.setColor(Color.WHITE);
        g2.drawLine(scale(450, true), scale(85, false), scale(540, true), scale(85, false));
        Image wind = new ImageIcon("src/image/wind.png").getImage();
        g2.drawImage(wind, scale(420, true), scale(8, false), scale(150, true), scale(150, false), null);
        g2.drawImage(wormred.getImage("/image/Red_Atk.png"), scale(430, true), scale(55, false), scale(50, true), scale(50, false), null);
        g2.drawImage(wormred.getImage("/image/Yellow_Atk.png"), scale(523, true), scale(55, false), scale(50, true), scale(50, false), null);
    }
    
    private void drawWind(Graphics2D g2) {
        g2.setColor(new Color(255, 215, 0));
        g2.setStroke(new BasicStroke(scale(20, true)));
        g2.drawRoundRect(scale(470, true), scale(120, false), scale(70, true), scale(10, false), scale(10, true), scale(10, true));
        g2.setColor(Color.WHITE);
        g2.setStroke(new BasicStroke(scale(5, true)));
        g2.drawRoundRect(scale(475, true), scale(120, false), scale(60, true), scale(10, false), scale(10, true), scale(10, true));
        g2.setStroke(new BasicStroke(scale(6, true)));
        if (direction == 0) { // If wind is to the left
            g2.setColor(Color.red);
            g2.drawLine(scale(500 - wind, true), scale(125, false), scale(500, true), scale(125, false)); // Draw red wind direction line
        } else { // If wind is to the right
           g2.setColor(new Color(50, 205, 50));
           g2.drawLine(scale(500, true), scale(125, false), scale(500 + wind, true), scale(125, false)); // Draw blue wind direction line
        }
    }
    
    public int scale(int value, boolean isXAxis) {
        double scaleFactor = isXAxis ? (double) getWidth() / 1000 : (double) getHeight() / 600;
        return (int) (value * scaleFactor);
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

    @Override
    public void componentMoved(ComponentEvent e) {
    }

    @Override
    public void componentShown(ComponentEvent e) {
    }

    @Override
    public void componentHidden(ComponentEvent e) {
    }
    
    public static void main(String[] args) {
        display = new Display();
    }
}
