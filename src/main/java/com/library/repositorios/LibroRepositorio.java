
package com.library.repositorios;

import com.library.entidades.Libro; // Clase 
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository; //Clase Padre JpaRepository
import org.springframework.data.jpa.repository.Query; // Etiqueta @Query

import org.springframework.data.repository.query.Param; // Etiqueta @Param 

import org.springframework.stereotype.Repository; //Etiqueta @Repository

//EN EL REPOSITORIO VAN LOS METODOS READ PERSONALIZADOS

@Repository
public interface LibroRepositorio extends  JpaRepository<Libro, String>{
    
    
    // CREATE
    
    
    
    
    // UPDATE
    
    //READ 
    
    //Buscar por id
    @Query("SELECT l FROM Libro l WHERE l.id LIKE :id")
    public Libro buscarPorId(@Param("id")String id);
    
    //Buscar por titulo
    @Query("SELECT l FROM Libro l WHERE l.titulo LIKE :titulo")
    public List<Libro> buscarPorTitulo(@Param("titulo")String titulo);
    
    //Buscar por isnb
       
   @Query("SELECT l FROM Libro l WHERE l.isbn LIKE :isbn")
   public Libro buscarPorIsbn(@Param("isbn")String isbn);
   
    //Buscar por autor
    
    @Query("SELECT l FROM Libro l WHERE l.autor.nombre LIKE :nombre")
    public List<Libro> buscarPorAutor(@Param("nombre") String nombre);
   
    //Buscar por Editorial
    
    @Query("SELECT l FROM Libro l WHERE l.editorial.nombre LIKE :nombre")
    public List<Libro> buscarPorEditorial(@Param("nombre") String nombre);
    
    // Buscar Activos
    
    @Query("SELECT l FROM Libro l WHERE l.active=true")
    public List<Libro> buscarActivos();
    
    // DELETE
    
    
    
    
    
    
    
}
