package figures;
import java.awt.*;


public class Arc extends Figure {

    public Arc (int x, int y, int w, int h, Color corFundo, Color corContorno){
        super(x, y, w, h, corFundo, corContorno);
    }

    public void paint(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(this.corFundo);
        g2d.drawArc(this.x, this.y, this.w, this.h, 0, 180);
        g2d.setColor(corContorno);
        g2d.fillArc(this.x, this.y, this.w, this.h, 0, 180);
    }

    
}
