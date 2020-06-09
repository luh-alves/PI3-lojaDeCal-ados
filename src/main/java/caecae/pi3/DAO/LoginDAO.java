package caecae.pi3.DAO;

import caecae.pi3.ConnectionFactory;
import caecae.pi3.exception.DaoException;
import caecae.pi3.model.Login;
import caecae.pi3.service.AppException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.mindrot.jbcrypt.BCrypt;
public class LoginDAO {
PreparedStatement stmt;
ResultSet rs;
Connection con;
    /**
     *
     * @param usuario
     * @return
     * @throws DaoException
     * @throws java.sql.SQLException
     */
    static Login login = new Login();

    public static boolean verifUser(String usuario, String senha) throws DaoException, SQLException {
        String sql = "SELECT func_user , func_senha FROM funcionario WHERE func_user = ?";

        try (Connection conn = ConnectionFactory.getConnection();) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, usuario);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                login.setUser(rs.getString("func_user"));
                login.setSenha(rs.getString("func_senha"));
            }
            if (usuario.equals(login.getUser())) {
                return BCrypt.checkpw(senha, login.getSenha());
            } else {
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoginDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

}
