package br.edu.ifpb.bibliotecaweb.comando;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import br.edu.ifpb.bibliotecaweb.controlador.Resultado;
import br.edu.ifpb.bibliotecaweb.controlador.UsuarioController;
import br.edu.ifpb.bibliotecaweb.dao.PersistenceUtil;
import br.edu.ifpb.bibliotecaweb.entidade.Usuario;

public class AtualizaUsuarioComando implements IComando {

	@Override
	public Resultado execute(HttpServletRequest request, HttpServletResponse response) {

		final String paginaSucesso = "controller.do?op=ConsultaUsuariosComando";
		final String paginaErro = "usuario/cadastro.jsp";
		
		UsuarioController usuarioCtrl = new UsuarioController(PersistenceUtil.getCurrentEntityManager());
		Resultado resultado = usuarioCtrl.atualize(request.getParameterMap());

		if (!resultado.isErro()) {
			resultado.setProximaPagina(paginaSucesso);
			resultado.setRedirect(true);
		} else {
			request.setAttribute("usuario", (Usuario) resultado.getEntidade());
			request.setAttribute("_msg", resultado.getMensagens());
			resultado.setProximaPagina(paginaErro);
		}
		return resultado;

	}

}
