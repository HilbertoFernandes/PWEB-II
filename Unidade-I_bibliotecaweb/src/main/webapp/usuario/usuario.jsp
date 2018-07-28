<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<c:if test="${usuario_logado.perfil eq 'aluno'}">
	<c:redirect url="/login/logon.jsp" />
</c:if>
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
		style="width: 40%; padding: 1%; margin-top: 5%; background-color: white; border-radius: 5px;">
		<a href="${pageContext.request.contextPath}/usuario/home.jsp"
			type="button" class="close" aria-label="Close"><span
			class="glyphicon glyphicon-remove-circle"
			style="font-size: 30px; color: red;"></span></a>
		<c:if test="${not empty usuario}">
			<h3>Usuário localizado</h3>
			<input id="matricula" value="${usuario.matricula}" name="matricula"
				type="text" class="form-control" placeholder="Matricula"
				disabled="disabled" />
			<input id="email" value="${usuario.email}" name="email" type="email"
				class="form-control" placeholder="Email" disabled="disabled" />
			<input id="nome" value="${usuario.nome}" name="nome"
				class="form-control" type="text" placeholder="Nome"
				disabled="disabled" />
			<input id="perfil" value="${usuario.perfil}" name="perfil"
				class="form-control" type="text" placeholder="perfil"
				disabled="disabled" />
			<fmt:formatDate var="dc" value="${usuario.dataAniversario}"
				pattern="dd/MM/yyyy" />
			<input id="dataaniv" value="${dc}" type="text" class="form-control"
				disabled="disabled" />
			<input id="fone" value="${usuario.fone}" name="fone"
				class="form-control" type="text" placeholder="Telefone"
				disabled="disabled" />
		</c:if>
		<c:if test="${empty usuario}">
			<h3>Matrícula não encontrada</h3>
		</c:if>
	</div>
	<c:set var="endofconversation" value="yes" scope="request" />
</body>
</html>