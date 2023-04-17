/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author lenovo
 */
public class Database {
    private String url = "jdbc:mysql://localhost:3306/wehealth2";
    private String user = "root";
    private String password = "";
    
    private Connection cnx;
    
    private static Database instance;

    private Database() {
        try {
            cnx = DriverManager.getConnection(url, user, password);
            System.out.println("connexion établie");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public static Database getInstance(){
        if(instance == null)
            instance = new Database();
        return instance;
    }

    public Connection getCnx() {
        return cnx;
    }
    
    
    
 
    
}
