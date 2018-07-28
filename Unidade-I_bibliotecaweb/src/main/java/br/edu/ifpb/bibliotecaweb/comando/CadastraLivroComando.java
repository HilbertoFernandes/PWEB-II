package br.edu.ifpb.bibliotecaweb.comando;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.ifpb.bibliotecaweb.controlador.LivroController;
import br.edu.ifpb.bibliotecaweb.controlador.Resultado;
import br.edu.ifpb.bibliotecaweb.dao.PersistenceUtil;
import br.edu.ifpb.bibliotecaweb.entidade.Livro;

public class CadastraLivroComando implements IComando {

	@Override
	public Resultado execute(HttpServletRequest request, HttpServletResponse response) {

		final String paginaSucesso = "controller.do?op=ConsultaLivrosComando";
		final String paginaErro = "livro/cadastro.jsp";
		
		LivroController livroCtrl = new LivroController(PersistenceUtil.getCurrentEntityManager());
		Resultado resultado = livroCtrl.cadastre(request.getParameterMap());

		if (!resultado.isErro()) {
			resultado.setProximaPagina(paginaSucesso);
			resultado.setRedirect(true);
		} else {
			request.setAttribute("livro", (Livro) resultado.getEntidade());
			request.setAttribute("_msg", resultado.getMensagens());
			resultado.setProximaPagina(paginaErro);
		}
		return resultado;

	}

}
