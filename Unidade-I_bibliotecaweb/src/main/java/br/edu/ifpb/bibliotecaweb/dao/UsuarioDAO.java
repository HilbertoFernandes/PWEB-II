package br.edu.ifpb.bibliotecaweb.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.edu.ifpb.bibliotecaweb.entidade.Usuario;

public class UsuarioDAO extends GenericDAO<Usuario, Integer> {

	public UsuarioDAO(EntityManager em) {
		super(em);
	}

	public Usuario findByMatricula(String matricula) {
		Query q = this.getEntityManager().createQuery("select u from Usuario u where u.matricula = :matricula");
		q.setParameter("matricula", matricula);
		Usuario u = null;
		try {
			u = (Usuario) q.getSingleResult();
		} catch (NoResultException e) {
		}
		return u;
	}

	public Usuario findMatricula(String matricula) {
		Query q = this.getEntityManager().createQuery("select u from Usuario u where u.matricula = :matricula");
		q.setParameter("matricula", matricula);
		Usuario u = null;
		try {
			u = (Usuario) q.getSingleResult();

		} catch (NoResultException e) {
		}
		return u;
	}

	public ArrayList<Usuario> findInativos() {
		Query q = this.getEntityManager().createQuery("select u from Usuario u where u.status = :status");
		q.setParameter("status", false);
		@SuppressWarnings("unchecked")
		ArrayList<Usuario> usuarios = (ArrayList<Usuario>) q.getResultList();
		return usuarios;
	}

	public List<Usuario> findAtivos() {
		Query q = this.getEntityManager().createQuery("select u from Usuario u where u.status = :status");
		q.setParameter("status", true);
		@SuppressWarnings("unchecked")
		ArrayList<Usuario> usuarios = (ArrayList<Usuario>) q.getResultList();
		return usuarios;
	}

}
