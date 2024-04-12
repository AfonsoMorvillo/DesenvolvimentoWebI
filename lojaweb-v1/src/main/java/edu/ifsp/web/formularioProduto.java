package edu.ifsp.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ifsp.modelo.Produto;
import edu.ifsp.persistencia.PersistenceException;
import edu.ifsp.persistencia.ProdutoDAO;
import edu.ifsp.web.templates.TemplateHelper;

/**
 * Servlet implementation class formularioProduto
 */
@WebServlet("/produto/salvar")
public class formularioProduto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//	   request.setAttribute( "erroPreco", true );
	   request.getParameter("id");
		TemplateHelper.render("formularioProduto", request, response);
			
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		boolean isvalid = validar(request);
		RequestDispatcher rd = null;
		
		if (isvalid) {
			Produto p = new Produto(  request.getParameter( "descricao" ),  Double.parseDouble( request.getParameter( "preco" ) ));
			
			ProdutoDAO produtoDAO =  new ProdutoDAO();
			
			int key = produtoDAO.salvarProduto(p);
			System.out.println( key );
			
		   request.setAttribute( "erro-preco", "O preço deve ser maior que 1." );
			enviaTelaProdutos(request, response);
		}else {
			TemplateHelper.render("formularioProduto", request, response);
		}
		
//		rd.forward(request, response);
		
		
	}

	private void enviaTelaProdutos(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			
			ProdutoDAO dao = new ProdutoDAO();
			List<Produto> produtos = dao.findAll();
			request.setAttribute("produtos", produtos);
			TemplateHelper.render("produtos", request, response);
			
		} catch (PersistenceException e) {
			e.printStackTrace();
		}
	}

	private boolean validar(HttpServletRequest request) {
		
		  boolean res = true;
	      
	      try{
	         Double preco = Double.parseDouble( request.getParameter( "preco" ) );
	         if( preco < 1 ){
	        	request.setAttribute( "erro-preco", "O preço deve ser maior que 1." );
	            res = false;
	         }
	         
	      }
	      catch( Exception e ){
	         res = false;
	         request.setAttribute( "erro-preco", "Preço deve ser um número" );
	         
	      }
	      
	      String descricao =  request.getParameter( "descricao" );
	        
	      if(descricao == null || descricao.trim().isEmpty()) {	    	  
	    	  res = false;
	    	  request.setAttribute( "erro-descricao", "Descrição é obrigátorio!" );
	      }

	   return res;
	}

}
