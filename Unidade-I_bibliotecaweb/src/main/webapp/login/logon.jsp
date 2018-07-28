<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>Biblioteca - Login</title>
<link
	href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/login.css"
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

</head>

<body
	style="background-image: url('${pageContext.request.contextPath}/imagens/livros.jpg');">
	<div class="container">
		<div id="loginbox" style="margin-top: 40px;"
			class="mainbox col-md-4 col-md-offset-4 col-sm-8 col-sm-offset-2">

			<div class="panel-footer ">
				<h3 style="margin-top: 0px; margin-bottom: 0px;">Login
					Biblioteca</h3>
			</div>
			<div class="panel panel-info">
				

				<form class="form-signin"
					action="${pageContext.request.contextPath}/controller.do?op=LoginComando"
					method="POST">

					<label for="inputMatricula" class="sr-only">Usuário</label> <input
						type="text" name="matricula" id="matricula" class="form-control"
						placeholder="Matrícula" required autofocus
						value="${cookie['loginCookie'].value}" maxlength="6" onkeyup="somenteNumeros(this);"/> <label
						for="inputPassword" class="sr-only">Senha</label><br>
					<input type="password" id="senha" name="senha" class="form-control"
						placeholder="Senha" required maxlength="6"/>
					<div class="checkbox">
						<label> <input type="checkbox" value="sim" id="lembrar"
							name="lembrar"> Lembrar-me
						</label>
					</div>
					<button class="btn btn-lg btn-primary btn-block" type="submit">Entrar</button>
				</form>

				<c:set var="endofconversation" value="yes" scope="request" />

				<div class="panel-footer ">
					Não é cadastrado ? <a
						href="${pageContext.request.contextPath}/usuario/solicita_cadastro.jsp"
						onClick=""> Cadastre-se. </a>

					<c:if test="${not empty _msg}">
						<ul style="list-style-type: none">
							<c:forEach var="_m" items="${_msg}">
								<li style="color: red;">${_m}</li>
							</c:forEach>
						</ul>
					</c:if>
				</div>
			</div>
		</div>
	</div>
</body>
</html>