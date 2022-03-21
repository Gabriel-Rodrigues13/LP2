import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;

import figures.*;
import properties.Colours;
import properties.KeyTypes;

class ProjetoApp {
    
    public static void main (String[] args) {
        FrameState frameState = new FrameState();
        EventHandlers eventHandler = new EventHandlers(frameState);
        ListFrame frame = new ListFrame(frameState, eventHandler);
        frame.setVisible(true);
    }
}

class EventHandlers{
    public FrameState frameState;
    public EventHandlers(FrameState frameState) {
        this.frameState = frameState;
    }

    public void handleColorSwitchEventColor(int keyCode) {
        switch(keyCode) {
            case KeyTypes.botaoPrimeiraCor:
                frameState.updateCorContorno(Colours.cor_fundo_um);
                frameState.updateCorFundo(Colours.cor_contorno_um);
                break;
            case KeyTypes.botaoSegundaCor:
                frameState.updateCorContorno(Colours.cor_fundo_dois);
                frameState.updateCorFundo(Colours.cor_contorno_dois);
                break;
            case KeyTypes.botaoTerceiraCor:
                frameState.updateCorContorno(Colours.cor_fundo_tres);
                frameState.updateCorFundo(Colours.cor_contorno_tres);
                break;
            default:
                break;
        }
    }

    public void handleMouseMovement(MouseEvent e) {
        frameState.posicaoMouse[0] = e.getX();
        frameState.posicaoMouse[1] = e.getY();
    }
    public void handleMouseDrag(MouseEvent e){

    }
}


class FrameState {
    ArrayList<Figure> figs = new ArrayList<Figure>();
    Random rand = new Random();
    Figure focus = null;
    Color corFundo = Colours.cor_fundo_um;
    Color corContorno = Colours.cor_contorno_um;
    int[] posicaoMouse = new int[2];

    public void updateCorFundo(Color cor) {
        this.corFundo = cor;
    }

    public void updateCorContorno(Color cor) {
        this.corContorno = cor;
    }
    
}

class ListFrame extends JFrame {
    FrameState frameState;
    EventHandlers eventHandler;

    
    ListFrame (FrameState frameState, EventHandlers eventHandler) {
        this.frameState = frameState;
        this.eventHandler = eventHandler;

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
                eventHandler.handleMouseMovement(e);
            }

        // Movendo a figura com o mouse 
            public void mouseDragged(MouseEvent e){
                Figure novaFigura = frameState.focus.drag(e);
                frameState.figs.remove(frameState.focus);
                frameState.figs.add(novaFigura);
                frameState.focus = novaFigura;
                repaint();
            }
        });

        // Key listeners de mudança de cores

        this.addKeyListener(
            new KeyAdapter(){
                public void keyPressed(KeyEvent evt){
                    eventHandler.handleColorSwitchEventColor(evt.getKeyCode());
                    repaint();
                }
            }
        );

        // Key listeners de manipulaçao de figuras
        this.addKeyListener (
            new KeyAdapter() {
                public void keyPressed (KeyEvent evt) {
                    int x = frameState.posicaoMouse[0];
                    int y = frameState.posicaoMouse[1];
                    int w = 50;
                    int h = 100;
                    Rect rect = new Rect(x, y, w, h, frameState.corFundo, frameState.corContorno);
                    Ellipse elli = new Ellipse(x, y, w, h, frameState.corFundo, frameState.corContorno);
                    Arc arc = new Arc(x, y, w, h, frameState.corFundo, frameState.corContorno);

                    if (evt.getKeyCode() == KeyEvent.VK_R) {
                        frameState.figs.add(rect);
                    }
                    else if (evt.getKeyCode() == KeyEvent.VK_E) {
                        frameState.figs.add(elli);
                    }
                    else if (evt.getKeyCode() == KeyEvent.VK_A) {
                        frameState.figs.add(arc);
                    }       
                    else if( evt.getKeyCode() == KeyEvent.VK_BACK_SPACE){
                        frameState.figs.remove(frameState.focus);
                        frameState.focus = null;;
                    }
                    else if( evt.getKeyCode() == KeyEvent.VK_DELETE){
                        frameState.figs.clear();
                        frameState.focus = null;
                    }
                    else if(evt.getKeyCode() == KeyEvent.VK_RIGHT){
                        Figure new_figure = frameState.focus.redimensionarW();
                        frameState.figs.remove(frameState.focus);
                        frameState.figs.add(new_figure);
                        frameState.focus = new_figure;
                        
                    }
                    else if(evt.getKeyCode() == KeyEvent.VK_LEFT){
                        Figure new_figure = frameState.focus.redimensionarX();
                        frameState.figs.remove(frameState.focus);
                        frameState.figs.add(new_figure);
                        frameState.focus = new_figure;
                        
                    }
                    else if(evt.getKeyCode() == KeyEvent.VK_UP){
                        Figure new_figure = frameState.focus.redimensionarY();
                        frameState.figs.remove(frameState.focus);
                        frameState.figs.add(new_figure);
                        frameState.focus = new_figure;
                        
                    }
                    else if(evt.getKeyCode() == KeyEvent.VK_DOWN){
                        Figure new_figure = frameState.focus.redimensionarH();
                        frameState.figs.remove(frameState.focus);
                        frameState.figs.add(new_figure);
                        frameState.focus = new_figure;
                        
                    }
            
                    repaint();
                }
            }
        );
        
        // Criando foco e z-order
        this.addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent evt){
                frameState.focus = null;
                for (int j = 0; j < frameState.figs.size(); j++){
                    Figure fig = frameState.figs.get(j);
                    if (fig.coordenadasDentro(evt.getX(), evt.getY())){
                        frameState.focus = fig; 
                        frameState.figs.remove(fig);
                        frameState.figs.add(frameState.focus);
                        repaint();    
                        break;
                    }else{
                        frameState.focus = null;
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
        for (Figure fig: frameState.figs) {
            fig.paint(g);
        }
        if (frameState.focus != null){
            // moldura do foco
            if (frameState.focus.getCorFundo() != Color.RED && frameState.focus.getCorContorno() != Color.RED){
                g2d.setColor(Color.RED);
            }
            g2d.setStroke(new BasicStroke(3));
            g2d.drawRect(frameState.focus.getX(),frameState.focus.getY(), frameState.focus.getW(),frameState.focus.getH());
        }
    }
}