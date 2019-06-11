/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mantenimientos;

import BD.Conn;
import Class.Ingreso_Mantenimiento;
import Class.Ingreso_Maquinas;
import Class.manto;
import Class.maquinas;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.TableColumn;
import oracle.sql.DATE;

/**
 *
 * @author jluis
 */
public class SeguimientodeManto extends javax.swing.JInternalFrame {

    int codigoid;
    int id;
    int id_mantenimiento;
    int diasrealizacion;
    int cumplimiento;
    int idindicador;

    /**
     * Creates new form Programar
     */
    public SeguimientodeManto() {
        initComponents();
        CODIGO.requestFocus();
        ListaMaquinas();
        FECHAINICIO.setEnabled(false);
        FECHAFIN.setEnabled(false);
    }

    private void calculardias() {
        try {

            Date fecha2 = FECHAINICIO.getDate();
            Date fecha3 = FECHAFIN.getDate();

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(fecha2);
            calendar.setTime(fecha3);
            Date fecha1 = sdf.parse(FECHAIDEAL.getText());
            diasrealizacion = (int) ((fecha3.getTime() - fecha2.getTime()) / 86400000);
            cumplimiento = (int) ((fecha1.getTime() - fecha3.getTime()) / 86400000);
            FECHAPROXIMO.setText(sdf.format(calendar.getTime()));

            System.out.println("Hay dias de cumplimiento " + cumplimiento);
            System.out.println("Hay dias de realizacion " + diasrealizacion);
        } catch (ParseException ex) {
            Logger.getLogger(SeguimientodeManto.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void sumarfecha() {
        int meses = Integer.parseInt(MESES.getText());
        Date fecha = FECHAFIN.getDate();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha);
        calendar.add(Calendar.MONTH, meses);
        FECHAPROXIMO.setText(sdf.format(calendar.getTime()));
    }

    public void idnew() {
        try {
            Connection con = Conn.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select max(id_mantenimiento) from mantenimiento_realizados");
            while (rs.next()) {
                int lastID = rs.getInt(1);
                id = lastID + 1;
            }
            rs.close();
            stmt.close();
            con.close();
        } catch (SQLException error) {
            System.out.print(error + " ERROR QUE OBTIENE EL ULTIMO ID DE INGRESO ");
        }
    }

    public void idindicadores() {
        try {
            Connection con = Conn.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select max(NO_INDICADORES) from indicadores");
            while (rs.next()) {
                int lastID = rs.getInt(1);
                idindicador = lastID + 1;
            }
            rs.close();
            stmt.close();
            con.close();
        } catch (SQLException error) {
            System.out.print(error + " ERROR QUE OBTIENE EL ULTIMO ID DE INGRESO ");
        }
    }

    public void actualizarmantenimiento() {
        try {
            Connection con = Conn.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("update mantenimiento_realizados set estado = 2 where id_mantenimiento =" + id_mantenimiento);
            rs.close();
            stmt.close();
            con.close();
        } catch (SQLException error) {
            System.out.print(error + " ERROR QUE OBTIENE EL ULTIMO ID DE INGRESO ");
        }
    }

    public void limpiar() {
        DESCRIPCION.setText("");
        MODELO.setText("");
        SN.setText("");
        FABRICANTE.setText("");
        DESCRIPCIONMANTENIMIENTO.setText("");
        MESES.setText("");
        FECHAPROXIMO.setText("");
        FECHAINICIO.setDate(null);
        FECHAFIN.setDate(null);
        FECHAIDEAL.setText("");
        id = 0;
        codigoid = 0;
        maquinas.clearSelection();
        CODIGO.requestFocus();
        FECHAINICIO.setEnabled(false);
        FECHAFIN.setEnabled(false);
        id_mantenimiento = 0;
        idindicador = 0;
        codigoid = 0;
        cumplimiento = 0;
        diasrealizacion = 0;
        ListaMaquinas();
    }

    public void insertarindicador() {
        idindicadores();
        try {
            manto m = new manto();
            m.setIdindicador(idindicador);
            m.setCodigo(codigoid);
            m.setId_matenimiento(id_mantenimiento);
            m.setFechainicio(FECHAINICIO.getDate());
            m.setCumplimiento(cumplimiento);
            m.setRealizacion(diasrealizacion);
            Ingreso_Mantenimiento.IngresoIndicador(m);
            calculardias();
            actualizarmantenimiento();
            limpiar();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR DE INSERTAR" + e);
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel11 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        CODIGO = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        DESCRIPCION = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        MODELO = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        SN = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        FABRICANTE = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        FECHAIDEAL = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        DESCRIPCIONMANTENIMIENTO = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        MESES = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        FECHAINICIO = new com.toedter.calendar.JDateChooser();
        jLabel10 = new javax.swing.JLabel();
        FECHAPROXIMO = new javax.swing.JTextField();
        FECHAFIN = new com.toedter.calendar.JDateChooser();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        maquinas = new javax.swing.JTable();

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel11.setText("FECHA DE MANTENIMIENTO");

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("PROGRAMAR MANTENIMIENTO");

        jPanel1.setBackground(new java.awt.Color(153, 204, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("CODIGO");

        CODIGO.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        CODIGO.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                CODIGOKeyReleased(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("DESCRIPCION");

        DESCRIPCION.setEditable(false);
        DESCRIPCION.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        DESCRIPCION.setForeground(new java.awt.Color(0, 102, 255));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("MODELO");

        MODELO.setEditable(false);
        MODELO.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        MODELO.setForeground(new java.awt.Color(0, 102, 255));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("S/N");

        SN.setEditable(false);
        SN.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        SN.setForeground(new java.awt.Color(0, 102, 255));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("FABRICANTE");

        FABRICANTE.setEditable(false);
        FABRICANTE.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        FABRICANTE.setForeground(new java.awt.Color(0, 102, 255));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel12.setText("FECHA DE MANTENIMIENTO");

        FECHAIDEAL.setEditable(false);
        FECHAIDEAL.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        FECHAIDEAL.setForeground(new java.awt.Color(255, 0, 0));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("DESCRIPCION DE MANTENIMIENTO");

        DESCRIPCIONMANTENIMIENTO.setEditable(false);
        DESCRIPCIONMANTENIMIENTO.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        DESCRIPCIONMANTENIMIENTO.setForeground(new java.awt.Color(0, 102, 255));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("FRECUENCIA DE MANTENIMIENTO ");

        MESES.setEditable(false);
        MESES.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        MESES.setForeground(new java.awt.Color(0, 102, 255));

        jLabel9.setText("MESES");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(SN, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 384, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(MODELO, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(DESCRIPCION, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(FECHAIDEAL, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(MESES, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel9))
                            .addComponent(jLabel8))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 1, Short.MAX_VALUE)
                        .addComponent(DESCRIPCIONMANTENIMIENTO, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(FABRICANTE, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(DESCRIPCION, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(MODELO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(SN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addComponent(FABRICANTE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(DESCRIPCIONMANTENIMIENTO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jLabel8))
                .addGap(6, 6, 6)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(FECHAIDEAL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(MESES, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addContainerGap())
        );

        jPanel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel4MouseClicked(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton1.setText("AGREGAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("FECHA INICIO MANTENIMIENTO");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setText("FECHA PROXIMO MANTENIMIENTO");

        FECHAPROXIMO.setEditable(false);
        FECHAPROXIMO.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        FECHAPROXIMO.setForeground(new java.awt.Color(0, 102, 255));
        FECHAPROXIMO.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                FECHAPROXIMOMouseClicked(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel13.setText("FECHA FIN MANTENIMIENTO");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(FECHAPROXIMO, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addGap(39, 39, 39))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(FECHAINICIO, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(FECHAFIN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(15, 15, 15))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(FECHAINICIO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(FECHAFIN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(FECHAPROXIMO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34))
        );

        maquinas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CODIGO", "DESCRIPCION", "FECHA MANTE"
            }
        ));
        maquinas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                maquinasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(maquinas);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 546, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CODIGO, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CODIGO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1))
                .addGap(17, 17, 17))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CODIGOKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CODIGOKeyReleased

        ListaMaquinas();

    }//GEN-LAST:event_CODIGOKeyReleased

    private void maquinasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_maquinasMouseClicked
        FECHAINICIO.setEnabled(true);
        FECHAFIN.setEnabled(true);
        try {
            manto p = Ingreso_Mantenimiento.buscarMaquinamanto(Integer.parseInt(String.valueOf(maquinas.getModel().getValueAt(maquinas.getSelectedRow(), 0))));
            codigoid = p.getCodigo();
            DESCRIPCION.setText(p.getDescripcion());
            MODELO.setText(p.getModelo());
            SN.setText(p.getSerie());
            FABRICANTE.setText(p.getFabricante());
            DESCRIPCIONMANTENIMIENTO.setText(p.getDescripcionmanto());
            MESES.setText(String.valueOf(p.getFrecuencia()));
            FECHAIDEAL.setText(p.getFechaproximo());
            id_mantenimiento = p.getId_matenimiento();
        } catch (Exception e) {
            System.out.println("Error de Seleccion:" + e);
        }
    }//GEN-LAST:event_maquinasMouseClicked

    private void FECHAPROXIMOMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_FECHAPROXIMOMouseClicked
        if (FECHAINICIO.getDate() != null) {
            sumarfecha();
        } else {
            JOptionPane.showMessageDialog(null, "LLENAR FECHA DE FINAL DE MANTENIMIENTO");
        }
    }//GEN-LAST:event_FECHAPROXIMOMouseClicked

    private void jPanel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel4MouseClicked
        if (FECHAINICIO.getDate() != null) {
            sumarfecha();
        } else {
            JOptionPane.showMessageDialog(null, "LLENAR FECHA DE FINAL DE MANTENIMIENTO");
        }
    }//GEN-LAST:event_jPanel4MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        idnew();
        if (codigoid != 0 && id != 0 && id_mantenimiento != 0 && FECHAIDEAL.getText().compareTo("") != 0 && FECHAINICIO.getDate() != null && FECHAIDEAL.getText().compareTo("") != 0 && FECHAPROXIMO.getText().compareTo("") != 0) {

            try {
                manto m = new manto();
                m.setCodigo(codigoid);
                m.setId_matenimiento(id);
                m.setFechaIdeal(FECHAIDEAL.getText());
                m.setFechainicio(FECHAINICIO.getDate());
                m.setFechafin(FECHAFIN.getDate());
                m.setFechaproximo(FECHAPROXIMO.getText());
                Ingreso_Mantenimiento.IngresoManto2(m);
                calculardias();
                insertarindicador();
                JOptionPane.showMessageDialog(null, "Ingreso Agregado...");
                actualizarmantenimiento();
                limpiar();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "ERROR DE INSERTAR" + e);
            }

        } else {
            JOptionPane.showMessageDialog(null, "LLene Los Campos necesario");
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void ListaMaquinas() {
        ArrayList<maquinas> result1 = Ingreso_Mantenimiento.ListarMantoSeguimiento(CODIGO.getText());
        recargarTable(result1);
    }

    public void recargarTable(ArrayList<maquinas> list) {
        Object[][] datos1 = new Object[list.size()][3];
        int i = 0;
        for (maquinas p : list) {
            datos1[i][0] = p.getNO();
            datos1[i][1] = p.getDESCRIPCION();
            datos1[i][2] = p.getFRECUENCIAMANT();
            i++;
        }
        maquinas.setModel(new javax.swing.table.DefaultTableModel(
                datos1,
                new String[]{
                    "CODIGO", "DESCRIPCION", "FECHA MANTE"
                }) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });

        TableColumn columna1 = maquinas.getColumn("CODIGO");
        columna1.setPreferredWidth(0);
        TableColumn columna2 = maquinas.getColumn("DESCRIPCION");
        columna2.setPreferredWidth(350);
    }

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
            java.util.logging.Logger.getLogger(SeguimientodeManto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SeguimientodeManto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SeguimientodeManto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SeguimientodeManto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SeguimientodeManto().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField CODIGO;
    private javax.swing.JTextField DESCRIPCION;
    private javax.swing.JTextField DESCRIPCIONMANTENIMIENTO;
    private javax.swing.JTextField FABRICANTE;
    private com.toedter.calendar.JDateChooser FECHAFIN;
    private javax.swing.JTextField FECHAIDEAL;
    private com.toedter.calendar.JDateChooser FECHAINICIO;
    private javax.swing.JTextField FECHAPROXIMO;
    private javax.swing.JTextField MESES;
    private javax.swing.JTextField MODELO;
    private javax.swing.JTextField SN;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable maquinas;
    // End of variables declaration//GEN-END:variables
}
