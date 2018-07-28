package br.edu.ifpb.bibliotecaweb.dao;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.edu.ifpb.bibliotecaweb.entidade.Livro;

public class LivroDAO extends GenericDAO<Livro, Integer> {

	public LivroDAO(EntityManager em) {
		super(em);
	}

	public ArrayList<Livro> findTitulo(String titulo) {
		Query q = this.getEntityManager().createQuery("select l from Livro l where upper(l.titulo) LIKE :titulo");
		titulo = titulo.toUpperCase();
		q.setParameter("titulo","%"+ titulo + "%");
		@SuppressWarnings("unchecked")
		ArrayList<Livro> livros = (ArrayList<Livro>) q.getResultList();
		return livros;
	}

	public ArrayList<Livro> findAutor(String autor) {
		Query q = this.getEntityManager().createQuery("select l from Livro l where upper(l.autor) LIKE :autor");
		autor = autor.toUpperCase();
		q.setParameter("autor", "%" + autor + "%");
		@SuppressWarnings("unchecked")
		ArrayList<Livro> livros = (ArrayList<Livro>) q.getResultList();
		return livros;
	}
}
