package br.edu.ifpb.bibliotecaweb.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.edu.ifpb.bibliotecaweb.dao.LivroDAO;
import br.edu.ifpb.bibliotecaweb.dao.ManagedEMContext;
import br.edu.ifpb.bibliotecaweb.dao.PersistenceUtil;
import br.edu.ifpb.bibliotecaweb.dao.UsuarioDAO;
import br.edu.ifpb.bibliotecaweb.entidade.Livro;
import br.edu.ifpb.bibliotecaweb.entidade.Usuario;

public class InsereDadosParaTeste {
	private static EntityManagerFactory emf;
	private EntityManager em;

	@BeforeClass
	public static void init() {
		PersistenceUtil.createEntityManagerFactory("bibliotecaweb");
		emf = PersistenceUtil.getEntityManagerFactory();
		ManagedEMContext.bind(emf, emf.createEntityManager());
		System.out.println("init()");
	}

	@AfterClass
	public static void destroy() {
		if (emf != null) {
			emf.close();
		}
	}

	@Before
	public void initEM() {
		em = emf.createEntityManager();
	}

	@Test
	public void testInsereUsuarios() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		Usuario usuario1 = new Usuario();
		usuario1.setMatricula("123456");
		usuario1.setNome("Hilberto Fernandes");
		usuario1.setEmail("hilberto@ifpb.edu.br");
		usuario1.setSenha("123456");
		usuario1.setFone("83996193926");
		Date aniversario1 = sdf.parse("1981-06-15");
		usuario1.setDataAniversario(aniversario1);
		usuario1.setStatus(true);
		usuario1.setPerfil("funcionario");

		Usuario usuario2 = new Usuario();
		usuario2.setMatricula("246810");
		usuario2.setNome("Rayssa Freitas");
		usuario2.setEmail("rayssa@ifpb.edu.br");
		usuario2.setSenha("123456");
		usuario2.setFone("83988158791");
		Date aniversario2 = sdf.parse("2000-08-29");
		usuario2.setDataAniversario(aniversario2);
		usuario2.setStatus(false);
		usuario2.setPerfil("aluno");

		Usuario usuario3 = new Usuario();
		usuario3.setMatricula("104670");
		usuario3.setNome("José Calado");
		usuario3.setEmail("calado@ifpb.edu.br");
		usuario3.setSenha("123456");
		usuario3.setFone("8388898107");
		Date aniversario3 = sdf.parse("2002-05-14");
		usuario3.setDataAniversario(aniversario3);
		usuario3.setStatus(false);
		usuario3.setPerfil("aluno");

		Usuario usuario4 = new Usuario();
		usuario4.setMatricula("987654");
		usuario4.setNome("Paulo Henrique");
		usuario4.setEmail("ph@ifpb.edu.br");
		usuario4.setSenha("123456");
		usuario4.setFone("83996053678");
		Date aniversario4 = sdf.parse("1999-05-14");
		usuario4.setDataAniversario(aniversario4);
		usuario4.setStatus(true);
		usuario4.setPerfil("funcionario");
		
		Usuario usuario5 = new Usuario();
		usuario5.setMatricula("975632");
		usuario5.setNome("Heremita Brasileiro");
		usuario5.setEmail("heremitabrasileiro@ifpb.edu.br");
		usuario5.setSenha("123456");
		usuario5.setFone("83973213456");
		Date aniversario5 = sdf.parse("1985-01-14");
		usuario5.setDataAniversario(aniversario5);
		usuario5.setStatus(true);
		usuario5.setPerfil("funcionario");

		UsuarioDAO udao = new UsuarioDAO(em);
		udao.beginTransaction();
		udao.insert(usuario1);
		udao.insert(usuario2);
		udao.insert(usuario3);
		udao.insert(usuario4);
		udao.insert(usuario5);
		udao.commit();

	}

	@Test
	public void testInsereLivros() {
		Livro livro1 = new Livro();
		livro1.setTitulo("Grande Sertão Veredas");
		livro1.setAutor("Guimarães Rosa");
		livro1.setQuantidade(2);
		livro1.setSinopse("Grande Sertão: Veredas é um livro de João Guimarães Rosa escrito em 1956");

		Livro livro2 = new Livro();
		livro2.setTitulo("Macunaíma");
		livro2.setAutor("Mário de Andrade");
		livro2.setQuantidade(4);
		livro2.setSinopse(
				"Macunaíma, o herói sem nenhum caráter é um livro de 1928 do escritor brasileiro Mário de Andrade");

		Livro livro3 = new Livro();
		livro3.setTitulo("O Guarani");
		livro3.setAutor("José de Alencar");
		livro3.setQuantidade(6);
		livro3.setSinopse("O Guarani é um romance escrito por José de Alencar, desenvolvido em princípio em folhetim");

		Livro livro4 = new Livro();
		livro4.setTitulo("Memórias Póstumas de Brás Cubas");
		livro4.setAutor("Machado de Assis");
		livro4.setQuantidade(8);
		livro4.setSinopse(
				"romance escrito por Machado de Assis, desenvolvido em princípio como folhetim, de março a dezembro de 1880");

		LivroDAO udao = new LivroDAO(em);
		udao.beginTransaction();
		udao.insert(livro1);
		udao.insert(livro2);
		udao.insert(livro3);
		udao.insert(livro4);
		udao.commit();

	}

}
