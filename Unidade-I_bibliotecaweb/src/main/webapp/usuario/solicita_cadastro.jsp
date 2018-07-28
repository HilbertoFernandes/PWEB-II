<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<title>Biblioteca - Solicitação de Cadastro de Usuario</title>
<link href="${pageContext.request.contextPath}/css/memoriam.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">

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

	<div class="container"
		style="width: 45%; padding: 1%; margin-top: 5%; background-color: white; border-radius: 5px;">
		<a href="${pageContext.request.contextPath}/index.jsp" type="button" class="close" aria-label="Close"><span
			class="glyphicon glyphicon-remove-circle"
			style="font-size: 30px; color: red;"></span></a>
		<h3>Solicitação de cadastro</h3>
		<form action="${pageContext.request.contextPath}/controller.do"
			method="POST" class="form-horizontal">
			<input type="hidden" name="op" value="PedidoCadastraComando">
			<input type="hidden" name="id" value="${usuario.id}"> <input
				id="matricula" value="${usuario.matricula}" name="matricula"
				type="text" class="form-control" placeholder="Matricula"
				maxlength="6" onkeyup="somenteNumeros(this);" required="required" />
			<input id="email" value="${usuario.email}" name="email" type="email"
				class="form-control" placeholder="Email" required="required" /> <input
				id="nome" value="${usuario.nome}" name="nome" class="form-control"
				type="text" placeholder="Nome" onkeyup="verificaNome(this);"
				required="required" /> <select class="form-control" name="perfil"
				required="required">
				<option value="" selected disabled>Selecione o perfil...</option>
				<option value="aluno">Aluno</option>
				<option value="funcionario">Funcionário</option>
			</select> <input id="senha" value="${usuario.senha}" name="senha"
				class="form-control" type="password" placeholder="Senha"
				maxlength="6" required="required" />

			<fmt:formatDate var="dc" value="${usuario.dataAniversario}"
				pattern="dd/MM/yyyy" />
			<input id="dataaniv" value="${dc}" name="dataaniv"
				class="form-control" type="date"
				placeholder="Data de aniversário (dd/mm/aaaa)" required="required" />
			<input id="fone" value="${usuario.fone}" name="fone"
				class="form-control" type="text" placeholder="Telefone"
				maxlength="10" onkeyup="somenteNumeros(this);" required="required" />
			<input type="submit" class="form-control btn btn-primary"
				value="Solicitar Cadastro">
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