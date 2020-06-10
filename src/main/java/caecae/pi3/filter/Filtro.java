/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caecae.pi3.filter;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Rogerio
 */
@WebFilter(filterName = "Filtro", urlPatterns = {"/*"})
public class Filtro implements Filter {
    
    private static final boolean debug = true;

    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;
   
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        
        //verifica se esta logado
        HttpSession sessao = httpRequest.getSession();
        
        chain.doFilter(request, response); 
        
        return;
//        if(sessao.getAttribute("usuario") == null){
//            httpResponse.sendRedirect(httpRequest.getContextPath() + "/login");
//            return;
//        }

        //recebe o usuario =(UsuarioSistema) sessao.getAttribute("usuario")
        
        //if(verificaUsuario(usuario, httpRequest)){
//           chain.doFilter(request, response); 
//        } else{
//              nao autorizado
//        }
    }

    @Override
    public void destroy() {        
    }

   
    @Override
    public void init(FilterConfig filterConfig) {        
       
    }

//    private boolean verificaAcesso(UsuarioSiatema, usuario, HttpServletRequest request){
//      String urlAcessada = request.getRequestURI();
//      if(urlAcessado.endWith("/home")){
//              return true
//      }
//      if(urlAcessado.endWith("/ralatorio") && usuario.getCargo()){
//              return true
//      }
//    }
   
}
