/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caecae.pi3.servlet.clientes;

import caecae.pi3.exception.DaoException;
import caecae.pi3.model.Cliente;
import caecae.pi3.service.AppException;
import caecae.pi3.service.ClienteService;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
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
@WebServlet(name = "ClienteSalvarServlet", urlPatterns = {"/clientes/salvar"})
public class ClienteSalvarServlet extends HttpServlet {
            private ClienteService service  = new ClienteService();

            
            
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sessao = request.getSession();
        List<Cliente> clientes;
        try {
            clientes = service.listar();
            sessao.setAttribute("listarClientes", clientes);
        } catch (AppException ex) {
            Logger.getLogger(ClienteSalvarServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            request.setCharacterEncoding("UTF-8");
        String nome = request.getParameter("nome");
        String cpf = request.getParameter("cpf");
        String email = request.getParameter("email");
        String sexo = request.getParameter("sexo");
        String dtnascimento = request.getParameter("dtnascimento");
        
        Cliente c = new Cliente();
        c.setNome(nome);
        c.setCpf(cpf);
        c.setEmail(email);
        c.setSexo(sexo);
        
        HttpSession sessao = request.getSession();
        
        try {
            service.incluir(c);
            sessao.setAttribute("msgSucesso", "Cliente salvo com sucesso");
        } catch (AppException ex) {
            sessao.setAttribute("msgErro", "Erro ao salvar cliente - " + ex.getMessage());
             response.sendRedirect(request.getContextPath() + "/clientes");
        }       catch (DaoException ex) {
                    Logger.getLogger(ClienteSalvarServlet.class.getName()).log(Level.SEVERE, null, ex);
                     response.sendRedirect(request.getContextPath() + "/clientes");
                }
        response.sendRedirect(request.getContextPath() + "/clientes");
    }


}
