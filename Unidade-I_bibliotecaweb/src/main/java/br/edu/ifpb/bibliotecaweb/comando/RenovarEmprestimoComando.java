package br.edu.ifpb.bibliotecaweb.comando;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import br.edu.ifpb.bibliotecaweb.controlador.EmprestimoController;
import br.edu.ifpb.bibliotecaweb.controlador.Resultado;
import br.edu.ifpb.bibliotecaweb.dao.PersistenceUtil;
import br.edu.ifpb.bibliotecaweb.entidade.Emprestimo;

public class RenovarEmprestimoComando implements IComando {

	@Override
	public Resultado execute(HttpServletRequest request, HttpServletResponse response) {
		EmprestimoController emprestimoCtrl = new EmprestimoController(PersistenceUtil.getCurrentEntityManager());
		Resultado resultado = emprestimoCtrl.renove(request.getParameterMap());
		resultado.setProximaPagina("controller.do?op=EmprestimosConsultaEmAndamentoComando");
		request.setAttribute("emprestimo", (Emprestimo) resultado.getEntidade());
		request.setAttribute("_msg", resultado.getMensagens());
		return resultado;
	}
}
