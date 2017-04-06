import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

class FractalPanel extends JPanel implements MouseMotionListener, MouseWheelListener {

    private int fractal, complexity, zoom,
            squareSide;
    private double radius;

    boolean firstTimeDragging;

    Point mousePos, formerMousePos,
            pT1, pT2, pT3,
            pS1, pS2, pS3, pS4,
            pK1, pK2,
            pC, pC1, pC2, pC3, pC4;


    public FractalPanel() {
        zoom = 1;
        fractal = 1;
        complexity = 0;
        firstTimeDragging = true;


        mousePos = new Point(0, 0);
        formerMousePos = new Point(0, 0);

        pT1 = new Point((500) - 300, (500) + 300);
        pT2 = new Point((500), (500) - 300);
        pT3 = new Point((500) + 300, (500) + 300);

        pS1 = new Point(200, 200);
        squareSide = 500;

        pK1 = new Point(200, 500);
        pK2 = new Point(800, 500);

        pC = new Point(500, 500);
        radius = 500;


        addMouseMotionListener(this);
        addMouseWheelListener(this);
    }

    public void updatePoint(Point p, int zoom) {
        p.x += (0.1) * (zoom) * (Math.hypot(mousePos.x - p.x, mousePos.y - p.y)) * (Math.cos(Math.atan2(mousePos.y - p.y, mousePos.x - p.x)));
        p.y += (0.1) * (zoom) * (Math.hypot(mousePos.x - p.x, mousePos.y - p.y)) * (Math.sin(Math.atan2(mousePos.y - p.y, mousePos.x - p.x)));
    }

    public void setFractal(int fract) {
        fractal = fract;
        complexity = 0;
        repaint();
    }

    public void setComplexity(int c) {
        complexity = c;
        repaint();
    }

    public int getComplexity() {
        return complexity;
    }


    public void mouseMoved(MouseEvent e) {
        mousePos.x = e.getX();
        mousePos.y = e.getY();
        formerMousePos.x = e.getX();
        formerMousePos.y = e.getY();
    }

    public void mouseDragged(MouseEvent e) {
        if (e.getX() <= pT1.x + 75 && e.getX() >= pT1.x - 75 && e.getY() <= pT1.y + 75 && e.getY() >= pT1.y - 75) {
            pT1.x -= formerMousePos.x - e.getX();
            pT1.y -= formerMousePos.y - e.getY();
        } else if (e.getX() <= pT2.x + 75 && e.getX() >= pT2.x - 75 && e.getY() <= pT2.y + 75 && e.getY() >= pT2.y - 75) {
            pT2.x -= formerMousePos.x - e.getX();
            pT2.y -= formerMousePos.y - e.getY();
        } else if (e.getX() <= pT3.x + 75 && e.getX() >= pT3.x - 75 && e.getY() <= pT3.y + 75 && e.getY() >= pT3.y - 75) {
            pT3.x -= formerMousePos.x - e.getX();
            pT3.y -= formerMousePos.y - e.getY();
        }
        formerMousePos.x = e.getX();
        formerMousePos.y = e.getY();

        firstTimeDragging = false;
        repaint();
    }


    public void mouseWheelMoved(MouseWheelEvent e) {
        if (e.getScrollType() == MouseWheelEvent.WHEEL_UNIT_SCROLL && fractal == 1) {
            zoom = e.getWheelRotation();

            updatePoint(pT1, zoom);
            updatePoint(pT2, zoom);
            updatePoint(pT3, zoom);

            repaint();
        } else if (e.getScrollType() == MouseWheelEvent.WHEEL_UNIT_SCROLL && fractal == 2) {
            zoom = e.getWheelRotation();

            pS2 = new Point(pS1.x + squareSide, pS1.y);
            pS3 = new Point(pS1.x + squareSide, pS1.y + squareSide);
            pS4 = new Point(pS1.x, pS1.y + squareSide);

            updatePoint(pS1, zoom);
            updatePoint(pS2, zoom);
            updatePoint(pS3, zoom);
            updatePoint(pS4, zoom);

            squareSide = pS2.x - pS1.x;

            repaint();
        } else if (e.getScrollType() == MouseWheelEvent.WHEEL_UNIT_SCROLL && fractal == 3) {
            zoom = e.getWheelRotation();

            updatePoint(pK1, zoom);
            updatePoint(pK2, zoom);

            repaint();
        } else if (e.getScrollType() == MouseWheelEvent.WHEEL_UNIT_SCROLL && fractal == 4) {
            zoom = e.getWheelRotation();

            pC1 = new Point(pC.x, (int) (pC.y - radius));
            pC2 = new Point((int) (pC.x + radius), pC.y);
            pC3 = new Point(pC.x, (int) (pC.y + radius));
            pC4 = new Point((int) (pC.x - radius), pC.y);

            updatePoint(pC, zoom);
            updatePoint(pC1, zoom);
            updatePoint(pC2, zoom);
            updatePoint(pC3, zoom);
            updatePoint(pC4, zoom);

            radius = pC.y - pC1.y;

            repaint();
        } else {
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (fractal == 1) {
            Fractals.sierpinskiTriangle(pT1, pT2, pT3, complexity, g);
            if (firstTimeDragging) {
                g.drawString("Try moving the corners of the triangle with your mouse!", 50, 50);
            }
        } else if (fractal == 2) {
            g.setColor(Color.black);
            g.fillRect(pS1.x, pS1.y, squareSide, squareSide);
            Fractals.sierpinskiSquare(pS1, squareSide, complexity, g);
        } else if (fractal == 3) {
            Fractals.koch(pK1, pK2, complexity, g);
        } else if (fractal == 4) {
            Fractals.drawCircle(pC, radius, g, complexity);
            Fractals.circle(pC, radius, complexity, g);
        } else {

        }
        g.setColor(Color.black);
        g.drawString("Order: " + complexity, this.getWidth() - 80, 30);
        ///////////////////////////////NAME TAG/////////////////////////////////
        g.setColor(Color.black);                                              //
        g.fillRect(this.getWidth() - 82, this.getHeight() - 40, 82, 40);             //
        g.setColor(Color.green);                                              //
        g.drawString("Ivan Kenevich", this.getWidth() - 80, this.getHeight() - 27); //
        g.drawString("Chris Medlin", this.getWidth() - 75, this.getHeight() - 7);   //
        ////////////////////////////////////////////////////////////////////////
    }
}
            
            
        
