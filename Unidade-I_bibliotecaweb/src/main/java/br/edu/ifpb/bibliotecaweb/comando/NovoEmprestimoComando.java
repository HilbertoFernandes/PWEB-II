package br.edu.ifpb.bibliotecaweb.comando;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.ifpb.bibliotecaweb.controlador.LivroController;
import br.edu.ifpb.bibliotecaweb.controlador.Resultado;
import br.edu.ifpb.bibliotecaweb.controlador.UsuarioController;
import br.edu.ifpb.bibliotecaweb.dao.PersistenceUtil;
import br.edu.ifpb.bibliotecaweb.entidade.Livro;
import br.edu.ifpb.bibliotecaweb.entidade.Usuario;

public class NovoEmprestimoComando implements IComando {

	@Override
	public Resultado execute(HttpServletRequest request, HttpServletResponse response) {

		LivroController livroCtrl = new LivroController(PersistenceUtil.getCurrentEntityManager());
		UsuarioController usuarioCtrl = new UsuarioController(PersistenceUtil.getCurrentEntityManager());
		
		List<Usuario> usuarios = usuarioCtrl.liste();
		List<Livro> livros = livroCtrl.liste();

		Resultado resultado = new Resultado();
		request.setAttribute("usuarios", usuarios);
		request.setAttribute("livros", livros);
		resultado.setProximaPagina("emprestimo/cadastro.jsp");
		return resultado;
	}

}
