package br.edu.ifpb.bibliotecaweb.controlador;

import java.util.ArrayList;
import java.util.List;

public class Resultado {
	private Object entidade;
	private boolean erro; 
	private List<String> mensagens;
	private String proximaPagina;
	private boolean redirect = false;
	
	public Resultado() {
		this.mensagens = new ArrayList<String>();
	}

	public void addMensagem(String m) {
		this.mensagens.add(m);
	}

	public boolean isErro() {
		return erro;
	}

	public void setErro(boolean erro) {
		this.erro = erro; 
	}

	public Object getEntidade() {
		return entidade;
	}

	public void setEntidade(Object entidade) {
		this.entidade = entidade;
	}

	public List<String> getMensagens() {
		return mensagens;
	}

	public void setMensagens(List<String> mensagens) {
		this.mensagens = mensagens;
	}

	public void addMensagens(List<String> mensagensErro) {
		this.mensagens.addAll(mensagens);
		
	}

	public String getProximaPagina() {
		return proximaPagina;
	}

	public void setProximaPagina(String proximaPagina) {
		this.proximaPagina = proximaPagina;
	}

	public void setRedirect(boolean b) {
		this.redirect = b;
	}

	public boolean isRedirect() {
		return redirect;
	}

}
