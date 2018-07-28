package br.edu.ifpb.bibliotecaweb.servlet;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import br.edu.ifpb.bibliotecaweb.controlador.UsuarioController;
import br.edu.ifpb.bibliotecaweb.dao.PersistenceUtil;
import br.edu.ifpb.bibliotecaweb.entidade.Usuario;

@WebServlet("/exportarcontatos.do")
public class ExportarContatos extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private List<Usuario> usuarios;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		UsuarioController usuarioCtrl = new UsuarioController(PersistenceUtil.getCurrentEntityManager());

		response.setContentType("application/pdf");
		usuarios = usuarioCtrl.liste();

		Document document = new Document();
		try {
			PdfWriter.getInstance(document, response.getOutputStream());

			document.open();
			Font paragraphFont = FontFactory.getFont(FontFactory.HELVETICA, 24, Font.BOLD);
			document.add(new Paragraph("Usuários cadastrados na BibliotecaWeb ", paragraphFont));
			paragraphFont = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.NORMAL);
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
				document.add(new Paragraph(" ", paragraphFont));
				document.add(new Paragraph("Matricula :" + matricula, paragraphFont));
				document.add(new Paragraph("Nome :" + nome, paragraphFont));
				document.add(new Paragraph("E-mail :" + email, paragraphFont));
				document.add(new Paragraph("Telefone :" + telefone, paragraphFont));
				document.add(new Paragraph("Status :" + status, paragraphFont));
			}
		} catch (DocumentException e1) {
			e1.printStackTrace();
		}
		document.close();
	}
}
