/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caecae.pi3.servlet.funcionarios;

import caecae.pi3.model.Funcionario;
import caecae.pi3.service.AppException;
import caecae.pi3.service.FuncionarioService;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Felipe
 */
@WebServlet(name = "FuncionarioRedireciona", urlPatterns = {"/funcionarios/redireciona"})
public class FuncionarioRedireciona extends HttpServlet {

    private FuncionarioService service = new FuncionarioService();
    
     @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {

            String cpf = request.getParameter("funcionarioCpf");
            Funcionario funcionario = service.buscar(cpf);
            request.setAttribute("funcionario", funcionario);

        } catch (AppException ex) {
            String msg = ex.getMessage();
            request.setAttribute("msgErro", msg);
        }
        request.getRequestDispatcher("/funcionariosAtualizar.jsp").forward(request, response);


    }

}
