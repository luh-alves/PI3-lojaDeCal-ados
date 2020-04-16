/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caecae.pi3.exception;

/**
 *
 * @author rolucon
 */
public class DaoException extends RuntimeException {
       
    public DaoException(String cause) {
        super(cause);
    }
    
    public DaoException(Exception cause) {
        super(cause);
    }
}
