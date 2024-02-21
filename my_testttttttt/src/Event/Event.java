/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Event;

import Character.*;

public class Event {
    public static boolean checkHit(Worm worm, double x, double y) {
        //System.out.println("Checking hit: Worm at (" + worm.x + ", " + worm.y + ") with coordinates (" + x + ", " + y + ")");
        if (x >= worm.x && x <= worm.x + worm.wormSizeX) {
            if (y >= worm.y && y <= worm.y + worm.wormSizeY) {
                return true;
            }
        }
        return false;
    }
}

