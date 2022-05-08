package com.library.servicios;

import com.library.repositorios.AutorRepositorio;
import com.library.entidades.Autor;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;


@Service
public class AutorServicio {

    @Autowired // Se crea una instancia cdo la necesita o usa la disponible.Cuando se termina de usar la borra. Esto permite no tener que instanciarlo con el constructor.
    private AutorRepositorio autorRepositorio;
    
    //METODOS CREATE
    
    @Transactional(rollbackFor = Exception.class)// rollback: si hay error vuelve al punto inicial de memoria
    public Autor guardar(Autor autor) {
        /* El metodo actualiza la DB, por lo tanto usamos un decorador Transactional*/
        //Autor autor = new Autor();
        //EL ROLLBACK ES COMO UN SEGURO. EN CASO QUE SALGA MAL VUELVE PARA ATRAS.    
        
        //seteo los  atributos
        return autorRepositorio.save(autor);   //devuelve el mismo objeto que creamos y adicionalmente un ID de la base de datos. Tal cual ocurre en mongoDB.
    }
    
    //METODOS READ

    
    // BUSCAR POR ID
   
    @Transactional(readOnly=true) // Supuestamente es una buena practica por que vos declaras que solo vas a leer y no vas a modificar nada.
    public Autor buscarPorId(String id) throws Exception {

        Optional<Autor> respuesta = autorRepositorio.findById(id);

        if (respuesta.isPresent()) {
            Autor libro = respuesta.get();
            //   
            return libro;
        } else {
            throw new Exception("No existe ese libro.");

        }

        }
    
    // BUSCAR POR NOMBRE
    
    @Transactional(readOnly=true)
    public List<Autor> buscarPorNombre(String nombre){
    
    return autorRepositorio.buscarPorNombre(nombre);
    }
    
    
    //LISTAR TODOS    
    
     @Transactional(readOnly=true)
     public List<Autor> listarTodos(){
     
         System.out.println(autorRepositorio.findAll());
         
         
     return autorRepositorio.findAll();
     }
    
    
    //METODOS UPDATE
    
    @Transactional (rollbackFor = Exception.class)
    public void update(String id, String nombre, String alta, boolean active) throws Exception{
     
         Optional<Autor> respuesta= autorRepositorio.findById(id);
         
         if(respuesta.isPresent()){
             
             
         Autor a=respuesta.get();
         a.setActive(active);
         a.setAlta(alta);
         a.setNombre(nombre);
         a.setId(id);
         autorRepositorio.save(a);
         
         }else{
             
         throw new Exception("No se encontro el libro");
         
         }
         
         
              }
    
   //METODOS DELETE
    
    
}
    
