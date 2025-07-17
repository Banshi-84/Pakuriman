import java.util.List;

public interface EnemyStrategy {
    void apply(List<Enemy> enemies);
    String getName();
}
