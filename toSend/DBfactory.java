/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemaclinica.core.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pbeanni
 */
public class DBfactory {
    private static Connection conexao;
    
    public static Connection getConnection(){
        try {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/GestaoClinicaDB";
            Properties props = new Properties();
            props.setProperty("user", "postgres");
            props.setProperty("password", "root");

             conexao = DriverManager.getConnection(url, props);

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBfactory.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DBfactory.class.getName()).log(Level.SEVERE, null, ex);
        }
 return conexao;
    }
    public static void closeConnection() throws SQLException{
        conexao.close();
    }
}
