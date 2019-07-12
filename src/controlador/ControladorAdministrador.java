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
import modelo.Usuario;

/**
 *
 * @author v-carica
 */
public class ControladorAdministrador {
    
    public ArrayList<Usuario> ObtenerAdministradores(String cadena){
        ArrayList<Usuario> empleados = new ArrayList<>();
        try{
            Conexion conn = new Conexion();
            Connection conexion = conn.getConnection();
            
            String consulta = "SELECT ID,RUT,NOMBRE,DIRECCION,TELEFONO,MAIL,CARGO,TIPO_USUARIO FROM USUARIO WHERE TIPO_USUARIO='A' AND (UPPER(RUT) LIKE '" + cadena.toUpperCase() + "%' OR UPPER(NOMBRE) LIKE '" + cadena.toUpperCase() + "%' "
                    + ")";
            
            Statement stmt = conexion.createStatement();
            ResultSet rst = stmt.executeQuery(consulta);
            
            while(rst.next()){
                Usuario u = new Usuario();
                u.setId(rst.getInt("id"));
                u.setRut(rst.getString("rut"));
                u.setNombre(rst.getString("nombre"));
                u.setDireccion(rst.getString("direccion"));
                u.setTelefono(rst.getString("telefono"));
                u.setMail(rst.getString("mail"));
                u.setCargo(rst.getString("cargo"));
                u.setTipo_usuario((char)rst.getString("tipo_usuario").charAt(0));
                empleados.add(u);
            }
            
            rst.close();
            stmt.close();
            conexion.close();
            return empleados;
        }catch (Exception err){
            return new ArrayList<>();
        }
    }
    
    public boolean AgregarAdministrador(Usuario u){
        try{
            Conexion conn = new Conexion();
            Connection conexion = conn.getConnection();
            
            String consulta = "INSERT INTO usuario (ID,RUT,NOMBRE,DIRECCION,TELEFONO,MAIL,CARGO,TIPO_USUARIO) VALUES (SQ_USUARIO.NextVal,'" +
                    u.getRut() + "','" +
                    u.getNombre() + "','" +
                    u.getDireccion() + "','" +
                    u.getTelefono() + "','" +
                    u.getMail() + "','" +
                    u.getCargo() + "','" +
                    u.getTipo_usuario() + "')";
            
            Statement stmt = conexion.createStatement();
            stmt.executeUpdate(consulta);
            stmt.close();
            conexion.close();
            return true;
        }catch (Exception err){
            return false;
        }
    }
    
    public boolean ModificarAdministrador(Usuario u){
        try{
            Conexion conn = new Conexion();
            Connection conexion = conn.getConnection();
            
            String consulta = "UPDATE usuario set rut='" + u.getRut() + "',nombre='" +
                    u.getNombre() + "',direccion='" +
                    u.getDireccion() + "',telefono='" +
                    u.getTelefono() + "', mail='" +
                    u.getMail() + "', tipo_usuario='" +
                    u.getTipo_usuario() + "', cargo='" +
                    u.getCargo() + "' where id=" + u.getId();
            
            Statement stmt = conexion.createStatement();
            stmt.executeUpdate(consulta);
            stmt.close();
            conexion.close();
            return true;
        }catch (Exception err){
            return false;
        }
    }
    
    public boolean EliminarAdministrador(int id){
        try{
            Conexion conn = new Conexion();
            Connection conexion = conn.getConnection();
            
            String consulta = "DELETE usuario where id="+id;
            
            Statement stmt = conexion.createStatement();
            
            stmt.executeUpdate(consulta);
            stmt.close();
            conexion.close();
            return true;
        }catch (Exception err){
            return false;
        }
    }
    
}
