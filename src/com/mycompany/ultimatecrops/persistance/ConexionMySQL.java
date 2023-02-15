/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ultimatecrops.persistance;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

/**
 *
 * @author asier
 */
public class ConexionMySQL {
    
    private Connection connection;

    private String host;
    private int puerto;
    private String database;
    private String usuario;
    private String password;

    public ConexionMySQL(String host, int puerto, String database, String usuario, String password) {        
        this.host = host;
        this.puerto = puerto;
        this.database = database;
        this.usuario = usuario;
        this.password = password;
        try {
            synchronized(this){

                if(connection != null && !connection.isClosed()){
                    Bukkit.getConsoleSender().sendMessage(ChatColor.RED+"Error al conectar UltimateCrops con MySQL");
                    return;
                }
                Class.forName("com.mysql.jdbc.Driver");
                this.connection = DriverManager.getConnection("jdbc:mysql://"+this.host+":"+this.puerto+"/"+this.database,this.usuario,this.password);

                Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN+"Se ha conectado UltimateCrops con MySQL correctamente");
            }
        } 
        catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ConexionMySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Connection getConnection() {
        return connection;
    }
    
    
}
