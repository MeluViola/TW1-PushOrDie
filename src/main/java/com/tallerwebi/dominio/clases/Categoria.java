package com.tallerwebi.dominio.clases;

import javax.persistence.*;
import java.util.List;

@Entity
public class Categoria {


        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int idCategoria;

        @Column(name = "nombre_categoria")
        private String nombreCategoria;


    public Categoria() {}

    public Categoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }
}



