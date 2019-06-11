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
public class Ingreso_Verificaciones {
    
     public static ArrayList<maquinas> ListarMaquinas(String a){
         return consultarSQL("select m.no,d.descripcion from maquinaria m inner join descripcion d on m.id_descri = d.id_descri LEFT join verificacion v on m.no = v.no where v.fecha_inicio is  null  and m.no like upper('"+a+"%') and verificacion = 'SI'  order by m.no");   
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
                p.setNO(rs.getInt("no"));
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
    
  
public static ArrayList<maquinas> ListadoGeneralMaquinas(String a){
        
         return SQLGeneral("select m.no,d.descripcion||' '||m.modelo as descripcion ,m.fabricante,m.no_serie,decode(m.funcionamiento,1,'EN FUNCIONAMIENTO',2,'MAL ESTADO',3,'FUERA DE SERVICIO')AS funcionamiento from maquinaria m inner join descripcion d on m.id_descri = d.id_descri where  m.no like upper('"+a+"%') and estado = 1 order by m.no");   
    }
        private static ArrayList<maquinas> SQLGeneral(String sql) {
        ArrayList<maquinas> list = new ArrayList<maquinas>();
        Connection cn = Conn.getConnection();
        try {
            maquinas p;
            Statement stmt = cn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                p = new maquinas();
                p.setNO(rs.getInt("no"));
                p.setDESCRIPCION(rs.getString("descripcion"));
                p.setFABRICANTE(rs.getString("fabricante"));
                p.setSERIE(rs.getString("no_serie"));
                p.setFUNCIONAMIENTO(rs.getString("funcionamiento"));
                list.add(p);
            }
            cn.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return null;
        }
        return list;
    }


public static ArrayList<maquinas> ListarVerifiSeguimiento(String a){
        
         return consultarSeguimiento("select m.no,d.descripcion,TO_CHAR(v.fecha_proximo,'dd/mm/YYYY') as fecha from maquinaria m inner join descripcion d on m.id_descri = d.id_descri join verificacion v on m.no = v.no where v.estado = 1 and m.no like upper('"+a+"%') order by m.no");   
    }
   
private static ArrayList<maquinas> consultarSeguimiento(String sql) {
        ArrayList<maquinas> list = new ArrayList<maquinas>();
        Connection cn = Conn.getConnection();
        try {
            maquinas p;
            Statement stmt = cn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                p = new maquinas();
                p.setNO(rs.getInt("no"));
                p.setDESCRIPCION(rs.getString("descripcion"));
                p.setFRECUENCIAMANT(rs.getString("fecha"));
                list.add(p);
            }
            cn.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return null;
        }
        return list;
    }

public static ArrayList<maquinas> ListarProximasVerifi(String a, String b){
        
         return proximosVeri("select m.no,d.descripcion||' '||m.modelo as descripcion,to_char(r.fecha_proximo,'dd/mm/yyyy') as fecha from maquinaria m inner join descripcion d on m.id_descri = d.id_descri join verificacion r on m.no = r.no where r.estado = 1 and r.fecha_proximo between to_date('"+a+"','dd/mm/yy') and to_date('"+b+"','dd/mm/yy')order by m.no");   
    }
   
private static ArrayList<maquinas> proximosVeri(String sql) {
        ArrayList<maquinas> list = new ArrayList<maquinas>();
        Connection cn = Conn.getConnection();
        try {
            maquinas p;
            Statement stmt = cn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                p = new maquinas();
                p.setNO(rs.getInt("no"));
                p.setDESCRIPCION(rs.getString("descripcion"));
                p.setFRECUENCIAMANT(rs.getString("fecha"));
                list.add(p);
            }
            cn.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return null;
        }
        return list;
    }

public static ArrayList<maquinas> ListarProximasVerifiTodo(){
        
         return proximosVeriTodo("select m.no,d.descripcion||' '||m.modelo as descripcion,to_char(r.fecha_proximo,'dd/mm/yyyy') as fecha from maquinaria m inner join descripcion d on m.id_descri = d.id_descri join verificacion r on m.no = r.no where r.estado = 1 order by m.no");   
    }
   
