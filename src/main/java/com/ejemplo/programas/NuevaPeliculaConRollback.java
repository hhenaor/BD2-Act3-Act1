package com.ejemplo.programas;

import com.ejemplo.entidades.Pelicula;
import com.ejemplo.util.JPAUtil;
import com.ejemplo.util.Utils;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

/**
 * insertar una nueva pelicula con manejo de rollback
 */
public class NuevaPeliculaConRollback {
    
    public static void main(String[] args) {
        EntityManager em = null;
        EntityTransaction tx = null;
        
        try {
            Utils.mostrarMensaje("Creando nueva pelicula con control de transacciones...");
            
            em = JPAUtil.getEntityManager();
            tx = em.getTransaction();
            
            // datos de la pelicula
            String titulo = Utils.leerString("Ingrese el titulo de la pelicula");
            String director = Utils.leerString("Ingrese el director");
            int anio = Utils.leerInt("Ingrese el año de estreno");
            String genero = Utils.leerString("Ingrese el genero");
            
            // nueva pelicula
            Pelicula nuevaPelicula = new Pelicula(titulo, director, anio, genero);
            
            // transaccion
            tx.begin();
            Utils.mostrarMensaje("Transaccion iniciada");
            
            // persistir la entidad
            em.persist(nuevaPelicula);
            Utils.mostrarMensaje("Pelicula persistida en memoria (aun no confirmada)");
            
            // Ffush para imsercion en la bd antes del commit
            em.flush();
            Utils.mostrarMensaje("Flush ejecutado - Pelicula visible en BD pero sin confirmar");
            
            // provocar error
            String errorStr = Utils.leerTexto("Provocamos error (s/n)?: ");
            if (errorStr.equals("s")) {
                throw new RuntimeException("Provocado error");
            }
            
            // no hay error? hacer commit
            tx.commit();
            Utils.mostrarMensaje("Transaccion confirmada - Pelicula guardada exitosamente");
            
        } catch (RuntimeException e) {
            // error? hacer rollback si la transaccion esta activa
            if (tx != null && tx.isActive()) {
                tx.rollback();
                Utils.mostrarMensaje("Error detectado - Rollback automatico ejecutado");
            }
            System.err.println("Error al crear pelicula: " + e.getMessage());
        } finally {
            JPAUtil.closeEntityManager(em);
            Utils.pausar();
        }
    }
}