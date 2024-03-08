package edu.ifsp.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/pedido")
public class PedidoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/plain");
		
		final PrintWriter out = response.getWriter();
		
		out.println("Dados recebidos");
		out.append("Nome: ").println(request.getParameter("nome"));		
		out.append("E-mail: ").println(request.getParameter("email"));
		out.append("CPF: ").println(request.getParameter("cpf"));
		out.append("Quantidade: ").println(request.getParameter("quantidade"));
		out.append("Forma de pagamento: ").println(request.getParameter("forma-pagamento"));
		
		String[] promocoes = request.getParameterValues("promocoes");
		out.println("Aceita mensagens de promoções por:");
		if (promocoes != null) {
			for (String promo : promocoes) {
				out.println(promo);
			}		
		}
		
	}

}