private static ArrayList<maquinas> proximosVeriTodo(String sql) {
        ArrayList<maquinas> list = new ArrayList<maquinas>();
        Connection cn = Conn.getConnection();
        try {
            maquinas p;
            Statement stmt = cn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                p = new maquinas();
                p.setNO(rs.getInt("no"));
                p.setDESCRIPCION(rs.getString("descripcion"));
                p.setFRECUENCIAMANT(rs.getString("fecha"));
                list.add(p);
            }
            cn.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return null;
        }
        return list;
    }

public static ArrayList<maquinas> ListarHistorial(String a){
        
         return Historial("select m.no,d.descripcion||' '||m.modelo as descripcion,t.descripcion as tipomantenimiento,r.fecha_proximo as fecha from maquinaria m inner join descripcion d on m.id_descri = d.id_descri join mantenimiento_realizados r on m.no = r.no join tipo_mante t on m.id_mant = t.id_mant where r.estado = 2 and m.no like upper('"+a+"%') order by m.no");   
    }
   
public static ArrayList<maquinas> ListarHistorialverifi(String a){
        
         return Historial("select m.no,d.descripcion||' '||m.modelo as descripcion,t.descripcion as tipomantenimiento,to_char(r.fecha_inicio,'dd/mm/yyyy') as fecha from maquinaria m inner join descripcion d on m.id_descri = d.id_descri join verificacion r on m.no = r.no join tipo_mante t on m.id_mant = t.id_mant where r.estado = 2 and m.no like upper('"+a+"%') order by m.no");   
    }
private static ArrayList<maquinas> Historial(String sql) {
        ArrayList<maquinas> list = new ArrayList<maquinas>();
        Connection cn = Conn.getConnection();
        try {
            maquinas p;
            Statement stmt = cn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                p = new maquinas();
                p.setNO(rs.getInt("no"));
                p.setDESCRIPCION(rs.getString("descripcion"));
                p.setMANTE(rs.getString("tipomantenimiento"));
                p.setFRECUENCIAMANT(rs.getString("fecha"));
                list.add(p);
            }
            cn.close();
        } catch (SQLException e) {
            System.out.println("error buscar "+e);
            System.err.println(e.getMessage());
            return null;
        }
        return list;
    }


    
