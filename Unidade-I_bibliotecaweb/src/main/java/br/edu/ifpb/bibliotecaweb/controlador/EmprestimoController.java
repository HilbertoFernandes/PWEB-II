package br.edu.ifpb.bibliotecaweb.controlador;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import br.edu.ifpb.bibliotecaweb.dao.EmprestimoDAO;
import br.edu.ifpb.bibliotecaweb.dao.LivroDAO;
import br.edu.ifpb.bibliotecaweb.dao.UsuarioDAO;
import br.edu.ifpb.bibliotecaweb.entidade.Emprestimo;
import br.edu.ifpb.bibliotecaweb.entidade.Livro;

public class EmprestimoController {

	private List<String> mensagensErro;
	private EntityManager entityManager;

	public EmprestimoController(EntityManager em) {
		this.entityManager = em;
	}

	public Resultado cadastre(Map<String, String[]> parametros) {
		Emprestimo emprestimo = null;
		Resultado resultado = new Resultado();

		if ((emprestimo = fromParametros(parametros)) != null) {

			String[] id_livro = parametros.get("id_livro");

			LivroDAO livrodao = new LivroDAO(entityManager);
			livrodao.beginTransaction();
			Livro livro = livrodao.find(Integer.parseInt(id_livro[0]));
			livro.setQuantidade(livro.getQuantidade() - 1);
			livro.setEmprestimos(true);
			livrodao.update(livro);
			livrodao.commit();
			emprestimo.setLivro(livrodao.find(Integer.parseInt(id_livro[0])));

			EmprestimoDAO dao = new EmprestimoDAO(entityManager);
			dao.beginTransaction();
			if (emprestimo.getId() == null) {
				emprestimo.setStatus(true);
				emprestimo.setRenovado(false);
				dao.insert(emprestimo);
			}
			dao.commit();
			resultado.setErro(false);
		} else {
			resultado.setEntidade(emprestimo);
			resultado.setErro(true);
			resultado.setMensagens(this.mensagensErro);
		}
		resultado.setEntidade(emprestimo);
		return resultado;
	}

	private Emprestimo fromParametros(Map<String, String[]> parametros) {

		String[] id = parametros.get("id");
		String[] id_usuario = parametros.get("id_usuario");
		String[] id_livro = parametros.get("id_livro");

		Emprestimo emprestimo = new Emprestimo();
		this.mensagensErro = new ArrayList<String>();
		UsuarioDAO usuariodao = new UsuarioDAO(entityManager);
		Date hoje = new Date(System.currentTimeMillis());
		emprestimo.setDataInicio(hoje);
		Date termina = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(hoje);
		c.add(Calendar.DATE, +7);
		termina = c.getTime();
		emprestimo.setDataFim(termina);

		if (id != null && id.length > 0 && !id[0].isEmpty()) {
			emprestimo.setId(Integer.parseInt(id[0]));
		}

		if (id_usuario == null || id_usuario.length == 0 || id_usuario[0].isEmpty()) {
			this.mensagensErro.add("Selecione o usuário.");
		} else {
			emprestimo.setUsuario(usuariodao.find(Integer.parseInt(id_usuario[0])));
		}

		if (id_livro == null || id_livro.length == 0 || id_livro[0].isEmpty()) {
			this.mensagensErro.add("Selecione o livro.");
		}

		EmprestimoDAO emprestimodao = new EmprestimoDAO(entityManager);
		ArrayList<Emprestimo> emprestimos = (ArrayList<Emprestimo>) emprestimodao.findAll();
		int qtd_emp_usuario = 0;
		for (Emprestimo emprestimo2 : emprestimos) {
			if (emprestimo2.getLivro().getId() == Integer.parseInt(id_livro[0])
					&& emprestimo2.getUsuario().getId() == Integer.parseInt(id_usuario[0])
					&& emprestimo2.getStatus() == true) {
				this.mensagensErro.add("Usuário já está com este livro.");
			}
		}

		for (Emprestimo emprestimo3 : emprestimos) {
			if (emprestimo3.getUsuario().getId() == Integer.parseInt(id_usuario[0])
					&& emprestimo3.getStatus() == true) {
				qtd_emp_usuario++;
			}
		}

		if (qtd_emp_usuario > 2) {
			this.mensagensErro.add("Usuário já tem 3 emrpréstimos em aberto.");
		}

		return this.mensagensErro.isEmpty() ? emprestimo : null;
	}

	public List<Emprestimo> liste() {
		EmprestimoDAO dao = new EmprestimoDAO(entityManager);
		List<Emprestimo> emprestimos = dao.findAll();
		return emprestimos;
	}

	public List<Emprestimo> listeInativos() {
		EmprestimoDAO dao = new EmprestimoDAO(entityManager);
		List<Emprestimo> emprestimos = dao.findInativos();
		return emprestimos;
	}

	public List<Emprestimo> listeAtivos() {
		EmprestimoDAO dao = new EmprestimoDAO(entityManager);
		List<Emprestimo> emprestimos = dao.findAtivos();
		return emprestimos;
	}

	@SuppressWarnings("unlikely-arg-type")
	public Resultado devolva(Map<String, String[]> parameterMap) {
		String id[] = parameterMap.get("id");
		EmprestimoDAO daoemprestimo = new EmprestimoDAO(entityManager);
		Emprestimo emprestimo = daoemprestimo.find(Integer.parseInt(id[0]));
		daoemprestimo.beginTransaction();
		emprestimo.setStatus(false);
		daoemprestimo.update(emprestimo);
		daoemprestimo.commit();

		LivroDAO daolivro = new LivroDAO(entityManager);
		daolivro.beginTransaction();
		Livro livro = emprestimo.getLivro();
		livro.setQuantidade(livro.getQuantidade() + 1);
		List<Emprestimo> livro_emprestimos = listeAtivos();
		if (!livro_emprestimos.contains(livro))
			livro.setEmprestimos(false);

		daolivro.update(livro);
		daolivro.commit();

		Resultado resultado = new Resultado();

		resultado.setErro(false);
		return resultado;
	}

	public Emprestimo busque(Map<String, String[]> parameterMap) {
		String[] id = parameterMap.get("id");
		EmprestimoDAO dao = new EmprestimoDAO(entityManager);
		Emprestimo emprestimo = dao.find(Integer.parseInt(id[0]));
		return emprestimo;
	}

	public Resultado renove(Map<String, String[]> parameterMap) {
		System.out.println("entrou no controller");
		Resultado resultado = new Resultado();
		String[] id = parameterMap.get("id");
		EmprestimoDAO dao = new EmprestimoDAO(entityManager);
		Emprestimo emprestimo = dao.find(Integer.parseInt(id[0]));
		dao.beginTransaction();

		Date hoje = new Date(System.currentTimeMillis());
		Date vencimento = emprestimo.getDataFim();

		if (vencimento.before(hoje) && emprestimo.getRenovado()) {
			resultado.setErro(true);
			this.mensagensErro.add("Empréstimo não pode ser renovado pois ainda não venceu ou já foi renovado antes.");
			resultado.setMensagens(mensagensErro);
		} else {
			resultado.setErro(false);
			Calendar c = Calendar.getInstance();
			c.setTime(hoje);
			c.add(Calendar.DATE, +7);
			vencimento = c.getTime();
			emprestimo.setRenovado(true);
			emprestimo.setDataFim(vencimento);
			dao.update(emprestimo);
			dao.commit();
			this.mensagensErro.add("Empréstimo renovado com sucesso.");
			resultado.setMensagens(mensagensErro);
		}
		return resultado;
	}
}
