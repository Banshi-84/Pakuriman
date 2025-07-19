package model;
import java.awt.*;

public abstract class Dot {
    protected double x, y;
    protected int radius;
    protected Color color = Color.BLACK;
    protected double ang = 0;
    
    public Dot(double x, double y, int radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    public Rectangle getCorners() {
        return new Rectangle((int)(x - radius), (int)(y - radius), radius * 2, radius * 2);
    }
    
    public boolean isInside(Point p) {
        return p.distance(x, y) < radius;
    }

    public void setColor(Color c) {
        this.color = c;
    }

    public void setAng(double ang) {
        this.ang = ang;
    }

    public double getAng() {
        return ang;
    }

    public int getCenterX() {
        return (int)x;
    }

    public int getCenterY() {
        return (int)y;
    }

    public abstract void draw(Graphics g);

}