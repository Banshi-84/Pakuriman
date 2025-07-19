package main;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GameOverPanel extends JPanel {
    private JLabel title;
    private JLabel scoreLabel;
    private JButton restartButton;

    private PanelManager panelManager;
    private int score;

    public GameOverPanel(PanelManager pm, int score) {
        this.panelManager = pm;
        this.score = score;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        title = new JLabel("Game Over !!");
        title.setFont(new Font("Arial", Font.BOLD, 48));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        scoreLabel = new JLabel("Total score: " + score, SwingConstants.CENTER);
        scoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(Box.createVerticalGlue());
        

        restartButton = new JButton("Restart");
        restartButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        restartButton.addActionListener(new RestartButtonListener());

        add(Box.createVerticalGlue());
        add(title);
        add(Box.createRigidArea(new Dimension(0, 20)));
        add(scoreLabel);
        add(Box.createRigidArea(new Dimension(0, 20)));
        add(restartButton);
        add(Box.createVerticalGlue());
    }


    class RestartButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            panelManager.showGameMenuPanel();
        }
    }
}
