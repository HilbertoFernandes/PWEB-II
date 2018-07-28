package br.edu.ifpb.bibliotecaweb.comando;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.ifpb.bibliotecaweb.controlador.Resultado;
import br.edu.ifpb.bibliotecaweb.controlador.UsuarioController;
import br.edu.ifpb.bibliotecaweb.dao.PersistenceUtil;
import br.edu.ifpb.bibliotecaweb.entidade.Usuario;

public class ConsultaUsuariosComando implements IComando {

	@Override
	public Resultado execute(HttpServletRequest request, HttpServletResponse response) {
		UsuarioController contatoCtrl = new UsuarioController(PersistenceUtil.getCurrentEntityManager());
		List<Usuario> usuarios = contatoCtrl.liste();
		Resultado resultado = new Resultado();
		request.setAttribute("usuarios", usuarios);
		resultado.setProximaPagina("usuario/listagem.jsp");
		return resultado;
	}

}
