package com.ejemplo.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * utilidad para administrar EntityManager de JPA
 */
public class JPAUtil {
    private static final String PERSISTENCE_UNIT = "PeliculasJPA";
    private static EntityManagerFactory emf;
    
    static {
        try {
            emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
        } catch (Exception e) {
            System.err.println("Error al crear EntityManagerFactory: " + e.getMessage());
            throw new ExceptionInInitializerError(e);
        }
    }
    
    /**
     * obtiene un EntityManager
     */
    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    /**
     * cierra el EntityManagerFactory
     */
    public static void closeEntityManagerFactory() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
    
    /**
     * cierra un EntityManager de forma segura
     */
    public static void closeEntityManager(EntityManager em) {
        if (em != null && em.isOpen()) {
            em.close();
        }
    }
}