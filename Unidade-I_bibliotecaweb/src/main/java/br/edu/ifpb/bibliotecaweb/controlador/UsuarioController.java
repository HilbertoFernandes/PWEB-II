package br.edu.ifpb.bibliotecaweb.controlador;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import com.itextpdf.text.Chapter;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;

import br.edu.ifpb.bibliotecaweb.dao.UsuarioDAO;
import br.edu.ifpb.bibliotecaweb.entidade.Usuario;

public class UsuarioController {

	private List<String> mensagensErro;
	private EntityManager entityManager;

	public UsuarioController(EntityManager em) {
		this.entityManager = em;
	}

	public Resultado cadastre(Map<String, String[]> parametros) {
		Resultado resultado = new Resultado();
		Usuario usuario = null;

		if ((usuario = fromParametros(parametros)) != null) {
			UsuarioDAO dao = new UsuarioDAO(entityManager);
			dao.beginTransaction();
			usuario.setStatus(true);

			if (usuario.getId() == null) {
				dao.insert(usuario);
				resultado.setErro(false);
			} else
				dao.update(usuario);
			resultado.setErro(false);
			dao.commit();
		} else {
			resultado.setEntidade(usuario);
			resultado.setErro(true);
			resultado.setMensagens(this.mensagensErro);
		}
		return resultado;
	}

	public Resultado soliciteCadastro(Map<String, String[]> parametros) {

		Usuario usuario = null;
		Resultado resultado = new Resultado();

		if ((usuario = fromParametros(parametros)) != null) {
			UsuarioDAO dao = new UsuarioDAO(entityManager);
			dao.beginTransaction();
			usuario.setStatus(false);
			dao.insert(usuario);
			dao.commit();
			resultado.setErro(false);
			this.mensagensErro.add("Usuario cadastrado com sucesso, aguarde ativação");
			resultado.setMensagens(mensagensErro);
		} else {
			resultado.setErro(true);
			resultado.setMensagens(mensagensErro);
		}
		return resultado;
	}

	private Usuario fromParametrosEditando(Map<String, String[]> parametros) {

		String[] id = parametros.get("id");
		String[] email = parametros.get("email");
		String[] nome = parametros.get("nome");
		String[] perfil = parametros.get("perfil");
		String[] senha = parametros.get("senha");
		String[] fone = parametros.get("fone");
		String[] dataAniv = parametros.get("dataaniv");
		String[] matricula = parametros.get("matricula");

		this.mensagensErro = new ArrayList<String>();
		Usuario usuario = new Usuario();

		if (id != null && id.length > 0 && !id[0].isEmpty()) {
			usuario.setId(Integer.parseInt(id[0]));
		}

		usuario.setMatricula(matricula[0]);

		if (email == null || email.length == 0 || email[0].isEmpty()) {
			this.mensagensErro.add("Email é campo obrigatório");
		} else {
			usuario.setEmail(email[0]);
		}

		if (nome == null || nome.length == 0 || nome[0].isEmpty()) {
			this.mensagensErro.add("Nome é campo obrigatório");
		} else {
			usuario.setNome(nome[0]);
		}

		if (perfil == null || perfil.length == 0 || perfil[0].isEmpty()) {
			this.mensagensErro.add("Perfil é campo obrigatório");
		} else {
			usuario.setPerfil(perfil[0]);
		}

		if (senha == null || senha.length == 0 || senha[0].isEmpty()) {
			this.mensagensErro.add("Senha é campo obrigatório");
		} else {
			usuario.setSenha(senha[0]);
		}

		if (fone == null || fone.length == 0 || fone[0].isEmpty()) {
			this.mensagensErro.add("Telefone é campo obrigatório");
		} else {
			usuario.setFone(fone[0]);
		}

		if (dataAniv == null || dataAniv.length == 0 || dataAniv[0].isEmpty()) {
			this.mensagensErro.add("Data de aniversário é campo obrigatório");
		} else {
			if (dataAniv[0].matches("(19|20)\\d{2,2}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])")) {
				try {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					sdf.setLenient(false); // verifica datas inválidas ex: 30 de fevereiro
					Date dataIni = sdf.parse(dataAniv[0]);
					usuario.setDataAniversario(dataIni);
				} catch (ParseException e) {
					this.mensagensErro.add("Data inválido para a data de aniversário!");
				}
			} else {
				this.mensagensErro.add("Formato inválido para a data de aniversário (use dd/mm/aaaa)!");
			}
		}
		return this.mensagensErro.isEmpty() ? usuario : null;
	}

