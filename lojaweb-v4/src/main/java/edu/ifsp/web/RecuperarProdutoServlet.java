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

@WebServlet("/produto/recuperar")
public class RecuperarProdutoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		ProdutoDAO dao = new ProdutoDAO();
		try {
			Produto p = dao.findById(id);
			request.setAttribute("produto", p);
			Template.render("produto/detalhes", request, response);			
		} catch (PersistenceException e) {			
			e.printStackTrace();
		}
		
	}

}
