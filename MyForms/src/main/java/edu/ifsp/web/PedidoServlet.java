package edu.ifsp.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ifsp.pizzaria.FormaContato;
import edu.ifsp.pizzaria.persistencia.FormaContatoDAO;

@WebServlet( "/pedido" )
public class PedidoServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;

   @Override
   protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {

      List<FormaContato> formasContato = FormaContatoDAO.findAll();

      request.setAttribute( "formasContato", formasContato );

      RequestDispatcher rd = request.getRequestDispatcher( "pedidos.jsp" );
      rd.forward( request, response );
   }


   protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {


      boolean isValid = validar( request );
      RequestDispatcher rd = null;
      
      if (isValid) {
         request.setAttribute( "pedido", "437912" );
         
          rd = request.getRequestDispatcher( "confirmacao.jsp" );
      }else {
         List<FormaContato> formasContato = FormaContatoDAO.findAll();

         request.setAttribute( "formasContato", formasContato );
         rd = request.getRequestDispatcher( "pedido.jsp" );
      }

      rd.forward( request, response );
   }


   private boolean validar( HttpServletRequest request ) {
      boolean res = true;
      try{
         int quantidade = Integer.parseInt( request.getParameter( "quantidade" ) );
         if( quantidade < 1 ){
            res = false;
         }
         request.setAttribute( "erro-quantidade", "O valor mínimo para a quantidade 1" );
      }
      catch( NumberFormatException e ){
         res = false;
         request.setAttribute( "erro-quantidade", "Não é um formato númerico válido!" );
      }
      
   String formaPagamento =  request.getParameter( "forma-pagamento" );
    formaPagamento =  "erro";
      
      if (!(formaPagamento.equals( "pix" ) || formaPagamento.equals( "cartao" ))) {
         res = false;
         request.setAttribute( "erro-forma", "Forma de pagamento é obrigátorio!" );
      }
      return res;
   }

}
