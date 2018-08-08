package br.edu.ifpb.bibliotecaweb.bean;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import br.edu.ifpb.bibliotecaweb.entity.Emprestimo;
import br.edu.ifpb.bibliotecaweb.entity.Livro;
import br.edu.ifpb.bibliotecaweb.entity.Usuario;
import br.edu.ifpb.bibliotecaweb.facade.EmprestimoController;
import br.edu.ifpb.bibliotecaweb.facade.LivroController;

@ManagedBean(name = "emprestimoBean")
@ViewScoped
public class EmprestimoBean extends GenericBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private Emprestimo emprestimo;
	private Usuario usuario;
	private Livro livro;
	private List<Emprestimo> emprestimos;
	@ManagedProperty("#{loginBean}")
	private LoginBean loginBean;

	public LoginBean getLoginBean() {
		return loginBean;
	}

	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}

	public String salvar() {
		LivroController controllerLivro = new LivroController();
		EmprestimoController controllerEmprestimo = new EmprestimoController();

		if (controllerEmprestimo.estaComLivro(usuario, livro)) {
			this.addErrorMessage("Usuário já está com o livro solicitado.");
			return "emprestimo/cadastro?faces-redirect=true";
		}

		if (controllerEmprestimo.qntEmprestimos(usuario) > 3) {
			this.addErrorMessage("Usuário já atingiu o número máximo de empréstimos ativos.");
			return "emprestimo/cadastro?faces-redirect=true";
		}

		try {
			Date termina = new Date();
			Date hoje = new Date(System.currentTimeMillis());
			Calendar c = Calendar.getInstance();
			c.setTime(hoje);
			c.add(Calendar.DATE, +7);
			termina = c.getTime();
			this.emprestimo = new Emprestimo();
			emprestimo.setUsuario(usuario);
			emprestimo.setLivro(livro);
			emprestimo.setDataInicio(hoje);
			emprestimo.setDataFim(termina);
			emprestimo.setRenovado(false);
			emprestimo.setStatus(true);
			livro.setEmprestimos(true);
			livro.setQuantidade(this.livro.getQuantidade() - 1);
			controllerLivro.atualizar(this.livro);
			controllerEmprestimo.cadastrar(emprestimo);
			usuario = new Usuario();
			return "emprestimos?faces-redirect=true";
		} catch (Exception e) {
			this.addErrorMessage("Não foi possível cadasrtar o empréstimo");
			return "emprestimos?faces-redirect=true";
		}
	}

	public String renovar(Emprestimo emprestimo) {
		this.emprestimo = emprestimo;
		EmprestimoController controllerEmprestimo = new EmprestimoController();
		Date termina = new Date();
		Date hoje = new Date(System.currentTimeMillis());
		Calendar c = Calendar.getInstance();
		c.setTime(hoje);
		c.add(Calendar.DATE, +10);
		termina = c.getTime();
		this.emprestimo.setDataFim(termina);
		this.emprestimo.setRenovado(true);
		controllerEmprestimo.atualizar(emprestimo);

		if (loginBean.getUsuarioLogado().getPerfil().equals("aluno"))
			return "home?faces-redirect=true";
		else
			return "emprestimos?faces-redirect=true";
	}

	public String devolver(Emprestimo emprestimo) {
		this.emprestimo = emprestimo;
		EmprestimoController controllerEmprestimo = new EmprestimoController();
		LivroController controllerLivro = new LivroController();
		this.livro = emprestimo.getLivro();
		this.livro.setQuantidade(this.livro.getQuantidade() + 1);
		if (controllerLivro.temEmprestimos(this.livro) == false)
			this.livro.setEmprestimos(false);
		controllerLivro.atualizar(livro);
		this.emprestimo.setStatus(false);

		controllerEmprestimo.atualizar(emprestimo);
		return "emprestimos?faces-redirect=true";
	}

	public String excluir(Emprestimo emprestimo) {
		EmprestimoController controller = new EmprestimoController();
		if (controller.excluir(emprestimo))
			this.addSuccessMessage("Empréstimo excluído com sucesso");
		else
			this.addErrorMessage("Não foi possível excluir o Empréstimo.");
		return "emprestimos?faces-redirect=true";
	}

	public void emprestimosUsuarioLogado() {
		EmprestimoController controller = new EmprestimoController();
		this.emprestimos = controller.emprestimosUsuarioLogado(loginBean.getUsuarioLogado());
	}

	public String goCadastro() {
		return "/emprestimo/cadastro?faces-redirect=true";
	}

	public String goEmprestimos() {
		return "/emprestimo/emprestimos?faces-redirect=true";
	}

	public void listaremprestimos() {
		EmprestimoController controller = new EmprestimoController();
		this.emprestimos = controller.listar();
	}

	public Emprestimo getEmprestimo() {
		return emprestimo;
	}

	public void setEmprestimo(Emprestimo emprestimo) {
		this.emprestimo = emprestimo;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	public List<Emprestimo> getEmprestimos() {
		return emprestimos;
	}

	public void setEmprestimos(List<Emprestimo> emprestimos) {
		this.emprestimos = emprestimos;
	}

}
