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


<c:if test="${empty sessionScope.usuario_logado}">
	<c:redirect url="/login/logon.jsp" />
</c:if>

<script>
	function somenteNumeros(num) {
		var er = /[^0-9.]/;
		er.lastIndex = 0;
		var telefone = num;
		if (er.test(telefone.value)) {
			telefone.value = "";
		}
	}
</script>

<script>
	function verificaNome(nome) {
		if (nome.value == " ") {
			nome.value = "";
		}
	}
</script>


</head>
<body
	style="background-image: url('${pageContext.request.contextPath}/imagens/livros.jpg');">
	<c:import url="../template/header.jsp" />

	<div class="container"
		style="width: 45%; padding: 1%; margin-top: 5%; background-color: white; border-radius: 5px;">
		<a href="${pageContext.request.contextPath}/usuario/home.jsp"
			type="button" class="close" aria-label="Close"><span
			class="glyphicon glyphicon-remove-circle"
			style="font-size: 30px; color: red;"></span></a>
		<h3>Dados do Usuário</h3>
		<form action="${pageContext.request.contextPath}/controller.do"
			method="POST" class="form-horizontal">
			<c:if test="${editando}">
				<input type="hidden" name="op" value="AtualizaUsuarioComando">
			</c:if>
			<c:if test="${not editando}">
				<input type="hidden" name="op" value="CadastraUsuarioComando">
			</c:if>
			<input type="hidden" name="id" value="${usuario.id}">

			<c:if test="${editando}">
				<input type="hidden" name="matricula" value="${usuario.matricula}">
			</c:if>
			<c:if test="${not editando}">
				<input id="matricula" value="${usuario.matricula}" name="matricula"
					type="text" class="form-control" placeholder="Matricula"
					required="required" maxlength="6" onkeyup="somenteNumeros(this);" />
			</c:if>
			
			<input id="email" value="${usuario.email}" name="email" type="email"
				class="form-control" placeholder="Email" required="required" /> <input
				id="nome" value="${usuario.nome}" name="nome" class="form-control"
				type="text" placeholder="Nome" required="required"
				onkeyup="verificaNome(this);" /> <select class="form-control"
				name="perfil" required="required">
				<option value="" selected disabled>Selecione o perfil...</option>
				<option value="aluno">Aluno</option>
				<option value="funcionario">Funcionário</option>
			</select> <input id="senha" value="${usuario.senha}" name="senha"
				class="form-control" type="password" placeholder="Senha"
				maxlength="6" required="required" />
			<fmt:formatDate var="dc" value="${usuario.dataAniversario}"
				pattern="dd/MM/yyyy" />

			<c:if test="${editando}">
				<input id="dataaniv" value="${usuario.dataAniversario}"
					name="dataaniv" type="date" class="form-control"
					placeholder="Data de criação (dd/mm/aaaa)" required="required" />
			</c:if>
			<c:if test="${not editando}">
				<input id="dataaniv" value="${dc}" name="dataaniv" type="date"
					class="form-control" placeholder="Data de criação (dd/mm/aaaa)"
					required="required" />
			</c:if>

			<input id="fone" value="${usuario.fone}" name="fone"
				class="form-control" type="text" placeholder="Telefone"
				maxlength="10" onkeyup="somenteNumeros(this);" required="required" />
			<div style="text-align: right;">
				<input type="submit" class="form-control btn btn-primary"
					value="Salvar" style="width: 15%;">
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