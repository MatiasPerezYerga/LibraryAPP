package com.library.entidades;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Editorial {
    
     
    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    //  Las 2 siguientes lineas generan un string alfanumerico Unique
    @GeneratedValue(generator="uuid")
    @GenericGenerator(name="uuid",strategy="uuid2")
    private String id;
    private String nombre;
    
    private String alta; // VALEN DECLARA EL ALTA COMO UN BOOLEANO y HACE REFERENCIA A LA FECHA DE INICIACIÓN DE ALGO.
    private boolean active;
    
    
    public Editorial() {
        
    }

    public Editorial(String id, String nombre, String alta, boolean active) {
        this.id = id;
        this.nombre = nombre;
        this.alta = alta;
        this.active = active;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setAlta(String alta) {
        this.alta = alta;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getAlta() {
        return alta;
    }

    public boolean isActive() {
        return active;
    }

    @Override
    public String toString() {
        return "Editorial{" + "id=" + id + ", nombre=" + nombre + ", alta=" + alta + ", active=" + active + '}';
    }

    

   
}
