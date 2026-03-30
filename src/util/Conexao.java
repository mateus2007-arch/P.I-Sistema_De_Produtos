/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

/**
 *
 * @author mateu
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    public static Connection conectar(){
        try{
            return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/gamestorage",
                "root",
                "senha123" 
            );
        }catch(SQLException e){
            return null;
        }
    }
}
