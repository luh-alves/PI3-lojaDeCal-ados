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
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Luciana Alves
 */
@WebServlet(name = "ProdutoListaController", urlPatterns = {"/restrito/produtos"})
public class ListaProdutoController extends HttpServlet {

    private ProdutoService service = new ProdutoService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<ProdutoModel> produtos;
        try {
            
            String nome = request.getParameter("produtoNome");
            if (nome == null) {
                produtos = service.listarTodos();
            } else {
                produtos = service.pesquisar(nome);
            }
            request.setAttribute("listaProdutos", produtos);

        } catch (AppException ex) {
            String msg = ex.getMessage();
            request.setAttribute("msgErro", msg);
        }
        request.getRequestDispatcher("/WEB-INF/jsp/produtos.jsp").forward(request, response);
    }
}
