import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class ButtonPanel extends JPanel implements ActionListener {

    JButton upComplexity, downComplexity, reset;
    FractalPanel fractalPanel;

    public ButtonPanel(FractalPanel fractPanel) {
        upComplexity = new JButton("Increase complexity");
        downComplexity = new JButton("Decrease complexity");
        //reset = new JButton("Reset position");
        upComplexity.addActionListener(this);
        downComplexity.addActionListener(this);
        //reset.addActionListener(this);
        fractalPanel = fractPanel;
        this.add(upComplexity);
        this.add(downComplexity);
        //this.add(reset);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == upComplexity)
            fractalPanel.setComplexity(fractalPanel.getComplexity() + 1);
        else
            fractalPanel.setComplexity(fractalPanel.getComplexity() - 1);
    }
}
    
        
    
