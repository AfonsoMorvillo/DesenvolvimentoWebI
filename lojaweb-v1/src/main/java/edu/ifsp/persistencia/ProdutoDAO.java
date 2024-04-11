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

			return psmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

}