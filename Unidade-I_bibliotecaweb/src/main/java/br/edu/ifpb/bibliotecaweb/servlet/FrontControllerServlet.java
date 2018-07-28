package br.edu.ifpb.bibliotecaweb.servlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import br.edu.ifpb.bibliotecaweb.comando.IComando;
import br.edu.ifpb.bibliotecaweb.controlador.Resultado;

@WebServlet("/controller.do")
public class FrontControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String NOME_PACOTE = "br.edu.ifpb.bibliotecaweb.comando.";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doRequest(request, response);
	}

	protected void doRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Limpa as mensagens entre pÃ¡ginas
		request.getServletContext().removeAttribute("_msg");

		String operacao = request.getParameter("op");
		// Descobre a classe de comando a ser executada.
		if (operacao == null) {
			this.getServletContext().setAttribute("msgs", "Operação não especificada na requisição!");
			response.sendRedirect(request.getHeader("Referer"));
			return;
		}

		Resultado resultado = null;
		String nomeClasseComando = operacao;
		try {
			Class<?> clazzComando = Class.forName(NOME_PACOTE + nomeClasseComando);
			IComando comando = (IComando) clazzComando.newInstance();
			resultado = comando.execute(request, response);

		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			this.getServletContext().setAttribute("_msg", "Comando "+operacao+" inexistente!");
			response.sendRedirect(request.getHeader("Referer"));
			return;
		} catch(Exception e) {
			this.getServletContext().setAttribute("_msg","Erro inesperado!");
			response.sendRedirect(request.getHeader("Referer"));
			return;
		}

		if (resultado.isErro()) {
			RequestDispatcher dispatcher = request.getRequestDispatcher(resultado.getProximaPagina());
			dispatcher.forward(request, response);			
		} else {
			if (resultado.isRedirect()) {
				response.sendRedirect(resultado.getProximaPagina());
			} else {
				request.getRequestDispatcher(resultado.getProximaPagina()).forward(request, response);
			}
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doRequest(request, response);
	}
	
}
