/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author v-carica
 */
public class Conexion {
    //Metodo para levantar conexion
    public Connection getConnection() throws ClassNotFoundException, SQLException{
        Connection conn = null;
        
        Class.forName("oracle.jdbc.OracleDriver");
        String BaseDeDatos = "jdbc:oracle:thin:@localhost:1521:xe";
        conn = DriverManager.getConnection(BaseDeDatos, "FERME", "ferme2018");
        if (conn != null)
        {
            System.out.println("CONEXION EXITOSA!");
        }else{
            System.out.println("ERROR");
        }
        return conn;
    }
}
