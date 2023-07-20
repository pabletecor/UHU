/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aplicacion;

import static Aplicacion.VentanaPrincipal.co;
import Persistencia.conexionOracle;
import Persistencia.experto;
import Persistencia.manejaExperto;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author usuario
 */
public class ventanaExpertos extends javax.swing.JFrame {

   //private JTable tExpertos;
   private manejaExperto mExp=null;
   //private JTextField txtPais;
   //private conexionOracle co=null;
   
    DefaultTableModel modeloTExpertos = new DefaultTableModel() {

    public boolean isCellEditable(int row, int column) {
        return false;
    }
};
public ventanaExpertos(conexionOracle c) throws SQLException {
    mExp = new manejaExperto(c);
    initComponents();
    DibujarTablaExpertos();
    pideExpertos();
}

    private ventanaExpertos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    private void DibujarTablaExpertos() {
        tExpertos.setModel(modeloTExpertos);
        String[] columnasTabla = {"Código","Nombre","País","Sexo","Especialidad"};
        modeloTExpertos.setColumnIdentifiers(columnasTabla);
        // Para no permitir el redimensionamiento de las columnas con el ratón
        tExpertos.getTableHeader().setResizingAllowed(false);
        // Así se fija el ancho de las columnas
        tExpertos.getColumnModel().getColumn(0).setPreferredWidth(25);
        tExpertos.getColumnModel().getColumn(1).setPreferredWidth(140);
        tExpertos.getColumnModel().getColumn(2).setPreferredWidth(80);
        tExpertos.getColumnModel().getColumn(3).setPreferredWidth(8);
        tExpertos.getColumnModel().getColumn(4).setPreferredWidth(122);
}
    
private void rellenarTablaExpertos (ArrayList<experto> expertos) {
    Object[] columna = new Object[5];
    int numRegistros = expertos.size();
    for (int i = 0; i < numRegistros; i++) {
        columna[0] = expertos.get(i).getCodExperto();
        columna[1] = expertos.get(i).getNombre();
        columna[2] = expertos.get(i).getPais();
        columna[3] = expertos.get(i).getSexo();
        columna[4] = expertos.get(i).getEspecialidad();
        modeloTExpertos.addRow(columna);
    }
}
private void vaciarTablaExpertos(){
    int i = modeloTExpertos.getRowCount();
    while (modeloTExpertos.getRowCount() > 0)
    modeloTExpertos.removeRow(0);
}
    

private void pideExpertos() throws SQLException {
    ArrayList<experto> lExp = mExp.listaExpertos();
    rellenarTablaExpertos (lExp);
}
private void pideExpertosPorPais() throws SQLException {
    String paisSeleccionado = txtPais.getText();
    ArrayList<experto> lExp = mExp.listaExpertosPorPais(paisSeleccionado);
    rellenarTablaExpertos (lExp);
}

private void pideSexo() throws SQLException{

    String SexoSeleccionado=txtSexo.getText();
    String tmp;
    char SexoSelec=SexoSeleccionado.charAt(0);
    
    if(SexoSelec=='M')
    {
        tmp="Hay "+mExp.sexoExperto(SexoSelec)+" Hombres en la Base de Datos.";
        Label.setText(tmp);
    }
    else if(SexoSelec=='F')
    {
        tmp="Hay "+mExp.sexoExperto(SexoSelec)+" Mujeres en la Base de Datos.";
        Label.setText(tmp);
    }
    
}

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPasswordField1 = new javax.swing.JPasswordField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        Tabla = new javax.swing.JScrollPane();
        tExpertos = new javax.swing.JTable();
        txtPais = new javax.swing.JTextField();
        EquiquetaPais = new javax.swing.JLabel();
        FiltrarPorPais = new javax.swing.JToggleButton();
        ListarTodos = new javax.swing.JToggleButton();
        jLabel1 = new javax.swing.JLabel();
        txtSexo = new javax.swing.JTextField();
        Contar = new javax.swing.JButton();
        MostrarSexo = new javax.swing.JLabel();
        Label = new javax.swing.JLabel();

        jPasswordField1.setText("jPasswordField1");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jScrollPane2.setViewportView(jTextArea2);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tExpertos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        Tabla.setViewportView(tExpertos);

        txtPais.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPaisActionPerformed(evt);
            }
        });

        EquiquetaPais.setText("País");

        FiltrarPorPais.setText("Filtrar por País");
        FiltrarPorPais.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FiltrarPorPaisActionPerformed(evt);
            }
        });

        ListarTodos.setText("Listar todos");
        ListarTodos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ListarTodosActionPerformed(evt);
            }
        });

        jLabel1.setText("Sexo");

        txtSexo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSexoActionPerformed(evt);
            }
        });

        Contar.setText("Contar");
        Contar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ContarActionPerformed(evt);
            }
        });

        Label.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                LabelInputMethodTextChanged(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Tabla, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(EquiquetaPais)
                                .addGap(18, 18, 18)
                                .addComponent(txtPais, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(203, 203, 203)
                                .addComponent(FiltrarPorPais)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(ListarTodos))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(txtSexo, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(Contar)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(MostrarSexo, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Label, javax.swing.GroupLayout.PREFERRED_SIZE, 387, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(Tabla, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(108, 108, 108)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPais, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(EquiquetaPais)
                    .addComponent(FiltrarPorPais, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ListarTodos))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtSexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Contar)
                    .addComponent(MostrarSexo)
                    .addComponent(Label))
                .addGap(24, 24, 24))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtPaisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPaisActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPaisActionPerformed

    private void FiltrarPorPaisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FiltrarPorPaisActionPerformed
        vaciarTablaExpertos();
        try {
            pideExpertosPorPais();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }//GEN-LAST:event_FiltrarPorPaisActionPerformed

    private void ListarTodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ListarTodosActionPerformed
        vaciarTablaExpertos();
        try {
            pideExpertos();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }//GEN-LAST:event_ListarTodosActionPerformed

    private void txtSexoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSexoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSexoActionPerformed

    private void ContarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ContarActionPerformed
       try {
           // TODO add your handling code here:
           pideSexo();
       } catch (SQLException ex) {
          JOptionPane.showMessageDialog(null, "Error al Contar: "+ex.getMessage());
       }
    }//GEN-LAST:event_ContarActionPerformed

    private void LabelInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_LabelInputMethodTextChanged
        // TODO add your handling code here:

    }//GEN-LAST:event_LabelInputMethodTextChanged

    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ventanaExpertos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ventanaExpertos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ventanaExpertos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ventanaExpertos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

                new ventanaExpertos().setVisible(true);
                

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Contar;
    private javax.swing.JLabel EquiquetaPais;
    private javax.swing.JToggleButton FiltrarPorPais;
    private javax.swing.JLabel Label;
    private javax.swing.JToggleButton ListarTodos;
    private javax.swing.JLabel MostrarSexo;
    private javax.swing.JScrollPane Tabla;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTable tExpertos;
    private javax.swing.JTextField txtPais;
    private javax.swing.JTextField txtSexo;
    // End of variables declaration//GEN-END:variables
}
