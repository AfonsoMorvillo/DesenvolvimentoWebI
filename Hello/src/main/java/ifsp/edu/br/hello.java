package ifsp.edu.br;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class hello
 */
@WebServlet("/hello")
public class hello extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("deprecation")
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		response.getWriter().append("Served at: ").append(request.getContextPath());

		response.setContentType("text/html; charset=utf-8");

		final PrintWriter writer = response.getWriter();

		writer.println("<!DOCTYPE html>");
		writer.println("<html>");
		writer.println("<head>");
		writer.println("<meta charset=\"utf-8\">");
		writer.println("<title>Hello</title>");
		writer.println("</head>");
		writer.println("<body>");

//		writer.println("<h1>Agora são: " + (new Date()) + "</h1>");
//		writer.println("<img src=\"https://suap.ifsp.edu.br/media/alunos/216374.p5bvFA8FHzCN.jpeg\" width=\"1920\" height=\"500\"/>");

		String nome = request.getParameter("nome");
		String idade = request.getParameter("idade");
//		String nascimento =  request.getParameter("nascimento");
//		
//		Date dateUser = new Date(nascimento);
//		
//		Date atual = new Date();	
//		
//		int idadeAnoQueVem = (atual.getYear()+1)- dateUser.getYear();

		writer.println("<p>Olá " + nome + ", você tem: " + idade + " anos" + "</p>");

		writer.println("</body>");
		writer.println("</html>");

	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		super.doPost(req, resp);
	}

}
