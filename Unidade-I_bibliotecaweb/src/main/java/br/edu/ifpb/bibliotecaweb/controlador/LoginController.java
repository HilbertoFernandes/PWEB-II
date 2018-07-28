package br.edu.ifpb.bibliotecaweb.controlador;

import java.util.Map;

import javax.persistence.EntityManager;

import br.edu.ifpb.bibliotecaweb.dao.UsuarioDAO;
import br.edu.ifpb.bibliotecaweb.entidade.Usuario;

public class LoginController {
	private EntityManager entityManager;

	public LoginController(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}

	public Resultado isValido(Map<String, String[]> parametros) {
		Resultado r = new Resultado();
		r.setErro(false);

		String matricula = parametros.get("matricula")[0];
		String passwd = parametros.get("senha")[0];

		UsuarioDAO udao = new UsuarioDAO(entityManager);
		Usuario user = udao.findByMatricula(matricula);
		if (user != null) {

			if (user.getStatus() == false) {
				r.setErro(true);
				r.addMensagem("Cadastro ainda não foi ativado.");
			}

			if (user.getSenha().equals(passwd)) {
				r.setEntidade(user);
			} else {
				r.setErro(true);
				r.addMensagem("Login ou senha inválido(s).");
			}
		} else {
			r.setErro(true);
			r.addMensagem("Login ou senha inválido(s).");

		}

		return r;
	}
}
