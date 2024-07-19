package edu.ifsp.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.ifsp.modelo.Carrinho;
import edu.ifsp.modelo.CarrinhoVisualizar;
import edu.ifsp.modelo.Produto;
import edu.ifsp.modelo.Usuario;

public class CarrinhoDAO {

   public Carrinho save( Carrinho carrinho ) throws PersistenceException {

      if( carrinho.isNew() ){
         insert( carrinho );
      }
      // else{
      // update( carrinho );
      // }

      return carrinho;
   }
   
   public void teste(Produto produto) {
      System.out.println( produto );
   }
   
   public void insert( Usuario usuario, Produto produto) throws PersistenceException {
      System.out.println( "teste" );
      insert( new Carrinho(usuario, produto) );
   }


   private void insert( Carrinho carrinho ) throws PersistenceException {
      try( Connection conn = DatabaseConnector.getConnection()){

         PreparedStatement ps = conn.prepareStatement( "INSERT INTO carrinho (usuario,produto) VALUES (?, ?);", Statement.RETURN_GENERATED_KEYS );
         ps.setInt( 1, carrinho.getUsuario().getId() );
         ps.setInt( 2, carrinho.getProduto().getId() );
         ps.executeUpdate();

         ResultSet rs = ps.getGeneratedKeys();
         if( !rs.next() ){
            throw new IllegalStateException( "Expected key missing." );
         }

         int id = rs.getInt( 1 );
         carrinho.setId( id );

      }
      catch( SQLException e ){
         throw new PersistenceException( e );
      }
   }


   public void deleteItemCarrinho( int idCarrinho, Usuario usuario ) throws PersistenceException {
      try( Connection conn = DatabaseConnector.getConnection()){

         PreparedStatement ps = conn.prepareStatement( "DELETE FROM carrinho WHERE id = ? AND usuario = ?;" );
         ps.setInt( 1, idCarrinho );
         ps.setInt( 2, usuario.getId() );
         ps.executeUpdate();

      }
      catch( SQLException e ){
         throw new PersistenceException( e );
      }
   }


   public void deleteCarrinho( Usuario usuario ) throws PersistenceException {
      try( Connection conn = DatabaseConnector.getConnection()){

         PreparedStatement ps = conn.prepareStatement( "DELETE FROM carrinho WHERE  usuario = ?;" );
         ps.setInt( 1, usuario.getId() );
         ps.executeUpdate();

      }
      catch( SQLException e ){
         throw new PersistenceException( e );
      }
   }


   public List<Carrinho> visualizarCarrinhoDoUsario( Usuario usuario ) throws PersistenceException {
      List<Carrinho> carrinho = new ArrayList<>();

      try( Connection conn = DatabaseConnector.getConnection()){

         PreparedStatement ps = conn.prepareStatement( "select c.id idCarrinho, p.id idProduto, p.descricao descricao, p.preco preco from carrinho c left join produto p on p.id = c.produto\n"
               + "where c.usuario = ?;" );

         ps.setInt( 1, usuario.getId() );

         ResultSet rs = ps.executeQuery();

         while( rs.next() ){
            Carrinho itemCarrinho = mapeiaCarrinho( rs );
            carrinho.add( itemCarrinho );
         }

      }
      catch( SQLException e ){
         throw new PersistenceException( e );
      }
      return carrinho;
   }


   private Carrinho mapeiaCarrinho( ResultSet rs ) throws SQLException {
      Carrinho carrinho = new Carrinho();
      carrinho.setId( rs.getInt( "idCarrinho" ) );

      Produto p = new Produto();
      p.setId( rs.getInt( "idProduto" ) );
      p.setDescricao( rs.getString( "descricao" ) );
      p.setPreco( rs.getDouble( "preco" ) );

      carrinho.setProduto( p );

      return carrinho;
   }

}
