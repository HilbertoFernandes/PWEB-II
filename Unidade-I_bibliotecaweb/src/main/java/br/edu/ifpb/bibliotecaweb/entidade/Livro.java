package br.edu.ifpb.bibliotecaweb.entidade;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TB_LIVRO")
public class Livro implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID_LIVRO")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "TITULO")
	private String titulo;

	@Column(name = "AUTOR")
	private String autor;

	@Column(name = "SINOPSE")
	private String sinopse;

	@Column(name = "QUANTIDADE")
	private int quantidade;

	@Column(name = "EMPRESTIMOS")
	private Boolean emprestimos;

	public Livro() {
	}

	public Livro(Integer id, String titulo, String autor, String sinopse, int quantidade) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.autor = autor;
		this.sinopse = sinopse;
		this.quantidade = quantidade;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getSinopse() {
		return sinopse;
	}

	public void setSinopse(String sinopse) {
		this.sinopse = sinopse;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public Boolean getEmprestimos() {
		return emprestimos;
	}

	public void setEmprestimos(Boolean emprestimos) {
		this.emprestimos = emprestimos;
	}
}
