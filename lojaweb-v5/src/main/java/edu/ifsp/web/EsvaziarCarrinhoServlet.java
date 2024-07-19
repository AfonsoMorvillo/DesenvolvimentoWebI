package edu.ifsp.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ifsp.modelo.Usuario;
import edu.ifsp.persistencia.CarrinhoDAO;
import edu.ifsp.persistencia.PersistenceException;

/**
 * Servlet implementation class EsvaziarCarrinhoServlet
 */
@WebServlet( "/carrinho/esvaziar" )
public class EsvaziarCarrinhoServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;

   protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
      HttpServletRequest httpRequest = (HttpServletRequest)request;
      Usuario usuario = (Usuario)httpRequest.getSession().getAttribute( "usuario" );

      CarrinhoDAO dao = new CarrinhoDAO();
      try{
         dao.deleteCarrinho( usuario );
      }
      catch( PersistenceException e ){
         e.printStackTrace();
      }
      response.sendRedirect( httpRequest.getContextPath() + "/carrinho" );
   }

}
