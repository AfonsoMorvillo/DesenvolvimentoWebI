package edu.ifsp.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ifsp.modelo.Carrinho;
import edu.ifsp.modelo.Produto;
import edu.ifsp.modelo.Usuario;
import edu.ifsp.persistencia.CarrinhoDAO;
import edu.ifsp.persistencia.PersistenceException;
import edu.ifsp.persistencia.ProdutoDAO;

@WebServlet( "/produto/adicionarCarrinho" )
public class AdicionarCarrinhoServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;

   protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
      HttpServletRequest httpRequest = (HttpServletRequest)request;
      Usuario usuario = (Usuario)httpRequest.getSession().getAttribute( "usuario" );
      
      Produto produto = null;

      if( request.getParameter( "id" ) == null ){

         response.sendRedirect( httpRequest.getContextPath() + "/produto/listar" );
      }
      else{

         int id = Integer.parseInt( request.getParameter( "id" ) );
         ProdutoDAO dao = new ProdutoDAO();
         try{
            produto = dao.findById( id );
         }
         catch( PersistenceException e ){
            e.printStackTrace();
         }
      }

      CarrinhoDAO carrinhoDAO = new CarrinhoDAO();

      try{
         carrinhoDAO.save( new Carrinho( usuario, produto ) );
         // voltar para pagina
         Flash.setAttribute(request, "produto/listar", "adicionado", "Produto %s adicionado ao carrinho.".formatted(produto.getId()));
      }
      catch( PersistenceException e ){

         // voltar
         e.printStackTrace();
      }

      response.sendRedirect( httpRequest.getContextPath() + "/produto/listar" );
   }

}
