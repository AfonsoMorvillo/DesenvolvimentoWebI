package edu.ifsp.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ifsp.modelo.Cliente;
import edu.ifsp.persistencia.ClienteDAO;
import edu.ifsp.persistencia.PersistenceException;
import edu.ifsp.web.templates.Template;

/**
 * Servlet implementation class DetalhesClienteServlet
 */
@WebServlet( "/cliente/detalhes" )
public class DetalhesClienteServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;

   protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
      int id = Integer.parseInt( request.getParameter( "id" ) );
      ClienteDAO dao = new ClienteDAO();
      try{
         Cliente cliente = dao.findById( id );
         request.setAttribute( "cliente", cliente );
         Template.render( "cliente/detalhes", request, response );
      }
      catch( PersistenceException e ){
         e.printStackTrace();
      }

   }
}
