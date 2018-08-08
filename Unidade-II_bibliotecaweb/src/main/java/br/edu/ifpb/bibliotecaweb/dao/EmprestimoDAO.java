package br.edu.ifpb.bibliotecaweb.dao;

import javax.persistence.EntityManager;
import br.edu.ifpb.bibliotecaweb.entity.Emprestimo;

public class EmprestimoDAO extends GenericDAO<Emprestimo, Integer> {

	public EmprestimoDAO() {
		this(PersistenceUtil.getCurrentEntityManager());
	}

	public EmprestimoDAO(EntityManager em) {
		super(em);
	}

}
