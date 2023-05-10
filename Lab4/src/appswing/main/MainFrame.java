package appswing.main;
import javax.swing.*;
import java.awt.*;

/**
 * @author Dawid Szeląg
 *
 * Kompilacja;
 * javac -d appswing/bin -cp .;appswing/lib/jcommon-1.0.23.jar;appswing/lib/jfreechart-1.0.19.jar appswing/models/Graph.java
 * javac -d appswing/bin -cp .;appswing/lib/jcommon-1.0.23.jar;appswing/lib/jfreechart-1.0.19.jar appswing/main/MainPanel.java -encoding UTF8
 * ...
 *
 */

public class MainFrame extends JFrame{
    MainPanel mainPanel;

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainFrame mainFrame = new MainFrame();
            }
        });
    }
    public MainFrame()
    {
        this.setTitle("Machine/Lab4");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        mainPanel = new MainPanel();
        this.add(mainPanel,BorderLayout.CENTER);
        this.pack();
        this.setResizable(false);
        this.setVisible(true);
    }

}
