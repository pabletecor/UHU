/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aplicacion;

import Persistencia.colabora;
import Persistencia.caso;
import Persistencia.experto;
import Persistencia.conexionOracle;
import Persistencia.manejaColabora;
import Persistencia.manejaCaso;
import Persistencia.manejaExperto;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.showConfirmDialog;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ignacio
 */
public class VentanaGCompleta extends javax.swing.JFrame {

    /**
     * Creates new form GestionCompleta
     */
    
    private conexionOracle conexion = null;
    private manejaColabora mCol;
    private manejaCaso mCas;
    private manejaExperto mEx;
    private DefaultTableModel modeloTCaso = new DefaultTableModel();
    private DefaultTableModel modeloTExperto = new DefaultTableModel();
    private DefaultTableModel modeloTColabora = new DefaultTableModel();  
    private PreparedStatement ps = null;
    
    public VentanaGCompleta() {
       initComponents();
    }
    
    
    public VentanaGCompleta(conexionOracle c)throws SQLException{
        conexion = c;
        mCol = new manejaColabora(c);
        mCas = new manejaCaso(c);
        mEx = new manejaExperto(c);
        initComponents();
        dibujarTablaExperto();
        dibujarTablaCaso();
        dibujarTablaColabora();
        //pideExpertos();
       //pideColaboraciones();
       // pideCasos();
       setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    
    private void dibujarTablaExperto(){
        jTablaExpertos.setModel(modeloTExperto);
        String[] columna = {"Código", "Nombre", "País", "Sexo", "Especialidad"};
        modeloTExperto.setColumnIdentifiers(columna);
        jTablaExpertos.getColumnModel().getColumn(0).setPreferredWidth(25);
        jTablaExpertos.getColumnModel().getColumn(1).setPreferredWidth(135);
        jTablaExpertos.getColumnModel().getColumn(2).setPreferredWidth(80);
        jTablaExpertos.getColumnModel().getColumn(3).setPreferredWidth(10);
        jTablaExpertos.getColumnModel().getColumn(4).setPreferredWidth(125);
    }
    
    private void dibujarTablaCaso(){
        
        jTablaCasos.setModel(modeloTCaso);
        String[] columna = {"Código", "Nombre", "Fecha de Incio", "Fecha Final"};
        modeloTCaso.setColumnIdentifiers(columna);
        jTablaCasos.getColumnModel().getColumn(0).setPreferredWidth(25);
        jTablaCasos.getColumnModel().getColumn(1).setPreferredWidth(135);
        jTablaCasos.getColumnModel().getColumn(2).setPreferredWidth(80);
        jTablaCasos.getColumnModel().getColumn(3).setPreferredWidth(10);
    }
    
    private void dibujarTablaColabora(){
        
        jTablaColabora.setModel(modeloTColabora);
        String[] columna = {"Experto", "Caso", "Fecha", "Descripción"};
        modeloTColabora.setColumnIdentifiers(columna);
        jTablaColabora.getColumnModel().getColumn(0).setPreferredWidth(25);
        jTablaColabora.getColumnModel().getColumn(1).setPreferredWidth(135);
        jTablaColabora.getColumnModel().getColumn(2).setPreferredWidth(80);
        jTablaColabora.getColumnModel().getColumn(3).setPreferredWidth(10);
    }
    
    public void rellenaTablaColabo(ArrayList<colabora> colaboras){
        
        Object[] columna = new Object[4];
        for (int i = 0; i < colaboras.size(); i++) {
            columna[0] = colaboras.get(i).getCodExperto();
            columna[1] = colaboras.get(i).getCodCaso();
            columna[2] = colaboras.get(i).getFecha();
            columna[3] = colaboras.get(i).getDescripcionColaboracion();
            modeloTColabora.addRow(columna);
        }
    }
    
    public void rellenaTablaExperto(ArrayList<experto> expertos){
        
        Object[] columna = new Object[5];
        for (int i = 0; i < expertos.size(); i++) {
            columna[0] = expertos.get(i).getCodExperto();
            columna[1] = expertos.get(i).getNombre();
            columna[2] = expertos.get(i).getPais();
            columna[3] = expertos.get(i).getSexo();
            columna[4] = expertos.get(i).getEspecialidad();
            modeloTExperto.addRow(columna);
        }
    }

    public void rellenaTablaCaso(ArrayList<caso> casos){
        
        Object[] columna = new Object[4];
        for (int i = 0; i < casos.size(); i++) {
            columna[0] = casos.get(i).getCodCaso();
            columna[1] = casos.get(i).getNombre();
            columna[2] = casos.get(i).getFechaInicio();
            columna[3] = casos.get(i).getFechaFin();
            modeloTCaso.addRow(columna);
        }
    }
    
    public void pideColaboraciones() throws SQLException{
        ArrayList<colabora> lCol = mCol.listaColabora();
        rellenaTablaColabo(lCol);
    }
    
    public void pideCasos() throws SQLException{
        ArrayList<caso> lCas = mCas.listaCasos();
        rellenaTablaCaso(lCas);
    }

    public void pideExpertos() throws SQLException{
        ArrayList<experto> lExp = mEx.listaExpertos();
        rellenaTablaExperto(lExp);
    }
    private void vaciarTablaExperto(){
        
        while(modeloTExperto.getRowCount() > 0)
            modeloTExperto.removeRow(0);
    }
    
     private void vaciarTablaColabo(){
         
        while(modeloTColabora.getRowCount() > 0)
            modeloTColabora.removeRow(0);
    }
     
      private void vaciarTablaCaso(){
          
        while(modeloTCaso.getRowCount() > 0)
            modeloTCaso.removeRow(0);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFrame1 = new javax.swing.JFrame();
        jL11 = new javax.swing.JLabel();
        jL12 = new javax.swing.JLabel();
        jL14 = new javax.swing.JLabel();
        jL13 = new javax.swing.JLabel();
        jL15 = new javax.swing.JLabel();
        jT11 = new javax.swing.JTextField();
        jT15 = new javax.swing.JTextField();
        jT14 = new javax.swing.JTextField();
        jT13 = new javax.swing.JTextField();
        jT12 = new javax.swing.JTextField();
        jBotInsertarExp = new javax.swing.JButton();
        jLT1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTablaCasos = new javax.swing.JTable();
        JL1 = new javax.swing.JLabel();
        jLT2 = new javax.swing.JLabel();
        jL21 = new javax.swing.JLabel();
        jL22 = new javax.swing.JLabel();
        jL23 = new javax.swing.JLabel();
        jL24 = new javax.swing.JLabel();
        jT22 = new javax.swing.JTextField();
        jT21 = new javax.swing.JTextField();
        jBotInsertarCaso = new javax.swing.JButton();
        jBotListarTodo = new javax.swing.JButton();
        jBotLimpiarTodo = new javax.swing.JButton();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jBotEliminarExperto = new javax.swing.JButton();
        jBotEliminarCaso = new javax.swing.JButton();
        jBotEliminarColabora = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jL31 = new javax.swing.JLabel();
        jT31 = new javax.swing.JTextField();
        jT32 = new javax.swing.JTextField();
        jDateChooser3 = new com.toedter.calendar.JDateChooser();
        jT34 = new javax.swing.JTextField();
        jL32 = new javax.swing.JLabel();
        jL33 = new javax.swing.JLabel();
        jL34 = new javax.swing.JLabel();
        jBotInsertarColabo = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTablaExpertos = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTablaColabora = new javax.swing.JTable();

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jL11.setText("Código");

        jL12.setText("Nombre");

        jL14.setText("Sexo");

        jL13.setText("País");

        jL15.setText("Especialidad");

        jT15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jT15ActionPerformed(evt);
            }
        });

        jBotInsertarExp.setText("Insertar Experto");
        jBotInsertarExp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBotInsertarExpActionPerformed(evt);
            }
        });

        jLT1.setText("Listado de Expertos");

        jTablaCasos.setModel(new javax.swing.table.DefaultTableModel(
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
        jTablaCasos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTablaCasosMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTablaCasos);

        JL1.setText("GESTIÓN COMPLETA DE LA BD");

        jLT2.setText("Listado de Casos Policiales");

        jL21.setText("Codigo");

        jL22.setText("Nombre");

        jL23.setText("Fecha de Inicio");

        jL24.setText("Fecha Final");

        jBotInsertarCaso.setText("Insertar Caso Policial");
        jBotInsertarCaso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBotInsertarCasoActionPerformed(evt);
            }
        });

        jBotListarTodo.setText("Listar Todo");
        jBotListarTodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBotListarTodoActionPerformed(evt);
            }
        });

        jBotLimpiarTodo.setText("Limpiar Todo");
        jBotLimpiarTodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBotLimpiarTodoActionPerformed(evt);
            }
        });

        jBotEliminarExperto.setText("Eliminar Experto");
        jBotEliminarExperto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBotEliminarExpertoActionPerformed(evt);
            }
        });

        jBotEliminarCaso.setText("Eliminar Caso");
        jBotEliminarCaso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBotEliminarCasoActionPerformed(evt);
            }
        });

        jBotEliminarColabora.setText("Eliminar Colaboracion");
        jBotEliminarColabora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBotEliminarColaboraActionPerformed(evt);
            }
        });

        jLabel1.setText("Listado de Colaboraciones");

        jL31.setText("Codigo del Experto");

        jL32.setText("Codigo del Caso");

        jL33.setText("Fecha de Incorporacion");

        jL34.setText("Descripcion");

        jBotInsertarColabo.setText("Insertar Colaboración");
        jBotInsertarColabo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBotInsertarColaboActionPerformed(evt);
            }
        });

        jTablaExpertos.setModel(new javax.swing.table.DefaultTableModel(
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
        jTablaExpertos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTablaExpertosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTablaExpertos);

        jTablaColabora.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(jTablaColabora);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(429, 429, 429)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jL22)
                                        .addComponent(jL21))
                                    .addGap(50, 50, 50))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jL24)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jL23)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(150, 150, 150))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jT21, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jBotInsertarCaso))
                                    .addComponent(jT22, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jBotLimpiarTodo, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jBotListarTodo, javax.swing.GroupLayout.Alignment.TRAILING)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jL32)
                                    .addComponent(jL31)
                                    .addComponent(jL34)
                                    .addComponent(jL33))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jDateChooser3, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(jT32, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jT31, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jBotInsertarColabo))
                                    .addComponent(jT34, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane3)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jBotEliminarColabora)
                                        .addGap(0, 0, Short.MAX_VALUE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jL11, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jL12)
                                            .addComponent(jL13)
                                            .addComponent(jL14))
                                        .addGap(31, 31, 31)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jT12)
                                            .addComponent(jT13)
                                            .addComponent(jT14, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jT11, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jBotInsertarExp, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jL15)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jT15, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 503, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jBotEliminarExperto, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 525, Short.MAX_VALUE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(jBotEliminarCaso)))))))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(175, 175, 175)
                .addComponent(jLT1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLT2)
                .addGap(160, 160, 160))
            .addGroup(layout.createSequentialGroup()
                .addGap(439, 439, 439)
                .addComponent(JL1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(226, 226, 226))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(JL1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jL21)
                            .addComponent(jT21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jBotInsertarCaso)
                            .addComponent(jBotListarTodo))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jL22, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jT22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jBotLimpiarTodo)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jL23)
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jL24))
                        .addGap(73, 73, 73)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLT1)
                            .addComponent(jLT2))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jBotEliminarExperto))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jBotEliminarCaso))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jT11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jL11)
                            .addComponent(jBotInsertarExp))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jT12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jL12))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jT13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jL13))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jT14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jL14))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jL15)
                            .addComponent(jT15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jT31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jL31, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jBotInsertarColabo))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jT32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jL32))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jDateChooser3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(7, 7, 7))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jL33)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jL34)
                            .addComponent(jT34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBotEliminarColabora)
                .addContainerGap(333, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jT15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jT15ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jT15ActionPerformed

    private void jBotInsertarExpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBotInsertarExpActionPerformed
        try{
            experto ex = new experto( jT11.getText(), jT12.getText(), jT13.getText(), jT14.getText(), jT15.getText());
            mEx.insertaExperto(ex);
            vaciarTablaExperto();
            pideExpertos();
        }catch(SQLException e){
            Logger.getLogger(VentanaGCompleta.class.getName()).log(Level.SEVERE, null, e);
        }
        
    }//GEN-LAST:event_jBotInsertarExpActionPerformed

    private void jBotLimpiarTodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBotLimpiarTodoActionPerformed
        vaciarTablaCaso();
        vaciarTablaColabo();
        vaciarTablaExperto();
    }//GEN-LAST:event_jBotLimpiarTodoActionPerformed

    private void jBotListarTodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBotListarTodoActionPerformed
        try{
            vaciarTablaExperto();
            pideExpertos();
            vaciarTablaCaso();
            pideCasos();
            vaciarTablaColabo();
            pideColaboraciones();
        }catch(SQLException e){
            Logger.getLogger(VentanaGCompleta.class.getName()).log(Level.SEVERE, null, e);
        }
            
    }//GEN-LAST:event_jBotListarTodoActionPerformed

    private void jBotEliminarExpertoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBotEliminarExpertoActionPerformed
        String cod_Exp = jTablaExpertos.getValueAt(jTablaExpertos.getSelectedRow(), 0).toString();
        int confirmacion = showConfirmDialog(null, "¿Seguro que quieres eliminar ese experto?", null, JOptionPane.YES_NO_OPTION); 
        if(confirmacion == 0)
        {
            try {
            ps = conexion.conn.prepareStatement("delete from EXPERTO where codExperto = ?");
            ps.setString(1, cod_Exp);
            ps.executeUpdate(); //como no es una consulta, simplemente se actualiza
            ps.close();
            vaciarTablaExperto();
            pideExpertos();
        } catch (SQLException ex) {
            System.out.println("Error al eliminar Experto");
            System.out.println(ex.getMessage());
            System.out.println(ex.getSQLState());
            System.out.println(ex.getErrorCode());

        }
        }
    }//GEN-LAST:event_jBotEliminarExpertoActionPerformed

    private void jBotEliminarCasoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBotEliminarCasoActionPerformed
        String cod_Cas = jTablaCasos.getValueAt(jTablaCasos.getSelectedRow(), 0).toString();
        int confirmacion = showConfirmDialog(null, "Seguro que quieres eliminar este caso?", null, JOptionPane.YES_NO_OPTION);
        if(confirmacion == 0){
            try{
                ps = conexion.conn.prepareStatement("delete from CASO_POLICIAL where codCaso = ?");
                ps.setString(1, cod_Cas);
                ps.executeUpdate();
                ps.close();
                vaciarTablaCaso();
                pideCasos();
            }catch(SQLException ex){
                System.out.println("Error al eliminar Caso");
                System.out.println(ex.getMessage());
                System.out.println(ex.getSQLState());
                System.out.println(ex.getErrorCode());
            }
        }
    }//GEN-LAST:event_jBotEliminarCasoActionPerformed

    private void jBotEliminarColaboraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBotEliminarColaboraActionPerformed
        String cod_Exp = jTablaColabora.getValueAt(jTablaColabora.getSelectedRow(), 0).toString();
        String cod_Cas = jTablaColabora.getValueAt(jTablaColabora.getSelectedRow(), 1).toString();
        //String fech = jTablaColabora.getValueAt(jTablaColabora.getSelectedRow(), 2).toString();
        int confirmacion = showConfirmDialog(null, "Seguro que quieres eliminar esta colaboración?", null, JOptionPane.YES_NO_OPTION);
        if(confirmacion == 0){
            try{
                ps = conexion.conn.prepareStatement("delete from COLABORA where codExperto = ? AND codCaso = ?");
                ps.setString(1, cod_Exp);
                ps.setString(2, cod_Cas);
               // ps.setString(3, fech);
                ps.executeUpdate();
                ps.close();
                vaciarTablaColabo();
                pideColaboraciones();
            }catch(SQLException ex){
                System.out.println("Error al eliminar Colaboracion");
                System.out.println(ex.getMessage());
                System.out.println(ex.getSQLState());
                System.out.println(ex.getErrorCode());
            }
        }
    }//GEN-LAST:event_jBotEliminarColaboraActionPerformed

    private void jBotInsertarColaboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBotInsertarColaboActionPerformed
         try {
            DateFormat d = new SimpleDateFormat("dd/MM/yyyy");
            String fech = d.format(jDateChooser3.getDate());
            colabora col = new colabora(jT31.getText(), jT32.getText(), fech, jT34.getText());
            mCol.insertaColaboracion(col);
            vaciarTablaColabo();
            pideColaboraciones();
        }catch(SQLException e){
            Logger.getLogger(VentanaGCompleta.class.getName()).log(Level.SEVERE, null, e);
        }
    }//GEN-LAST:event_jBotInsertarColaboActionPerformed

    private void jTablaExpertosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTablaExpertosMouseClicked
        int fila = jTablaExpertos.getSelectedRow();
        String cod = jTablaExpertos.getValueAt(fila, 0).toString();
        jT31.setText(cod);
    }//GEN-LAST:event_jTablaExpertosMouseClicked

    private void jTablaCasosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTablaCasosMouseClicked
        int fila = jTablaCasos.getSelectedRow();
        String cod = jTablaCasos.getValueAt(fila, 0).toString();
        jT32.setText(cod);
    }//GEN-LAST:event_jTablaCasosMouseClicked

    private void jBotInsertarCasoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBotInsertarCasoActionPerformed
        try {
            if(jDateChooser2.getDate() == null){
                DateFormat d = new SimpleDateFormat("dd/MM/yyyy");
                String fi = d.format(jDateChooser1.getDate());
                caso c = new caso(jT21.getText(), jT22.getText(), fi, null);
                mCas.insertaCaso(c);
                vaciarTablaCaso();
                pideCasos();
            }
            else {
                DateFormat d = new SimpleDateFormat("dd/MM/yyyy");
                String fi = d.format(jDateChooser1.getDate());
                String ff = d.format(jDateChooser2.getDate());
                if(jDateChooser1.getDate().compareTo(jDateChooser2.getDate())<0)
                {
                    caso c = new caso(jT21.getText(), jT22.getText(), fi, ff);
                    mCas.insertaCaso(c);
                    vaciarTablaCaso();
                    pideCasos();
                }
            }
            
        }catch (SQLException ex){
            Logger.getLogger(VentanaGCompleta.class.getName()).log(Level.SEVERE, null, ex);
        }
        /*
        try {
            if(vFechaFinCaso.getDate() == null)
            {
                DateFormat d = new SimpleDateFormat("dd/MM/yyyy");
                String fi = d.format(vFechaIniCaso.getDate());
                caso c = new caso(vCodigoCaso.getText(), vNombreCaso.getText(), fi, null);
                cas.insertaCaso(c);
                vaciarTablaCaso();
                ArrayList<caso> casos = cas.listarCasos();
                rellenaTablaCaso(casos);
                        
            }
            else
            {
                DateFormat d = new SimpleDateFormat("dd/MM/yyyy");
                String fi = d.format(vFechaIniCaso.getDate());
                String ff = d.format(vFechaFinCaso.getDate());
                if(vFechaIniCaso.getDate().compareTo(vFechaFinCaso.getDate())<0)
                {
                    caso c = new caso(vCodigoCaso.getText(), vNombreCaso.getText(), fi, ff);
                    cas.insertaCaso(c);
                    vaciarTablaCaso();
                    ArrayList<caso> casos = cas.listarCasos();
                    rellenaTablaCaso(casos);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(VentanaGestionCompleta.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        */
    }//GEN-LAST:event_jBotInsertarCasoActionPerformed

    
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
            java.util.logging.Logger.getLogger(VentanaGCompleta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaGCompleta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaGCompleta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaGCompleta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaGCompleta().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel JL1;
    private javax.swing.JButton jBotEliminarCaso;
    private javax.swing.JButton jBotEliminarColabora;
    private javax.swing.JButton jBotEliminarExperto;
    private javax.swing.JButton jBotInsertarCaso;
    private javax.swing.JButton jBotInsertarColabo;
    private javax.swing.JButton jBotInsertarExp;
    private javax.swing.JButton jBotLimpiarTodo;
    private javax.swing.JButton jBotListarTodo;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private com.toedter.calendar.JDateChooser jDateChooser3;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JLabel jL11;
    private javax.swing.JLabel jL12;
    private javax.swing.JLabel jL13;
    private javax.swing.JLabel jL14;
    private javax.swing.JLabel jL15;
    private javax.swing.JLabel jL21;
    private javax.swing.JLabel jL22;
    private javax.swing.JLabel jL23;
    private javax.swing.JLabel jL24;
    private javax.swing.JLabel jL31;
    private javax.swing.JLabel jL32;
    private javax.swing.JLabel jL33;
    private javax.swing.JLabel jL34;
    private javax.swing.JLabel jLT1;
    private javax.swing.JLabel jLT2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField jT11;
    private javax.swing.JTextField jT12;
    private javax.swing.JTextField jT13;
    private javax.swing.JTextField jT14;
    private javax.swing.JTextField jT15;
    private javax.swing.JTextField jT21;
    private javax.swing.JTextField jT22;
    private javax.swing.JTextField jT31;
    private javax.swing.JTextField jT32;
    private javax.swing.JTextField jT34;
    private javax.swing.JTable jTablaCasos;
    private javax.swing.JTable jTablaColabora;
    private javax.swing.JTable jTablaExpertos;
    // End of variables declaration//GEN-END:variables
}
