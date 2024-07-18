package edu.ifsp.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ifsp.modelo.Cliente;
import edu.ifsp.modelo.Usuario;
import edu.ifsp.persistencia.ClienteDAO;
import edu.ifsp.persistencia.PersistenceException;
import edu.ifsp.persistencia.UsuarioDAO;
import edu.ifsp.web.templates.Template;

@WebServlet( "/cliente/cadastrar" )
public class CadastrarClienteServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;

   protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
      Cliente cliente = null;

      if( request.getParameter( "id" ) == null ){

         cliente = new Cliente();
      }
      else{

         int id = Integer.parseInt( request.getParameter( "id" ) );
         ClienteDAO dao = new ClienteDAO();
         try{
            cliente = dao.findById( id );
         }
         catch( PersistenceException e ){
            e.printStackTrace();
         }
      }

      request.setAttribute( "cliente", cliente );
      Template.render( "cliente/cadastrar", request, response );

   }


   protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
      Cliente cliente = new Cliente();

      if( request.getParameter( "id" ) != null ){
         cliente.setId( Integer.parseInt( request.getParameter( "id" ) ) );
      }
      
      cliente.setNome( request.getParameter( "nome" ) );
      cliente.setEmail( request.getParameter( "email" ) );
      cliente.setLogradouro( request.getParameter( "logradouro" ) );
      cliente.setBairro( request.getParameter( "bairro" ) );
      cliente.setCidade( request.getParameter( "cidade" ) );
      cliente.setEstado( request.getParameter( "estado" ) );
      cliente.setCep( request.getParameter( "cep" ) );
      cliente.setPassword( request.getParameter( "password" ) );

      try{
         if( cliente.isNew() ){

            Usuario usuario = new Usuario();
            UsuarioDAO usuarioDao = new UsuarioDAO();

            usuario.setUsername( cliente.getNome() );
            usuario.setPassword( cliente.getPassword() );
            usuarioDao.insert( usuario );

            cliente.setUserId( usuario.getId() );
         }

         ClienteDAO dao = new ClienteDAO();
         dao.save( cliente );
         response.sendRedirect("detalhes?id=" + cliente.getId());

      }
      catch( PersistenceException e ){
         e.printStackTrace();
      }

   }

}
