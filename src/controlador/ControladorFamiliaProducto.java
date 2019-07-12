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
import modelo.FamiliaProducto;

/**
 *
 * @author v-carica
 */
public class ControladorFamiliaProducto {
    public ArrayList<FamiliaProducto> ObtenerFamilias(){
        ArrayList<FamiliaProducto> fp = new ArrayList();
        try{
            Conexion conn = new Conexion();
            Connection conexion = conn.getConnection();
            Statement stmt = conexion.createStatement();
            
            String consulta = "SELECT id,id_familia,descripcion from familia_producto order BY ID";
            
            ResultSet rst = stmt.executeQuery(consulta);
            while (rst.next()){
                FamiliaProducto famp = new FamiliaProducto();
                famp.setId(rst.getInt("id"));
                famp.setId_familia(rst.getString("id_familia"));
                famp.setDescripcion(rst.getString("descripcion"));
                fp.add(famp);
            }
            rst.close();
            stmt.close();
            conexion.close();
            return fp;
        } catch (Exception err){
            return new ArrayList();
        }
    }
    
    public ArrayList<FamiliaProducto> FiltroFamilias(String cadena){
        ArrayList<FamiliaProducto> fp = new ArrayList();
        try{
            Conexion conn = new Conexion();
            Connection conexion = conn.getConnection();
            Statement stmt = conexion.createStatement();
            
            String consulta = "SELECT id,id_familia,descripcion from familia_producto where UPPER(descripcion) LIKE '"+cadena.toUpperCase()+"%'";
            
            ResultSet rst = stmt.executeQuery(consulta);
            while (rst.next()){
                FamiliaProducto famp = new FamiliaProducto();
                famp.setId(rst.getInt("id"));
                famp.setId_familia(rst.getString("id_familia"));
                famp.setDescripcion(rst.getString("descripcion"));
                fp.add(famp);
            }
            rst.close();
            stmt.close();
            conexion.close();
            return fp;
        } catch (Exception err){
            return new ArrayList();
        }        
    }
    
    public String ObtenerCodigoFamilia(int id){
        String codigo="";
        try{
            Conexion conn = new Conexion();
            Connection conexion = conn.getConnection();
            
            Statement stmt = conexion.createStatement();
            
            String consulta = "SELECT id_familia from familia_producto where id="+id;
            
            ResultSet rst = stmt.executeQuery(consulta);
            
            while (rst.next()){
                codigo = rst.getString("id_familia");
            }
            rst.close();
            stmt.close();
            conexion.close();
            return codigo;
        }catch (Exception err){
            return "";
        }
    }
    
    public boolean AgregarFamilia(FamiliaProducto fp){
        try{
            Conexion conn = new Conexion();
            Connection conexion = conn.getConnection();
            Statement stmt = conexion.createStatement();
            
            String consulta = "INSERT INTO familia_producto (id, id_familia, descripcion) VALUES (SQ_FAMILIA_PRODUCTO.Nextval,'" +
                    fp.getId_familia() +"','"+
                    fp.getDescripcion()+"')";
            
            stmt.executeUpdate(consulta);
            stmt.close();
            conexion.close();
            return true;
        }catch (Exception err){
            return false;
        }
    }
    
    public boolean EliminarFamilia(int id){
        try{
            Conexion conn = new Conexion();
            Connection conexion = conn.getConnection();
            Statement stmt = conexion.createStatement();
            
            String consulta = "DELETE from familia_producto where id=" + id + "";
            
            stmt.executeUpdate(consulta);
            stmt.close();
            conexion.close();
            return true;
        }catch (Exception err){
            return false;
        }
    }
    
    public boolean ActualizarFamilia(int id, String descripcion){
        try{
            Conexion conn = new Conexion();
            Connection conexion = conn.getConnection();
            Statement stmt = conexion.createStatement();
            
            String consulta = "UPDATE familia_producto set descripcion='" + 
                    descripcion + "' where id=" +
                    id +"";
            
            stmt.executeUpdate(consulta);
            stmt.close();
            conexion.close();
            return true;
        }catch (Exception err){
            return false;
        }
    }
    
    public boolean VerificarSiExiste(String codfamilia, String descripcion){
        try{
            Conexion conn = new Conexion();
            Connection conexion = conn.getConnection();
            Statement stmt = conexion.createStatement();
            
            String consulta = "SELECT id from familia_producto where id_familia='" + 
                    codfamilia +"' OR descripcion='" +
                    descripcion + "'";
            
            ResultSet rst = stmt.executeQuery(consulta);
            
            rst.next();
            int total = rst.getRow();
            rst.close();
            stmt.close();
            conexion.close();
            if (total == 0){
                return false;
            }else{
                return true;
            }
            
        }catch (Exception err){
            return true;
        }
    }
    
    public String ObtenerDescripcion(String id){
        String descripcion = "";
        try{
            Conexion conn = new Conexion();
            Connection conexion = conn.getConnection();
            
            String consulta = "SELECT descripcion from familia_producto where id_familia='"+id+"'";
            
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
}
