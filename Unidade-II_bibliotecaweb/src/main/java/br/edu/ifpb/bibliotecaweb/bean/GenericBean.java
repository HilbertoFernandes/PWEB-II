package br.edu.ifpb.bibliotecaweb.bean;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.ValueExpression;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;



public abstract class GenericBean {

	
	
	/** Devolve um objeto acessivel a partir de uma expressao JSF-EL.
	 * @param elExpression A expressao EL de acesso ao objeto.
	 * @param clazz A classe deste objeto.
	 * @return O objeto acessivel pela EL.
	 */
	public Object getValueOf(String elExpression, Class<?> clazz) {
		FacesContext current = FacesContext.getCurrentInstance();
		ELContext elContext = current.getELContext();
		Application app = current.getApplication();
		ExpressionFactory fac = app.getExpressionFactory();
		ValueExpression ve = fac.createValueExpression(elContext, elExpression, clazz);
		return ve.getValue(current.getELContext());
	}

	/** Modifica um objeto acessivel a partir de uma expressao JSF-EL.
	 * @param elExpression A expressao EL de acesso ao objeto.
	 * @param clazz A classe deste objeto.
	 * @param value O novo objeto.
	 */
	public void setValueOf(String elExpression, Class<?> clazz, Object value) {
		FacesContext current = FacesContext.getCurrentInstance();
		ELContext elContext = current.getELContext();
		Application app = current.getApplication();
		ExpressionFactory fac = app.getExpressionFactory();
		ValueExpression ve = fac.createValueExpression(elContext, elExpression, clazz);
		ve.setValue(current.getELContext(), value);		
	}
	
	/** Obtem o backing bean a partir do seu nome.
	 * @param beanName O nome do backing bean.
	 * @return O backing bean.
	 */
	public Object getBean(String beanName) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
        ELContext elContext = facesContext.getELContext();
        return facesContext.getApplication().getELResolver().getValue(elContext, null, beanName);
	}

	/** Invalida a sessao HTTP.
	 * @param beanName O nome do backing bean.
	 */
	public void invalidateSession() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
        ((HttpSession)facesContext.getExternalContext().getSession(false)).invalidate();
	}
	
	
	public void logDebug(Logger logger, String message) {
		if (logger.isDebugEnabled()) {
			logger.debug(message);
		}
	}

	public void addMessage(FacesMessage.Severity severity, String message) {
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		FacesContext fc = FacesContext.getCurrentInstance();
		FacesMessage msg = new FacesMessage(severity,
				message, message);
		fc.addMessage(null, msg);
	}


	public Object getContextAttribute(String elExpression) {
		FacesContext facesCtx = FacesContext.getCurrentInstance();
		ELContext elCtx = facesCtx.getELContext();
		ValueExpression varasVE = facesCtx.getApplication().getExpressionFactory().createValueExpression(elCtx, elExpression, Object.class);
		return  varasVE.getValue(elCtx);

	}

	public void addErrorMessage(String mensagem) {
		this.addMessage(FacesMessage.SEVERITY_ERROR, mensagem);
	}

	public void addWarnMessage(String mensagem) {
		this.addMessage(FacesMessage.SEVERITY_WARN, mensagem);
	}
	
	public void addSuccessMessage(String mensagem) {
		this.addMessage(FacesMessage.SEVERITY_INFO, mensagem);
	}
	
	
	/**
	 * Seta um parametro de request chamado endofconversation para sinalizar para o filtro
	 * que a conversacao deve ser finalizada e o entitymanager fechado. Usado logo depois de um
	 * rollback em uma transacao de BD.
	 */
	public void endOfConversation() {
		this.setValueOf("#{requestScope.endofconversation}", String.class, "true");
	}
	
	public void setFlash(String nome, Object valor) {
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put(nome, valor);
	}
	
	public Object getFlash(String nome) {
		return FacesContext.getCurrentInstance().getExternalContext().getFlash().get(nome);
	}
	


}
