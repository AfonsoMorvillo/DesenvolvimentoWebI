package edu.ifsp.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.ifsp.modelo.Usuario;
import edu.ifsp.persistencia.PersistenceException;
import edu.ifsp.persistencia.UsuarioDAO;
import edu.ifsp.web.templates.Template;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Flash.move(request, "login");
		Template.render("common/login", request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		UsuarioDAO dao = new UsuarioDAO();
		Usuario usuario = null;
		try {
			usuario = dao.check(username, password);
		} catch (PersistenceException e) {
			e.printStackTrace();
		}
		
		if (usuario != null) {
			/* salvando usuário na sessão */
			HttpSession session = request.getSession(true);
			session.setAttribute("usuario", usuario);
			response.sendRedirect("home");
		} else {
			Flash.setAttribute(request, "login", "auth_error", "Nome de usuário ou senha inválidos.");
			response.sendRedirect("login");			
		}
	}

}
