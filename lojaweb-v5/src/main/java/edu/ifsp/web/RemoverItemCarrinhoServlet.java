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
 * Servlet implementation class RemoverItemCarrinhoServlet
 */
@WebServlet( "/carrinho/remover" )
public class RemoverItemCarrinhoServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;

   protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
      HttpServletRequest httpRequest = (HttpServletRequest)request;
      Usuario usuario = (Usuario)httpRequest.getSession().getAttribute("usuario");

      if( request.getParameter( "id" ) == null ){

         response.sendRedirect(httpRequest.getContextPath() + "/carrinho" );
      }
      else{

         int id = Integer.parseInt( request.getParameter( "id" ) );
         CarrinhoDAO dao = new CarrinhoDAO();
         try{
            dao.deleteItemCarrinho( id, usuario );
         }
         catch( PersistenceException e ){
            e.printStackTrace();
         }
         response.sendRedirect(httpRequest.getContextPath() + "/carrinho" );
      }

   }


}
