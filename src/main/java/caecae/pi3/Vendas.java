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
            ProdutoDao pdao = new ProdutoDao();//Gerar um produto pelo id
            try {
                ArrayList<ProdutoModel> prods = pdao.getAll();
                for (ProdutoModel prod : prods) {
                    carrinho.addProduto(prod);
                }
            } catch (DaoException ex) {
                Logger.getLogger(Vendas.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            request.setAttribute("clienteAtr", cliente);
        } else if(remover){
            request.setAttribute("clienteAtr", cliente);
        }else if(cancelar) {
            carrinho.cancelaCompra();
        } else if(finalizar) {
            int aux = Integer.parseInt(cliente);
            carrinho.confirmaCompra(aux);
        } 
        
        request.setAttribute("listaProd", carrinho.getProdutos());
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("vendas.jsp");
        dispatcher.forward(request, response);
    }

}
