/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caecae.pi3;

import caecae.pi3.DAO.ProdutoDao;
import caecae.pi3.exception.DaoException;
import caecae.pi3.model.ProdutoModel;
import caecae.pi3.model.VendaModel;
import caecae.pi3.service.AppException;
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

/**
 *
 * @author rolucon
 */
@WebServlet(name = "Vendas", urlPatterns = {"/vendas"})
public class Vendas extends HttpServlet {
    CarrinhoDeCompras carrinho = new CarrinhoDeCompras();
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
        
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/plain");
        
        request.setAttribute("listaProd", carrinho.getProdutos());
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("vendas.jsp");
        dispatcher.forward(request, response);
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
        
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/plain");
        
        boolean finalizar = request.getParameter("finalizarBtt") != null;
        boolean addProd = request.getParameter("addProdBtt") != null;
        boolean remover = request.getParameter("removeBtt") != null;
        boolean cancelar = request.getParameter("cancelarBtt") != null;
        String cliente = request.getParameter("cliente");
        
        if(addProd){
            addProduto(request,response);
            request.setAttribute("clienteAtr", cliente);
        } else if(remover){
            removeProduto(request,response);
            request.setAttribute("clienteAtr", cliente);
        }else if(cancelar) {
            carrinho.cancelaCompra();
        } else if(finalizar) {
            //Validaçoes de campo para finalizar a compra
            if(cliente == null || cliente.trim().length() < 1) {
                request.setAttribute("erroCliente", "Id do Cliente Invalido");
            } else if(carrinho.getProdutos().isEmpty()) {
                // Tentar Colocar um alert
                request.setAttribute("erroCliente", "Carrinho vazio");
            }else{
                request.setAttribute("erroCliente", "Compra Executada");
                int aux = Integer.parseInt(cliente);
                carrinho.confirmaCompra(aux);
            }
        } 
        request.setAttribute("totalAtr", "R$: " + carrinho.getTotal());
        request.setAttribute("listaProd", carrinho.getProdutos());
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("vendas.jsp");
        dispatcher.forward(request, response);
    }
    
    private void addProduto(HttpServletRequest request, HttpServletResponse response){
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
                    
                    if(qtdN <= prod.getQuantidade()){
                        ProdutoModel finalProd = new ProdutoModel();
                        finalProd.setId(prod.getId());
                        finalProd.setNome(prod.getNome());
                        finalProd.setQuantidade(qtdN);
                        finalProd.setDescricao(prod.getDescricao());
                        finalProd.setValor(prod.getValor());
                        carrinho.addProduto(finalProd);
                    } else {
                        request.setAttribute("erroQtd", "Quantidade Superior ao estoque(" + prod.getQuantidade() + ")");
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
        } catch (AppException ex) {
            throw new RuntimeException(ex);
        } finally{
            if(erro){
                request.setAttribute("prodIdAtr", idProd);
                request.setAttribute("qtdAtr", qtd);
            }
        }
        
//        if(idProd == null || idProd.trim().length() < 1){
//            //Id Prod Invalido
//            request.setAttribute("erroProdId", "Id do Produto Invalido");
//        }
//        if(qtd == null || qtd.trim().length() < 1){
//            request.setAttribute("erroQtd", "Insira a Quantidade desejada");
//        } else {
//            ProdutoDao pdao = new ProdutoDao();//Gerar um produto pelo id
//            try {
//                ArrayList<ProdutoModel> prods = pdao.getAll();
//                for (ProdutoModel prod : prods) {
//                    carrinho.addProduto(prod);
//                }
//            } catch (DaoException ex) {
//                Logger.getLogger(Vendas.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
        
            
    }
    
    private void removeProduto(HttpServletRequest request, HttpServletResponse response){
        try{ 
            int idProdCarrinho = Integer.parseInt(request.getParameter("linhaSelec"));
            if(idProdCarrinho != -1) {
                carrinho.removeProduto(idProdCarrinho);
            }
        } catch (Exception e) {

        }
    }
    
}
