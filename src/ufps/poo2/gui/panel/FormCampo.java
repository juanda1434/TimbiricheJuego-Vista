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
import ufps.poo2.gui.utility.Utility;

/**
 *
 * @author USUARIO
 */
public class FormCampo extends JPanel {

    private ArrayList<JButton[]> lineas;
    private int turnoActual;
    private JButton[][] puntos;
    private ArrayList<JButton> linea;
    private ArrayList<Integer> turnos;
    private ArrayList<int[]> cuadrados;

    public FormCampo(int num) {
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
                Rectangle2D r2 = new Rectangle2D.Double(puntos[cuadrados.get(i)[0]][cuadrados.get(i)[1]].getX() + puntos[cuadrados.get(i)[0]][cuadrados.get(i)[1]].getWidth() / 2, puntos[cuadrados.get(i)[0]][cuadrados.get(i)[1]].getY() + puntos[cuadrados.get(i)[0]][cuadrados.get(i)[1]].getHeight() / 2, ((GridLayout)this.getLayout()).getHgap()+puntos[0][0].getWidth(),((GridLayout)this.getLayout()).getVgap()+puntos[0][0].getWidth() );
//                System.out.println(((GridLayout)this.getLayout()).getHgap()+"___"+((GridLayout)this.getLayout()).getHgap());
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
                System.out.println("estoy pintando");
                Line2D lin = new Line2D.Double(this.lineas.get(i)[0].getX() + this.lineas.get(i)[0].getWidth() / 2, this.lineas.get(i)[0].getY() + this.lineas.get(i)[0].getHeight() / 2, this.lineas.get(i)[1].getX() + this.lineas.get(i)[1].getWidth() / 2, this.lineas.get(i)[1].getY() + this.lineas.get(i)[1].getHeight() / 2);
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

    void llenar() {
        for (int i = 0; i < puntos.length; i++) {
            for (int j = 0; j < puntos[i].length; j++) {
                this.personalizarBotones(i, j);
                this.darActionPerformandBotones(i, j);
            }
        }
    }

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

    private void darActionPerformandBotones(int i, int j) {
        this.puntos[i][j].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(((JButton) e.getSource()).getName());
//                System.out.println(((JButton)e.getSource()).getParent().getName());;
                
                if (linea.size() < 2) {
                    linea.add(puntos[i][j]);
                }
                if (linea.size() == 2) {
                    if (validarMov()) {
                        turnos.add(turnoActual);
                        agregarCuadrado(-1, 0);
                        agregarCuadrado(1, 0);
                        agregarCuadrado(0, -1);
                        agregarCuadrado(0, 1);

                    } else {
                        JOptionPane.showMessageDialog(null, "MOVIMIENTO PROHIBIDO");
                        linea.clear();
                        return;
                    }
                    if (validarEx(validarCuadrado(-1, 0)) || validarEx(validarCuadrado(1, 0)) || validarEx(validarCuadrado(0, -1)) || validarEx(validarCuadrado(0, 1))) {
                        turnoActual--;
                    }
                    
                    JButton[] lin = new JButton[2];
                    lin[0] = linea.get(0);
                    lin[1] = linea.get(1);
                    lineas.add(lin);
                    linea.clear();
                    if (turnoActual == 3) {
                        turnoActual = 0;
                    }
                    turnoActual++;
                    repaint();
                }
                

            }
        });
    }

    private boolean validarMov() {
        boolean w = false;
        int indiceI = subString(linea.get(0).getName())[0];
        int indiceJ = subString(linea.get(0).getName())[1];
        int indiceI2 = subString(linea.get(1).getName())[0];
        int indiceJ2 = subString(linea.get(1).getName())[1];
        if ((indiceI == indiceI2 && (indiceJ + 1 == indiceJ2 || indiceJ - 1 == indiceJ2)) || ((indiceI + 1 == indiceI2 || indiceI - 1 == indiceI2) && indiceJ == indiceJ2)) {
            w = true;
        }
        for (int i = 0; i < lineas.size(); i++) {
            if ((lineas.get(i)[0].equals(linea.get(0)) && lineas.get(i)[1].equals(linea.get(1))) || (lineas.get(i)[0].equals(linea.get(1)) && lineas.get(i)[1].equals(linea.get(0)))) {
                w = false;
            }
        }

        return w;
    }
private int[] subString(String a){
    int[]sub=new int[2];
    String[]aux=a.split("--");
    sub[0]=Integer.parseInt(aux[0]);
    sub[1]=Integer.parseInt(aux[1]);
    return sub;
}
    private int[] validarCuadrado(int a, int b) {
        int[] cuadrado = null;

        int i = subString(linea.get(0).getName())[0];
        int j = subString(linea.get(0).getName())[1];
        int  i2= subString(linea.get(1).getName())[0];
        int j2 = subString(linea.get(1).getName())[1];
        if ((i + a >= 0 && i2 + a >= 0) && (i + a <= this.puntos.length - 1 && i2 + a <= this.puntos.length - 1) && (j + b >= 0 && j2 + b >= 0) && (j + b <= this.puntos.length - 1 && j2 + b <= this.puntos.length - 1)) {
            JButton primero = this.puntos[i][j];
            JButton segundo = this.puntos[i2][j2];
            JButton arribaP = this.puntos[i + a][j + b];
            JButton arribaS = this.puntos[i2 + a][j2 + b];

            for (int k = 0; k < lineas.size(); k++) {
                if ((primero.equals(lineas.get(k)[0]) && arribaP.equals(lineas.get(k)[1])) || (primero.equals(lineas.get(k)[1]) && arribaP.equals(lineas.get(k)[0]))) {

                    for (int l = 0; l < lineas.size(); l++) {
                        if ((segundo.equals(lineas.get(l)[0]) && arribaS.equals(lineas.get(l)[1])) || (segundo.equals(lineas.get(l)[1]) && arribaS.equals(lineas.get(l)[0]))) {

                            for (int m = 0; m < lineas.size(); m++) {
                                if ((arribaP.equals(lineas.get(m)[0]) && arribaS.equals(lineas.get(m)[1])) || (arribaP.equals(lineas.get(m)[1]) && arribaS.equals(lineas.get(m)[0]))) {
                                    cuadrado = cuadrado(i, i2, i + a, j, j2, j + b);

                                    System.out.println("COMPLETASTE CUADRADO");
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

    boolean validarEx(int[] x) {
        for (int i = 0; i < cuadrados.size(); i++) {
            if (x!=null&&(cuadrados.get(i)[0] == x[0] && cuadrados.get(i)[1] == x[1])) {
                return true;
            }

        }
        return false;
    }

    public boolean agregarCuadrado(int a, int b) {
        int[] cuadrado = validarCuadrado(a, b);
        if (cuadrado != null) {
            this.cuadrados.add(cuadrado);
            return true;
        }
        return false;
    }

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

    public void burbuja(int[] A) {
        int i, j, aux;
        for (i = 0; i < A.length - 1; i++) {
            for (j = 0; j < A.length - i - 1; j++) {
                if (A[j + 1] < A[j]) {
                    aux = A[j + 1];
                    A[j + 1] = A[j];
                    A[j] = aux;
                }
            }
        }

    }
}
