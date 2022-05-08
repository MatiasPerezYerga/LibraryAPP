
package com.library.controladores;

import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.ModelMap;


//Seria como el app COMPONENT.

//Los controladores son el nexo entre la vista y los servicios o logica de negocio.

//APARENTEMENTE ESTA EL CONTROLADOR Y EL APP.ROUTER UNIDO 
@Controller
public class PortalControlador {
    
//Aca vamos a tener las rutas de nuestra app

//Para traer datos o traer vistas  usamos el método  Get     
    
// Cuando queramos mandar datos desde el front hacia el back utilizamos el método POST
    
//Realizamos la ruta para cada determinada vistas    
    
    @GetMapping("/pepe") //o @PostMapping
    public String index2(ModelMap modelo){/*Aqui podemos pasar parametros y Spring cuando llame al metodo lo va a inyectar a nuestro Template*/
    
      List<String> nombres= Arrays.asList("ivo", "valen", "rami");
      
      modelo.put("nombres",nombres);
        
        //modelo.put(nombre,"")
        // EL METODO PUT METE UN ELEMENTO EN EL FRONT!
        modelo.put("mensaje", "Este es un mensaje desde el back");
    return "inicio.html"; //retornamos el archivo
    
   //Th nos permite realizar logica en el front.  
    
    }
}
