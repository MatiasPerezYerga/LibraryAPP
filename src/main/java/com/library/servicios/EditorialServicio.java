
package com.library.servicios;

import com.library.repositorios.EditorialRepositorio;
import com.library.entidades.Editorial;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import  org.springframework.beans.factory.annotation.Autowired;


 @Service //Esto le dice a spring que es un servicio y lo inyecta como tal.
public class EditorialServicio {

    
   
 
    
    @Autowired // Se crea una instancia cdo la necesita o usa la disponible.Cuando se termina de usar la borra. Esto permite no tener que instanciarlo con el constructor.
     private EditorialRepositorio editorialRepositorio;
 
    //METODOS CREATE
    
    @Transactional(rollbackFor = Exception.class)// rollback: si hay error vuelve al punto inicial de memoria
     public  Editorial guardar(Editorial editorial){
        /* El metodo actualiza la DB, por lo tanto usamos un decorador Transactional*/    
        //EL ROLLBACK ES COMO UN SEGURO. EN CASO QUE SALGA MAL VUELVE PARA ATRAS.    
        //seteo los  atributos
        
        return editorialRepositorio.save(editorial);   //devuelve el mismo objeto que creamos y adicionalmente un ID de la base de datos. Tal cual ocurre en mongoDB.
          
        
    //METODOS READ
        
    }
     @Transactional(readOnly=true) // Supuestamente es una buena practica por que vos declaras que solo vas a leer y no vas a modificar nada.
     public Editorial buscarPorId(String id) throws Exception{
     
     Optional<Editorial> respuesta = editorialRepositorio.findById(id);
     
     
     if(respuesta.isPresent()){
         Editorial libro= respuesta.get();
       //   
     return libro; }else{
        throw new Exception("No existe ese libro.");
    
     }
    }
     //METODO BUSCAR POR NOMBRE
     
     @Transactional(readOnly=true)
     public List<Editorial> buscarPorNombre(String nombre){
     
     return editorialRepositorio.buscarPorNombre(nombre);
     }
     
     
     
     
     //METODO LISTAR TODOS
     @Transactional(readOnly=true)
     public List<Editorial> listarTodas(){
     
     return editorialRepositorio.findAll();
     }
              
     //METODO LISTAR ACTIVOS
     
     @Transactional(readOnly=true)
     public List<Editorial> listarActivos(){
     
     return editorialRepositorio.listarTodos();
     }
     
     
     //METODOS UPDATED
     
     @Transactional (rollbackFor = Exception.class)
        public void update(String id, String nombre, String alta, boolean active) throws Exception{
     
         Optional<Editorial> respuesta= editorialRepositorio.findById(id);
         
         if(respuesta.isPresent()){
             
         Editorial e=respuesta.get();
         e.setActive(active);
         e.setAlta(alta);
         e.setNombre(nombre);
         e.setId(id);
         editorialRepositorio.save(e);
         
         }else{
             
         throw new Exception("No se encontro el libro");
         
         }
         
         
              }
     
     
     
     //METODOS DELETE
    
   
}
