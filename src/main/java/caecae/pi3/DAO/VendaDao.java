/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caecae.pi3.DAO;

import caecae.pi3.exception.DaoException;
import caecae.pi3.model.VendaModel;
import java.util.ArrayList;

/**
 *
 * @author rolucon
 */
public class VendaDao implements DaoInterface<VendaModel>{

    @Override
    public ArrayList<VendaModel> getAll() throws DaoException {
        return null;
    }

    @Override
    public boolean create(VendaModel venda) throws DaoException {
        return false;
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
    
}
