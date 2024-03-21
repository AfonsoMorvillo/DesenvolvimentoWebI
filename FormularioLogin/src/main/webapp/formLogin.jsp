<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="style.css">
<title>Login</title>
</head>
<body>
	<div class="container">
		<form action="login" method="post">
			
			<div class="input-box">
			<label for="usuario">Usuário</label>
			<input type="text" id="usuario" name="usuario" required="required" minlength="3">
			<% if (request.getAttribute( "erro-usuario" ) != null){ %>
		<span class="erro"><%= request.getAttribute( "erro-usuario" ) %></span>
			<%} %>
			</div>
			
			<div  class="input-box">
			<label for="senha">Senha</label>
			<input type="password" id="senha" name="senha" required="required" minlength="8">
			<% if (request.getAttribute( "erro-senha" ) != null){ %>
		<span class="erro"><%= request.getAttribute( "erro-senha" ) %></span>
			<%} %>
			</div>
			
			<div class="button-box">
			<button class="button-style" type="submit" value="submit">Login</button>
			</div>
			</form>
		</div>
</body>
</html>