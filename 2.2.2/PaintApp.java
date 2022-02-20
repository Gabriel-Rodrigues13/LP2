import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.geom.Ellipse2D;

class PaintApp {
    public static void main (String[] args) {
        PaintFrame frame = new PaintFrame();
        frame.setVisible(true);
    }
}

class PaintFrame extends JFrame {
    Elipse e1;
    Elipse e2;
    Elipse e3;

    PaintFrame () {
        this.addWindowListener (
            new WindowAdapter() {
                public void windowClosing (WindowEvent e) {
                    System.exit(0);
                }
            }
        );
        this.setTitle("Painting Figures");
        this.setSize(750, 750);
        this.e1 = new Elipse(100,100, 100,100,(new Color(253, 92, 170)), (new Color(105, 182, 94)));
        this.e2 = new Elipse(300, 400, 250, 250, new Color(137, 92, 52), new Color(0, 0 , 250));
        this.e3 = new Elipse(150, 250, 200, 50, new Color(45, 128, 170), new Color(250, 0, 0));
    }

    public void paint (Graphics g) {
        super.paint(g);
        this.e1.paint(g);
        this.e2.paint(g);
        this.e3.paint(g);
    }
}

class Elipse {
    int x, y;
    int w, h;
    Color corFundo, corContorno;

    Elipse (int x, int y, int w, int h, Color corFundo, Color CorContorno) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.corContorno = CorContorno;
        this.corFundo = corFundo; 
    }

    void paint (Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(this.corFundo);
        g2d.fill(new Ellipse2D.Double(this.x,this.y,this.w,this.h));
        g2d.setColor(this.corContorno);
        g2d.draw(new Ellipse2D.Double(this.x, this.y, this.w, this.h));
    }
}