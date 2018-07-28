package br.edu.ifpb.bibliotecaweb.comando;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.ifpb.bibliotecaweb.controlador.LivroController;
import br.edu.ifpb.bibliotecaweb.controlador.Resultado;
import br.edu.ifpb.bibliotecaweb.dao.PersistenceUtil;

public class ExcluiLivroComando implements IComando {

	@Override
	public Resultado execute(HttpServletRequest request, HttpServletResponse response) {
		LivroController livroCtrl = new LivroController(PersistenceUtil.getCurrentEntityManager());
		Resultado resultado = livroCtrl.exclua(request.getParameterMap());

		request.setAttribute("_msg", resultado.getMensagens());
		resultado.setProximaPagina("controller.do?op=ConsultaLivrosComando");
		return resultado;
	}

}
