package com.library.controladores;


import com.library.entidades.Autor;
import com.library.entidades.Editorial;
import com.library.entidades.Libro;
import com.library.servicios.AutorServicio;
import com.library.servicios.EditorialServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.library.servicios.LibroServicio;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;



@Controller
//@RequestMapping("/libro")//LA ETIQUETA REQUEST MAPPING DECLARA LA RUTA MADRE 
public class LibroControlador {

    @Autowired
    private LibroServicio libroServicio;

    @Autowired
    private AutorServicio autorServicio;

    @Autowired
    private EditorialServicio editorialServicio;

    private boolean option = true;
    private int opNum = 0;
    
    private boolean opIf= true;


    
@RequestMapping(value="/do-stuff")
    public void set1(){
        
        System.out.println("es 1: " +opNum);
         opNum=1;   
         
           System.out.println("despues del transform: " +opNum);
       System.out.println("QUE MIERDA PASA");
    }
    
    
   
    public int set2(){
        
         System.out.println("es 2" +opNum);
    return opNum=2;    
        
    }

    // Dividir el controlador en metodos GET para matchear con metodos READ  y POST para CREATE UPDATE and DELETE.
@GetMapping("/book/{id}")
    public String detalleGet(ModelMap modelo, @PathVariable String id) throws Exception {

        Libro l = libroServicio.buscarPorId(id);

        modelo.put("libro", l);
        return "libro-detail.html";
    }

@GetMapping("/lista")
    public String lista(ModelMap modelo) {

        List<Libro> todos = libroServicio.listarTodos();

        modelo.put("libros", todos);

        return "list-libro.html";
    }

@GetMapping("/index2")
    public String index2(ModelMap modelo) {
        List<String> nombres = Arrays.asList("ivo", "valen", "rami");

        modelo.put("nombres", nombres);

        //modelo.put(nombre,"")
        // EL METODO PUT METE UN ELEMENTO EN EL FRONT!
        modelo.put("mensaje", "Este es un mensaje desde el back");
        return "index2.html";

    }

@GetMapping("/libro/editar/{id}")
    public String editarGet(ModelMap modelo, @PathVariable String id) {

        try {
            Libro l = libroServicio.buscarPorId(id);
            modelo.put("libro", l);
        } catch (Exception e) {
            modelo.put("error", e.getMessage());
        }

        return "edit-libro.html";

    }

    @PostMapping("/edit")
    public String editarPost(RedirectAttributes attr, @RequestParam String id, @RequestParam Long isbn, @RequestParam String titulo, @RequestParam Integer anio, @RequestParam Integer ejemplares, @RequestParam Integer ejemplaresPrestrados, @RequestParam Integer ejemplaresRestantes, @RequestParam String alta, @RequestParam boolean active, @RequestParam Autor autor, @RequestParam Editorial editorial) throws Exception {

        libroServicio.update(id, isbn, titulo, anio, ejemplares, ejemplaresPrestrados, ejemplaresRestantes, alta, active, autor, editorial);

        return "redirect:/edit-libro.html";
    }

    @GetMapping("/libro/registro")
    public String form(ModelMap modelo) {

        List<Autor> todos = autorServicio.listarTodos();
        List<Editorial> todas = editorialServicio.listarTodas();

        modelo.put("editoriales", todas);
        modelo.put("autores", todos);
      //  modelo.put("option", option);
       // modelo.put("opNum", opNum);
       // modelo.put("opIf",opIf);
       
        
       
        
        return "libro-form.html";

    }

    //Como el get y el post van por caminos diferentes no hay problema que haya 2 URls. 
    @PostMapping("/libro/registro") //ResquesParam ES PARA CUANDO RECIBIMOS UN PARAMETRO DEL FRONT
    //RequestParam es un parametro de la Request
    public String crearLibro(ModelMap modelo, @RequestParam String titulo, @RequestParam Long isbn, @RequestParam Integer anio, @RequestParam Integer ejemplares, @RequestParam Integer ejemplaresPrestados, @RequestParam Integer ejemplaresRestantes, @RequestParam String alta, @RequestParam boolean active /*,@RequestParam  String autorNombre*/) {
        try { //COMO LA ULTIMA CAPA DE LOGICA ES EL CONTROLADOR AHI COLOCAMOS EL TRY AND CATCH

            Libro l = new Libro();
            Autor a = new Autor();
            l.setTitulo(titulo);
            l.setIsbn(isbn);
            l.setAnio(anio);
            l.setEjemplares(ejemplares);
            l.setEjemplaresPrestados(ejemplaresPrestados);
            l.setEjemplaresRestantes(ejemplaresRestantes);
            l.setAlta(alta);
            l.setActive(active);
            //a.setNombre(autorNombre);

            libroServicio.guardar(l);
            modelo.put("exito", "El libro '" + l.getTitulo() + "' se cargo exitosamente");
            System.out.println("titulo= " + l.getTitulo());

        } catch (Exception e) {

            modelo.put("error", e.getMessage());
        }

        return "libro-form.html";
    }

}



/* GUILLE PINTOS API REST

package com.libreriaSpring.controladores;

import com.libreriaSpring.entidades.Autor;
import com.libreriaSpring.errores.ErrorServicio;
import com.libreriaSpring.servicios.AutorServicio;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authors")
public class AutorControllerRest {
    
    private HashMap<Object, Object> data;
    
    @Autowired
    public AutorServicio autorService;
    
    @GetMapping
    public ResponseEntity<List<Autor>> getAll() {
        List<Autor> authors = autorService.listarTodos();
        return ResponseEntity.status(HttpStatus.OK).body(authors);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<HashMap<Object, Object>> getById(@PathVariable String id) {
        data = new HashMap();
        try {
            Autor author = autorService.buscarPorId(id);
            data.put("author", author);
            return ResponseEntity.status(HttpStatus.OK).body(data);
        } catch (ErrorServicio ex) {
            data.put("message", "Author with id: " + id + " not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(data);
        }
        
    }
    
}




*/
