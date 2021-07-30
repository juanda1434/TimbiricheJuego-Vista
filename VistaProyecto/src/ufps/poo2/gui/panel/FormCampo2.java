/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufps.poo2.gui.panel;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import ufps.poo2.gui.controlador.Controlador;
import ufps.poo2.gui.utility.Utility;

/**
 *
 * @author USUARIO
 */
public class FormCampo2 extends JPanel {

    /* la variable lineas guarda enteros de 4 posiciones [0]=la posicion 'i' del primer pulto pulsado,
    [1]=la posicion 'j' del primer pulto pulsado,
    [2]=la posicion 'i' del segundo pulto pulsado,
    [3]=la posicion 'j' del segundo pulto pulsado 
    cada entero de 4 posiciones me representa 1 linea
     */

 /*
    la variable linea me guarda enteros de 2 posiciones y maximo 2 de estos que me representaran coordenadas 
    i,j de 2 puntos que se opriman
     */
 /*
    la variable cuadrados me guarda enteros de 2 posiciones cada arreglo de estos me representa la 
    esquina superior izquierda de un cuadrado que me ayudara a pintarlos mas adelante
     */
    private ArrayList<int[]> lineas;
    private int turnoActual;
    private JButton[][] puntos;// la variable puntos me guarda todos los puntos del panel
    private ArrayList<int[]> linea;//linea me guarda las coordenadas
    private ArrayList<Integer> turnos;
    private ArrayList<int[]> cuadrados;
    private Controlador controlador;
    public FormCampo2(int num) {
        controlador=new Controlador();
        turnoActual = 1;
        lineas = new ArrayList<>();
        linea = new ArrayList<>();
        turnos = new ArrayList<>();
        cuadrados = new ArrayList<>();
        this.setSize(200, 200);
        int tamaño = ((num * 23) + ((num + 1) * 9)) + 30;
        this.setLayout(new GridLayout(num + 1, num + 1, 23, 23));
        puntos = new JButton[num + 1][num + 1];
        this.setBackground(Color.WHITE);
        this.setPreferredSize(new Dimension(tamaño, tamaño));
        this.setSize(tamaño, tamaño);
        this.setName("Juego");
        llenar();

    }

    //ME PINTA LINEAS Y CUADRADOS
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(5));
        g2.setBackground(Color.BLACK);

        if (!cuadrados.isEmpty()) {
            for (int i = 0; i < cuadrados.size(); i++) {
                Rectangle2D r2 = new Rectangle2D.Double(puntos[cuadrados.get(i)[0]][cuadrados.get(i)[1]].getX() + puntos[cuadrados.get(i)[0]][cuadrados.get(i)[1]].getWidth() / 2, puntos[cuadrados.get(i)[0]][cuadrados.get(i)[1]].getY() + puntos[cuadrados.get(i)[0]][cuadrados.get(i)[1]].getHeight() / 2, ((GridLayout) this.getLayout()).getHgap() + puntos[0][0].getWidth(), ((GridLayout) this.getLayout()).getVgap() + puntos[0][0].getWidth());
                System.out.println("estoy pintando un cuadrado");
                switch (cuadrados.get(i)[2]) {
                    case 1:
                        g2.setPaint(Color.RED.brighter().brighter().brighter());
                        break;
                    case 2:
                        g2.setPaint(Color.BLUE.brighter().brighter().brighter());
                        break;
                    case 3:
                        g2.setPaint(Color.GREEN.brighter().brighter().brighter());
                        break;
                }
                g2.fill(r2);
            }
        }
        if (!this.lineas.isEmpty()) {

            for (int i = 0; i < lineas.size(); i++) {
                System.out.println("estoy pintando " + i);
                int x1 = puntos[lineas.get(i)[0]][lineas.get(i)[1]].getX() + puntos[lineas.get(i)[0]][lineas.get(i)[1]].getWidth() / 2;
                int y1 = puntos[lineas.get(i)[0]][lineas.get(i)[1]].getY() + puntos[lineas.get(i)[0]][lineas.get(i)[1]].getHeight() / 2;
                int x2 = puntos[lineas.get(i)[2]][lineas.get(i)[3]].getX() + puntos[lineas.get(i)[2]][lineas.get(i)[3]].getWidth() / 2;
                int y2 = puntos[lineas.get(i)[2]][lineas.get(i)[3]].getY() + puntos[lineas.get(i)[2]][lineas.get(i)[3]].getHeight() / 2;
                Line2D lin = new Line2D.Double(x1, y1, x2, y2);
                switch (turnos.get(i)) {
                    case 1:
                        g2.setPaint(Color.RED.darker().darker());
                        break;
                    case 2:
                        g2.setPaint(Color.BLUE.darker().darker());
                        break;
                    case 3:
                        g2.setPaint(Color.GREEN.darker().darker());
                        break;
                }
                g2.draw(lin);
            }
        }
    }
