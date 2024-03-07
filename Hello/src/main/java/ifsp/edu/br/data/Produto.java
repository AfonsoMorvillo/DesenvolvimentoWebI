package ifsp.edu.br.data;

import java.util.List;
import java.util.stream.Collectors;

public class Produto {
	private int codigo;
	private String descricao;
	private double preco;

	public Produto(int codigo, String descricao, double preco) {
		this.codigo = codigo;
		this.preco = preco;
		this.descricao = descricao;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public static List<Produto> criaProdutos() {
		return List.of(new Produto(1, "Teclado 104 teclas", 104), new Produto(2, "SSD Kingston 1 TB", 437),
				new Produto(3, "Pen drive 16 GB", 40.70), new Produto(4, "Processador AMD", 790),
				new Produto(5, "Gabinete Corsair", 530));

	}
	
	public static List<Produto> buscaProduto(final List<Produto> produtos, String descricaoProduto) {
		return produtos.stream().filter(prod -> prod.getDescricao().contains(descricaoProduto))
				.collect(Collectors.toList());
	}

}
