package model;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Enemy extends Dot {
    private static final Random rand = new Random();
    private int speed = 4;

    private boolean canMoveHorizontally = true;
    private int direction = 1;

    public Enemy(double x, double y) {
        super(x, y, 16);
        this.color = Color.RED;
    }

    public void update(GameMap map) {
        double tempX = x;
        double tempY = y;

        if (canMoveHorizontally) {
            tempX += speed * direction;
        }
        else {
            tempY += speed * direction;
        }

        if (canMoveTo(tempX, tempY, map)) {
            if (rand.nextDouble() < 0.02) {
                tryOtherAxis(map);
            }
            x = tempX;
            y = tempY;
        }
        else {
            tryOtherAxis(map);
        }
    }

    private void tryOtherAxis(GameMap map) {
        List<String> optionsOfDirections = new ArrayList<>();

        if (canMoveHorizontally) {
            if (canMoveTo(x, y - speed, map)) optionsOfDirections.add("UP");
            if (canMoveTo(x, y + speed, map)) optionsOfDirections.add("DOWN");
        }
        else {
            if (canMoveTo(x - speed, y, map)) optionsOfDirections.add("LEFT");
            if (canMoveTo(x + speed, y, map)) optionsOfDirections.add("RIGHT");
        }

        if (!optionsOfDirections.isEmpty()) {
            String chosenDirection = optionsOfDirections.get(rand.nextInt(optionsOfDirections.size()));
            switch (chosenDirection) {
                case "UP":
                    canMoveHorizontally = false;
                    direction = -1;
                    break;
                case "DOWN":
                    canMoveHorizontally = false;
                    direction = 1;
                    break;
                case "LEFT":
                    canMoveHorizontally = true;
                    direction = -1;
                    break;
                case "RIGHT":
                    canMoveHorizontally = true;
                    direction = 1;
                    break;
            }
        }
        else {
            direction *= -1;
        }
    }

    private boolean canMoveTo(double nextX, double nextY, GameMap map) {
        int left = (int)(nextX - radius);
        int right = (int)(nextX + radius - 1);
        int top = (int)(nextY - radius);
        int bottom = (int)(nextY + radius - 1);

        return map.isWalkable(left, top) &&
               map.isWalkable(right, top) &&
               map.isWalkable(left, bottom) &&
               map.isWalkable(right, bottom);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect((int)(x - radius), (int)(y - radius), radius * 2, radius * 2);
    }

    public void setSpeed(int s) {
        speed = s;
    }

    public int getSpeed() {
        return speed;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
