package br.edu.ifpb.bibliotecaweb.comando;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.ifpb.bibliotecaweb.controlador.LivroController;
import br.edu.ifpb.bibliotecaweb.controlador.Resultado;
import br.edu.ifpb.bibliotecaweb.dao.PersistenceUtil;
import br.edu.ifpb.bibliotecaweb.entidade.Livro;

public class ConsultaLivrosComando implements IComando {

	@Override
	public Resultado execute(HttpServletRequest request, HttpServletResponse response) {
		LivroController livrosCtrl = new LivroController(PersistenceUtil.getCurrentEntityManager());
		List<Livro> livros = livrosCtrl.liste();
		Resultado resultado = new Resultado();
		
		request.setAttribute("livros", livros);
		resultado.setProximaPagina("livro/listagem.jsp");
		return resultado;
	}

}
