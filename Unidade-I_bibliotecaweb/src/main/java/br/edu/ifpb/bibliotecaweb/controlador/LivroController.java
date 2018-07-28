package br.edu.ifpb.bibliotecaweb.controlador;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import br.edu.ifpb.bibliotecaweb.dao.EmprestimoDAO;
import br.edu.ifpb.bibliotecaweb.dao.LivroDAO;
import br.edu.ifpb.bibliotecaweb.entidade.Emprestimo;
import br.edu.ifpb.bibliotecaweb.entidade.Livro;

public class LivroController {

	private List<String> mensagensErro;
	private EntityManager entityManager;

	public LivroController(EntityManager em) {
		this.entityManager = em;
	}

	public Resultado cadastre(Map<String, String[]> parametros) {

		Resultado resultado = new Resultado();
		Livro livro = null;

		if ((livro = fromParametros(parametros)) != null) {
			LivroDAO dao = new LivroDAO(entityManager);
			dao.beginTransaction();

			if (livro.getId() == null) {
				dao.insert(livro);
				resultado.setErro(false);
			} else
				dao.update(livro);
			resultado.setErro(false);
			dao.commit();
		} else {
			resultado.setEntidade(livro);
			resultado.setErro(true);
			resultado.setMensagens(this.mensagensErro);
		}
		return resultado;
	}

	private Livro fromParametros(Map<String, String[]> parametros) {

		String[] id = parametros.get("id");
		String[] titulo = parametros.get("titulo");
		String[] autor = parametros.get("autor");
		String[] sinopse = parametros.get("sinopse");
		String[] quantidade = parametros.get("quantidade");
		String[] emprestimos = parametros.get("emprestimos");

		Livro livro = new Livro();
		livro.setEmprestimos(false);
		this.mensagensErro = new ArrayList<String>();

		livro.setEmprestimos(Boolean.valueOf(emprestimos[0]));

		if (id != null && id.length > 0 && !id[0].isEmpty()) {
			livro.setId(Integer.parseInt(id[0]));
		}

		if (titulo == null || titulo.length == 0 || titulo[0].isEmpty()) {
			this.mensagensErro.add("Título é campo obrigatório");
		} else {
			livro.setTitulo(titulo[0]);
		}

		if (autor == null || autor.length == 0 || autor[0].isEmpty()) {
			this.mensagensErro.add("Autor é campo obrigatório");
		} else {
			livro.setAutor(autor[0]);
		}

		if (sinopse == null || sinopse.length == 0 || sinopse[0].isEmpty()) {
			this.mensagensErro.add("Sinopse é campo obrigatório");
		} else {
			livro.setSinopse(sinopse[0]);
		}

		if (quantidade == null || quantidade.length == 0 || quantidade[0].isEmpty()) {
			this.mensagensErro.add("Quantidade é campo obrigatório");
		} else {
			livro.setQuantidade(Integer.parseInt(quantidade[0]));
		}

		return this.mensagensErro.isEmpty() ? livro : null;
	}

	public List<Livro> liste() {
		LivroDAO dao = new LivroDAO(entityManager);
		List<Livro> livros = dao.findAll();
		return livros;
	}

	public Livro busque(Map<String, String[]> parameterMap) {
		String[] id = parameterMap.get("id");
		LivroDAO dao = new LivroDAO(entityManager);
		Livro livro = dao.find(Integer.parseInt(id[0]));
		return livro;
	}

	public Resultado exclua(Map<String, String[]> parameterMap) {
		EmprestimoDAO edao = new EmprestimoDAO(entityManager);
		String id[] = parameterMap.get("id");
		LivroDAO dao = new LivroDAO(entityManager);
		Livro l = dao.find(Integer.parseInt(id[0]));
		List<Emprestimo> emprestimos = edao.findAll();
		edao.beginTransaction();
		for (Emprestimo emprestimo : emprestimos) {
			if (emprestimo.getLivro().equals(l)) {
				edao.delete(emprestimo);
			}
		}
		edao.commit();
		Resultado resultado = new Resultado();
		dao.beginTransaction();
		dao.delete(l);
		dao.commit();
		resultado.setMensagens(this.mensagensErro);
		return resultado;
	}

	public Resultado busqueTitulo(Map<String, String[]> parameterMap) {
		Resultado resultado = new Resultado();
		String[] titulo = parameterMap.get("titulo");
		LivroDAO dao = new LivroDAO(entityManager);
		ArrayList<Livro> livros = dao.findTitulo(titulo[0]);

		if (livros != null) {
			resultado.setEntidade(livros);
			resultado.setErro(false);
		} else {
			this.mensagensErro.add("Livro não encontrado.");
			resultado.addMensagens(mensagensErro);
			resultado.setErro(true);
		}
		return resultado;
	}

	public Resultado busqueAutor(Map<String, String[]> parameterMap) {
		Resultado resultado = new Resultado();
		String[] autor = parameterMap.get("autor");
		LivroDAO dao = new LivroDAO(entityManager);
		ArrayList<Livro> livros = dao.findAutor(autor[0]);

		if (livros != null) {
			resultado.setEntidade(livros);
			resultado.setErro(false);
		} else {
			this.mensagensErro.add("Livro não encontrado.");
			resultado.addMensagens(mensagensErro);
			resultado.setErro(true);
		}
		return resultado;
	}

	public Livro busqueLivro(Map<String, String[]> parameterMap) {
		String[] id = parameterMap.get("id");
		LivroDAO dao = new LivroDAO(entityManager);
		Livro livro = dao.find(Integer.parseInt(id[0]));
		return livro;
	}

}