<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Exemplo</title>
</head>
<body>
<p>Oi, <%= request.getParameter("nome") %>!</p>

<ul>
	<% 
	for(int i = 0; i < 5; i++){
	%>
	<li>Item <%= i %></li>
	<% 
	}
	%>

</ul>


</body>
</html>