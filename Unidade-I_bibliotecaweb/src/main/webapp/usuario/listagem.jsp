<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<title>Biblioteca - Usuarios</title>
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
		style="width: 99%; padding: 1%; margin-top: 5%; background-color: white; border-radius: 5px;">
		<a href="${pageContext.request.contextPath}/usuario/home.jsp"
			type="button" class="close" aria-label="Close"><span
			class="glyphicon glyphicon-remove-circle"
			style="font-size: 30px; color: red;"></span></a>
		<h2>Biblioteca</h2>

		<form action="${pageContext.request.contextPath}/controller.do"
			method="POST">
			<input type="hidden" name="op" value="ExcluiUsuarioComando">
			<table class="table">
				<tr align="left">
					<th>Matrícula</th>
					<th>Nome</th>
					<th>email</th>
					<th>Aniversário</th>
					<th>Telefone</th>
					<th>Perfil</th>
				</tr>
				<c:forEach var="usuario" items="${usuarios}">
					<c:if test="${usuario.id != usuario_logado.id}">
						<tr align="left">
							<td>${usuario.matricula}</td>
							<td>${usuario.nome}</td>
							<td>${usuario.email}</td>
							<fmt:formatDate var="dc" value="${usuario.dataAniversario}"
								pattern="dd/MM/yyyy" />
							<td>${dc}</td>
							<td>${usuario.fone}</td>
							<td>${usuario.perfil}</td>


							<c:if test="${usuario.status}">
								<td><a class="btn btn-danger btn-xs" style="width: 100%;"
									href="${pageContext.request.contextPath}/controller.do?op=DesativaUsuarioComando&id=${usuario.id}">Desativar</a></td>

								<td><a class="btn btn-info btn-xs" style="width: 100%;"
									href="${pageContext.request.contextPath}/controller.do?op=EditaUsuarioComando&id=${usuario.id}">Editar</a></td>
							</c:if>

							<c:if test="${not usuario.status}">
								<td><a class="btn btn-success btn-xs" style="width: 100%;"
									href="${pageContext.request.contextPath}/controller.do?op=AtivaUsuarioComando&id=${usuario.id}">Ativar</a></td>
							</c:if>
						</tr>
					</c:if>
				</c:forEach>
			</table>
		</form>
		<div style="text-align: right;">
			<a href="usuario/cadastro.jsp" class="form-control btn btn-primary"
				style="width: 10%;">Novo</a>
		</div>
		<div style="text-align: right;">
			<br> <a style="width: 10%;"
				href="${pageContext.request.contextPath}/exportarcontatos.do">Exportar
				lista de usuários</a>
		</div>
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