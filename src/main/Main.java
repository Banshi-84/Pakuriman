package main;
import java.awt.*;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Pacuri-Man");
            PanelManager panelManager = new PanelManager();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);
            frame.setMinimumSize(new Dimension(800, 600));
            frame.setContentPane(panelManager);
            frame.setVisible(true);
        });
}

}
