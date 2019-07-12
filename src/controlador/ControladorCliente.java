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
public class ControladorCliente {
    
    public ArrayList<Usuario> ObtenerClientes(String cadena){
        ArrayList<Usuario> clientes = new ArrayList<>();
        try{
            Conexion conn = new Conexion();
            Connection conexion = conn.getConnection();
            
            String consulta = "SELECT ID,RUT,NOMBRE,DIRECCION,TELEFONO,MAIL,TIPO_CLIENTE FROM USUARIO WHERE TIPO_USUARIO='C' AND (UPPER(RUT) LIKE '" + cadena.toUpperCase() + "%' OR UPPER(NOMBRE) LIKE '" + cadena.toUpperCase() + "%' "
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
                u.setTipo_cliente((char)rst.getString("tipo_cliente").charAt(0));
                clientes.add(u);
            }
            
            rst.close();
            stmt.close();
            conexion.close();
            return clientes;
        }catch (Exception err){
            return new ArrayList<>();
        }
    }
    
    public boolean AgregarCliente(Usuario u){
        try{
            Conexion conn = new Conexion();
            Connection conexion = conn.getConnection();
            
            String consulta = "INSERT INTO usuario (ID,RUT,NOMBRE,DIRECCION,TELEFONO,MAIL,TIPO_USUARIO,TIPO_CLIENTE) VALUES (SQ_USUARIO.NextVal,'" +
                    u.getRut() + "','" +
                    u.getNombre() + "','" +
                    u.getDireccion() + "','" +
                    u.getTelefono() + "','" +
                    u.getMail() + "','C','" +
                    u.getTipo_cliente() +"')";
            
            Statement stmt = conexion.createStatement();
            stmt.executeUpdate(consulta);
            stmt.close();
            conexion.close();
            return true;
        }catch (Exception err){
            return false;
        }
    }
    
    public boolean ModificarCliente(Usuario u){
        try{
            Conexion conn = new Conexion();
            Connection conexion = conn.getConnection();
            
            String consulta = "UPDATE usuario set rut='" + u.getRut() + "',nombre='" +
                    u.getNombre() + "',direccion='" +
                    u.getDireccion() + "',telefono='" +
                    u.getTelefono() + "', mail='" +
                    u.getMail() + "', tipo_cliente='" +
                    u.getTipo_cliente() + "' where id=" + u.getId();
            
            Statement stmt = conexion.createStatement();
            stmt.executeUpdate(consulta);
            stmt.close();
            conexion.close();
            return true;
        }catch (Exception err){
            return false;
        }
    }
    
    public boolean EliminarCliente(int id){
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
