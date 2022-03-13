package figures;

import java.awt.*;

public abstract class Figure{
    public int x,y;
    public int w,h;
    public Color corFundo, corContorno;

    public Figure (int x, int y, int w, int h, Color corFundo, Color corContorno) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.corFundo = corFundo;
        this.corContorno = corContorno;
    }

    public abstract void paint (Graphics g);

}

