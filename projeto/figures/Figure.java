package figures;

import java.awt.*;
import java.awt.event.MouseEvent;

public abstract class Figure {
    public int x, y;
    public int w = 50;
    public int h = 100;
    public Color corFundo, corContorno;
    public int tamanhoDeMudanca = 10;

    public Figure(int x, int y, Color corFundo, Color corContorno) {
        this.x = x;
        this.y = y;
        this.corFundo = corFundo;
        this.corContorno = corContorno;
    }

    public Figure(int x, int y, int w, int h, Color corFundo, Color corContorno) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.corFundo = corFundo;
        this.corContorno = corContorno;
    }

    public abstract void paint(Graphics g);

    public abstract boolean coordenadasDentro(int coordenadasX, int coordenadasY);

    public abstract int getX();

    public abstract int getY();

    public abstract int getW();

    public abstract int getH();

    public abstract Color getCorFundo();

    public abstract Color getCorContorno();

    public abstract Figure redimensionarX();

    public abstract Figure redimensionarY();

    public abstract Figure redimensionarW();

    public abstract Figure redimensionarH();

    public abstract Figure drag(MouseEvent e);

}
