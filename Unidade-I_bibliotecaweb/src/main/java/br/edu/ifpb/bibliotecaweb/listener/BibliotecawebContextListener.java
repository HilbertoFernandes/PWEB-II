package br.edu.ifpb.bibliotecaweb.listener;

import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import br.edu.ifpb.bibliotecaweb.dao.PersistenceUtil;

@WebListener
public class BibliotecawebContextListener implements ServletContextListener {
	private EntityManagerFactory emf;

    public void contextDestroyed(ServletContextEvent e)  {
    	if (emf != null && emf.isOpen()) {
    		emf.close();
    	}
    }

    public void contextInitialized(ServletContextEvent e)  { 
         emf = PersistenceUtil.createEntityManagerFactory("bibliotecaweb");
         System.out.println("Fábrica de EntityManagers construida!");
         
    }
	
}
