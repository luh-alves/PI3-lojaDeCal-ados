/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caecae.pi3.controller.produto;

import caecae.pi3.model.ProdutoModel;
import caecae.pi3.service.AppException;
import caecae.pi3.service.ProdutoService;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Luciana Alves
 */
@WebServlet(name = "excluiProdutoController", urlPatterns = {"/produtos/excluir"})
public class excluiProdutoController extends HttpServlet {

    private ProdutoService service = new ProdutoService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            String produtoId = req.getParameter("produtoId");
            service.excluirProduto(Integer.parseInt(produtoId));
        } catch (AppException ex) {
            Logger.getLogger(excluiProdutoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        resp.sendRedirect(req.getContextPath() + "/produtos");

    }

}
