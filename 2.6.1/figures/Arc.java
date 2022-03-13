package figures;
import java.awt.*;


public class Arc extends Figure {

    public Arc (int x, int y, int w, int h, Color corFundo, Color corContorno){
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;

        this.corContorno = corContorno;
        this.corFundo = corFundo;
    }

    public void paint(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(this.corFundo);
        g2d.drawArc(this.x, this.y, this.w, this.h, 0, 180);
        g2d.setColor(corContorno);
        g2d.fillArc(this.x, this.y, this.w, this.h, 0, 180);
    }

    
}
