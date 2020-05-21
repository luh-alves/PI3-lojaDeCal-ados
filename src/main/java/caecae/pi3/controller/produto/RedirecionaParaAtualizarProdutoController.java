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
import java.io.PrintWriter;
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
@WebServlet(name = "RedirecionaParaAtualizarProdutoController", urlPatterns = {"/produtos/redireciona-para-atualizar"})
public class RedirecionaParaAtualizarProdutoController extends HttpServlet {

    private ProdutoService service = new ProdutoService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {

            String id = request.getParameter("produtoId");
            ProdutoModel produto = service.pesquisar(Integer.parseInt(id));
            request.setAttribute("produto", produto);

        } catch (AppException ex) {
            String msg = ex.getMessage();
            request.setAttribute("msgErro", msg);
        }
        request.getRequestDispatcher("/WEB-INF/jsp/telaAtualizar.jsp").forward(request, response);

    }
}