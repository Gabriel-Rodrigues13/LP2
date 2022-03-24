package figures;

import java.awt.*;
import java.awt.event.MouseEvent;

public class Arc extends Figure {

    public Arc(int x, int y, Color corFundo, Color corContorno) {
        super(x, y, corFundo, corContorno);
    }

    public Arc(int x, int y, int w, int h, Color corFundo, Color corContorno) {
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

    public Figure reduzirEixoX() {
        int novoW = this.w - this.tamanhoDeMudanca;
        return new Arc(this.x, this.y, novoW, this.h, this.corFundo, this.corContorno);
    }

    public Figure aumentarEixoY() {
        int novoH = this.h - this.tamanhoDeMudanca;
        return new Arc(this.x, this.y, this.w, novoH, this.corFundo, this.corContorno);
    }

    public Figure aumentarEixoX() {
        int novoW = this.w + this.tamanhoDeMudanca;
        return new Arc(this.x, this.y, novoW, this.h, this.corFundo, this.corContorno);
    }

    public Figure reduzirEixoY() {
        int novoH = this.h + this.tamanhoDeMudanca;
        return new Arc(this.x, this.y, this.w, novoH, this.corFundo, this.corContorno);
    }

    public Figure drag(MouseEvent e) {
        int dx = e.getX() - this.x;
        int dy = e.getY() - this.y;
        return new Arc(this.x + dx, this.y + dy, this.w, this.h, this.corFundo, this.corContorno);
    }

    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(this.corFundo);
        g2d.fillArc(this.x, this.y, this.w, this.h, 0, 180);
        g2d.setColor(this.corContorno);
        g2d.drawArc(this.x, this.y, this.w, this.h, 0, 180);

    }

    public void desenharContorno(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.RED);
        g2d.setStroke(new BasicStroke(3));
        g2d.drawArc(this.x - 1, this.y - 1, this.w + 1, this.h + 1, 0, 180);

    }

}
