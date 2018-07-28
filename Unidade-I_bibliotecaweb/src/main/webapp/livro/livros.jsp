<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<title>Memoriam - Cadastro de Usuarios</title>
<link href="${pageContext.request.contextPath}/css/memoriam.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body
	style="background-image: url('${pageContext.request.contextPath}/imagens/livros.jpg');">
	<c:import url="../template/header.jsp" />
	<c:if test="${not emptylivros}">
		<div class="container"
			style="width: 99%; padding: 1%; margin-top: 5%; background-color: white; border-radius: 5px;">
			<a href="${pageContext.request.contextPath}/usuario/home.jsp"
				type="button" class="close" aria-label="Close"><span
				class="glyphicon glyphicon-remove-circle"
				style="font-size: 30px; color: red;"></span></a>
			<table class="table">
				<tr align="left">
					<th>Titulo</th>
					<th>Autor</th>
					<th>Sinopse</th>
					<th>Quantidade</th>
				</tr>
				<c:forEach var="livro" items="${livros}">
					<tr align="left">
						<td>${livro.titulo}</td>
						<td>${livro.autor}</td>
						<td>${livro.sinopse}</td>
						<td>${livro.quantidade}</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</c:if>
	<c:if test="${empty livros}">
		<div class="container"
			style="width: 30%; padding: 1%; margin-top: 5%; background-color: white; border-radius: 5px;">
			<a href="${pageContext.request.contextPath}/usuario/home.jsp"
				type="button" class="close" aria-label="Close">X</a>

			<h3>Livro não localizado.</h3>
		</div>
	</c:if>
	<c:set var="endofconversation" value="yes" scope="request" />
</body>
</html>