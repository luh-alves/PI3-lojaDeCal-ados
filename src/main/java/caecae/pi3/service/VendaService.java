/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caecae.pi3.service;

import caecae.pi3.ConnectionFactory;
import caecae.pi3.DAO.VendaDao;
import caecae.pi3.exception.DaoException;
import caecae.pi3.model.VendaModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author rolucon
 */
public class VendaService  {
    
    public void confirmaVenda(VendaModel venda) throws DaoException{
        VendaDao vendaDao = new VendaDao();
        vendaDao.create(venda);
    }
}
