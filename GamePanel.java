import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;


public class GamePanel extends JPanel implements ActionListener, KeyListener {
    private Pacuriman pacuriman;
    private GameMap gmap;
    private Timer timer;
    private boolean isGameOver = false;

    private GameMenuPanel gmpanel;
    private PanelManager panelManager;

    public GamePanel(GameMenuPanel gmpanel, PanelManager panelManager) {
        this.gmpanel = gmpanel;
        this.panelManager = panelManager;

        setFocusable(true);
        requestFocusInWindow();
        addKeyListener(this);

        gmap = new GameMap();

        int tileSize = GameMap.TILE_SIZE;
        int pacX = tileSize + tileSize / 2;
        int pacY = tileSize + tileSize / 2;

        pacuriman = new Pacuriman(pacX, pacY);
        pacuriman.setEnemies(gmap.getEnemies());

        timer = new Timer(33, this);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());

        gmap.draw(g);
        pacuriman.draw(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (isGameOver) return;

        gmap.updateAllBaitDots();
        pacuriman.update(gmap);
        gmap.updateEnemies();
        gmap.eatDotAt(pacuriman.getCenterX(), pacuriman.getCenterY(), pacuriman);

        checkCollisionWithEnemy();
        gmpanel.updateMenu();

        repaint();
    }

    private void checkCollisionWithEnemy() {
        Rectangle pacArea = new Rectangle(pacuriman.getCenterX() - 12, pacuriman.getCenterY() - 12, 24, 24);

        List<Enemy> enemies = gmap.getEnemies();
        for(Enemy enemy : enemies) {
            Rectangle enemyArea = new Rectangle((int)(enemy.getX() - 12), (int)(enemy.getY() - 12), 24, 24);

            if(pacArea.intersects(enemyArea)) {
                isGameOver = true;
                timer.stop();

                panelManager.showGameOverPanel(pacuriman.getTotalEatenCount());
                break;
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (isGameOver) return;

        switch(e.getKeyCode()) {
            case KeyEvent.VK_UP:
                pacuriman.setNextDirection(Direction.UP);
                break;
            
            case KeyEvent.VK_RIGHT:
                pacuriman.setNextDirection(Direction.RIGHT);
                break;

            case KeyEvent.VK_DOWN:
                pacuriman.setNextDirection(Direction.DOWN);
                break;

            case KeyEvent.VK_LEFT:
                pacuriman.setNextDirection(Direction.LEFT);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}

    public GameMap getMap() {
        return gmap;
    }

    public Pacuriman getPacuriman() {
        return pacuriman;
    }

}