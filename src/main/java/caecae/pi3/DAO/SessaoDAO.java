package caecae.pi3.DAO;

import caecae.pi3.ConnectionFactory;
import caecae.pi3.exception.DaoException;
import caecae.pi3.model.Sessao;
import caecae.pi3.service.AppException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mateus
 */
public class SessaoDAO {
    
    public void guardaSessao(String user) throws DaoException, AppException, SQLException {
        Sessao sessao = null;
        String sql = "Select * from Funcionario where func_user = ?";
           try (Connection conn = ConnectionFactory.getConnection();) {
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, user);
                ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                sessao = new Sessao();
                sessao.setUser(rs.getString("func_user"));
                sessao.setFilial(rs.getInt("func_filial"));
                sessao.setCargo(rs.getString("func_cargo"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(SessaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
           
}
}
