package br.edu.ifpb.bibliotecaweb.facade;

import java.util.List;

import javax.persistence.PersistenceException;
import javax.persistence.Query;

import br.edu.ifpb.bibliotecaweb.dao.LivroDAO;
import br.edu.ifpb.bibliotecaweb.dao.PersistenceUtil;
import br.edu.ifpb.bibliotecaweb.entity.Livro;

public class LivroController {

	public void cadastrar(Livro livro) {
		LivroDAO dao = new LivroDAO(PersistenceUtil.getCurrentEntityManager());
		dao.beginTransaction();
		dao.insert(livro);
		dao.commit();
	}

	public void atualizar(Livro livro) {
		LivroDAO dao = new LivroDAO(PersistenceUtil.getCurrentEntityManager());
		dao.beginTransaction();
		dao.update(livro);
		dao.commit();
	}

	public Livro buscar(int livro_id) {
		LivroDAO dao = new LivroDAO(PersistenceUtil.getCurrentEntityManager());
		Livro livro_encontrado = dao.find(livro_id);
		return livro_encontrado;
	}

	public boolean excluir(Livro Livro) {
		boolean excluiu = false;
		LivroDAO dao = new LivroDAO();
		try {
			dao.beginTransaction();
			Livro c = dao.find(Livro.getId());
			dao.delete(c);
			dao.commit();
			excluiu = true;
		} catch (PersistenceException e) {
			dao.rollback();
		}
		return excluiu;
	}

	public List<Livro> listar() {
		LivroDAO dao = new LivroDAO();
		List<Livro> livros = dao.findAll();
		return livros;
	}

	public List<Livro> listarDisponiveis() {
		Query q = PersistenceUtil.getCurrentEntityManager().createQuery("select l from Livro l where l.quantidade > 0");
		@SuppressWarnings("unchecked")
		List<Livro> livros = q.getResultList();
		return livros;
	}

	public boolean temEmprestimos(Livro livro) {
		Query q = PersistenceUtil.getCurrentEntityManager()
				.createQuery("select e from Emprestimo e where e.livro = :livro");
		q.setParameter("livro", livro);
		if (q.getResultList().isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

	public List<Livro> pesquisar(String argumento) {
		Query q = PersistenceUtil.getEntityManager().createQuery(
				"select l from Livro l where upper(l.titulo) LIKE :argumento OR upper(l.autor) LIKE :argumento");
		argumento = argumento.toUpperCase();
		q.setParameter("argumento", "%" + argumento + "%");
		@SuppressWarnings("unchecked")
		List<Livro> livros = (List<Livro>) q.getResultList();
		return livros;
	}
}
