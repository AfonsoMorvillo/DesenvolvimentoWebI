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

/**
 * Servlet implementation class formularioProduto
 */
@WebServlet("/produto/salvar")
public class formularioProduto extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Produto produto = new Produto(  );
	private ProdutoDAO dao = new ProdutoDAO();
       

	
	// Para editar produto basta passar na URL o id salvar?id=15
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   
	   // Passou id do produto na url
      if (request.getParameter("id") != null) {
         int id = Integer.parseInt(request.getParameter("id")); // validar caso nao seja string
         
         try{
            produto = dao.findProduto(new Produto( id, "desc", 0.0 ));
            request.setAttribute("produto", produto);
            request.setAttribute("edicao", true);
         }
         catch( PersistenceException e ){
            e.printStackTrace();
         }
         
	   }else {
	      produto = new Produto( "", 0 );
	      request.setAttribute("produto", produto);
	   }
	   
		TemplateHelper.render("formularioProduto", request, response);
			
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		boolean isvalid = validar(request);
		int chaveGerada ;
		
		if (isvalid) {
		   produto.setDescricao( request.getParameter( "descricao" ) );
		   produto.setPreco( Double.parseDouble( request.getParameter( "preco" ) ));
			
		   
		   if (produto.getId() > 0) {
		      
		      if (request.getParameter( "excluir" ) != null) {
		         dao.excluirProdutos( produto );
		      }else {
		         dao.editarProduto( produto );
		      }
		      chaveGerada = 0;
		   }
		   else {
		      chaveGerada = dao.salvarProduto(produto);
		   }
			
			enviaTelaProdutos(request, response, chaveGerada);
		}else {
		   // erros e volta pra pagina com valores preenchidos
		   request.setAttribute("produto", produto);
			TemplateHelper.render("formularioProduto", request, response);
		}
		
	}

	private boolean validar(HttpServletRequest request) {
		
	     Produto aux = new Produto();
		  boolean res = true;
		  
		  String descricao =  request.getParameter( "descricao" );
        
        if(descricao == null || descricao.trim().isEmpty()) {         
          res = false;
          request.setAttribute( "erroDescricao", "Descrição é obrigátorio!" );
        }else {
           aux.setDescricao( descricao );
           aux.setId( produto.getId() );
           produto = aux;
        }
	      
	      try{
	         Double preco = Double.parseDouble( request.getParameter( "preco" ) );
	         if( preco <= 0 ){
	        	request.setAttribute( "erroPreco", "O preço deve ser maior que 0." );
	         res = false;
	         }
	         
	      }
	      catch( Exception e ){
	         res = false;
	         request.setAttribute( "erroPreco", "Preço deve ser um número" );
	         
	      }
	      
	      if (res== true){
	         aux.setPreco( Double.parseDouble( request.getParameter( "preco" ) ) );
	      }

	   return res;
	}
	

   private void enviaTelaProdutos(HttpServletRequest request, HttpServletResponse response, int chaveProduto) throws IOException {
      try {
         
         ProdutoDAO dao = new ProdutoDAO();
         List<Produto> produtos = dao.findAll();
         request.setAttribute("produtos", produtos);
         
         if (chaveProduto > 0) {
            request.setAttribute( "chaveProduto", chaveProduto );
         }
         
         TemplateHelper.render("produtos", request, response);
         
      } catch (PersistenceException e) {
         e.printStackTrace();
      }
   }

}
