<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script
	src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>


<c:if test="${empty sessionScope.usuario_logado}">
	<c:redirect url="${pageContext.request.contextPath}/login/logon.jsp" />
</c:if>

<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="container">
		<div class="navbar-header">
			<a class="navbar-brand"
				href="${pageContext.request.contextPath}/usuario/home.jsp">Biblioteca</a>
		</div>
		<div id="navbar">

			<c:if test="${usuario_logado.perfil eq 'aluno'}">
				<a class="navbar-brand"
					href="${pageContext.request.contextPath}/controller.do?op=EmprestimoConsultaTodosComando">Meus
					Empréstimos</a>
				<ul class="nav navbar-nav">
					<li class="dropdown"><a class="dropdown-toggle"
						data-toggle="dropdown" href="#">Livro<span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a
								href="${pageContext.request.contextPath}/controller.do?op=ConsultaLivrosComando">Acervo</a></li>
							<li><a
								href="${pageContext.request.contextPath}/livro/busca_titulo.jsp">Buscar
									Por Título</a></li>
							<li><a
								href="${pageContext.request.contextPath}/livro/busca_autor.jsp">Buscar
									Por Autor</a></li>
						</ul></li>
				</ul>
			</c:if>

			<c:if test="${usuario_logado.perfil eq 'funcionario'}">
				<ul class="nav navbar-nav">
					<li class="dropdown"><a class="dropdown-toggle"
						data-toggle="dropdown" href="#">Empréstimo<span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a
								href="${pageContext.request.contextPath}/controller.do?op=NovoEmprestimoComando">Novo
									Empréstimo</a></li>
							<li><a
								href="${pageContext.request.contextPath}/controller.do?op=EmprestimosConsultaEmAndamentoComando">Em
									andamento</a></li>
							<li><a
								href="${pageContext.request.contextPath}/controller.do?op=EmprestimoConsultaDevolvidosComando">Concluidos</a></li>
							<li><a
								href="${pageContext.request.contextPath}/controller.do?op=EmprestimoConsultaTodosComando">Todos</a></li>
						</ul></li>
					<li class="dropdown"><a class="dropdown-toggle"
						data-toggle="dropdown" href="#">Livro<span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a
								href="${pageContext.request.contextPath}/livro/cadastro.jsp">Novo</a></li>
							<li><a
								href="${pageContext.request.contextPath}/controller.do?op=ConsultaLivrosComando">Acervo</a></li>
							<li><a
								href="${pageContext.request.contextPath}/livro/busca_titulo.jsp">Buscar
									Por Título</a></li>
							<li><a
								href="${pageContext.request.contextPath}/livro/busca_autor.jsp">Buscar
									Por Autor</a></li>
						</ul></li>
					<li class="dropdown"><a class="dropdown-toggle"
						data-toggle="dropdown" href="#">Usuario<span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a
								href="${pageContext.request.contextPath}/usuario/cadastro.jsp">Novo
									Cadastro</a></li>
							<li><a
								href="${pageContext.request.contextPath}/controller.do?op=ConsultaUsuariosComando">Usuarios
									Cadastrados</a></li>
							<li><a
								href="${pageContext.request.contextPath}/usuario/busca_matricula.jsp">Consultar
									Matricula</a></li>
						</ul></li>
				</ul>
			</c:if>
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false">${usuario_logado.nome}
						(${usuario_logado.perfil})<span class="caret"></span>
				</a>
					<ul class="dropdown-menu">
						<li><a
							href="${pageContext.request.contextPath}/controller.do?op=LogoutComando"
							id="link-submit"><i class="glyphicon glyphicon-log-out"></i>
								Sair</a></li>

						<li><a
							href="${pageContext.request.contextPath}/usuario/alterar_senha.jsp"><i
								class="glyphicon glyphicon-wrench"></i> Alterar senha</a></li>


					</ul></li>
			</ul>
		</div>
		<!--/.nav-collapse -->
	</div>
</nav>
<c:if test="${empty sessionScope.usuario_logado}">
	<c:redirect url="/login/logon.jsp" />
</c:if>
