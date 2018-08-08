package br.edu.ifpb.bibliotecaweb.facade;

import java.util.List;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import br.edu.ifpb.bibliotecaweb.dao.EmprestimoDAO;
import br.edu.ifpb.bibliotecaweb.dao.PersistenceUtil;
import br.edu.ifpb.bibliotecaweb.entity.Emprestimo;
import br.edu.ifpb.bibliotecaweb.entity.Livro;
import br.edu.ifpb.bibliotecaweb.entity.Usuario;

public class EmprestimoController {

	public void cadastrar(Emprestimo Emprestimo) {
		EmprestimoDAO dao = new EmprestimoDAO(PersistenceUtil.getCurrentEntityManager());
		dao.beginTransaction();
		dao.insert(Emprestimo);
		dao.commit();
	}

	public void atualizar(Emprestimo Emprestimo) {
		EmprestimoDAO dao = new EmprestimoDAO(PersistenceUtil.getCurrentEntityManager());
		dao.beginTransaction();
		dao.update(Emprestimo);
		dao.commit();
	}

	public List<Emprestimo> consultar(Emprestimo Emprestimo) {
		EmprestimoDAO dao = new EmprestimoDAO(PersistenceUtil.getCurrentEntityManager());
		List<Emprestimo> contatos = dao.findAll();
		return contatos;
	}

	public boolean excluir(Emprestimo Emprestimo) {
		boolean excluiu = false;
		EmprestimoDAO dao = new EmprestimoDAO();
		try {
			dao.beginTransaction();
			Emprestimo c = dao.find(Emprestimo.getId());
			dao.delete(c);
			dao.commit();
			excluiu = true;
		} catch (PersistenceException e) {
			dao.rollback();
		}
		return excluiu;
	}

	public List<Emprestimo> listar() {
		EmprestimoDAO dao = new EmprestimoDAO();
		List<Emprestimo> Emprestimos = dao.findAll();
		return Emprestimos;
	}

	public boolean estaComLivro(Usuario usuario, Livro livro) {
		Query q = PersistenceUtil.getCurrentEntityManager().createQuery(
				"select e from Emprestimo e where e.usuario = :usuario and e.livro = :livro and e.status = true");
		q.setParameter("usuario", usuario);
		q.setParameter("livro", livro);
		if (q.getResultList().isEmpty()) {
			return false;
		} else {
			return true;
		}
	}

	public List<Emprestimo> emprestimosUsuarioLogado(Usuario usuarioLogado) {
		Query q = PersistenceUtil.getCurrentEntityManager()
				.createQuery("select e from Emprestimo e where e.usuario = :usuario");
		q.setParameter("usuario", usuarioLogado);
		@SuppressWarnings("unchecked")
		List<Emprestimo> emprestimos = q.getResultList();
		return emprestimos;
	}

	public int qntEmprestimos(Usuario usuario) {
		Query q = PersistenceUtil.getCurrentEntityManager()
				.createQuery("select e from Emprestimo e where e.usuario = :usuario and e.status = true");
		q.setParameter("usuario", usuario);
		int quantidade = q.getResultList().size();
		return quantidade;
	}
}
