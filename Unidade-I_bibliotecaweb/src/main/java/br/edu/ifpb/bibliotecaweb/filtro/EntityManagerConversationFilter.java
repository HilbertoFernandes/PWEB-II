package br.edu.ifpb.bibliotecaweb.filtro;

import java.io.IOException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.hibernate.StaleObjectStateException;
import br.edu.ifpb.bibliotecaweb.dao.ManagedEMContext;
import br.edu.ifpb.bibliotecaweb.dao.PersistenceUtil;

@WebFilter (urlPatterns = {"*.do", "*.jsp"})
public class EntityManagerConversationFilter implements Filter {
	private static Logger logger = Logger.getLogger(EntityManagerConversationFilter.class);

	private EntityManagerFactory emf;

	public static final String ENTITYMANAGER_FACTORY_KEY = "currentEntityManager";

	public static final String END_OF_CONVERSATION_FLAG = "endofconversation";

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		EntityManager currentEntityManager;
		
		// Try to get a EntityManager from the HttpSession
		HttpSession httpSession = 
			((HttpServletRequest) request).getSession();
		EntityManager disconnectedEM = 
			(EntityManager) httpSession
				.getAttribute(ENTITYMANAGER_FACTORY_KEY);

		// Context path
		String context = ((HttpServletRequest) request).getServletPath();
		context += ": ";
		try {

			// Start a new conversation or in the middle?
			if (disconnectedEM == null) {
				logger.debug(context + ">>> Nova conversaÁ„o");
				// Define o modo manual de flush
				currentEntityManager = emf.createEntityManager();
			} else {
				logger.debug(context + "< Continuando conversaÁ„o");
				currentEntityManager = disconnectedEM;
			}
			
			ManagedEMContext.bind(emf, currentEntityManager);
			logger.debug(context + "Associou EntityManager ao contexto");

			logger.debug(context + "Processando servlet/JSP"); 
			chain.doFilter(request, response); 

			// End or continue the long-running conversation?
			if (request.getAttribute(END_OF_CONVERSATION_FLAG) != null
					|| request.getParameter(END_OF_CONVERSATION_FLAG) != null) {

				currentEntityManager.close();
				logger.debug(context + "Fechando o EntityManager");

				ManagedEMContext.unbind(emf);
				logger.debug(context + "Desassociou EntityManager do contexto");
				
				if (((HttpServletRequest) request).isRequestedSessionIdValid()) {
					httpSession.removeAttribute(ENTITYMANAGER_FACTORY_KEY);
					logger.debug(context + "Retirou EntityManager da HttpSession");
				}

				logger.debug(context + "<<< Fim da conversaÁ„o");

			} else {
				ManagedEMContext.unbind(emf);
				logger.debug(context + "Desassociou EntityManager do contexto");
				
				if (((HttpServletRequest) request).isRequestedSessionIdValid()) {
					httpSession.setAttribute(ENTITYMANAGER_FACTORY_KEY, currentEntityManager);
					logger.debug(context + "Associou EntityManager a HttpSession");
				}

				logger.debug(context + "> Retornando para o usuario");
			}

		} catch (StaleObjectStateException staleEx) {
			logger.error(context
							+ "This interceptor does not implement optimistic concurrency control!");
			logger.error(context
							+ "Your application will not work until you add compensation actions!");
			throw staleEx;
		} catch (Throwable ex) {
			// Rollback only
			logger.debug(context + "Original exception:", ex);
			try {
				if (PersistenceUtil.getCurrentEntityManager().getTransaction().isActive()) {
					logger.debug(context
									+ "Tentando rollback da transa√ß√£o ap√≥s exception");
					PersistenceUtil.getCurrentEntityManager().getTransaction().rollback();
				}
			} catch (Throwable rbEx) {
				logger.error(context
						+ "Rollback n√£o efetivado!",
						rbEx);
			} finally {
				logger.error(context + "Cleanup after exception!");

				// Cleanup
				currentEntityManager = ManagedEMContext.unbind(emf);
				logger.debug(context + "Desassociou EntityManager do contexto");

				currentEntityManager.close();
				logger.debug(context + "Fechou EntityManager ap√≥s exception");
				
				if (((HttpServletRequest) request).isRequestedSessionIdValid()) {
					httpSession.setAttribute(ENTITYMANAGER_FACTORY_KEY, null);
					logger.debug(context + "Removeu EntityManager da HttpSession");
				}

			}

			// Let others handle it... maybe another interceptor for exceptions?
			throw new ServletException(ex);
		} finally {

		}
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		emf = PersistenceUtil.getEntityManagerFactory();
	}

	public void destroy() {
	}

}
