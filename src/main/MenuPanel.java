package main;
import java.awt.*;
import javax.swing.*;

public class MenuPanel extends JPanel {
    private final JLabel total;
    private final JLabel eaten;
    private final JLabel strategy;
    private final JLabel enemyStrategy;

    public MenuPanel() {
        setPreferredSize(new Dimension(200,400));

        total = new JLabel("Total Eaten Dot: ");
        eaten = new JLabel("Eaten Count: ");
        strategy = new JLabel("Strategy: ");
        enemyStrategy = new JLabel("Enemy Strategy: ");

        add(total);
        add(eaten);
        add(strategy);
        add(enemyStrategy);
    }

    public void setTotalEaten(int count) {
        total.setText("Total Eaten Dot: " + count);
    }

    public void setEatenCount(int count) {
        eaten.setText("Eaten Count: " + count);
    }

    public void setStrategy(String strategyName) {
        strategy.setText("Strategy: " + (strategyName != null ? strategyName : "(None)"));
    }

    public void setEnemyStrategy(String enemyStrategyName) {
        enemyStrategy.setText("Enemy Strategy: " + (enemyStrategyName != null ? enemyStrategyName : "(None)"));
    }
}