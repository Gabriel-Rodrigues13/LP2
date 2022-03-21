package figures;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.event.MouseEvent;

public class Ellipse extends Figure {

    public Ellipse(int x, int y, Color corFundo, Color corContorno) {
        super(x, y, corFundo, corContorno);
    }

    public Ellipse(int x, int y, int w, int h, Color corFundo, Color corContorno) {
        super(x, y, w, h, corFundo, corContorno);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getW() {
        return w;
    }

    public int getH() {
        return h;
    }

    public Color getCorFundo() {
        return corFundo;
    }

    public Color getCorContorno() {
        return corContorno;
    }

    public boolean coordenadasDentro(int coordenadasX, int coordenadasY) {
        boolean estaDentroX = coordenadasX >= this.x && coordenadasX <= (this.x + this.w);
        boolean estaDentroY = coordenadasY >= this.y && coordenadasY <= (this.y + this.h);
        return estaDentroX && estaDentroY;
    }

    public Figure redimensionarX() {
        int novoW = this.w - this.tamanhoDeMudanca;
        return new Ellipse(this.x, this.y, novoW, this.h, this.corFundo, this.corContorno);
    }

    public Figure redimensionarY() {
        int novoH = this.h - this.tamanhoDeMudanca;
        return new Ellipse(this.x, this.y, this.w, novoH, this.corFundo, this.corContorno);
    }

    public Figure redimensionarW() {
        int novoW = this.w + this.tamanhoDeMudanca;
        return new Ellipse(this.x, this.y, novoW, this.h, this.corFundo, this.corContorno);
    }

    public Figure redimensionarH() {
        int novoH = this.h + this.tamanhoDeMudanca;
        return new Ellipse(this.x, this.y, this.w, novoH, this.corFundo, this.corContorno);
    }

    public Figure drag(MouseEvent e) {
        int dx = e.getX() - this.x;
        int dy = e.getY() - this.y;
        return new Ellipse(this.x + dx, this.y + dy, this.w, this.h, this.corFundo, this.corContorno);
    }

    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(this.corFundo);
        g2d.fill(new Ellipse2D.Double(this.x, this.y, this.w, this.h));
        g2d.setColor(this.corContorno);
        g2d.draw(new Ellipse2D.Double(this.x, this.y, this.w, this.h));
    }
}