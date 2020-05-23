/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caecae.pi3;

import caecae.pi3.exception.DaoException;
import caecae.pi3.model.Cliente;
import caecae.pi3.model.ProdutoModel;
import caecae.pi3.model.VendaModel;
import caecae.pi3.service.VendaService;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rolucon
 */
public class CarrinhoDeCompras {
    private VendaService service;
    private HashMap<Integer, ProdutoModel> produtos; 
    private double total = 0;
    
    public CarrinhoDeCompras() {
        this.produtos = new HashMap<Integer, ProdutoModel>();
        this.service = new VendaService();
    }
    
    public void addProduto(ProdutoModel produto) {
        if(produtos.containsKey(produto.getId())){
            ProdutoModel prod = produtos.get(produto.getId());
            prod.setQuantidade(prod.getQuantidade() + produto.getQuantidade());
            total += produto.getValor() * produto.getQuantidade();
        } else {
            produtos.put(produto.getId(), produto);
            total += produto.getValor() * produto.getQuantidade();
        }
//        produtos.add(produto);
//        total += produto.getValor() * produto.getQuantidade();
    }
    
    public void removeProduto(int id) {
        produtos.remove(id);
//        for (ProdutoModel produto : produtos) {
//            if(produto.getId() == id){
//                produtos.remove(produto);
//                total -= produto.getValor() * produto.getQuantidade();
//                return;
//            }
//        }
        //Procura produto e remove da lsita
        
    }
    
    public boolean confirmaCompra(Cliente cliente){
        ArrayList<ProdutoModel> list = new ArrayList<>();
        for (Integer key : produtos.keySet()) {
            list.add(produtos.get(key));              
        }
        try {
            VendaModel venda = new VendaModel();
            venda.setDataVenda(new Date(System.currentTimeMillis()));
            venda.setIdCliente(cliente.getId());
            venda.setProdutos(list);
            venda.setValorTotal(total);
            service.confirmaVenda(venda);
            produtos.clear();
            total = 0;
            return true;
        } catch (DaoException ex) {
//            return false;
            throw new RuntimeException("Falha ao Confirmar Compra ", ex);
        }
        
    }
    
    public void cancelaCompra(){
        produtos.clear();
    }
    
    public ArrayList<ProdutoModel> getProdutos(){
        ArrayList<ProdutoModel> list = new ArrayList<>();
        for (Integer key : produtos.keySet()) {
            list.add(produtos.get(key));              
        }
        return list;
    }
    
    public int qtdById(int id){
        if(produtos.containsKey(id)){
            return produtos.get(id).getQuantidade();
        } else {
            return 0;
        }
        
    }
    
    public double getTotal(){
        return total;
    }
}
