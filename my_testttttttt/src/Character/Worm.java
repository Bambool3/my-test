package Character;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Worm {
    public int x, y, wormSizeX, wormSizeY, currentPlayer, health=350, dam=50;
    private boolean immortal, damX2;
    public Worm(int x, int y, int wormSizeX, int wormSizeY, int currentPlayer) {
        this.x = x;
        this.y = y;
        this.wormSizeX = wormSizeX;
        this.wormSizeY = wormSizeY;
        this.currentPlayer = currentPlayer;
    }
    
    public boolean isAlive() {
        return health > 0;
    }
    
    public void setImmortal(boolean immortal) {
        this.immortal = immortal;
    }
    
    public boolean isImmortal() {
        return immortal;
    }
    
    public void setDamX2(boolean damX2) {
        this.damX2 = damX2;
    }
    
    public boolean isDamX2() {
        return damX2;
    }
    
    public void setScale(int x, int y, int sizeX, int sizeY) {
        this.x = x;
        this.y = y;
        this.wormSizeX = sizeX;
        this.wormSizeY = sizeY;
    }
    
    public BufferedImage getImage(String path) {
        BufferedImage image = null;
        try {
            // Load image using resource URL
            URL resource = getClass().getResource(path);
            if (resource != null) {
                image = ImageIO.read(resource);
            } else {
                System.err.println("Image file not found.");
            }
        } catch (IOException e) {
            
        }
        return image;
    }
    public ImageIcon getGif(String filePath) {
        return new ImageIcon(getClass().getResource(filePath));
    }
}
