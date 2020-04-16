/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caecae.pi3.model;

/**
 *
 * @author rolucon
 */
public class VendaProdutoModel {
    private int idVenda;
    private int idProduto;

    public VendaProdutoModel(int idVenda, int idProduto) {
        this.idVenda = idVenda;
        this.idProduto = idProduto;
    }

    public int getIdVenda() {
        return idVenda;
    }

    public int getIdProduto() {
        return idProduto;
    }
    
    
}
