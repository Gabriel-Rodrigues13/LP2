package figures;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.geom.Ellipse2D;

public class Ellipse {
    int x, y;
    int w, h;
    Color corFundo, corContorno;

    public Ellipse (int x, int y, int w, int h, Color corFundo, Color CorContorno) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.corContorno = CorContorno;
        this.corFundo = corFundo; 
    }

    public void paint (Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(this.corFundo);
        g2d.fill(new Ellipse2D.Double(this.x,this.y,this.w,this.h));
        g2d.setColor(this.corContorno);
        g2d.draw(new Ellipse2D.Double(this.x, this.y, this.w, this.h));
    }
}