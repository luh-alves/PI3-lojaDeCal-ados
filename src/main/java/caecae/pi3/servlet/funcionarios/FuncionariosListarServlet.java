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
import java.util.ArrayList;
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
 * @author Felipe
 */
@WebServlet(name = "FuncionariosListarServlet", urlPatterns = {"/restrito/funcionarios"})
public class FuncionariosListarServlet extends HttpServlet {
    
    private FuncionarioService service = new FuncionarioService();
    
@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         List<Funcionario> funcionarios;
        try {
            service.listar();
            String cpf = request.getParameter("CPF");
            if (cpf == null || cpf == "" || cpf == " ") {
                funcionarios = service.listar();
            } else {
                funcionarios = new ArrayList<>();//Alterado
                funcionarios.add(service.buscar(cpf));//Alterado
            }
            request.setAttribute("listarFuncionarios", funcionarios);

        } catch (AppException ex) {
            String msg = ex.getMessage();
            request.setAttribute("msgErro", msg);
        } 
        request.getRequestDispatcher(request.getContextPath() + "/WEB-INF/jsp/funcionarios.jsp").forward(request, response);
    
    }
}
