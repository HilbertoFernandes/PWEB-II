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
<c:if test="${usuario_logado.perfil eq 'aluno'}">
	<c:redirect url="/login/logon.jsp" />
</c:if>
<body
	style="background-image: url('${pageContext.request.contextPath}/imagens/livros.jpg');">
	<c:import url="../template/header.jsp" />

	<div class="container"
		style="width: 30%; padding: 1%; margin-top: 5%; background-color: white; border-radius: 5px; padding: 1%; padding-bottom: 0px;">
		<a href="${pageContext.request.contextPath}/usuario/home.jsp"
			type="button" class="close" aria-label="Close"><span
			class="glyphicon glyphicon-remove-circle"
			style="font-size: 30px; color: red;"></span></a>
		<h3>Novo livro</h3>
		<form action="${pageContext.request.contextPath}/controller.do"
			method="POST" class="form-horizontal">
			<input type="hidden" name="op" value="CadastraLivroComando">
			<input type="hidden" name="emprestimos" value="${livro.emprestimos}">
			<input type="hidden" name="id" value="${livro.id}"> <input
				id="titulo" value="${livro.titulo}" name="titulo" type="text"
				class="form-control" placeholder="Título" /> <input id="autor"
				value="${livro.autor}" name="autor" type="text" class="form-control"
				placeholder="Autor" /> <input id="sinopse" value="${livro.sinopse}"
				name="sinopse" class="form-control" type="text"
				placeholder="Sinopse" style="height: 10%;" />
			<div style="text-align: left;">
				<input id="quantidade" value="${livro.quantidade}" name="quantidade"
					class="form-control" type="number" placeholder="Quantidade" min="1"
					maxlength="120" style="width: 30%;" />
			</div>
			<div style="text-align: right;">
				<input type="submit" class="form-control btn btn-primary"
					value="Salvar" style="width: 30%;">
			</div>
		</form>
		<c:if test="${not empty _msg}">
			<ul style="list-style-type: none">
				<c:forEach var="_m" items="${_msg}">
					<li style="color: red;">${_m}</li>
				</c:forEach>
			</ul>
		</c:if>
	</div>
	<c:set var="endofconversation" value="yes" scope="request" />
</body>
</html>