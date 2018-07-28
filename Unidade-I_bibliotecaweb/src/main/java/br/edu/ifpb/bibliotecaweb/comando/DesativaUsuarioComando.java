package br.edu.ifpb.bibliotecaweb.comando;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.ifpb.bibliotecaweb.controlador.Resultado;
import br.edu.ifpb.bibliotecaweb.controlador.UsuarioController;
import br.edu.ifpb.bibliotecaweb.dao.PersistenceUtil;

public class DesativaUsuarioComando implements IComando {

	@Override
	public Resultado execute(HttpServletRequest request, HttpServletResponse response) {

		UsuarioController usuarioCtrl = new UsuarioController(PersistenceUtil.getCurrentEntityManager());
		usuarioCtrl.desative(request.getParameterMap());
		Resultado resultado = new Resultado();
		resultado.setProximaPagina("controller.do?op=ConsultaUsuariosAtivosComando");
		return resultado;
	}

}
