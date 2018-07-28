package br.edu.ifpb.bibliotecaweb.comando;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.ifpb.bibliotecaweb.controlador.Resultado;
import br.edu.ifpb.bibliotecaweb.controlador.UsuarioController;
import br.edu.ifpb.bibliotecaweb.dao.PersistenceUtil;

public class AlterarSenhaComando implements IComando {

	@Override
	public Resultado execute(HttpServletRequest request, HttpServletResponse response) {

		final String paginaSucesso = "usuario/alterar_senha.jsp";
		final String paginaErro = "usuario/alterar_senha.jsp";
		UsuarioController usuarioCtrl = new UsuarioController(PersistenceUtil.getCurrentEntityManager());
		Resultado resultado = usuarioCtrl.alterarSenha(request.getParameterMap());

		if (!resultado.isErro()) {
			request.setAttribute("_msg", resultado.getMensagens());
			resultado.setProximaPagina(paginaSucesso);
		} else {
			request.setAttribute("_msg", resultado.getMensagens());
			resultado.setProximaPagina(paginaErro);
		}
		return resultado;

	}

}
