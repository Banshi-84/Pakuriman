package strategy;
import java.util.List;

import model.Enemy;

public interface EnemyStrategy {
    void apply(List<Enemy> enemies);
    String getName();
}
