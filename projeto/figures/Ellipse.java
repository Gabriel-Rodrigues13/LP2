package figures;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Ellipse extends Figure {

    public Ellipse (int x, int y, int w, int h, Color corFundo, Color corContorno) {
        super(x, y, w, h, corFundo, corContorno);
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }
    
    public int getW(){
        return w;
    }
    
    public int getH(){
        return h;
    }

    public Color getCorFundo(){
        return corFundo;
    }
    
    public Color getCorContorno(){
        return corContorno;
    }

    public boolean coordenadasDentro(int coordenadasX, int coordenadasY){
        boolean estaDentroX = coordenadasX >= this.x && coordenadasX <= (this.x + this.w) ;
        boolean estaDentroY = coordenadasY >= this.y && coordenadasY <= (this.y + this.h);
        return estaDentroX && estaDentroY;
    }
    
    public Figure redimensionarX(){
        return new Ellipse(this.x, this.y, (this.w - 10), this.h, this.corFundo, this.corContorno);

    }
    public Figure redimensionarY(){
        return new Ellipse(this.x, this.y, this.w, (this.h - 10), this.corFundo, this.corContorno);
    }
    public Figure redimensionarW(){
        return new Ellipse(this.x, this.y, (this.w + 10), this.h, this.corFundo, this.corContorno);
    }
    public Figure redimensionarH(){
        return new Ellipse(this.x, this.y, this.w, (this.h + 10), this.corFundo, this.corContorno);
    }


    public void paint (Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(this.corFundo);
        g2d.fill(new Ellipse2D.Double(this.x,this.y,this.w,this.h));
        g2d.setColor(this.corContorno);
        g2d.draw(new Ellipse2D.Double(this.x, this.y, this.w, this.h));
    }
}