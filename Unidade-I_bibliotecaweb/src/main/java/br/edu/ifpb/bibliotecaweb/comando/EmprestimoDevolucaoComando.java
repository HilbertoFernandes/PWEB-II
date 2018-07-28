package br.edu.ifpb.bibliotecaweb.comando;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import br.edu.ifpb.bibliotecaweb.controlador.EmprestimoController;
import br.edu.ifpb.bibliotecaweb.controlador.Resultado;
import br.edu.ifpb.bibliotecaweb.dao.PersistenceUtil;

public class EmprestimoDevolucaoComando implements IComando {

		@Override
		public Resultado execute(HttpServletRequest request, HttpServletResponse response) {
			EmprestimoController emprestimoCtrl = new EmprestimoController(PersistenceUtil.getCurrentEntityManager());

			Resultado resultado = emprestimoCtrl.devolva(request.getParameterMap());

			resultado.setProximaPagina("controller.do?op=ConsultaEmprestimoComando");
			return resultado;
		}

	}

