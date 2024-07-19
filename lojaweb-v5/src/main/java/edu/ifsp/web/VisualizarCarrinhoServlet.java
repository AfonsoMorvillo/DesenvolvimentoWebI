package edu.ifsp.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ifsp.modelo.Carrinho;
import edu.ifsp.modelo.Usuario;
import edu.ifsp.persistencia.CarrinhoDAO;
import edu.ifsp.persistencia.PersistenceException;
import edu.ifsp.web.templates.Template;

/**
 * Servlet implementation class VisualizarCarrinhoServlet
 */
@WebServlet( "/carrinho" )
public class VisualizarCarrinhoServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;

   protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {

      HttpServletRequest httpRequest = (HttpServletRequest)request;
      Usuario usuario = (Usuario)httpRequest.getSession().getAttribute( "usuario" );

      try{

         CarrinhoDAO dao = new CarrinhoDAO();
         List<Carrinho> carrinho = dao.visualizarCarrinhoDoUsario( usuario );

         double valorTotal = getValorTotalCarrinho( carrinho );

         request.setAttribute( "carrinho", carrinho );
         request.setAttribute( "valorTotal", valorTotal );
         
         Template.render( "carrinho/listar", request, response );

      }
      catch( PersistenceException e ){
         e.printStackTrace();
      }
   }


   private double getValorTotalCarrinho( List<Carrinho> carrinho ) {
      double valorTotal = 0;

      for( Carrinho c : carrinho ){
         valorTotal += c.getProduto().getPreco();
      }

      return valorTotal;
   }
   
}
