package com.ejemplo.entidades;

import javax.persistence.*;

@Entity
@Table(name = "peliculas")
public class Pelicula {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "titulo", nullable = false, length = 100)
    private String titulo;
    
    @Column(name = "director", length = 100)
    private String director;
    
    @Column(name = "anio")
    private Integer anio;
    
    @Column(name = "genero", length = 50)
    private String genero;
    
    // constructor por defecto
    public Pelicula() {}
    
    // constructor con parametros
    public Pelicula(String titulo, String director, Integer anio, String genero) {
        this.titulo = titulo;
        this.director = director;
        this.anio = anio;
        this.genero = genero;
    }
    
    // getters y geters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getTitulo() {
        return titulo;
    }
    
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    
    public String getDirector() {
        return director;
    }
    
    public void setDirector(String director) {
        this.director = director;
    }
    
    public Integer getAnio() {
        return anio;
    }
    
    public void setAnio(Integer anio) {
        this.anio = anio;
    }
    
    public String getGenero() {
        return genero;
    }
    
    public void setGenero(String genero) {
        this.genero = genero;
    }
    
    @Override
    public String toString() {
        return String.format("ID: %d | %s (%d) - Dir: %s - Genero: %s", 
                           id, titulo, anio, director, genero);
    }
}