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
    private Date dataVenda;
    private ArrayList<Object> produtos;

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

    public Date getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(Date dataVenda) {
        this.dataVenda = dataVenda;
    }

    public ArrayList<Object> getProdutos() {
        return produtos;
    }

    public void setProdutos(ArrayList<Object> produtos) {
        this.produtos = produtos;
    }
    
}
