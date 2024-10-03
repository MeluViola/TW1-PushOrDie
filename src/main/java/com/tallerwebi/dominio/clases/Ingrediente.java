package com.tallerwebi.dominio.clases;

import javax.persistence.*;
import java.util.List;

@Entity
public class Ingrediente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idIngrediente;

    @Column(name = "nombre_ingrediente",nullable = false, length = 45)
    private String nombreIngrediente;

    @OneToMany(mappedBy = "ingrediente")
    private List<RecetaIngrediente> recetaIngredientes;

    // Getters y Setters
}
