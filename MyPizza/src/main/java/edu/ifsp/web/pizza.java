package edu.ifsp.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class pizza
 */
@WebServlet("/pizza")
public class pizza extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html; charset=utf-8");
		
		PrintWriter out = response.getWriter();

		String tamanho = request.getParameter("tamanho");
		List<String> sabores = Arrays.asList(request.getParameterValues("sabor"));
		String nome = request.getParameter("nome");
		String telefone = request.getParameter("telefone");
		String endereco = request.getParameter("endereco");

		String saboresFormatados = "";

		double valor = calculaValor(sabores);

		for (String string : sabores) {

			saboresFormatados += string + ", ";
		}

		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset=\"utf-8\">");
		out.println("<title>Pedido</title>");
		out.println("</head>");
		out.println("<body>");

		out.println("<p>Nome: " + nome + "</p>");
		out.println("<p>Telefone: " + telefone + "</p>");
		out.println("<p>Endereço: " + endereco + "</p>");

		out.println("<p>Tamanho: " + tamanho + "</p>");
		out.println("<p>Sabores: " + saboresFormatados + "</p>");

		out.println("<h2>Valor: R$" + valor + "<h2>");

		out.println("</body>");
		out.println("</html>");

	}

	private double calculaValor(List<String> sabores) {
		double base = 40;
		int tamanho = sabores.size() - 3;
		double valorFinal = base;

		if (tamanho > 0) {
			valorFinal += (base * (0.2)) * tamanho;
		}

		return valorFinal;
	}

}
