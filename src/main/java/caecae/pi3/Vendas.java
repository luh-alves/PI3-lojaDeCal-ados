/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caecae.pi3;

import caecae.pi3.DAO.ProdutoDao;
import caecae.pi3.exception.DaoException;
import caecae.pi3.model.Cliente;
import caecae.pi3.model.ProdutoModel;
import caecae.pi3.model.VendaModel;
import caecae.pi3.service.AppException;
import caecae.pi3.service.ClienteService;
import caecae.pi3.service.ProdutoService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author rolucon
 */
@WebServlet(name = "Vendas", urlPatterns = {"/restrito/vendas"})
public class Vendas extends HttpServlet {
    ProdutoService prodService = new ProdutoService();
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sessao = request.getSession();
        CarrinhoDeCompras carrinho;
        if(sessao.getAttribute("carrinho") == null){
            carrinho = new CarrinhoDeCompras();
            sessao.setAttribute("carrinho", carrinho);
        }
        carrinho = (CarrinhoDeCompras) sessao.getAttribute("carrinho");
        
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/plain");
        
        request.setAttribute("listaProd", carrinho.getProdutos());
        
        // FeedBacks
//        request.setAttribute("erroCliente", "CPF do Cliente Invalido");
        
        request.getRequestDispatcher(request.getContextPath() + "/WEB-INF/jsp/vendas.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sessao = request.getSession();
        PrintWriter out = response.getWriter();
        CarrinhoDeCompras carrinho;
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/plain");
        
        boolean finalizar = request.getParameter("finalizarBtt") != null;
        boolean addProd = request.getParameter("addProdBtt") != null;
        boolean remover = request.getParameter("removeBtt") != null;
        boolean cancelar = request.getParameter("cancelarBtt") != null;
        String cliente = request.getParameter("cliente").trim();
        
        if(sessao.getAttribute("carrinho") == null){
            carrinho = new CarrinhoDeCompras();
            sessao.setAttribute("carrinho", carrinho);
        }
        carrinho = (CarrinhoDeCompras) sessao.getAttribute("carrinho");
        
        
        if(addProd){
            try {
                addProduto(request,response,carrinho);
            } catch (AppException ex) {
                throw new RuntimeException("Erro ao Add Produto");
            }
            request.setAttribute("clienteAtr", cliente);
        } else if(remover){
            removeProduto(request,response,carrinho);
            request.setAttribute("clienteAtr", cliente);
        }else if(cancelar) {
            carrinho.cancelaCompra();
        } else if(finalizar) {
            //Validaçoes de campo para finalizar a compra
            if(cliente == null || cliente.trim().length() != 11) {
                request.setAttribute("erroCliente", "CPF do Cliente Invalido" + cliente.trim().length());
            } else if(carrinho.getProdutos().isEmpty()) {
                //Alerta Carrinho
                request.setAttribute("erroAlert", "Carrinho vazio esta vazio");
            }else{
                try{
//                    int cpf = Integer.parseInt(cliente); Nao esta funcionando
                    Cliente cli = clienteByCpf(cliente);
                    if(cli != null){
                        if(carrinho.confirmaCompra(cli)){  
                            request.setAttribute("erroAlert", "Venda Realizada com sucesso");
                        } else{
                           request.setAttribute("erroAlert", "Erro ao Finalizar a venda");
                        }
                    } else{
                        request.setAttribute("erroAlert", "Cliente nao possue cadastro");
                    }
                } catch(NumberFormatException e){
                    request.setAttribute("erroCliente", "CPF do Cliente Invalido Number");
                } catch (AppException ex) {
                    Logger.getLogger(Vendas.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } 
        request.setAttribute("totalAtr", "R$: " + carrinho.getTotal());
        request.setAttribute("listaProd", carrinho.getProdutos());
        
        RequestDispatcher dispatcher = request.getRequestDispatcher(request.getContextPath() + "/WEB-INF/jsp/vendas.jsp");
        dispatcher.forward(request, response);
//        response.sendRedirect(request.getContextPath() + "/vendas"); Problemas para mostrar o feedback
    }
    
    private void addProduto(HttpServletRequest request, HttpServletResponse response,
            CarrinhoDeCompras carrinho) throws AppException{
        String idProd = (request.getParameter("produtoId"));
        String qtd = request.getParameter("qtd");
        boolean erro = false;
        //Validaçoes dos Campos deve vir aqui
        try{
            int id = Integer.parseInt(idProd);
            ProdutoModel prod = prodService.pesquisar(id);
            
            if(prod == null){
                request.setAttribute("erroProdId", "Id do Produto Invalido");
                erro = true;
            } else {
                try{
                    int qtdN = Integer.parseInt(qtd);
                    
                    if(qtdN + carrinho.qtdById(id) <= prod.getQuantidade()){
                        ProdutoModel finalProd = new ProdutoModel();
                        finalProd.setId(prod.getId());
                        finalProd.setNome(prod.getNome());
                        finalProd.setQuantidade(qtdN);
                        finalProd.setDescricao(prod.getDescricao());
                        finalProd.setValor(prod.getValor());
                        carrinho.addProduto(finalProd);
                    } else {
                        request.setAttribute("erroQtd", 
                                "Quantidade Superior ao estoque(Max: " 
                                        + prod.getQuantidade() + ", No carrinho: " 
                                        + carrinho.qtdById(id) +")");
                        erro = true;
                    }
                }catch(NumberFormatException e){
                    request.setAttribute("erroQtd", "Campo Quantidade deve conter apenas numeros");
                    erro = true;
                }
            }
        } catch(NumberFormatException e) {
            erro = true;
            request.setAttribute("erroProdId", "Id do Produto deve conter apenas numeros");
        } finally{
            if(erro){
                request.setAttribute("prodIdAtr", idProd);
                request.setAttribute("qtdAtr", qtd);
            }
        }    
    }
    
    private void removeProduto(HttpServletRequest request, HttpServletResponse response,
            CarrinhoDeCompras carrinho){
        try{ 
            int idProdCarrinho = Integer.parseInt(request.getParameter("linhaSelec"));
            if(idProdCarrinho != -1) {
                carrinho.removeProduto(idProdCarrinho);
            }
        } catch (Exception e) {

        }
    }
    
    private Cliente clienteByCpf(String cliente) throws AppException{
        ClienteService cliService = new ClienteService();
        return cliService.buscar(cliente);
    }
}
