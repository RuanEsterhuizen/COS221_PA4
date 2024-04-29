/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PA4;
import java.sql.*;
import java.io.*;

/**
 *
 * @author ruben
 */
public class Connector {
    
    private String url;
    private String username;
    private String pass;
    private Connection conn;
    
    public Connector(String username, String pass){
        //Class.forName("org.mariadb.jdbc.Driver");
        url = "jdbc:mariadb://localhost:3306/sakila";
        this.username = username;
        this.pass = pass;
        
        try {
            this.conn = DriverManager.getConnection(url, this.username, this.pass);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
    
    public Connection getConnection(){
        return conn;
    }
}
