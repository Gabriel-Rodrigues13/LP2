import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;

import figures.*;

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
    Color corFundo = new Color(35, 40, 219);
    Color corContorno = new Color(235,176,38);
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
        });

        // Key listeners de mudança de cores

        this.addKeyListener(
            new KeyAdapter(){
                public void keyPressed(KeyEvent evt){
                    if (evt.getKeyCode() == KeyEvent.VK_1){
                        corFundo = new Color(35, 40, 219);
                        corContorno = new Color(235,176,38);
                    }
                    else if (evt.getKeyCode() == KeyEvent.VK_2){
                        corFundo = new Color(21, 219, 54);
                        corContorno = new Color(242,195,12);
                    }
                    else if (evt.getKeyCode() == KeyEvent.VK_3){
                        corFundo = new Color(192, 21, 219);
                        corContorno = new Color(235,89,33);
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