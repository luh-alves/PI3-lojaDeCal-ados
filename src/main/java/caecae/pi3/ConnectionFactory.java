/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caecae.pi3;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rolucon
 */
public class ConnectionFactory {
    private static final String DB_NAME = "lorentiBD";
    private static final String URL = "jdbc:mysql://localhost:3306/" + DB_NAME+"?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PW = "";

    public static Connection getConnection() throws SQLException {
        // Mudei a forma de conseguir a conexão porque não estava funcionando e baseado em https://stackoverflow.com/a/2839563
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUser(USER);
        dataSource.setPassword(PW);
        System.out.println(URL);
        dataSource.setURL(URL);
        try {
            Connection connection = dataSource.getConnection();
            return connection;
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
            throw new SQLException("Erro de Conexao", ex);
        }
    }

}
