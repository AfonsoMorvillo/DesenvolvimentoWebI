package edu.ifsp.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ifsp.modelo.Produto;
import edu.ifsp.persistencia.PersistenceException;
import edu.ifsp.persistencia.ProdutoDAO;
import edu.ifsp.web.templates.TemplateHelper;

@WebServlet("/produto/listar")
public class ListarProdutosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			ProdutoDAO dao = new ProdutoDAO();
			List<Produto> produtos = dao.findAll();
			request.setAttribute("produtos", produtos);
			TemplateHelper.render("produtos", request, response);
			
		} catch (PersistenceException e) {
			e.printStackTrace();
		}

		
	}
	

}




