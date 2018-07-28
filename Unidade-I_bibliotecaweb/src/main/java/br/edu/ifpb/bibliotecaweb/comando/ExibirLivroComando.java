package br.edu.ifpb.bibliotecaweb.comando;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.ifpb.bibliotecaweb.controlador.LivroController;
import br.edu.ifpb.bibliotecaweb.controlador.Resultado;
import br.edu.ifpb.bibliotecaweb.dao.PersistenceUtil;
import br.edu.ifpb.bibliotecaweb.entidade.Livro;

public class ExibirLivroComando implements IComando {

	@Override
	public Resultado execute(HttpServletRequest request, HttpServletResponse response) {
		LivroController livroCtrl = new LivroController(PersistenceUtil.getCurrentEntityManager());

		Livro livro = livroCtrl.busqueLivro(request.getParameterMap());

		request.setAttribute("livro", livro);
		Resultado resultado = new Resultado();
		resultado.setProximaPagina("livro/livro.jsp");
		return resultado;
	}

}
