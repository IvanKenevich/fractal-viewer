import java.awt.event.*;
import javax.swing.*;

class ButtonPanel extends JPanel implements ActionListener {

    private JButton upComplexity, downComplexity;
    private FractalPanel fractalPanel;

    public ButtonPanel(FractalPanel fractPanel) {
        upComplexity = new JButton("Increase complexity");
        downComplexity = new JButton("Decrease complexity");
        upComplexity.addActionListener(this);
        downComplexity.addActionListener(this);
        fractalPanel = fractPanel;
        this.add(upComplexity);
        this.add(downComplexity);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == upComplexity)
            fractalPanel.setComplexity(fractalPanel.getComplexity() + 1);
        else if (e.getSource() == downComplexity)
            fractalPanel.setComplexity(fractalPanel.getComplexity() - 1);
    }
}
    
        
    
