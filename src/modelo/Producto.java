/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author v-carica
 */
public class Producto {
    private int id;
    private String id_producto;
    private String descripcion;
    private int precio;
    private int stock;
    private int stock_critico;
    private int tipo_producto_id;
    
    public Producto(){
        this.id = 0;
        this.id_producto = "";
        this.precio = 0;
        this.stock = 0;
        this.stock_critico = 0;
        this.tipo_producto_id = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getId_producto() {
        return id_producto;
    }

    public void setId_producto(String id_producto) {
        this.id_producto = id_producto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getStock_critico() {
        return stock_critico;
    }

    public void setStock_critico(int stock_critico) {
        this.stock_critico = stock_critico;
    }

    public int getTipo_producto_id() {
        return tipo_producto_id;
    }

    public void setTipo_producto_id(int tipo_producto_id) {
        this.tipo_producto_id = tipo_producto_id;
    }
    
    
}