public static manto buscarMaquina(int id) throws SQLException {
      return buscarMaquina(id, null);
    }
    public static manto buscarMaquina(int id, manto p) throws SQLException {
        Connection cnn = Conn.getConnection();
        PreparedStatement ps = null;
        ps = cnn.prepareStatement("select m.no,d.descripcion,m.fabricante,m.modelo,m.no_serie,m.frecuenciaverifi from maquinaria m inner join descripcion d on m.id_descri = d.id_descri where m.no = ?");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            if (p == null) {
                p = new manto(){
                };
            }
            p.setCodigo(rs.getInt("no"));
            p.setDescripcion(rs.getString("descripcion"));
            p.setModelo(rs.getString("modelo"));
            p.setSerie(rs.getString("no_serie"));
            p.setFabricante(rs.getString("fabricante"));
            p.setFrecuencia(rs.getInt("frecuenciaverifi"));
        }
        cnn.close();
        ps.close();
        return p;
    }
   
    public static verificacion buscarMaquinaVerifi(int id) throws SQLException {
      return buscarMaquinaV(id, null);
    }
    public static verificacion buscarMaquinaV(int id, verificacion p) throws SQLException {
        Connection cnn = Conn.getConnection();
        PreparedStatement ps = null;
        ps = cnn.prepareStatement("select m.no,d.descripcion,m.fabricante,m.modelo,m.no_serie,m.frecuenciaverifi,to_char(r.fecha_proximo,'dd/mm/yyyy') as fecha_proximo,r.id_verifi from maquinaria m inner join descripcion d on m.id_descri = d.id_descri  join verificacion r on m.no = r.no where m.no = ? and r.estado = 1");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            if (p == null) {
                p = new verificacion(){
                };
            }
            p.setCodigo(rs.getInt("no"));
            p.setDescripcion(rs.getString("descripcion"));
            p.setModelo(rs.getString("modelo"));
            p.setSerie(rs.getString("no_serie"));
            p.setFabricante(rs.getString("fabricante"));
            p.setFrecuencia(rs.getInt("frecuenciaverifi"));
            p.setFechaproximo(rs.getString("fecha_proximo"));
            p.setId_verifi(rs.getInt("id_verifi"));
        }
        cnn.close();
        ps.close();
        return p;
    }
    
    public static void IngresoVerificacion(verificacion c) throws SQLException { //ya modificado para verificacion
        
        Connection cnn = Conn.getConnection();
        PreparedStatement ps = null;
        ps = cnn.prepareStatement("insert into VERIFICACION(id_verifi,no,fecha_inicio,fecha_final,nota,estado,fecha_proximo) values (?,?,?,?,?,1,?)");
        ps.setInt(1,c.getId_verifi());
        ps.setInt(2,c.getCodigo());
        //ps.setString(3,c.getFechainicio());
        ps.setDate(3, new java.sql.Date(c.getFechainicio().getTime()));
        ps.setDate(4, new java.sql.Date(c.getFechafin().getTime()));
        ps.setString(5, c.getNotas());
        ps.setString(6,c.getFechaproximo());
        ps.execute();
        cnn.close();
        ps.close();
    }

     public static void IngresoVerifi2(verificacion c) throws SQLException {
        
        Connection cnn = Conn.getConnection();
        PreparedStatement ps = null;
        ps = cnn.prepareStatement("insert into verificacion(id_verifi,no,fecha_inicio,fecha_final,fecha_proximo,estado,fechaideal) values (?,?,?,?,?,1,?)");
        ps.setInt(1,c.getId_verifi());
        ps.setInt(2,c.getCodigo());
        //ps.setString(3,c.getFechainicio());
        ps.setDate(3, new java.sql.Date(c.getFechainicio().getTime()));
        ps.setDate(4, new java.sql.Date(c.getFechafin().getTime()));
        ps.setString(5,c.getFechaproximo());
        ps.setString(6,c.getFechaIdeal());
        ps.execute();
        cnn.close();
        ps.close();
    }
     
     public static void IngresoIndicador(manto c) throws SQLException {
        
        Connection cnn = Conn.getConnection();
        PreparedStatement ps = null;
        ps = cnn.prepareStatement("insert into indicadores(no_indicadores,no,id_mantenimiento,fecha,tiempo_realizacion,cumplimiento,fechasistema) values (?,?,?,?,?,?,sysdate)");
        ps.setInt(1,c.getIdindicador());
        ps.setInt(2,c.getCodigo());
        ps.setInt(3,c.getId_matenimiento());
        //ps.setString(3,c.getFechainicio());
        ps.setDate(4, new java.sql.Date(c.getFechainicio().getTime()));
        ps.setInt(5,c.getRealizacion());
        ps.setInt(6,c.getCumplimiento());
        ps.execute();
        cnn.close();
        ps.close();
    }
     
     public static ArrayList<manto> ListarIndicadores(String a, String b){
        
         return Indicadores("select i.NO,d.descripcion||' '||m.modelo as descripcion,i.FECHA,i.tiempo_realizacion,i.cumplimiento from indicadores i inner join maquinaria m on i.no = m.no join descripcion d on d.id_descri = m.id_descri where fecha BETWEEN TO_DATE('"+a+"','dd/mm/yy') and TO_DATE('"+b+"','dd/mm/yy')");   
    }
   
private static ArrayList<manto> Indicadores(String sql) {
        ArrayList<manto> list = new ArrayList<manto>();
        Connection cn = Conn.getConnection();
        try {
            manto p;
            Statement stmt = cn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                p = new manto();
                p.setCodigo(rs.getInt("no"));
                p.setDescripcion(rs.getString("descripcion"));
                p.setFechaIdeal(rs.getString("fecha"));
                p.setRealizacion(rs.getInt("tiempo_realizacion"));
                p.setCumplimiento(rs.getInt("cumplimiento"));
                list.add(p);
            }
            cn.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return null;
        }
        return list;
    }

}
