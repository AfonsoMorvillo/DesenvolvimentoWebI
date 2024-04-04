package edu.ifsp.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.IContext;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.web.servlet.JavaxServletWebApplication;

import edu.ifsp.model.Produto;

/**
 * Servlet implementation class HelloServlet
 */
@WebServlet( "/hello" )
public class HelloServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;

   protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
      
      List<Produto> produtos = List.of(
               new Produto("SSD Kingston",998.80),
               new Produto("Teclado USB",135.80),
               new Produto("Memória Corsair 16 GB",998.80),
               new Produto("Jontex sabor farofa",9.99)
            );
      
      request.setAttribute( "usuario", "Joãoinho" );
      request.setAttribute( "produtos", produtos );
      
      TemplateHelper.render( "hello", request, response );
      
    

   }


   protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
      doGet( request, response );
   }

}
