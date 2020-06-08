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
import java.util.List;
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
@WebServlet(name = "FuncionariosSalvarServlet", urlPatterns = {"/funcionarios/salvar"})
public class FuncionariosSalvarServlet extends HttpServlet {

    private FuncionarioService service = new FuncionarioService();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sessao = request.getSession();
        List<Funcionario> funcionarios;
        try {
            funcionarios = service.listar();
            sessao.setAttribute("listarFuncionarios", funcionarios);
        } catch (AppException ex) {
            Logger.getLogger(FuncionariosSalvarServlet.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            request.setCharacterEncoding("UTF-8");
        String nome = request.getParameter("nome");
        String cpf = request.getParameter("cpf");
        String email = request.getParameter("email");
        String celular = request.getParameter("celular");
        String sexo = request.getParameter("sexo");
        String cargo = request.getParameter("cargo");
        String user = request.getParameter("user");
        String senha = request.getParameter("senha");
        
        Funcionario f = new Funcionario();
        f.setNome(nome);
        f.setCpf(cpf);
        f.setEmail(email);
        f.setSexo(sexo);
        f.setCelular(celular);
        f.setCargo(cargo);
        f.setUser(user);
        f.setSenha(senha);
        
        HttpSession sessao = request.getSession();
        try {
            service.incluir(f);
            sessao.setAttribute("msgSucesso", "Funcionario salvo com sucesso");
        } catch (AppException ex) {
            sessao.setAttribute("msgErro", "Erro ao salvar funcionario - " + ex.getMessage());
        } catch (DaoException ex) {
            Logger.getLogger(FuncionariosSalvarServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        response.sendRedirect(request.getContextPath() + "/funcionarios");
    }
}
