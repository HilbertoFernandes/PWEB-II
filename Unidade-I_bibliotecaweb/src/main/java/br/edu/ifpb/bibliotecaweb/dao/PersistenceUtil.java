package br.edu.ifpb.bibliotecaweb.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PersistenceUtil {

    private static EntityManagerFactory emf;
    private static ManagedEMContext emc;

	private PersistenceUtil() {
		// Singleton
	}
	
    public static EntityManagerFactory createEntityManagerFactory(String persistenceUnitName) {
        try {
    		emf = Persistence.createEntityManagerFactory(persistenceUnitName);
    		emc = new ManagedEMContext(emf);
            return emf;
        } catch (Throwable ex) {
            System.out.println("Falha ao criar EntityManagerFactory.");
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static EntityManagerFactory getEntityManagerFactory() {
        return emf;
    }
    
    public static EntityManager getCurrentEntityManager() {
    	return emc.currentEntityManager();
    }
    
    public static EntityManager getEntityManager() {
    	return emf.createEntityManager();
    }
}