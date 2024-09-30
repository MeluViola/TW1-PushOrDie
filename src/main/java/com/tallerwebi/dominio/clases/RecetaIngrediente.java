package com.tallerwebi.dominio.clases;

import javax.persistence.*;

@Entity
public class RecetaIngrediente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idRecetaIngrediente;

    @ManyToOne
    @JoinColumn(name = "receta_id_receta")
    private Receta receta;

    @ManyToOne
    @JoinColumn(name = "ingrediente_id_ingrediente")
    private Ingrediente ingrediente;

    // Getters y Setters
}
