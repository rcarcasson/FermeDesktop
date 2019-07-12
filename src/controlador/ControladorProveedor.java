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
import modelo.Proveedor;

/**
 *
 * @author Ricardo Carcassón
 */
public class ControladorProveedor {
    public ArrayList<Proveedor> ObtenerProveedores(){
        ArrayList<Proveedor> listaproveedores = new ArrayList<>();
        try{
            Conexion conn = new Conexion();
            Connection conexion = conn.getConnection();
            
            String consulta = "SELECT ID,RUT,NOMBRE,DIRECCION,TELEFONO,RUBRO,MAIL from PROVEEDOR";
            
            Statement stmt = conexion.createStatement();
            
            ResultSet rst = stmt.executeQuery(consulta);
            
            while(rst.next()){
                Proveedor p = new Proveedor();
                p.setId(rst.getInt("id"));
                p.setRut(rst.getString("rut"));
                p.setNombre(rst.getString("nombre"));
                p.setDireccion(rst.getString("direccion"));
                p.setTelefono(rst.getString("telefono"));
                p.setRubro(rst.getString("rubro"));
                p.setMail(rst.getString("mail"));
                listaproveedores.add(p);
            }
            rst.close();
            stmt.close();
            conexion.close();
            return listaproveedores;
        }catch (Exception err){
            return new ArrayList<Proveedor>();
        }
    }

    public ArrayList<Proveedor> ObtenerProveedores(String cadena){
        ArrayList<Proveedor> listaproveedores = new ArrayList<>();
        try{
            Conexion conn = new Conexion();
            Connection conexion = conn.getConnection();
            
            String consulta = "SELECT ID,RUT,NOMBRE,DIRECCION,TELEFONO,RUBRO,MAIL from PROVEEDOR where UPPER(rut) like '"+cadena.toUpperCase()+"%' or UPPER(nombre) like '"+cadena.toUpperCase()+"%'";
            
            Statement stmt = conexion.createStatement();
            
            ResultSet rst = stmt.executeQuery(consulta);
            
            while(rst.next()){
                Proveedor p = new Proveedor();
                p.setId(rst.getInt("id"));
                p.setRut(rst.getString("rut"));
                p.setNombre(rst.getString("nombre"));
                p.setDireccion(rst.getString("direccion"));
                p.setTelefono(rst.getString("telefono"));
                p.setRubro(rst.getString("rubro"));
                p.setMail(rst.getString("mail"));
                listaproveedores.add(p);
            }
            rst.close();
            stmt.close();
            conexion.close();
            return listaproveedores;
        }catch (Exception err){
            return new ArrayList<Proveedor>();
        }
    }

    
    public ArrayList<Proveedor> FiltroProveedores(String nombre){
        ArrayList<Proveedor> listaproveedores = new ArrayList<>();
        try{
            Conexion conn = new Conexion();
            Connection conexion = conn.getConnection();
            
            String consulta = "SELECT ID,RUT,NOMBRE,DIRECCION,TELEFONO,RUBRO,MAIL from PROVEEDOR where UPPER(NOMBRE) LIKE '"+nombre.toUpperCase()+"%'";
            
            Statement stmt = conexion.createStatement();
            
            ResultSet rst = stmt.executeQuery(consulta);
            
            while(rst.next()){
                Proveedor p = new Proveedor();
                p.setId(rst.getInt("id"));
                p.setRut(rst.getString("rut"));
                p.setNombre(rst.getString("nombre"));
                p.setDireccion(rst.getString("direccion"));
                p.setTelefono(rst.getString("telefono"));
                p.setRubro(rst.getString("rubro"));
                p.setMail(rst.getString("mail"));
                listaproveedores.add(p);
            }
            rst.close();
            stmt.close();
            conexion.close();
            return listaproveedores;
        }catch (Exception err){
            return new ArrayList<Proveedor>();
        }        
    }
    
    public String ObtenerNombre(int id){
        String nombre="";
        try{
            Conexion conn = new Conexion();
            Connection conexion = conn.getConnection();
            
            String consulta ="SELECT nombre from proveedor where id="+id;
            
            Statement stmt = conexion.createStatement();
            ResultSet rst = stmt.executeQuery(consulta);
            
            rst.next();
            nombre = rst.getString("nombre");
            rst.close();
            stmt.close();
            conexion.close();
            return nombre;
        }catch (Exception err){
            return "";
        }
    }
    
    public boolean AgregarProveedor(Proveedor p){
        try{
            Conexion conn = new Conexion();
            Connection conexion = conn.getConnection();
            
            String consulta = "INSERT INTO PROVEEDOR VALUES (SQ_PROVEEDOR.NextVal, '" +
                    p.getRut() +"','" + 
                    p.getNombre() + "','" +
                    p.getDireccion() + "','" +
                    p.getTelefono() + "','" +
                    p.getRubro() + "','" +
                    p.getMail() + "')";
            
            Statement stmt = conexion.createStatement();
            
            stmt.executeUpdate(consulta);
            stmt.close();
            conexion.close();
            return true;
        }catch (Exception err){
            return false;
        }
    }
    
    public boolean ModificarProveedor(Proveedor p){
        try{
            Conexion conn = new Conexion();
            Connection conexion = conn.getConnection();
            
            String consulta = "UPDATE proveedor set rut='" +
                    p.getRut() + "', nombre='" +
                    p.getNombre() + "', direccion='" +
                    p.getDireccion() + "', telefono='" +
                    p.getTelefono() + "', rubro='" +
                    p.getRubro() + "', mail='" +
                    p.getMail() + "' where id="+p.getId();
            
            Statement stmt = conexion.createStatement();
            stmt.executeUpdate(consulta);
            stmt.close();
            conexion.close();
            return true;
        }catch (Exception err){
            return false;
        }
    }
    
    public boolean EliminarProveedor(int id){
        try{
            Conexion conn = new Conexion();
            Connection conexion = conn.getConnection();
            
            String consulta = "DELETE proveedor where id=" + id;
            
            Statement stmt = conexion.createStatement();
            stmt.executeUpdate(consulta);
            stmt.close();
            conexion.close();
            return true;
        }catch (Exception err){
            return false;
        }
    }
    
    public boolean ExisteRut(String rut){
        boolean existe = false;
        try{
            Conexion conn = new Conexion();
            Connection conexion = conn.getConnection();
            
            String consulta = "SELECT ID from proveedor where UPPER(rut)='" + rut.toUpperCase() +"'";
            
            Statement stmt = conexion.createStatement();
            ResultSet rst = stmt.executeQuery(consulta);
            
            rst.next();
            if (rst.getRow()>0){
                existe = true;
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
        boolean existe = false;
        try{
            Conexion conn = new Conexion();
            Connection conexion = conn.getConnection();
            
            String consulta = "SELECT ID from proveedor where UPPER(mail)='" + mail.toUpperCase() +"'";
            
            Statement stmt = conexion.createStatement();
            ResultSet rst = stmt.executeQuery(consulta);
            
            rst.next();
            if (rst.getRow()>0){
                existe = true;
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
