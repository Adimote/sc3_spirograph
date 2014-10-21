import javax.swing.*;
import java.awt.*;

/**
 * Main class
 *
 * Created by Andy on 17/10/2014.
 */
public class Main {
    // Class Variables
    private static JFrame frame;

    public static void startCanvas() {
        frame.add(new SpirographCanvas());
    }

    public static void showGUI() {
        //Create and set up the window.
        frame = new JFrame("Spirograph Generator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.getContentPane().setPreferredSize(new Dimension(700, 700));

        //Display the window
        frame.pack();
        frame.setVisible(true);
    }

    public static void start() {
        showGUI();
        startCanvas();
    }

    public static void main(String[] args) {
//        javax.swing.SwingUtilities.invokeLater(() -> start());
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                start();
            }
        });

    }
}
