<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<c:if test="${usuario_logado.perfil eq 'aluno'}">
	<c:redirect url="/login/logon.jsp" />
</c:if>
<title>Biblioteca - Consultar Aluno</title>
<link href="${pageContext.request.contextPath}/css/memoriam.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">

<script>
	function verificaSenha(nome) {
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
		style="width: 30%; padding: 1%; margin-top: 5%; background-color: white; border-radius: 5px;">
		<a href="${pageContext.request.contextPath}/usuario/home.jsp"
			type="button" class="close" aria-label="Close"><span
			class="glyphicon glyphicon-remove-circle"
			style="font-size: 30px; color: red;"></span></a>
		<h3>Alterar senha do usuário</h3>
		<form action="${pageContext.request.contextPath}/controller.do"
			method="POST" class="form-horizontal">
			<input type="hidden" name="op" value="AlterarSenhaComando"> <input
				type="hidden" name="id_usuario" value="${usuario_logado.id}">


			<input id="senha" value="" name="senha" class="form-control"
				type="password" placeholder="Senha atual" maxlength="6"
				required="required" onkeyup="verificaSenha(this);" /> <input
				id="nova_senha" value="" name="nova_senha" class="form-control"
				type="password" placeholder="Nova senha" maxlength="6"
				required="required" onkeyup="verificaSenha(this);" /> <input
				id="confirma_senha" value="" name="confirma_senha"
				class="form-control" type="password"
				placeholder="Confirme a senha desejada" maxlength="6"
				required="required" onkeyup="verificaSenha(this);" />
			<div style="text-align: right;">
				<input type="submit" class="form-control btn btn-primary"
					value="Alterar" style="width: 30%;">
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