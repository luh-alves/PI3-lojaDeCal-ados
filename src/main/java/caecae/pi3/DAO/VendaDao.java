/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caecae.pi3.DAO;

import caecae.pi3.ConnectionFactory;
import caecae.pi3.exception.DaoException;
import caecae.pi3.model.ProdutoModel;
import caecae.pi3.model.VendaModel;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

//        create table venda(
//    venda_id integer auto_increment not null unique,
//    venda_data_venda datetime default current_timestamp,
//    venda_func smallint not null,
//    venda_val_total numeric(10,2) not null,
/**
 *
 * @author rolucon
 */
public class VendaDao implements DaoInterface<VendaModel>{
    /** Retorna todas as vendas, se nao tiver vendas retorna null
    *@return ArrayList<ProdutoModel> - Lista de Todas as Vendas
    * @throws DaoException
    */
    @Override
    public ArrayList<VendaModel> getAll() throws DaoException {
        
        String sql = "SELECT * FROM venda";
        
        ArrayList<VendaModel> vendas = null;
        
        try(Connection con = ConnectionFactory.getConnection()){
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            stmt.close();
            vendas = new ArrayList<>();
            while(rs.next()){
                VendaModel venda = new VendaModel();
                venda.setIdVenda(rs.getInt("venda_id"));
                venda.setIdCliente(rs.getInt("venda_cli"));
                venda.setIdFuncionario(rs.getInt("venda_func"));
                venda.setValorTotal(rs.getDouble("venda_val_total"));
                venda.setDataVenda(rs.getDate("venda_data_venda"));
                
                //Percorre e pega todas os produtos vendidos no id da compra
                ArrayList<ProdutoModel> produtos = new ArrayList<>();
                String sqlProd = "SELECT * FROM itens WHERE id = ?";
                stmt = con.prepareStatement(sql);
                stmt.setInt(1, venda.getIdVenda());
                ResultSet rsProd = stmt.executeQuery();
                
                while(rsProd.next()){
                    //Cria produto e add na lista
                }
                rsProd.close();
                stmt.close();
                venda.setProdutos(produtos);
            }
            rs.close();
            con.close();
        } catch (SQLException ex) {
           throw new RuntimeException("ERRO getALL VendaDAO", ex);
        } finally {
            return vendas;
        }
            
    }
    /** Regista uma venda no Banco de dados
    * @param venda - Venda a ser registrada
    * @return boolean - confirma se a operacao foi realizada
    * @throws DaoException
    */
    @Override
    public boolean create(VendaModel venda) throws DaoException {
//        Ajustar para a venda
        int vendaId;
        String sql = "INSERT INTO venda (venda_cli_id, venda_func, venda_val_total, venda_data_venda) VALUES (?,?,?,?)";
        try (Connection conn = ConnectionFactory.getConnection()) {
            conn.setAutoCommit(false);
            try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setInt(1, venda.getIdCliente());
                stmt.setInt(2, 1);//venda.getIdFuncionario());
                stmt.setDouble(3, venda.getValorTotal());
                stmt.setDate(4, new java.sql.Date(venda.getDataVenda().getTime()));
                stmt.executeUpdate();
                ResultSet rs = stmt.getGeneratedKeys();
                rs.next();
                vendaId = (int) rs.getLong(1);
                for (ProdutoModel prod : venda.getProdutos()) {
                    sql = "INSERT INTO itens (it_produto, it_venda, it_valor_prod, it_qtd) VALUES(?,?,?,?)";
                    try(PreparedStatement stmtProd = conn.prepareStatement(sql)){
                        stmtProd.setInt(1, prod.getId());
                        stmtProd.setInt(2, vendaId);
                        stmtProd.setDouble(3, prod.getValor());
                        stmtProd.setInt(4, prod.getQuantidade());
                        stmtProd.execute();
                        stmtProd.close();
                    }
                }
                conn.commit();
                return true;
            } catch (SQLException e) {
                conn.rollback();
                System.out.println(e.getMessage());
                throw new RuntimeException(e);
            }
        } catch (SQLException ex) {
            throw new RuntimeException("ERRO create-con VendaDAO", ex);
        }
    }  

    @Override
    public VendaModel read(int id) throws DaoException {
        return null;
    }

    @Override
    public boolean update(VendaModel venda) throws DaoException {
        return false;
    }

    //Nao Testado
    @Override
    public boolean delete(int id) throws DaoException {
        String sql = "DELETE itens.*, venda.* FROM itens, venda WHERE itens.it_venda = ? AND venda.venda_id = ?";
        try (Connection conn = ConnectionFactory.getConnection()) {
            conn.setAutoCommit(false);
            try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setInt(1, id);
                stmt.setInt(2, id);
                stmt.execute();
                conn.commit();
                return true;
            } catch (SQLException e) {
                conn.rollback();
                System.out.println(e.getMessage());
                throw new RuntimeException(e);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            throw new RuntimeException(ex);
        }
    }

    @Override
    public ArrayList get(String nome) throws DaoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