	private Usuario fromParametros(Map<String, String[]> parametros) {

		String[] id = parametros.get("id");
		String[] matricula = parametros.get("matricula");
		String[] email = parametros.get("email");
		String[] nome = parametros.get("nome");
		String[] perfil = parametros.get("perfil");
		String[] senha = parametros.get("senha");
		String[] fone = parametros.get("fone");
		String[] dataAniv = parametros.get("dataaniv");

		Usuario usuario = new Usuario();
		this.mensagensErro = new ArrayList<String>();

		if (id != null && id.length > 0 && !id[0].isEmpty()) {
			usuario.setId(Integer.parseInt(id[0]));
		}

		if (email == null || email.length == 0 || email[0].isEmpty()) {
			this.mensagensErro.add("Email é campo obrigatório");
		} else {
			usuario.setEmail(email[0]);
		}

		if (nome == null || nome.length == 0 || nome[0].isEmpty()) {
			this.mensagensErro.add("Nome é campo obrigatório");
		} else {
			usuario.setNome(nome[0]);
		}

		if (perfil == null || perfil.length == 0 || perfil[0].isEmpty()) {
			this.mensagensErro.add("Perfil é campo obrigatório");
		} else {
			usuario.setPerfil(perfil[0]);
		}

		if (matricula == null || matricula.length == 0 || matricula[0].isEmpty()) {
			this.mensagensErro.add("Matrícula é campo obrigatório");
		} else {
			usuario.setMatricula(matricula[0]);
		}

		if (matricula != null && matricula.length > 0 && !matricula[0].isEmpty()) {
			UsuarioDAO dao = new UsuarioDAO(entityManager);
			List<Usuario> usuarios = dao.findAll();
			boolean matricula_cadastrada = false;
			boolean email_cadastrado = false;
			for (Usuario usuario_m : usuarios) {

				if (usuario_m.getMatricula().equals(matricula[0])) {
					matricula_cadastrada = true;
				}
				if (usuario_m.getEmail().equals(email[0])) {
					email_cadastrado = true;
				}
			}
			if (matricula_cadastrada) {
				this.mensagensErro.add("A matrícula informada já é cadastrada na Biblioteca.");
			}
			if (email_cadastrado) {
				this.mensagensErro.add("E-mail informado já é utilizado por outro usuário.");
			}
		}

		if (senha == null || senha.length == 0 || senha[0].isEmpty()) {
			this.mensagensErro.add("Senha é campo obrigatório");
		} else {
			usuario.setSenha(senha[0]);
		}

		if (fone == null || fone.length == 0 || fone[0].isEmpty()) {
			this.mensagensErro.add("Telefone é campo obrigatório");
		} else {
			usuario.setFone(fone[0]);
		}

		if (dataAniv == null || dataAniv.length == 0 || dataAniv[0].isEmpty()) {
			this.mensagensErro.add("Data de aniversário é campo obrigatório");
		} else {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date hoje = new Date(System.currentTimeMillis());
			Date nascimento = null;
			try {
				nascimento = sdf.parse(dataAniv[0]);
			} catch (ParseException e1) {
				e1.printStackTrace();
			}

			if (hoje.before(nascimento)) {
				this.mensagensErro.add("A data de nascimento não pode ser posterior a data atual");
			}

			if (dataAniv[0].matches("(19|20)\\d{2,2}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])")) {
				try {
					sdf.setLenient(false); // verifica datas inválidas ex: 30 de fevereiro
					Date dataIni = sdf.parse(dataAniv[0]);
					usuario.setDataAniversario(dataIni);
				} catch (ParseException e) {
					this.mensagensErro.add("Data inválido para a data de aniversário!");
				}
			} else {
				this.mensagensErro.add("Formato inválido para a data de aniversário (use dd/mm/aaaa)!");
			}
		}
		return this.mensagensErro.isEmpty() ? usuario : null;
	}

	public List<Usuario> liste() {
		UsuarioDAO dao = new UsuarioDAO(entityManager);
		List<Usuario> usuarios = dao.findAll();
		return usuarios;
	}

	public List<Usuario> listeInativos() {
		UsuarioDAO dao = new UsuarioDAO(entityManager);
		List<Usuario> usuarios = dao.findInativos();
		return usuarios;
	}

	public List<Usuario> listeAtivos() {
		UsuarioDAO dao = new UsuarioDAO(entityManager);
		List<Usuario> usuarios = dao.findAtivos();
		return usuarios;
	}

