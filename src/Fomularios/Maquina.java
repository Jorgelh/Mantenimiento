/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Fomularios;

import BD.Conn;
import Class.*;
import java.awt.Dimension;
import static Fomularios.MenuPrincipal.Pane1;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author jluis
 */
public class Maquina extends javax.swing.JInternalFrame {
     int no;
     int descrip;
     int descripmante;
     int depto;
     int mante;
     int verifi;
     int funcionamiento;
    /**
     * Creates new form Maquina
     */
    public Maquina() {
        
        initComponents();
        DESCRIPCION.requestFocus();
        FREC_MANTENIMIENTO.setEnabled(false);
        FREC_VERIFICACION.setEnabled(false);
        DESCRI_MANTE.setEnabled(false);
         codigonew();
         llenarcombodescripcion();
         llenarcombodescripcionmante();
         
       // ((JComponent) evt.getSource()).transferFocus();
      
    }
    
    public static void llenarcombodescripcion(){
     DESCRIPCION.removeAllItems();
     DESCRIPCION.addItem("SELECCIONAR...");
     DESCRIPCION.setSelectedItem("SELECCIONAR...");
    try {
            Connection con = Conn.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select descripcion from descripcion order by descripcion");
            while (rs.next()) {
                DESCRIPCION.addItem((String) rs.getObject(1));
            }
            rs.close();
            stmt.close();
            con.close();
        } catch (SQLException error) {
            System.out.println(error);
        }
     
    }
    
     public static void llenarcombodescripcionmante(){
         DESCRI_MANTE.removeAllItems();
    try { 
            Connection con = Conn.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select descripcion from tipo_mante order by descripcion");
            while (rs.next()) {

                DESCRI_MANTE.addItem((String) rs.getObject(1));
        
            }
            rs.close();
            stmt.close();
            con.close();
        } catch (SQLException error) {
            System.out.println(error);
        }
    DESCRI_MANTE.setSelectedItem("SELECCIONAR...");
    }
    
    public void iddescripcion(){}
    public void iddescripcionmante(){}
    
    public void codigonew()
     {
         try {
            Connection con = Conn.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select max(no) from maquinaria");
            while (rs.next()) {
                int lastID = rs.getInt(1);
                no=lastID;
            }
            rs.close();
            stmt.close();
            con.close();
        } catch (SQLException error) {
            System.out.print(error+" ERROR QUE OBTIENE EL ULTIMO ID DE INGRESO ");
        }
             NO.setText(String.valueOf(no+1));
     }
    
    public void limpiar(){
        codigonew();
        DESCRIPCION.setSelectedItem("SELECCIONAR...");
        Fabricante.setText("");
        Modelo.setText("");
        NoSerie.setText("");
        DEPTO.setSelectedItem("SELECCIONAR...");
        Mante.setSelectedItem("NO");
        FREC_MANTENIMIENTO.setText("");
        DESCRI_MANTE.setSelectedItem("SELECCIONAR...");
        Verifi.setSelectedItem("NO");
        FREC_VERIFICACION.setText("");
        funciona.setSelectedItem("SI");
    }
    
    public void insertar1(){
       
        try {
            maquinas m = new maquinas();
            m.setNO(Integer.parseInt(NO.getText()));
            m.setDESCRIP(descrip);
            m.setFABRICANTE(Fabricante.getText());
            m.setMODELO(Modelo.getText());
            m.setSERIE(NoSerie.getText());
            m.setDEPTO(depto);
            m.setMANTE(Mante.getSelectedItem().toString());
            m.setFRECUENCIAMANT(FREC_MANTENIMIENTO.getText());
            m.setDESCRIP_MANT(descripmante);
            m.setVERIFI(Verifi.getSelectedItem().toString());
            m.setFRECUENCIAVERIFI(FREC_VERIFICACION.getText());
            m.setFuncionamiento(funcionamiento);
            Ingreso_Maquinas.NuevaMAquina(m);
            JOptionPane.showMessageDialog(null, "Ingreso Agregado...");
            limpiar();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR DE INSERTAR"+e);
        }
        
        System.out.println("test " + descripmante);
    }
    public void insertar2(){
        
       
    
        try {
            maquinas m = new maquinas();
            m.setNO(Integer.parseInt(NO.getText()));
            m.setDESCRIP(descrip);
            m.setFABRICANTE(Fabricante.getText());
            m.setMODELO(Modelo.getText());
            m.setSERIE(NoSerie.getText());
            m.setDEPTO(depto);
            m.setMANTE(Mante.getSelectedItem().toString());
            m.setFRECUENCIAMANT(FREC_MANTENIMIENTO.getText());
            m.setDESCRIP_MANT(1);
            m.setVERIFI(Verifi.getSelectedItem().toString());
            m.setFRECUENCIAVERIFI(FREC_VERIFICACION.getText());
            m.setFuncionamiento(funcionamiento);
            Ingreso_Maquinas.NuevaMAquina(m);
            JOptionPane.showMessageDialog(null, "Ingreso Agregado...");
            limpiar();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR DE INSERTAR"+e);
        }
        
        
    }
    
