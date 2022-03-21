import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;

import figures.*;
import properties.Colours;

class ProjetoApp {
    public static void main (String[] args) {
        ListFrame frame = new ListFrame();
        frame.setVisible(true);
    }
}


class ListFrame extends JFrame {
    ArrayList<Figure> figs = new ArrayList<Figure>();
    Random rand = new Random();
    Figure focus = null;
    Color corFundo = Colours.cor_fundo_um;
    Color corContorno = Colours.cor_contorno_um;
    int[] posicaoMouse = new int[2]; 

    
    ListFrame () {
        this.addWindowListener (
            new WindowAdapter() {
                public void windowClosing (WindowEvent e) {
                    System.exit(0);
                }
            }
        );


        // Pegando a posição do cursor 
        this.addMouseMotionListener(new MouseAdapter() {
            public void mouseMoved(MouseEvent e){
                posicaoMouse[0] = e.getX();
                posicaoMouse[1] = e.getY();
            }

        // Movendo a figura com o mouse 
            public void mouseDragged(MouseEvent e){
                Figure novaFigura = focus.drag(e);
                figs.remove(focus);
                figs.add(novaFigura);
                focus = novaFigura;
                repaint();
            }
        });

        // Key listeners de mudança de cores

        this.addKeyListener(
            new KeyAdapter(){
                public void keyPressed(KeyEvent evt){
                    switch(evt.getKeyCode()) {
                        case KeyEvent.VK_1:
                            corFundo = Colours.cor_fundo_um;
                            corContorno = Colours.cor_contorno_um;
                            break;
                        case KeyEvent.VK_2:
                            corFundo = Colours.cor_fundo_dois;
                            corContorno = Colours.cor_contorno_dois;
                            break;
                        case KeyEvent.VK_3:
                            corFundo = Colours.cor_fundo_tres;
                            corContorno = Colours.cor_contorno_tres;
                            break;
                        default:
                            break;
                    }
                    repaint();
                }
            }
        );

        // Key listeners de manipulaçao de figuras
        this.addKeyListener (
            new KeyAdapter() {
                public void keyPressed (KeyEvent evt) {
                    int x = posicaoMouse[0];
                    int y = posicaoMouse[1];
                    int w = 50;
                    int h = 100;
                    Rect rect = new Rect(x, y, w, h, corFundo, corContorno);
                    Ellipse elli = new Ellipse(x, y, w, h, corFundo, corContorno);
                    Arc arc = new Arc(x, y, w, h, corFundo, corContorno);

                    if (evt.getKeyCode() == KeyEvent.VK_R) {
                        figs.add(rect);
                    }
                    else if (evt.getKeyCode() == KeyEvent.VK_E) {
                        figs.add(elli);
                    }
                    else if (evt.getKeyCode() == KeyEvent.VK_A) {
                        figs.add(arc);
                    }       
                    else if( evt.getKeyCode() == KeyEvent.VK_BACK_SPACE){
                        figs.remove(focus);
                        focus = null;;
                    }
                    else if( evt.getKeyCode() == KeyEvent.VK_DELETE){
                        figs.clear();
                        focus = null;
                    }
                    else if(evt.getKeyCode() == KeyEvent.VK_RIGHT){
                        Figure new_figure = focus.redimensionarW();
                        figs.remove(focus);
                        figs.add(new_figure);
                        focus = new_figure;
                        
                    }
                    else if(evt.getKeyCode() == KeyEvent.VK_LEFT){
                        Figure new_figure = focus.redimensionarX();
                        figs.remove(focus);
                        figs.add(new_figure);
                        focus = new_figure;
                        
                    }
                    else if(evt.getKeyCode() == KeyEvent.VK_UP){
                        Figure new_figure = focus.redimensionarY();
                        figs.remove(focus);
                        figs.add(new_figure);
                        focus = new_figure;
                        
                    }
                    else if(evt.getKeyCode() == KeyEvent.VK_DOWN){
                        Figure new_figure = focus.redimensionarH();
                        figs.remove(focus);
                        figs.add(new_figure);
                        focus = new_figure;
                        
                    }
            
                    repaint();
                }
            }
        );
        
        // Criando foco e z-order
        this.addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent evt){
                focus = null;
                for (int j = 0; j < figs.size(); j++){
                    Figure fig = figs.get(j);
                    if (fig.coordenadasDentro(evt.getX(), evt.getY())){
                        focus = fig; 
                        figs.remove(fig);
                        figs.add(focus);
                        repaint();    
                        break;
                    }else{
                        focus = null;
                        repaint();
                    }
                }
            }
        });

        this.setTitle("Projeto");
        this.setSize(700, 700);
    }

    public void paint (Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        super.paint(g);
        for (Figure fig: this.figs) {
            fig.paint(g);
        }
        if (focus != null){
            // moldura do foco
            if (focus.getCorFundo() != Color.RED && focus.getCorContorno() != Color.RED){
                g2d.setColor(Color.RED);
            }
            g2d.setStroke(new BasicStroke(3));
            g2d.drawRect(focus.getX(),focus.getY(), focus.getW(),focus.getH());
        }
    }
}