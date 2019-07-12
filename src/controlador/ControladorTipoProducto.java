/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import db.Conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import modelo.TipoProducto;

/**
 *
 * @author Ricardo Carcassón
 */
public class ControladorTipoProducto {
    public ArrayList<TipoProducto> ObtenerTiposPorID(int id){
        ArrayList<TipoProducto> listatipos = new ArrayList();
        try{
            Conexion conn = new Conexion();
            Connection conexion = conn.getConnection();
            
            String consulta = "SELECT id,secuencia,descripcion,familia_producto_id from tipo_producto where familia_producto_id=" + id;
            
            Statement stmt = conexion.createStatement();
            ResultSet rst = stmt.executeQuery(consulta);
            while(rst.next()){
                TipoProducto tp = new TipoProducto();
                tp.setId(rst.getInt("id"));
                tp.setSecuencia(rst.getInt("secuencia"));
                tp.setDescripcion(rst.getString("descripcion"));
                tp.setFamilia_producto_id(rst.getInt("familia_producto_id"));
                listatipos.add(tp);
            }
            rst.close();
            stmt.close();
            conexion.close();
            return listatipos;
        }catch (Exception err){
            return new ArrayList<TipoProducto>();
        }
    }

    public ArrayList<TipoProducto> FiltrarTipos(int id,String cadena){
        ArrayList<TipoProducto> listatipos = new ArrayList();
        try{
            Conexion conn = new Conexion();
            Connection conexion = conn.getConnection();
            
            String consulta = "SELECT id,secuencia,descripcion,familia_producto_id from tipo_producto where familia_producto_id=" + id +" and UPPER(descripcion) LIKE '"
                    +cadena.toUpperCase()+"%'";
            
            Statement stmt = conexion.createStatement();
            ResultSet rst = stmt.executeQuery(consulta);
            while(rst.next()){
                TipoProducto tp = new TipoProducto();
                tp.setId(rst.getInt("id"));
                tp.setSecuencia(rst.getInt("secuencia"));
                tp.setDescripcion(rst.getString("descripcion"));
                tp.setFamilia_producto_id(rst.getInt("familia_producto_id"));
                listatipos.add(tp);
            }
            rst.close();
            stmt.close();
            conexion.close();
            return listatipos;
        }catch (Exception err){
            return new ArrayList<TipoProducto>();
        }
    }


    public ArrayList<TipoProducto> ObtenerTipos(){
        ArrayList<TipoProducto> listatipos = new ArrayList();
        try{
            Conexion conn = new Conexion();
            Connection conexion = conn.getConnection();
            
            String consulta = "SELECT id,secuencia,descripcion,familia_producto_id from tipo_producto";
            
            Statement stmt = conexion.createStatement();
            ResultSet rst = stmt.executeQuery(consulta);
            while(rst.next()){
                TipoProducto tp = new TipoProducto();
                tp.setId(rst.getInt("id"));
                tp.setSecuencia(rst.getInt("secuencia"));
                tp.setDescripcion(rst.getString("descripcion"));
                tp.setFamilia_producto_id(rst.getInt("familia_producto_id"));
                listatipos.add(tp);
            }
            rst.close();
            stmt.close();
            conexion.close();
            return listatipos;
        }catch (Exception err){
            return new ArrayList<TipoProducto>();
        }
        
    }
    
    public boolean Agregar(TipoProducto tp){
        try{
            Conexion conn = new Conexion();
            Connection conexion = conn.getConnection();
            
            String consulta = "INSERT INTO TIPO_PRODUCTO VALUES (SQ_TIPO_PRODUCTO.NextVal,"+
                    tp.getSecuencia()+",'"+
                    tp.getDescripcion()+"',"+
                    tp.getFamilia_producto_id()+")";
            
            Statement stmt = conexion.createStatement();
            
            stmt.executeUpdate(consulta);
            stmt.close();
            conexion.close();
            return true;
        }catch (Exception err){
            return false;
        }
    }
    
    public String ObtenerDescripcion(int id){
        String descripcion="";
        try{
            Conexion conn = new Conexion();
            Connection conexion = conn.getConnection();
            
            String consulta = "SELECT descripcion from tipo_producto where id="+id;
            
            Statement stmt = conexion.createStatement();
            ResultSet rst = stmt.executeQuery(consulta);
            
            rst.next();
            descripcion = rst.getString("descripcion");
            rst.close();
            stmt.close();
            conexion.close();
            return descripcion;
        }catch (Exception err){
            return "";
        }
    }
    
    public int ObtenerSecuencia(int id){
        int secuencia;
        try{
            Conexion conn = new Conexion();
            Connection conexion = conn.getConnection();
            
            String consulta = "SELECT secuencia from tipo_producto where id="+id;
            
            Statement stmt = conexion.createStatement();
            ResultSet rst = stmt.executeQuery(consulta);
            
            rst.next();
            secuencia = rst.getInt("secuencia");
            rst.close();
            stmt.close();
            conexion.close();
            return secuencia;
        }catch(Exception err){
            return 0;
        }
    }
}
