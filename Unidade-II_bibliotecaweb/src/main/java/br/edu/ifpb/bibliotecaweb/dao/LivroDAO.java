package br.edu.ifpb.bibliotecaweb.dao;

import javax.persistence.EntityManager;

import br.edu.ifpb.bibliotecaweb.entity.Livro;

public class LivroDAO extends GenericDAO<Livro, Integer> {
	
	public LivroDAO() {
		this(PersistenceUtil.getCurrentEntityManager());
	}

	public LivroDAO(EntityManager em) {
		super(em);
	}

	
}
