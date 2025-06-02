package com.ejemplo.programas;

import com.ejemplo.entidades.Pelicula;
import com.ejemplo.util.JPAUtil;
import com.ejemplo.util.Utils;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * listar todas las peliculas
 */
public class ListarPeliculas {
    
    public static void main(String[] args) {
        EntityManager em = null;
        
        try {
            Utils.mostrarMensaje("Iniciando listado de peliculas...");
            
            em = JPAUtil.getEntityManager();
            
            // crear consulta para obtener todas las peliculas
            TypedQuery<Pelicula> query = em.createQuery(
                "SELECT p FROM Pelicula p ORDER BY p.titulo", Pelicula.class);
            
            List<Pelicula> peliculas = query.getResultList();
            
            if (peliculas.isEmpty()) {
                Utils.mostrarMensaje("No hay peliculas registradas en la base de datos");
            } else {
                Utils.mostrarMensaje("Peliculas encontradas: " + peliculas.size());
                System.out.println("\n=== LISTADO DE PELICULAS ===");
                
                for (Pelicula pelicula : peliculas) {
                    System.out.println(pelicula);
                }
            }
            
        } catch (Exception e) {
            System.err.println("Error al listar peliculas: " + e.getMessage());
        } finally {
            JPAUtil.closeEntityManager(em);
            Utils.pausar();
        }
    }
}