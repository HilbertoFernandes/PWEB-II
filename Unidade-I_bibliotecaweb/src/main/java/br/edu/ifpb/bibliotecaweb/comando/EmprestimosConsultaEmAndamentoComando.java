package br.edu.ifpb.bibliotecaweb.comando;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.ifpb.bibliotecaweb.controlador.EmprestimoController;
import br.edu.ifpb.bibliotecaweb.controlador.Resultado;
import br.edu.ifpb.bibliotecaweb.dao.PersistenceUtil;
import br.edu.ifpb.bibliotecaweb.entidade.Emprestimo;

public class EmprestimosConsultaEmAndamentoComando implements IComando {

	@Override
	public Resultado execute(HttpServletRequest request, HttpServletResponse response) {
		EmprestimoController emprestimoCtrl = new EmprestimoController(PersistenceUtil.getCurrentEntityManager());

		List<Emprestimo> emprestimos = emprestimoCtrl.listeAtivos();
		
		Resultado resultado = new Resultado();
		request.setAttribute("emprestimos", emprestimos);
		resultado.setProximaPagina("emprestimo/listagem.jsp");
		return resultado;
	}

}
