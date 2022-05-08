
package com.library.repositorios;
import com.library.entidades.Editorial; // Clase 
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository; //Clase Padre JpaRepository
import org.springframework.data.jpa.repository.Query; // Etiqueta @Query

import org.springframework.data.repository.query.Param; // Etiqueta @Param 

import org.springframework.stereotype.Repository; //Etiqueta @Repository






@Repository
public interface EditorialRepositorio extends  JpaRepository<Editorial, String>{
    
    
    //Busqueda por ID
    @Query("SELECT e FROM Editorial e WHERE e.id LIKE :id")
    public Editorial buscarPorId(@Param("id")String id);
    
        
    //Buscar por nombre
    @Query("SELECT e FROM Editorial e WHERE e.nombre LIKE :nombre")
    public List<Editorial> buscarPorNombre(@Param("nombre")String nombre);
    
        
    @Query("SELECT e FROM Editorial e WHERE e.active=true")
    public List<Editorial>  listarTodos();
      
    
    
}    

