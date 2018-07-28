package br.edu.ifpb.bibliotecaweb.comando;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.ifpb.bibliotecaweb.controlador.EmprestimoController;
import br.edu.ifpb.bibliotecaweb.controlador.Resultado;
import br.edu.ifpb.bibliotecaweb.dao.PersistenceUtil;
import br.edu.ifpb.bibliotecaweb.entidade.Emprestimo;

public class CadastraEmprestimoComando implements IComando {

	@Override
	public Resultado execute(HttpServletRequest request, HttpServletResponse response) {

		final String paginaSucesso = "controller.do?op=EmprestimosConsultaEmAndamentoComando";
		final String paginaErro = "controller.do?op=NovoEmprestimoComando";

		EmprestimoController emprestimoCtrl = new EmprestimoController(PersistenceUtil.getCurrentEntityManager());
		Resultado resultado = emprestimoCtrl.cadastre(request.getParameterMap());

		if (!resultado.isErro()) {
			resultado.setProximaPagina(paginaSucesso);
			resultado.setRedirect(true);
		} else {
			request.setAttribute("emprestimo", (Emprestimo) resultado.getEntidade());
			request.setAttribute("_msg", resultado.getMensagens());
			resultado.setProximaPagina(paginaErro);
		}
		return resultado;
	}
}
