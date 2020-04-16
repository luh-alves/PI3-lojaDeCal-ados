/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caecae.pi3.service;

import caecae.pi3.DAO.VendaDao;
import caecae.pi3.DAO.VendaProdutoDao;
import caecae.pi3.model.VendaModel;
import caecae.pi3.model.VendaProdutoModel;

/**
 *
 * @author rolucon
 */
public class VendaService {
    
    public void confirmaVenda(VendaModel venda){
        VendaDao vendaDao = new VendaDao();
        vendaDao.create(venda);
        
        for (Object produto : venda.getProdutos()) {
            VendaProdutoDao dao = new VendaProdutoDao();
            VendaProdutoModel vPModel = 
                new VendaProdutoModel(venda.getIdVenda(), 0/*produto.getId()*/);
            
            dao.create(vPModel);
        }
    }
}
