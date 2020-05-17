/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caecae.pi3.DAO;

import caecae.pi3.ConnectionFactory;
import caecae.pi3.exception.DaoException;
import caecae.pi3.model.Cliente;
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

/**
 *
 * @author Felipe
 */
public class ClienteDAO {
    
     public static List<Cliente> listar() throws DaoException {
        String sql = "SELECT  cli_id ,cli_cpf, cli_nome, cli_email, cli_sexo FROM cliente";
        List<Cliente> clientes = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setId(rs.getInt("cli_id"));
                cliente.setCpf(rs.getString("cli_cpf"));
                cliente.setNome(rs.getString("cli_nome"));
                cliente.setEmail(rs.getString("cli_email"));
                cliente.setSexo(rs.getString("cli_sexo"));
                clientes.add(cliente);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (ArrayList<Cliente>) clientes;
    
       }
            
    
    public void incluir(Cliente c) throws DaoException {
         String sql = "INSERT INTO cliente (cli_cpf, cli_nome, cli_email, cli_sexo) VALUES (?,?,?,?)";
        try (Connection conn = ConnectionFactory.getConnection()) {
            // DESLIGAR AUTO-COMMIT -> POSSIBILITAR DESFAZER OPERAÇÕES NO BD CASO OCORRA ERRO
            conn.setAutoCommit(false);

            // ADICIONAR O Statement.RETURN_GENERATED_KEYS PARA RECUPERAR ID GERADO NO BD
            try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setString(1, c.getCpf());
                stmt.setString(2, c.getNome());
                stmt.setString(3, c.getEmail());
                stmt.setString(4, String.valueOf(c.getSexo()));
                stmt.execute();
                }
                // EFETIVAR NO BD TODAS AS OPERACOES REALIZADAS
                conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
       
    }

    public void excluir(int id) throws DaoException, AppException {
        String sql = "DELETE FROM cliente WHERE cli_id = ?";
        try (Connection conn = ConnectionFactory.getConnection()) {
            conn.setAutoCommit(false);
            try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setInt(1, id);
                stmt.execute();
                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                System.out.println(e.getMessage());
                throw new DaoException(e);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            throw new DaoException(ex);
        }
        
    }
    
    public void alterar(Cliente c) throws SQLException{
        
    }
    
}
