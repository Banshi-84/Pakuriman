package main;
import java.awt.*;
import javax.swing.*;

public class PanelManager extends JPanel {
    public PanelManager() {
        showGameMenuPanel();
    }

    public void showGameMenuPanel() {
        GameMenuPanel gmp = new GameMenuPanel(this);
        setPanel(gmp);
    }

    public void showGameOverPanel(int score) {
        GameOverPanel gop = new GameOverPanel(this, score);
        setPanel(gop);
    }

    public void setPanel(JPanel panel) {
        this.removeAll();
        this.setLayout(new BorderLayout());
        this.add(panel, BorderLayout.CENTER);
        this.revalidate();
        this.repaint();
    }
}
