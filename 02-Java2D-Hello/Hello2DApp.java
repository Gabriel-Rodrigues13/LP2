import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.geom.Ellipse2D;

public class Hello2DApp {
    public static void main (String[] args) {
        Hello2DFrame frame = new Hello2DFrame();
    }
}

class Hello2DFrame extends JFrame {
    public Hello2DFrame () {
        this.addWindowListener (
            new WindowAdapter() {
                public void windowClosing (WindowEvent e) {
                    System.exit(0);
                }
            }
        );
        this.setTitle("Java2D - Hello World!");
        this.setSize(500, 500);
        this.setVisible(true);
    }

    public void paint (Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        int w = getWidth();
        int h = getHeight();
        g2d.setPaint(Color.red);
        g2d.fill(new Ellipse2D.Double(50,50,250,250));
        g2d.setPaint(Color.ORANGE);
        g2d.drawLine(50, 50, 300, 300);
        g2d.drawLine(300, 50, 50, 300);
        GradientPaint brancoPreto = new GradientPaint(50,350,Color.WHITE,200, 450, Color.BLACK);
        g2d.setPaint(brancoPreto);
        g2d.fillRect(50, 350,150, 100);
        
        
    }
}