
package com.library.entidades;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Libro {
    // Es la llave primaria
    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    //@Enumerated(EnumType.STRING) 
    /*
    private  Sexo sexo;
     
    enum Sexo(
    MASC, FEM, OTRO
    )
    
    
    */
        
    //  Las 2 siguientes lineas generan un string alfanumerico Unique
    @GeneratedValue(generator="uuid")
    @GenericGenerator(name="uuid",strategy="uuid2")
    private String id;
    
    private Long isbn;
    private String titulo;
    private Integer anio;
    private Integer ejemplares;
    private Integer ejemplaresPrestados;
    private Integer ejemplaresRestantes;
 
    //@Temporal(TemporalType.TIMESTAMP)
    private String alta; // VALEN DECLARA EL ALTA COMO UN BOOLEANO y HACE REFERENCIA A LA FECHA DE INICIACIÓN DE ALGO.
    private boolean active;
    
    
    @OneToOne
    private Autor autor;
    
    @OneToOne
    private Editorial editorial;

    public Libro() {
    }

    public Libro(String id, Long isbn, String titulo, Integer anio, Integer ejemplares, Integer ejemplaresPrestrados, Integer ejemplaresRestantes, String alta, boolean active, Autor autor, Editorial editorial) {
        this.id = id;
        this.isbn = isbn;
        this.titulo = titulo;
        this.anio = anio;
        this.ejemplares = ejemplares;
        this.ejemplaresPrestados = ejemplaresPrestrados;
        this.ejemplaresRestantes = ejemplaresRestantes;
        this.alta = alta;
        this.active = active;
        this.autor = autor;
        this.editorial = editorial;
    }

    public String getId() {
        return id;
    }

    public Long getIsbn() {
        return isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public Integer getAnio() {
        return anio;
    }

    public Integer getEjemplares() {
        return ejemplares;
    }

    public Integer getEjemplaresPrestados() {
        return ejemplaresPrestados;
    }

    public Integer getEjemplaresRestantes() {
        return ejemplaresRestantes;
    }

    public String getAlta() {
        return alta;
    }

    public boolean isActive() {
        return active;
    }

    public Autor getAutor() {
        return autor;
    }

    public Editorial getEditorial() {
        return editorial;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setIsbn(Long isbn) {
        this.isbn = isbn;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public void setEjemplares(Integer ejemplares) {
        this.ejemplares = ejemplares;
    }

    public void setEjemplaresPrestados(Integer ejemplaresPrestrados) {
        this.ejemplaresPrestados = ejemplaresPrestrados;
    }

    public void setEjemplaresRestantes(Integer ejemplaresRestantes) {
        this.ejemplaresRestantes = ejemplaresRestantes;
    }

    public void setAlta(String alta) {
        this.alta = alta;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public void setEditorial(Editorial editorial) {
        this.editorial = editorial;
    }

    @Override
    public String toString() {
        return "Libro{" + "id=" + id + ", isbn=" + isbn + ", titulo=" + titulo + ", anio=" + anio + ", ejemplares=" + ejemplares + ", ejemplaresPrestrados=" + ejemplaresPrestados + ", ejemplaresRestantes=" + ejemplaresRestantes + ", alta=" + alta + ", active=" + active + ", autor=" + autor + ", editorial=" + editorial + '}';
    }

    
    
    
    
}
