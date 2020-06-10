/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caecae.pi3.DAO;

import caecae.pi3.ConnectionFactory;
import caecae.pi3.exception.DaoException;
import caecae.pi3.model.ProdutoModel;
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
 * @author Luciana Alves
 */
public class ProdutoDao implements DaoInterface<ProdutoModel> {
    
    @Override
    public ArrayList<ProdutoModel> getAll() throws DaoException {
        String sql = "SELECT  prod_id ,prod_nome, prod_qtd, prod_preco, prod_descr, prod_filial FROM produto";
        List<ProdutoModel> produtos = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                ProdutoModel produto = new ProdutoModel();
                produto.setId(rs.getInt("prod_id"));
                produto.setNome(rs.getString("prod_nome"));
                produto.setQuantidade(rs.getInt("prod_qtd"));
                produto.setValor(rs.getDouble("prod_preco"));
                produto.setDescricao(rs.getString("prod_descr"));
                produto.setFilial(rs.getInt("prod_filial"));
                produtos.add(produto);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (ArrayList<ProdutoModel>) produtos;
    }
    
    @Override
    public boolean create(ProdutoModel t) throws DaoException {
        String sql = "INSERT INTO produto (prod_nome, prod_qtd, prod_preco, prod_descr, prod_filial) VALUES (?,?,?,?,?)";
        try (Connection conn = ConnectionFactory.getConnection()) {
            conn.setAutoCommit(false);
            try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setString(1, t.getNome());
                stmt.setInt(2, t.getQuantidade());
                stmt.setDouble(3, t.getValor());
                stmt.setString(4, t.getDescricao());
                stmt.setInt(5, t.getFilial());
                stmt.execute();
                conn.commit();
                return true;
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
    
    @Override
    public boolean delete(int id) throws DaoException {
        String sql = "DELETE FROM produto WHERE prod_id = ?";
        try (Connection conn = ConnectionFactory.getConnection()) {
            conn.setAutoCommit(false);
            try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setInt(1, id);
                stmt.execute();
                conn.commit();
                return true;
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
    
    @Override
    public ArrayList<ProdutoModel> get(String nome) throws DaoException {
        String sql = "SELECT * FROM produto where prod_nome like ?";
        List<ProdutoModel> produtos = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getConnection();) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, "%" + nome + "%");
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                ProdutoModel produto = new ProdutoModel();
                produto.setId(rs.getInt("prod_id"));
                produto.setNome(rs.getString("prod_nome"));
                produto.setQuantidade(rs.getInt("prod_qtd"));
                produto.setValor(rs.getDouble("prod_preco"));
                produto.setDescricao(rs.getString("prod_descr"));
                produto.setFilial(rs.getInt("prod_filial"));
                produtos.add(produto);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (ArrayList<ProdutoModel>) produtos;
        
    }
    
    @Override
    public boolean update(int id, ProdutoModel p) throws DaoException {
        boolean retorno = false;
        PreparedStatement instrucaoSQL = null;
        
        try {
            Connection conn = ConnectionFactory.getConnection();
            String sql = "UPDATE produto SET prod_nome = ?, prod_qtd = ?, prod_preco = ?, prod_descr = ?, prod_filial = ? WHERE prod_id = ?";
            instrucaoSQL = conn.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
            System.out.println(p.getId());
            instrucaoSQL.setString(1, p.getNome());
            instrucaoSQL.setInt(2, p.getQuantidade());
            instrucaoSQL.setDouble(3, p.getValor());
            instrucaoSQL.setString(4, p.getDescricao());
            instrucaoSQL.setInt(5, p.getFilial());
            instrucaoSQL.setInt(6, p.getId());
            
            int linhasAfetadas = instrucaoSQL.executeUpdate();
            
            retorno = linhasAfetadas > 0;
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            retorno = false;
        }
        
        return retorno;
    }
    
    @Override
    public ProdutoModel read(int id) throws DaoException {
        String sql = "SELECT * from produto WHERE prod_id = ?";
        
        try (Connection conn = ConnectionFactory.getConnection();) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                ProdutoModel produto = new ProdutoModel();
                produto.setId(rs.getInt("prod_id"));
                produto.setNome(rs.getString("prod_nome"));
                produto.setQuantidade(rs.getInt("prod_qtd"));
                produto.setValor(rs.getDouble("prod_preco"));
                produto.setDescricao(rs.getString("prod_descr"));
                produto.setFilial(rs.getInt("prod_filial"));
                return produto;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    // Precisa de login
    public ProdutoModel readFilial(int id, int filial) throws DaoException {
        String sql = "SELECT * from produto WHERE prod_id = ? and prod_filial = ?";
        
        try (Connection conn = ConnectionFactory.getConnection();) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.setInt(2, filial);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                ProdutoModel produto = new ProdutoModel();
                produto.setId(rs.getInt("prod_id"));
                produto.setNome(rs.getString("prod_nome"));
                produto.setQuantidade(rs.getInt("prod_qtd"));
                produto.setValor(rs.getDouble("prod_preco"));
                produto.setDescricao(rs.getString("prod_descr"));
                produto.setFilial(rs.getInt("prod_filial"));
                return produto;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
