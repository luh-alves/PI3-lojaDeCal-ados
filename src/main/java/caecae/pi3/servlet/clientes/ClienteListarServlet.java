/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caecae.pi3.servlet.clientes;

import caecae.pi3.model.Cliente;
import caecae.pi3.service.AppException;
import caecae.pi3.service.ClienteService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Felipe
 */
@WebServlet(name = "ClienteListarServlet", urlPatterns = {"/clientes"})
public class ClienteListarServlet extends HttpServlet {

        private ClienteService service = new ClienteService();
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         List<Cliente> clientes;
        try {
            
            String cpf = request.getParameter("CPF");
            if (cpf == null) {
                clientes = service.listar();
            } else {
                clientes = service.buscar(cpf);
            }
            request.setAttribute("listarClientes", clientes);

        } catch (AppException ex) {
            String msg = ex.getMessage();
            request.setAttribute("msgErro", msg);
        }
        request.getRequestDispatcher("clientes.jsp").forward(request, response);
    
    }


    
}
