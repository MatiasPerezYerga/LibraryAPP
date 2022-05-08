package com.library.servicios;



import com.library.entidades.Autor;
import com.library.entidades.Editorial;
import com.library.repositorios.LibroRepositorio;
import com.library.entidades.Libro;
import java.util.Date;
import java.util.List;
import java.util.Optional;


import org.springframework.stereotype.Service;
import  org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



@Service //Esto le dice a spring que es un servicio y lo inyecta como tal.
 public class LibroServicio {
    
    @Autowired // Se crea una instancia cdo la necesita o usa la disponible.Cuando se termina de usar la borra. Esto permite no tener que instanciarlo con el constructor.
     private LibroRepositorio libroRepositorio;
 
 
     @Autowired
     private AutorServicio autorServicio; 
    //Creamos este Servicio, (aunque innecesario. Respetamos el orden de la comunicacion de capas) Realizamos best practices.
    //Usamos el servicio para llamar al repositorio y esto habilita para pasarle el objeto Autor
     /*
     Autor autor = autorServicio.buscarPorId(idAutor);
     libro.setAutor(autor);
     
     
     */
     //SI SE REGISTRAN ERRORES DEL TIPO BIN SE REFIERE A QUE NOS HEMOS OLVIDADO DE PONER UNAS NOTATION.
     /* SUPER RECOMENDADO POR VALEN:
     Si estamos trabajando en el libroServicio  el UNICO repositorio que puede haber es libroRepositorio,
     para llamar a otros repositorios de otros objetos tenemos que instanciar los servicios . Esta prohibido que haya otros repositorios en esta capa.
        
     
     */
     
     
     //METODOS CREATE
     
     
    @Transactional(rollbackFor = Exception.class)// rollback: si hay error vuelve al punto inicial de memoria
     public  Libro guardar(Libro libro)throws Exception {
        /* El metodo actualiza la DB, por lo tanto usamos un decorador Transactional*/    
        
        //validar(titulo);
        
        //Libro libro2 = new Libro();
    //EL ROLLBACK ES COMO UN SEGURO. EN CASO QUE SALGA MAL VUELVE PARA ATRAS.    
        //seteo los  atributos
      //  Long isbn =123423123123238910L;
        //le setea el ALTA COMO ATRIBUTO DATE
       // libro2.setTitulo(titulo);
      //  libro2.setIsbn(isbn);
        
        System.out.println(libro);
        return libroRepositorio.save(libro);   //devuelve el mismo objeto que creamos y adicionalmente un ID de la base de datos. Tal cual ocurre en mongoDB.
        
        
    }
     
    
     
     //METODOS UPDATE
     
     /**************************FALTA COMPLETA LOS UPDATE
     * @return S************************/
     
     @Transactional(rollbackFor = Exception.class)
     public void update(String id, Long isbn, String titulo, Integer anio, Integer ejemplares, Integer ejemplaresPrestrados, Integer ejemplaresRestantes, String alta, boolean active, Autor autor, Editorial editorial) throws Exception{
     
         Optional<Libro> respuesta= libroRepositorio.findById(id);
         
         if(respuesta.isPresent()){
         Libro l=respuesta.get();
         l.setActive(active);
         l.setAlta(alta);
         l.setAutor(autor);
         l.setEditorial(editorial);
         l.setEjemplares(ejemplares);
         l.setEjemplaresPrestados(ejemplaresPrestrados);
         l.setEjemplaresRestantes(ejemplaresRestantes);
         l.setIsbn(isbn);
         l.setTitulo(titulo);
         libroRepositorio.save(l);
         
         }else{
         throw new Exception("No se encontro el libro");
         }
         
         
              }
     
     
     
     
     
     
     //METODOS READ
     
         
     
     //LISTAR TODOS    
     @Transactional(readOnly=true)
     public List<Libro> listarTodos(){
     
         System.out.println(libroRepositorio.findAll());
         
         
     return libroRepositorio.findAll();
     }
     
        
     // LISTAR ACTIVOS
     @Transactional(readOnly=true)
     public List<Libro> listarActivos(){
     
     return libroRepositorio.buscarActivos();
     }
     
     //BUSCAR POR ID
     @Transactional(readOnly=true) // Supuestamente es una buena practica por que vos declaras que solo vas a leer y no vas a modificar nada.
     public Libro buscarPorId(String id) throws Exception{
     
     Optional<Libro> respuesta = libroRepositorio.findById(id);
     
     
     if(respuesta.isPresent()){
         Libro libro= respuesta.get();
       //   
     return libro; }else{
        throw new Exception("No existe ese libro.");
    //VALEN EN LOS SERVICIOS NO RECOMIENDA COLOCAR TRY CATH, SI EN LOS CONTROLADORES.
     }
    }
     
     //BUSCAR POR TITULO
    @Transactional(readOnly=true)
    public List<Libro> buscarPorTitulo(String titulo){
        
            
    return libroRepositorio.buscarPorTitulo(titulo);
        
    }
    
    
     @Transactional(readOnly=true)
     
     //BUSCAR POR ISBN
     
     public Libro buscarPorIsbn(String id) {
     
        
        return libroRepositorio.buscarPorIsbn(id);
        
     }
     

     //Metodo para retornar una lista
     
     @Transactional(readOnly=true)
     public List<Libro> buscarPorAutor(String nombre){
         
         
    return libroRepositorio.buscarPorAutor(nombre);
    
    }
     
  
     //BUSCAR POR EDITORIAL 
     
     @Transactional (readOnly=true)
     public List<Libro> buscarPorEditorial(String nombre){
     
     return libroRepositorio.buscarPorEditorial(nombre);
     }
     
     
     //METODOS DELETE
     
     /* 
     
     
     
     */
     
     
     //METODOS VALIDATE
     private void validar(String titulo) throws Exception{
         if(titulo==null ||titulo.isEmpty()){
         
             throw new Exception ("El título no puede estar vacío.");
         
         }
     
     }
     
     
     
   /*  @Transactional(readOnly=true)
     public List<Libro> listar(){
     
         return libroRepositorio.finAll();
     
     }*/
     
     
}
