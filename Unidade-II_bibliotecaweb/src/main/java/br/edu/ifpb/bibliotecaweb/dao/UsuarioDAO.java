package br.edu.ifpb.bibliotecaweb.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.edu.ifpb.bibliotecaweb.entity.Usuario;

public class UsuarioDAO extends GenericDAO<Usuario, Integer> {
	
	public UsuarioDAO() {
		this(PersistenceUtil.getCurrentEntityManager());
	}

	public UsuarioDAO(EntityManager em) {
		super(em);
	}

	public Usuario findByMatricula(String matricula) {
		Query q = this.getEntityManager().createQuery("select u from Usuario u where u.matricula = :matricula");
		q.setParameter("matricula", matricula);
		Usuario u = null;
		try {
			u = (Usuario) q.getSingleResult();
		}catch (NoResultException e) {		
		}
		return u; 
	}
	
}
