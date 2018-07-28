package br.edu.ifpb.bibliotecaweb.comando;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import br.edu.ifpb.bibliotecaweb.controlador.LivroController;
import br.edu.ifpb.bibliotecaweb.controlador.Resultado;
import br.edu.ifpb.bibliotecaweb.dao.PersistenceUtil;

public class ConsultaTituloComando implements IComando {

	@Override
	public Resultado execute(HttpServletRequest request, HttpServletResponse response) {
		final String paginaSucesso = "livro/livros.jsp";
		LivroController livroCtrl = new LivroController(PersistenceUtil.getCurrentEntityManager());
		Resultado resultado = livroCtrl.busqueTitulo(request.getParameterMap());
		request.setAttribute("livros", resultado.getEntidade());
		resultado.setProximaPagina(paginaSucesso);
		return resultado;
	}
}
