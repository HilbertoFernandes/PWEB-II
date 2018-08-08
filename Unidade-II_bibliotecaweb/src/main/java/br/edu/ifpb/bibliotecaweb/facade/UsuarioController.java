package br.edu.ifpb.bibliotecaweb.facade;

import java.util.List;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import br.edu.ifpb.bibliotecaweb.dao.PersistenceUtil;
import br.edu.ifpb.bibliotecaweb.dao.UsuarioDAO;
import br.edu.ifpb.bibliotecaweb.entity.Usuario;

public class UsuarioController {

	public void cadastrar(Usuario usuario) {
		UsuarioDAO dao = new UsuarioDAO(PersistenceUtil.getCurrentEntityManager());
		dao.beginTransaction();
		dao.insert(usuario);
		dao.commit();
	}

	public List<Usuario> consultar(Usuario usuario) {
		UsuarioDAO dao = new UsuarioDAO(PersistenceUtil.getCurrentEntityManager());
		List<Usuario> contatos = dao.findAll();
		return contatos;
	}

	public boolean excluir(Usuario usuario) {
		boolean excluiu = false;
		UsuarioDAO dao = new UsuarioDAO();
		try {
			dao.beginTransaction();
			Usuario c = dao.find(usuario.getId());
			dao.delete(c);
			dao.commit();
			excluiu = true;
		} catch (PersistenceException e) {
			dao.rollback();
		}
		return excluiu;
	}

	public List<Usuario> listar() {
		UsuarioDAO dao = new UsuarioDAO();
		List<Usuario> usuarios = dao.findAll();
		return usuarios;
	}

	public Usuario buscar(int usuario_id) {
		UsuarioDAO dao = new UsuarioDAO(PersistenceUtil.getCurrentEntityManager());
		Usuario usuario_encontrado = dao.find(usuario_id);
		return usuario_encontrado;
	}

	public void atualizar(Usuario usuario) {
		UsuarioDAO dao = new UsuarioDAO(PersistenceUtil.getCurrentEntityManager());
		dao.beginTransaction();
		dao.update(usuario);
		dao.commit();
	}

	public List<Usuario> listarUsuariosAtivos() {
		Query q = PersistenceUtil.getCurrentEntityManager()
				.createQuery("select u from Usuario u where u.status = true");
		@SuppressWarnings("unchecked")
		List<Usuario> usuarios = q.getResultList();
		return usuarios;
	}

	public boolean temEmprestimosAtivos(Usuario usuario) {
		Query q = PersistenceUtil.getCurrentEntityManager()
				.createQuery("select e from Emprestimo e where e.usuario = :usuario and e.status = true");
		q.setParameter("usuario", usuario);
		if (q.getResultList().isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

	public boolean temEmprestimos(Usuario usuario) {
		Query q = PersistenceUtil.getCurrentEntityManager()
				.createQuery("select e from Emprestimo e where e.usuario = :usuario");
		q.setParameter("usuario", usuario);
		if (q.getResultList().isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

	public boolean buscarMatricula(String matricula) {
		Query q = PersistenceUtil.getCurrentEntityManager()
				.createQuery("select u from Usuario u where u.matricula = :matricula");
		q.setParameter("matricula", matricula);
		if (q.getResultList().isEmpty()) {
			return false;
		} else {
			return true;
		}
	}

	public List<Usuario> pesquisar(String argumento) {
		Query q = PersistenceUtil.getEntityManager().createQuery(
				"select u from Usuario u where upper(u.nome) LIKE :argumento OR u.matricula LIKE :argumento");
		argumento = argumento.toUpperCase();
		q.setParameter("argumento", "%" + argumento + "%");
		@SuppressWarnings("unchecked")
		List<Usuario> usuarios = (List<Usuario>) q.getResultList();
		return usuarios;
	}
}
