import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import figures.*;
import properties.Colours;
import properties.KeyTypes;

class ProjetoApp {

    public static void main(String[] args) {
        FrameState frameState = new FrameState();
        EventHandlers eventHandler = new EventHandlers(frameState);
        ListFrame frame = new ListFrame(frameState, eventHandler);
        frame.setVisible(true);
    }
}

class EventHandlers {
    public FrameState frameState;

    public EventHandlers(FrameState frameState) {
        this.frameState = frameState;
    }

    public void handleColorSwitchEventColor(int keyCode) {
        switch (keyCode) {
            case KeyTypes.botaoPrimeiraCor:
                frameState.updateCorContorno(Colours.cor_contorno_um);
                frameState.updateCorFundo(Colours.cor_fundo_um);
                break;
            case KeyTypes.botaoSegundaCor:
                frameState.updateCorContorno(Colours.cor_contorno_dois);
                frameState.updateCorFundo(Colours.cor_fundo_dois);
                break;
            case KeyTypes.botaoTerceiraCor:
                frameState.updateCorContorno(Colours.cor_contorno_tres);
                frameState.updateCorFundo(Colours.cor_fundo_tres);
                break;
            case KeyTypes.botaoQuartaCor:
                frameState.updateCorContorno(Colours.cor_contorno_quatro);
                frameState.updateCorFundo(Colours.cor_fundo_quatro);
                break;
            default:
                break;
        }
    }

    public void handleAlteracaoFiguras(int keyCode) {
        switch (keyCode) {
            case KeyTypes.botaoCriarRect:
                Rect rect = new Rect(frameState.getMouseX(), frameState.getMouseY(),
                        frameState.corFundo, frameState.corContorno);
                frameState.figs.add(rect);
                break;
            case KeyTypes.botaoCriarRoundedRect:
                RoundRect rr = new RoundRect(frameState.getMouseX(), frameState.getMouseY(),
                        frameState.corFundo, frameState.corContorno);
                frameState.figs.add(rr);
                break;
            case KeyTypes.botaoCriarEllipse:
                Ellipse elli = new Ellipse(frameState.getMouseX(), frameState.getMouseY(),
                        frameState.corFundo, frameState.corContorno);
                frameState.figs.add(elli);
                break;
            case KeyTypes.botaoCriarArc:
                Arc arc = new Arc(frameState.getMouseX(), frameState.getMouseY(),
                        frameState.corFundo, frameState.corContorno);
                frameState.figs.add(arc);
                break;
            case KeyTypes.botaoApagarFigura:
                frameState.figs.remove(frameState.focus);
                frameState.focus = null;
                break;
            case KeyTypes.botaoApagarTodas:
                frameState.figs.clear();
                frameState.focus = null;
                break;
            case KeyTypes.botaoResizeUp:
                resizeFigure(frameState.focus.aumentarEixoY());
                break;
            case KeyTypes.botaoResizeDown:
                resizeFigure(frameState.focus.reduzirEixoY());
                break;
            case KeyTypes.botaoResizeLeft:
                resizeFigure(frameState.focus.reduzirEixoX());
                break;
            case KeyTypes.botaoResizeRight:
                resizeFigure(frameState.focus.aumentarEixoX());
                break;
            default:
                break;
        }
    }

    private void resizeFigure(Figure novaFigura) {
        this.frameState.figs.remove(frameState.focus);
        this.frameState.figs.add(novaFigura);
        this.frameState.focus = novaFigura;
    }

    public void handleMouseMovement(MouseEvent e) {
        frameState.setMouseX(e.getX());
        frameState.setMouseY(e.getY());
    }

    public void mouseDragged(MouseEvent e) {
        Figure novaFigura = frameState.focus.drag(e);
        frameState.figs.remove(frameState.focus);
        frameState.figs.add(novaFigura);
        frameState.focus = novaFigura;
    }

}

class FrameState {
    ArrayList<Figure> figs = new ArrayList<Figure>();
    Figure focus = null;
    Color[] corFundo = Colours.cor_fundo_um;
    Color corContorno = Colours.cor_contorno_um;
    private int posicaoMouseX = 0;
    private int posicaoMouseY = 0;

    public void updateCorFundo(Color[] cor) {
        this.corFundo = cor;
    }

    public void updateCorContorno(Color cor) {
        this.corContorno = cor;
    }

    public int getMouseX() {
        return this.posicaoMouseX;
    }

    public int getMouseY() {
        return this.posicaoMouseY;
    }

    public void setMouseX(int pos) {
        this.posicaoMouseX = pos;
    }

    public void setMouseY(int pos) {
        this.posicaoMouseY = pos;
    }

}

class ListFrame extends JFrame {
    FrameState frameState;
    EventHandlers eventHandler;

    ListFrame(FrameState frameState, EventHandlers eventHandler) {
        this.frameState = frameState;
        this.eventHandler = eventHandler;

        this.addWindowListener(
                new WindowAdapter() {
                    public void windowClosing(WindowEvent e) {
                        System.exit(0);
                    }
                });

        // Pegando a posição do cursor
        this.addMouseMotionListener(new MouseAdapter() {
            public void mouseMoved(MouseEvent e) {
                eventHandler.handleMouseMovement(e);
            }

            // Movendo a figura com o mouse
            public void mouseDragged(MouseEvent e) {
                eventHandler.mouseDragged(e);
                repaint();
            }
        });

        // Key listeners de mudança de cores

        this.addKeyListener(
                new KeyAdapter() {
                    public void keyPressed(KeyEvent evt) {
                        eventHandler.handleColorSwitchEventColor(evt.getKeyCode());
                        repaint();
                    }
                });

        // Key listeners de manipulaçao de figuras
        this.addKeyListener(
                new KeyAdapter() {
                    public void keyPressed(KeyEvent evt) {
                        eventHandler.handleAlteracaoFiguras(evt.getKeyCode());
                        repaint();
                    }
                });

        // Criando foco e z-order
        this.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent evt) {
                frameState.focus = null;
                for (int j = 0; j < frameState.figs.size(); j++) {
                    Figure fig = frameState.figs.get(j);
                    if (fig.coordenadasDentro(evt.getX(), evt.getY())) {
                        frameState.focus = fig;
                        frameState.figs.remove(fig);
                        frameState.figs.add(frameState.focus);
                        repaint();
                        break;
                    } else {
                        frameState.focus = null;
                        repaint();
                    }
                }
            }
        });

        this.setTitle("Projeto");
        this.setSize(700, 700);
    }

    public void paint(Graphics g) {
        super.paint(g);
        for (Figure fig : frameState.figs) {
            fig.paint(g);
        }
        if (frameState.focus != null) {

            frameState.focus.desenharContorno(g);
        }
    }
}