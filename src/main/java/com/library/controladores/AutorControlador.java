
package com.library.controladores;

import com.library.entidades.Autor;
import com.library.entidades.Libro;
import com.library.servicios.AutorServicio;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/autor")
public class AutorControlador {
    
    @Autowired
     private AutorServicio autorServicio;
    
    /// METODO GET LIBRO & DETAIL
    
    @GetMapping("/autor/{id}")
    public String detalleGet(ModelMap modelo, @PathVariable String id) throws Exception{
    
        Autor a = autorServicio.buscarPorId(id);
        
        modelo.put("autor",a);
        
        return "autor-detail.html";
        
    }
    
   // METODO GET ALL 
    
    @GetMapping("/lista")
     public String lista(ModelMap modelo){
     
     List <Autor> todos = autorServicio.listarTodos();
     
      modelo.put("autores", todos);
     
     return "list-autor.html";
         
     }
     
     
     // METODO EDITAR/{ID}
     @GetMapping("/libro/editar/{id}")
    public String editarGet(ModelMap modelo,@PathVariable String id){
    
        
        try{
        Autor a = autorServicio.buscarPorId(id);
        modelo.put("libro",a);
        }catch(Exception e){
        modelo.put("error",e.getMessage());
    }
        
        return "edit-autor.html";
        
        
    }
    
     @PostMapping("/edit")
      public String editarPost(RedirectAttributes attr,@RequestParam String id, @RequestParam  String nombre, @RequestParam String alta, @RequestParam  boolean active) throws Exception{
      
      autorServicio.update( id,nombre ,  alta,  active);
          
          
      return "redirect:/edit-libro.html";
      }
    
    
      //Como el get y el post van por caminos diferentes no hay problema que haya 2 URls. 
    @PostMapping("/autor/form") //ResquesParam ES PARA CUANDO RECIBIMOS UN PARAMETRO DEL FRONT
    //RequestParam es un parametro de la Request
    public String crearAutor(ModelMap modelo, @RequestParam Autor autor){
        try { //COMO LA ULTIMA CAPA DE LOGICA ES EL CONTROLADOR AHI COLOCAMOS EL TRY AND CATCH
            autorServicio.guardar(autor);
            modelo.put("exito","El libro '"+autor.getNombre()+"' se cargo exitosamente");
        } catch (Exception e) {
            
            modelo.put("error", e.getMessage());
        }
        
        System.out.println("titulo= "+autor.getNombre());
        
    
    return "libro-form.html";
    }
    
      
      
    
    
}
