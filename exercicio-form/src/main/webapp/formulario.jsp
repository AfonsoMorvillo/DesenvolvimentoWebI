<%@page import="edu.ifsp.web.dto.Sabor"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="edu.ifsp.web.dto.Sabor" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Pizza</title>
</head>
<body>

<h1>Pizzaria</h1>
<p><strong>Faça seu pedido</strong></p>


<form action="pedido" method="post">
	Tamanho:
	<input type="radio" name="tamanho" value="P" id="tamanho-p" required>
	<label for="tamanho-p">Pequena</label>
	<input type="radio" name="tamanho" value="M" id="tamanho-m" required>
	<label for="tamanho-m">Média</label>
	<input type="radio" name="tamanho" value="G" id="tamanho-g" required>
	<label for="tamanho-g">Grande</label><br>

	<br>Escolha os sabores: <br>
	
	<% List<Sabor> sabores = (List<Sabor>)request.getAttribute(  "sabores" );
		for (Sabor sabor: sabores){	%>
	<input type="checkbox" name="sabores" id="sabor-<%=sabor.getValor(  ) %>" value="<%=sabor.getValor(  ) %>">
	<label for="sabor-<%=sabor.getValor(  ) %>"><%=sabor.getDescricao( )%></label><br>
<%} %>	

	Forma de pagamento: <br>
	<input type="radio" name="forma-pagamento" value="pix" id="forma-pix" required>
	<label for="forma-pix">PIX</label>
	<input type="radio" name="forma-pagamento" value="cartao" id="forma-cartao" required>
	<label for="forma-cartao">Cartão</label>
		<% if (request.getAttribute( "erro-forma" ) != null){ %>
		<span style="color: red"><%= request.getAttribute( "erro-forma" ) %></span>
			<%} %>

	
	<br>
	
	<label for="quantidade">Quantidade: </label>
			<input type="number" name="quantidade" id="quantidade" min="1"><br>	
			<% if (request.getAttribute( "erro-quantidade" ) != null){ %>
			<span style="color: red"><%= request.getAttribute( "erro-quantidade" ) %></span>
			<%} %>
	
	<br>
	
	<label for="nome">Seu nome: </label>
	<input type="text" name="nome" id="nome" minlength="3" required><br>

	<br>
	<label for="telefone">Telefone: </label>
	<input type="tel" name="telefone" id="telefone" placeholder="(99) 99999-9999" required><br>

	<br>
	<label for="endereco">Endereço: </label>
	<input type="text" name="endereco" id="endereco" required><br>


	<br>
	<button type="submit" value="submit">Enviar</button>	
</form>

</body>
</html>