
package Weapon;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Weapon {
    public int x, y, size;
    private double maxDistance;
    public int initialVelocity; // Initial velocity
    public double angle = Math.PI / 4; // Angle in radians
    public double gravity = 9.81; // Acceleration due to gravity
    public double time = 0; // Current time
    private boolean hit=false;
    private Timer timer;
    public Weapon() {
    }
    public Weapon(int x, int y, int size) {
        this.x = x;
        this.y = y; 
        this.size = size;
    }
    public void setHit(boolean hit) {
        this.hit = hit;
    }
    public boolean getHit() {
        return hit;
    }
    private double calculateMaxDistance() {
        maxDistance = Math.pow(initialVelocity, 2) * Math.sin(2 * angle) / gravity;
        return maxDistance;
    }
    public double calculateHorizontalDistance() {
        return initialVelocity * Math.cos(angle) * time;
    }
    public double calculateVerticalDistance() {
        return initialVelocity * Math.sin(angle) * time - 0.5 * gravity * time * time;
    }
    public void startAnimation(JPanel page, int value) {
        initialVelocity = 85;
        // Reset time
        calculateMaxDistance();
        timer = new Timer(1, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                time += 0.1;
                page.repaint();
                // Calculate the vertical distance
                double verticalDistance = calculateVerticalDistance(); 
                // Check if the vertical distance is less than or equal to 0 (reached the ground)
                if (verticalDistance  <= 0) {
                    timer.stop(); // Stop the timer
                    time = 0;
                } 
            }
        });        
        timer.start();        
    }
}