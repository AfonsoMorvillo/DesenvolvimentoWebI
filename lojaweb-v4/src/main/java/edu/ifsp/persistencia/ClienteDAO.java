package edu.ifsp.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import edu.ifsp.modelo.Cliente;
import edu.ifsp.modelo.Produto;

public class ClienteDAO {

   public Cliente save( Cliente cliente ) throws PersistenceException {

      if( cliente.isNew() ){
         insert( cliente );
      }
      else{
         update( cliente );
      }

      return cliente;
   }


   private void insert( Cliente cliente ) throws PersistenceException {
      try( Connection conn = DatabaseConnector.getConnection()){

         PreparedStatement ps = conn.prepareStatement( "INSERT INTO cliente (usuario, nome, email, logradouro, bairro, cidade, estado, cep) VALUES (?, ?, ?, ?, ?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS );
         ps.setLong( 1, cliente.getUserId() );
         ps.setString( 2, cliente.getNome() );
         ps.setString( 3, cliente.getEmail() );
         ps.setString( 4, cliente.getLogradouro() );
         ps.setString( 5, cliente.getBairro() );
         ps.setString( 6, cliente.getCidade() );
         ps.setString( 7, cliente.getEstado() );
         ps.setString( 8, cliente.getCep() );

         ps.executeUpdate();

         ResultSet rs = ps.getGeneratedKeys();
         if( !rs.next() ){
            throw new IllegalStateException( "Expected key missing." );
         }

         int id = rs.getInt( 1 );
         cliente.setId( id );

      }
      catch( SQLException e ){
         throw new PersistenceException( e );
      }
   }


   private void update( Cliente cliente ) throws PersistenceException {
      try( Connection conn = DatabaseConnector.getConnection()){

         PreparedStatement ps = conn.prepareStatement( "UPDATE cliente SET nome = ?,email = ?,logradouro = ?,bairro = ?,cidade = ?,estado= ?,   cep = ? WHERE id = ?;" );
         ps.setString( 1, cliente.getNome() );
         ps.setString( 2, cliente.getEmail() );
         ps.setString( 3, cliente.getLogradouro() );
         ps.setString( 4, cliente.getBairro() );
         ps.setString( 5, cliente.getCidade() );
         ps.setString( 6, cliente.getEstado() );
         ps.setString( 7, cliente.getCep() );
         ps.setLong( 8, cliente.getId() );
         ps.executeUpdate();

      }
      catch( SQLException e ){
         throw new PersistenceException( e );
      }

   }


   public Cliente findById( int id ) throws PersistenceException {
      Cliente cliente = null;

      try( Connection conn = DatabaseConnector.getConnection()){

         PreparedStatement ps = conn.prepareStatement( "SELECT id, nome, email, logradouro, bairro, cidade, estado, cep, usuario FROM cliente WHERE id = ?;" );
         ps.setInt( 1, id );
         ResultSet rs = ps.executeQuery();

         if( rs.next() ){
            cliente = mapRow( rs );
         }

      }
      catch( SQLException e ){
         throw new PersistenceException( e );
      }

      return cliente;
   }


   private Cliente mapRow( ResultSet rs ) throws SQLException {
      Cliente cliente = new Cliente();
      cliente.setId( rs.getInt( "id" ) );
      cliente.setNome( rs.getString( "nome" ) );
      cliente.setEmail( rs.getString( "email" ) );
      cliente.setLogradouro( rs.getString( "logradouro" ) );
      cliente.setBairro( rs.getString( "bairro" ) );
      cliente.setCidade( rs.getString( "cidade" ) );
      cliente.setEstado( rs.getString( "estado" ) );
      cliente.setCep( rs.getString( "cep" ) );
      cliente.setUserId( rs.getInt( "usuario" ) );
      return cliente;
   }
}
