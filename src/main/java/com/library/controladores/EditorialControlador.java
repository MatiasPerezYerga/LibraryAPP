package com.library.controladores;

import com.library.entidades.Editorial;
import com.library.servicios.EditorialServicio;
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
@RequestMapping("/editorial")
public class EditorialControlador {
    
@Autowired
 private EditorialServicio editorialServicio;

//METODO GET EDITORIAL & DETAIL
@GetMapping("/editorial/{id}")
public String detalleGet(ModelMap modelo,@PathVariable String id) throws Exception {

    Editorial e= editorialServicio.buscarPorId(id);
    
    modelo.put("editorial",e);
    
    return "editorial-detail.html";

}

// METODO GET ALL

@GetMapping("/lista/editorial")
public String lista(ModelMap modelo){

List <Editorial> todos = editorialServicio.listarTodas();
modelo.put("editoriales",todos);

return "list-editorial.html";
} 

 // METODO EDITAR/{ID}
     @GetMapping("/editorial/editar/{id}")
    public String editarGet(ModelMap modelo,@PathVariable String id){
    
        
        try{
        Editorial a = editorialServicio.buscarPorId(id);
        modelo.put("libro",a);
        }catch(Exception e){
        modelo.put("error",e.getMessage());
    }
        
        return "edit-editorial.html";
        
        
    }
    
    //METODO POST EDIT
     @PostMapping("/edit")
      public String editarPost(RedirectAttributes attr,@RequestParam String id, @RequestParam  String nombre, @RequestParam String alta, @RequestParam  boolean active) throws Exception{
      
      editorialServicio.update( id,nombre ,  alta,  active);
                
      return "redirect:/edit-editorial.html";
      }
      
      
      
      //Como el get y el post van por caminos diferentes no hay problema que haya 2 URls. 
    @PostMapping("/editorial/form") //ResquesParam ES PARA CUANDO RECIBIMOS UN PARAMETRO DEL FRONT
    //RequestParam es un parametro de la Request
    public String crearEditorial(ModelMap modelo, @RequestParam Editorial editorial){
        try { //COMO LA ULTIMA CAPA DE LOGICA ES EL CONTROLADOR AHI COLOCAMOS EL TRY AND CATCH
            editorialServicio.guardar(editorial);
            modelo.put("exito","El libro '"+editorial.getNombre()+"' se cargo exitosamente");
        } catch (Exception e) {
            
            modelo.put("error", e.getMessage());
        }
        
        System.out.println("titulo= "+editorial.getNombre());
        
    
    return "editorial-form.html";
    }




    
}

