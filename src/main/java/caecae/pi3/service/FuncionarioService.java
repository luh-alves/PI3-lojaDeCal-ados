/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caecae.pi3.service;

import caecae.pi3.DAO.FuncionarioDAO;
import caecae.pi3.exception.DaoException;
import caecae.pi3.model.Funcionario;
import java.util.List;

/**
 *
 * @author Felipe
 */
public class FuncionarioService {
    
    private FuncionarioDAO dao = new FuncionarioDAO();

    public List<Funcionario> listar() throws AppException {
        List<Funcionario> lista;
        try {
            lista = dao.listar();
            return lista;
        } catch (DaoException ex) {
            System.out.println(ex.getMessage());
            throw new AppException(ex.getMessage(), ex);
        }

    }

    private boolean isValid(Funcionario f) {
        if (f != null && f.getNome() != null && f.getCpf() != null && f.getEmail() != null && f.getCelular() != null && f.getSexo() != null && f.getCargo() != null && f.getUser() != null && f.getSenha() != null) {
            return true;
        }
        return false;
    }

    public void incluir(Funcionario f) throws DaoException, AppException {
        try {
            if (isValid(f)) {
                dao.incluir(f);
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

    public void alterar(Funcionario f) throws DaoException, AppException {
        try {
            dao.alterar(f);
        } catch (DaoException e) {
            e.printStackTrace();
            throw new AppException("ERRO NA EDIÇÃO DOS DADOS", e);
        }
    }

    //Alterado
    public Funcionario buscar(String cpf) throws AppException {
        try {
            return dao.buscar(cpf);
        } catch (DaoException e) {
            e.printStackTrace();
            throw new AppException("ERRO NA BUSCA DOS DADOS", e);
        }
    }
    
}
