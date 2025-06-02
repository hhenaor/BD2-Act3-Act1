package com.ejemplo.programas;

import com.ejemplo.entidades.Pelicula;
import com.ejemplo.util.JPAUtil;
import com.ejemplo.util.Utils;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * demostrar el nivel de aislamiento READ_UNCOMMITTED
 */
public class ListarPeliculasReadUncommitted {
    
    public static void main(String[] args) {
        EntityManager em = null;
        EntityTransaction tx = null;
        
        try {
            Utils.mostrarMensaje("Demostracion de READ_UNCOMMITTED...");
            Utils.mostrarMensaje("Este programa lee datos incluso si no estan confirmados");
            
            em = JPAUtil.getEntityManager();
            
            // configurar nivel de aislamiento READ_UNCOMMITTED
            org.hibernate.Session session = em.unwrap(org.hibernate.Session.class);
            Connection connection = session.doReturningWork(conn -> conn);
            int nivelOriginal = connection.getTransactionIsolation();
            
            Utils.mostrarMensaje("Nivel de aislamiento original: " + getNombreNivel(nivelOriginal));
            
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
            Utils.mostrarMensaje("Nivel cambiado a: READ_UNCOMMITTED");
            
            tx = em.getTransaction();
            tx.begin();
            
            // consulta con READ_UNCOMMITTED
            TypedQuery<Pelicula> query = em.createQuery(
                "SELECT p FROM Pelicula p ORDER BY p.titulo", Pelicula.class);
            
            List<Pelicula> peliculas = query.getResultList();
            
            Utils.mostrarMensaje("Peliculas leidas con READ_UNCOMMITTED: " + peliculas.size());
            System.out.println("\n=== PELICULAS (READ_UNCOMMITTED) ===");
            
            if (peliculas.isEmpty()) {
                Utils.mostrarMensaje("No hay peliculas en la base de datos");
            } else {
                for (Pelicula pelicula : peliculas) {
                    System.out.println(pelicula);
                }
            }
            
            tx.commit();
            
            // restaurar nivel de aislamiento original
            connection.setTransactionIsolation(nivelOriginal);
//            Utils.mostrarMensaje("Nivel de aislamiento restaurado");
            
        } catch (SQLException e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            System.err.println("Error en la consulta: " + e.getMessage());
        } finally {
            JPAUtil.closeEntityManager(em);
            Utils.pausar();
        }
    }
    
    /**
     * conviertir el codigo de nivel de aislamiento a nombre legible
     */
    private static String getNombreNivel(int nivel) {
        switch (nivel) {
            case Connection.TRANSACTION_READ_UNCOMMITTED:
                return "READ_UNCOMMITTED";
            case Connection.TRANSACTION_READ_COMMITTED:
                return "READ_COMMITTED";
            case Connection.TRANSACTION_REPEATABLE_READ:
                return "REPEATABLE_READ";
            case Connection.TRANSACTION_SERIALIZABLE:
                return "SERIALIZABLE";
            default:
                return "DESCONOCIDO (" + nivel + ")";
        }
    }
}