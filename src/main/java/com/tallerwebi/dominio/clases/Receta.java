package com.tallerwebi.dominio.clases;


import javax.persistence.*;
import java.util.List;

@Entity
public class Receta {
    private String nombre;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idReceta;
    private int puntaje;
    private String instrucciones;

    @ManyToOne
    @JoinColumn(name = "categoria_id_categoria")
    private Categoria categoria;

    @OneToMany(mappedBy = "receta")
    private List<RecetaIngrediente> recetaIngredientes;

    public Receta(String nombre, int puntaje, String instrucciones, Categoria categoria) {
        this.nombre = nombre;
        this.puntaje = puntaje;
        this.instrucciones = instrucciones;
        this.categoria = categoria;

    }
    public Receta(String nombre, int puntaje, String instrucciones) {
        this.nombre = nombre;
        this.puntaje = puntaje;
        this.instrucciones = instrucciones;


    }

    public Receta() {

    }


    public int getIdReceta() {
        return idReceta;
    }

    public void setIdReceta(int idReceta) {
        this.idReceta = idReceta;
    }


    public String getNombre() {
        return nombre;
    }

    public int getPuntaje() {
        return puntaje;
    }
    public String getInstrucciones() {
        return instrucciones;
    }
}