//este metodo me llena el panel de botones

    private void llenar() {
        for (int i = 0; i < puntos.length; i++) {
            for (int j = 0; j < puntos[i].length; j++) {
                this.personalizarBotones(i, j);
                this.darActionPerformandBotones(i, j);
            }
        }
    }
// este metodo toma cada boton y lo personaliza

    private void personalizarBotones(int i, int j) {
        puntos[i][j] = new JButton();
        puntos[i][j].setBorder(null);
        puntos[i][j].setContentAreaFilled(false);
        puntos[i][j].setBorderPainted(false);
        puntos[i][j].setIcon(new ImageIcon(getClass().getResource("/images/cirulo.png")));
        puntos[i][j].setPreferredSize(new Dimension(10, 10));
        puntos[i][j].setName(i + "--" + j);
        this.add(this.puntos[i][j]);
    }
    // para hallar el centro en x = getX()+ getWidth()/2
    // para hallar el centro en y = getY()+ getHeight()/2

//Este metodo le da a cada boton las acciones que debe hacer cuando lo oprimo
    private void darActionPerformandBotones(int i, int j) {
        this.puntos[i][j].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(((JButton) e.getSource()).getName());

                //cuando se pulsa un boton este if guarda la coordenada de estos botones y deja un maximo de 2 botones que significan una linea
                if (linea.size() < 2) {
                    int[] x = new int[2];
                    x[0] = i;
                    x[1] = j;
                    linea.add(x);
                }
                // este if me valida que linea tenga guardado las coordenadas de 2 botones
                if (linea.size() == 2) {
                    // este if valida que estas coordenadas sean correctas equivalentes a una linea horizontal o vertical
                    if (validarMov()) {
                        // guarda en una variable el turno de quien hizo esa linea
                        turnos.add(turnoActual);
                        agregarCuadrado(-1, 0);
                        agregarCuadrado(1, 0);
                        agregarCuadrado(0, -1);
                        agregarCuadrado(0, 1);

                    } else {
                        //en caso de que un movimiento no sea valido limpia la variable linea para poder ingresar una nueva
                        JOptionPane.showMessageDialog(null, "MOVIMIENTO PROHIBIDO");
                        linea.clear();
                        return;
                    }
                    // si alguien marca hace un cuadrado en ese turno devuelve el turno actual
                    if (validarEx(validarCuadrado(-1, 0)) || validarEx(validarCuadrado(1, 0)) || validarEx(validarCuadrado(0, -1)) || validarEx(validarCuadrado(0, 1))) {
                        turnoActual--;
                    }
                    // las cordenadas de los 2 botones los guarda en lineas;
                    int[] lin = new int[4];
                    lin[0] = linea.get(0)[0];
                    lin[1] = linea.get(0)[1];
                    lin[2] = linea.get(1)[0];
                    lin[3] = linea.get(1)[1];
                    lineas.add(lin);
                    //cuando guarda la linea limpia la variable linea
                    linea.clear();
                    //turno actual va aumentando y si llega a 3 se acaba
                    if (turnoActual == 3) {
                        turnoActual = 0;
                    }
                    turnoActual++;
                    // cuando se hace una linea manda a pintar el panel
                    repaint();
                }

            }
        });
    }
// este metodo valida movimientos

    private boolean validarMov() {
        boolean w = false;
        int indiceI = linea.get(0)[0];
        int indiceJ = linea.get(0)[1];
        int indiceI2 = linea.get(1)[0];
        int indiceJ2 = linea.get(1)[1];
        //este if valida que las coordenadas de los 2 botones guardadas en la variable linea. valida que los 2 botones hagan una lina horizontal o vertical
        if ((indiceI == indiceI2 && (indiceJ + 1 == indiceJ2 || indiceJ - 1 == indiceJ2)) || ((indiceI + 1 == indiceI2 || indiceI - 1 == indiceI2) && indiceJ == indiceJ2)) {
            w = true;
            for (int i = 0; i < lineas.size(); i++) {
                //este if valida que no se esten metiendo coordenadas de 2 botones que hagan una linea y que la linea ya este guardada pero en otro orden ejemplo: linea 00-01 si pulso despues 01-00 manda false;
                if ((lineas.get(i)[0] == linea.get(0)[0] && lineas.get(i)[1] == linea.get(0)[1] && lineas.get(i)[2] == linea.get(1)[0] && lineas.get(i)[3] == linea.get(1)[1]) || (lineas.get(i)[0] == linea.get(1)[0] && lineas.get(i)[1] == linea.get(1)[1] && lineas.get(i)[2] == linea.get(0)[0] && lineas.get(i)[3] == linea.get(0)[1])) {
                    w = false;
                    break;
                }
            }
        }

        return w;
    }
