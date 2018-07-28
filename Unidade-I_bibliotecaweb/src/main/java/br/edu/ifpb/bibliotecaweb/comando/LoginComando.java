package br.edu.ifpb.bibliotecaweb.comando;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.edu.ifpb.bibliotecaweb.controlador.LoginController;
import br.edu.ifpb.bibliotecaweb.controlador.Resultado;
import br.edu.ifpb.bibliotecaweb.dao.PersistenceUtil;
import br.edu.ifpb.bibliotecaweb.entidade.Usuario;

public class LoginComando implements IComando {
	
	
	@Override
	public Resultado execute(HttpServletRequest request, HttpServletResponse response) {
		String paginaSucesso = "usuario/home.jsp";
		String paginaErro = "login/logon.jsp";
		HttpSession session = request.getSession();

		LoginController loginCtrl = new LoginController(PersistenceUtil.getCurrentEntityManager());
		Resultado resultado = loginCtrl.isValido(request.getParameterMap());
		Usuario usuarioLogado = (Usuario) resultado.getEntidade();
		if (resultado.isErro()) {
			request.getServletContext().setAttribute("_msg", resultado.getMensagens());
			resultado.setProximaPagina(paginaErro);
		} else {
			session.setAttribute("usuario_logado", usuarioLogado);
			resultado.setProximaPagina(paginaSucesso);

			// trata o lembrar
			String lembrar = request.getParameter("lembrar");
			if (lembrar != null) {
				Cookie c = new Cookie("loginCookie", usuarioLogado.getMatricula());
				c.setMaxAge(-1);
				response.addCookie(c);
			} else {
				for (Cookie cookie : request.getCookies()) {
					if (cookie.getName().equals("loginCookie")) {
						cookie.setValue(null);
						cookie.setMaxAge(0);
						response.addCookie(cookie);
					}
				}
			}
		}
		return resultado;
	}
}
