package br.edu.ifpb.bibliotecaweb.entidade;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "TB_EMPRESTIMO")
public class Emprestimo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO")
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	private Usuario usuario;

	@JoinColumn(name = "ID_LIVRO", referencedColumnName = "ID_LIVRO")
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	private Livro livro;

	@Temporal(TemporalType.DATE)
	@Column(name = "DT_INICIO")
	private Date dataInicio;

	@Temporal(TemporalType.DATE)
	@Column(name = "DT_FIM")
	private Date dataFim;

	@Column(name = "STATUS")
	private Boolean status;

	@Column(name = "RENOVADO")
	private Boolean renovado;

	public Emprestimo() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Boolean getRenovado() {
		return renovado;
	}

	public void setRenovado(Boolean renovado) {
		this.renovado = renovado;
	}

}
