import java.awt.*;
import java.awt.event.*;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Write a description of class Fractals here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Fractals {
    private static int x = 0, y = 1;

    private static void drawTriangle(Point p0, Point p1, Point p2, Graphics g) {
        int[] xs = new int[]{(int) p0.x, (int) p1.x, (int) p2.x};
        int[] ys = new int[]{(int) p0.y, (int) p1.y, (int) p2.y};
        g.setColor(Color.black);
        g.fillPolygon(xs, ys, xs.length);
    }

    public static void fillSquare(Point point, int side, Graphics g) {
        g.setColor(Color.white);
        if (!((point.x + side) > 1000 || (point.y + side) > 1000 || (point.x + side) < 0 || (point.y + side) < 0))
            g.fillRect(point.x + side, point.y + side, side, side);
    }

    public static void drawCircle(Point p0, double radius, Graphics g, int limit) {
        Point point = new Point((int) (p0.x + 0.5), (int) (p0.y + 0.5));
        int radrad = (int) (radius + 0.5);

        g.drawOval(point.x - radrad, point.y - radrad, 2 * radrad, 2 * radrad);
    }

    public static void sierpinskiTriangle(Point p0, Point p1, Point p2, int limit, Graphics g) {
        if (limit > 0) {
            Point pA = new Point((p0.x + p1.x) / 2, (p0.y + p1.y) / 2);
            Point pB = new Point((p1.x + p2.x) / 2, (p1.y + p2.y) / 2);
            Point pC = new Point((p0.x + p2.x) / 2, (p0.y + p2.y) / 2);

            sierpinskiTriangle(pA, p1, pB, limit - 1, g);
            sierpinskiTriangle(p0, pA, pC, limit - 1, g);
            sierpinskiTriangle(pC, pB, p2, limit - 1, g);
        } else
            drawTriangle(p0, p1, p2, g);
    }

    public static void sierpinskiSquare(Point point, int length, int limit, Graphics g) {
        if (limit > 0) {
            Point p1 = new Point(point.x, point.y);
            Point p2 = new Point(p1.x + (length / 3), p1.y);
            Point p3 = new Point(p2.x + (length / 3), p2.y);
            Point p4 = new Point(p3.x, p3.y + (length / 3));
            Point p5 = new Point(p3.x, p4.y + (length / 3));
            Point p6 = new Point(p2.x, p5.y);
            Point p7 = new Point(p1.x, p5.y);
            Point p8 = new Point(p1.x, p4.y);

            fillSquare(point, length / 3, g);

            if (!((p1.x + length / 3) > 1000 || (p1.y + length / 3) > 1000 || (p1.x + length / 3) < 0 || (p1.y + length / 3) < 0))
                sierpinskiSquare(p1, length / 3, limit - 1, g);
            if (!((p2.x + length / 3) > 1000 || (p2.y + length / 3) > 1000 || (p2.x + length / 3) < 0 || (p2.y + length / 3) < 0))
                sierpinskiSquare(p2, length / 3, limit - 1, g);
            if (!((p3.x + length / 3) > 1000 || (p3.y + length / 3) > 1000 || (p3.x + length / 3) < 0 || (p3.y + length / 3) < 0))
                sierpinskiSquare(p3, length / 3, limit - 1, g);
            if (!((p4.x + length / 3) > 1000 || (p4.y + length / 3) > 1000 || (p4.x + length / 3) < 0 || (p4.y + length / 3) < 0))
                sierpinskiSquare(p4, length / 3, limit - 1, g);
            if (!((p5.x + length / 3) > 1000 || (p5.y + length / 3) > 1000 || (p5.x + length / 3) < 0 || (p5.y + length / 3) < 0))
                sierpinskiSquare(p5, length / 3, limit - 1, g);
            if (!((p6.x + length / 3) > 1000 || (p6.y + length / 3) > 1000 || (p6.x + length / 3) < 0 || (p6.y + length / 3) < 0))
                sierpinskiSquare(p6, length / 3, limit - 1, g);
            if (!((p7.x + length / 3) > 1000 || (p7.y + length / 3) > 1000 || (p7.x + length / 3) < 0 || (p7.y + length / 3) < 0))
                sierpinskiSquare(p7, length / 3, limit - 1, g);
            if (!((p8.x + length / 3) > 1000 || (p8.y + length / 3) > 1000 || (p8.x + length / 3) < 0 || (p8.y + length / 3) < 0))
                sierpinskiSquare(p8, length / 3, limit - 1, g);

        }
    }

    public static void koch(Point p0, Point p1, int limit, Graphics g) {
        double dx = p1.x - p0.x;
        double dy = p1.y - p0.y;
        double length = Math.hypot(dx, dy);

        double angle1 = Math.atan2(dy, dx);
        double angle2 = Math.PI / 3;

        Point pA = new Point((int) (p0.x + dx / 3), (int) (p0.y + dy / 3));
        Point pB = new Point((int) (pA.x + (length / 3) * Math.cos(angle1 - angle2)), (int) (pA.y + (length / 3) * Math.sin(angle1 - angle2)));
        Point pC = new Point((int) (p1.x - dx / 3), (int) (p1.y - dy / 3));
        if (limit > 0) {

            koch(p0, pA, limit - 1, g);
            koch(pA, pB, limit - 1, g);
            koch(pB, pC, limit - 1, g);
            koch(pC, p1, limit - 1, g);


        } else {
            g.drawLine(p0.x, p0.y, pA.x, pA.y);
            g.drawLine(pA.x, pA.y, pB.x, pB.y);
            g.drawLine(pB.x, pB.y, pC.x, pC.y);
            g.drawLine(pC.x, pC.y, p1.x, p1.y);
        }
    }


    public static void circle(Point p0, double R, int limit, Graphics g) {
        if (limit > 0) {

            double r = (R / ((2 * Math.sqrt(3) / 3) + 1));

            Point pA = new Point((int) p0.x, (int) (p0.y - (2 * r * Math.cos(Math.PI / 6) * 0.666)));
            Point pB = new Point((int) (p0.x + r), (int) (pA.y + 2 * r * Math.sin(Math.PI / 3)));
            Point pC = new Point((int) (p0.x - r), (int) (pA.y + 2 * r * Math.sin(Math.PI / 3)));


            drawCircle(pA, r, g, limit);
            drawCircle(pB, r, g, limit);
            drawCircle(pC, r, g, limit);


            circle(pA, r, limit - 1, g);
            circle(pB, r, limit - 1, g);
            circle(pC, r, limit - 1, g);


        }
        /*else
        {}*/
    }
}
