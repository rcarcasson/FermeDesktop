/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import controlador.ControladorTipoProducto;

/**
 *
 * @author Ricardo Carcassón
 */
public class TipoProducto {
    private int id;
    private int secuencia;
    private String descripcion;
    private int familia_producto_id;
    
    public TipoProducto(){
        this.id = 0;
        this.secuencia = 0;
        this.descripcion = "";
        this.familia_producto_id = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSecuencia() {
        return secuencia;
    }

    public void setSecuencia(int secuencia) {
        this.secuencia = secuencia;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getFamilia_producto_id() {
        return familia_producto_id;
    }

    public void setFamilia_producto_id(int familia_producto_id) {
        this.familia_producto_id = familia_producto_id;
    }
        
}
