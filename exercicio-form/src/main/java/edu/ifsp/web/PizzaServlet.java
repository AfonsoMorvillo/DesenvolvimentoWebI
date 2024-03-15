package edu.ifsp.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/pizza")
public class PizzaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/plain");		
		final PrintWriter out = response.getWriter();
		
		out.println("Pedido");
		out.append("Tamanho: ").println(request.getParameter("tamanho"));
		out.println("Sabores:");
		String[] sabores = request.getParameterValues("sabores");
		if (sabores != null) {
			for (String sabor : sabores) {
				out.println(sabor);
			}
		}
		out.append("Nome: ").println(request.getParameter("nome"));
		out.append("Telefone: ").println(request.getParameter("telefone"));
		out.append("Endereço: ").println(request.getParameter("endereco"));

		double preco = 40;
		if (sabores != null && sabores.length > 3) {
			int nExtras = sabores.length - 3;
			preco = (1 + 0.2 * nExtras) * preco;
		}
		out.append("Preço: ").println(preco);
	}

}
