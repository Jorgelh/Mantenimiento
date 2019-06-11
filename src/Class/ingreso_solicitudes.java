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
public class ingreso_solicitudes {
    
    public static SOLICITUDES buscarMaquina(int id) throws SQLException {
      return buscarMaquina(id, null);
    }
    public static SOLICITUDES buscarMaquina(int id, SOLICITUDES p) throws SQLException {
        Connection cnn = Conn.getConnection();
        PreparedStatement ps = null;
        ps = cnn.prepareStatement("select d.descripcion,m.fabricante,m.modelo,m.no_serie from maquinaria m inner join descripcion d on m.id_descri = d.id_descri where m.no = ?");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            if (p == null) {
                p = new SOLICITUDES(){
                };
            }
            p.setDescripcion(rs.getString("descripcion"));
            p.setModelo(rs.getString("modelo"));
            p.setSerie(rs.getString("no_serie"));
            p.setFabricante(rs.getString("fabricante"));
        }
        cnn.close();
        ps.close();
        return p;
    }
    
public static void NuevaSolicitud(SOLICITUDES c) throws SQLException {
        
        Connection cnn = Conn.getConnection();
        PreparedStatement ps = null;
        ps = cnn.prepareStatement("insert into incidencias(no_incidencias,no,fecha,estado,depto,descripcion_falla,estatus) values (?,?,?,1,?,?,1)");
        ps.setInt(1,c.getId_incidencia());
        ps.setInt(2,c.getCodigo());
        ps.setString(3,c.getFecha1());
        ps.setInt(4,c.getDepto());
        ps.setString(5,c.getNotas());
        //ps.setDate(4, new java.sql.Date(c.getFechaIngre().getTime()));
        ps.execute();
        cnn.close();
        ps.close();
    }



public static ArrayList<SOLICITUDES> ListarSolicitudes(){
        
         return Solicitud("select i.no_incidencias,m.no,d.descripcion||' '||m.modelo as descripcion,\n" +
"i.descripcion_falla as falla,\n" +
"DECODE(i.depto,1,'INSPECCION',2,'TESTING',3,'CHIPS',4,'STRIP Y POTTING',5,'TRANSFORMADORES',6,'TALLER',7,'BODEGA') AS DEPTO,\n" +
"DECODE(i.estatus,1,'PENDIENTE',2,'EN PROCESO',3,'ESPERANDO REPUESTOS',4,'TERMINADA') AS ESTATUS \n" +
"from maquinaria m inner join descripcion d on m.id_descri = d.id_descri join incidencias i on m.no = i.no where i.estado = 1 order by i.no_incidencias");   
    }
   
private static ArrayList<SOLICITUDES> Solicitud(String sql) {
        ArrayList<SOLICITUDES> list = new ArrayList<SOLICITUDES>();
        Connection cn = Conn.getConnection();
        try {
            SOLICITUDES p;
            Statement stmt = cn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                p = new SOLICITUDES();
                p.setCodigo(rs.getInt("no"));
                p.setDescripcion(rs.getString("descripcion"));
                p.setDeparta(rs.getString("depto"));
                p.setNotas(rs.getString("falla"));
                p.setEstado(rs.getString("estatus"));
                p.setId_incidencia(rs.getInt("no_incidencias"));
                list.add(p);
            }
            cn.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return null;
        }
        return list;
    }


public static ArrayList<SOLICITUDES> ListarSolicitudesHistorial(String a){
        
         return SolicitudHistorial("select m.no,d.descripcion||' '||m.modelo as descripcion,\n" +
"i.descripcion_falla as falla,\n" +
"DECODE(i.depto,1,'INSPECCION',2,'TESTING',3,'CHIPS',4,'STRIP Y POTTING',5,'TRANSFORMADORES',6,'TALLER',7,'BODEGA') AS DEPTO,\n" +
"DECODE(i.estatus,1,'PENDIENTE',2,'EN PROCESO',3,'ESPERANDO REPUESTOS',4,'TERMINADA') AS ESTATUS \n" +
"from maquinaria m inner join descripcion d on m.id_descri = d.id_descri join incidencias i on m.no = i.no where i.estado = 2 and i.no like upper('"+a+"%') order by m.no");   
    }
   
private static ArrayList<SOLICITUDES> SolicitudHistorial(String sql) {
        ArrayList<SOLICITUDES> list = new ArrayList<SOLICITUDES>();
        Connection cn = Conn.getConnection();
        try {
            SOLICITUDES p;
            Statement stmt = cn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                p = new SOLICITUDES();
                p.setCodigo(rs.getInt("no"));
                p.setDescripcion(rs.getString("descripcion"));
                p.setDeparta(rs.getString("depto"));
                p.setNotas(rs.getString("falla"));
                p.setEstado(rs.getString("estatus"));
                list.add(p);
            }
            cn.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return null;
        }
        return list;
    }

public static SOLICITUDES buscarMaquinaActualizar(int id) throws SQLException {
      return buscarMaquinaActua(id, null);
    }
    public static SOLICITUDES buscarMaquinaActua(int id, SOLICITUDES p) throws SQLException {
        Connection cnn = Conn.getConnection();
        PreparedStatement ps = null;
        ps = cnn.prepareStatement("select d.descripcion,m.fabricante,m.modelo,m.no_serie,\n" +
"DECODE(i.depto,1,'INSPECCION',2,'TESTING',3,'CHIPS',4,'STRIP Y POTTING',5,'TRANSFORMADORES',6,'TALLER',7,'BODEGA') AS DEPTO,\n" +
"i.fecha,i.descripcion_falla,i.notas from maquinaria m inner join descripcion d on m.id_descri = d.id_descri \n" +
"JOIN incidencias i on m.no = i.no where i.no_incidencias = ?");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            if (p == null) {
                p = new SOLICITUDES(){
                };
            }
            p.setDescripcion(rs.getString("descripcion"));
            p.setModelo(rs.getString("modelo"));
            p.setSerie(rs.getString("no_serie"));
            p.setFabricante(rs.getString("fabricante"));
            p.setDeparta(rs.getString("depto"));
            p.setNotadefalla(rs.getString("descripcion_falla"));
            p.setDeparta(rs.getString("depto"));
            p.setFecha1(rs.getString("fecha"));
            p.setNotas(rs.getString("notas"));
        }
        cnn.close();
        ps.close();
        return p;
    }
  
}
