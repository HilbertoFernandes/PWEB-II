<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<title>Biblioteca - Busca de Livro - Título</title>
<link href="${pageContext.request.contextPath}/css/memoriam.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body
	style="background-image: url('${pageContext.request.contextPath}/imagens/livros.jpg');">
	<c:import url="../template/header.jsp" />

	<div class="container"
		style="width: 30%; padding: 1%; margin-top: 5%; background-color: white; border-radius: 5px;">
		<a href="${pageContext.request.contextPath}/usuario/home.jsp"
			type="button" class="close" aria-label="Close"><span
			class="glyphicon glyphicon-remove-circle"
			style="font-size: 30px; color: red;"></span></a> <a
			href="${pageContext.request.contextPath}/usuario/home.jsp"
			type="button" class="close" aria-label="Close">X</a>
		<h3>Informe o titulo desejado</h3>
		<form action="${pageContext.request.contextPath}/controller.do"
			method="POST" class="form-horizontal">
			<input type="hidden" name="op" value="ConsultaTituloComando">

			<input id="titulo" value="" name="titulo" class="form-control"
				type="text" placeholder="Título" />
			<div style="text-align: right;">
				<input type="submit" class="form-control btn btn-primary"
					value="Buscar" style="width: 30%;">
			</div>
		</form>
	</div>
	<c:set var="endofconversation" value="yes" scope="request" />
</body>
</html>