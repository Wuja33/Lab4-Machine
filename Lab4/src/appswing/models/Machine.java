package appswing.models;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;

public class Machine extends JComponent {
    double x;
    double y;

    public void draw(Graphics g, int l1, int l2, int h, int d, int s)
    {
        Graphics2D g2d = (Graphics2D) g;

        //PRZEGUBY
        Rectangle rectanglel2 = new Rectangle((int)Math.sqrt(Math.pow(Math.abs(h-l1+s),2)+Math.pow(d,2)),-5,20,10);
        Rectangle rectanglel1 = new Rectangle(-5,-10,10,20);

        //Poprawa wyglądu
        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.addRenderingHints(rh);

        //Zapisanie początkowego położenia
        AffineTransform start = g2d.getTransform();
        AffineTransform helpDraw;

        //ustalanie punktu (0,0)
        int x0 = g2d.getClipBounds().width/2;
        int y0 = g2d.getClipBounds().height/2;

        //Przenieś osie na środek ekranu
        g2d.translate(x0,y0);


        //Odwróć oś OY
        g2d.scale(1,-1);

        //rysowanie przegubu L1
        if (l1!=0)
            g2d.draw(rectanglel1);

        g2d.translate(0,-s);

        //rysowanie l1
        g2d.drawLine(0,0,0,l1);

        helpDraw = g2d.getTransform();

        //Przenieś oś i obróć
        g2d.translate(0,l1);
        //obliczenie kątu
        double alfa = Math.atan2(h - l1 + s, d);

        y = (Math.sin(alfa) * l2) + l1 - s;
        x = Math.cos(alfa) * l2;

        g2d.rotate(alfa); //obrócenie osi
        //Rysowanie przegubu L2
        if (l2!=0)
            g2d.draw(rectanglel2);
        g2d.setTransform(helpDraw);
        g2d.translate(0,l1);
        g2d.rotate(alfa); //obrócenie osi
        //Rysowanie L2
        g2d.drawLine(0,0,l2,0);

        //Rysowanie punktu P oraz tekstu
        Ellipse2D circle = new Ellipse2D.Double(-3,-3,6,6);

        g2d.translate(l2,0);
        g2d.rotate(-alfa); //obrócenie osi

        g2d.scale(1,-1);
        if (l2!=0) {
            g2d.drawString("P", -3, 20);
            g2d.fill(circle);
        }

        //powrót do początkowego stanu
        g2d.transform(start);
    }

    public double getX2() {
        return x;
    }

    public double getY2() {
        return y;
    }
}
