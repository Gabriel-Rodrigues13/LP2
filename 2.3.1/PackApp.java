import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import figures.*;

class PackApp {
    public static void main (String[] args) {
        PaintFrame frame = new PaintFrame();
        frame.setVisible(true);
    }
}

class PaintFrame extends JFrame {

    Rect r1;
    Ellipse e1;
    Arc a1;

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
        //Criando Ellipse
        this.e1 = new Ellipse(150, 250, 200, 50);
        
        //Criando retangulo
        this.r1 = new Rect(400, 100, 300, 250);
        
        //Criando semi-circunferencia
        this.a1 = new Arc(200, 400, 200, 150);
    }

    public void paint (Graphics g) {
        super.paint(g);
        this.e1.paint(g);
        this.r1.paint(g);
        this.a1.paint(g);
    }
}
