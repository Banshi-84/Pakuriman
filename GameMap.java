import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class GameMap {
    private final int[][] mapData = {
        {1,1,1,1,1,1,1,1,1,1},
        {1,0,0,0,0,0,0,0,0,1},
        {1,0,1,1,1,0,1,1,0,1},
        {1,0,1,0,0,0,0,0,0,1},
        {1,0,1,0,1,1,1,1,0,1},
        {1,0,0,0,0,1,0,0,0,1},
        {1,0,1,1,0,0,0,1,0,1},
        {1,0,1,1,0,1,0,1,0,1},
        {1,0,0,0,0,0,0,0,0,1},
        {1,1,1,1,1,1,1,1,1,1}
    };

    public static final int TILE_SIZE = 40;

    private List<BaitDot> dots;
    private List<Enemy> enemies;

    public GameMap() {
        dots = new ArrayList<>();
        enemies = new ArrayList<>();
        loadDots();
        loadEnemies();
    }

    private void loadDots() {
        for (int y = 0; y < mapData.length; y++) {
            for (int x = 0; x < mapData[0].length; x++) {
                if (mapData[y][x] == 0) {
                    double centerX = x * TILE_SIZE + TILE_SIZE / 2.0;
                    double centerY = y * TILE_SIZE + TILE_SIZE / 2.0;
                    dots.add(new BaitDot(centerX, centerY));
                }
            }
        }
    }

    private void loadEnemies() {
        enemies.add(new Enemy(7 * TILE_SIZE + 20, 1 * TILE_SIZE + 20));
    }

    public void draw(Graphics g) {
        for (int y = 0; y < mapData.length; y++) {
            for (int x = 0; x < mapData[0].length; x++) {
                if (mapData[y][x] == 1) {
                    g.setColor(Color.BLUE);
                    g.fillRect(x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
                }
            }
        }

        for (BaitDot dot : dots) {
            dot.draw(g);
        }

        for (Enemy enemy : enemies) {
            enemy.draw(g);
        }
    }

    public void updateAllBaitDots() {
        for (BaitDot dot : dots) {
            dot.update();
        }
    }

    public void updateEnemies() {
        for (Enemy enemy : enemies) {
            enemy.update(this);
        }
    }

    public void eatDotAt(int pixelX, int pixelY, Pacuriman pacuriman) {
        for (BaitDot dot : dots) {
            if (dot.isActive() && dot.getCorners().contains(pixelX, pixelY)) {
                dot.eat();
                pacuriman.addEatenCount(1);
                break;
            }
        }
    }

    public boolean isWalkable(int pixelX, int pixelY) {
        int tileX = pixelX / TILE_SIZE;
        int tileY = pixelY / TILE_SIZE;
        return isWalkableTile(tileX, tileY);
    }

    public boolean isWalkableTile(int tileX, int tileY) {
        if (tileY < 0 || tileY >= mapData.length || tileX < 0 || tileX >= mapData[0].length) return false;
        return mapData[tileY][tileX] != 1;
    }

    public List<BaitDot> getDots() {
        return dots;
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public void addEnemy(Enemy e) {
        enemies.add(e);
    }

    public int getTotalDotCount() {
        return dots.size();
    }
}
