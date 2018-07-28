<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<title>Biblioteca - Novo Empréstimo</title>
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
		style="width: 30%; padding: 1%; margin-top: 5%; background-color: white; border-radius: 5px;">
		<a href="${pageContext.request.contextPath}/usuario/home.jsp"
			type="button" class="close" aria-label="Close"><span
			class="glyphicon glyphicon-remove-circle"
			style="font-size: 30px; color: red;"></span></a>
		<h3>Novo Empréstimo</h3>
		<form action="${pageContext.request.contextPath}/controller.do"
			method="POST" class="form-horizontal" name="cadastro">
			<input type="hidden" name="op" value="CadastraEmprestimoComando">
			<input type="hidden" name="id" value="${emprestimo.id}"> <select
				name="id_usuario" class="form-control">
				<option value="0" selected disabled>Selecione o usuário...</option>
				<c:forEach items="${usuarios}" var="usuario">
					<c:if test="${usuario.status}">
						<option value="${usuario.id}">${usuario.matricula}-
							${usuario.nome}</option>
					</c:if>
				</c:forEach>
			</select> <select name="id_livro" class="form-control">
				<option value="0" selected disabled>Selecione o livro...</option>
				<c:forEach items="${livros}" var="livro">
					<c:if test="${livro.quantidade > 0}">
						<option value="${livro.id}">${livro.titulo}</option>
					</c:if>
				</c:forEach>
			</select>

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