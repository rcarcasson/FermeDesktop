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
import modelo.Producto;

/**
 *
 * @author Ricardo Carcassón
 */
public class ControladorProducto {
    public ArrayList<Producto> ObtenerProductos(String codigo){
        ArrayList<Producto> listaproductos = new ArrayList<>();
        try{
            Conexion conn = new Conexion();
            Connection conexion = conn.getConnection();
            
            String consulta="";
            if (!codigo.isEmpty()){
                consulta = "SELECT ID,ID_PRODUCTO,DESCRIPCION,PRECIO,STOCK,STOCK_CRITICO,TIPO_PRODUCTO_ID from PRODUCTO where ID_PRODUCTO LIKE '" + codigo +"%'";
            }else{
                consulta = "SELECT ID,ID_PRODUCTO,DESCRIPCION,PRECIO,STOCK,STOCK_CRITICO,TIPO_PRODUCTO_ID from PRODUCTO";
            }
            
            
            Statement stmt = conexion.createStatement();
            ResultSet rst = stmt.executeQuery(consulta);
            
            while(rst.next()){
                Producto p = new Producto();
                p.setId(rst.getInt("id"));
                p.setId_producto(rst.getString("id_producto"));
                p.setDescripcion(rst.getString("descripcion"));
                p.setPrecio(rst.getInt("precio"));
                p.setStock(rst.getInt("stock"));
                p.setStock_critico(rst.getInt("stock_critico"));
                p.setTipo_producto_id(rst.getInt("tipo_producto_id"));
                listaproductos.add(p);
            }
            rst.close();
            stmt.close();
            conexion.close();
            return listaproductos;
        }catch (Exception ex){
            return new ArrayList<>();
        }
        
    }

    public boolean AgregarProducto(Producto p){
        try{
            Conexion conn = new Conexion();
            Connection conexion = conn.getConnection();
            
            String consulta = "INSERT INTO PRODUCTO (ID,ID_PRODUCTO,DESCRIPCION,PRECIO,STOCK,STOCK_CRITICO,TIPO_PRODUCTO_ID) VALUES (SQ_PRODUCTO.NextVal,'"+
                    p.getId_producto() + "','" +
                    p.getDescripcion() + "'," +
                    p.getPrecio() + "," +
                    p.getStock() + "," +
                    p.getStock_critico() + "," +
                    p.getTipo_producto_id() + ")";
            
            Statement stmt = conexion.createStatement();
            stmt.executeUpdate(consulta);
            stmt.close();
            conexion.close();
            return true;
        }catch (Exception err){
            return false;
        }
    }
    
    public boolean ActualizarProducto(Producto p){
        try{
            Conexion conn = new Conexion();
            Connection conexion = conn.getConnection();
            
            String consulta = "UPDATE producto set descripcion='"+
                    p.getDescripcion()+"',precio="+
                    p.getPrecio()+",stock="+
                    p.getStock()+",stock_critico="+
                    p.getTipo_producto_id()+" where id="+p.getId();
            
            Statement stmt = conexion.createStatement();
            stmt.executeUpdate(consulta);
            stmt.close();
            conexion.close();
            return true;
        }catch (Exception err){
            return false;
        }
    }
    
    public boolean EliminarProducto(int id){
        try{
            Conexion conn = new Conexion();
            Connection conexion = conn.getConnection();
            
            String consulta = "DELETE FROM producto where id="+id;
            
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