	public Usuario busque(Map<String, String[]> parameterMap) {
		String[] id = parameterMap.get("id");
		UsuarioDAO dao = new UsuarioDAO(entityManager);
		Usuario usuario = dao.find(Integer.parseInt(id[0]));
		return usuario;
	}

	public void ative(Map<String, String[]> parameterMap) {
		String[] id = parameterMap.get("id");
		UsuarioDAO dao = new UsuarioDAO(entityManager);
		Usuario usuario = dao.find(Integer.parseInt(id[0]));
		dao.beginTransaction();
		usuario.setStatus(true);
		dao.update(usuario);
		dao.commit();
	}

	public void desative(Map<String, String[]> parameterMap) {
		String[] id = parameterMap.get("id");
		UsuarioDAO dao = new UsuarioDAO(entityManager);
		Usuario usuario = dao.find(Integer.parseInt(id[0]));
		dao.beginTransaction();
		usuario.setStatus(false);
		dao.update(usuario);
		dao.commit();

	}

	public Usuario busqueMatricula(Map<String, String[]> parameterMap) {
		String[] matricula = parameterMap.get("matricula");
		UsuarioDAO dao = new UsuarioDAO(entityManager);
		Usuario usuario = dao.findByMatricula(matricula[0]);
		return usuario;
	}

	public Resultado atualize(Map<String, String[]> parametros) {
		Resultado resultado = new Resultado();
		Usuario usuario = null;

		if ((usuario = fromParametrosEditando(parametros)) != null) {
			UsuarioDAO dao = new UsuarioDAO(entityManager);
			dao.beginTransaction();
			usuario.setStatus(true);
			dao.update(usuario);
			resultado.setErro(false);
			dao.commit();
		} else {
			resultado.setEntidade(usuario);
			resultado.setErro(true);
			resultado.setMensagens(this.mensagensErro);
		}
		return resultado;
	}

	@SuppressWarnings("unused")
	public Resultado alterarSenha(Map<String, String[]> parameterMap) {
		Usuario usuario = new Usuario();
		Resultado resultado = new Resultado();

		String[] p_senha = parameterMap.get("senha");
		String[] p_nova_senha = parameterMap.get("nova_senha");
		String[] P_confirma_senha = parameterMap.get("confirma_senha");
		String[] P_id_usuario = parameterMap.get("id_usuario");

		String senha = p_senha[0];
		String nova_senha = p_nova_senha[0];
		String confirma_senha = P_confirma_senha[0];

		UsuarioDAO dao = new UsuarioDAO(entityManager);
		usuario = dao.find(Integer.parseInt(P_id_usuario[0]));

		if ((senha.equals(usuario.getSenha())) && (nova_senha.equals(confirma_senha))) {
			usuario.setSenha(nova_senha);
			dao.beginTransaction();
			dao.update(usuario);
			resultado.addMensagem("Senha Alterada com Sucesso");
			resultado.setErro(false);
			dao.commit();
		} else {
			resultado.addMensagem("Dados inválidos");
			resultado.setErro(true);
		}
		return resultado;
	}

	public void exportarUsuarios(Map<String, String[]> parameterMap) {
		UsuarioDAO dao = new UsuarioDAO(entityManager);
		Document document = new Document();
		document.open();
		Font chapterFont = FontFactory.getFont(FontFactory.HELVETICA, 16, Font.BOLDITALIC);
		Font paragraphFont = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.NORMAL);

		List<Usuario> usuarios = dao.findAll();
		Chunk chunk = new Chunk("Dados informados :", chapterFont);
		for (Usuario usuario : usuarios) {
			String matricula = usuario.getMatricula();
			String nome = usuario.getNome();
			String email = usuario.getEmail();
			String telefone = usuario.getFone();
			String status;
			if (usuario.getStatus()) {
				status = "Ativo";
			} else {
				status = "Inativo";
			}
			Chapter chapter = new Chapter(new Paragraph(chunk), 1);
			chapter.setNumberDepth(0);
			chapter.add(new Paragraph("Matricula :" + matricula, paragraphFont));
			chapter.add(new Paragraph("Nome :" + nome, paragraphFont));
			chapter.add(new Paragraph("E-mail :" + email, paragraphFont));
			chapter.add(new Paragraph("Telefone :" + telefone, paragraphFont));
			chapter.add(new Paragraph("Status :" + status, paragraphFont));
			try {
				document.add(chapter);
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		document.close();
	}
}
