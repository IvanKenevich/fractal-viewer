import java.awt.*;
import javax.swing.*;

//Group 9
public class Driver {

    public static void main(String[] args) {
        JFrame fractalViewer = new JFrame("Fractal Viewer");
        fractalViewer.setLayout(new BorderLayout());

        FractalPanel fp = new FractalPanel();
        ButtonPanel bp = new ButtonPanel(fp);

        JSplitPane sp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        FractalMenuBar mb = new FractalMenuBar(fp);


        sp.setLeftComponent(bp);
        fractalViewer.add(fp, BorderLayout.CENTER);
        fractalViewer.add(sp, BorderLayout.NORTH);
        fractalViewer.setJMenuBar(mb);


        fractalViewer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fractalViewer.setSize(500, 500);
        fractalViewer.setMinimumSize(new Dimension(1000, 1000));
        fractalViewer.pack();
        fractalViewer.setVisible(true);
    }

}
