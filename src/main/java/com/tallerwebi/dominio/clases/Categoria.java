package com.tallerwebi.dominio.clases;

import javax.persistence.*;
import java.util.List;

@Entity
public class Categoria {


        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int idCategoria;

        private String nombreCategoria;



    }


