import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;

class FractalMenuBar extends JMenuBar implements ActionListener {

    FractalPanel fractalPanel;
    JMenu fractals;
    JMenuItem triangle, carpet, koch, circle;

    public FractalMenuBar(FractalPanel fractPanel) {
        fractalPanel = fractPanel;
        fractals = new JMenu("Fractals");

        triangle = new JMenuItem("Sierpinski Triangle");
        carpet = new JMenuItem("Sierpinski Carpet");
        koch = new JMenuItem("Koch Curve");
        circle = new JMenuItem("Inscribed Circles");

        triangle.addActionListener(this);
        carpet.addActionListener(this);
        koch.addActionListener(this);
        circle.addActionListener(this);

        fractals.add(triangle);
        fractals.add(carpet);
        fractals.add(koch);
        fractals.add(circle);

        this.add(fractals);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == triangle)
            fractalPanel.setFractal(1);
        else if (e.getSource() == carpet)
            fractalPanel.setFractal(2);
        else if (e.getSource() == koch)
            fractalPanel.setFractal(3);
        else
            fractalPanel.setFractal(4);
    }

}