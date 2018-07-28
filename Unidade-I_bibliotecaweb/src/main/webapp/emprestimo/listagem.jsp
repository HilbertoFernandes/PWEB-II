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
	<c:if test="${not empty emprestimos}">
		<c:if test="${usuario_logado.perfil eq 'aluno'}">
			<div class="container"
				style="width: 99%; padding: 1%; margin-top: 5%; background-color: white; border-radius: 5px;">
				<a href="${pageContext.request.contextPath}/usuario/home.jsp"
					type="button" class="close" aria-label="Close"><span
					class="glyphicon glyphicon-remove-circle"></span></a>
				<table class="table">
					<tr align="left">
						<th>Livro</th>
						<th>Usuario</th>
						<th>Matrícula</th>
						<th>Inicio</th>
						<th>Fim</th>
					</tr>
					<c:forEach var="emprestimo" items="${emprestimos}">
						<c:if test="${usuario_logado.id == emprestimo.usuario.id}">
							<tr align="left">
								<td>${emprestimo.livro.titulo}</td>
								<td>${emprestimo.usuario.nome}</td>
								<td>${emprestimo.usuario.matricula}</td>
								<fmt:formatDate var="datainicio"
									value="${emprestimo.dataInicio}" pattern="dd/MM/yyyy" />
								<td>${datainicio}</td>
								<fmt:formatDate var="datafim" value="${emprestimo.dataFim}"
									pattern="dd/MM/yyyy" />
								<td>${datafim}</td>
								<c:if test="${emprestimo.status != true}">
									<td><a href="#"
										style="color: green; text-decoration: none;">Concluido</a></td>
								</c:if>
								<c:if
									test="${emprestimo.renovado == false and emprestimo.status == true}">
									<td><a
										href="${pageContext.request.contextPath}/controller.do?op=RenovarEmprestimoComando&id=${emprestimo.id}"
										style="color: orange;">Renovar</a></td>
								</c:if>
							</tr>
						</c:if>
					</c:forEach>
				</table>
			</div>
		</c:if>
	</c:if>
	<c:if
		test="${empty emprestimos and usuario_logado.perfil eq 'funcionario'}">
		<div class="container"
			style="width: 45%; padding: 1%; margin-top: 5%; background-color: white; border-radius: 5px; padding: 0px; text-align: center;">
			<a href="${pageContext.request.contextPath}/usuario/home.jsp"
				type="button" class="close" aria-label="Close"><span
				class="glyphicon glyphicon-remove-circle"></span></a>
			<h3>Não há empréstimos em aberto no momento</h3>
		</div>
	</c:if>
	<c:if
		test="${not empty emprestimos and usuario_logado.perfil eq 'funcionario'}">
		<div class="container"
			style="width: 99%; padding: 1%; margin-top: 5%; background-color: white; border-radius: 5px;">
			<a href="${pageContext.request.contextPath}/usuario/home.jsp"
				type="button" class="close" aria-label="Close"><span
				class="glyphicon glyphicon-remove-circle"
				style="font-size: 30px; color: red;"></span></a>
			<table class="table">
				<tr align="left">
					<th>Livro</th>
					<th>Usuario</th>
					<th>Matrícula</th>
					<th>Inicio</th>
					<th>Fim</th>
				</tr>
				<c:forEach var="emprestimo" items="${emprestimos}">
					<tr align="left">
						<td>${emprestimo.livro.titulo}</td>
						<td>${emprestimo.usuario.nome}</td>
						<td>${emprestimo.usuario.matricula}</td>
						<fmt:formatDate var="datainicio" value="${emprestimo.dataInicio}"
							pattern="dd/MM/yyyy" />
						<td>${datainicio}</td>
						<fmt:formatDate var="datafim" value="${emprestimo.dataFim}"
							pattern="dd/MM/yyyy" />
						<td>${datafim}</td>
						<c:if test="${emprestimo.status == true }">
							<td><a class="btn btn-success btn-xs" style="width: 100%;"
								href="${pageContext.request.contextPath}/controller.do?op=EmprestimoDevolucaoComando&id=${emprestimo.id}">Devolver</a></td>

							<c:if test="${emprestimo.renovado == false }">
								<td><a class="btn btn-info btn-xs" style="width: 100%;"
									href="${pageContext.request.contextPath}/controller.do?op=RenovarEmprestimoComando&id=${emprestimo.id}"
									style="color: orange;">Renovar</a></td>
							</c:if>
						</c:if>
						<c:if test="${not emprestimo.status}">
							<td><a class="btn btn-default btn-xs" style="width: 100%;">Devolvido</a>
						</c:if>
					</tr>
				</c:forEach>
			</table>
			<div style="text-align: right;">
				<a href="controller.do?op=NovoEmprestimoComando"
					class="form-control btn btn-primary" style="width: 10%;">Novo</a>
			</div>
			</td>
	</c:if>

	<c:set var="endofconversation" value="yes" scope="request" />

</body>
</html>