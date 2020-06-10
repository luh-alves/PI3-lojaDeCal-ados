/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caecae.pi3.servlet.funcionarios;

import caecae.pi3.exception.DaoException;
import caecae.pi3.model.Funcionario;
import caecae.pi3.service.AppException;
import caecae.pi3.service.FuncionarioService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Felipe
 */
@WebServlet(name = "FuncionariosEditarServlet", urlPatterns = {"/restrito/funcionarios/atualizar"})
public class FuncionariosEditarServlet extends HttpServlet {

    private FuncionarioService service = new FuncionarioService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         request.setCharacterEncoding("UTF-8");
        String id = request.getParameter("funcionarioId");
        String nome = request.getParameter("nome");
        String cpf = request.getParameter("cpf");
        String email = request.getParameter("email");
        String celular = request.getParameter("celular");
        String sexo = request.getParameter("sexo");
        String cargo = request.getParameter("cargo");
        String user = request.getParameter("user");
        String senha = request.getParameter("senha");
        //String dtnascimento = request.getParameter("dtnascimento");
        
        Funcionario f = new Funcionario ();
        f.setId(Integer.parseInt(id));
        System.out.println("o Id é:" + id);
        f.setNome(nome);
        f.setCpf(cpf);
        f.setEmail(email);
        f.setCelular(celular);
        f.setCargo(cargo);
        f.setUser(user);
        f.setSenha(senha);
        f.setSexo(sexo);
        //LocalDate dtNascimento = LocalDate.parse(dtnascimento);
        //c.setDataNascimento(2017-01-23);
        
        
        HttpSession sessao = request.getSession();
        
        try {
            service.alterar(f);
            sessao.setAttribute("msgSucesso", "Funcionario alterado com sucesso");
        } catch (AppException ex) {
            sessao.setAttribute("msgErro", "Erro ao editar funcionario - " + ex.getMessage());
             response.sendRedirect(request.getContextPath() + "/funcionarios");
        }       catch (DaoException ex) {
                    Logger.getLogger(FuncionariosSalvarServlet.class.getName()).log(Level.SEVERE, null, ex);
                     response.sendRedirect(request.getContextPath() + "/funcionarios");
                }
        response.sendRedirect(request.getContextPath() + "/WEB-INF/jsp/funcionarios");

    }

}
