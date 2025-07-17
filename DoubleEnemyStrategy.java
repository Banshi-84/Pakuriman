import java.util.ArrayList;
import java.util.List;

public class DoubleEnemyStrategy implements EnemyStrategy {

    @Override
    public void apply(List<Enemy> enemies) {
        List<Enemy> newEnemies = new ArrayList<>();

        for (Enemy e : enemies) {
            Enemy clone = new Enemy(e.getX(), e.getY());
            clone.setSpeed(e.getSpeed());
            newEnemies.add(clone);
        }

        enemies.addAll(newEnemies);
    }

    @Override
    public String getName() {
        return "Double Enemies";
    }
}
