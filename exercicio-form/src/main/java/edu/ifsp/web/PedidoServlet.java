package edu.ifsp.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ifsp.web.dto.Sabor;
import edu.ifsp.web.persistencia.SaboresDAO;

/**
 * Servlet implementation class PedidoServlet
 */
@WebServlet( "/pedido" )
public class PedidoServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;

   protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {

      List<Sabor> sabores = SaboresDAO.findAll();

      request.setAttribute( "sabores", sabores );

      RequestDispatcher rd = request.getRequestDispatcher( "formulario.jsp" );
      rd.forward( request, response );
   }


   protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {

      boolean isValid = validar( request );

      RequestDispatcher rd = null;

      if( isValid ){
         request.setAttribute( "pedido", "123465" );
         rd = request.getRequestDispatcher( "confirmacao.jsp" );

      }
      else{
         List<Sabor> sabores = SaboresDAO.findAll();

         request.setAttribute( "sabores", sabores );

         rd = request.getRequestDispatcher( "formulario.jsp" );
      }

      rd.forward( request, response );
   }


   private boolean validar(HttpServletRequest request) {
      boolean res = true;
      
      try{
         int quantidade = Integer.parseInt( request.getParameter( "quantidade" ) );
         if( quantidade < 1 ){
            res = false;
         }
         request.setAttribute( "erro-quantidade", "O valor mínimo para a quantidade é 1 (um)." );
         
      }
      catch( Exception e ){
         res = false;
         request.setAttribute( "erro-quantidade", "Deve ser um número inteiro" );
         
      }
      
      String formaPagamento =  request.getParameter( "forma-pagamento" );
        
        if (!(formaPagamento.equals( "pix" ) || formaPagamento.equals( "cartao" ))) {
           res = false;
           request.setAttribute( "erro-forma", "Forma de pagamento é obrigátorio!" );
        }

   return res;
}

}
