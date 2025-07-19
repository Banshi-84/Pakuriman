package model;
import java.awt.*;
import java.util.Random;

public class BaitDot extends Dot {
    private boolean isEaten = false;
    private long eatenTime = 0;
    private int eatenCount = 0;
    private int respawnDelayMs = 3000;

    private static final int MIN_DELAY = 3000;
    private static final int MAX_DELAY = 5000;
    private static final Random rand = new Random();

    public BaitDot(double x, double y) {
        super(x, y, 5);
        this.color = Color.WHITE;
    }

    public void eat() {
        if (!isEaten) {
            isEaten = true;
            eatenTime = System.currentTimeMillis();
            eatenCount++;
            respawnDelayMs = MIN_DELAY + rand.nextInt(MAX_DELAY - MIN_DELAY + 1);
        }
    }

    public void update() {
        if (isEaten) {
            long now = System.currentTimeMillis();
            if (now - eatenTime >= respawnDelayMs) {
                isEaten = false;
            }
        }
    }

    public boolean isActive() {
        return !isEaten;
    }

    public int getEatenCount() {
        return eatenCount;
    }

    @Override
    public void draw(Graphics g) {
        if (!isEaten) {
            g.setColor(color);
            g.fillOval((int)(x - radius), (int)(y - radius), radius * 2, radius * 2);
        }
    }
}
