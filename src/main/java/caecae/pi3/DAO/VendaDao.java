/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caecae.pi3.DAO;

import caecae.pi3.ConnectionFactory;
import caecae.pi3.exception.DaoException;
import caecae.pi3.model.VendaModel;
import java.sql.Connection;
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
    *@return ArrayList<Object> - Lista de Todas as Vendas 
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
                ArrayList<Object> produtos = new ArrayList<>();
                String sqlProd = "SELECT * FROM vendaProduto WHERE id = ?";
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
            
        } catch (SQLException ex) {;;
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
        
        String sql = "INSERT INTO venda (venda_data_venda, venda_cli, venda_func,"
                + " venda_val_total) VALUES (?,?,?,?)";

        try (Connection con = ConnectionFactory.getConnection()) {
            con.setAutoCommit(false);
            try (PreparedStatement stmt = con.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS)){
                stmt.setDate(1, java.sql.Date.valueOf(LocalDate.now()));
                stmt.setInt(2, venda.getIdCliente());
                stmt.setInt(3, venda.getIdFuncionario());
                stmt.setDouble(4, venda.getValorTotal());
    
                // Pega o id gerado para a venda
                int idVenda = stmt.executeUpdate();
                stmt.close(); // Verificar o close do stmt - Onde deve ficar
                
                //Percorrer produtos e add a vendas.
                if (!venda.getProdutos().isEmpty()){
                    for (Object produto : venda.getProdutos()) {
                        
                    }
                }
                con.commit();
            } catch(Exception e) {
                con.rollback();
                throw new RuntimeException("ERRO create-stmt VendaDAO", e);
//                return false;
            }
        } catch (SQLException ex) {
            throw new RuntimeException("ERRO create-con VendaDAO", ex);
//            return false;
        } finally {
            return true;
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

    @Override
    public boolean delete(int id) throws DaoException {
        return false;
    }

    @Override
    public ArrayList get(String nome) throws DaoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
