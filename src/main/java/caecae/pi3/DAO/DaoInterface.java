/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caecae.pi3.DAO;

import caecae.pi3.exception.DaoException;
import java.util.ArrayList;

/**
 *
 * @author rolucon
 * @param <T> classe model a ser utilizada
 */
public interface DaoInterface<T> {
    public ArrayList<T> getAll() throws DaoException;
    public boolean create(T t) throws DaoException;
    public T read(int id) throws DaoException;
    public boolean update(T t) throws DaoException;
    public boolean delete(int id) throws DaoException;
    public ArrayList get( String nome) throws DaoException;
}
