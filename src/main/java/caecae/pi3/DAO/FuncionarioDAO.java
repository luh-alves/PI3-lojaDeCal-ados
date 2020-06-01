/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caecae.pi3.DAO;

import caecae.pi3.ConnectionFactory;
import caecae.pi3.exception.DaoException;
import caecae.pi3.model.Funcionario;
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
public class FuncionarioDAO {
    
     public static List<Funcionario> listar() throws DaoException {
        String sql = "SELECT  func_id, func_nome, func_cpf, func_email, func_celular, func_sexo, func_cargo, func_user, func_senha FROM funcionario";
        List<Funcionario> funcionarios = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Funcionario funcionario = new Funcionario();
                funcionario.setId(rs.getInt("func_id"));
                funcionario.setNome(rs.getString("func_nome"));
                funcionario.setCpf(rs.getString("func_cpf"));
                funcionario.setEmail(rs.getString("func_email"));
                funcionario.setCelular(rs.getString("func_celular"));
                funcionario.setSexo("func_sexo");
                funcionario.setCargo("func_cargo");
                funcionario.setUser("func_user");
                funcionario.setSenha("func_senha");
                funcionarios.add(funcionario);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (ArrayList<Funcionario>) funcionarios;
    
       }
            
    
    public void incluir(Funcionario f) throws DaoException {
         String sql = "INSERT INTO funcionario (func_nome, func_cpf, func_email, func_celular, func_sexo, func_cargo, func_user, func_senha) VALUES (?,?,?,?,?,?,?,?)";
        try (Connection conn = ConnectionFactory.getConnection()) {
            // DESLIGAR AUTO-COMMIT -> POSSIBILITAR DESFAZER OPERAÇÕES NO BD CASO OCORRA ERRO
            conn.setAutoCommit(false);

            // ADICIONAR O Statement.RETURN_GENERATED_KEYS PARA RECUPERAR ID GERADO NO BD
            try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setString(1, f.getNome());
                stmt.setString(2, f.getCpf());
                stmt.setString(3, f.getEmail());
                stmt.setString(4, f.getCelular());
                stmt.setString(5, f.getSexo());
                stmt.setString(6, f.getCargo());
                stmt.setString(7, f.getUser());
                stmt.setString(8, f.getSenha());
                //stmt.setDate(5, (java.sql.Date)c.getDataNascimento());
                stmt.execute();
                
                }
                // EFETIVAR NO BD TODAS AS OPERACOES REALIZADAS
                conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
       
    }

    public void excluir(int id) throws DaoException, AppException {
        String sql = "DELETE FROM funcionario WHERE func_id = ?";
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
    
    public void alterar(Funcionario f) throws DaoException, AppException{
         String sql = "UPDATE funcionario set  func_nome = ?, func_cpf = ?, func_email = ?, func_celular = ?, func_sexo = ?, func_cargo = ?, func_user = ?, func_senha  = ? WHERE func_id = ?";
        try (Connection conn = ConnectionFactory.getConnection()) {
            conn.setAutoCommit(false);

            try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setString(1, f.getNome());
                stmt.setString(2, f.getCpf());
                stmt.setString(3, f.getEmail());
                stmt.setString(4, f.getCelular());
                stmt.setString(5, f.getSexo());
                stmt.setString(6, f.getCargo());
                stmt.setString(7, f.getUser());
                stmt.setString(8, f.getSenha());
                stmt.setInt(9, f.getId());
                stmt.execute();
                }
            //Efetivar operações
                conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public Funcionario buscar(String cpf) throws DaoException, AppException {
        Funcionario funcionario = null;
        String sql = "Select * from FUNCIONARIO where func_cpf = ?";
           try (Connection conn = ConnectionFactory.getConnection();) {
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, cpf);
                ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                funcionario = new Funcionario();
                funcionario.setNome("func_nome");
                funcionario.setCpf("func_cpf");
                funcionario.setEmail("func_email");
                funcionario.setCelular("func_celular");
                funcionario.setSexo("func_sexo");
                funcionario.setCargo("func_cargo");
                funcionario.setUser("func_user");
                funcionario.setSenha("func_senha");
            }
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return funcionario;
    }
    
}
