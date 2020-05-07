/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caecae.pi3.service;

import caecae.pi3.DAO.ClienteDAO;
import caecae.pi3.model.Cliente;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Felipe
 */
public class ClienteService {
    
        private ClienteDAO dao = new ClienteDAO();
    
    //public List<Cliente> listar() throws AppException {
      //  try {
      //      return dao.listar();
      //  } catch (SQLException e) {
      //      throw new AppException("ERRO NA CONSULTA DOS DADOS", e);
      //  }
   //    }
    
    private boolean isValid(Cliente c) {
        if (c != null && c.getNome() != null && c.getCpf()!= null && c.getEmail()!= null) {
            return true;
        }
        return false;
    }
    
    public void incluir(Cliente c) throws AppException, ClassNotFoundException {
        try {
            if (isValid(c)) {
                dao.incluir(c);
            } else {
                 throw new AppException("DADOS INVALIDOS", null);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new AppException("ERRO NA INCLUSAO DOS DADOS", e);
        }
    }
    
}
