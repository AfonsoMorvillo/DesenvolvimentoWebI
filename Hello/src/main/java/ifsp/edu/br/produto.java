package ifsp.edu.br;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.tagplugins.jstl.core.ForEach;

/**
 * Servlet implementation class produto
 */
@WebServlet("/produto")
public class produto extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		final PrintWriter writer = response.getWriter();

		response.setContentType("text/html; charset=utf-8");

		final List<Produto> produtos = criaProdutos();

		writer.println("<!DOCTYPE html>");
		writer.println("<html>");
		writer.println("<head>");
		writer.println("<meta charset=\"utf-8\">");
		writer.println("<title>Hello</title>");
		writer.println("</head>");
		writer.println("<body>");


		List<Produto> produtosEncontrados = new ArrayList<>();

		if (request.getParameter("chave") != null) {
			Integer chaveProduto = Integer.parseInt(request.getParameter("chave"));

			produtosEncontrados = produtos.stream().filter(prod -> prod.getCodigo() == chaveProduto)
					.collect(Collectors.toList());
		} else {
			String descricaoProduto = request.getParameter("busca");
			
			if (request.getParameter("busca") == null) {
				descricaoProduto = "";
			}
			
			produtosEncontrados = buscaProduto(produtos, descricaoProduto);
		}

		writer.println("<table border=\"1\">");

		/* Cabeçalho da tabela */
		writer.println("<tr>");
		writer.println("<th>Código</th>");
		writer.println("<th>Descrição</th>");
		writer.println("<th>Preço</th>");
		writer.println("</tr>");

		for (Produto p : produtosEncontrados) {

			writer.println("<tr>");
			writer.printf("<td>%d</td>\n", p.getCodigo());
			writer.printf("<td>%s</td>\n", p.getDescricao());
			writer.printf("<td>%.2f</td>\n", p.getPreco());
			writer.println("</tr>");

		}
		writer.println("</table>");

		writer.println("</body>");
		writer.println("</html>");

	}

	private List<Produto> buscaProduto(final List<Produto> produtos, String descricaoProduto) {
		List<Produto> produtosEncontrados;
		produtosEncontrados = produtos.stream().filter(prod -> prod.getDescricao().contains(descricaoProduto))
				.collect(Collectors.toList());
		return produtosEncontrados;
	}

	private List<Produto> criaProdutos() {
		return List.of(new Produto(1, "Teclado 104 teclas", 104), new Produto(2, "SSD Kingston 1 TB", 437),
				new Produto(3, "Pen drive 16 GB", 40.70), new Produto(4, "Processador AMD", 790),
				new Produto(5, "Gabinete Corsair", 530));

	}

}
