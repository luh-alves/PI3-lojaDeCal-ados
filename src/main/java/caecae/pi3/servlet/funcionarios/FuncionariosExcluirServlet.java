/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caecae.pi3.servlet.funcionarios;

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

/**
 *
 * @author Felipe
 */
@WebServlet(name = "FuncionariosExcluirServlet", urlPatterns = {"/restrito/funcionarios/excluir"})
public class FuncionariosExcluirServlet extends HttpServlet {

    private FuncionarioService service = new FuncionarioService();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {  
        
         try {
            String id = request.getParameter("ID");
            service.excluir(Integer.parseInt(id));
        } catch (AppException ex) {
            Logger.getLogger(FuncionariosExcluirServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        response.sendRedirect(request.getContextPath() + "/WEB-INF/jsp/funcionarios");
    }

}
