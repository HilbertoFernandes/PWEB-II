package br.edu.ifpb.bibliotecaweb.facade;

import br.edu.ifpb.bibliotecaweb.dao.UsuarioDAO;
import br.edu.ifpb.bibliotecaweb.entity.Usuario;
import br.edu.ifpb.bibliotecaweb.util.PasswordUtil;

public class LoginController {

public Usuario isValido(String matricula, String senha) {
		
		UsuarioDAO udao = new UsuarioDAO();
		Usuario user = udao.findByMatricula(matricula);
		if (user == null  
				|| !PasswordUtil.encryptMD5(senha).equals(user.getSenha())) {
			user = null;
		}
		return user;
	}
}
