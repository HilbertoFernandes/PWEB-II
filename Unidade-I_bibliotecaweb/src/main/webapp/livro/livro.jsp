<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<title>Biblioteca - Cadastro de Usuario</title>
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
		style="width: 30%; padding: 1%; margin-top: 5%; background-color: white; border-radius: 5px; padding: 1%; padding-bottom: 0px;">
		<a href="${pageContext.request.contextPath}/controller.do?op=ConsultaLivrosComando"
			type="button" class="close" aria-label="Close"><span
			class="glyphicon glyphicon-step-backward"
			style="font-size: 30px; color: red;"></span></a>
		<h3>Novo livro</h3>
		<form action="${pageContext.request.contextPath}/controller.do"
			method="POST" class="form-horizontal">
			<input type="hidden" name="op" value="CadastraLivroComando">
			<input type="hidden" name="emprestimos" value="${livro.emprestimos}"> <input id="titulo" value="${livro.titulo}" name="titulo" type="text"
				class="form-control" placeholder="Título" disabled="disabled" /> <input
				id="autor" value="${livro.autor}" name="autor" type="text"
				class="form-control" placeholder="Autor" disabled="disabled" /> <input
				id="sinopse" value="${livro.sinopse}" name="sinopse"
				class="form-control" type="text" placeholder="Sinopse"
				style="height: 10%;" disabled="disabled" />
			<div style="text-align: left;">
				<input id="quantidade" value="${livro.quantidade}" name="quantidade"
					class="form-control" type="number" placeholder="Quantidade" min="1"
					maxlength="120" style="width: 30%;" disabled="disabled" />
			</div>
		</form>
	</div>
	<c:set var="endofconversation" value="yes" scope="request" />
</body>
</html>