    private void sirveono(){
    if(funciona.getSelectedItem() == "SI"){funcionamiento = 1;}
        else if(funciona.getSelectedItem() == "NO"){funcionamiento = 2;}
        else if(funciona.getSelectedItem() == "FUERA DE SERVICIO"){funcionamiento = 3;}
    }
    
    private void verifica(){
        
        if (Verifi.getSelectedItem().toString().equalsIgnoreCase("SI")){
            FREC_VERIFICACION.setEnabled(true);FREC_VERIFICACION.requestFocus();
        }else{FREC_VERIFICACION.setEnabled(false); Guargar.requestFocus();}
    
    }
    
    private void manteni(){
        if (Mante.getSelectedItem().toString().equalsIgnoreCase("SI")){
            FREC_MANTENIMIENTO.setEnabled(true);FREC_MANTENIMIENTO.requestFocus();DESCRI_MANTE.setEnabled(true);
        }else{FREC_MANTENIMIENTO.setEnabled(false);DESCRI_MANTE.setEnabled(false); DESCRI_MANTE.requestFocus();
          DESCRI_MANTE.setSelectedItem("SELECCIONAR...");FREC_MANTENIMIENTO.setText("");
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

        jComboBox2 = new javax.swing.JComboBox<>();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        NO = new javax.swing.JTextField();
        Fabricante = new javax.swing.JTextField();
        Modelo = new javax.swing.JTextField();
        NoSerie = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        DEPTO = new javax.swing.JComboBox<>();
        DESCRIPCION = new javax.swing.JComboBox<>();
        MASDESCRIP = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        Mante = new javax.swing.JComboBox<>();
        FREC_MANTENIMIENTO = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        DESCRI_MANTE = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        Verifi = new javax.swing.JComboBox<>();
        FREC_VERIFICACION = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        MASMANTE = new javax.swing.JButton();
        Guargar = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        funciona = new javax.swing.JComboBox<>();

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("INGRESO NUEVA MAQUINA");

        jPanel1.setBackground(new java.awt.Color(153, 204, 255));

        jLabel1.setFont(new java.awt.Font("Eras Bold ITC", 0, 18)); // NOI18N
        jLabel1.setText("REGISTRO DE MAQUINARIA");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("NO STOCK");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("DESCRIPCION");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("FABRICANTE ");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("MODELO");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("NO SERIE");

        NO.setEditable(false);
        NO.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        NO.setForeground(new java.awt.Color(255, 0, 0));
        NO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NOActionPerformed(evt);
            }
        });

        Fabricante.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Fabricante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FabricanteActionPerformed(evt);
            }
        });

        Modelo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Modelo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ModeloActionPerformed(evt);
            }
        });

        NoSerie.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        NoSerie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NoSerieActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel14.setText("DEPARTAMENTO");

        DEPTO.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        DEPTO.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECCIONAR...", "INSPECCION", "TESTING", "CHIPS", "STRIP Y POTTING", "TRANSFORMADORES", "TALLER", "BODEGA" }));
        DEPTO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DEPTOActionPerformed(evt);
            }
        });

        DESCRIPCION.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        DESCRIPCION.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECCIONAR..." }));
        DESCRIPCION.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DESCRIPCIONActionPerformed(evt);
            }
        });

        MASDESCRIP.setText("+");
        MASDESCRIP.setMargin(new java.awt.Insets(1, 1, 1, 1));
        MASDESCRIP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MASDESCRIPActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(DESCRIPCION, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(MASDESCRIP))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(DEPTO, 0, 329, Short.MAX_VALUE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(NO, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(Modelo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 329, Short.MAX_VALUE)
                        .addComponent(Fabricante, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(NoSerie, javax.swing.GroupLayout.Alignment.LEADING)))
                .addGap(0, 10, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(NO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(DESCRIPCION, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(MASDESCRIP))
                .addGap(10, 10, 10)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Fabricante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Modelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(NoSerie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(DEPTO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("MANTENIMIENTO");

        Mante.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Mante.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "NO", "SI" }));
        Mante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ManteActionPerformed(evt);
            }
        });

        FREC_MANTENIMIENTO.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        FREC_MANTENIMIENTO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FREC_MANTENIMIENTOActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setText("FRECUENCIA MANTENIMIENTO");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel11.setText("MESES");

        DESCRI_MANTE.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        DESCRI_MANTE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DESCRI_MANTEActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setText("DESCRIPION DE MANTENIMIENTO");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("VERIFICACION");

        Verifi.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Verifi.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "NO", "SI" }));
        Verifi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VerifiActionPerformed(evt);
            }
        });

        FREC_VERIFICACION.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        FREC_VERIFICACION.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FREC_VERIFICACIONActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setText("FRECUENCIA VERIFICACION");

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel13.setText("MESES");

        MASMANTE.setText("+");
        MASMANTE.setMargin(new java.awt.Insets(1, 1, 1, 1));
        MASMANTE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MASMANTEActionPerformed(evt);
            }
        });

        Guargar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Guargar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/save2.png"))); // NOI18N
        Guargar.setText("GUARDAR");
        Guargar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GuargarActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel15.setText("FUNCIONAMIENTO");

        funciona.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        funciona.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SI", "NO", "FUERA DE SERVICIO" }));
        funciona.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                funcionaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                            .addComponent(Mante, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(28, 28, 28)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel10)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addComponent(FREC_MANTENIMIENTO, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jLabel11))))
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(DESCRI_MANTE, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(MASMANTE))
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(Verifi, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel7))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel12)
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                            .addComponent(FREC_VERIFICACION, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(jLabel13))))
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addGap(0, 0, Short.MAX_VALUE)
                                    .addComponent(Guargar)
                                    .addGap(91, 91, 91)))
                            .addGap(15, 15, 15)))
                    .addComponent(jLabel8)
                    .addComponent(jLabel15)
                    .addComponent(funciona, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel10))
                .addGap(7, 7, 7)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Mante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(FREC_MANTENIMIENTO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addGap(21, 21, 21)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(DESCRI_MANTE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(MASMANTE))
                .addGap(21, 21, 21)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(FREC_VERIFICACION, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(Verifi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(funciona, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Guargar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(224, 224, 224)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void NOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NOActionPerformed

    }//GEN-LAST:event_NOActionPerformed

    private void FabricanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FabricanteActionPerformed
       Modelo.requestFocus();
    }//GEN-LAST:event_FabricanteActionPerformed

    private void ModeloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ModeloActionPerformed
        NoSerie.requestFocus();
    }//GEN-LAST:event_ModeloActionPerformed

    private void NoSerieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NoSerieActionPerformed
        DEPTO.requestFocus();
    }//GEN-LAST:event_NoSerieActionPerformed

    private void ManteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ManteActionPerformed
        manteni();
    }//GEN-LAST:event_ManteActionPerformed

    private void VerifiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VerifiActionPerformed
       verifica();
    }//GEN-LAST:event_VerifiActionPerformed

    private void GuargarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GuargarActionPerformed
         sirveono();
         manteni();
         verifica();
       
        if(Mante.getSelectedItem().toString().equalsIgnoreCase("SI")){
            
          
            if(NO.getText().compareTo("")!=0 && 
               !DESCRIPCION.getSelectedItem().toString().equalsIgnoreCase("SELECCIONAR...")&&
               !DEPTO.getSelectedItem().toString().equalsIgnoreCase("SELECCIONAR...")&&
               FREC_MANTENIMIENTO.getText().compareTo("")!=0 &&
               !DESCRI_MANTE.getSelectedItem().toString().equalsIgnoreCase("SELECCIONAR...")&&
               Fabricante.getText().compareTo("")!=0 && 
               Modelo.getText().compareTo("")!=0 &&
               NoSerie.getText().compareTo("")!=0){insertar1();         
            }
            else{JOptionPane.showMessageDialog(null, "LLene Los Campos necesario");}
        
        }else{
            if(NO.getText().compareTo("")!=0 && 
               !DESCRIPCION.getSelectedItem().toString().equalsIgnoreCase("SELECCIONAR...")&&
               !DEPTO.getSelectedItem().toString().equalsIgnoreCase("SELECCIONAR...")&&
               Fabricante.getText().compareTo("")!=0 && 
               Modelo.getText().compareTo("")!=0 &&
               NoSerie.getText().compareTo("")!=0){insertar2();         
            }
            else{JOptionPane.showMessageDialog(null, "LLene Los Campos necesario");}
        }
    }//GEN-LAST:event_GuargarActionPerformed

    private void MASMANTEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MASMANTEActionPerformed
        
        
        TIPOMANTENIMIENTO ma = new TIPOMANTENIMIENTO();
        Pane1.add(ma); 
        Dimension desktopSize = Pane1.getSize();
        Dimension FrameSize = ma.getSize();
        ma.setLocation((desktopSize.width - FrameSize.width)/2 , (desktopSize.height - FrameSize.height)/2);
        ma.show();
    }//GEN-LAST:event_MASMANTEActionPerformed

    private void funcionaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_funcionaActionPerformed
          
           sirveono();
    }//GEN-LAST:event_funcionaActionPerformed

    private void DESCRI_MANTEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DESCRI_MANTEActionPerformed
       
         try {
            Connection con = Conn.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select id_mant from tipo_mante where descripcion ='"+this.DESCRI_MANTE.getSelectedItem()+"'");
            while (rs.next())
            {
            descripmante = rs.getInt(1);
            }
            rs.close();
            stmt.close();
        } catch (SQLException error) {
            System.out.println("NO ERROR DE DESCRIPCION"+error);
        }
        FREC_VERIFICACION.requestFocus();
    }//GEN-LAST:event_DESCRI_MANTEActionPerformed

    private void FREC_VERIFICACIONActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FREC_VERIFICACIONActionPerformed
                Guargar.requestFocus();
    }//GEN-LAST:event_FREC_VERIFICACIONActionPerformed

    private void FREC_MANTENIMIENTOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FREC_MANTENIMIENTOActionPerformed
                
        DESCRI_MANTE.requestFocus();

    }//GEN-LAST:event_FREC_MANTENIMIENTOActionPerformed

    private void DESCRIPCIONActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DESCRIPCIONActionPerformed
        
        try {
            Connection con = Conn.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select id_descri from descripcion where descripcion ='"+this.DESCRIPCION.getSelectedItem()+"'");
            while (rs.next())
            {
            descrip = rs.getInt(1);
            }
            rs.close();
            stmt.close();
        } catch (SQLException error) {
            System.out.println("NO ERROR DE DESCRIPCION"+error);
        }
    }//GEN-LAST:event_DESCRIPCIONActionPerformed

    private void MASDESCRIPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MASDESCRIPActionPerformed

        TIPODESCRIPCION ma = new TIPODESCRIPCION();
        Pane1.add(ma); 
        Dimension desktopSize = Pane1.getSize();
        Dimension FrameSize = ma.getSize();
        ma.setLocation((desktopSize.width - FrameSize.width)/2 , (desktopSize.height - FrameSize.height)/2);
        ma.show();        
       
    }//GEN-LAST:event_MASDESCRIPActionPerformed

    private void DEPTOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DEPTOActionPerformed
         
        if (DEPTO.getSelectedItem().toString().equalsIgnoreCase("INSPECCION")) {depto = 1;}
        else if(DEPTO.getSelectedItem().toString().equalsIgnoreCase("TESTING")){depto = 2;}
        else if(DEPTO.getSelectedItem().toString().equalsIgnoreCase("CHIPS")){depto = 3;}
        else if(DEPTO.getSelectedItem().toString().equalsIgnoreCase("STRIP Y POTTING")){depto = 4;}
        else if(DEPTO.getSelectedItem().toString().equalsIgnoreCase("TRANSFORMADORES")){depto = 5;}
        else if(DEPTO.getSelectedItem().toString().equalsIgnoreCase("TALLER")){depto = 6;}
        else if(DEPTO.getSelectedItem().toString().equalsIgnoreCase("BODEGA")){depto = 7;}
        
    }//GEN-LAST:event_DEPTOActionPerformed

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
            java.util.logging.Logger.getLogger(Maquina.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Maquina.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Maquina.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Maquina.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Maquina().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> DEPTO;
    private static javax.swing.JComboBox<String> DESCRIPCION;
    private static javax.swing.JComboBox<String> DESCRI_MANTE;
    private javax.swing.JTextField FREC_MANTENIMIENTO;
    private javax.swing.JTextField FREC_VERIFICACION;
    private javax.swing.JTextField Fabricante;
    private javax.swing.JButton Guargar;
    private javax.swing.JButton MASDESCRIP;
    private javax.swing.JButton MASMANTE;
    private javax.swing.JComboBox<String> Mante;
    private javax.swing.JTextField Modelo;
    private javax.swing.JTextField NO;
    private javax.swing.JTextField NoSerie;
    private javax.swing.JComboBox<String> Verifi;
    private javax.swing.JComboBox<String> funciona;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
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
    private javax.swing.JPanel jPanel3;
    // End of variables declaration//GEN-END:variables
}
