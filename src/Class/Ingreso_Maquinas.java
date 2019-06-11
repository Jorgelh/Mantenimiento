/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Class;

import BD.Conn;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author jluis
 */
public class Ingreso_Maquinas {
    
    
    
     public static void NuevaMAquina(maquinas c) throws SQLException {
        
        Connection cnn = Conn.getConnection();
        PreparedStatement ps = null;
        ps = cnn.prepareStatement("insert into maquinaria(no,id_mant,id_descri,fabricante,modelo,no_serie,mantenimiento,frecuenciamante,verificacion,frecuenciaverifi,depto,funcionamiento,estado) values (?,?,?,?,?,?,?,?,?,?,?,?,1)");
        ps.setInt(1,c.getNO());
        ps.setInt(2,c.getDESCRIP_MANT());
        ps.setInt(3,c.getDESCRIP());
        ps.setString(4,c.getFABRICANTE());
        //ps.setDate(4, new java.sql.Date(c.getFechaIngre().getTime()));
        ps.setString(5,c.getMODELO());
        ps.setString(6,c.getSERIE());
        ps.setString(7,c.getMANTE());
        ps.setString(8,c.getFRECUENCIAMANT());
        ps.setString(9,c.getVERIFI());
        ps.setString(10,c.getFRECUENCIAVERIFI());
        ps.setInt(11,c.getDEPTO());
        ps.setInt(12,c.getFuncionamiento());
        ps.execute();
        cnn.close();
        ps.close();
    }
    
    
     public static ArrayList<maquinas> ListarDescripcion(){
        
         return consultarSQL("select id_descri,descripcion from descripcion");   
    }
   
    
private static ArrayList<maquinas> consultarSQL(String sql) {
        ArrayList<maquinas> list = new ArrayList<maquinas>();
        Connection cn = Conn.getConnection();
        try {
            maquinas p;
            Statement stmt = cn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                p = new maquinas();
                p.setNO(rs.getInt("id_descri"));
                p.setDESCRIPCION(rs.getString("descripcion"));
                list.add(p);
            }
            cn.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return null;
        }
        return list;
    }
    
public static ArrayList<maquinas> ListarDescripcionManto(){
        
         return MantoSQL("select id_mant,descripcion from tipo_mante");   
    }
   
    
private static ArrayList<maquinas> MantoSQL(String sql) {
        ArrayList<maquinas> list = new ArrayList<maquinas>();
        Connection cn = Conn.getConnection();
        try {
            maquinas p;
            Statement stmt = cn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                p = new maquinas();
                p.setNO(rs.getInt("id_mant"));
                p.setDESCRIPCION(rs.getString("descripcion"));
                list.add(p);
            }
            cn.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return null;
        }
        return list;
    }   





public static maquinas buscarTipomanto(int id) throws SQLException {
      return buscarTipomanto(id, null);
    }
    public static maquinas buscarTipomanto(int id, maquinas p) throws SQLException {
        Connection cnn = Conn.getConnection();
        PreparedStatement ps = null;
        ps = cnn.prepareStatement("select descripcion,nota from tipo_mante where id_mant = ?");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            if (p == null) {
                p = new maquinas(){
                };
            }
            p.setNO(id);
            p.setDESCRIPCION(rs.getString("Descripcion"));
            p.setNota(rs.getString("nota"));
            
        }
        cnn.close();
        ps.close();
        return p;
    }

public static maquinas buscarTipoDescri(int id) throws SQLException {
      return buscarTipoDescri(id, null);
    }
    public static maquinas buscarTipoDescri(int id, maquinas p) throws SQLException {
        Connection cnn = Conn.getConnection();
        PreparedStatement ps = null;
        ps = cnn.prepareStatement("select descripcion,nota from descripcion where id_descri = ?");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            if (p == null) {
                p = new maquinas(){
                };
            }
            p.setNO(id);
            p.setDESCRIPCION(rs.getString("descripcion"));
            p.setNota(rs.getString("nota"));
            
        }
        cnn.close();
        ps.close();
        return p;
    }



public static void insertarDescri (maquinas m) throws SQLException{
    Connection cnn = Conn.getConnection();
        PreparedStatement ps = null;
    ps= cnn.prepareStatement("insert into descripcion (id_descri,Descripcion,nota) Values(?,?,?)");
    ps.setInt(1,m.getNO());
    ps.setString(2, m.getDESCRIPCION());
    ps.setString(3, m.getNota());
    ps.executeUpdate();
    cnn.close();
    ps.close();
    }

public static boolean actualizarDescri(maquinas p) throws SQLException {
        Connection cnn = Conn.getConnection();
        PreparedStatement ps = null;
        ps = cnn.prepareStatement("update descripcion set Descripcion=?,nota=? where id_presentacion=" + p.getNO());
        ps.setString(1, p.getDESCRIPCION());
        ps.setString(2, p.getNota());
        int rowsUpdated = ps.executeUpdate();
        cnn.close();
        ps.close();
        if (rowsUpdated > 0) {
            return true;
        } else {
            return false;
        }
    }


public static void insertarManto (maquinas m) throws SQLException{
    Connection cnn = Conn.getConnection();
        PreparedStatement ps = null;
    ps= cnn.prepareStatement("insert into tipo_mante (id_mant,Descripcion,nota) Values(?,?,?)");
    ps.setInt(1,m.getNO());
    ps.setString(2, m.getDESCRIPCION());
    ps.setString(3, m.getNota());
    ps.executeUpdate();
    cnn.close();
    ps.close();
    }

public static boolean actualizarManto(maquinas p) throws SQLException {
        Connection cnn = Conn.getConnection();
        PreparedStatement ps = null;
        ps = cnn.prepareStatement("update descripcion set Descripcion=?,nota=? where id_presentacion=" + p.getNO());
        ps.setString(1, p.getDESCRIPCION());
        ps.setString(2, p.getNota());
        int rowsUpdated = ps.executeUpdate();
        cnn.close();
        ps.close();
        if (rowsUpdated > 0) {
            return true;
        } else {
            return false;
        }
    }


}
