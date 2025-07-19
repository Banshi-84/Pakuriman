package model;
import java.awt.*;
import java.util.List;
import java.util.Random;

import strategy.*;

public class Pacuriman extends Dot {
    private int speed = 4;
    private int currentDir = Direction.NONE;
    private int nextDir = Direction.NONE;

    private int eatenCount = 0;
    private int totalEatenCount = 0;

    private Strategy currentStrategy = null;
    private EnemyStrategy currentEnemyStrategy = null;

    private boolean isInvertedControlled = false;

    private List<Enemy> enemies;

    private static final Random rand = new Random();

    private static final Strategy[] pacuriStrategies = {
        new DoubleSpeedStrategy(),
        new HalfSpeedStrategy(),
        new InvertControlStrategy()
    };

    private static final EnemyStrategy[] enemyStrategies = {
        new DoubleSpeedEnemyStrategy(),
        new DoubleEnemyStrategy()
    };

    public Pacuriman(double x, double y) {
        super(x, y, 16);
        this.color = Color.YELLOW;
    }

    public void setEnemies(List<Enemy> enemies) {
        this.enemies = enemies;
    }

    public void setNextDirection(int dir) {
        if (isInvertedControlled) {
            switch (dir) {
                case Direction.UP:
                    dir = Direction.DOWN;
                    break;

                case Direction.RIGHT:
                    dir = Direction.LEFT;
                    break;

                case Direction.DOWN:
                    dir = Direction.UP;
                    break;
                case Direction.LEFT:
                    dir = Direction.RIGHT;
                    break;
            }
        }
        this.nextDir = dir;
    }

    public void update(GameMap gmap) {
        if (canTurnCorner(nextDir, gmap)) {
            currentDir = nextDir;
        }

        double tempX = x;
        double tempY = y;

        switch (currentDir) {
            case Direction.UP: tempY -= speed;
                break;

            case Direction.RIGHT: tempX += speed;
                break;

            case Direction.DOWN: tempY += speed;
                break;

            case Direction.LEFT: tempX -= speed;
                break;

            default: return;
        }

        if (canMoveTo(tempX, tempY, gmap)) {
            x = tempX;
            y = tempY;
        }
    }

    public void addEatenCount(int n) {
        eatenCount += n;
        totalEatenCount += n;

        if (totalEatenCount % 50 == 0) {
            eatenCount = 0;
            applyRandomStrategy();
            applyRandomEnemyStrategy();
        }
    }

    public int getEatenCount() {
        return eatenCount;
    }

    public int getTotalEatenCount() {
        return totalEatenCount;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int newSpeed) {
        this.speed = Math.max(1, newSpeed);
    }

    public boolean isInvertedControlled() {
        return isInvertedControlled;
    }

    public void setInvertedControlled(boolean b) {
        this.isInvertedControlled = b;
    }

    public Strategy getCurrentStrategy() {
        return currentStrategy;
    }

    public EnemyStrategy getCurrentEnemyStrategy() {
        return currentEnemyStrategy;
    }

    private void applyRandomStrategy() {
        Strategy selected = pacuriStrategies[rand.nextInt(pacuriStrategies.length)];
        selected.apply(this);
        currentStrategy = selected;
        System.out.println("Pacuriman Strategy: " + selected.getName());
    }

    private void applyRandomEnemyStrategy() {
        if (enemies == null || enemies.isEmpty()) return;
        EnemyStrategy selected = enemyStrategies[rand.nextInt(enemyStrategies.length)];
        selected.apply(enemies);
        currentEnemyStrategy = selected;
        System.out.println("Enemy Strategy: " + selected.getName());
    }

    private boolean canTurnCorner(int dir, GameMap gmap) {
        if (dir == Direction.NONE) return false;

        int centerX = getCenterX();
        int centerY = getCenterY();
        int tileSize = GameMap.TILE_SIZE;
        int tileX = centerX / tileSize;
        int tileY = centerY / tileSize;

        int margin = 6;

        if (dir == Direction.RIGHT || dir == Direction.LEFT) {
            if (Math.abs((centerY % tileSize) - tileSize / 2) < margin) {
                return gmap.isWalkableTile(tileX + (dir == Direction.RIGHT ? 1 : -1), tileY);
            }
        } 
        else {
            if (Math.abs((centerX % tileSize) - tileSize / 2) < margin) {
                return gmap.isWalkableTile(tileX, tileY + (dir == Direction.DOWN ? 1 : -1));
            }
        }
        return false;
    }

    private boolean canMoveTo(double nextX, double nextY, GameMap gmap) {
        int left = (int)(nextX - radius);
        int right = (int)(nextX + radius - 1);
        int top = (int)(nextY - radius);
        int bottom = (int)(nextY + radius - 1);

        return gmap.isWalkable(left, top) &&
               gmap.isWalkable(right, top) &&
               gmap.isWalkable(left, bottom) &&
               gmap.isWalkable(right, bottom);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval((int)(x - radius), (int)(y - radius), radius * 2, radius * 2);
    }
}
