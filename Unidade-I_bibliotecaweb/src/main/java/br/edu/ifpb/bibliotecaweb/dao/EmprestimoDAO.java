package br.edu.ifpb.bibliotecaweb.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import br.edu.ifpb.bibliotecaweb.entidade.Emprestimo;

public class EmprestimoDAO extends GenericDAO<Emprestimo, Integer> {

	public EmprestimoDAO(EntityManager em) {
		super(em);
	}

	public ArrayList<Emprestimo> findInativos() {
		Query q = this.getEntityManager().createQuery("select e from Emprestimo e where e.status = :status");
		q.setParameter("status", false);
		@SuppressWarnings("unchecked")
		ArrayList<Emprestimo> emprestimos = (ArrayList<Emprestimo>) q.getResultList();
		return emprestimos;
	}

	public List<Emprestimo> findAtivos() {
		Query q = this.getEntityManager().createQuery("select e from Emprestimo e where e.status = :status");
		q.setParameter("status", true);
		@SuppressWarnings("unchecked")
		ArrayList<Emprestimo> emprestimos = (ArrayList<Emprestimo>) q.getResultList();
		return emprestimos;
	}

}
