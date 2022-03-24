package figures;

import java.awt.*;
import java.awt.event.MouseEvent;

public class Rect extends Figure {

    // Construtores da classe
    public Rect(int x, int y, Color[] corFundo, Color corContorno) {
        super(x, y, corFundo, corContorno);
    }

    public Rect(int x, int y, int w, int h, Color[] corFundo, Color corContorno) {
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

    public Color[] getCorFundo() {
        return corFundo;
    }

    public Color getCorContorno() {
        return corContorno;
    }
    // Define se uma um par de coordenadas estão dentro da figura

    public boolean coordenadasDentro(int coordenadasX, int coordenadasY) {
        boolean estaDentroX = coordenadasX >= this.x && coordenadasX <= (this.x + this.w);
        boolean estaDentroY = coordenadasY >= this.y && coordenadasY <= (this.y + this.h);
        return estaDentroX && estaDentroY;
    }

    // Metodos para redimensionar a figura

    public Figure reduzirEixoX() {
        int novoW = this.w - this.tamanhoDeMudanca;
        return new Rect(this.x, this.y, novoW, this.h, this.corFundo, this.corContorno);
    }

    public Figure aumentarEixoY() {
        int novoH = this.h - this.tamanhoDeMudanca;
        return new Rect(this.x, this.y, this.w, novoH, this.corFundo, this.corContorno);
    }

    public Figure aumentarEixoX() {
        int novoW = this.w + this.tamanhoDeMudanca;
        return new Rect(this.x, this.y, novoW, this.h, this.corFundo, this.corContorno);
    }

    public Figure reduzirEixoY() {
        int novoH = this.h + this.tamanhoDeMudanca;
        return new Rect(this.x, this.y, this.w, novoH, this.corFundo, this.corContorno);
    }

    // Método para arrastar a figura

    public Figure drag(MouseEvent e) {
        int dx = e.getX() - this.x;
        int dy = e.getY() - this.y;
        return new Rect(this.x + dx, this.y + dy, this.w, this.h, this.corFundo, this.corContorno);
    }

    // Desenha a figura
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        GradientPaint fundo = new GradientPaint(this.x, this.y, this.corFundo[0], this.x + this.w, this.y + this.h,
                this.corFundo[1]);
        g2d.setPaint(fundo);
        g2d.fillRect(this.x, this.y, this.w, this.h);
        g2d.setColor(corContorno);
        g2d.drawRect(this.x, this.y, this.w, this.h);

    }

    // Desenhar contorno do foco
    public void desenharContorno(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.RED);
        g2d.setStroke(new BasicStroke(3));
        g2d.drawRect(this.x - 1, this.y - 1, this.w + 1, this.h + 1);

    }

}