// valida que se halla hecho un cuadrado, las variables a,b me ayudan a a validad arriba abajo izquierda o derecha
    //si se ingresa a=-1 y b=0 significa union de puntos horizontales y busca cuadrado arriba de esta linea
    //si se ingresa a=1 y b=0 significa union de puntos horizontales y busca cuadrado abajo de esta linea
    //si se ingresa a=0 y b = -1 signifca la union de puntos verticalmente y busca cuadrado a la izquierda de esta linea
    // si se ingresa a=0 y b= 1 significa la union de puntos verticalmente ybusca cuadrado a la derecha de esta linea

    private int[] validarCuadrado(int a, int b) {
        int[] cuadrado = null;
        int i = linea.get(0)[0];
        int j = linea.get(0)[1];
        int i2 = linea.get(1)[0];
        int j2 = linea.get(1)[1];
        //este if valida que que no busque cuadrados fuera de los limites de los botones
        if ((i + a >= 0 && i2 + a >= 0) && (i + a <= this.puntos.length - 1 && i2 + a <= this.puntos.length - 1) && (j + b >= 0 && j2 + b >= 0) && (j + b <= this.puntos.length - 1 && j2 + b <= this.puntos.length - 1)) {
            int arribaI = i + a;
            int arribaJ = j + b;
            int arribaSI = i2 + a;
            int arribaSJ = j2 + b;

            //aqui busca dependiendo de los valores de la a,b arriba abajo izquierda o derecha los cuadrados
            //si se traza una linea horizontal a un cuadrado seleccionando 2 puntos y esta es la inferior y la ultima para conformarlo :
            for (int l = 0; l < lineas.size(); l++) {
                //este if buscara en la variable lineas que guarda todas las lineas la linea equivalente a : la formada por la union del primer punto pulsado y su pulso supeior;
                if ((i == lineas.get(l)[0] && j == lineas.get(l)[1] && arribaI == lineas.get(l)[2] && arribaJ == lineas.get(l)[3]) || (i == lineas.get(l)[2] && j == lineas.get(l)[3] && arribaI == lineas.get(l)[0] && arribaJ == lineas.get(l)[1])) {
                    for (int k = 0; k < lineas.size(); k++) {
                        //este if buscara en la variable lineas que guarda todas las lineas la linea equivalente a : la formada por la union del segundo punto pulsado y su pulso supeior;
                        if ((i2 == lineas.get(k)[0] && j2 == lineas.get(k)[1] && arribaSI == lineas.get(k)[2] && arribaSJ == lineas.get(k)[3]) || (i2 == lineas.get(k)[2] && j2 == lineas.get(k)[3] && arribaSI == lineas.get(k)[0] && arribaSJ == lineas.get(k)[1])) {
                            for (int m = 0; m < lineas.size(); m++) {
                                //este if buscara en la variable lineas que guarda todas las lineas la linea equivalente a : la formada por la union de los puntos superiores del primer y segundo punto pulsado
                                if ((arribaI == lineas.get(m)[0] && arribaJ == lineas.get(m)[1] && arribaSI == lineas.get(m)[2] && arribaSJ == lineas.get(m)[3]) || (arribaI == lineas.get(m)[2] && arribaJ == lineas.get(m)[3] && arribaSI == lineas.get(m)[0] && arribaSJ == lineas.get(m)[1])) {
                                    // if encuentra todas esas lineas se guardara generara un cuadrado
                                    cuadrado = cuadrado(i, i2, arribaI, j, j2, arribaJ);
                                    return cuadrado;
                                }
                            }
                        }
                    }
                }
            }
        }
        return cuadrado;
    }
    //valida que exista un cuadrado

    boolean validarEx(int[] x) {
        for (int i = 0; i < cuadrados.size(); i++) {
            if (x != null && (cuadrados.get(i)[0] == x[0] && cuadrados.get(i)[1] == x[1])) {
                return true;
            }

        }
        return false;
    }
// agrega un cuadrado al arreglo de cuadrados me guarda la coordenada mas alta del cuadrado

    public boolean agregarCuadrado(int a, int b) {
        int[] cuadrado = validarCuadrado(a, b);
        if (cuadrado != null) {
            this.cuadrados.add(cuadrado);
            return true;
        }
        return false;
    }
// me genera un cuadrado -ingresa las coordenadas del cuadrado y deja las mas bajas osea las coordenadas de la esquina superior izquierda

    int[] cuadrado(int i, int i2, int i3, int j, int j1, int j2) {
        int[] x = new int[3];
        int[] y = new int[3];
        x[0] = i;
        x[1] = i2;
        x[2] = i3;
        y[0] = j;
        y[1] = j1;
        y[2] = j2;
        Utility.burbuja(x);
        Utility.burbuja(y);
        int cuadrado[] = new int[3];
        cuadrado[0] = x[0];
        cuadrado[1] = y[0];
        cuadrado[2] = this.turnoActual;
        return cuadrado;
    }

}
