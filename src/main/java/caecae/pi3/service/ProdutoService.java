/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caecae.pi3.service;

import caecae.pi3.DAO.ProdutoDao;
import caecae.pi3.exception.DaoException;
import caecae.pi3.model.ProdutoModel;
import java.util.List;

/**
 *
 * @author Luciana Alves
 */
public class ProdutoService {

    private ProdutoDao produtoDao = new ProdutoDao();

    private boolean isValid(ProdutoModel produto) {
        return produto != null && produto.getNome() != null && produto.getDescricao() != null
                && produto.getValor() == 0 && produto.getQuantidade() == 0;
    }

    public void incluir(ProdutoModel produto) throws AppException {
//        if (!isValid(produto)) {
//            throw new AppException("DADOS INVALIDOS", null);
//        }
        try {
            produtoDao.create(produto);
        } catch (DaoException ex) {
            System.out.println(ex.getMessage());
            throw new AppException(ex.getMessage(), ex);
        }

    }

    public List<ProdutoModel> listarTodos() throws AppException {
        List<ProdutoModel> lista;
        try {
            lista = produtoDao.getAll();
            return lista;
        } catch (DaoException ex) {
            System.out.println(ex.getMessage());
            throw new AppException(ex.getMessage(), ex);
        }

    }

    public Boolean excluirProduto(int id) throws AppException {
        try {
            produtoDao.delete(id);
            return true;
        } catch (DaoException ex) {
            System.out.println(ex.getMessage());
            throw new AppException(ex.getMessage(), ex);
        }
    }

    public List<ProdutoModel> pesquisar(String nome) throws AppException {
        List<ProdutoModel> lista;
        try {
            lista = produtoDao.get(nome);
            return lista;
        } catch (DaoException ex) {
            System.out.println(ex.getMessage());
            throw new AppException(ex.getMessage(), ex);
        }
    }

}
