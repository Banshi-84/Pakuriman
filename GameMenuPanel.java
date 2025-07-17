import java.awt.*;
import javax.swing.*;

public class GameMenuPanel extends JPanel {
    private final GamePanel gameP;
    private final MenuPanel menuP;

    public GameMenuPanel(PanelManager pm){
        setLayout(new BorderLayout());

        gameP = new GamePanel(this, pm);
        menuP = new MenuPanel();

        gameP.setPreferredSize(new Dimension(400, 400));
        menuP.setPreferredSize(new Dimension(200, 400));

        add(gameP, BorderLayout.CENTER);
        add(menuP, BorderLayout.EAST);
    }

    public void updateMenu() {
        Pacuriman pac = gameP.getPacuriman();

        menuP.setTotalEaten(pac.getTotalEatenCount());
        menuP.setEatenCount(pac.getEatenCount());
        menuP.setStrategy(pac.getCurrentStrategy() != null ? pac.getCurrentStrategy().getName() : null);
        menuP.setEnemyStrategy(pac.getCurrentEnemyStrategy() != null ? pac.getCurrentEnemyStrategy().getName() : null);
    }
}