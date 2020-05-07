/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caecae.pi3.DAO;

import caecae.pi3.ConnectionFactory;
import caecae.pi3.model.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Felipe
 */
public class ClienteDAO {
    
     public List<Cliente> listar() throws SQLException, ClassNotFoundException {
         return null;
       }
            
    
    public void incluir(Cliente c) throws SQLException, ClassNotFoundException {
         String sql = "INSERT INTO cliente (cli_cpf, cli_nome, cli_email, cli_sexo) VALUES (?,?,?,?,?)";
        try (Connection conn = ConnectionFactory.getConnection()) {
            // DESLIGAR AUTO-COMMIT -> POSSIBILITAR DESFAZER OPERAÇÕES NO BD CASO OCORRA ERRO
            conn.setAutoCommit(false);

            // ADICIONAR O Statement.RETURN_GENERATED_KEYS PARA RECUPERAR ID GERADO NO BD
            try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setString(1, c.getNome());
                stmt.setString(2, c.getCpf());
                stmt.setString(3, c.getEmail());
                stmt.setString(4, String.valueOf(c.getSexo()));
                }
                // EFETIVAR NO BD TODAS AS OPERACOES REALIZADAS
                conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
       
    }
    
    public void excluir(Cliente c) throws SQLException {
        
    }
    
    public void alterar(Cliente c) throws SQLException{
        
    }
    
}
