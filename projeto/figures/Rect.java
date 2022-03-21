package figures;
import java.awt.*;
import java.awt.event.MouseEvent;
// TODO: 
// 1) Remover tamanho randomico (V)
// 2) Manter quadrado como figura padrao (V)
// 3) Criar figura na posição do mouse (V)
// 4) Adicionar comportamento de mudar tamanho da figura com setas direcionais (V)
// 5) Adicionar comportamento de mudar forma quando aperta s (?)


public class Rect extends Figure {

    public Rect(int x, int y, int w, int h, Color corFundo, Color corContorno){
        super(x, y, w, h, corFundo, corContorno);
    }

    public boolean coordenadasDentro(int coordenadasX, int coordenadasY){
        boolean estaDentroX = coordenadasX >= this.x && coordenadasX <= (this.x + this.w) ;
        boolean estaDentroY = coordenadasY >= this.y && coordenadasY <= (this.y + this.h);
        return estaDentroX && estaDentroY;
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

    public Figure redimensionarX(){
        return new Rect(this.x, this.y, (this.w - 10), this.h, this.corFundo, this.corContorno);

    }
    public Figure redimensionarY(){
        return new Rect(this.x, this.y, this.w, (this.h - 10), this.corFundo, this.corContorno);
    }
    public Figure redimensionarW(){
        return new Rect(this.x, this.y, (this.w + 10), this.h, this.corFundo, this.corContorno);
    }
    public Figure redimensionarH(){
        return new Rect(this.x, this.y, this.w, (this.h + 10), this.corFundo, this.corContorno);
    }
    public Figure drag(MouseEvent e){
        int dx = e.getX() - this.x;
        int dy = e.getY() - this.y;
        return new Rect(this.x + dx,this. y + dy,this.w,this.h, this.corFundo, this.corContorno );
    }
    public void paint(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(this.corFundo);
        g2d.fillRect(this.x, this.y, this.w, this.h);
        g2d.setColor(corContorno);
        g2d.drawRect(this.x, this.y, this.w, this.h);
    }

}
