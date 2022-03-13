import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

import figures.*;

class ListApp {
    public static void main (String[] args) {
        ListFrame frame = new ListFrame();
        frame.setVisible(true);
    }
}

class ListFrame extends JFrame {
    ArrayList<Figure> figs = new ArrayList<Figure>();
    Random rand = new Random();

    ListFrame () {
        this.addWindowListener (
            new WindowAdapter() {
                public void windowClosing (WindowEvent e) {
                    System.exit(0);
                }
            }
        );

        this.addKeyListener (
            new KeyAdapter() {
                public void keyPressed (KeyEvent evt) {
                    int x = rand.nextInt(350);
                    int y = rand.nextInt(350);
                    int w = rand.nextInt(50);
                    int h = rand.nextInt(50);
                    if (evt.getKeyChar() == 'r') {
                        figs.add(new Rect(x,y, w,h,new Color(253, 92, 170), new Color(105, 182, 94)));
                    }
                    if (evt.getKeyChar() == 'e') {
                        figs.add(new Ellipse(x,y, w,h,new Color(45, 128, 170), new Color(250, 0, 0)));
                    }
                    if (evt.getKeyChar() == 'a') {
                        figs.add(new Arc(x,y, w,h,new Color(45, 128, 170), new Color(250, 0, 0)));
                    }
                    repaint();
                }
            }
        );

        this.setTitle("Lista de Figuras");
        this.setSize(350, 350);
    }

    public void paint (Graphics g) {
        super.paint(g);
        for (Figure fig: this.figs) {
            fig.paint(g);
        }
    }
}