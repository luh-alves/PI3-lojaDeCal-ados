/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caecae.pi3;

import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author rolucon
 */
public class ConnectionFactory {
    private static String nameBD = "MangaStore";
    private static final String IP = "localhost:3306";
    private static final String URL = "jdbc:mysql://"+IP+"/"+ nameBD;
    private static final String USER = "root";
    private static final String PW = "";
    
    public static java.sql.Connection getConnection(){
        try {
            return DriverManager.getConnection(URL,USER,PW);
        } catch (SQLException ex) {
            throw new RuntimeException("Erro de Conexao", ex);
        }
    }
    
}
