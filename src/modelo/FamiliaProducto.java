/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import controlador.ControladorFamiliaProducto;
import java.util.ArrayList;

/**
 *
 * @author v-carica
 */
public class FamiliaProducto {
    private int id;
    private String id_familia;
    private String descripcion;

    public FamiliaProducto(int id, String id_familia, String descripcion) {
        this.id = id;
        this.id_familia = id_familia;
        this.descripcion = descripcion;
    }
    
    public FamiliaProducto(){
        this.id = 0;
        this.id_familia = "";
        this.descripcion = "";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getId_familia() {
        return id_familia;
    }

    public void setId_familia(String id_familia) {
        this.id_familia = id_familia;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public ArrayList<FamiliaProducto> ObtenerFamilias(){
        ControladorFamiliaProducto ctrlFP = new ControladorFamiliaProducto();
        return ctrlFP.ObtenerFamilias();
    }
    
}
