package figures;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
// TODO: 
// 1) Remover tamanho randomico (V)
// 2) Manter quadrado como figura padrao (V)
// 3) Criar figura na posição do mouse (V)
// 4) Adicionar comportamento de mudar tamanho da figura com setas direcionais (V)
// 5) Adicionar comportamento de mudar forma quando aperta s (?)

public class Rect extends Figure {

    public Rect(int x, int y, Color corFundo, Color corContorno) {
        super(x, y, corFundo, corContorno);
    }

    public Rect(int x, int y, int w, int h, Color corFundo, Color corContorno) {
        super(x, y, w, h, corFundo, corContorno);
    }

    public boolean coordenadasDentro(int coordenadasX, int coordenadasY) {
        boolean estaDentroX = coordenadasX >= this.x && coordenadasX <= (this.x + this.w);
        boolean estaDentroY = coordenadasY >= this.y && coordenadasY <= (this.y + this.h);
        return estaDentroX && estaDentroY;
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

    public Figure drag(MouseEvent e) {
        int dx = e.getX() - this.x;
        int dy = e.getY() - this.y;
        return new Rect(this.x + dx, this.y + dy, this.w, this.h, this.corFundo, this.corContorno);
    }

    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(this.corFundo);
        g2d.fillRect(this.x, this.y, this.w, this.h);
        g2d.setColor(corContorno);
        g2d.drawRect(this.x, this.y, this.w, this.h);

    }

    public void desenharContorno(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.RED);
        g2d.setStroke(new BasicStroke(3));
        g2d.drawRect(this.x - 1, this.y - 1, this.w + 1, this.h + 1);

    }

}
