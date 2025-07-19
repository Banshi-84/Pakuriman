package strategy;
import java.util.List;

import model.Enemy;

public class DoubleSpeedEnemyStrategy implements EnemyStrategy {

    @Override
    public void apply(List<Enemy> enemies) {
        for (Enemy e : enemies) {
            e.setSpeed(e.getSpeed() * 2);
        }
    }

    @Override
    public String getName() {
        return "Enemy Speed x2";
    }
}
