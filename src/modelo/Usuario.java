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
public class Usuario {
    private int id;
    private String rut;
    private String nombre;
    private String direccion;
    private String telefono;
    private String mail;
    private String cargo;
    private char tipo_usuario;
    private char tipo_cliente;
    
    public Usuario(){
        this.id = 0;
        this.rut = "";
        this.nombre = "";
        this.direccion = "";
        this.telefono = "";
        this.mail = "";
        this.cargo = "";
        this.tipo_usuario = ' ';
        this.tipo_cliente = ' ';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getMail() {
        return mail;
    }
    
    public String getCargo(){
        return this.cargo;
    }
    
    public void setCargo(String cargo){
        this.cargo = cargo;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
    
    public void setTipo_usuario(char tipo_usuario){
        this.tipo_usuario = tipo_usuario;
    }
    
    public char getTipo_usuario(){
        return this.tipo_usuario;
    }

    public char getTipo_cliente() {
        return tipo_cliente;
    }

    public void setTipo_cliente(char tipo_cliente) {
        this.tipo_cliente = tipo_cliente;
    }
    
}
