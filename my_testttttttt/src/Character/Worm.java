/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Character;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

public class Worm {
    public int x, y, wormSizeX, wormSizeY, health=180;
    public Worm(int x, int y, int wormSizeX, int wormSizeY) {
        this.x = x;
        this.y = y;
        this.wormSizeX = wormSizeX;
        this.wormSizeY = wormSizeY;
    }
    public boolean isAlive() {
        return health > 0;
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
}
