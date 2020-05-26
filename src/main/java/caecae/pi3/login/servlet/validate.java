package caecae.pi3.login.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Mateus
 */
@WebServlet(name = "validate", urlPatterns = {"/login/validate"})
public class validate extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String user = request.getParameter("user");
        String senha = request.getParameter("senha");

        request.setAttribute("loginUser", user);
        request.setAttribute("loginSenha", senha);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/testeDeLogin.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
