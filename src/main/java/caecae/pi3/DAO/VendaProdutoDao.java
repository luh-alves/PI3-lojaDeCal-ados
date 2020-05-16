/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caecae.pi3.DAO;

import caecae.pi3.exception.DaoException;
import caecae.pi3.model.VendaProdutoModel;
import java.util.ArrayList;

/**
 *
 * @author rolucon
 */
public class VendaProdutoDao implements DaoInterface<VendaProdutoModel>{

    @Override
    public ArrayList<VendaProdutoModel> getAll() throws DaoException {
        return null;
    }

    @Override
    public boolean create(VendaProdutoModel t) throws DaoException {
        return false;
    }

    @Override
    public VendaProdutoModel read(int id) throws DaoException {
       return null;
    }

    @Override
    public boolean update(VendaProdutoModel t) throws DaoException {
        return false;
    }

    @Override
    public boolean delete(int id) throws DaoException {
        return false;
    }
    
}
