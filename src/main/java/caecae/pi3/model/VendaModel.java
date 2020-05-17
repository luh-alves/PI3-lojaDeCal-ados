/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caecae.pi3.model;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author rolucon
 */
public class VendaModel {
    private int idVenda;
    private int idCliente;
    private int idFuncionario;
    private double valorTotal;
    private Date dataVenda;
    private ArrayList<ProdutoModel> produtos;

    public int getIdVenda() {
        return idVenda;
    }

    public void setIdVenda(int idVenda) {
        this.idVenda = idVenda;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(int idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Date getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(Date dataVenda) {
        this.dataVenda = dataVenda;
    }

    public ArrayList<ProdutoModel> getProdutos() {
        return produtos;
    }

    public void setProdutos(ArrayList<ProdutoModel> produtos) {
        this.produtos = produtos;
    }
    
}
