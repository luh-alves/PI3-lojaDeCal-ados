/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caecae.pi3;

import caecae.pi3.model.VendaModel;
import caecae.pi3.service.VendaService;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author rolucon
 */
public class CarrinhoDeCompras {
    private VendaService service;

    private ArrayList<Object> produtos; 

    public CarrinhoDeCompras() {
        service = new VendaService();
        produtos = new ArrayList();
    }
    
    public void addProduto(Object produto) {
        produtos.add(produto);
    }
    
    public void removeProduto(Object produto) {
        //Procura produto e remove da lsita
    }
    
    public void confirmaCompra(int idCliente){
        VendaModel venda = new VendaModel();
        venda.setDataVenda(new Date(System.currentTimeMillis()));
        venda.setIdCliente(idCliente);
        venda.setProdutos(produtos);
        service.confirmaVenda(venda);
        produtos.clear();
        System.out.println("ID Cliente: " + idCliente);
    }
    
    public void cancelaCompra(){
        produtos.clear();
    }
    
    public ArrayList<Object> getProdutos(){
        return produtos;
    }
}
