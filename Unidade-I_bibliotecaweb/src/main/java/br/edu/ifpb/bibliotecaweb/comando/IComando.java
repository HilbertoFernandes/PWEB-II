package br.edu.ifpb.bibliotecaweb.comando;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.ifpb.bibliotecaweb.controlador.Resultado;

public interface IComando {
	
	Resultado execute(HttpServletRequest request, HttpServletResponse response);

}
