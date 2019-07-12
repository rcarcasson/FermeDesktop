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
import modelo.Acceso;

/**
 *
 * @author v-carica
 */
public class ControladorAcceso {
    public boolean AgregarUsuario(Acceso a){
        try{
            Conexion conn = new Conexion();
            Connection conexion = conn.getConnection();
            
            String consulta = "INSERT INTO acceso (ID,USUARIO,CLAVE,USUARIO_ID) VALUES (SQ_ACCESO.NextVal,'" +
                    a.getUsuario() + "','" +
                    a.getClave() + "'," +
                    a.getUsuario_id() + ")";
            
            Statement stmt = conexion.createStatement();
            stmt.executeUpdate(consulta);
            stmt.close();
            conexion.close();
            return true;
        }catch (Exception err){
            return false;
        }
    }
    
    public boolean AgregarUsuarioProveedor(Acceso a){
        try{
            Conexion conn = new Conexion();
            Connection conexion = conn.getConnection();
            
            String consulta = "INSERT INTO acceso (ID,USUARIO,CLAVE,PROVEEDOR_ID) VALUES (SQ_ACCESO.NextVal,'" +
                    a.getUsuario() + "','" +
                    a.getClave() + "'," +
                    a.getProveedor_id() + ")";
            
            Statement stmt = conexion.createStatement();
            stmt.executeUpdate(consulta);
            stmt.close();
            conexion.close();
            return true;
        }catch (Exception err){
            return false;
        }        
    }
    
    
    public boolean ModificarUsuario(Acceso a){
        try{
            Conexion conn = new Conexion();
            Connection conexion = conn.getConnection();
            
            Statement stmt = conexion.createStatement();
            if (a.getClave().length()!=0){
                String consulta = "UPDATE acceso set usuario='" +
                        a.getUsuario() + "', clave='" + 
                        a.getClave() + "' where usuario_id=" +
                        a.getUsuario_id();
                
                stmt.executeUpdate(consulta);
                
            }else{
                String consulta = "UPDATE acceso set usuario='" +
                        a.getUsuario() + "' where usuario_id=" +
                        a.getUsuario_id();
                
                stmt.executeUpdate(consulta);         
            }
            stmt.close();
            conexion.close();
            return true;
        }catch (Exception err){
            return false;
        }
    }
    
    public boolean ModificarUsuarioProveedor(Acceso a){
        try{
            Conexion conn = new Conexion();
            Connection conexion = conn.getConnection();
            
            Statement stmt = conexion.createStatement();
            if (a.getClave().length()!=0){
                String consulta = "UPDATE acceso set usuario='" +
                        a.getUsuario() + "', clave='" + 
                        a.getClave() + "' where proveedor_id=" +
                        a.getProveedor_id();
                
                stmt.executeUpdate(consulta);
                
            }else{
                String consulta = "UPDATE acceso set usuario='" +
                        a.getUsuario() + "' where proveedor_id=" +
                        a.getProveedor_id();
                
                stmt.executeUpdate(consulta);         
            }
            stmt.close();
            conexion.close();
            return true;
        }catch (Exception err){
            return false;
        }
    }

   
    public boolean EliminarUsuario(int id){
        try{
            Conexion conn = new Conexion();
            Connection conexion = conn.getConnection();
            
            Statement stmt = conexion.createStatement();
            
            String consulta = "DELETE acceso where usuario_id="+id;
            
            stmt.executeUpdate(consulta);
            stmt.close();
            conexion.close();
            return true;
        }catch (Exception err){
            return false;
        }
    }
    
    public boolean EliminarUsuarioProveedor(int id){
        try{
            Conexion conn = new Conexion();
            Connection conexion = conn.getConnection();
            
            Statement stmt = conexion.createStatement();
            
            String consulta = "DELETE acceso where proveedor_id="+id;
            
            stmt.executeUpdate(consulta);
            stmt.close();
            conexion.close();
            return true;
        }catch (Exception err){
            return false;
        }        
    }


    
    public int ObtenerIDUsuario(String mail){
        try{
            Conexion conn = new Conexion();
            Connection conexion = conn.getConnection();
            
            String consulta = "SELECT ID from usuario where mail='"+mail+"'";
            
            Statement stmt = conexion.createStatement();
            
            ResultSet rst = stmt.executeQuery(consulta);
            rst.next();
            int id = rst.getInt("ID");
            rst.close();
            stmt.close();
            conexion.close();
            return id;
        } catch (Exception err){
            return -1;
        }
    }

    public int ObtenerIDProveedor(String mail){
        try{
            Conexion conn = new Conexion();
            Connection conexion = conn.getConnection();
            
            String consulta = "SELECT ID from proveedor where mail='"+mail+"'";
            
            Statement stmt = conexion.createStatement();
            
            ResultSet rst = stmt.executeQuery(consulta);
            rst.next();
            int id = rst.getInt("ID");
            rst.close();
            stmt.close();
            conexion.close();
            return id;
        } catch (Exception err){
            return -1;
        }
    }

}
