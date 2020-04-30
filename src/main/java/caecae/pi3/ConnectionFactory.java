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
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static String nameBD = "lorentiBD";
    private static final String URL = "jdbc:mysql://localhost:3306/" + nameBD;
    private static final String USER = "root";
    private static final String PW = "";
    
    public static java.sql.Connection getConnection() throws ClassNotFoundException{
        System.out.println(URL);
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL,USER,PW);
        } catch (SQLException ex) {
            throw new RuntimeException("Erro de Conexao", ex);
        }
    }
    
}
