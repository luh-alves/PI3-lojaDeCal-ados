/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caecae.pi3.controller.produto;

import caecae.pi3.model.ProdutoModel;
import caecae.pi3.service.AppException;
import caecae.pi3.service.ProdutoService;
import static com.sun.corba.se.spi.presentation.rmi.StubAdapter.request;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Luciana Alves
 */
@WebServlet(name = "AtualizarProdutoController", urlPatterns = {"/produtos/atualizar"})
public class AtualizarProdutoController extends HttpServlet {

    private ProdutoService service = new ProdutoService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String sid = request.getParameter("id");
        String nome = request.getParameter("nome");
        String quantidade = request.getParameter("quantidade");
        String valor = request.getParameter("valor");
        String descricao = request.getParameter("descricao");
        String filial = request.getParameter("filial");

        int id = Integer.parseInt(sid);
        ProdutoModel produto = new ProdutoModel();
        produto.setId(id);
        produto.setNome(nome);
        produto.setQuantidade(Integer.parseInt(quantidade));
        produto.setValor(Double.parseDouble(valor));      
        produto.setDescricao(descricao);
        produto.setFilial(Integer.parseInt(filial));
        

        HttpSession sessao = request.getSession();
        try {
            service.atualizar(id, produto);
            System.out.println("Produto salvo com sucesso");
            sessao.setAttribute("msgSucesso", "Produto salvo com sucesso");

        } catch (AppException ex) {
            System.out.println(ex.getMessage());
            sessao.setAttribute("msgErro", "Erro ao salvar Produto - " + ex.getMessage());
        }
        response.sendRedirect(request.getContextPath() + "/produtos");

    }

}
