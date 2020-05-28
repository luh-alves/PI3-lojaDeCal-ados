/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caecae.pi3.service;

import caecae.pi3.DAO.ClienteDAO;
import caecae.pi3.exception.DaoException;
import caecae.pi3.model.Cliente;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Felipe
 */
public class ClienteService {

    private ClienteDAO dao = new ClienteDAO();

    public List<Cliente> listar() throws AppException {
        List<Cliente> lista;
        try {
            lista = ClienteDAO.listar();
            return lista;
        } catch (DaoException ex) {
            System.out.println(ex.getMessage());
            throw new AppException(ex.getMessage(), ex);
        }

    }

    private boolean isValid(Cliente c) {
        if (c != null && c.getNome() != null && c.getCpf() != null && c.getEmail() != null) {
            return true;
        }
        return false;
    }

    public void incluir(Cliente c) throws DaoException, AppException {
        try {
            if (isValid(c)) {
                dao.incluir(c);
            } else {
                throw new AppException("DADOS INVALIDOS", null);
            }
        } catch (DaoException e) {
            e.printStackTrace();
            throw new AppException("ERRO NA INCLUSAO DOS DADOS", e);
        }
    }

    public void excluir(int id) throws AppException {
        try {
            dao.excluir(id);
        } catch (DaoException e) {
            e.printStackTrace();
            throw new AppException("ERRO NA EXCLUSÃO DOS DADOS", e);
        }
    }

    public void alterar(Cliente c) throws DaoException, AppException {
        try {
            dao.alterar(c);
        } catch (DaoException e) {
            e.printStackTrace();
            throw new AppException("ERRO NA EDIÇÃO DOS DADOS", e);
        }
    }

    //Alterado
    public Cliente buscar(String cpf) throws AppException {
        try {
            return dao.buscar(cpf);
        } catch (DaoException e) {
            e.printStackTrace();
            throw new AppException("ERRO NA BUSCA DOS DADOS", e);
        }
    }

}
