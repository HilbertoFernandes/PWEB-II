package br.edu.ifpb.bibliotecaweb.comando;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.ifpb.bibliotecaweb.controlador.Resultado;
import br.edu.ifpb.bibliotecaweb.controlador.UsuarioController;
import br.edu.ifpb.bibliotecaweb.dao.PersistenceUtil;
import br.edu.ifpb.bibliotecaweb.entidade.Usuario;

public class PedidoCadastraComando implements IComando {

	@Override
	public Resultado execute(HttpServletRequest request, HttpServletResponse response) {

		final String paginaSucesso = "index.jsp";
		final String paginaErro = "usuario/solicita_cadastro.jsp";

		UsuarioController contatoCtrl = new UsuarioController(PersistenceUtil.getCurrentEntityManager());

		Resultado resultado = contatoCtrl.soliciteCadastro(request.getParameterMap());

		if (!resultado.isErro()) {
			resultado.setProximaPagina(paginaSucesso);
			request.setAttribute("usuario", (Usuario) resultado.getEntidade());
			request.getServletContext().setAttribute("_msg", resultado.getMensagens());
		} else {
			request.setAttribute("usuario", (Usuario) resultado.getEntidade());
			request.getServletContext().setAttribute("_msg", resultado.getMensagens());
			resultado.setProximaPagina(paginaErro);
		}
		return resultado;
	}
}
