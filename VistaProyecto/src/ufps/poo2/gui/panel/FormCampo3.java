/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufps.poo2.gui.panel;

/**
 *
 * @author USUARIO
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import ufps.poo2.gui.controlador.Controlador;

/**
 *
 * @author USUARIO
 */
public class FormCampo3 extends JPanel implements MouseMotionListener {

    private JLabel[][] puntos;
    private ArrayList<int[]> linea;
    private Controlador controlador;
    private int posx1;
    private int posy1;
    private int posx2;
    private int posy2;
    private boolean poderPintar = false;
    private boolean esmiTurno = false;
    private boolean juegoActivo=true;
    public FormCampo3(int num) {
        this.addMouseMotionListener(this);
        controlador = new Controlador();
        linea = new ArrayList<>();
        this.setSize(200, 200);
        int tam = ((num * 23) + ((num + 1) * 9)) + 30;
        this.setLayout(new GridLayout(num + 1, num + 1, 23, 23));
        puntos = new JLabel[num + 1][num + 1];
        this.setBackground(Color.WHITE);
        this.setPreferredSize(new Dimension(tam, tam));
        this.setSize(tam, tam);
        this.setName("Juego");
        llenar();
        actualizarTurno();
    }

    //ME PINTA LINEAS Y CUADRADOS
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        ArrayList<int[]> cuadrados = this.controlador.getCuadrado();
        ArrayList<int[]> lineas = this.controlador.getLineas();
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(5));
        g2.setBackground(Color.white);
        if (!cuadrados.isEmpty()) {
            for (int[] cuadrado : cuadrados) {
                Rectangle2D r2 = new Rectangle2D.Double(puntos[cuadrado[0]][cuadrado[1]].getX() + puntos[cuadrado[0]][cuadrado[1]].getWidth() / 2, puntos[cuadrado[0]][cuadrado[1]].getY() + puntos[cuadrado[0]][cuadrado[1]].getHeight() / 2, ((GridLayout) this.getLayout()).getHgap() + puntos[0][0].getWidth(), ((GridLayout) this.getLayout()).getVgap() + puntos[0][0].getWidth());
                switch (cuadrado[2]) {
                    case 1:
                        g2.setPaint(new Color(194,111,105).brighter().brighter());
                        break;
                    case 2:
                        g2.setPaint(new Color(28,158,215).brighter().brighter());
                        break;
                    case 3:
                        g2.setPaint(new Color(174,88,123).brighter().brighter());
                        break;
                    case 4:
                        g2.setPaint(new Color(0,128,99).brighter().brighter());
                        break;
                }
                g2.fill(r2);
            }

        }
        if (!lineas.isEmpty()) {
            for (int[] linea : lineas) {
                int x1 = puntos[linea[0]][linea[1]].getX() + puntos[linea[0]][linea[1]].getWidth() / 2;
                int y1 = puntos[linea[0]][linea[1]].getY() + puntos[linea[0]][linea[1]].getHeight() / 2;
                int x2 = puntos[linea[2]][linea[3]].getX() + puntos[linea[2]][linea[3]].getWidth() / 2;
                int y2 = puntos[linea[2]][linea[3]].getY() + puntos[linea[2]][linea[3]].getHeight() / 2;
                Line2D lin = new Line2D.Double(x1, y1, x2, y2);
                switch (linea[4]) {
                    case 1:
                        g2.setPaint(new Color(194,111,105));
                        break;
                    case 2:
                        g2.setPaint(new Color(28,158,215));
                        break;
                    case 3:
                        g2.setPaint(new Color(174,88,123));
                        break;
                    case 4:
                        g2.setPaint(new Color(0,128,99));
                        break;
                }
                g2.draw(lin);
            }
        }
        
        if (poderPintar) {
            g2.setPaint(new Color(233,141,81));
            Line2D lin = new Line2D.Double(posx1, posy1, posx2, posy2);
            g2.draw(lin);
        }
    }

    private void llenar() {
        for (int i = 0; i < puntos.length; i++) {
            for (int j = 0; j < puntos[i].length; j++) {
                this.personalizarBotones(i, j);
                this.darActionPerformandBotones(i, j);
            }
        }
    }

    private void personalizarBotones(int i, int j) {
        puntos[i][j] = new JLabel();
        puntos[i][j].setBorder(null);
         //String dir=System.getProperty("user.dir");//sacar carpeta de proyecto
        // String sep=System.getProperty("file.separator");//sacar separador de carpetas
        puntos[i][j].setIcon(new ImageIcon(getClass().getResource("/Images/cirulo.png")));
        puntos[i][j].setPreferredSize(new Dimension(10, 10));
        puntos[i][j].setName(i + "--" + j);
        this.add(this.puntos[i][j]);
    }
    // para hallar el centro en x = getX()+ getWidth()/2
    // para hallar el centro en y = getY()+ getHeight()/2

    private void darActionPerformandBotones(int i, int j) {
        this.puntos[i][j].addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ex) {                    
                }
                if (esmiTurno) {
                    System.out.println(((JLabel) e.getSource()).getName());
                    if (linea.size() < 2) {
                        int[] lin = new int[2];
                        lin[0] = i;
                        lin[1] = j;
                        linea.add(lin);

                        if (!poderPintar) {
                            posx1 = puntos[i][j].getX() + puntos[i][j].getWidth() / 2;
                            posy1 = puntos[i][j].getY() + puntos[i][j].getHeight() / 2;
                            posx2 = puntos[i][j].getX() + puntos[i][j].getWidth() / 2;
                            posy2 = puntos[i][j].getY() + puntos[i][j].getHeight() / 2;
                        }
                        poderPintar = !poderPintar;
                    }
                    if (linea.size() == 2) {
                        try {
                            if (!controlador.enviarLinea(linea.get(0)[0], linea.get(0)[1], linea.get(1)[0], linea.get(1)[1])) {
                                JOptionPane.showMessageDialog(null, "ERROR AL PULSAR LA LINEA");
                                poderPintar = false;
                                repaint();
                                linea.clear();
                                return;
                            }
                        } catch (Exception ex) {
                           ex.printStackTrace();
                           
                        }
                        linea.clear();
                    }
                    repaint();
                } else {
                    JOptionPane.showMessageDialog(null, "no es tu turno");
                }

            }

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {
                if (poderPintar) {
                    posx2 = puntos[i][j].getX() + puntos[i][j].getWidth() / 2;
                    posy2 = puntos[i][j].getY() + puntos[i][j].getHeight() / 2;
                    repaint();
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {}
        });
    }

    
    
    private void desYBloPuntos(boolean val) {
        for (int i = 0; i < puntos.length; i++) {
            for (int j = 0; j < puntos[i].length; j++) {
                puntos[i][j].setEnabled(val);
            }
        }
    }

    public void actualizarTurno() {
        Thread hilo = new Thread(new Runnable() {
            
            public void run() {               
                while (juegoActivo) {
                    boolean[]esMiturno=controlador.esMiTurno();
                    boolean miTurno = esMiturno[0];                    
                    juegoActivo=esMiturno[1];  
                    if (miTurno && !esmiTurno) {                        
                        JOptionPane.showMessageDialog(null,"Es tu turno");                        
                    }    
                    esmiTurno=miTurno;
                    desYBloPuntos(esmiTurno);
                    repaint();
                    
                }
            }
            
        });
        hilo.start();
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (poderPintar) {
            posx2 = e.getX();
            posy2 = e.getY();
            repaint();            
        }
    }

}
