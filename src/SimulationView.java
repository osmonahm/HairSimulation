import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

public class SimulationView
{
    public static final int WIDTH = 800;
    public static final int HEIGHT = 1200;

    public static void main(String[] args) {
        new SimulationView();
    }

    public SimulationView() {
        EventQueue.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                ex.printStackTrace();
            }

            JFrame frame = new JFrame("Hair Simulation");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(new CirclePane());
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
