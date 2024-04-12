package edu.ifsp.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.ifsp.modelo.Produto;

public class ProdutoDAO {
	
	public List<Produto> findAll() throws PersistenceException {
		List<Produto> produtos = new ArrayList<>();

		try (Connection conn = DatabaseConnector.getConnection()) {

			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select id, descricao, preco from produto;");

			while (rs.next()) {
				int id = rs.getInt("id");
				String descricao = rs.getString("descricao");
				double preco = rs.getDouble("preco");

				produtos.add(new Produto(id, descricao, preco));
			}

		} catch (SQLException e) {
			throw new PersistenceException(e);
		}
		return produtos;
	}

	public int salvarProduto(Produto produto) {

		try (Connection conn = DatabaseConnector.getConnection()) {

			String insert = "insert into produto (descricao, preco) values ( ? , ? )";
			PreparedStatement psmt = conn.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
			psmt.setString(1, produto.getDescricao());
			psmt.setDouble(2, produto.getPreco());

		   int affectedRows = psmt.executeUpdate();

		    if (affectedRows == 0) {
		        throw new SQLException("Inserção falhou, nenhuma linha afetada.");
		    }

		    try (ResultSet generatedKeys = psmt.getGeneratedKeys()) {
		        if (generatedKeys.next()) {
		            long idGerado = generatedKeys.getLong(1);
		            return (int)idGerado;
		        } else {
		            throw new SQLException("Nenhuma chave gerada retornada.");
		        }
		    }

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public int editarProduto (Produto produto) {
	   try (Connection conn = DatabaseConnector.getConnection()) {

         String update = "update produto set descricao = ? , preco = ? where id = ?";
         PreparedStatement psmt = conn.prepareStatement(update);
         psmt.setString(1, produto.getDescricao());
         psmt.setDouble(2, produto.getPreco());
         psmt.setInt(3, produto.getId());
       
         return psmt.executeUpdate();

      } catch (SQLException e) {
         e.printStackTrace();
      }
      return -1;
	}
	
	
	public int  excluirProdutos (Produto produto) {
		try (Connection conn = DatabaseConnector.getConnection()){
			  String delete = "delete from produto where id = ?";
		         PreparedStatement psmt = conn.prepareStatement(delete);
		         psmt.setInt(1, produto.getId());
		         psmt.executeUpdate();
		         return 0 ;
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public Produto findProduto(Produto produto) throws PersistenceException {
		try (Connection conn = DatabaseConnector.getConnection()) {
			String consulta = "select descricao, preco, id from produto where id = ?";
			PreparedStatement psmt = conn.prepareStatement(consulta);
			psmt.setInt(1, produto.getId());
			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
			 return	new Produto(rs.getInt( "id" ),rs.getString("descricao"),rs.getDouble("preco"));
			}
		}
		 catch (SQLException e) {
			throw new PersistenceException(e);
		}
		return produto = null;
	}

}