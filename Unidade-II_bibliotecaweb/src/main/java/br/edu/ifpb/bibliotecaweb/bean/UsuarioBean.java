package br.edu.ifpb.bibliotecaweb.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.persistence.PersistenceException;

import br.edu.ifpb.bibliotecaweb.entity.Usuario;
import br.edu.ifpb.bibliotecaweb.facade.UsuarioController;
import br.edu.ifpb.bibliotecaweb.util.PasswordUtil;

@ManagedBean(name = "usuarioBean")
@ViewScoped
public class UsuarioBean extends GenericBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private Usuario usuario;
	private String matricula;
	private String nome;
	private String perfil;
	private String telefone;
	private Date nascimento;
	private String senha;
	private String nova_senha;
	private String confirmacao;
	private String email;
	private List<Usuario> usuarios;
	private Integer id = null;
	private boolean editando = false;

	@ManagedProperty("#{loginBean}")
	private LoginBean loginBean;
	private String argumento;

	public String salvar() {
		UsuarioController controller = new UsuarioController();
		String proxView = null;

		if (controller.buscarMatricula(this.matricula)) {
			this.addErrorMessage("Já existe usuário cadastrado com a matricula informada!.");
			return "cadastro?faces-redirect=true";
		}

		if (id != null) {
			usuario = controller.buscar(id);
			usuario.setNome(nome);
			usuario.setEmail(email);
			usuario.setFone(telefone);
			usuario.setPerfil(perfil);
			controller.atualizar(usuario);
			proxView = "usuarios?faces-redirect=true";
		} else {
			try {
				usuario = new Usuario();
				usuario.setMatricula(matricula);
				usuario.setNome(nome);
				usuario.setEmail(email);
				usuario.setPerfil(perfil);
				usuario.setFone(telefone);
				usuario.setDataAniversario(nascimento);
				usuario.setSenha(PasswordUtil.encryptMD5(senha));
				usuario.setStatus(true);
				controller.cadastrar(usuario);
				this.addSuccessMessage("Usuario salvo com sucesso");
				proxView = "usuarios?faces-redirect=true";
				usuario = new Usuario();
			} catch (PersistenceException e) {
				this.addErrorMessage("Erro ao tentar salvar o usuário.");
			}
		}
		return proxView;
	}

	public String mudarStatus(Usuario usuario) {

		String proxView = null;
		try {
			UsuarioController controller = new UsuarioController();
			usuario = controller.buscar(usuario.getId());
			usuario.setStatus(!usuario.getStatus());
			controller.atualizar(usuario);
			this.addSuccessMessage("Usuario atulizado com sucesso");
			proxView = "usuarios?faces-redirect=true";
			usuario = new Usuario();
		} catch (PersistenceException e) {
			this.addErrorMessage("Erro ao tentar atualizar o usuário.");
		}
		return proxView;
	}

	public String editar(Usuario usuario) {
		this.nome = usuario.getNome();
		this.email = usuario.getEmail();
		this.perfil = usuario.getPerfil();
		this.telefone = usuario.getFone();
		this.nascimento = usuario.getDataAniversario();
		this.id = usuario.getId();
		this.editando = true;
		return "cadastro?faces-redirect=true&includeViewParams=true";
	}

	public String excluir(Usuario usuario) {
		String proxima_pagina = null;
		if (usuario.getStatus() == true) {
			this.addSuccessMessage("Usuário ativo não pode ser excluído");
			proxima_pagina = "usuarios?faces-redirect=true";
		} else {
			UsuarioController controller = new UsuarioController();
			controller.excluir(usuario);
			this.addSuccessMessage("Usuário excluído com sucesso");
			proxima_pagina = "usuarios?faces-redirect=true";
		}
		return proxima_pagina;
	}

	public String alterarSenha() {
		UsuarioController controller = new UsuarioController();
		loginBean.getUsuarioLogado().setSenha(nova_senha);
		controller.atualizar(loginBean.getUsuarioLogado());
		this.addSuccessMessage("Senha alterada com sucesso");
		return "/usuario/alterar_senha?faces-redirect=true";
	}

	public String pesquisarUsuario() {
		UsuarioController controller = new UsuarioController();
		this.usuarios = controller.pesquisar(argumento);

		if (usuarios.isEmpty())
			this.addErrorMessage("Não há usuários para o argumento informado.");
		return "busca?faces-redirect=true&includeViewParams=true";
	}

	public String goCadastro() {
		return "/usuario/cadastro?faces-redirect=true";
	}

	public String goUsuarios() {
		return "/usuario/usuarios?faces-redirect=true";
	}

	public boolean temEmprestimos(Usuario usuario) {
		UsuarioController controller = new UsuarioController();
		return controller.temEmprestimos(usuario);
	}

	public boolean temEmprestimosAtivos(Usuario usuario) {
		UsuarioController controller = new UsuarioController();
		return controller.temEmprestimosAtivos(usuario);
	}

	public void listarUsuariosAtivos() {
		UsuarioController controller = new UsuarioController();
		this.usuarios = controller.listarUsuariosAtivos();
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Date getDataaniversario() {
		return nascimento;
	}

	public void setDataaniversario(Date dataaniversario) {
		this.nascimento = dataaniversario;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public void listarUsuarios() {
		UsuarioController controller = new UsuarioController();
		this.usuarios = controller.listar();
	}

	public Date getNascimento() {
		return nascimento;
	}

	public void setNascimento(Date nascimento) {
		this.nascimento = nascimento;
	}

	public String getConfirmacao() {
		return confirmacao;
	}

	public void setConfirmacao(String confirmacao) {
		this.confirmacao = confirmacao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public boolean isEditando() {
		return editando;
	}

	public void setEditando(boolean editando) {
		this.editando = editando;
	}

	public String getNova_senha() {
		return nova_senha;
	}

	public void setNova_senha(String nova_senha) {
		this.nova_senha = nova_senha;
	}

	public LoginBean getLoginBean() {
		return loginBean;
	}

	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}

	public String getArgumento() {
		return argumento;
	}

	public void setArgumento(String argumento) {
		this.argumento = argumento;
	}

}
