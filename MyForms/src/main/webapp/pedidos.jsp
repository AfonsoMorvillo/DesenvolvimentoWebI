<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="edu.ifsp.pizzaria.FormaContato" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Pedidos</title>
<link rel="stylesheet" href="style.css">
</head>
<body>

<div class="container">
	<div class="form">

		<div class="form-header">
			<h1>Pedidos</h1>
		</div>
		
	<form action="pedido" method="post" style="width: 100%;">
		
		<div class="input-box">
			<label for="nome">Nome: </label>
			<input type="text" name="nome" id="nome" required="required" minlength="3"><br>
		</div>

		<div class="input-box">

			<label for="email">E-mail: </label>
			<input type="email" name="email" id="email" placeholder="usario@exemplo.com"><br>
		</div>
		
		<div class="input-box">

			<label for="cpf">CPF: </label>
			<input name="cpf" id="cpf" placeholder="999.999.999-99" pattern="\d{3}\.\d{3}\.\d{3}-\d{2}"><br>
			
		</div>

		<div class="input-box">
			<label for="quantidade">Quantidade: </label>
			<input type="number" name="quantidade" id="quantidade" min="1"><br>	
			<% if (request.getAttribute( "erro-quantidade" ) != null){ %>
			<span class="erro"><%= request.getAttribute( "erro-quantidade" ) %>></span>
			<%} %>
		</div>
		Prefiro pagar com:
		<input type="radio" name="forma-pagamento" id="forma-pagamento-cartao" value="cartao">
		<label for="forma-pagamento-cartao">Cartão de crédito</label>
		
	<input type="radio" name="forma-pagamento" id="forma-pagamento-pix" value="pix">
	<label for="forma-pagamento-pix">Pix</label><br>
	
	Aceito receber promoções por:
	
	<%
	List<FormaContato> formasContato = (List<FormaContato>) request.getAttribute( "formasContato" );
		for(FormaContato forma : formasContato){
			%>
			<input type="checkbox" name="promocoes" id="promo-<%= forma.getValor() %>" value="<%= forma.getValor() %>">
			<label for="promo-<%= forma.getValor() %>"><%=forma.getDescricao() %></label>
			<%
		}
		%>
		
		<br>
		<button type="submit" value="submit">Enviar</button>
	</form>
</div>
</div>
</body>
</html>