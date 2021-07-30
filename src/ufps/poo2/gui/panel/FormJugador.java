/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufps.poo2.gui.panel;

import java.io.File;
import java.net.InetAddress;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import ufps.poo2.gui.controlador.Controlador;
import ufps.poo2.gui.main.Main;

/**
 *
 * @author USUARIO
 */
public class FormJugador extends javax.swing.JFrame {

    /**
     * Creates new form FormJugador
     */
    public FormJugador() {
        setLocationRelativeTo(null);
        initComponents();
    }

    Main main;

    public FormJugador(Main x) {
        initComponents();
        main = x;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cmdAceptar = new javax.swing.JButton();
        txtNombre = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        lblDirectorio = new javax.swing.JLabel();
        cmdSeleccionar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Datos Jugador"));

        jLabel1.setText("Nombre Jugador");

        cmdAceptar.setText("Aceptar");
        cmdAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdAceptarActionPerformed(evt);
            }
        });

        txtNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreActionPerformed(evt);
            }
        });

        jLabel2.setText("Directorio para guardar archivos");

        lblDirectorio.setBackground(new java.awt.Color(204, 255, 204));

        cmdSeleccionar.setText("Seleccionar");
        cmdSeleccionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdSeleccionarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmdAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblDirectorio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(cmdSeleccionar)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmdAceptar))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblDirectorio, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cmdSeleccionar)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 37, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmdAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdAceptarActionPerformed
        actualizar();
    }//GEN-LAST:event_cmdAceptarActionPerformed

    private void txtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreActionPerformed
        actualizar();
    }//GEN-LAST:event_txtNombreActionPerformed

    private void cmdSeleccionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdSeleccionarActionPerformed
        try {
            JFileChooser buscador = new JFileChooser();
            buscador.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            buscador.showOpenDialog(this);
            File archivo = buscador.getSelectedFile();
            lblDirectorio.setText(archivo.getAbsolutePath());
        } catch (NullPointerException ex) {
            if (lblDirectorio.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Debe ingresar la ruta del buzón de archivos", "Alerta", JOptionPane.WARNING_MESSAGE);
            }
        }

    }//GEN-LAST:event_cmdSeleccionarActionPerformed

    private void actualizar() {
        if (txtNombre.getText().length() > 16) {
            JOptionPane.showMessageDialog(this, "El nombre del jugador debe tener máximo 16 caracteres", "Alerta", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (txtNombre.getText().length() < 4) {
            JOptionPane.showMessageDialog(this, "El nombre del jugador debe tener mínimo 3 caracteres", "Alerta", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (lblDirectorio.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Seleccione la ubicación del buzón de archivos", "Alerta", JOptionPane.WARNING_MESSAGE);
            return;
        }
        Controlador controlador = new Controlador();
        if (!controlador.ingresarNick(txtNombre.getText(), lblDirectorio.getText())) {
            JOptionPane.showMessageDialog(null, "Error al crear el Nick o seleccionar carpeta", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        } else {
            JOptionPane.showMessageDialog(null, "Se actualizó el Nick y la dirección del buzón de archivos", "Notificación", JOptionPane.INFORMATION_MESSAGE);
            main.block(true);
            main.iniciarCrear();

        }
    }
    /**
     * @param args the command line arguments
     */


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cmdAceptar;
    private javax.swing.JButton cmdSeleccionar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblDirectorio;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}