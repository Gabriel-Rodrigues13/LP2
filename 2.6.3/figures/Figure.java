package figures;

import java.awt.*;

public abstract class Figure{
    int x,y;
    int w,h;
    Color corFundo, corContorno;

    public abstract void paint (Graphics g);

}

