/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caecae.pi3;

import caecae.pi3.exception.DaoException;
import caecae.pi3.model.ProdutoModel;
import caecae.pi3.model.VendaModel;
import caecae.pi3.service.VendaService;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rolucon
 */
public class CarrinhoDeCompras {
    private VendaService service;
    private ArrayList<ProdutoModel> produtos; 
    private double total = 0;
    
    public CarrinhoDeCompras() {
        service = new VendaService();
        produtos = new ArrayList();
    }
    
    public void addProduto(ProdutoModel produto) {
        produtos.add(produto);
        total += produto.getValor() * produto.getQuantidade();
    }
    
    public void removeProduto(int id) {
        for (ProdutoModel produto : produtos) {
            if(produto.getId() == id){
                produtos.remove(produto);
                return;
            }
        }
        //Procura produto e remove da lsita
        
    }
    
    public void confirmaCompra(int idCliente){
        try {
            VendaModel venda = new VendaModel();
            venda.setDataVenda(new Date(System.currentTimeMillis()));
            venda.setIdCliente(idCliente);
            venda.setProdutos(produtos);
            service.confirmaVenda(venda);
            produtos.clear();
            total = 0;
        } catch (DaoException ex) {
            throw new RuntimeException("Falha ao Confirmar Compra ", ex);
        }
        
    }
    
    public void cancelaCompra(){
        produtos.clear();
    }
    
    public ArrayList<ProdutoModel> getProdutos(){
        return produtos;
    }
    
    public double getTotal(){
        return total;
    }
}
