package figures;
import java.awt.*;


public class Arc {
    int x, y;
    int w, h;

    public Arc (int x, int y, int w, int h){
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    public void paint(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawArc(this.x, this.y, this.w, this.h, 0, 180);
    }

    
}