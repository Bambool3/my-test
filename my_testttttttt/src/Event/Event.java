package Event;

import Character.*;

public class Event {
    private double wind;
    
    public static boolean checkHit(Worm worm, double x, double y) {
        //System.out.println("Checking hit: Worm at (" + worm.x + ", " + worm.y + ") with coordinates (" + x + ", " + y + ")");
        if (x >= worm.x && x <= worm.x + worm.wormSizeX) {
            if (y >= worm.y && y <= worm.y + worm.wormSizeY) {
                return true;
            }
        }
        return false;
    }
    
    public static boolean checkDirection(Worm worm, int direction) {
        return worm.currentPlayer == direction;
    }
}

