package br.edu.ifpb.bibliotecaweb.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.edu.ifpb.bibliotecaweb.entity.Usuario;
import br.edu.ifpb.bibliotecaweb.facade.LoginController;

@ManagedBean(name = "loginBean")
@SessionScoped
public class LoginBean extends GenericBean {
	private String matricula;
	private String senha;
	private Usuario usuarioLogado;

	public String autenticar() {
		String proxView = null;
		LoginController controller = new LoginController();
		if ((usuarioLogado = controller.isValido(matricula, senha)) != null) {
			this.setValueOf("#{sessionScope.loginUser}", String.class, usuarioLogado.getNome());
			proxView = "/usuario/home?faces-redirect=true";
		} else {
			FacesMessage msg = new FacesMessage("Login inválido.");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}

		return proxView;
	}
	

	public String logout() {
		this.invalidateSession();
		return "/index?faces-redirect=true";
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

}
