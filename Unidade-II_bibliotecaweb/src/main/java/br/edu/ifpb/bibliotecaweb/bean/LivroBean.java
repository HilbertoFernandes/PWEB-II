package br.edu.ifpb.bibliotecaweb.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.PersistenceException;

import br.edu.ifpb.bibliotecaweb.entity.Emprestimo;
import br.edu.ifpb.bibliotecaweb.entity.Livro;
import br.edu.ifpb.bibliotecaweb.facade.EmprestimoController;
import br.edu.ifpb.bibliotecaweb.facade.LivroController;

@ManagedBean(name = "livroBean")
@ViewScoped
public class LivroBean extends GenericBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<Livro> livros;
	private Livro livro;
	private String titulo;
	private String autor;
	private int quantidade;
	private String sinopse;
	private Integer id = null;
	private String argumento;

	public String salvar() {
		LivroController controller = new LivroController();
		if (id == null) {
			livro = new Livro();
		} else {
			livro = controller.buscar(id);
		}
		livro.setTitulo(titulo);
		livro.setAutor(autor);
		livro.setQuantidade(quantidade);
		livro.setSinopse(sinopse);
		livro.setEmprestimos(false);

		String proxima_pagina = null;
		try {

			controller.cadastrar(livro);
			this.addSuccessMessage("Livro salvo com sucesso");
			proxima_pagina = "livros?faces-redirect=true";
			livro = new Livro();
		} catch (PersistenceException e) {
			this.addErrorMessage("Erro ao tentar salvar o livro.");
		}
		return proxima_pagina;
	}

	public String excluir(Livro livro) {
		LivroController controller = new LivroController();
		if (controller.excluir(livro))
			this.addSuccessMessage("Livro excluído com sucesso");
		else
			this.addErrorMessage("Não foi possível excluir o livro..");
		return "livros?faces-redirect=true";
	}

	public String editar(Livro livro) {
		this.id = livro.getId();
		this.titulo = livro.getTitulo();
		this.autor = livro.getAutor();
		this.quantidade = livro.getQuantidade();
		this.sinopse = livro.getSinopse();
		return "cadastro?faces-redirect=true&includeViewParams=true";
	}

	public boolean jaFoiEmprestado(Livro livroE) {
		EmprestimoController controllerEmprestimo = new EmprestimoController();
		boolean tem_emprestimos = false;
		List<Emprestimo> emprestimos = controllerEmprestimo.listar();
		for (Emprestimo emprestimo : emprestimos) {
			if (emprestimo.getLivro().getId().equals(livroE.getId()))
				tem_emprestimos = true;
			else
				tem_emprestimos = false;
		}
		return tem_emprestimos;
	}

	public String goCadastro() {
		return "/livro/cadastro?faces-redirect=true";

	}

	public String goLivros() {
		return "/livro/livros?faces-redirect=true";
	}

	public List<Livro> getLivros() {
		return livros;
	}

	public void setLivros(List<Livro> livros) {
		this.livros = livros;
	}

	public void listarLivros() {
		LivroController controller = new LivroController();
		this.livros = controller.listar();
	}

	public String pesquisarLivro() {
		LivroController controller = new LivroController();
		this.livros = controller.pesquisar(argumento);
		
		if(livros.isEmpty())
			this.addErrorMessage("Não há resultados para o argumento informado.");
		
		return "busca?faces-redirect=true&includeViewParams=true";
	}

	public void listarLivrosDisponiveis() {
		LivroController controller = new LivroController();
		this.livros = controller.listarDisponiveis();
	}

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public String getSinopse() {
		return sinopse;
	}

	public void setSinopse(String sinopse) {
		this.sinopse = sinopse;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getArgumento() {
		return argumento;
	}

	public void setArgumento(String argumento) {
		this.argumento = argumento;
	}
}
