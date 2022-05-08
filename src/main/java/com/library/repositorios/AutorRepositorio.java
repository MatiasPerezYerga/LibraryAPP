
package com.library.repositorios;

import com.library.entidades.Autor; // Clase 
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository; //Clase Padre JpaRepository
import org.springframework.data.jpa.repository.Query; // Etiqueta @Query

import org.springframework.data.repository.query.Param; // Etiqueta @Param 

import org.springframework.stereotype.Repository; //Etiqueta @Repository

        

@Repository
public interface AutorRepositorio extends  JpaRepository<Autor, String>{
    
    
    //Buscar por ID
    @Query("SELECT a FROM Autor a WHERE a.id LIKE :id")
    public Autor buscarPorId(@Param("id")String id);
    
          
    //Buscar por nombre
    @Query("SELECT a FROM Autor a WHERE a.nombre LIKE :nombre")
    public List<Autor> buscarPorNombre(@Param("nombre")String nombre);
    
    
}    
    

