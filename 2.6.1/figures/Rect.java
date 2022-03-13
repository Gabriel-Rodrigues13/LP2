package figures;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Rect extends Figure {

    public Rect(int x, int y, int w, int h, Color corFundo, Color corContorno){
        super(x, y, w, h, corFundo, corContorno);
    }

    public void paint(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(this.corFundo);
        g2d.fillRect(this.x, this.y, this.w, this.h);
        g2d.setColor(corContorno);
        g2d.drawRect(this.x, this.y, this.w, this.h);
    }

}
