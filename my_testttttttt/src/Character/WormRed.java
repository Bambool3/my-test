/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Character;

/**
 *
 * @author anakponggun
 */
public class WormRed {
    public int x, y, wormRize;
    public double health;
    public WormRed(int x, int y, int wormRize, int health) {
        this.x = x;
        this.y = y;
        this.wormRize = wormRize;
        this.health = health;
    }
    public boolean isAlive() {
        return health > 0;
    }
}
