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

/**
 *
 * @author v-carica
 */
public class ControladorUsuario {
    public boolean ExisteRut(String rut){
        boolean existe=false;
        try{
            Conexion conn = new Conexion();
            Connection conexion = conn.getConnection();
            
            String consulta = "SELECT id from usuario where UPPER(rut)='"+rut.toUpperCase()+"'";
            
            Statement stmt = conexion.createStatement();
            
            ResultSet rst = stmt.executeQuery(consulta);
            rst.next();
            if (rst.getRow()>0){
                existe=true;
            }
            rst.close();
            stmt.close();
            conexion.close();
            return existe;
        }catch (Exception err){
            return false;
        }
    }
    
    public boolean ExisteCorreo(String mail){
        boolean existe=false;
        try{
            Conexion conn = new Conexion();
            Connection conexion = conn.getConnection();
            
            String consulta = "SELECT id from usuario where UPPER(mail)='"+mail.toUpperCase()+"'";
            
            Statement stmt = conexion.createStatement();
            
            ResultSet rst = stmt.executeQuery(consulta);
            rst.next();
            if (rst.getRow()>0){
                existe=true;
            }
            rst.close();
            stmt.close();
            conexion.close();
            return existe;
        }catch (Exception err){
            return false;
        }
        
    }
}
