/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caecae.pi3;

import java.io.IOException;
import java.io.PrintWriter;
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
        
        
        if(addProd){
            carrinho.addProduto(10);
            System.out.println("AddProd");
        } else if(cancelar) {
            carrinho.cancelaCompra();
            request.setAttribute("cliente", "");
        }
        
        System.out.println("Ta funcionando");
        request.setAttribute("listaProd", carrinho.getProdutos());
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("vendas.jsp");
        dispatcher.forward(request, response);
    }

}
