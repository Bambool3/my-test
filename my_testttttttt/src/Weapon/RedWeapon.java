
package Weapon;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.Timer;

public class RedWeapon {
    public int x, y, size;
    private double maxDistance;
    public double initialVelocity = 70; // Initial velocity
    public double angle = Math.PI / 4; // Angle in radians
    public double gravity = 9.81; // Acceleration due to gravity
    public double time = 0; // Current time
    public boolean check;
    private Timer timer;
    public RedWeapon() {
    }
    public RedWeapon(int x, int y, int size) {
        this.x = x;
        this.y = y; 
        this.size = size;
        calculateMaxDistance();
    }
    public double calculateMaxDistance() {
        maxDistance = Math.pow(initialVelocity, 2) * Math.sin(2 * angle) / gravity;
        return maxDistance;
    }
    public double calculateHorizontalDistance() {
        return initialVelocity * Math.cos(angle) * time;
    }
    public void startAnimation(JPanel page) {
        // Reset time
        timer = new Timer(50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                time += 0.1;
                page.repaint();
                if (calculateHorizontalDistance() >= maxDistance) {
                    timer.stop(); // Stop the animation when reaching max distance
                }
            }
        });
        timer.start();
    }
}
