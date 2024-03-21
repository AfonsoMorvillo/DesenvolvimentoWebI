package edu.ifsp.web;

import java.io.IOException;
import java.lang.reflect.Array;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Login
 */
@WebServlet( "/login" )
public class Login extends HttpServlet {
   private static final long serialVersionUID = 1L;

   protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
      RequestDispatcher rd = request.getRequestDispatcher( "formLogin.jsp" );
      rd.forward( request, response );
   }


   protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {

      RequestDispatcher rd = null;
      
      String usuario = request.getParameter( "usuario" );
      String senha = request.getParameter( "senha" );

      if( isUsuarioValido( request, usuario ) && isSenhaValido( request, senha ) ){
         
         request.setAttribute( "usuario", usuario);
         request.setAttribute( "senha", senha );
         
         rd = request.getRequestDispatcher( "confirmacao.jsp" );
      }else {
         
         rd = request.getRequestDispatcher( "formLogin.jsp" );
      }

      rd.forward( request, response );
   }


   private boolean isUsuarioValido( HttpServletRequest request, String usuario ) {

      if( isEmpyt( usuario ) ){
         request.setAttribute( "erro-usuario", "Usuário é obrigátorio" );
         return false;
      }
      else if( usuario.trim().length() < 3 ){
         request.setAttribute( "erro-usuario", "Usuário deve ter no mínimo 3 caracteres" );
         return false;
      }

      return true;

   }


   private boolean isSenhaValido( HttpServletRequest request, String senha ) {
      if( isEmpyt( senha ) ){
         request.setAttribute( "erro-senha", "Senha é obrigátorio" );
         return false;
      }
      else if( senha.trim().length() < 8 ){
         request.setAttribute( "erro-senha", "Senha deve ter no mínimo 8 caracteres" );
         return false;
      }else if(  !isLetrasNumerosSimbolos(senha)) {
         request.setAttribute( "erro-senha", "Senha  deve conter letras, números e símbolos." );
      }
      return true;

   }
   
   private boolean isLetrasNumerosSimbolos(String string) {
      for (char c : string.toCharArray()) {
         if (!Character.isLetterOrDigit(c) && !Character.isWhitespace(c) && !Character.isAlphabetic( c )) {
             return false;
         }
     }
     return true;
 }
     


   private boolean isEmpyt( String string ) {
      if( string == null )
         return true;

      return string.trim().length() <= 0;
   }

}
