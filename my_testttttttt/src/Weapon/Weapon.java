
package Weapon;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Weapon {
    public int x, y, size;
    private double maxDistance;
    public double initialVelocity = 70; // Initial velocity
    public double angle = Math.PI / 4; // Angle in radians
    public double gravity = 9.81; // Acceleration due to gravity
    public double time = 0; // Current time
    public boolean check = false;
    private Timer timer;
    public Weapon() {
    }
    public Weapon(int x, int y, int size) {
        this.x = x;
        this.y = y; 
        this.size = size;
        calculateMaxDistance();
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
    public void startAnimation(JPanel page) {
        // Reset time
        timer = new Timer(30, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                time += 0.1;
                page.repaint();
                if (calculateHorizontalDistance() >= maxDistance) {
                    timer.stop(); // Stop the animation when reaching max distance
                    check = true;
                }
            }
        });
        timer.start();
    }
}
