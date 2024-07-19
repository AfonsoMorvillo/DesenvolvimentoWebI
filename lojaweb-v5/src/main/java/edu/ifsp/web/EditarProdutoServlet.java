package edu.ifsp.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ifsp.modelo.Produto;
import edu.ifsp.persistencia.PersistenceException;
import edu.ifsp.persistencia.ProdutoDAO;
import edu.ifsp.web.templates.Template;

@WebServlet("/produto/editar")
public class EditarProdutoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Produto produto = null;

		if (request.getParameter("id") == null) {
			
			produto = new Produto();			
		} else {
		
			int id = Integer.parseInt(request.getParameter("id"));
			ProdutoDAO dao = new ProdutoDAO();
			try {
				produto = dao.findById(id);
			} catch (PersistenceException e) {			
				e.printStackTrace();
			}
		}
		
		
		request.setAttribute("produto", produto);
		Template.render("produto/editar", request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Produto produto = new Produto();
		if (!validate(request)) {
			request.setAttribute("produto", produto);
			Template.render("produto/editar", request, response);
			return;
		}
				
		
		if (request.getParameter("id") != null) {
			produto.setId(Integer.parseInt(request.getParameter("id")));
		}
		produto.setDescricao(request.getParameter("descricao"));
		produto.setPreco(Double.parseDouble(request.getParameter("preco")));
		
		ProdutoDAO dao = new ProdutoDAO();
		try {
			dao.save(produto);
			response.sendRedirect("recuperar?id=" + produto.getId());
			
		} catch (PersistenceException e) {
			// TODO enviar para página de edição, com mensagem de erro
			e.printStackTrace();
		}
		
	}

	private boolean validate(HttpServletRequest request) {
		boolean valid = true;
		
		String descricao = request.getParameter("descricao");
		if (descricao == null || descricao.isBlank()) {
			valid = false;
			request.setAttribute("erroDescricao", "Este campo não pode ficar em branco.");
		}
		
		try {
			double preco = Double.parseDouble(request.getParameter("preco"));
			if (preco < 0) {
				valid = false;
				request.setAttribute("erroPreco", "O valor deste campo não pode ser negativo.");
			}
		} catch (NumberFormatException e) {
			valid = false;
			request.setAttribute("erroPreco", "Valor inválido: " + request.getParameter("preco"));
		}
		
				
		return valid;
	}

}
