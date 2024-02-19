/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Character;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

public class WormRed {
    public int x, y, wormSizeX, wormSizeY;
    public double health;
    public WormRed(int x, int y, int wormSizeX, int wormSizeY, int health) {
        this.x = x;
        this.y = y;
        this.wormSizeX = wormSizeX;
        this.wormSizeY = wormSizeY;
        this.health = health;
    }
    public boolean isAlive() {
        return health > 0;
    }
    public BufferedImage getImageCharacter() {
        try {
            BufferedImage Red_Idle = ImageIO.read(getClass().getResource("/image/Red_Idle.png"));
            return Red_Idle;
        } catch (IOException ex) {
            
        }
        return null;
    }
    public BufferedImage getImageWeapon() {
        BufferedImage image = null;
        try {
            // Load image using resource URL
            URL resource = getClass().getResource("/image/Red_Weapon.png");
            if (resource != null) {
                image = ImageIO.read(resource);
            } else {
                System.err.println("Image file not found.");
            }
        } catch (IOException e) {
            
        }
        return image;
    }
}
