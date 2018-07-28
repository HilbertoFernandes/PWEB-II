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

	<div class="container"
		style="width: 99%; padding: 1%; margin-top: 5%; background-color: white; border-radius: 5px;">
		<a href="${pageContext.request.contextPath}/usuario/home.jsp"
			type="button" class="close" aria-label="Close"><span
			class="glyphicon glyphicon-remove-circle"
			style="font-size: 30px; color: red;"></span></a>
		<form action="${pageContext.request.contextPath}/controller.do"
			method="POST">
			<input type="hidden" name="op" value="ExcluiLivroComando">
			<table class="table">
				<thead>
					<tr align="left">
						<th>Titulo</th>
						<th>Autor</th>
						<th style="text-align: center;">Quantidade</th>

					</tr>
				</thead>
				<c:forEach var="livro" items="${livros}">
					<tr align="left">
						<td><a href="${pageContext.request.contextPath}/controller.do?op=ExibirLivroComando&id=${livro.id}"> ${livro.titulo}</a></td>
						<td>${livro.autor}</td>
							<td style="text-align: center;">${livro.quantidade}</td>
						<c:if test="${usuario_logado.perfil eq 'funcionario'}">
							<td><a class="btn btn-info btn-xs" style="width: 100px;"
								href="${pageContext.request.contextPath}/controller.do?op=EditaLivroComando&id=${livro.id}">Editar</a></td>
							<c:if test="${not livro.emprestimos}">
								<td><a class="btn btn-danger btn-xs" style="width: 100px;"
									onclick="return confirm('Excluindo o livro os empréstimos dele tabém serão excluídos, confirma ?')"
									href="${pageContext.request.contextPath}/controller.do?op=ExcluiLivroComando&id=${livro.id}">Excluir</a></td>
							</c:if>
							<c:if test="${livro.emprestimos}">
								<td><a style="color: green; text-decoration: none;">Empréstimos
										Ativos</a></td>
							</c:if>
						</c:if>
					</tr>
				</c:forEach>
				</tbody>
			</table>
		</form>
		<c:if test="${usuario_logado.perfil eq 'funcionario'}">
			<div style="text-align: right;">
				<a href="livro/cadastro.jsp" class="form-control btn btn-primary"
					style="width: 10%;">Novo</a>
			</div>
		</c:if>
		<c:set var="endofconversation" value="yes" scope="request" />
	</div>
</body>
</html>